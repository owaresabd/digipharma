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
@Table(name = "log_mc_media_reagent_materials_infos")
public class LogMCMediaReagentMaterialsInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;
		
	@Column(name = "dispensed_by")
	private UUID dispensedBy;

	@Transient
	private String dispensedByName;	
		
	@Column(name = "remarks")
	private String remarks;
		
	@Transient
	private String codeNo;	
	
	@Column(name = "materials_id")
	private UUID materialsId;
	
	@Transient
	private String materialsName;	
	
	@Transient
	private String specification;
	
	@Transient
	private String batchNo;	
	
	@Type(type = "date")
	@Column(name = "exp_date")
	private Date expDate;	
	
	@Type(type = "date")
	@Column(name = "mfg_date")
	private Date mfgDate;	
		
	@Column(name = "supplier_id")
	private UUID supplierId;
	@Transient
	private String supplierName;
	
	@Column(name = "country_origin_id")
	private UUID countryOriginId;
	@Transient
	private String countryOrigenName;
	@Transient
	private String quantity;
	@Column(name = "unit_id")
	private UUID unitId;
	@Transient
	private String unitName;	
	
	@Type(type = "date")
	@Column(name = "opening_date")
	private Date openingDate;	
	@Column(name = "opening_by")
	private UUID openingBy;
	@Transient
	private String openindByName;
	
	@Transient
	private String openingStatus;
		
	@Column(name = "checked_by")
	private UUID checkedBy;
	@Transient
	private String checkedByName;
	
	@Transient
	private String checkedStatus;
	
	@Transient
	private String issuedStatus;
	
	@Transient
	private String finishedStatus;
	
	
	
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

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public UUID getDispensedBy() {
		return dispensedBy;
	}

	public void setDispensedBy(UUID dispensedBy) {
		this.dispensedBy = dispensedBy;
	}

	public String getDispensedByName() {
		return dispensedByName;
	}

	public void setDispensedByName(String dispensedByName) {
		this.dispensedByName = dispensedByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	public UUID getMaterialsId() {
		return materialsId;
	}

	public void setMaterialsId(UUID materialsId) {
		this.materialsId = materialsId;
	}

	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public UUID getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(UUID supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public UUID getCountryOriginId() {
		return countryOriginId;
	}

	public void setCountryOriginId(UUID countryOriginId) {
		this.countryOriginId = countryOriginId;
	}

	public String getCountryOrigenName() {
		return countryOrigenName;
	}

	public void setCountryOrigenName(String countryOrigenName) {
		this.countryOrigenName = countryOrigenName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}


	public String getOpenindByName() {
		return openindByName;
	}

	public void setOpenindByName(String openindByName) {
		this.openindByName = openindByName;
	}

	public String getOpeningStatus() {
		return openingStatus;
	}

	public void setOpeningStatus(String openingStatus) {
		this.openingStatus = openingStatus;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public UUID getUnitId() {
		return unitId;
	}

	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

	public String getIssuedStatus() {
		return issuedStatus;
	}

	public void setIssuedStatus(String issuedStatus) {
		this.issuedStatus = issuedStatus;
	}

	public UUID getOpeningBy() {
		return openingBy;
	}

	public void setOpeningBy(UUID openingBy) {
		this.openingBy = openingBy;
	}

	public String getFinishedStatus() {
		return finishedStatus;
	}

	public void setFinishedStatus(String finishedStatus) {
		this.finishedStatus = finishedStatus;
	}

		
}
