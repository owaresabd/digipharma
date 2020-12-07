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
@Table(name = "lims_log_temp_humidity_record_infos")
public class LogTempeHumidityRecordInfo {

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

	@Column(name = "before_relative_humidity")
	private String beforeRelativeHumidity;

	@Column(name = "after_relative_humidity")
	private String afterRelativeHumidity;

	@Column(name = "before_temp")
	private String beforeTemp;

	@Column(name = "after_temp")
	private String afterTemp;

	@Column(name = "done_by")
	private UUID doneBy;

	@Column(name = "is_verify")
	private String isVerify;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Column(name = "verified_at")
	private Timestamp verifiedAt;

	@Column(name = "update_by")
	private UUID updateBy;

	@Column(name = "update_at")
	private Timestamp updateAt;

	@Column(name = "equipment_id")
	private UUID equipmentId;

	@Column(name = "remarks")
	private String remarks;

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

	@Transient
	private String companyByName;

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

	public String getBeforeRelativeHumidity() {
		return beforeRelativeHumidity;
	}

	public void setBeforeRelativeHumidity(String beforeRelativeHumidity) {
		this.beforeRelativeHumidity = beforeRelativeHumidity;
	}

	public String getAfterRelativeHumidity() {
		return afterRelativeHumidity;
	}

	public void setAfterRelativeHumidity(String afterRelativeHumidity) {
		this.afterRelativeHumidity = afterRelativeHumidity;
	}

	public String getBeforeTemp() {
		return beforeTemp;
	}

	public void setBeforeTemp(String beforeTemp) {
		this.beforeTemp = beforeTemp;
	}

	public String getAfterTemp() {
		return afterTemp;
	}

	public void setAfterTemp(String afterTemp) {
		this.afterTemp = afterTemp;
	}

	public UUID getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(UUID doneBy) {
		this.doneBy = doneBy;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public UUID getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(UUID updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
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

}
