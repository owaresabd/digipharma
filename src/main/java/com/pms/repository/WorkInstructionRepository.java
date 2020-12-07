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

import com.pms.model.User;
import com.pms.model.WorkInstructionInfo;
import com.pms.model.WorkInstructionRefInfo;
import com.pms.service.IUserService;

@Repository
@Transactional
public class WorkInstructionRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<WorkInstructionInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_work_instruction_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by instruction_name ",
						new Object[] { user.getCompanyId() }, new InstructionRowMapper());
	}
	public List<WorkInstructionRefInfo> findRefAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id,work_ins_id,fnc_inst_name(work_ins_id) inst_name,work_ins_ud_id,revision_no,effective_date,work_ins_doc_name,remarks,status from lims_work_instruction_ref_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status"),
						new Object[] { user.getCompanyId() }, new InstructionRefRowMapper());
	}
	public List<WorkInstructionInfo> findUdNo(UUID insId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_work_instruction_infos where company_id = ? and id= ? ",
						new Object[] { user.getCompanyId(),insId }, new InstructionRowMapper());
	}
	public List<WorkInstructionInfo> findAllWorkInstruction() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		String query = "select id, instruction_name from lims_work_instruction_infos where company_id = '"
					+ user.getCompanyId() + "' order by instruction_name";
		return jdbcTemplate.query(query, new RowMapper<WorkInstructionInfo>() {
			public WorkInstructionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WorkInstructionInfo info = new WorkInstructionInfo();
				info.setId(UUID.fromString(rs.getString("id")));
				info.setInstructionName(rs.getString("instruction_name"));
				return info;
			}
		});
	}

	public void saveWorkInstructionInfos(WorkInstructionInfo info,UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_work_instruction_infos ( id,instruction_name,work_ins_ud_id,work_ins_doc_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)",
							idRandom,info.getInstructionName(),info.getInsUdId(),info.getWorkInsDocName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_work_instruction_infos SET instruction_name = ?,work_ins_ud_id= ?, work_ins_doc_name= ?, remarks = ?, status = ? where id = ?",
					info.getInstructionName(),info.getInsUdId(),info.getWorkInsDocName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}
	public void saveWorkInstructionRefInfos(WorkInstructionRefInfo info,UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_work_instruction_ref_infos ( id,work_ins_id,work_ins_ud_id,revision_no,effective_date,work_ins_doc_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)",
							idRandom,info.getWorkInsId(),info.getWorkInsUdId(),info.getRevisionNo(),info.getEffectiveDate(),info.getWorkInsDocName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_work_instruction_ref_infos SET work_ins_id = ?,work_ins_ud_id = ?,revision_no = ?,effective_date = ?,work_ins_doc_name= ?, remarks = ?, status = ? where id = ?",
					info.getWorkInsId(),info.getWorkInsUdId(),info.getRevisionNo(),info.getEffectiveDate(),info.getWorkInsDocName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}
	public void saveWorkInstructionFile(WorkInstructionInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_work_instruction_info ( instruction_name, remarks, status, file_content, file_name, company_id, created_by, created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?)",
					info.getInstructionName(), info.getRemarks(), info.getStatus(), info.getFileContent(), info.getFileName(), 
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_work_instruction_info SET instruction_name = ?, remarks = ?, status = ?  where id = ?", 
					info.getInstructionName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}
	
	public List<WorkInstructionInfo> validateInstNo(String instNo) {
		List<WorkInstructionInfo> entityList = jdbcTemplate.query("select * from lims_work_instruction_infos where work_ins_ud_id = ?",
				new Object[] { instNo }, new InstructionRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	public List<WorkInstructionInfo> validateInstructionName(String instructionName) {
		List<WorkInstructionInfo> entityList = jdbcTemplate.query("select * from lims_work_instruction_infos where instruction_name = ?",
				new Object[] { instructionName }, new InstructionRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class InstructionRowMapper implements RowMapper<WorkInstructionInfo> {
		@Override
		public WorkInstructionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkInstructionInfo info = new WorkInstructionInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setInstructionName(rs.getString("instruction_name"));
			info.setInsUdId(rs.getString("work_ins_ud_id"));
			info.setWorkInsDocName(rs.getString("work_ins_doc_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class InstructionRefRowMapper implements RowMapper<WorkInstructionRefInfo> {
		@Override
		public WorkInstructionRefInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkInstructionRefInfo info = new WorkInstructionRefInfo();
			
			
			info.setId((UUID) rs.getObject("id"));
			info.setWorkInsId((UUID) rs.getObject("work_ins_id"));
			info.setInsName(rs.getString("inst_name"));
			info.setWorkInsUdId(rs.getString("work_ins_ud_id"));
			info.setRevisionNo(rs.getString("revision_no"));
			info.setEffectiveDate(rs.getDate("effective_date"));
			info.setWorkInsDocName(rs.getString("work_ins_doc_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
}
