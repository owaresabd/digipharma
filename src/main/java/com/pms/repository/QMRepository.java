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

import com.pms.model.QMInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class QMRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	
	public List<QMInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, ud_qm_id, qm_name, effective_date, revision_no, attachment_file, remarks, status "
				+ " from lims_qm_infos where company_id = ? "
				+ " and status="+ (status != null ? "'" + status + "'" : "status")+" "
				+ " order by qm_name "  ,
						new Object[] { user.getCompanyId() }, new QMInfoRowMapper());
	}
	
	
	public void saveQmInfo(UUID idRandom,QMInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_qm_infos (id, ud_qm_id, qm_name, effective_date, revision_no, attachment_file, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)",
							idRandom,info.getUdQmId(),info.getQmName(),info.getEffectiveDate(), info.getRevisionNo(),info.getAttachmentFile(),info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_qm_infos SET ud_qm_id=?, qm_name=?, effective_date=?, revision_no=?, attachment_file=?, remarks=?, status = ?, updated_by=?, updated_at = ? where id = ?",
					info.getUdQmId(),info.getQmName(),info.getEffectiveDate(), info.getRevisionNo(),info.getAttachmentFile(),info.getRemarks(), info.getStatus(),user.getId(), time, info.getId());
		}
	}
	
	
	
	class QMInfoRowMapper implements RowMapper<QMInfo> {
		@Override
		public QMInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			QMInfo info = new QMInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setUdQmId(rs.getString("ud_qm_id"));
			info.setQmName(rs.getString("qm_name"));
			info.setEffectiveDate(rs.getDate("effective_date"));
			info.setRevisionNo(rs.getString("revision_no"));
			info.setAttachmentFile(rs.getString("attachment_file"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
}
