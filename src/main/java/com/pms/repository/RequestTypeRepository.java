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

import com.pms.model.RequestTypeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class RequestTypeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<RequestTypeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id, type_no, type_name, remarks, status from lims_request_type_infos "
				+ " where company_id = ? and status=" +(status != null ? "'" + status + "'" : "status") + " "
				+ " order by type_name ",
				new Object[] { user.getCompanyId() }, new RequestTypeInfoRowMapper());
	}

	

	public void saveRequestTypeInfos(RequestTypeInfo typeInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (typeInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_request_type_infos(type_no, type_name,remarks, status,company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?)",
							typeInfo.getTypeNo().toUpperCase(), typeInfo.getTypeName(), typeInfo.getRemarks(),
							typeInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_request_type_infos SET type_no = ?, type_name = ?,remarks = ?, status = ?,updated_by=?, updated_at=? where id = ?",
					typeInfo.getTypeNo().toUpperCase(), typeInfo.getTypeName(), typeInfo.getRemarks(),
					typeInfo.getStatus(),user.getId(), time, typeInfo.getId());
		}
	}

	public List<RequestTypeInfo> validateTypeNo(String typeNo) {
		User user = userService.getCurrentUser();
		List<RequestTypeInfo> entityList = jdbcTemplate.query("select id, type_no, type_name, remarks, status from lims_request_type_infos where type_no = ? "
				+ " and company_id='"+user.getCompanyId()+"'",
				new Object[] { typeNo }, new RequestTypeInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class RequestTypeInfoRowMapper implements RowMapper<RequestTypeInfo> {

		@Override
		public RequestTypeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RequestTypeInfo typeInfo = new RequestTypeInfo();

			typeInfo.setId((UUID) rs.getObject("id"));
			typeInfo.setTypeNo(rs.getString("type_no"));
			typeInfo.setTypeName(rs.getString("type_name"));
			typeInfo.setRemarks(rs.getString("remarks"));
			typeInfo.setStatus(rs.getString("status"));
			return typeInfo;
		}
	}
}
