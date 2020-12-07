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

import com.pms.model.QPDocumentInfo;
import com.pms.model.QPInfo;
import com.pms.model.QmQpDocumentInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class QPRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	
	public List<QPInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id,qm_id, ud_qp_id, qp_name, effective_date, revision_no, attachment_file, remarks, status"
				+ "  from lims_qp_infos where company_id = ? and status="+ (status != null ? "'" + status + "'" : "status")+" order by qp_name ",
						new Object[] { user.getCompanyId() }, new QPInfoRowMapper());
	}
	
	
	public void saveQpInfo(UUID idRandom,QPInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_qp_infos (id,qm_id,ud_qp_id,qp_name, effective_date, revision_no, attachment_file, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
							idRandom,info.getQmId(),info.getUdQpId(),info.getQpName(),info.getEffectiveDate(), info.getRevisionNo(),info.getAttachmentFile(),info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_qp_infos SET qm_id=?, ud_qp_id=?, qp_name=?, effective_date=?, revision_no=?, attachment_file=?, remarks=?, status = ?, updated_by=?, updated_at = ? where id = ?",
					info.getQmId(),info.getUdQpId(),info.getQpName(),info.getEffectiveDate(), info.getRevisionNo(),info.getAttachmentFile(),info.getRemarks(), info.getStatus(),user.getId(), time, info.getId());
		}
	}
	public List<QPDocumentInfo> findAllDocuments(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select a.id, a.qp_id,b.ud_qp_id, a.doc_type_id,c.type_name, a.ud_document_id, a.document_name, a.effective_date,"
				+ "  a.revision_no, a.attachment_file, a.remarks, a.status "
				+ "  FROM lims_qm_qp_document_infos a,"
				+ "	 lims_qp_infos b,"
				+ "	 lims_document_type_infos c"
				+ "  where a.qp_id=b.id "
				+ "  and a.doc_type_id=c.id "
				+ "  and  a.company_id = ? and a.status="+ (status != null ? "'" + status + "'" : "a.status"),
				new Object[] { user.getCompanyId() }, new QPDocumentInfoRowMapper());
	}
	public void saveDocumentInfo(UUID idRandom,QPDocumentInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_qm_qp_document_infos (id, qp_id, doc_type_id, ud_document_id, document_name, effective_date, revision_no, attachment_file, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
							idRandom,info.getQpId(),info.getDocTypeId(),info.getUdDocumentId(),info.getDocumentName(),info.getEffectiveDate(), info.getRevisionNo(),info.getAttachmentFile(),info.getRemarks(), info.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_qm_qp_document_infos SET qp_id=?,doc_type_id=?, ud_document_id=?, document_name=?, effective_date=?, revision_no=?, attachment_file=?, remarks=?, status = ?, updated_by=?, updated_at = ? where id = ?",
					info.getQpId(),info.getDocTypeId(),info.getUdDocumentId(),info.getDocumentName(),info.getEffectiveDate(), info.getRevisionNo(),info.getAttachmentFile(),info.getRemarks(), info.getStatus(),user.getId(), time, info.getId());
		}
	}
	
	public List<QPDocumentInfo> findUnassignedDocument(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select a.id, a.qp_id,b.ud_qp_id, a.doc_type_id,c.type_name, a.ud_document_id, a.document_name, a.effective_date,"
				+ " a.revision_no, a.attachment_file, a.remarks, a.status "
				+ " FROM lims_qm_qp_document_infos a,"
				+ " lims_qp_infos b,"
				+ " lims_document_type_infos c "
				+ " where a.qp_id=b.id "
				+ " and a.doc_type_id=c.id "
				+ " and a.id not in(select document_id from lims_document_privilege_infos where user_id=? and  company_id = ?)",
				new Object[] { id,user.getCompanyId() }, new QPDocumentInfoRowMapper());
	}
	public List<QPDocumentInfo> findAssignedDocument(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select a.id, a.qp_id,b.ud_qp_id, a.doc_type_id,c.type_name, a.ud_document_id, a.document_name, a.effective_date,"
				+ " a.revision_no, a.attachment_file, a.remarks, a.status "
				+ " FROM lims_qm_qp_document_infos a,"
				+ " lims_qp_infos b,"
				+ " lims_document_type_infos c "
				+ " where a.qp_id=b.id "
				+ " and a.doc_type_id=c.id "
				+ " and a.id  in(select document_id from lims_document_privilege_infos where user_id=? and  company_id = ?)",
				new Object[] { id,user.getCompanyId() }, new QPDocumentInfoRowMapper());
	}
	
	public void saveDocumentMapping(UUID documentId, UUID userId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				"insert into lims_document_privilege_infos(user_id, document_id, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?)",
				userId,documentId,user.getCompanyId(),user.getId(),time);
	}

	public void deleteDocumentMapping(UUID documentId, UUID userId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		System.out.println();
		jdbcTemplate.update("DELETE FROM lims_document_privilege_infos WHERE document_id = ? "
				+ " AND user_id = ? AND  company_id = ?", documentId, userId,user.getCompanyId());
		
	}
	public List<QmQpDocumentInfo> getQMByUserId() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from (select distinct on (qm_id) qm_id, ud_qm_id, qm_name, qm_effective_date, qm_revision_no, qm_attachment_file,"
				+ " qp_id, ud_qp_id, qp_name, qp_effective_date, qp_revision_no, qp_attachment_file,"
				+ " document_id, doc_type_id, doc_type_name, ud_document_id, document_name, doc_effective_date, doc_revision_no, doc_attachment_file"
				+ " from view_qm_qp_document_infos where company_id=? and user_id='"+user.getId()+"') a  order by  ud_qm_id ",
				new Object[] { user.getCompanyId() }, new QmQPDocumentInfoRowMapper());
	}
	public List<QmQpDocumentInfo> getQPByUserId() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select * from (select distinct on (qp_id) qp_id, qm_id, ud_qm_id, qm_name, qm_effective_date, qm_revision_no, qm_attachment_file,"
				+ " ud_qp_id, qp_name, qp_effective_date, qp_revision_no, qp_attachment_file,"
				+ " document_id, doc_type_id, doc_type_name, ud_document_id, document_name, doc_effective_date, doc_revision_no, doc_attachment_file"
				+ " from view_qm_qp_document_infos where company_id=? and user_id='"+user.getId()+"') a order by ud_qp_id",
				new Object[] { user.getCompanyId() }, new QmQPDocumentInfoRowMapper());
	}
	public List<QmQpDocumentInfo> getQPDocumentByUserId() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select qp_id, qm_id, ud_qm_id, qm_name, qm_effective_date, qm_revision_no, qm_attachment_file,"
				+ " ud_qp_id, qp_name, qp_effective_date, qp_revision_no, qp_attachment_file,"
				+ " document_id, doc_type_id, doc_type_name, ud_document_id, document_name, doc_effective_date, doc_revision_no, doc_attachment_file"
				+ " from view_qm_qp_document_infos where company_id=? and user_id='"+user.getId()+"' order by ud_document_id ",
				new Object[] { user.getCompanyId()}, new QmQPDocumentInfoRowMapper());
	}
	class QPInfoRowMapper implements RowMapper<QPInfo> {
		@Override
		public QPInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			QPInfo info = new QPInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setQmId((UUID) rs.getObject("qm_id"));
			info.setUdQpId(rs.getString("ud_qp_id"));
			info.setQpName(rs.getString("qp_name"));
			info.setEffectiveDate(rs.getDate("effective_date"));
			info.setRevisionNo(rs.getString("revision_no"));
			info.setAttachmentFile(rs.getString("attachment_file"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class QPDocumentInfoRowMapper implements RowMapper<QPDocumentInfo> {
		@Override
		public QPDocumentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			QPDocumentInfo info = new QPDocumentInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setQpId((UUID)rs.getObject("qp_id"));
			info.setUdQpId(rs.getString("ud_qp_id"));
			info.setDocTypeId((UUID)rs.getObject("doc_type_id"));
			info.setDocTypeName(rs.getString("type_name"));
			info.setUdDocumentId(rs.getString("ud_document_id"));
			info.setDocumentName(rs.getString("document_name"));
			info.setEffectiveDate(rs.getDate("effective_date"));
			info.setRevisionNo(rs.getString("revision_no"));
			info.setAttachmentFile(rs.getString("attachment_file"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class QmQPDocumentInfoRowMapper implements RowMapper<QmQpDocumentInfo> {
		
		@Override
		public QmQpDocumentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			QmQpDocumentInfo info = new QmQpDocumentInfo();
		    info.setQmId((UUID)rs.getObject("qm_id"));
			info.setUdQmId(rs.getString("ud_qm_id"));
			info.setQmName(rs.getString("qm_name"));
			info.setQmEffectiveDate(rs.getDate("qm_effective_date"));
			info.setQmRevisionNo(rs.getString("qm_revision_no"));
			info.setQmAttachmentFile(rs.getString("qm_attachment_file"));
			info.setQpId((UUID)rs.getObject("qp_id"));
			info.setUdQpId(rs.getString("ud_qp_id"));
			info.setQpName(rs.getString("qp_name"));
			info.setQpEffectiveDate(rs.getDate("qp_effective_date"));
			info.setQpRevisionNo(rs.getString("qp_revision_no"));
			info.setQpAttachmentFile(rs.getString("qp_attachment_file"));
			info.setDocumentId((UUID)rs.getObject("document_id"));
			info.setDocTypeId((UUID)rs.getObject("doc_type_id"));
			info.setDocTypeName(rs.getString("doc_type_name"));
			info.setUdDocumentId(rs.getString("ud_document_id"));
			info.setDocumentName(rs.getString("document_name"));
			info.setDocEffectiveDate(rs.getDate("doc_effective_date"));
			info.setDocRevisionNo(rs.getString("doc_revision_no"));
			info.setDocAttachmentFile(rs.getString("doc_attachment_file"));
			return info;
		}
	}
	
	
	
}
