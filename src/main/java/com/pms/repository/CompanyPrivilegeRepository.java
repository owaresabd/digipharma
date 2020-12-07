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
import org.springframework.stereotype.Repository;

import com.pms.model.Company;
import com.pms.model.CompanyPrivilege;
import com.pms.model.Privilege;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CompanyPrivilegeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private CompanyRepository companyRepository;

	public List<CompanyPrivilege> findDocs(UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_company_privileges where company_id = ?",
				new Object[] { companyId }, new CompanyPrivilegeRowMapper());
	}

	public CompanyPrivilege findByPrivId(UUID privId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(
				"select * from lims_company_privileges where privilege_id = ? and company_id = ?",
				new Object[] { privId, companyId }, new CompanyPrivilegeRowMapper());
	}

	public List<CompanyPrivilege> findPrivilgeWithoutRole(UUID typeId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"SELECT * FROM lims_company_privileges WHERE company_id = ? AND privilege_id NOT IN(SELECT privilege_id FROM lims_role_privileges "
						+ "WHERE type_id = ? AND company_id = ?)",
				new Object[] { companyId, typeId, companyId }, new CompanyPrivilegeRowMapper());
	}

	public void saveCompanyPrivileges(UUID privId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		Privilege priv = menuRepository.findByPrivilegeId(privId);
		Company company = companyRepository.findById(companyId);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				"INSERT INTO lims_company_privileges(privilege_code, privilege_name, privilege_id, module_code, module_name, module_id, "
						+ "suite_code, suite_name, suite_id, created_by_name, created_by_username, created_at, menu_icon, menu_url, company_code, "
						+ "company_name, company_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				priv.getPrivilegeCode(), priv.getPrivilegeName(), priv.getId(), priv.getModuleCode(),
				priv.getModuleName(), priv.getModuleId(), priv.getSuiteCode(), priv.getSuiteName(), priv.getSuiteId(),
				user.getFullName(), user.getUserName(), time, priv.getMenuIcon(), priv.getMenuUrl(),
				company.getCompanyCode(), company.getCompanyName(), company.getId());
	}

	public void deleteCompanyPrivilge(UUID privId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_company_privileges WHERE privilege_id = ? AND company_id = ?", privId,
				companyId);
		jdbcTemplate.update("DELETE FROM lims_role_privileges WHERE privilege_id = ? AND company_id = ?", privId,
				companyId);
	}

	class CompanyPrivilegeRowMapper implements RowMapper<CompanyPrivilege> {

		@Override
		public CompanyPrivilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			CompanyPrivilege priv = new CompanyPrivilege();
			UUID id = (UUID) rs.getObject("id");
			UUID pId = (UUID) rs.getObject("privilege_id");
			UUID mId = (UUID) rs.getObject("module_id");
			UUID sId = (UUID) rs.getObject("suite_id");
			UUID cId = (UUID) rs.getObject("company_id");

			priv.setId(id);
			priv.setPrivilegeCode(rs.getString("privilege_code"));
			priv.setPrivilegeName(rs.getString("privilege_name"));
			priv.setPrivilegeId(pId);
			priv.setModuleCode(rs.getString("module_code"));
			priv.setModuleName(rs.getString("module_name"));
			priv.setModuleId(mId);
			priv.setSuiteCode(rs.getString("suite_code"));
			priv.setSuiteName(rs.getString("suite_name"));
			priv.setSuiteId(sId);
			priv.setMenuIcon(rs.getString("menu_icon"));
			priv.setMenuUrl(rs.getString("menu_url"));
			priv.setCompanyCode(rs.getString("company_code"));
			priv.setCompanyName(rs.getString("company_name"));
			priv.setCompanyId(cId);
			priv.setCreatedByName(rs.getString("created_by_name"));
			priv.setCreatedByUsername(rs.getString("created_by_username"));
			return priv;
		}
	}
}
