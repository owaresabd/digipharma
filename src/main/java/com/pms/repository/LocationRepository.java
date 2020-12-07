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

import com.pms.model.LocationInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class LocationRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<LocationInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id, location_id, location_name, remarks, status from lims_location_setup_infos "
				+ " where company_id = ? and status=" +(status != null ? "'" + status + "'" : "status") + " "
				+ " order by location_name ",
				new Object[] { user.getCompanyId() }, new LocationInfoRowMapper());
	}

	

	public void saveLocationInfos(LocationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_location_setup_infos(location_id, location_name,remarks, status,company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?)",
							info.getLocationId().toUpperCase(), info.getLocationName(), info.getRemarks(),
							info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_location_setup_infos SET location_id = ?, location_name = ?,remarks = ?, status = ?,updated_by=?, updated_at=? where id = ?",
					info.getLocationId().toUpperCase(), info.getLocationName(), info.getRemarks(),
					info.getStatus(),user.getId(), time, info.getId());
		}
	}

	public List<LocationInfo> validateTypeNo(String typeNo) {
		User user = userService.getCurrentUser();
		List<LocationInfo> entityList = jdbcTemplate.query("select id, location_id, location_name, remarks, status "
				+ " from lims_location_setup_infos where location_id = ? "
				+ " and company_id='"+user.getCompanyId()+"'",
				new Object[] { typeNo }, new LocationInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class LocationInfoRowMapper implements RowMapper< LocationInfo> {

		@Override
		public  LocationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			 LocationInfo info = new  LocationInfo();

			 info.setId((UUID) rs.getObject("id"));
			 info.setLocationId(rs.getString("location_id"));
			 info.setLocationName(rs.getString("location_name"));
			 info.setRemarks(rs.getString("remarks"));
			 info.setStatus(rs.getString("status"));
			return info;
		}
	}
}
