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

import com.pms.model.TestMethodInfo;
import com.pms.model.TestMethodRefInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class TestMethodRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<TestMethodInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_test_method_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by method_name ",
						new Object[] { user.getCompanyId() }, new MethodInfoRowMapper());
	}
	public List<TestMethodInfo> findUdNo(UUID methodId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_test_method_infos where company_id = ? and id= ? ",
						new Object[] { user.getCompanyId(),methodId }, new MethodInfoRowMapper());
	}
	public List<TestMethodRefInfo> findRefAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id,method_id,fnc_test_method_name(method_id) test_method_name,"
				+ " method_ud_id,revision_no,effective_date,method_doc_name,status, remarks "
				+ " from lims_test_method_ref_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by method_id ",
						new Object[] { user.getCompanyId() }, new MethodRefInfoRowMapper());
	}
	public void saveTestMethodInfos(TestMethodInfo info,UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_test_method_infos ( id,method_name,method_ud_id, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?)",
							idRandom,info.getMethodName(),info.getMethodUdId(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_test_method_infos SET method_name = ?,method_ud_id=?, remarks = ?, status = ? where id = ?",
					info.getMethodName(),info.getMethodUdId(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public void saveTestMethodRefInfos(TestMethodRefInfo info,UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_test_method_ref_infos ( id,method_id,method_ud_id,Revision_no,effective_date,method_doc_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)",
							idRandom,info.getMethodId(),info.getMethodUdId(),info.getRevisionNo(),info.getEffectiveDate(),info.getTestDocName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			if (info.getTestDocName() != null) {
			jdbcTemplate.update(
					"UPDATE lims_test_method_ref_infos SET method_id = ?,method_ud_id = ?,Revision_no= ?,effective_date=?, method_doc_name= ?, remarks = ?, status = ? where id = ?",
					info.getMethodId(),info.getMethodUdId(),info.getRevisionNo(),info.getEffectiveDate(),info.getTestDocName(), info.getRemarks(), info.getStatus(), info.getId());
			}else {
				jdbcTemplate.update(
						"UPDATE lims_test_method_ref_infos SET method_id = ?,method_ud_id = ?,Revision_no= ?,effective_date=?, remarks = ?, status = ? where id = ?",
						info.getMethodId(),info.getMethodUdId(),info.getRevisionNo(),info.getEffectiveDate(), info.getRemarks(), info.getStatus(), info.getId());
			}
		}
	}
	public List<TestMethodInfo> validateMethodName(String methodName) {
		List<TestMethodInfo> entityList = jdbcTemplate.query("select * from lims_test_method_infos where method_name = ?",
				new Object[] { methodName }, new MethodInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}
	
	public List<TestMethodInfo> validateMethodNo(String methodNo) {
		List<TestMethodInfo> entityList = jdbcTemplate.query("select * from lims_test_method_infos where method_ud_id = ?",
				new Object[] { methodNo }, new MethodInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class MethodInfoRowMapper implements RowMapper<TestMethodInfo> {
		@Override
		public TestMethodInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestMethodInfo methodInfo = new TestMethodInfo();

			methodInfo.setId((UUID) rs.getObject("id"));
			methodInfo.setMethodName(rs.getString("method_name"));
			methodInfo.setMethodUdId(rs.getString("method_ud_id"));
			methodInfo.setRemarks(rs.getString("remarks"));
			methodInfo.setStatus(rs.getString("status"));
			return methodInfo;
		}
	}
	class MethodRefInfoRowMapper implements RowMapper<TestMethodRefInfo> {
		@Override
		public TestMethodRefInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestMethodRefInfo methodRefInfo = new TestMethodRefInfo();
			
			
			methodRefInfo.setId((UUID) rs.getObject("id"));
			methodRefInfo.setMethodId((UUID) rs.getObject("method_Id"));
			methodRefInfo.setMethodName(rs.getString("test_method_name"));
			methodRefInfo.setMethodUdId(rs.getString("method_ud_id"));
			methodRefInfo.setRevisionNo(rs.getString("revision_no"));
			methodRefInfo.setEffectiveDate(rs.getDate("effective_date"));
			methodRefInfo.setTestDocName(rs.getString("method_doc_name"));
			methodRefInfo.setRemarks(rs.getString("remarks"));
			methodRefInfo.setStatus(rs.getString("status"));
			return methodRefInfo;
		}
	}
	
}
