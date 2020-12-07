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
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CompanyRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<Company> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		UUID id = UUID.fromString("9b4169c4-84c8-4f15-adf8-976ab433c343");
		if (user.getCompanyId().equals(id)) {
			return jdbcTemplate.query("select * from pms_companies ORDER BY company_code", new CompanyRowMapper());
		} else {
			return jdbcTemplate.query("select * from pms_companies where id = ? ORDER BY company_code",
					new Object[] { user.getCompanyId() }, new CompanyRowMapper());
		}
		// return jdbcTemplate.query("select * from pms_companies ORDER BY
		// company_code", new CompanyRowMapper());
	}

	public Company findById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from pms_companies where id = ?", new Object[] { id },
				new CompanyRowMapper());
	}

	public List<Company> findCompanyInfo(UUID companyId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("select * from pms_companies where id = ?", new Object[] { companyId },
				new CompanyRowMapper());
	}

	public void manageCompanies(Company company) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (company.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO pms_companies(company_code, company_name, company_group, businessnature, currency_code, currency_name, "
							+ "fismonths, fisyear, tin, vat, regno, country, city, address, phone, mobile, email, fax, website, created_by_name, created_by_username, "
							+ "created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					company.getCompanyCode().toUpperCase(), company.getCompanyName(), company.getCompanyGroup(),
					company.getBusinessnature(), company.getCurrencyCode(), company.getCurrencyName(),
					company.getFismonths(), company.getFisyear(), company.getTin(), company.getVat(),
					company.getRegno(), company.getCountry(), company.getCity(), company.getAddress(),
					company.getPhone(), company.getMobile(), company.getEmail(), company.getFax(), company.getWebsite(),
					user.getFullName(), user.getUserName(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE pms_companies SET company_code = ?, company_name = ?, company_group = ?, businessnature = ?, currency_code = ?, "
							+ "currency_name = ?, fismonths = ?, fisyear = ?, tin = ?, vat = ?, regno = ?, country = ?, city = ?, address = ?, phone = ?, mobile = ?, "
							+ "email = ?, fax = ?, website = ?, created_by_name = ?, created_by_username = ?, created_at = ? WHERE id = ?",
					company.getCompanyCode().toUpperCase(), company.getCompanyName(), company.getCompanyGroup(),
					company.getBusinessnature(), company.getCurrencyCode(), company.getCurrencyName(),
					company.getFismonths(), company.getFisyear(), company.getTin(), company.getVat(),
					company.getRegno(), company.getCountry(), company.getCity(), company.getAddress(),
					company.getPhone(), company.getMobile(), company.getEmail(), company.getFax(), company.getWebsite(),
					user.getFullName(), user.getUserName(), time, company.getId());
			updateUserByCompanyId(company);
			/*
			 * updateCompanyPrivilegeByCompanyId(company);
			 * updateRolePrivilegeByCompanyId(company);
			 */
		}
	}

	public void updateUserByCompanyId(Company company) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_users SET company_code = ?, company_name = ? where company_id = ?",
				company.getCompanyCode(), company.getCompanyName(), company.getId());
	}

	/*
	 * public void updateCompanyPrivilegeByCompanyId(Company company) { jdbcTemplate
	 * = new JdbcTemplate(datasource); jdbcTemplate.
	 * update("UPDATE company_privileges SET company_code = ?, company_name = ? where company_id = ?"
	 * , company.getCompanyCode(), company.getCompanyName(), company.getId()); }
	 * 
	 * public void updateRolePrivilegeByCompanyId(Company company) { jdbcTemplate =
	 * new JdbcTemplate(datasource); jdbcTemplate.
	 * update("UPDATE role_privileges SET company_code = ?, company_name = ? where company_id = ?"
	 * , company.getCompanyCode(), company.getCompanyName(), company.getId()); }
	 */

	public UUID delete(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM pms_companies WHERE id = ?", id);
		return id;
	}

	class CompanyRowMapper implements RowMapper<Company> {

		@Override
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			Company company = new Company();
			UUID id = (UUID) rs.getObject("id");

			company.setId(id);
			company.setCompanyCode(rs.getString("company_code"));
			company.setCompanyName(rs.getString("company_name"));
			company.setCompanyGroup(rs.getString("company_group"));
			company.setBusinessnature(rs.getString("businessnature"));
			company.setCurrencyCode(rs.getString("currency_code"));
			company.setCurrencyName(rs.getString("currency_name"));
			company.setFismonths(rs.getString("fismonths"));
			company.setFisyear(rs.getString("fisyear"));
			company.setTin(rs.getString("tin"));
			company.setVat(rs.getString("vat"));
			company.setRegno(rs.getString("regno"));
			company.setCountry(rs.getString("country"));
			company.setCity(rs.getString("city"));
			company.setAddress(rs.getString("address"));
			company.setPhone(rs.getString("phone"));
			company.setMobile(rs.getString("mobile"));
			company.setEmail(rs.getString("email"));
			company.setFax(rs.getString("fax"));
			company.setWebsite(rs.getString("website"));
			company.setCreatedByName(rs.getString("created_by_name"));
			company.setCreatedByUsername(rs.getString("created_by_username"));
			return company;
		}
	}
}
