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

import com.pms.model.DisinfectantInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class DisinfectantRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<DisinfectantInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, disinfectant_name, remarks, status"
				+ "  from lims_disinfectant_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by disinfectant_name ",
						new Object[] { user.getCompanyId() }, new DisinfectantnfoRowMapper());
	}

	

	public void saveDisinfectantInfos(DisinfectantInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_disinfectant_infos ( disinfectant_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					info.getDisinfectantName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_disinfectant_infos SET disinfectant_name = ?, remarks = ?, status = ? where id = ?",
					info.getDisinfectantName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public List<DisinfectantInfo> validateDisinfectantName(String roomName) {
		List<DisinfectantInfo> entityList = jdbcTemplate.query("select id, disinfectant_name, remarks, status"
				+ "  from lims_disinfectant_infos where disinfectant_name = ?",
				new Object[] { roomName }, new DisinfectantnfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class DisinfectantnfoRowMapper implements RowMapper<DisinfectantInfo> {
		@Override
		public DisinfectantInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DisinfectantInfo info = new DisinfectantInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setDisinfectantName(rs.getString("disinfectant_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
