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

import com.pms.model.SupplierInfo;
import com.pms.model.User;
import com.pms.service.IUserService;


@Repository
@Transactional
public class SupplierRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<SupplierInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from vms_supplier_infos where company_id=? and status="
						+ (status != null ? "'" + status + "'" : "status") + "  order by supp_name ",
				new Object[] { user.getCompanyId() }, new SupplierRowMapper());
	}

	public SupplierInfo findById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from vms_supplier_infos where id = ?", new Object[] { id },
				new SupplierRowMapper());
	}

	public void saveSupplierInfos(SupplierInfo supplierInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (supplierInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO vms_supplier_infos(supp_code,supp_name ,address ,contact_person, designation,"
							+ " phone_number,mobile,email,status,company_id, created_by_name, created_by_username, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					supplierInfo.getSupplierCode().toUpperCase(), supplierInfo.getSupplierName(),
					supplierInfo.getAddress(), supplierInfo.getContactPerson(), supplierInfo.getDesignation(),
					supplierInfo.getPhoneNumber(), supplierInfo.getMobile(), supplierInfo.getEmail(),
					supplierInfo.getStatus(), user.getCompanyId(), user.getFullName(), user.getUserName(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE vms_supplier_infos SET supp_code=?,supp_name=? ,address=? ,contact_person=?,"
							+ " designation=?,phone_number=?,mobile=?,email=?,status=? where id = ?",
					supplierInfo.getSupplierCode().toUpperCase(), supplierInfo.getSupplierName(),
					supplierInfo.getAddress(), supplierInfo.getContactPerson(), supplierInfo.getDesignation(),
					supplierInfo.getPhoneNumber(), supplierInfo.getMobile(), supplierInfo.getEmail(),
					supplierInfo.getStatus(), supplierInfo.getId());

		}
	}

	public UUID delete(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("delete from vms_supplier_infos where id = ?", id);
		return id;
	}

	public List<SupplierInfo> validateSupplierCode(String supplierCode) {
		List<SupplierInfo> entityList = jdbcTemplate.query("select * from vms_supplier_infos where supp_code = ?",
				new Object[] { supplierCode }, new SupplierRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class SupplierRowMapper implements RowMapper<SupplierInfo> {

		@Override
		public SupplierInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SupplierInfo supplierInfo = new SupplierInfo();
			UUID id = (UUID) rs.getObject("id");

			supplierInfo.setId(id);
			supplierInfo.setSupplierCode(rs.getString("supp_code"));
			supplierInfo.setSupplierName(rs.getString("supp_name"));
			supplierInfo.setAddress(rs.getString("address"));
			supplierInfo.setContactPerson(rs.getString("contact_person"));
			supplierInfo.setDesignation(rs.getString("designation"));
			supplierInfo.setPhoneNumber(rs.getString("phone_number"));
			supplierInfo.setMobile(rs.getString("mobile"));
			supplierInfo.setEmail(rs.getString("email"));
			supplierInfo.setStatus(rs.getString("status"));
			return supplierInfo;
		}
	}
}
