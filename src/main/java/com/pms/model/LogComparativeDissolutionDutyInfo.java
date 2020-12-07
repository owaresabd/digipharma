package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lims_log_comparative_dissolution_study_infos")
public class LogComparativeDissolutionDutyInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "manufacturer_id")
	private UUID manufacturerId;

	@Transient
	private String manufacturerName;

	/*@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;*/

	@Transient
	private String resultComp;
	
	@Transient
	private String batchNo;
	
	@Transient
	private String productName;
	
	@Column(name = "created_by")
	private UUID createdBy;
	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String createdyByName;
	
	@Transient
	private String resultByName;
	
	@Transient
	private String resultStatus;
	
	@Transient
	private String flag;	
	

	
/*		
	@Transient
	private String verifyByName;*/

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private UUID analystBy;
		
	@Transient
	private String analystByName;
	

	@Transient
	private String doneBy;

	@Transient
	private String cleanByName;	
	
	
	@Transient
	private UUID distributedBy;
		
	@Transient
	private String distributedName;
	
	@Transient
	private String distributedStatus;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}

	public String getCleanByName() {
		return cleanByName;
	}

	public void setCleanByName(String cleanByName) {
		this.cleanByName = cleanByName;
	}

	public UUID getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(UUID manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getResultComp() {
		return resultComp;
	}

	public void setResultComp(String resultComp) {
		this.resultComp = resultComp;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getCreatedyByName() {
		return createdyByName;
	}

	public void setCreatedyByName(String createdyByName) {
		this.createdyByName = createdyByName;
	}


	public String getAnalystByName() {
		return analystByName;
	}

	public void setAnalystByName(String analystByName) {
		this.analystByName = analystByName;
	}

	public UUID getDistributedBy() {
		return distributedBy;
	}

	public void setDistributedBy(UUID distributedBy) {
		this.distributedBy = distributedBy;
	}

	public String getDistributedName() {
		return distributedName;
	}

	public void setDistributedName(String distributedName) {
		this.distributedName = distributedName;
	}


	public UUID getAnalystBy() {
		return analystBy;
	}

	public void setAnalystBy(UUID analystBy) {
		this.analystBy = analystBy;
	}

	public String getDistributedStatus() {
		return distributedStatus;
	}

	public void setDistributedStatus(String distributedStatus) {
		this.distributedStatus = distributedStatus;
	}

	public String getResultByName() {
		return resultByName;
	}

	public void setResultByName(String resultByName) {
		this.resultByName = resultByName;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}
