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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pms.model.User;
import com.pms.model.UserTypes;
import com.pms.service.IUserService;

@Repository
@Transactional
public class RoleRepositoy {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<UserTypes> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_user_types where company_id = ? ORDER BY role_code",
				new Object[] { user.getCompanyId() }, new UserRoleRowMapper());
	}

	public UserTypes findById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from lims_user_types where id = ?", new Object[] { id },
				new UserRoleRowMapper());
	}

	public List<UserTypes> findDocs(UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_user_types where company_id = ?", new Object[] { companyId },
				new UserRoleRowMapper());
	}

	/*
	 * public List<UserTypes> findRoleWithoutMultiUser(UUID userId, UUID companyId)
	 * { jdbcTemplate = new JdbcTemplate(datasource); return jdbcTemplate.
	 * query("SELECT * FROM lims_user_types WHERE company_id = ? AND id NOT IN(SELECT type_id FROM multi_user_roles "
	 * + "WHERE user_id = ? AND company_id = ?)", new Object[] { companyId, userId,
	 * companyId }, new UserRoleRowMapper()); }
	 */

	public void saveRoles(UserTypes role) {
		jdbcTemplate = new JdbcTemplate(datasource);

		if (role.getId() == null) {
			jdbcTemplate.update("INSERT INTO lims_user_types(role_code, role_name, company_id) VALUES (?,?,?)",
					role.getRoleCode().toUpperCase(), role.getRoleName(), role.getCompanyId());
		} else {
			jdbcTemplate.update("UPDATE lims_user_types SET role_code = ?, role_name = ? where id = ?",
					role.getRoleCode().toUpperCase(), role.getRoleName(), role.getId());
			updateUserByRoleId(role);
		}
	}

	public void updateUserByRoleId(UserTypes role) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_users SET user_type = ? where type_id = ?", role.getRoleCode().toUpperCase(),
				role.getId());
	}

	public UUID delete(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_user_types WHERE id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_users WHERE type_id = ?", id);
		// jdbcTemplate.update("DELETE FROM role_privileges WHERE type_id = ?", id);
		return id;
	}

	public List<UserTypes> validateRoleCode(UUID companyId, String roleCode) {
		List<UserTypes> entityList = jdbcTemplate.query(
				"select * from lims_user_types where company_id = ? and role_code = ?",
				new Object[] { companyId, roleCode }, new UserRoleRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class UserRoleRowMapper implements RowMapper<UserTypes> {

		@Override
		public UserTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserTypes role = new UserTypes();
			UUID id = (UUID) rs.getObject("id");
			UUID cId = (UUID) rs.getObject("company_id");

			role.setId(id);
			role.setRoleCode(rs.getString("role_code"));
			role.setRoleName(rs.getString("role_name"));
			role.setCompanyId(cId);
			return role;
		}
	}
}
