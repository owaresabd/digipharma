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
@Table(name = "lims_reagent_receive_infos")
public class ReagentReceiveInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "reagent_id")
	private UUID reagentId;
	
	@Transient
	private String reagentName;
	
	@Column(name = "ud_receive_id")
	private String udReceiveId;
	
	@Column(name = "receive_by")
	private UUID receiveBy;
	
	@Transient
	private String receiveName;
	
	@Type(type = "date")
	@Column(name = "receive_date")
	private Date receiveDate;
	
	@Column(name = "receive_qty")
	private String receiveQty;
	
	@Column(name = "receive_unit_id")
	private UUID receiveUnitId;
	
	@Transient
	private String receiveUnitName;
	
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "lot_no")
	private String lotNo;

	@Column(name = "pack_size")
	private String packSize;
	
	@Type(type = "date")
	@Column(name = "mfg_date")
	private Date mfgDate;

	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;
	
	@Column(name = "purity")
	private String purity;
	
	@Column(name = "manufacturer_id")
	private UUID manufacturerId;
	
	@Transient
	private String manufacturerName;
	
	@Column(name = "country_id")
	private UUID countryId;
	
	@Transient
	private String countryName;

	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getReagentId() {
		return reagentId;
	}

	public void setReagentId(UUID reagentId) {
		this.reagentId = reagentId;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}

	public String getUdReceiveId() {
		return udReceiveId;
	}

	public void setUdReceiveId(String udReceiveId) {
		this.udReceiveId = udReceiveId;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveQty() {
		return receiveQty;
	}

	public void setReceiveQty(String receiveQty) {
		this.receiveQty = receiveQty;
	}

	public UUID getReceiveUnitId() {
		return receiveUnitId;
	}

	public void setReceiveUnitId(UUID receiveUnitId) {
		this.receiveUnitId = receiveUnitId;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public UUID getReceiveBy() {
		return receiveBy;
	}

	public void setReceiveBy(UUID receiveBy) {
		this.receiveBy = receiveBy;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
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

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
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

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
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

	public UUID getCountryId() {
		return countryId;
	}

	public void setCountryId(UUID countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	

}
