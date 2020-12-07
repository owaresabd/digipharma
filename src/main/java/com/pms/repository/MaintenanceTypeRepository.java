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

import com.pms.model.MaintenanceTypeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class MaintenanceTypeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<MaintenanceTypeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_maintenance_type_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by type_name ",
						new Object[] { user.getCompanyId() }, new MaintenanceTypeInfoRowMapper());
	}

	

	public void saveMaintenanceTypeInfos(MaintenanceTypeInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_maintenance_type_infos ( type_name,  status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?)",
					info.getTypeName(),  info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_maintenance_type_infos SET type_name = ?, status = ? where id = ?",
					info.getTypeName(),  info.getStatus(), info.getId());
		}
	}

	public List<MaintenanceTypeInfo> validateMaintenanceType(String typeName) {
		List<MaintenanceTypeInfo> entityList = jdbcTemplate.query("select * from lims_maintenance_type_infos where type_name = ?",
				new Object[] { typeName }, new MaintenanceTypeInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class MaintenanceTypeInfoRowMapper implements RowMapper<MaintenanceTypeInfo> {
		@Override
		public MaintenanceTypeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaintenanceTypeInfo info = new MaintenanceTypeInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setTypeName(rs.getString("type_name"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
