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

@Entity
@Table(name = "view_column_performance_pending")
public class LogColumnPerformanceRecordInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private String equipmentByName;
	@Transient
	private String arnName;
	@Transient
	private String colName;
	@Transient
	private UUID equipmentId;
	@Transient
	private UUID qcRefId;
	@Transient
	private UUID colId; 
	
	@Transient
	private UUID  performanceId;
	@Transient
	private Date recordDate;	
	@Transient
	private String tp;
	@Transient
	private String tf;
	@Transient
	private String rsd;
	@Transient
	private String remarks;
	
	@Transient
	private String columnRecordStatus;
	
	@Transient
	private String isRecordVerify;
	
	@Transient
	private String isVerify;
		
	
	public String getIsVerify() {
		return isVerify;
	}
	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
		
	public String getEquipmentByName() {
		return equipmentByName;
	}
	public void setEquipmentByName(String equipmentByName) {
		this.equipmentByName = equipmentByName;
	}
	public String getArnName() {
		return arnName;
	}
	public void setArnName(String arnName) {
		this.arnName = arnName;
	}

	public UUID getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}
	public UUID getQcRefId() {
		return qcRefId;
	}
	public void setQcRefId(UUID qcRefId) {
		this.qcRefId = qcRefId;
	}

	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getTf() {
		return tf;
	}
	public void setTf(String tf) {
		this.tf = tf;
	}
	public String getRsd() {
		return rsd;
	}
	public void setRsd(String rsd) {
		this.rsd = rsd;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public UUID getColId() {
		return colId;
	}
	public void setColId(UUID colId) {
		this.colId = colId;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColumnRecordStatus() {
		return columnRecordStatus;
	}
	public void setColumnRecordStatus(String columnRecordStatus) {
		this.columnRecordStatus = columnRecordStatus;
	}
	public String getIsRecordVerify() {
		return isRecordVerify;
	}
	public void setIsRecordVerify(String isRecordVerify) {
		this.isRecordVerify = isRecordVerify;
	}
	public UUID getPerformanceId() {
		return performanceId;
	}
	public void setPerformanceId(UUID performanceId) {
		this.performanceId = performanceId;
	}
	

}
