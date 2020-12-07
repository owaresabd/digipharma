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

import com.pms.model.SamplingMethodInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class SampleMethodRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<SamplingMethodInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_sampling_method_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by method_name ",
						new Object[] { user.getCompanyId() }, new MethodInfoRowMapper());
	}

	public List<SamplingMethodInfo> findAllMethod() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		String query = "select distinct id, method_name from lims_sampling_method_infos where company_id = '"
				+ user.getCompanyId() + "' order by method_name";
		return jdbcTemplate.query(query, new RowMapper<SamplingMethodInfo>() {
			public SamplingMethodInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				SamplingMethodInfo info = new SamplingMethodInfo();
				info.setId(UUID.fromString(rs.getString("id")));
				info.setMethodName(rs.getString("method_name"));
				return info;
			}
		});
	}

	public void saveSamplingMethodInfos(SamplingMethodInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_sampling_method_infos ( method_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					info.getMethodName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_sampling_method_infos SET method_name = ?, remarks = ?, status = ? where id = ?",
					info.getMethodName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public List<SamplingMethodInfo> validateMethodName(String methodName) {
		List<SamplingMethodInfo> entityList = jdbcTemplate.query("select * from lims_sampling_method_infos where method_name = ?",
				new Object[] { methodName }, new MethodInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class MethodInfoRowMapper implements RowMapper<SamplingMethodInfo> {
		@Override
		public SamplingMethodInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SamplingMethodInfo methodInfo = new SamplingMethodInfo();

			methodInfo.setId((UUID) rs.getObject("id"));
			methodInfo.setMethodName(rs.getString("method_name"));
			methodInfo.setRemarks(rs.getString("remarks"));
			methodInfo.setStatus(rs.getString("status"));
			return methodInfo;
		}
	}
	
	
}
