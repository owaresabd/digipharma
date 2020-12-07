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
@Table(name = "lims_log_temp_humidity_infos")
public class LogTempHumidityInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "before_temp")
	private String beforeTemp;
	
	@Column(name = "after_temp")
	private String afterTemp;
	
	@Column(name ="before_humidity")
	private String beforeHumidity;
	
	@Column(name ="after_humidity")
	private String afterHumidity;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;

	@Column(name = "rel_humidity")
	private String relHumidity;

	@Column(name = "done_by")
	private UUID doneBy;
	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String doneByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public String getRelHumidity() {
		return relHumidity;
	}

	public void setRelHumidity(String relHumidity) {
		this.relHumidity = relHumidity;
	}

	public UUID getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(UUID doneBy) {
		this.doneBy = doneBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
