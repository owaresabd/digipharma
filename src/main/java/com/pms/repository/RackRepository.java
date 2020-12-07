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

import com.pms.model.RackInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class RackRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<RackInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, rack_name, remarks, status from lims_rack_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by rack_name ",
						new Object[] { user.getCompanyId() }, new RackInfoRowMapper());
	}

	

	public void saveRackInfos(RackInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_rack_infos ( rack_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					info.getRackName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_rack_infos SET rack_name = ?, remarks = ?, status = ? where id = ?",
					info.getRackName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public List<RackInfo> validateRackName(String rackName) {
		List<RackInfo> entityList = jdbcTemplate.query("select * from lims_rack_infos where rack_name = ?",
				new Object[] { rackName }, new RackInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class RackInfoRowMapper implements RowMapper<RackInfo> {
		@Override
		public RackInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RackInfo methodInfo = new RackInfo();

			methodInfo.setId((UUID) rs.getObject("id"));
			methodInfo.setRackName(rs.getString("rack_name"));
			methodInfo.setRemarks(rs.getString("remarks"));
			methodInfo.setStatus(rs.getString("status"));
			return methodInfo;
		}
	}
	
	
}
