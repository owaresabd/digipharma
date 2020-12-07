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
@Table(name = "lims_log_verification_dissolution_infos")
public class LogVerificationDissolutionInfo {
	
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

	@Column(name = "operate_by")
	private UUID operateBy;
	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String operateByName;
	
	@Transient
	private String verifyByName;

	@Transient
	private UUID equipmentId;
	
	@Transient
	private String EquipmentName;
	
	@Transient
	private String verifyStatus;
	
	@Transient
	private String doneBy;
	
	@Transient
	private String arnNo;	
	
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

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
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
		
	public String getArnNo() {
		return arnNo;
	}

	public void setArnNo(String arnNo) {
		this.arnNo = arnNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
