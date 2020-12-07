package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pms.model.RefStandSetupInfo;
import com.pms.model.RefStandardInfo;
import com.pms.model.RefStandardIssueInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class RefStandardRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	
	public List<RefStandardInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query("SELECT id, ref_standard_id, rcv_date, source_supplier_id,"
				+ " (select source_name from lims_manufacturer_infos a where a.id=c.source_supplier_id) source_name,id_type,"
				+ " cat_no, lot_no, batch_no, potency, rcv_qty, unit_id, fnc_unit_nm(unit_id) unit_nm, valid_to_date, storage_condition,"
				+ " (select storage_desc from lims_storage_infos b where b.id=c.storage_condition) storage_name, "
				+ " (select ref_satnadrd_name from lims_ref_standard_setup_infos d where d.id=c.ref_standard_id) ref_standard_name,"
				+ " abs(c.valid_to_date :: date - now() :: date) as diff_days "
				+ " from lims_ref_standard_infos c where company_id= ? ",
				new Object[] { user.getCompanyId() },new RefStandardInfoRowMapper());
	}
	public List<RefStandardInfo> findRefNotificationInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query("SELECT id, ref_standard_id, rcv_date, source_supplier_id,"
				+ " (select source_name from lims_manufacturer_infos a where a.id=c.source_supplier_id) source_name,id_type,"
				+ " cat_no, lot_no, batch_no, potency, rcv_qty, unit_id, fnc_unit_nm(unit_id) unit_nm, valid_to_date, storage_condition,"
				+ " (select storage_desc from lims_storage_infos b where b.id=c.storage_condition) storage_name, "
				+ " (select ref_satnadrd_name from lims_ref_standard_setup_infos d where d.id=c.ref_standard_id) ref_standard_name,"
				+ " abs(c.valid_to_date :: date - now() :: date) as diff_days "
				+ " from lims_ref_standard_infos c "
				+ " where company_id=? "
				+ " and c.valid_to_date is not null "
				+ " and abs(c.valid_to_date :: date - now() :: date) <=180 ",
				new Object[] { user.getCompanyId() },new RefStandardInfoRowMapper());
	}
	
	public RefStandardInfo findReferenceById(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return (RefStandardInfo)jdbcTemplate.queryForObject("SELECT id, ref_standard_id, rcv_date, source_supplier_id,"
				+ " (select source_name from lims_manufacturer_infos a where a.id=c.source_supplier_id) source_name,id_type,"
				+ " cat_no, lot_no, batch_no, potency, rcv_qty, unit_id, fnc_unit_nm(unit_id) unit_nm, valid_to_date, storage_condition,"
				+ " (select storage_desc from lims_storage_infos b where b.id=c.storage_condition) storage_name, "
				+ " (select ref_satnadrd_name from lims_ref_standard_setup_infos d where d.id=c.ref_standard_id) ref_standard_name, "
				+ " abs(c.valid_to_date :: date - now() :: date) as diff_days "
				+ " from lims_ref_standard_infos c where ref_standard_id = ?", new Object[] { id }, new RefStandardInfoRowMapper());
	}
	
	public List<RefStandardIssueInfo> findAllReferenceIssue() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query("SELECT id, ref_standard_id, used_date, used_qty, purpose, used_by, remarks, unit_id, fnc_unit_nm(unit_id) unit_nm,"
				+ " (select ref_satnadrd_name from lims_ref_standard_setup_infos b where b.id=c.ref_standard_id) refStandard_name "
				+ " from lims_ref_standard_details_infos c where company_id= ? ",
				new Object[] { user.getCompanyId() },new RefStandardIssueRowMapper());
	}
	
	public List<RefStandSetupInfo> findRefStandSetupInfo(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, ref_satnadrd_name, status from lims_ref_standard_setup_infos where company_id = ? "
				+ "and status=" + (status != null ? "'" + status + "'" : "status") + " order by ref_satnadrd_name ",
				new Object[] { user.getCompanyId() }, new RefStandSetupInfoRowMapper());
	}
	
	public void saveRefStandSetupInfo(RefStandSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_ref_standard_setup_infos ( ref_satnadrd_name, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?)",
					info.getRefStandardName(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_ref_standard_setup_infos SET ref_satnadrd_name = ?, status = ? where id = ?",
					info.getRefStandardName(), info.getStatus(), info.getId());
		}
	}
	
	public void saveRefStandardInfo(RefStandardInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_ref_standard_infos( ref_standard_id, rcv_date, source_supplier_id,id_type, cat_no, lot_no, batch_no,"
					+ " potency, rcv_qty, unit_id, valid_to_date, storage_condition, "
					+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getRefStandardId(),info.getRcvDate(), info.getSourceSupplierId(),info.getIdType(), info.getCatNo(), info.getLotNo(), info.getBatchNo(),
					info.getPotency(), info.getRcvQty(), info.getUnitId(), info.getValidDate(), info.getStorageCondition(), 
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_ref_standard_infos SET ref_standard_id = ?, rcv_date = ?, source_supplier_id = ?,id_type = ?, cat_no = ?, lot_no= ?,"
					+ " batch_no = ?, potency = ?, rcv_qty =?, unit_id =?, valid_to_date = ?, storage_condition = ?, "
					+ " updated_by = ?, updated_at = ? where id = ?",
					info.getRefStandardId(),info.getRcvDate(), info.getSourceSupplierId(),info.getIdType(), info.getCatNo(), info.getLotNo(), info.getBatchNo(),
					info.getPotency(), info.getRcvQty(), info.getUnitId(), info.getValidDate(), info.getStorageCondition(), 
					user.getId(), time, info.getId());

		}
	}
	
	public void saveRefStandardIssueInfo(RefStandardIssueInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_ref_standard_details_infos( ref_standard_id, used_date, used_qty, purpose, used_by, remarks, "
					+ " unit_id, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getRefStandardChildId(),info.getUsedDate(), info.getUsedQty(), info.getPurpose(), user.getId(), info.getRemarks(),
					info.getUnitId(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_ref_standard_details_infos SET ref_standard_id = ?, used_date = ?, used_qty = ?, purpose = ?, used_by= ?,"
					+ " remarks = ?, unit_id =?, updated_by = ?, updated_at = ? where id = ?",
					info.getRefStandardChildId(),info.getUsedDate(), info.getUsedQty(), info.getPurpose(), user.getId(), info.getRemarks(), 
					info.getUnitId(), user.getId(), time, info.getId());
		}
	}
	
	public List<RefStandSetupInfo> validateRefStandName(String refStandName) {
		List<RefStandSetupInfo> entityList = jdbcTemplate.query("select id, ref_satnadrd_name, status from lims_ref_standard_setup_infos where ref_satnadrd_name = ?",
				new Object[] { refStandName }, new RefStandSetupInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	
	class RefStandardInfoRowMapper implements RowMapper<RefStandardInfo> {

		@Override
		public RefStandardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RefStandardInfo info = new RefStandardInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRefStandardId((UUID) rs.getObject("ref_standard_id"));
			info.setRcvDate(rs.getDate("rcv_date"));
			info.setSourceSupplierId((UUID) rs.getObject("source_supplier_id"));
			info.setSourceSupplierName(rs.getString("source_name"));
			info.setIdType(rs.getString("id_type"));
			info.setCatNo(rs.getString("cat_no"));
			info.setLotNo(rs.getString("lot_no"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setPotency(rs.getString("potency"));
			info.setRcvQty(rs.getString("rcv_qty"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_nm"));
			info.setValidDate(rs.getDate("valid_to_date"));
			info.setStorageCondition((UUID) rs.getObject("storage_condition"));
			info.setStorageCondName(rs.getString("storage_name"));
			info.setRefStandardName(rs.getString("ref_standard_name"));
			info.setDiffDays(rs.getInt("diff_days"));
			return info;
		}
	}
	
	class RefStandardIssueRowMapper implements RowMapper<RefStandardIssueInfo> {

		@Override
		public RefStandardIssueInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RefStandardIssueInfo info = new RefStandardIssueInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRefStandardChildId((UUID) rs.getObject("ref_standard_id"));
			info.setUsedDate(rs.getDate("used_date"));
			info.setUsedQty(rs.getString("used_qty"));
			info.setPurpose(rs.getString("purpose"));
			info.setUsedBy((UUID) rs.getObject("used_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_nm"));
			info.setRefStandardName(rs.getString("refStandard_name"));
			return info;
		}
	}
	
	class RefStandSetupInfoRowMapper implements RowMapper<RefStandSetupInfo> {

		@Override
		public RefStandSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RefStandSetupInfo info = new RefStandSetupInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRefStandardName(rs.getString("ref_satnadrd_name"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	

	
}
