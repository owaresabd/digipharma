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

import com.pms.model.MediumReagentInfo;
import com.pms.model.SurgicalProductInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class SurgicalProductRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<SurgicalProductInfo> findAll(String status,String typeCode) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("Select id, product_code, product_name,type_code,reagent_type, unit_id,fnc_unit_nm(unit_id) unit_name, status "
				+ " from lims_surgical_product_infos  where company_id = ? and type_code=" + (typeCode != null ? "'" + typeCode + "'" : "type_code") + " "
				+ " and status=" + (status != null ? "'" + status + "'" : "status") + "  ",
				new Object[] { user.getCompanyId() }, new SurgicalProductInfoRowMapper());
	}

	

	public void saveSurgicalProductInfos(SurgicalProductInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_surgical_product_infos (product_code, product_name,type_code,reagent_type, unit_id,status,company_id,created_by,created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)",
					  info.getProductCode(),info.getProductName(),"O",info.getReagentType(),info.getUnitId(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_surgical_product_infos SET product_code = ?,product_name=?,reagent_type=?,unit_id=?, status = ?,updated_by=?, updated_at=? where id = ?",
					info.getProductCode(),info.getProductName(),info.getReagentType(),info.getUnitId(), info.getStatus(),user.getId(), time, info.getId());
		}
	}
	
	public void saveReagentInfos(SurgicalProductInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_surgical_product_infos (product_code, product_name,type_code,reagent_type, unit_id,status,company_id,created_by,created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)",
					  info.getProductCode(),info.getProductName(),"R",info.getReagentType(),info.getUnitId(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_surgical_product_infos SET product_code = ?,product_name=?,reagent_type=?,unit_id=?, status = ?,updated_by=?, updated_at=? where id = ?",
					info.getProductCode(),info.getProductName(),info.getReagentType(),info.getUnitId(), info.getStatus(),user.getId(), time, info.getId());
		}
	}

	public List<MediumReagentInfo> findMediumReagent(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("Select id, reagent_name, code_no, status  "
				+ " from lims_medium_reagent_infos  where company_id = ? "
				+ " and status=" + (status != null ? "'" + status + "'" : "status") + "  ",
				new Object[] { user.getCompanyId() }, new MediumReagentInfoRowMapper());
	}
	
	public MediumReagentInfo getBatchInfo(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.queryForObject("Select id, reagent_name, code_no, status ,"
				+ " (select coalesce(max(batch_seq_no),0)+1 from log_mc_medium_reagent_infos "
				+ " where reagent_id=a.id  and created_at :: date=now():: date )  batch_no  "
				+ " from lims_medium_reagent_infos a  where company_id = ?  and id='"+id+"'  ",
				new Object[] { user.getCompanyId() }, new BtachInfoRowMapper());
	}
	public void saveMediumReagentInfos(MediumReagentInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"insert into lims_medium_reagent_infos (reagent_name, code_no,status,company_id,created_by,created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					  info.getReagentName(),info.getCodeNo(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"update lims_medium_reagent_infos SET reagent_name = ?,code_no=?,status = ?,updated_by=?, updated_at=? where id = ?",
					info.getReagentName(),info.getCodeNo(), info.getStatus(),user.getId(), time, info.getId());
		}
	}
	class SurgicalProductInfoRowMapper implements RowMapper<SurgicalProductInfo> {
		@Override
		public SurgicalProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SurgicalProductInfo info = new SurgicalProductInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setProductCode(rs.getString("product_code"));
			info.setProductName(rs.getString("product_name"));
			info.setReagentType(rs.getString("reagent_type"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setTypeCode(rs.getString("type_code"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class MediumReagentInfoRowMapper implements RowMapper<MediumReagentInfo> {
		@Override
		public MediumReagentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MediumReagentInfo info = new MediumReagentInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setReagentName(rs.getString("reagent_name"));
			info.setCodeNo(rs.getString("code_no"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class BtachInfoRowMapper implements RowMapper<MediumReagentInfo> {
		@Override
		public MediumReagentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MediumReagentInfo info = new MediumReagentInfo();
			DateFormat df = new SimpleDateFormat("ddMMyy");
			String dayMonthYear = df.format(Calendar.getInstance().getTime());
			info.setId((UUID) rs.getObject("id"));
			info.setReagentName(rs.getString("reagent_name"));
			info.setCodeNo(rs.getString("code_no"));
			info.setStatus(rs.getString("status"));
			info.setBatchSeqNo(rs.getString("batch_no"));
			if (rs.getString("batch_no").length() <= 2) {
				
				info.setLabBatchNo(rs.getString("code_no")+ dayMonthYear+ StringUtils.leftPad(rs.getString("batch_no"), 2, "0"));
				
			} else {
				info.setLabBatchNo(rs.getString("code_no") + dayMonthYear
						+ StringUtils.leftPad(rs.getString("batch_no"), rs.getString("batch_no").length(), "0"));
				

			}
			//info.setLabBtachNo(rs.getString("batch_no"));
			return info;
		}
	}
	
	
}
