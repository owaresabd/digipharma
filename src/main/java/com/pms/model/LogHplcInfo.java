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
@Table(name = "lims_log_hplc_infos")
public class LogHplcInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;
	
	@Column(name = "qc_ref_id")
	private UUID qcRefId;
	
	@Transient
	private String qcRefName;
	
	@Column(name = "lot_no")
	private String lotNo;

	@Column(name = "run_start_time")
	private String runStartTime;
	
	@Column(name = "run_end_time")
	private String runEndTime;

	@Column(name = "first_mp_ratio")
	private String firstMpRatio;
	
	@Column(name = "second_mp_ratio")
	private String secondMpRatio;
	
	@Column(name = "first_start_time")
	private String firstStartTime;
	
	@Column(name = "first_end_time")
	private String firstEndTime;
	
	@Column(name = "second_start_time")
	private String secondStartTime;
	
	@Column(name = "second_end_time")
	private String secondEndTime;
	
	@Column(name = "total_time")
	private String totalTime;
	
	@Column(name = "operate_by_id")
	private UUID operateBy;

	@Column(name = "verified_by_id")
	private UUID verifyBy;

	@Transient
	private String operateByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;
	
	
	@Column(name = "column_id")
	private UUID columnId;
	
	@Transient
	private String columnName;
	
	@Transient
	private String verifyStatus;
	
	@Transient
	private UUID equipmentId;
	
	@Transient
	private String equipmentName;
	
	
	
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

	public UUID getQcRefId() {
		return qcRefId;
	}

	public void setQcRefId(UUID qcRefId) {
		this.qcRefId = qcRefId;
	}

	public String getQcRefName() {
		return qcRefName;
	}

	public void setQcRefName(String qcRefName) {
		this.qcRefName = qcRefName;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getRunStartTime() {
		return runStartTime;
	}

	public void setRunStartTime(String runStartTime) {
		this.runStartTime = runStartTime;
	}

	public String getRunEndTime() {
		return runEndTime;
	}

	public void setRunEndTime(String runEndTime) {
		this.runEndTime = runEndTime;
	}

	public String getFirstMpRatio() {
		return firstMpRatio;
	}

	public void setFirstMpRatio(String firstMpRatio) {
		this.firstMpRatio = firstMpRatio;
	}

	public String getSecondMpRatio() {
		return secondMpRatio;
	}

	public void setSecondMpRatio(String secondMpRatio) {
		this.secondMpRatio = secondMpRatio;
	}

	public String getFirstStartTime() {
		return firstStartTime;
	}

	public void setFirstStartTime(String firstStartTime) {
		this.firstStartTime = firstStartTime;
	}

	public String getFirstEndTime() {
		return firstEndTime;
	}

	public void setFirstEndTime(String firstEndTime) {
		this.firstEndTime = firstEndTime;
	}

	public String getSecondStartTime() {
		return secondStartTime;
	}

	public void setSecondStartTime(String secondStartTime) {
		this.secondStartTime = secondStartTime;
	}

	public String getSecondEndTime() {
		return secondEndTime;
	}

	public void setSecondEndTime(String secondEndTime) {
		this.secondEndTime = secondEndTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public UUID getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(UUID operateBy) {
		this.operateBy = operateBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getOperateByName() {
		return operateByName;
	}

	public void setOperateByName(String operateByName) {
		this.operateByName = operateByName;
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


	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public UUID getColumnId() {
		return columnId;
	}

	public void setColumnId(UUID columnId) {
		this.columnId = columnId;
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
	

}
