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
@Table(name = "lims_log_disinfec_solution_infos")
public class LogDisinfectantSolInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "disinfec_name")
	private String disinfecName;
	
	@Column(name = "amt_disinfec")
	private String amtDisinfec;
	
	@Column(name = "amt_distilled")
	private String amtDistilled;
	
	@Column(name = "total_amt")
	private String totalAmt;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "store_temp")
	private String storeTemp;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;
	
	@Column(name = "prepare_by")
	private UUID prepareBy;

	@Column(name = "check_by")
	private UUID checkBy;

	@Transient
	private String prepareByName;
	
	@Transient
	private String checkByName;

	@Column(name = "remarks")
	private String remarks;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDisinfecName() {
		return disinfecName;
	}

	public void setDisinfecName(String disinfecName) {
		this.disinfecName = disinfecName;
	}

	public String getAmtDisinfec() {
		return amtDisinfec;
	}

	public void setAmtDisinfec(String amtDisinfec) {
		this.amtDisinfec = amtDisinfec;
	}

	public String getAmtDistilled() {
		return amtDistilled;
	}

	public void setAmtDistilled(String amtDistilled) {
		this.amtDistilled = amtDistilled;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStoreTemp() {
		return storeTemp;
	}

	public void setStoreTemp(String storeTemp) {
		this.storeTemp = storeTemp;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public UUID getPrepareBy() {
		return prepareBy;
	}

	public void setPrepareBy(UUID prepareBy) {
		this.prepareBy = prepareBy;
	}

	public UUID getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(UUID checkBy) {
		this.checkBy = checkBy;
	}

	public String getPrepareByName() {
		return prepareByName;
	}

	public void setPrepareByName(String prepareByName) {
		this.prepareByName = prepareByName;
	}

	public String getCheckByName() {
		return checkByName;
	}

	public void setCheckByName(String checkByName) {
		this.checkByName = checkByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
