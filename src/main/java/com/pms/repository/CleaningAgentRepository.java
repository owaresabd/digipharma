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

import com.pms.model.CleaningAgentInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CleaningAgentRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IUserService userService;

	public List<CleaningAgentInfo> findCleaningAgentInfos(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, agent_id, agent_name, manufac_date, exp_date, remarks, status"
				+ "  from lims_cleaning_agent_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new AgentInfosRowMapper());
	}

	public void saveCleaningAgentInfo(CleaningAgentInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_cleaning_agent_infos (agent_id, agent_name, manufac_date, exp_date, remarks, status, company_id, created_by, created_at) VALUES(?,?,?,?,?,?,?,?,?) ",
					info.getAgentId(), info.getAgentName(), info.getManufacDate(), info.getExpDate(),info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time );
		} else {
			jdbcTemplate.update(
					"UPDATE lims_cleaning_agent_infos SET agent_id =?, agent_name =?, manufac_date =?, exp_date =?,remarks = ?, status = ?,  updated_by = ?, updated_at = ? where id = ?",
					info.getAgentId(), info.getAgentName(), info.getManufacDate(), info.getExpDate(),info.getRemarks(), info.getStatus(), user.getId(), time, info.getId());
				}
	}
	
	class AgentInfosRowMapper implements RowMapper<CleaningAgentInfo> {
		@Override
		public CleaningAgentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CleaningAgentInfo info = new CleaningAgentInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setAgentId(rs.getString("agent_id"));
			info.setAgentName(rs.getString("agent_name"));
			info.setManufacDate(rs.getDate("manufac_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setStatus(rs.getString("status"));
			info.setRemarks(rs.getString("remarks"));
			return info;
		}
	}
	
	
}
