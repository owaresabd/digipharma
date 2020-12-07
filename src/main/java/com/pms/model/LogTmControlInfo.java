package com.pms.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "lims_log_tmcontrol_infos")
public class LogTmControlInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "qc_reference_id")
	private UUID qcReferenceId;

	@Transient
	private String qcReference;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	

	@Column(name = "used_by")
	private UUID usedBy;

	@Column(name = "return_by")
	private UUID returnBy;
	
	@Type(type = "date")
	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "controlled_by")
	private UUID controlledBy;

	@Transient
	private String usedByName;
	
	@Transient
	private String returnByName;

	@Transient
	private String controlByName;

	@Column(name = "remarks")
	private String remarks;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getQcReferenceId() {
		return qcReferenceId;
	}

	public void setQcReferenceId(UUID qcReferenceId) {
		this.qcReferenceId = qcReferenceId;
	}

	public String getQcReference() {
		return qcReference;
	}

	public void setQcReference(String qcReference) {
		this.qcReference = qcReference;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public UUID getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(UUID usedBy) {
		this.usedBy = usedBy;
	}

	public UUID getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(UUID returnBy) {
		this.returnBy = returnBy;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public UUID getControlledBy() {
		return controlledBy;
	}

	public void setControlledBy(UUID controlledBy) {
		this.controlledBy = controlledBy;
	}

	public String getUsedByName() {
		return usedByName;
	}

	public void setUsedByName(String usedByName) {
		this.usedByName = usedByName;
	}

	public String getReturnByName() {
		return returnByName;
	}

	public void setReturnByName(String returnByName) {
		this.returnByName = returnByName;
	}

	public String getControlByName() {
		return controlByName;
	}

	public void setControlByName(String controlByName) {
		this.controlByName = controlByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	

}
