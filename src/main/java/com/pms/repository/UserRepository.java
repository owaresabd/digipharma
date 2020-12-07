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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.pms.model.Company;
import com.pms.model.RolePrivilege;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository("mainRepository")
@Transactional
public class UserRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private IUserService userService;
	@Autowired
	private CompanyRepository companyRepository;

	public User findByUsername(String username) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from lims_users where user_name = ?", new Object[] { username },
				new UserRowMapper());
	}

	public List<User> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_users where company_id = ? ORDER BY user_type",
				new Object[] { user.getCompanyId() }, new UserRowMapper());
	}

	public List<User> getMultipleUser() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_users ORDER BY user_name", new UserRowMapper());
	}

	public User findById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from lims_users where id = ?", new Object[] { id },
				new UserRowMapper());
	}

	public List<User> getUserByTypeId(UUID typeId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_users where type_id = ? and company_id = ?",
				new Object[] { typeId, companyId }, new UserRowMapper());
	}

	public List<User> findDocs(UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_users where company_id = ?", new Object[] { companyId },
				new UserRowMapper());
	}

	public List<User> findUserWithoutAppUser(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate
				.query("SELECT * FROM lims_users WHERE company_id = ? AND id NOT IN(SELECT user_id FROM lims_user_roles "
						+ "WHERE company_id = ?)", new Object[] { id, id }, new UserRowMapper());
	}

	public void saveOrUpdateUsers(User user) {
		jdbcTemplate = new JdbcTemplate(datasource);
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		User currentUser = userService.getCurrentUser();
		Company company = companyRepository.findById(user.getCompanyId());
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (user.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_users(user_name, full_name, email, password, active, user_type, company_code, company_name, "
							+ "role_id, type_id, designation, address, mobile, created_date, modified_date, created_by, modified_by, company_id, super_user,is_employee, employee_id) VALUES "
							+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					user.getUserName(), user.getFullName(), user.getEmail(), password, 1, user.getUserType(),
					company.getCompanyCode(), company.getCompanyName(), currentUser.getRoleId(), user.getTypeId(),
					user.getDesignation(), user.getAddress(), user.getMobile(), time, time, currentUser.getFullName(),
					currentUser.getFullName(), company.getId(), user.getSuperUser(),user.getIsEmployee(),user.getEmployeeId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_users SET user_name = ?, full_name = ?, email = ?, user_type = ?, type_id = ?, designation = ?, "
							+ "address = ?, mobile = ?, super_user = ?, is_employee = ?, employee_id = ? where id = ?",
					user.getUserName(), user.getFullName(), user.getEmail(), user.getUserType(), user.getTypeId(),
					user.getDesignation(), user.getAddress(), user.getMobile(), user.getSuperUser(),user.getIsEmployee(),user.getEmployeeId(), user.getId());
			updateAppUserByUserId(user);
		}
	}

	public void updateAppUserByUserId(User user) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_user_roles SET user_name = ?, full_name = ? where user_id = ?",
				user.getUserName(), user.getFullName(), user.getId());
	}

	public UUID delete(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_users WHERE id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_user_roles WHERE user_id = ?", id);
		return id;
	}

	public List<User> validateUsername(String username) {
		List<User> entityList = jdbcTemplate.query("select * from lims_users where user_name = ?",
				new Object[] { username }, new UserRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	public List<User> getSysUser() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User currentUser = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_user_roles where company_id = ?",
				new Object[] { currentUser.getCompanyId() }, new SysUserRowMapper());
	}

	public void activeUser(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_users SET super_user = ? where id = ? ", "true", id);
	}

	public void deactiveUser(String username) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_users SET super_user = ? where user_name = ? ", "false", username);
	}

	public List<RolePrivilege> getAllMenu(UUID typeId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"select distinct suite_code::INTEGER, suite_name, module_code::INTEGER, module_name, privilege_code::INTEGER, privilege_name, menu_icon, menu_url from "
						+ "lims_role_privileges where type_id = ? order by suite_code, module_code::INTEGER, privilege_code::INTEGER;",
				new Object[] { typeId }, new RolePrivilegeRowMapper());
	}

	public List<User> validateEmployee(String username, String email) {
		List<User> entityList = jdbcTemplate.query("select * from lims_users where user_name = ? and email = ?",
				new Object[] { username, email }, new UserRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	public int changePassword(User user) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User info = findByUsername(user.getUserName());
		int i = jdbcTemplate.update("UPDATE lims_users SET password = ? where id = ? and company_id = ?",
				user.getPassword(), info.getId(), info.getCompanyId());

		return i;
	}

	class SysUserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			UUID id = (UUID) rs.getObject("user_id");
			UUID rId = (UUID) rs.getObject("role_id");

			user.setId(id);
			user.setUserName(rs.getString("user_name"));
			user.setRoleId(rId);
			return user;
		}

	}

	class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId((UUID) rs.getObject("id"));
			user.setUserName(rs.getString("user_name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFullName(rs.getString("full_name"));
			user.setActive(Integer.parseInt(rs.getString("active")));
			user.setUserType(rs.getString("user_type"));
			user.setCompanyCode(rs.getString("company_code"));
			user.setCompanyName(rs.getString("company_name"));
			user.setCompanyId((UUID) rs.getObject("company_id"));
			user.setRoleId((UUID) rs.getObject("role_id"));
			user.setTypeId((UUID) rs.getObject("type_id"));
			user.setDesignation(rs.getString("designation"));
			user.setAddress(rs.getString("address"));
			user.setMobile(rs.getString("mobile"));
			user.setSuperUser(rs.getString("super_user"));
			user.setIsEmployee(rs.getString("is_employee"));
			user.setEmployeeId((UUID) rs.getObject("employee_id"));
			return user;
		}

	}

	class RolePrivilegeRowMapper implements RowMapper<RolePrivilege> {

		@Override
		public RolePrivilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			RolePrivilege priv = new RolePrivilege();

			priv.setPrivilegeCode(rs.getString("privilege_code"));
			priv.setPrivilegeName(rs.getString("privilege_name"));
			priv.setModuleCode(rs.getString("module_code"));
			priv.setModuleName(rs.getString("module_name"));
			priv.setSuiteCode(rs.getString("suite_code"));
			priv.setSuiteName(rs.getString("suite_name"));
			priv.setMenuIcon(rs.getString("menu_icon"));
			priv.setMenuUrl(rs.getString("menu_url"));
			return priv;
		}
	}
}
