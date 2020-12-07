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
@Table(name = "lims_ref_standard_infos")
public class RefStandardInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "ref_standard_id")
	private UUID refStandardId;
	
	@Transient
	private String refStandardName;
	
	@Column(name = "id_type")
	private String idType;
	
	@Column(name = "source_supplier_id")
	private UUID sourceSupplierId;
	
	@Transient
	private String sourceSupplierName;
	
	@Column(name = "cat_no")
	private String catNo;
	
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "lot_no")
	private String lotNo;

	@Column(name = "potency")
	private String potency;
	
	@Column(name = "rcv_qty")
	private String rcvQty;
	
	@Type(type = "date")
	@Column(name = "rcv_date")
	private Date rcvDate;
	
	@Type(type = "date")
	@Column(name = "valid_to_date")
	private Date validDate;
	
	@Column(name = "storage_condition")
	private UUID storageCondition;
	
	@Transient
	private String storageCondName;
	
	@Column(name = "unit_id")
	private UUID unitId;
	
	@Transient
	private String unitName;
	
	@Column(name = "status")
	private String status;
	
	@Transient
	private int diffDays;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRefStandardId() {
		return refStandardId;
	}

	public void setRefStandardId(UUID refStandardId) {
		this.refStandardId = refStandardId;
	}

	public String getRefStandardName() {
		return refStandardName;
	}

	public void setRefStandardName(String refStandardName) {
		this.refStandardName = refStandardName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public UUID getSourceSupplierId() {
		return sourceSupplierId;
	}

	public void setSourceSupplierId(UUID sourceSupplierId) {
		this.sourceSupplierId = sourceSupplierId;
	}

	public String getSourceSupplierName() {
		return sourceSupplierName;
	}

	public void setSourceSupplierName(String sourceSupplierName) {
		this.sourceSupplierName = sourceSupplierName;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getPotency() {
		return potency;
	}

	public void setPotency(String potency) {
		this.potency = potency;
	}

	public String getRcvQty() {
		return rcvQty;
	}

	public void setRcvQty(String rcvQty) {
		this.rcvQty = rcvQty;
	}

	public Date getRcvDate() {
		return rcvDate;
	}

	public void setRcvDate(Date rcvDate) {
		this.rcvDate = rcvDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public UUID getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(UUID storageCondition) {
		this.storageCondition = storageCondition;
	}

	public String getStorageCondName() {
		return storageCondName;
	}

	public void setStorageCondName(String storageCondName) {
		this.storageCondName = storageCondName;
	}

	public UUID getUnitId() {
		return unitId;
	}

	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(int diffDays) {
		this.diffDays = diffDays;
	}

	
		

}
