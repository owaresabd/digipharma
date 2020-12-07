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

import com.pms.model.StorageInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ConditionRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired	
	private IUserService userService;

	public List<StorageInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, storage_desc, status from lims_storage_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by storage_desc ",
						new Object[] { user.getCompanyId() }, new ConditionRowMapper());
	}

	public void saveConditionInfos(StorageInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_storage_infos (storage_desc, status, company_id, created_by, created_at) VALUES (?,?,?,?,?)", 
					info.getStorageDesc(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update( "UPDATE lims_storage_infos SET storage_desc = ?, status = ? where id = ?", info.getStorageDesc(), info.getStatus(), info.getId());
			jdbcTemplate.update( "UPDATE lims_material_infos SET storage_condition = ? where storage_con_id = ?", info.getStorageDesc(),info.getId());
			jdbcTemplate.update( "UPDATE lims_sampling_infos SET storage_condition = ? where storage_con_id = ?", info.getStorageDesc(),info.getId());
		}
			
	}

	class ConditionRowMapper implements RowMapper<StorageInfo> {
		@Override
		public StorageInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			StorageInfo info = new StorageInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setStorageDesc(rs.getString("storage_desc"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
