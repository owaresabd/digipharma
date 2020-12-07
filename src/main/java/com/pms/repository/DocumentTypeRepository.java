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

import com.pms.model.DocumentTypeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class DocumentTypeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<DocumentTypeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from lims_document_type_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by type_name ",
						new Object[] { user.getCompanyId() }, new WaterTypenfoRowMapper());
	}

	

	public void saveDocumentTypeInfos(DocumentTypeInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_document_type_infos ( type_name, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?)",
					info.getTypeName(), info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_document_type_infos SET type_name = ?, remarks = ?, status = ? where id = ?",
					info.getTypeName(), info.getRemarks(), info.getStatus(), info.getId());
		}
	}

	public List<DocumentTypeInfo> validateDocumentTypeName(String roomName) {
		List<DocumentTypeInfo> entityList = jdbcTemplate.query("select * from lims_document_type_infos where type_name = ?",
				new Object[] { roomName }, new WaterTypenfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class WaterTypenfoRowMapper implements RowMapper<DocumentTypeInfo> {
		@Override
		public DocumentTypeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DocumentTypeInfo info = new DocumentTypeInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setTypeName(rs.getString("type_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	
}
