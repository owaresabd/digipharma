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
@Table(name = "log_mc_temp_refrigerator_record_infos")
public class LogMCTempRefrigeratorRecordInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Transient
	private String currentDate;

	@Column(name = "record_time")
	private String recordTime;
	
	@Column(name = "done_by")
	private UUID doneBy;

	@Column(name = "cleaned_status")
	private String cleanedStatus;

	@Column(name = "cleaned_by")
	private UUID cleanedBy;
	
	@Transient
	private String cleanedByName;
	
	@Transient
	private String checkedByName;
	

	@Column(name = "cleaned_at")
	private Timestamp cleanedAt;
	
	
	@Column(name = "checked_by")
	private UUID checkedBy;

	@Column(name = "checked_status")
	private String checkedStatus;

	@Column(name = "checked_at")
	private Timestamp checkedAt;
	

	@Column(name = "updated_by")
	private UUID updatedBy;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "equipment_id")
	private UUID equipmentId;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "evening_status")
	private String eveningStatus;

		
	@Transient
	private String departmentByName;

	@Transient
	private String locationByName;

	@Transient
	private String equipmentName;

	@Transient
	private String doneByName;

	@Transient
	private String verifyByName;

	@Column(name = "company_id")
	private UUID companyId;
	
	@Column(name = "morning_time")
	private String morningTime;
	
	@Column(name = "evening_time")
	private String eveningTime;
	
	@Transient
	private String morningTemp;
	
	@Transient
	private String eveningTemp;
	
	@Transient
	private String companyByName;
	
	@Transient
	private String eveningDoneByName;
	
	@Column(name = "agent_id")
	private UUID agentId;
	
	@Transient
	private String agentName;
	

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

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
	public UUID getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(UUID doneBy) {
		this.doneBy = doneBy;
	}

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDepartmentByName() {
		return departmentByName;
	}

	public void setDepartmentByName(String departmentByName) {
		this.departmentByName = departmentByName;
	}

	public String getLocationByName() {
		return locationByName;
	}

	public void setLocationByName(String locationByName) {
		this.locationByName = locationByName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getDoneByName() {
		return doneByName;
	}

	public void setDoneByName(String doneByName) {
		this.doneByName = doneByName;
	}

	public String getVerifyByName() {
		return verifyByName;
	}

	public void setVerifyByName(String verifyByName) {
		this.verifyByName = verifyByName;
	}

	public UUID getCompanyId() {
		return companyId;
	}

	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}

	public String getCompanyByName() {
		return companyByName;
	}

	public void setCompanyByName(String companyByName) {
		this.companyByName = companyByName;
	}

	public String getMorningTime() {
		return morningTime;
	}

	public void setMorningTime(String morningTime) {
		this.morningTime = morningTime;
	}

	public String getEveningTime() {
		return eveningTime;
	}

	public void setEveningTime(String eveningTime) {
		this.eveningTime = eveningTime;
	}

	public String getMorningTemp() {
		return morningTemp;
	}

	public void setMorningTemp(String morningTemp) {
		this.morningTemp = morningTemp;
	}

	public String getEveningTemp() {
		return eveningTemp;
	}

	public void setEveningTemp(String eveningTemp) {
		this.eveningTemp = eveningTemp;
	}

	public String getEveningDoneByName() {
		return eveningDoneByName;
	}

	public void setEveningDoneByName(String eveningDoneByName) {
		this.eveningDoneByName = eveningDoneByName;
	}

	public UUID getAgentId() {
		return agentId;
	}

	public void setAgentId(UUID agentId) {
		this.agentId = agentId;
	}

	public String getCleanedStatus() {
		return cleanedStatus;
	}

	public void setCleanedStatus(String cleanedStatus) {
		this.cleanedStatus = cleanedStatus;
	}

	public UUID getCleanedBy() {
		return cleanedBy;
	}

	public void setCleanedBy(UUID cleanedBy) {
		this.cleanedBy = cleanedBy;
	}

	public Timestamp getCleanedAt() {
		return cleanedAt;
	}

	public void setCleanedAt(Timestamp cleanedAt) {
		this.cleanedAt = cleanedAt;
	}

	public Timestamp getCheckedAt() {
		return checkedAt;
	}

	public void setCheckedAt(Timestamp checkedAt) {
		this.checkedAt = checkedAt;
	}

	public UUID getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UUID updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UUID getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(UUID checkedBy) {
		this.checkedBy = checkedBy;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public String getEveningStatus() {
		return eveningStatus;
	}

	public void setEveningStatus(String eveningStatus) {
		this.eveningStatus = eveningStatus;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCleanedByName() {
		return cleanedByName;
	}

	public void setCleanedByName(String cleanedByName) {
		this.cleanedByName = cleanedByName;
	}

	public String getCheckedByName() {
		return checkedByName;
	}

	public void setCheckedByName(String checkedByName) {
		this.checkedByName = checkedByName;
	}

}
