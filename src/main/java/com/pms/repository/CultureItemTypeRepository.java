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

import com.pms.model.CultureItemTypeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CultureItemTypeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<CultureItemTypeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, culture_item_type_name, remarks, status"
				+ "  from lims_culture_item_type_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by culture_item_type_name ",
						new Object[] { user.getCompanyId() }, new CulturalItemTypeRowMapper());
	}

	

	public void saveCultureItemTypeInfos(CultureItemTypeInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_culture_item_type_infos ( culture_item_type_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					info.getCultureItemTypeName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_culture_item_type_infos SET culture_item_type_name = ?, remarks = ?, status = ? where id = ?",
					info.getCultureItemTypeName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public List<CultureItemTypeInfo> validateCultureItemTypeName(String roomName) {
		List<CultureItemTypeInfo> entityList = jdbcTemplate.query("select id, culture_item_type_name, remarks, status"
				+ "  from lims_culture_item_type_infos where culture_item_type_name = ?",
				new Object[] { roomName }, new CulturalItemTypeRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class CulturalItemTypeRowMapper implements RowMapper<CultureItemTypeInfo> {
		@Override
		public CultureItemTypeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CultureItemTypeInfo info = new CultureItemTypeInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setCultureItemTypeName(rs.getString("culture_item_type_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
