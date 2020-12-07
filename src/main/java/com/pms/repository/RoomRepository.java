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

import com.pms.model.RoomInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class RoomRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<RoomInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_room_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by room_name ",
						new Object[] { user.getCompanyId() }, new RoomnfoRowMapper());
	}

	

	public void saveRoomInfos(RoomInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_room_infos ( room_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					info.getRoomName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_room_infos SET room_name = ?, remarks = ?, status = ? where id = ?",
					info.getRoomName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public List<RoomInfo> validateRoomName(String roomName) {
		List<RoomInfo> entityList = jdbcTemplate.query("select * from lims_room_infos where room_name = ?",
				new Object[] { roomName }, new RoomnfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class RoomnfoRowMapper implements RowMapper<RoomInfo> {
		@Override
		public RoomInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoomInfo info = new RoomInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRoomName(rs.getString("room_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
