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
@Table(name = "lims_log_sterile_prepared_infos")
public class LogSterilePreparedInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "medium_name")
	private String mediumName;
	
	@Column(name = "storage_type")
	private String storageType;
	
	@Column(name = "refrigerator_id")
	private String refrigeratorId;
	
	@Column(name = "storage_condition")
	private String storageCondition;
	
	@Type(type = "date")
	@Column(name = "storage_date")
	private Date storageDate;	
	
	@Column(name = "storage_qty")
	private String storageQty;
	
	@Column(name = "lab_batch_no")
	private String labBatcNo;
	
	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;
	
	@Column(name = "pack_size")
	private String packSize;

	@Column(name = "storage_by")
	private UUID storageBy;
	
	@Type(type = "date")
	@Column(name = "issue_date")
	private Date issueDate;
	
	@Column(name = "issued_qty")
	private String issuedQty;
	
	@Column(name = "purpose")
	private String purpose;
	
	@Column(name = "issued_by")
	private UUID issuedBy;
	
	@Column(name = "stock_qty")
	private String stockQty;
	
	@Column(name = "checked_by")
	private UUID checkedBy;
	
	@Column(name = "remarks")
	private String remarks;
	@Transient
	private String storageByName;
	
	@Transient
	private String issuedByName;
	
	@Transient
	private String checkedByName;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	

	public String getRefrigeratorId() {
		return refrigeratorId;
	}

	public void setRefrigeratorId(String refrigeratorId) {
		this.refrigeratorId = refrigeratorId;
	}

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}

	public Date getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}

	public String getStorageQty() {
		return storageQty;
	}

	public void setStorageQty(String storageQty) {
		this.storageQty = storageQty;
	}

 

	public String getLabBatcNo() {
		return labBatcNo;
	}

	public void setLabBatcNo(String labBatcNo) {
		this.labBatcNo = labBatcNo;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public UUID getStorageBy() {
		return storageBy;
	}

	public void setStorageBy(UUID storageBy) {
		this.storageBy = storageBy;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuedQty() {
		return issuedQty;
	}

	public void setIssuedQty(String issuedQty) {
		this.issuedQty = issuedQty;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public UUID getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(UUID issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getStockQty() {
		return stockQty;
	}

	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
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

	public String getStorageByName() {
		return storageByName;
	}

	public void setStorageByName(String storageByName) {
		this.storageByName = storageByName;
	}

	public String getIssuedByName() {
		return issuedByName;
	}

	public void setIssuedByName(String issuedByName) {
		this.issuedByName = issuedByName;
	}

	public String getCheckedByName() {
		return checkedByName;
	}

	public void setCheckedByName(String checkedByName) {
		this.checkedByName = checkedByName;
	}

	
	
	
	
	
}
