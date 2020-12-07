package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class FrequencyInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "equipment_id")
	private UUID equipmentId;
	
	@Transient
	private String equipmentName;
	
	@Column(name = "type_id")
	private UUID typeId;
	
	@Transient
	private String typeName;

	@Column(name = "freq_type")
	private String freqType;
	
	@Transient
	private String freqTypeName;
	
	@Column(name = "freq_duration")
	private String freqDuration;
	
	@Column(name = "notify_time")
	private String notifyTime;
	
	@Column(name = "status")
	private String status;


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


	public UUID getTypeId() {
		return typeId;
	}


	public void setTypeId(UUID typeId) {
		this.typeId = typeId;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEquipmentName() {
		return equipmentName;
	}


	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getFreqTypeName() {
		return freqTypeName;
	}


	public void setFreqTypeName(String freqTypeName) {
		this.freqTypeName = freqTypeName;
	}


	public String getNotifyTime() {
		return notifyTime;
	}


	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	


}
