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

import com.pms.model.ClientInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ClientRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ClientInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select id, ud_client_id, client_name, address, mobile_no, email, status from lims_client_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by client_name ",
				new Object[] { user.getCompanyId() }, new ClientInfoRowMapper());
	}
	
	public ClientInfo getClientInfoById(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (ClientInfo)jdbcTemplate.queryForObject("select id, ud_client_id, client_name, address, mobile_no, email, status from lims_client_infos  where id = ?",
				 new Object[] { id }, new ClientInfoRowMapper());
	}
	
	public void saveClientInfos(ClientInfo clientInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (clientInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_client_infos (ud_client_id, client_name, address, mobile_no, email, status,"
						+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?)",
						clientInfo.getUdClientId(), clientInfo.getClientName(), clientInfo.getAddress(), clientInfo.getMobileNo(), 
						clientInfo.getEmail(), clientInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_client_infos SET ud_client_id = ?, client_name = ?, address = ?, mobile_no = ?, email = ?, status = ?,"
					+ " updated_by = ?, updated_at = ? where id = ?", clientInfo.getUdClientId(), clientInfo.getClientName(), clientInfo.getAddress(),  
					clientInfo.getMobileNo(), clientInfo.getEmail(), clientInfo.getStatus(), user.getId(), time, clientInfo.getId());
			jdbcTemplate.update( "UPDATE lims_sampling_infos SET client_name = ? where client_id = ?", clientInfo.getClientName(),clientInfo.getUdClientId());
			
			
		}
	}

	public List<ClientInfo> validateClientId(String clientId) {
		List<ClientInfo> entityList = jdbcTemplate.query("select * from lims_client_infos where ud_client_id = ?",
				new Object[] { clientId }, new ClientInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class ClientInfoRowMapper implements RowMapper<ClientInfo> {

		@Override
		public ClientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ClientInfo info = new ClientInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setUdClientId(rs.getString("ud_client_id"));
			info.setClientName(rs.getString("client_name"));
			info.setAddress(rs.getString("address"));
			info.setMobileNo(rs.getString("mobile_no"));
			info.setEmail(rs.getString("email"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
