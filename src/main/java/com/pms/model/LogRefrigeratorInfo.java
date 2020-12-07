package com.pms.model;

import java.sql.Timestamp;
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
@Table(name = "lims_log_refrigerator_infos")
public class LogRefrigeratorInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "qc_reference_id")
	private UUID qcRefId;

	@Column(name = "sample_name_id")
	private UUID sampleNameId;

	@Column(name = "sample_type_id")
	private UUID sampleTypeId;

	@Transient
	private String qcRefName;

	@Transient
	private String sampleName;

	@Transient
	private String sampleTypeName;

	@Column(name = "qty")
	private String qty;

	@Type(type = "date")
	@Column(name = "in_date")
	private Date inDate;

	@Type(type = "date")
	@Column(name = "out_date")
	private Date outDate;

	@Type(type = "date")
	@Column(name = "discard_date")
	private Date discardDate;

	@Column(name = "discard_qty")
	private String discardQty;

	@Column(name = "discard_by")
	private UUID discardBy;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Column(name = "is_verify")
	private String isVerify;

	@Column(name = "verified_at")
	private Timestamp verifiedAt;

	@Transient
	private String discardByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "equipment_id")
	private UUID equipmentId;

	@Transient
	private String equipmentName;
	
	@Transient
	private UUID unitId;
	
	@Transient
	private String unitName;

	@Transient
	private UUID disUnitId;
	
	@Transient
	private String disUnitName;
	
	@Transient
	private UUID materialId;
	
	@Transient
	private String materialName;
	

	
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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getQcRefId() {
		return qcRefId;
	}

	public void setQcRefId(UUID qcRefId) {
		this.qcRefId = qcRefId;
	}

	public UUID getSampleNameId() {
		return sampleNameId;
	}

	public void setSampleNameId(UUID sampleNameId) {
		this.sampleNameId = sampleNameId;
	}

	public UUID getSampleTypeId() {
		return sampleTypeId;
	}

	public void setSampleTypeId(UUID sampleTypeId) {
		this.sampleTypeId = sampleTypeId;
	}

	public String getQcRefName() {
		return qcRefName;
	}

	public void setQcRefName(String qcRefName) {
		this.qcRefName = qcRefName;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getSampleTypeName() {
		return sampleTypeName;
	}

	public void setSampleTypeName(String sampleTypeName) {
		this.sampleTypeName = sampleTypeName;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Date getDiscardDate() {
		return discardDate;
	}

	public void setDiscardDate(Date discardDate) {
		this.discardDate = discardDate;
	}

	public String getDiscardQty() {
		return discardQty;
	}

	public void setDiscardQty(String discardQty) {
		this.discardQty = discardQty;
	}

	public UUID getDiscardBy() {
		return discardBy;
	}

	public void setDiscardBy(UUID discardBy) {
		this.discardBy = discardBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getDiscardByName() {
		return discardByName;
	}

	public void setDiscardByName(String discardByName) {
		this.discardByName = discardByName;
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

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
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

	public UUID getDisUnitId() {
		return disUnitId;
	}

	public void setDisUnitId(UUID disUnitId) {
		this.disUnitId = disUnitId;
	}

	public String getDisUnitName() {
		return disUnitName;
	}

	public void setDisUnitName(String disUnitName) {
		this.disUnitName = disUnitName;
	}

	public UUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(UUID materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


}
