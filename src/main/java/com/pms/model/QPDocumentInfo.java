package com.pms.model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "lims_qm_qp_document_infos")
public class QPDocumentInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "qp_id")
	private UUID qpId;
	
	@Transient
	private String udQpId;
	
	@Column(name = "doc_type_id")
	private UUID docTypeId;
	
	@Transient
	private String docTypeName;
	
	@Column(name = "ud_document_id")
	private String udDocumentId;
	
	@Column(name = "document_name")
	private String documentName;
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "revision_no")
	private String revisionNo;
	
	
	@Column(name = "attachment_file")
	private String attachmentFile;
	
	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getQpId() {
		return qpId;
	}

	public void setQpId(UUID qpId) {
		this.qpId = qpId;
	}

	public UUID getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(UUID docTypeId) {
		this.docTypeId = docTypeId;
	}

	

	public String getUdDocumentId() {
		return udDocumentId;
	}

	public void setUdDocumentId(String udDocumentId) {
		this.udDocumentId = udDocumentId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getUdQpId() {
		return udQpId;
	}

	public void setUdQpId(String udQpId) {
		this.udQpId = udQpId;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	
	
}