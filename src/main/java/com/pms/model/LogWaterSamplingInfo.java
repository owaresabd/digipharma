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
@Table(name = "lims_log_water_sampling_infos")
public class LogWaterSamplingInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;
	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Column(name = "dispensed_by")
	private UUID dispensedBy;

	@Transient
	private String dispensedByName;

	@Transient
	private String verifyName;

	@Column(name = "is_verify")
	private String isVerify;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private Timestamp verifiedAt;
	
	@Transient
	private String hiddenValCkbx;
	
	
	@Column(name = "location_id")
	private UUID locationId;
	
	@Transient
	private String locationName;
	
	@Column(name = "water_type_id")
	private UUID waterTypeId;
	
	@Transient
	private String waterTypeName;
	
	@Transient
	private String sampleId;
		

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

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}


	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public UUID getDispensedBy() {
		return dispensedBy;
	}

	public void setDispensedBy(UUID dispensedBy) {
		this.dispensedBy = dispensedBy;
	}

	public String getDispensedByName() {
		return dispensedByName;
	}

	public void setDispensedByName(String dispensedByName) {
		this.dispensedByName = dispensedByName;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public String getHiddenValCkbx() {
		return hiddenValCkbx;
	}

	public void setHiddenValCkbx(String hiddenValCkbx) {
		this.hiddenValCkbx = hiddenValCkbx;
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public UUID getWaterTypeId() {
		return waterTypeId;
	}

	public void setWaterTypeId(UUID waterTypeId) {
		this.waterTypeId = waterTypeId;
	}


	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getWaterTypeName() {
		return waterTypeName;
	}

	public void setWaterTypeName(String waterTypeName) {
		this.waterTypeName = waterTypeName;
	}
	
}
