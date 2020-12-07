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

import com.pms.model.CompanyPrivilege;
import com.pms.model.RolePrivilege;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class RolePrivilegeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;
	@Autowired
	private CompanyPrivilegeRepository companyPrivilegeRepository;

	public List<RolePrivilege> findDocs(UUID typeId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from lims_role_privileges where type_id = ? and company_id = ?",
				new Object[] { typeId, companyId }, new RolPrivilegeRowMapper());
	}

	public void saveRolePrivileges(UUID privId, UUID typeId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		CompanyPrivilege priv = companyPrivilegeRepository.findByPrivId(privId, companyId);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		try {
			jdbcTemplate.update(
					"INSERT INTO lims_role_privileges(privilege_code, privilege_name, privilege_id, module_code, module_name, module_id, "
							+ "suite_code, suite_name, suite_id, created_by_name, created_by_username, created_at, type_id, menu_icon, menu_url, company_code, "
							+ "company_name, company_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					priv.getPrivilegeCode(), priv.getPrivilegeName(), priv.getPrivilegeId(), priv.getModuleCode(),
					priv.getModuleName(), priv.getModuleId(), priv.getSuiteCode(), priv.getSuiteName(),
					priv.getSuiteId(), user.getFullName(), user.getUserName(), time, typeId, priv.getMenuIcon(),
					priv.getMenuUrl(), priv.getCompanyCode(), priv.getCompanyName(), priv.getCompanyId());
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public void deleteRolePrivilge(UUID privId, UUID typeId, UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_role_privileges WHERE privilege_id = ? AND type_id = ? AND company_id = ?",
				privId, typeId, companyId);
	}

	class RolPrivilegeRowMapper implements RowMapper<RolePrivilege> {

		@Override
		public RolePrivilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			RolePrivilege priv = new RolePrivilege();
			UUID id = (UUID) rs.getObject("id");
			UUID pId = (UUID) rs.getObject("privilege_id");
			UUID mId = (UUID) rs.getObject("module_id");
			UUID sId = (UUID) rs.getObject("suite_id");
			UUID tId = (UUID) rs.getObject("type_id");
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
			priv.setTypeId(tId);
			priv.setCompanyCode(rs.getString("company_code"));
			priv.setCompanyName(rs.getString("company_name"));
			priv.setCompanyId(cId);
			priv.setCreatedByName(rs.getString("created_by_name"));
			priv.setCreatedByUsername(rs.getString("created_by_username"));
			return priv;
		}
	}
}
