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
@Table(name = "lims_maintenance_detail_infos")
public class EquipMaintenInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "equipment_id")
	private UUID equipmentId;
	
	@Transient
	private String equipmentName;
	
	@Transient
	private String equipFullName;
	
	@Transient
	private String currLocation;
	@Transient
	private String dateArray;
	
	@Column(name = "type_id")
	private UUID typeId;
	
	@Transient
	private String typeName;
	
	@Transient
	private int maxValue;

	@Column(name = "freq_type")
	private String freqType;
	
	@Column(name = "freq_duration")
	private String freqDuration;
	
	@Type(type = "date")
	@Column(name = "last_schedule")
	private Date lastSchedule;
	
	@Type(type = "date")
	@Column(name = "next_schedule")
	private Date nextSchedule;
	
	@Column(name = "attachment_nm")
	private String attachmentNm;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "status")
	private String status;
	
	@Transient
	private int diffDays;
	@Transient
	private int notifyTime;

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
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

	public String getEquipFullName() {
		return equipFullName;
	}

	public void setEquipFullName(String equipFullName) {
		this.equipFullName = equipFullName;
	}

	public String getCurrLocation() {
		return currLocation;
	}

	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}

	public UUID getTypeId() {
		return typeId;
	}

	public void setTypeId(UUID typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFreqType() {
		return freqType;
	}

	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}

	public String getFreqDuration() {
		return freqDuration;
	}

	public void setFreqDuration(String freqDuration) {
		this.freqDuration = freqDuration;
	}

	public Date getLastSchedule() {
		return lastSchedule;
	}

	public void setLastSchedule(Date lastSchedule) {
		this.lastSchedule = lastSchedule;
	}

	public Date getNextSchedule() {
		return nextSchedule;
	}

	public void setNextSchedule(Date nextSchedule) {
		this.nextSchedule = nextSchedule;
	}

	public String getAttachmentNm() {
		return attachmentNm;
	}

	public void setAttachmentNm(String attachmentNm) {
		this.attachmentNm = attachmentNm;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(int diffDays) {
		this.diffDays = diffDays;
	}

	public int getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(int notifyTime) {
		this.notifyTime = notifyTime;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getDateArray() {
		return dateArray;
	}

	public void setDateArray(String dateArray) {
		this.dateArray = dateArray;
	}

	

}
