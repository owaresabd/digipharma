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
@Table(name = "log_mc_ph_meter_infos")
public class LogMCPhMeterInfo {
	
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

	@Column(name = "record_time")
	private String recordTime;
	
	@Column(name = "ph_result")
	private String phResult;

	@Column(name = "operate_by")
	private UUID operateBy;

	@Column(name = "verified_by")
	private UUID verifiedBy;

	@Transient
	private String operateByName;
	
	@Transient
	private String verifiedByName;

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private UUID equipmentId;
	
	@Transient
	private String EquipmentName;
	
	@Transient
	private String verifiedStatus;
	
	@Transient
	private String doneBy;
	
	@Transient
	private Timestamp verifiedAt;
	
	
	@Transient
	private String temperature;
	
	@Transient
	private String batchNo;
	

	
	
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

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getPhResult() {
		return phResult;
	}

	public void setPhResult(String phResult) {
		this.phResult = phResult;
	}

	public UUID getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(UUID operateBy) {
		this.operateBy = operateBy;
	}

	public String getOperateByName() {
		return operateByName;
	}

	public void setOperateByName(String operateByName) {
		this.operateByName = operateByName;
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

	public String getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}


	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public UUID getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(UUID verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public String getVerifiedByName() {
		return verifiedByName;
	}

	public void setVerifiedByName(String verifiedByName) {
		this.verifiedByName = verifiedByName;
	}

	public String getVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(String verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}
	
	
}
