package com.pms.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lims_temp_humidity_setup_infos")
public class TempAndHumiditySetupInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "equipment_id")
	private UUID equipmentId;

	@Column(name = "locaton_id")
	private UUID locationId;

	@Column(name = "department_id")
	private UUID departmentId;

	@Column(name = "created_by")
	private UUID createBy;

	@Column(name = "create_at")
	private Timestamp createAt;

	@Column(name = "update_by")
	private UUID updateBy;

	@Column(name = "update_at")
	private Timestamp updateAt;

	@Column(name = "correction_val_fr_Temp")
	private String correctionValFrTemp;

	@Column(name = "correction_val_fr_humidity")
	private String correctionValFrHumidity;

	@Transient
	private String equipmentByName;

	@Transient
	private String departmentByName;

	@Transient
	private String locationByName;

	@Transient
	private String createByName;

	@Transient
	private String updateByName;

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

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public UUID getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(UUID departmentId) {
		this.departmentId = departmentId;
	}

	public UUID getCreateBy() {
		return createBy;
	}

	public void setCreateBy(UUID createBy) {
		this.createBy = createBy;
	}

	public UUID getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(UUID updateBy) {
		this.updateBy = updateBy;
	}

	public String getCorrectionValFrTemp() {
		return correctionValFrTemp;
	}

	public void setCorrectionValFrTemp(String correctionValFrTemp) {
		this.correctionValFrTemp = correctionValFrTemp;
	}

	public String getCorrectionValFrHumidity() {
		return correctionValFrHumidity;
	}

	public void setCorrectionValFrHumidity(String correctionValFrHumidity) {
		this.correctionValFrHumidity = correctionValFrHumidity;
	}

	public String getEquipmentByName() {
		return equipmentByName;
	}

	public void setEquipmentByName(String equipmentByName) {
		this.equipmentByName = equipmentByName;
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

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

}
