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

import com.pms.model.LabItemSetupInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class LabItemRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	
	public List<LabItemSetupInfo> getLabItemSetupInfos(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, item_name,  created_by, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName "
				+ " FROM lims_lab_item_setup_infos WHERE company_id = ? and status=" +(status != null ? "'" + status + "'" : "status") + " ",
				new Object[] { user.getCompanyId() }, new labItemInfosRowMapper());
	}
	class labItemInfosRowMapper implements RowMapper<LabItemSetupInfo> {
		@Override
		public LabItemSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LabItemSetupInfo info = new LabItemSetupInfo();
			
			info.setId((UUID) rs.getObject("id"));
		//	info.setTemperature(rs.getString("temperature"));
			info.setItemName(rs.getString("item_name"));
			info.setCreateBy((UUID) rs.getObject("created_by"));
		//	info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setUpdateAt(rs.getTimestamp("update_at"));
			info.setCreateAt(rs.getTimestamp("create_at"));
			info.setCreateByName(rs.getString("createByName"));
			info.setUpdateByName(rs.getString("updateByName"));
		//	info.setEquipmentByName(rs.getString("equipmentByName"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	public void saveLabItemSetupInfo(LabItemSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_lab_item_setup_infos (item_name,  created_by,  status, create_at, company_id) VALUES( ?, ?, ?, ?, ?)",
						info.getItemName(), user.getId(),  info.getStatus(), time, user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_lab_item_setup_infos SET item_name =?,  status = ?, update_by = ?, update_at = ? where id = ?",
					info.getItemName(),   info.getStatus(), user.getId(), time, info.getId());
				}
	}
}
