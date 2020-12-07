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
@Table(name = "lims_log_dissolution_infos")
public class LogDissolutionInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "qc_reference_id")
	private UUID qcReferenceId;

	@Transient
	private String qcReferenceName;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;
	
	@Column(name = "operate_by")
	private UUID testedBy;
	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String testedByName;
	
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
	
	@Column(name = "clean_by")
	private UUID cleanBy;
	
	
	@Transient
	private String cleanByName;	
	
	

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

	public String getQcReferenceName() {
		return qcReferenceName;
	}

	public void setQcReferenceName(String qcReferenceName) {
		this.qcReferenceName = qcReferenceName;
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

	public UUID getTestedBy() {
		return testedBy;
	}

	public void setTestedBy(UUID testedBy) {
		this.testedBy = testedBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getTestedByName() {
		return testedByName;
	}

	public void setTestedByName(String testedByName) {
		this.testedByName = testedByName;
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


	public String getCleanByName() {
		return cleanByName;
	}

	public void setCleanByName(String cleanByName) {
		this.cleanByName = cleanByName;
	}

	public UUID getCleanBy() {
		return cleanBy;
	}

	public void setCleanBy(UUID cleanBy) {
		this.cleanBy = cleanBy;
	}
				

}
