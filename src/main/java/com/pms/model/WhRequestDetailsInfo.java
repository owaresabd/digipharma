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
@Table(name = "lims_wh_request_details_infos")
public class WhRequestDetailsInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID childId;

	@Column(name = "wh_request_id")
	private UUID whRequestId;
	
	@Column(name = "gr_no")
	private String grNo;
	
	@Type(type = "date")
	@Column(name = "mfg_date")
	private Date mfgDate;
	
	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;
	
	@Column(name = "batch_lot")
	private String batchLot;
	
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "unit_id")
	private UUID unitId;
	
	@Column(name = "pack_size")
	private String packSize;
	
	@Transient
	private String unitName;
	
	@Column(name = "number_of_drums")
	private String noOfDrums;
	
	
	
	public UUID getChildId() {
		return childId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}

	public UUID getWhRequestId() {
		return whRequestId;
	}

	public void setWhRequestId(UUID whRequestId) {
		this.whRequestId = whRequestId;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getBatchLot() {
		return batchLot;
	}

	public void setBatchLot(String batchLot) {
		this.batchLot = batchLot;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNoOfDrums() {
		return noOfDrums;
	}

	public void setNoOfDrums(String noOfDrums) {
		this.noOfDrums = noOfDrums;
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

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	
	
	
	

}
