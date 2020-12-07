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

import com.pms.model.ManufacturerInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ManufacturerRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ManufacturerInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query( " select id, source_name, status from lims_manufacturer_infos "
					+ " where company_id = ? and status=" +(status != null ? "'" + status + "'" : "status") + " "
					+ " order by source_name ",
					new Object[] { user.getCompanyId() }, new ManufacturerInfoRowMapper());
	}

	

	public void saveManufacturerInfos(ManufacturerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_manufacturer_infos (source_name, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?)",
							info.getSourceName(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_manufacturer_infos SET source_name = ?,  status = ?, updated_by=?, updated_at=? where id = ?",
					info.getSourceName(), info.getStatus(), user.getId(), time, info.getId());
			jdbcTemplate.update("UPDATE lims_wh_request_infos SET manufacture_name ='"+info.getSourceName()+"' where manufacture_id = ?", info.getId() );
		}
	}

	

	class ManufacturerInfoRowMapper implements RowMapper<ManufacturerInfo> {

		@Override
		public ManufacturerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ManufacturerInfo info = new ManufacturerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setSourceName(rs.getString("source_name"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
