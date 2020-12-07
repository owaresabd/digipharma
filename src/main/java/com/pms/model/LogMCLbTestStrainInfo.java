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
@Table(name = "log_mc_lb_test_strain_infos")
public class LogMCLbTestStrainInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private UUID cultureId;
	@Transient
	private String cultureName;
	
	@Transient
	private String atccNo;	
	
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
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
	private String totalSuspensionVolue;
	@Transient
	private String resultP1;		
	@Transient
	private String resultP2;
	@Transient
	private String averageResult;
	@Transient
	private String storageCondition;
	
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
	
	public UUID getCultureId() {
		return cultureId;
	}

	public void setCultureId(UUID cultureId) {
		this.cultureId = cultureId;
	}

	public String getCultureName() {
		return cultureName;
	}

	public void setCultureName(String cultureName) {
		this.cultureName = cultureName;
	}

	public String getAtccNo() {
		return atccNo;
	}

	public void setAtccNo(String atccNo) {
		this.atccNo = atccNo;
	}

	public String getTotalSuspensionVolue() {
		return totalSuspensionVolue;
	}

	public void setTotalSuspensionVolue(String totalSuspensionVolue) {
		this.totalSuspensionVolue = totalSuspensionVolue;
	}

	public String getResultP1() {
		return resultP1;
	}

	public void setResultP1(String resultP1) {
		this.resultP1 = resultP1;
	}

	public String getResultP2() {
		return resultP2;
	}

	public void setResultP2(String resultP2) {
		this.resultP2 = resultP2;
	}

	public String getAverageResult() {
		return averageResult;
	}

	public void setAverageResult(String averageResult) {
		this.averageResult = averageResult;
	}

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}
	
}
