package com.pms.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "lims_log_waterbath_infos")
public class LogWaterBathInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	 
	@Type(type = "date")
	@Column(name = "operation_date")
	private Date operationDate;
	
	
	@Column(name = "switch_on_time")
	private String switchOnTime;
	
	@Column(name = "material_id")
	private UUID materialId;
	
	@Column(name = "switch_off_time")
	private String switchOffTime;
	
	@Column(name = "temperature")
	private String temperature;
	
	@Column(name = "operated_by")
	private UUID operatedBy;
	
	@Column(name = "checked_by")
	private UUID checkedBy;
	
	@Column(name = "remarks")
	private String remarks;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getSwitchOnTime() {
		return switchOnTime;
	}

	public void setSwitchOnTime(String switchOnTime) {
		this.switchOnTime = switchOnTime;
	}

	public UUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(UUID materialId) {
		this.materialId = materialId;
	}

	public String getSwitchOffTime() {
		return switchOffTime;
	}

	public void setSwitchOffTime(String switchOffTime) {
		this.switchOffTime = switchOffTime;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public UUID getOperatedBy() {
		return operatedBy;
	}

	public void setOperatedBy(UUID operatedBy) {
		this.operatedBy = operatedBy;
	}

	public UUID getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(UUID checkedBy) {
		this.checkedBy = checkedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
