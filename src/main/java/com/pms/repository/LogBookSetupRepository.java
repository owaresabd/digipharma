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

import com.pms.model.EquipmentInfo;
import com.pms.model.LogBookSetupInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class LogBookSetupRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<LogBookSetupInfo> findLogBookSetupInfos(String status,String isApllicable) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id,ud_logbook_id, logbook_name, document_no, revision_no, effective_date, logbook_type, remarks, status,is_applicable "
				+ " FROM lims_log_book_infos WHERE company_id = ? "
				+ " and status="+ (status != null ? "'" + status + "'" : "status") + " "
				+ " and is_applicable="+ (isApllicable != null ? "'" + isApllicable + "'" : "is_applicable") + " order by ud_logbook_id ",
				new Object[] { user.getCompanyId() }, new LogBookSetupInfosRowMapper());
	}
	
	public List<LogBookSetupInfo> validateDocumentNo(String documentNo) {
		List<LogBookSetupInfo> entityList = jdbcTemplate.query("SELECT id,ud_logbook_id, logbook_name, document_no, revision_no, effective_date, logbook_type, remarks, status,is_applicable "
				+ " FROM lims_log_book_infos  where document_no = ? ",
				new Object[] { documentNo }, new LogBookSetupInfosRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}
	
	public void saveLogBookSetupInfo(LogBookSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_book_infos ( ud_logbook_id,logbook_name, document_no, revision_no, effective_date, logbook_type, remarks, status,is_applicable, " 
					+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getLogbookId(),info.getLogbookName(), info.getDocumentNo(), info.getRevisionNo(), info.getEffectiveDate(), info.getLogbookType(), 
					info.getRemarks(), info.getStatus(),info.getIsApplicable(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_book_infos SET ud_logbook_id =?,logbook_name =?, document_no =?, revision_no =?, effective_date =?, logbook_type = ?, "
					+ " remarks = ?, status = ?,is_applicable=?, updated_by = ?, updated_at = ? where id = ?",
					info.getLogbookId(),info.getLogbookName(), info.getDocumentNo(), info.getRevisionNo(), info.getEffectiveDate(), info.getLogbookType(), 
					info.getRemarks(), info.getStatus(),info.getIsApplicable(), user.getId(), time, info.getId());
				}
	}
	public List<EquipmentInfo> findUnassignedEquipment(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id,equipment_id, equipment_name FROM lims_equipment_infos "
				+ " WHERE id NOT IN(SELECT equipment_id FROM lims_logbook_equipment_mapping_infos WHERE logbook_id=? and  company_id = ?)",
				new Object[] { id,user.getCompanyId() }, new EquipmentRowMapper());
	}
	public List<EquipmentInfo> findAssignedEquipment(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id,equipment_id, equipment_name FROM lims_equipment_infos "
				+ " WHERE id  IN(SELECT equipment_id FROM lims_logbook_equipment_mapping_infos WHERE logbook_id=? and  company_id = ?)",
				new Object[] { id,user.getCompanyId() }, new EquipmentRowMapper());
	}
	public void saveEquipmentMapping(UUID equipmentId, UUID logbookId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				"insert into lims_logbook_equipment_mapping_infos(logbook_id, equipment_id, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?)",
				logbookId,equipmentId,user.getCompanyId(),user.getId(),time);
	}

	public void deleteEquipmentMapping(UUID equipmentId, UUID logbookId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		System.out.println();
		jdbcTemplate.update("DELETE FROM lims_logbook_equipment_mapping_infos WHERE equipment_id = ? "
				+ " AND logbook_id = ? AND  company_id = ?", equipmentId, logbookId,user.getCompanyId());
		
	}
	class EquipmentRowMapper implements RowMapper<EquipmentInfo> {

		@Override
		public EquipmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentInfo info = new EquipmentInfo();
			
			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId(rs.getString("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			
			return info;
		}
	}

	class LogBookSetupInfosRowMapper implements RowMapper<LogBookSetupInfo> {

		@Override
		public LogBookSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogBookSetupInfo info = new LogBookSetupInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setLogbookId(rs.getString("ud_logbook_id"));
			info.setLogbookName(rs.getString("logbook_name"));
			info.setDocumentNo(rs.getString("document_no"));
			info.setRevisionNo(rs.getString("revision_no"));
			info.setEffectiveDate(rs.getDate("effective_date"));
			info.setLogbookType(rs.getString("logbook_type"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			info.setIsApplicable(rs.getString("is_applicable"));
			return info;
		}
	}
	
	
}
