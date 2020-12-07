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


public class LogAtomicAbsorptionInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "sample_name_id")
	private UUID sampleNameId;
	
	@Transient
	private String sampleName;

	/*@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;
	
	@Type(type = "date")
	@Column(name = "end_date")
	private Date endDate;*/
	
	@Column(name = "first_time")
	private String firstTime;
	
	@Column(name = "second_time")
	private String secondTime;
	
	@Column(name = "operate_by")
	private UUID operateBy;

	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String operateByName;
	

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;

	@Transient
	private UUID equipmentId;
	
	@Transient
	private String EquipmentName;
	
	@Transient
	private String verifyStatus;
	
	@Transient
	private String doneBy;
	
	@Transient
	private Timestamp verifyAt;
	
	@Transient
	private String lotNo;
	
	@Transient
	private String batchNo;
	
	@Transient
	private String hno3No;
	
	@Transient
	private String h2oNo;
	
	@Transient
	private String sampleTypeName;
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;
	
	@Column(name = "record_time")
	private String recordTime;
	
	@Transient
	private String dispensedByName;
	
	@Transient
	private String totalAnalystTime;
	
	
	
		
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public UUID getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(UUID operateBy) {
		this.operateBy = operateBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getOperateByName() {
		return operateByName;
	}

	public void setOperateByName(String operateByName) {
		this.operateByName = operateByName;
	}

	public String getVerifyByName() {
		return verifyByName;
	}

	public void setVerifyByName(String verifyByName) {
		this.verifyByName = verifyByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}

	public Timestamp getVerifyAt() {
		return verifyAt;
	}

	public void setVerifyAt(Timestamp verifyAt) {
		this.verifyAt = verifyAt;
	}

	public UUID getSampleNameId() {
		return sampleNameId;
	}

	public void setSampleNameId(UUID sampleNameId) {
		this.sampleNameId = sampleNameId;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getSecondTime() {
		return secondTime;
	}

	public void setSecondTime(String secondTime) {
		this.secondTime = secondTime;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getHno3No() {
		return hno3No;
	}

	public void setHno3No(String hno3No) {
		this.hno3No = hno3No;
	}

	public String getH2oNo() {
		return h2oNo;
	}

	public void setH2oNo(String h2oNo) {
		this.h2oNo = h2oNo;
	}

	public String getSampleTypeName() {
		return sampleTypeName;
	}

	public void setSampleTypeName(String sampleTypeName) {
		this.sampleTypeName = sampleTypeName;
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

	public String getDispensedByName() {
		return dispensedByName;
	}

	public void setDispensedByName(String dispensedByName) {
		this.dispensedByName = dispensedByName;
	}

	public String getTotalAnalystTime() {
		return totalAnalystTime;
	}

	public void setTotalAnalystTime(String totalAnalystTime) {
		this.totalAnalystTime = totalAnalystTime;
	}
	
	
}
