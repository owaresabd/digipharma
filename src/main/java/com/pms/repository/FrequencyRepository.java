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

import com.pms.model.FrequencyInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class FrequencyRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<FrequencyInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, equipment_id,fnc_equipment_name(equipment_id) equipment_name, type_id,fnc_maintenance_type_nm(type_id) type_name,"
						+ " freq_type,(CASE WHEN freq_type='D' THEN 'Daily'  WHEN freq_type='M' THEN 'Monthly' WHEN freq_type='Y' THEN 'Yearly' WHEN freq_type='C' THEN (freq_duration||' Days') ELSE '' END) freq_type_name,"
						+ " freq_duration, status,notify_time "
						+ " from lims_frequency_infos  where company_id = ? and status=" + (status != null ? "'" + status + "'" : "status") + "  ",
						new Object[] { user.getCompanyId() }, new FrequencyInfoRowMapper());
	}

	

	public void saveFrequencyInfos(FrequencyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_frequency_infos (equipment_id,type_id,freq_type,freq_duration,notify_time,status,company_id,created_by,created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)",
					  info.getEquipmentId(),info.getTypeId(),info.getFreqType(),info.getFreqDuration(),info.getNotifyTime(),info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_frequency_infos SET equipment_id = ?,type_id=?,freq_type=?,freq_duration=?,notify_time=?, status = ? where id = ?",
					info.getEquipmentId(),info.getTypeId(),info.getFreqType(),info.getFreqDuration(),info.getNotifyTime(), info.getStatus(), info.getId());
		}
	}

	

	class FrequencyInfoRowMapper implements RowMapper<FrequencyInfo> {
		@Override
		public FrequencyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			FrequencyInfo info = new FrequencyInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setTypeId((UUID) rs.getObject("type_id"));
			info.setTypeName(rs.getString("type_name"));
			info.setFreqType(rs.getString("freq_type"));
			info.setFreqTypeName(rs.getString("freq_type_name"));
			info.setFreqDuration(rs.getString("freq_duration"));
			info.setNotifyTime(rs.getString("notify_time"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
