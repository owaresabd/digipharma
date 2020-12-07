package com.pms.model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "lims_qp_infos")
public class QPInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "qm_id")
	private UUID qmId;

	@Column(name = "ud_qp_id")
	private String udQpId;
	
	@Column(name = "qp_name")
	private String qpName;
	
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

	

	public UUID getQmId() {
		return qmId;
	}

	public void setQmId(UUID qmId) {
		this.qmId = qmId;
	}

	public String getUdQpId() {
		return udQpId;
	}

	public void setUdQpId(String udQpId) {
		this.udQpId = udQpId;
	}

	public String getQpName() {
		return qpName;
	}

	public void setQpName(String qpName) {
		this.qpName = qpName;
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

	
	
}