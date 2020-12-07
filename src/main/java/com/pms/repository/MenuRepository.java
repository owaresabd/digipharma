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

import com.pms.model.Module;
import com.pms.model.Privilege;
import com.pms.model.Suite;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class MenuRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	/*********************************************
	 * START :: Suite Modification Business Logic
	 ***************************************/

	public List<Suite> findAllSuites() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select id, suite_code, suite_name, suite_icon, created_by_name,created_by_username "
				+ " from pms_suites ORDER BY suite_code::int", new SuiteRowMapper());
	}

	public Suite findBySuiteId(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select id, suite_code, suite_name, suite_icon, created_by_name,created_by_username "
				+ " from pms_suites where id = ?", new Object[] { id },
				new SuiteRowMapper());
	}

	public void saveOrUpdateSuites(Suite suite) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (suite.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO pms_suites(suite_code, suite_name, suite_icon, created_by_name, created_by_username, created_at) VALUES (?,?,?,?,?,?)",
					suite.getSuiteCode(), suite.getSuiteName(), suite.getSuiteIcon(), user.getFullName(),
					user.getUserName(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE pms_suites SET suite_code = ?, suite_name = ?, suite_icon = ?, created_by_name = ?, created_by_username = ?, created_at = ? where id = ?",
					suite.getSuiteCode(), suite.getSuiteName(), suite.getSuiteIcon(), user.getFullName(),
					user.getUserName(), time, suite.getId());
			updateModules(suite);
			updatePrivileges(suite);
			updateCompanyPrivileges(suite);
			updateRolePrivileges(suite);
		}
	}

	public void updateModules(Suite suite) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE pms_modules SET suite_code = ?, suite_name = ?, menu_icon = ? where suite_id = ?",
				suite.getSuiteCode(), suite.getSuiteName(), suite.getSuiteIcon(), suite.getId());
	}

	public void updatePrivileges(Suite suite) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update(
				"UPDATE lims_privileges SET suite_code = ?, suite_name = ?, menu_icon = ? where suite_id = ?",
				suite.getSuiteCode(), suite.getSuiteName(), suite.getSuiteIcon(), suite.getId());
	}

	public void updateRolePrivileges(Suite suite) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update(
				"UPDATE lims_role_privileges SET suite_code = ?, suite_name = ?, menu_icon = ? where suite_id = ?",
				suite.getSuiteCode(), suite.getSuiteName(), suite.getSuiteIcon(), suite.getId());
	}

	public void updateCompanyPrivileges(Suite suite) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update(
				"UPDATE lims_company_privileges SET suite_code = ?, suite_name = ?, menu_icon = ? where suite_id = ?",
				suite.getSuiteCode(), suite.getSuiteName(), suite.getSuiteIcon(), suite.getId());
	}

	public UUID deleteSuite(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM pms_suites WHERE id = ?", id);
		jdbcTemplate.update("DELETE FROM pms_modules WHERE suite_id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_privileges WHERE suite_id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_company_privileges WHERE suite_id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_role_privileges WHERE suite_id = ?", id);
		return id;
	}

	public List<Suite> validateSuiteCode(String suiteCode) {
		jdbcTemplate = new JdbcTemplate(datasource);
		List<Suite> entityList = jdbcTemplate.query("select * from pms_suites where suite_code = ?",
				new Object[] { suiteCode }, new SuiteRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class SuiteRowMapper implements RowMapper<Suite> {

		@Override
		public Suite mapRow(ResultSet rs, int rowNum) throws SQLException {
			Suite suite = new Suite();
			UUID id = (UUID) rs.getObject("id");

			suite.setId(id);
			suite.setSuiteCode(rs.getString("suite_code"));
			suite.setSuiteName(rs.getString("suite_name"));
			suite.setSuiteIcon(rs.getString("suite_icon"));
			suite.setCreatedByName(rs.getString("created_by_name"));
			suite.setCreatedByUsername(rs.getString("created_by_username"));
			return suite;
		}
	}

	/**********************************************
	 * END :: Suite Modification Business Logic
	 ****************************************/

	/*********************************************
	 * START :: Module Modification Business Logic
	 **************************************/

	public List<Module> findAllModules() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from pms_modules ORDER BY suite_name", new ModuleRowMapper());
	}

	public Module findByModuleId(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from pms_modules where id = ?", new Object[] { id },
				new ModuleRowMapper());
	}

	public List<Module> findModuleBySuiteId(UUID suiteId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from pms_modules where suite_id = ? order by module_code",
				new Object[] { suiteId }, new ModuleRowMapper());
	}

	public void saveOrUpdateModules(Module module) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Suite suite = this.findBySuiteId(module.getSuiteId());
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (module.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO pms_modules(module_code, module_name, suite_code, suite_name, suite_id, created_by_name, "
							+ "created_by_username, created_at, menu_icon) VALUES (?,?,?,?,?,?,?,?,?)",
					module.getModuleCode(), module.getModuleName(), suite.getSuiteCode(), suite.getSuiteName(),
					suite.getId(), user.getFullName(), user.getUserName(), time, suite.getSuiteIcon());
		} else {
			jdbcTemplate.update(
					"UPDATE pms_modules SET module_code = ?, module_name = ?, suite_code = ?, suite_name = ?, suite_id = ?, "
							+ "created_by_name = ?, created_by_username = ?, created_at = ?, menu_icon = ? where id = ?",
					module.getModuleCode(), module.getModuleName(), suite.getSuiteCode(), suite.getSuiteName(),
					suite.getId(), user.getFullName(), user.getUserName(), time, suite.getSuiteIcon(), module.getId());
			updatePrivileges(module);
			updateCompanyPrivileges(module);
			updateRolePrivileges(module);
		}
	}

	public void updatePrivileges(Module module) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_privileges SET module_code = ?, module_name = ? where module_id = ?",
				module.getModuleCode(), module.getModuleName(), module.getId());
	}

	public void updateRolePrivileges(Module module) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_role_privileges SET module_code = ?, module_name = ? where module_id = ?",
				module.getModuleCode(), module.getModuleName(), module.getId());
	}

	public void updateCompanyPrivileges(Module module) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_company_privileges SET module_code = ?, module_name = ? where module_id = ?",
				module.getModuleCode(), module.getModuleName(), module.getId());
	}

	public UUID deleteModule(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM pms_modules WHERE id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_privileges WHERE module_id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_company_privileges WHERE module_id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_role_privileges WHERE module_id = ?", id);
		return id;
	}

	public List<Module> validateModuleCode(UUID suiteId, String moduleCode) {
		jdbcTemplate = new JdbcTemplate(datasource);
		List<Module> entityList = jdbcTemplate.query("select * from pms_modules where suite_id = ? and module_code = ?",
				new Object[] { suiteId, moduleCode }, new ModuleRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class ModuleRowMapper implements RowMapper<Module> {

		@Override
		public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
			Module module = new Module();
			UUID id = (UUID) rs.getObject("id");
			UUID sId = (UUID) rs.getObject("suite_id");

			module.setId(id);
			module.setModuleCode(rs.getString("module_code"));
			module.setModuleName(rs.getString("module_name"));
			module.setSuiteCode(rs.getString("suite_code"));
			module.setSuiteName(rs.getString("suite_name"));
			module.setSuiteId(sId);
			module.setMenuIcon(rs.getString("menu_icon"));
			module.setCreatedByName(rs.getString("created_by_name"));
			module.setCreatedByUsername(rs.getString("created_by_username"));
			return module;
		}
	}

	/**********************************************
	 * END :: Module Modification Business Logic
	 ***************************************/

	/*******************************************
	 * START :: Privilege Modification Business Logic
	 *************************************/

	public List<Privilege> findAllPrivileges() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_privileges ORDER BY suite_name", new PrivilegeRowMapper());
	}

	public List<Privilege> findPrivilgeWithoutCompany(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"SELECT * FROM lims_privileges WHERE id NOT IN(SELECT privilege_id FROM lims_company_privileges WHERE company_id = ?)",
				new Object[] { id }, new PrivilegeRowMapper());
	}

	public Privilege findByPrivilegeId(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from lims_privileges where id = ?", new Object[] { id },
				new PrivilegeRowMapper());
	}

	public List<Privilege> findBySuiteModuleId(UUID suiteId, UUID moduleId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"select * from lims_privileges where suite_id = ? and module_id = ? order by privilege_code",
				new Object[] { suiteId, moduleId }, new PrivilegeRowMapper());
	}

	public void saveOrUpdatePrivileges(Privilege privilege) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Module module = this.findByModuleId(privilege.getModuleId());
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (privilege.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_privileges(privilege_code, privilege_name, module_code, module_name, module_id, suite_code, "
							+ "suite_name, suite_id, created_by_name, created_by_username, created_at, menu_icon, menu_url) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					privilege.getPrivilegeCode(), privilege.getPrivilegeName(), module.getModuleCode(),
					module.getModuleName(), module.getId(), module.getSuiteCode(), module.getSuiteName(),
					module.getSuiteId(), user.getFullName(), user.getUserName(), time, module.getMenuIcon(),
					privilege.getMenuUrl());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_privileges SET privilege_code = ?, privilege_name = ?, module_code = ?, module_name = ?, module_id = ?, "
							+ "suite_code = ?, suite_name = ?, suite_id = ?, created_by_name = ?, created_by_username = ?, created_at = ?, menu_icon = ?, menu_url = ? where id = ?",
					privilege.getPrivilegeCode(), privilege.getPrivilegeName(), module.getModuleCode(),
					module.getModuleName(), module.getId(), module.getSuiteCode(), module.getSuiteName(),
					module.getSuiteId(), user.getFullName(), user.getUserName(), time, module.getMenuIcon(),
					privilege.getMenuUrl(), privilege.getId());
			updateCompanyPrivileges(privilege);
			updateRolePrivileges(privilege);
		}
	}

	public void updateRolePrivileges(Privilege privilege) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update(
				"UPDATE lims_role_privileges SET privilege_code = ?, privilege_name = ?, menu_url = ? where privilege_id = ?",
				privilege.getPrivilegeCode(), privilege.getPrivilegeName(), privilege.getMenuUrl(), privilege.getId());
	}

	public void updateCompanyPrivileges(Privilege privilege) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update(
				"UPDATE lims_company_privileges SET privilege_code = ?, privilege_name = ?, menu_url = ? where privilege_id = ?",
				privilege.getPrivilegeCode(), privilege.getPrivilegeName(), privilege.getMenuUrl(), privilege.getId());
	}

	public UUID deletePrivilege(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_privileges WHERE id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_company_privileges WHERE privilege_id = ?", id);
		jdbcTemplate.update("DELETE FROM lims_role_privileges WHERE privilege_id = ?", id);
		return id;
	}

	public List<Privilege> validatePrivilegeCode(UUID suiteId, UUID moduleId, String privilegeCode) {
		jdbcTemplate = new JdbcTemplate(datasource);
		List<Privilege> entityList = jdbcTemplate.query(
				"select * from lims_privileges where suite_id = ? and module_id = ? and privilege_code = ?",
				new Object[] { suiteId, moduleId, privilegeCode }, new PrivilegeRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class PrivilegeRowMapper implements RowMapper<Privilege> {

		@Override
		public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			Privilege privilege = new Privilege();
			UUID id = (UUID) rs.getObject("id");
			UUID mId = (UUID) rs.getObject("module_id");
			UUID sId = (UUID) rs.getObject("suite_id");

			privilege.setId(id);
			privilege.setPrivilegeCode(rs.getString("privilege_code"));
			privilege.setPrivilegeName(rs.getString("privilege_name"));
			privilege.setModuleCode(rs.getString("module_code"));
			privilege.setModuleName(rs.getString("module_name"));
			privilege.setModuleId(mId);
			privilege.setSuiteCode(rs.getString("suite_code"));
			privilege.setSuiteName(rs.getString("suite_name"));
			privilege.setSuiteId(sId);
			privilege.setMenuIcon(rs.getString("menu_icon"));
			privilege.setMenuUrl(rs.getString("menu_url"));
			privilege.setCreatedByName(rs.getString("created_by_name"));
			privilege.setCreatedByUsername(rs.getString("created_by_username"));
			return privilege;
		}
	}

	/********************************************
	 * END :: Privilege Modification Business Logic
	 **************************************/
}
