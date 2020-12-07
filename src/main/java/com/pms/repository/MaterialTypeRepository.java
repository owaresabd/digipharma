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

import com.pms.model.MaterialTypeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class MaterialTypeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<MaterialTypeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id, type_no, type_name, remarks, status from lims_material_type_infos "
				+ " where company_id = ? and status=" +(status != null ? "'" + status + "'" : "status") + " "
				+ " order by type_name ",
				new Object[] { user.getCompanyId() }, new MaterialTypeInfoRowMapper());
	}

	

	public void saveMaterialTypeInfos(MaterialTypeInfo typeInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (typeInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_material_type_infos(type_no, type_name,remarks, status,company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?)",
							typeInfo.getTypeNo().toUpperCase(), typeInfo.getTypeName(), typeInfo.getRemarks(),
							typeInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_material_type_infos SET type_no = ?, type_name = ?,remarks = ?, status = ?,updated_by=?, updated_at=? where id = ?",
					typeInfo.getTypeNo().toUpperCase(), typeInfo.getTypeName(), typeInfo.getRemarks(),
					typeInfo.getStatus(),user.getId(), time, typeInfo.getId());
		}
	}

	public List<MaterialTypeInfo> validateTypeNo(String typeNo) {
		User user = userService.getCurrentUser();
		List<MaterialTypeInfo> entityList = jdbcTemplate.query("select id, type_no, type_name, remarks, status from lims_material_type_infos where type_no = ? "
				+ " and company_id='"+user.getCompanyId()+"'",
				new Object[] { typeNo }, new MaterialTypeInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class MaterialTypeInfoRowMapper implements RowMapper<MaterialTypeInfo> {

		@Override
		public MaterialTypeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialTypeInfo typeInfo = new MaterialTypeInfo();

			typeInfo.setId((UUID) rs.getObject("id"));
			typeInfo.setTypeNo(rs.getString("type_no"));
			typeInfo.setTypeName(rs.getString("type_name"));
			typeInfo.setRemarks(rs.getString("remarks"));
			typeInfo.setStatus(rs.getString("status"));
			return typeInfo;
		}
	}
}
