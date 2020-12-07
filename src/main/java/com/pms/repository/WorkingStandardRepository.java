package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.CommonInfo;
import com.pms.model.User;
import com.pms.model.WorkingStandardInfo;
import com.pms.model.WorkingStandardIssueInfo;
import com.pms.model.WorkingStandardRequestInfo;
import com.pms.service.IUserService;

@Repository
@Transactional
public class WorkingStandardRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<WorkingStandardInfo> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, work_standard_name, arn_no, no_of_vial, date_of_prep, valid_to_date,"
				+ " assay_desc, ref_stand_id, storage_condition,ws_request_id,amount_per_vial,"
				+ " (select storage_desc from lims_storage_infos b where b.id=c.storage_condition) storage_name "
				+ " from lims_work_standard_infos c where company_id = ?",
						new Object[] { user.getCompanyId() }, new StandardInfoRowMapper());
	}
	public List<WorkingStandardRequestInfo> findAllWsInfos(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT b.id, material_id, b.remarks, b.status,a.material_name, a.material_code,a. material_type_id,"
				+ " fnc_material_type_nm(material_type_id) type_name,b.kept_amount,fnc_unit_nm(a.unit_id) unit_name "
				+ " FROM lims_material_infos a, lims_work_standard_req_infos b " + 
				"   where a.id=b.material_id "
				+ " and b.status=" + (status != null ? "'" + status +"'" :"b.status") +" "
				+ " and  a.company_id = ?",
				 new Object[] { user.getCompanyId() }, new WorkingStandardReqInfoRowMapper());
	}

	public WorkingStandardInfo findWorkStandardById(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return (WorkingStandardInfo)jdbcTemplate.queryForObject("SELECT id, work_standard_name, arn_no, no_of_vial,"
				+ " date_of_prep, valid_to_date, assay_desc, ref_stand_id, storage_condition,ws_request_id,"
				+ " (select storage_desc from lims_storage_infos b where b.id=c.storage_condition) storage_name "
				+ " from lims_work_standard_infos c where id = ?", new Object[] { id }, new StandardInfoRowMapper());
	}
	
	public List<WorkingStandardIssueInfo> findAllWorkIssue() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query("SELECT id, work_standard_id, vial_no, opening_date, used_date, used_by, validity_date, remarks, "
				+ " (select work_standard_name from lims_work_standard_infos b where b.id=c.work_standard_id) workStandard_name "
				+ " from lims_work_standard_details_infos c where company_id= ? ",
				new Object[] { user.getCompanyId() },new WorkStandardIssueRowMapper());
	}

	public void saveWorkStandardInfo(WorkingStandardInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		if (info.getId() == null) {
			info.setArnNo(arnNoGen());//this line for system generated ARn NO
			jdbcTemplate.update(
					"INSERT INTO lims_work_standard_infos (work_standard_name, arn_no, no_of_vial,amount_per_vial,date_of_prep, valid_to_date,"
					+ "  assay_desc, storage_condition, ref_stand_id,ws_request_id, company_id,created_by,created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					  info.getWorkStandardName(),info.getArnNo(),info.getNoOfVial(),info.getVialAmount(),info.getDateOfPrep(),info.getValidToDate(),
					  info.getAssayDesc(),info.getStorageCondition(),info.getRefStandId(),info.getWsRequestId(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_work_standard_infos SET work_standard_name = ?,arn_no=?,no_of_vial=?,amount_per_vial=?,date_of_prep=?,"
					+ "  valid_to_date = ?,assay_desc = ?,storage_condition=?, ref_stand_id=?,ws_request_id=? where id = ?",
					info.getWorkStandardName(),info.getArnNo(),info.getNoOfVial(),info.getVialAmount(),info.getDateOfPrep(),info.getValidToDate(),
					info.getAssayDesc(),info.getStorageCondition(),info.getRefStandId(),info.getWsRequestId(), info.getId());
		}
	}
	public   String  arnNoGen() {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udSampleId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		User user = userService.getCurrentUser();
		CommonInfo info = jdbcTemplate.queryForObject(" SELECT (COALESCE(CAST(MAX(RIGHT(arn_no,4)) as integer),0)+1) max_id "
				+ " from lims_work_standard_infos  "
				+ " where company_id=?",new Object[] { user.getCompanyId()}, new IdInfoRowMapper());
		udSampleId.append("ARN-").append("WS-") .append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		
		
		
		return udSampleId.toString();
	}
	public void saveWorkStandardIssueInfo(WorkingStandardIssueInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_work_standard_details_infos( work_standard_id, vial_no, opening_date, used_date, used_by, validity_date, "
					+ " remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getWorkStandardChildId(),info.getVialNo(), info.getOpeningDate(), info.getUsedDate(), user.getId(), info.getValidityDate(), 
					info.getRemarks(),user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_work_standard_details_infos SET work_standard_id = ?, vial_no = ?, opening_date = ?, used_date = ?, used_by= ?,"
					+ " validity_date =?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getWorkStandardChildId(),info.getVialNo(), info.getOpeningDate(), info.getUsedDate(), user.getId(), info.getValidityDate(),  
					info.getRemarks(), user.getId(), time, info.getId());
		}
	}
	
	public void saveWsRequestInfos(WorkingStandardRequestInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"insert into lims_work_standard_req_infos( material_id,kept_amount, remarks, status, company_id, created_by, created_at)"
					+ "  VALUES (?,?,?,?,?,?,?)",
					info.getMaterialId(),info.getKeptAmount(),info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"update lims_work_standard_req_infos set material_id = ?,kept_amount=?,remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getMaterialId(), info.getKeptAmount(),info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	

	class StandardInfoRowMapper implements RowMapper<WorkingStandardInfo> {
		@Override
		public WorkingStandardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkingStandardInfo info = new WorkingStandardInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setWorkStandardName(rs.getString("work_standard_name"));
			info.setArnNo(rs.getString("arn_no"));
			info.setNoOfVial(rs.getString("no_of_vial"));
			info.setVialAmount(rs.getString("amount_per_vial"));
			info.setDateOfPrep(rs.getDate("date_of_prep"));
			info.setValidToDate(rs.getDate("valid_to_date"));
			info.setAssayDesc(rs.getString("assay_desc"));
			info.setRefStandId((UUID) rs.getObject("ref_stand_id"));
			info.setStorageCondition((UUID) rs.getObject("storage_condition"));
			info.setStorageCondName(rs.getString("storage_name"));
			info.setWsRequestId((UUID) rs.getObject("ws_request_id"));
			return info;
		}
	}
	
	class WorkingStandardReqInfoRowMapper implements RowMapper<WorkingStandardRequestInfo> {
		@Override
		public WorkingStandardRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkingStandardRequestInfo info = new WorkingStandardRequestInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID) rs.getObject("material_type_id"));
			info.setMaterialTypeName(rs.getString("type_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			info.setKeptAmount(rs.getString("kept_amount"));
			info.setUnitName(rs.getString("unit_name"));
			
			return info;
		}
	}
	
	class WorkStandardIssueRowMapper implements RowMapper<WorkingStandardIssueInfo> {

		@Override
		public WorkingStandardIssueInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkingStandardIssueInfo info = new WorkingStandardIssueInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setWorkStandardChildId((UUID) rs.getObject("work_standard_id"));
			info.setVialNo(rs.getString("vial_no"));
			info.setOpeningDate(rs.getDate("opening_date"));
			info.setUsedDate(rs.getDate("used_date"));
			info.setValidityDate(rs.getDate("validity_date"));
			info.setUsedBy((UUID) rs.getObject("used_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setWorkStandardName(rs.getString("workStandard_name"));
			return info;
		}
	}
	
	class IdInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setMaxId(rs.getString("max_id"));
			
			return info;
			
		}
		
	}
}
