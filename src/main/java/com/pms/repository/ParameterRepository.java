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

import com.pms.model.ParameterInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ParameterRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ParameterInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select id, test_parameter_no, test_parameter_name,iso_scope, remarks, status from lims_test_parameter_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by test_parameter_name ",
				new Object[] { user.getCompanyId() }, new ParameterInfoRowMapper());
	}

	public List<ParameterInfo> findParameterInfoById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query("select id, test_parameter_no, test_parameter_name,iso_scope, remarks, status from lims_test_parameter_infos where id = ? and company_id = ?",
				new Object[] { id, user.getCompanyId() }, new ParameterInfoRowMapper());
	}

	public void saveParameterInfos(ParameterInfo parameterInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (parameterInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_test_parameter_infos(test_parameter_no, test_parameter_name,iso_scope, "
				+ " remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?)",
					parameterInfo.getTestParameterNo().toUpperCase(), parameterInfo.getTestParameterName(),parameterInfo.getIsoScope(), parameterInfo.getRemarks(),
					parameterInfo.getStatus(), user.getCompanyId(), user.getId(),  time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_test_parameter_infos SET test_parameter_no = ?, test_parameter_name = ?,iso_scope=?,remarks = ?, status = ?,updated_by=?, updated_at=? where id = ?",
					parameterInfo.getTestParameterNo().toUpperCase(), parameterInfo.getTestParameterName(),parameterInfo.getIsoScope(), parameterInfo.getRemarks(),
					parameterInfo.getStatus(),user.getId(), time, parameterInfo.getId());
		}
	}

	public List<ParameterInfo> validateTestParameterNo(String parameterNo) {
		List<ParameterInfo> entityList = jdbcTemplate.query("select * from lims_test_parameter_infos where test_parameter_no = ?",
				new Object[] { parameterNo }, new ParameterInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class ParameterInfoRowMapper implements RowMapper<ParameterInfo> {

		@Override
		public ParameterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ParameterInfo parameterInfo = new ParameterInfo();

			parameterInfo.setId((UUID) rs.getObject("id"));
			parameterInfo.setTestParameterNo(rs.getString("test_parameter_no"));
			parameterInfo.setTestParameterName(rs.getString("test_parameter_name"));
			parameterInfo.setIsoScope(rs.getString("iso_scope"));
			parameterInfo.setRemarks(rs.getString("remarks"));
			parameterInfo.setStatus(rs.getString("status"));
			return parameterInfo;
		}
	}

	
}
