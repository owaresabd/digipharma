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
@Table(name = "lims_log_ref_culture_infos")
public class LogRefCultureInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "mfg_supplier_id")
	private UUID mfgSupplierId;
	
	@Transient
	private String mfgSupplierName;
	
	@Column(name = "ref_seed_name")
	private String refSeedName;
	
	@Column(name = "atcc_no")
	private String atccNo;
	
	@Column(name = "batch_lot_no")
	private String batchLotNo;
	
	@Column(name = "certi_avail")
	private String certiAvail;
	
	@Type(type = "date")
	@Column(name = "receive_date")
	private Date receiveDate;

	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;
	
	@Type(type = "date")
	@Column(name = "verify_date")
	private Date verifyDate;
	
	@Column(name = "receive_by")
	private UUID receiveBy;

	@Column(name = "check_by")
	private UUID checkBy;

	@Transient
	private String receiveByName;
	
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

	public UUID getMfgSupplierId() {
		return mfgSupplierId;
	}

	public void setMfgSupplierId(UUID mfgSupplierId) {
		this.mfgSupplierId = mfgSupplierId;
	}

	public String getMfgSupplierName() {
		return mfgSupplierName;
	}

	public void setMfgSupplierName(String mfgSupplierName) {
		this.mfgSupplierName = mfgSupplierName;
	}

	public String getRefSeedName() {
		return refSeedName;
	}

	public void setRefSeedName(String refSeedName) {
		this.refSeedName = refSeedName;
	}

	public String getAtccNo() {
		return atccNo;
	}

	public void setAtccNo(String atccNo) {
		this.atccNo = atccNo;
	}

	public String getBatchLotNo() {
		return batchLotNo;
	}

	public void setBatchLotNo(String batchLotNo) {
		this.batchLotNo = batchLotNo;
	}

	public String getCertiAvail() {
		return certiAvail;
	}

	public void setCertiAvail(String certiAvail) {
		this.certiAvail = certiAvail;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public UUID getReceiveBy() {
		return receiveBy;
	}

	public void setReceiveBy(UUID receiveBy) {
		this.receiveBy = receiveBy;
	}

	public UUID getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(UUID checkBy) {
		this.checkBy = checkBy;
	}

	public String getReceiveByName() {
		return receiveByName;
	}

	public void setReceiveByName(String receiveByName) {
		this.receiveByName = receiveByName;
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
