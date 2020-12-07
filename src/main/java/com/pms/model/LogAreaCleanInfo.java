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
@Table(name = "lims_log_area_cleaning_infos")
public class LogAreaCleanInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "agent_id")
	private UUID agentId;
	
	@Transient
	private String agentName;
	
	@Column(name = "cleaning_equipment")
	private String cleaningEquipment;
	
	@Type(type = "date")
	@Column(name = "cleaning_date")
	private Date cleaningDate;
	
	@Type(type = "date")
	@Column(name = "exp_date")
	private Date cleaningExpDate;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "clean_by")
	private UUID cleanBy;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String cleanByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;
	
	@Transient
	private Timestamp verifiedAt;

	@Column(name = "is_verify")
	private String isVerify;
	
	@Transient
	private UUID cleaningEquipID;
	
	@Transient
	private String cleaningEquipName;
	
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getAgentId() {
		return agentId;
	}

	public void setAgentId(UUID agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	

	public Date getCleaningDate() {
		return cleaningDate;
	}

	public void setCleaningDate(Date cleaningDate) {
		this.cleaningDate = cleaningDate;
	}

	public Date getCleaningExpDate() {
		return cleaningExpDate;
	}

	public void setCleaningExpDate(Date cleaningExpDate) {
		this.cleaningExpDate = cleaningExpDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public UUID getCleaningEquipID() {
		return cleaningEquipID;
	}

	public void setCleaningEquipID(UUID cleaningEquipID) {
		this.cleaningEquipID = cleaningEquipID;
	}

	public String getCleaningEquipName() {
		return cleaningEquipName;
	}

	public void setCleaningEquipName(String cleaningEquipName) {
		this.cleaningEquipName = cleaningEquipName;
	}

	public String getCleaningEquipment() {
		return cleaningEquipment;
	}

	public void setCleaningEquipment(String cleaningEquipment) {
		this.cleaningEquipment = cleaningEquipment;
	}

}
