package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.ApplicationUser;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ApplicationUserRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ApplicationUser> findDocs(UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_user_roles where company_id = ?", new Object[] { companyId },
				new ApplicationUserRowMapper());
	}

	public void saveApplicationUsers(UUID userId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User appUser = userService.findById(userId);

		jdbcTemplate.update(
				"INSERT INTO lims_user_roles(user_id, role_id, user_name, full_name, company_id) VALUES (?,?,?,?,?)",
				userId, appUser.getRoleId(), appUser.getUserName(), appUser.getFullName(), companyId);
	}

	public void deleteApplicationUser(UUID userId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_user_roles WHERE user_id = ? AND company_id = ?", userId, companyId);
	}

	class ApplicationUserRowMapper implements RowMapper<ApplicationUser> {

		@Override
		public ApplicationUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			ApplicationUser user = new ApplicationUser();
			UUID id = (UUID) rs.getObject("id");
			UUID uId = (UUID) rs.getObject("user_id");
			UUID rId = (UUID) rs.getObject("role_id");
			UUID cId = (UUID) rs.getObject("company_id");

			user.setId(id);
			user.setUserId(uId);
			user.setRoleId(rId);
			user.setCompanyId(cId);
			user.setUserName(rs.getString("user_name"));
			user.setFullName(rs.getString("full_name"));
			return user;
		}
	}
}
