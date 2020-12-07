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
@Table(name = "lims_log_moisture_analyzer_infos")
public class LogMoistureAnalyzerInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	
	@Transient
	private String itemName;
	
			
	@Column(name = "operate_by")
	private UUID operateBy;
	
	@Column(name = "clean_by")
	private UUID cleanBy;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String operateByName;

	@Transient
	private String cleanByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private UUID equipmentId;
	
	@Transient
	private String lotNo;
	
	@Transient
	private String EquipmentName;
	
	@Transient
	private String verifyStatus;
	
	@Transient
	private String doneBy;
	
	@Transient
	private String cleanStatus;
	
	@Transient
	private String recordTime;
	@Column(name = "sample_type_id")
	private UUID sampleTypeId;
	
	@Transient
	private String qcRefName;
	
	@Transient
	private String sampleTypeName;
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;
	
	@Column(name = "qc_reference_id")
	private UUID qcReferenceId;

	@Transient
	private String qcReference;
	
	@Transient
	private String result;	
	
	@Transient
	private UUID qcRefId;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public UUID getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(UUID operateBy) {
		this.operateBy = operateBy;
	}

	public UUID getCleanBy() {
		return cleanBy;
	}

	public void setCleanBy(UUID cleanBy) {
		this.cleanBy = cleanBy;
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

	public String getCleanByName() {
		return cleanByName;
	}

	public void setCleanByName(String cleanByName) {
		this.cleanByName = cleanByName;
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

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
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

	public String getCleanStatus() {
		return cleanStatus;
	}

	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public UUID getSampleTypeId() {
		return sampleTypeId;
	}

	public void setSampleTypeId(UUID sampleTypeId) {
		this.sampleTypeId = sampleTypeId;
	}

	public String getQcRefName() {
		return qcRefName;
	}

	public void setQcRefName(String qcRefName) {
		this.qcRefName = qcRefName;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public UUID getQcRefId() {
		return qcRefId;
	}

	public void setQcRefId(UUID qcRefId) {
		this.qcRefId = qcRefId;
	}

	
}
