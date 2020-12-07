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
@Table(name = "lims_work_instruction_ref_infos")
public class WorkInstructionRefInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "work_ins_id")
	private UUID workInsId;
	
	@Transient
	private String insName;
	
	@Column(name = "work_ins_ud_id")
	private String workInsUdId;
	
	@Column(name = "revision_no")
	private String revisionNo;
	//@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "work_ins_doc_name")
	private String workInsDocName;
	
	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;
	
	@Column(name = "file_content")
	private byte[] fileContent;

	@Column(name = "file_name")
	private String fileName;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public UUID getWorkInsId() {
		return workInsId;
	}

	public void setWorkInsId(UUID workInsId) {
		this.workInsId = workInsId;
	}

	public String getWorkInsUdId() {
		return workInsUdId;
	}

	public void setWorkInsUdId(String workInsUdId) {
		this.workInsUdId = workInsUdId;
	}

	public String getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkInsDocName() {
		return workInsDocName;
	}

	public void setWorkInsDocName(String workInsDocName) {
		this.workInsDocName = workInsDocName;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}


}
