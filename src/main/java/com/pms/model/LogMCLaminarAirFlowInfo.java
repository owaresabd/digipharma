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
@Table(name = "log_mc_laminar_air_flow_infos")
public class LogMCLaminarAirFlowInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private UUID equipmentId;
	
	@Transient
	private String EquipmentName;
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "laf_on_time")
	private String lafOnTime;
	
	@Column(name = "uv_on_time")
	private String uvOnTime;

	@Column(name = "laf_off_time")
	private String lafOffTime;
	
	@Column(name = "uv_off_time")
	private String uvOffTime;
	
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

	
	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private String purpose;
	
	@Transient
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

	public String getLafOnTime() {
		return lafOnTime;
	}

	public void setLafOnTime(String lafOnTime) {
		this.lafOnTime = lafOnTime;
	}

	public String getUvOnTime() {
		return uvOnTime;
	}

	public void setUvOnTime(String uvOnTime) {
		this.uvOnTime = uvOnTime;
	}

	public String getLafOffTime() {
		return lafOffTime;
	}

	public void setLafOffTime(String lafOffTime) {
		this.lafOffTime = lafOffTime;
	}

	public String getUvOffTime() {
		return uvOffTime;
	}

	public void setUvOffTime(String uvOffTime) {
		this.uvOffTime = uvOffTime;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
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

}
