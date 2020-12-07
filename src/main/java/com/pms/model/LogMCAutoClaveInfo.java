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
@Table(name = "log_mc_auto_clave_infos")
public class LogMCAutoClaveInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private UUID equipmentId;
	@Transient
	private String equipmentName;
	

	@Transient
	private UUID materialId;
	@Transient
	private String materialName;
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;
	
	@Column(name = "ster_start_time")
	private String sterStartTime;	
	
	@Column(name = "ster_end_time")
	private String sterEndTime;
	
	@Column(name = "exha_start_time")
	private String exhaStartTime;	
	
	@Column(name = "exha_end_time")
	private String exhaEndTime;
	
	
	@Column(name = "operate_by")
	private UUID operateBy;

	@Transient
	private String operateByName;
	
	@Column(name = "checked_by")
	private UUID checkedBy;
	
	@Transient
	private String checkedStatus;
	
	@Transient
	private String checkedByName;

	@Column(name = "cleaned_status")
	private String cleanedStatus;

	@Column(name = "cleaned_by")
	private UUID cleanedBy;
	
	@Transient
	private String cleanedByName;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private String heatingCycle;
	@Transient
	private String totalCycle;
	@Transient
	private String batchNo;
	@Transient
	private String indicatorTape;
	
	
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


	public UUID getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(UUID checkedBy) {
		this.checkedBy = checkedBy;
	}

	public String getCheckedByName() {
		return checkedByName;
	}

	public void setCheckedByName(String checkedByName) {
		this.checkedByName = checkedByName;
	}

	public String getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(String checkedStatus) {
		this.checkedStatus = checkedStatus;
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

	public String getCleanedByName() {
		return cleanedByName;
	}

	public void setCleanedByName(String cleanedByName) {
		this.cleanedByName = cleanedByName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public UUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(UUID materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSterStartTime() {
		return sterStartTime;
	}

	public void setSterStartTime(String sterStartTime) {
		this.sterStartTime = sterStartTime;
	}

	public String getSterEndTime() {
		return sterEndTime;
	}

	public void setSterEndTime(String sterEndTime) {
		this.sterEndTime = sterEndTime;
	}

	public String getExhaStartTime() {
		return exhaStartTime;
	}

	public void setExhaStartTime(String exhaStartTime) {
		this.exhaStartTime = exhaStartTime;
	}

	public String getExhaEndTime() {
		return exhaEndTime;
	}

	public void setExhaEndTime(String exhaEndTime) {
		this.exhaEndTime = exhaEndTime;
	}

	public String getHeatingCycle() {
		return heatingCycle;
	}

	public void setHeatingCycle(String heatingCycle) {
		this.heatingCycle = heatingCycle;
	}

	public String getTotalCycle() {
		return totalCycle;
	}

	public void setTotalCycle(String totalCycle) {
		this.totalCycle = totalCycle;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getIndicatorTape() {
		return indicatorTape;
	}

	public void setIndicatorTape(String indicatorTape) {
		this.indicatorTape = indicatorTape;
	}

}
