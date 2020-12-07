package com.pms.model;

import java.sql.Timestamp;
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
@Table(name = "lims_log_toc_infos")
public class LogTOCInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;
	
	@Column(name = "equipment_id")
	private UUID equipmentId;
	
	@Transient
	private String equipmentName;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Column(name = "dispensed_by")
	private UUID dispensedBy;

	@Transient
	private String dispensedByName;

	@Transient
	private String verifyName;

	@Column(name = "is_verify")
	private String isVerify;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private Timestamp verifiedAt;
	
	@Transient
	private String hiddenValCkbx;
	
	@Transient
	private String result;
	@Transient
	private String batchNo;
	

	@Column(name = "sample_name")
	private String sampleName;
	
	
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}


	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public UUID getDispensedBy() {
		return dispensedBy;
	}

	public void setDispensedBy(UUID dispensedBy) {
		this.dispensedBy = dispensedBy;
	}

	public String getDispensedByName() {
		return dispensedByName;
	}

	public void setDispensedByName(String dispensedByName) {
		this.dispensedByName = dispensedByName;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public String getHiddenValCkbx() {
		return hiddenValCkbx;
	}

	public void setHiddenValCkbx(String hiddenValCkbx) {
		this.hiddenValCkbx = hiddenValCkbx;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	
	
}
