package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "lims_work_instruction_infos")
public class WorkInstructionInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "instruction_name")
	private String instructionName;
	
	@Column(name = "work_ins_ud_id")
	private String insUdId;
	
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

	
	
	public String getInsUdId() {
		return insUdId;
	}

	public void setInsUdId(String insUdId) {
		this.insUdId = insUdId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getInstructionName() {
		return instructionName;
	}

	public void setInstructionName(String instructionName) {
		this.instructionName = instructionName;
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


}
