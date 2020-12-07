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
@Table(name = "lims_log_multi_parameter_infos")
public class LogMultiParameterInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "item_id")
	private UUID itemId;
	
	@Transient
	private String itemName;
	
	@Type(type = "date")
	@Column(name = "operate_date")
	private Date operateDate;
	
	@Column(name = "batch_no")
	private String batchNo;
	
	@Column(name = "ph")
	private String ph;

	@Column(name = "conductivity")
	private String conductivity;

	@Column(name = "tds")
	private String tds;
	
	@Column(name = "do_val")
	private String doVal;
	
	@Column(name = "operate_by")
	private UUID operateBy;
	
	@Column(name = "clean_by")
	private UUID cleanBy;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String operateByName;

	@Transient
	private String cleanByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private UUID equipmentId;
	
	@Transient
	private String lotNo;
	
	@Transient
	private String EquipmentName;
	
	@Transient
	private String verifyStatus;
	
	@Transient
	private String doneBy;
	
	@Transient
	private String cleanStatus;
	
	@Transient
	private String recordTime;
	
	@Transient
	private UUID qcRefId;

	@Transient
	private UUID sampleTypeId;
	
	@Transient
	private String qcRefName;
	
	@Transient
	private String sampleTypeName;
	
	@Transient
	private String result;
	
	@Transient
	private UUID materialId;
	
	@Transient
	private String materialName;
	
	
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getItemId() {
		return itemId;
	}

	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getConductivity() {
		return conductivity;
	}

	public void setConductivity(String conductivity) {
		this.conductivity = conductivity;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getDoVal() {
		return doVal;
	}

	public void setDoVal(String doVal) {
		this.doVal = doVal;
	}

	public UUID getOperateBy() {
		return operateBy;
	}

	public void setOperateBy(UUID operateBy) {
		this.operateBy = operateBy;
	}

	public UUID getCleanBy() {
		return cleanBy;
	}

	public void setCleanBy(UUID cleanBy) {
		this.cleanBy = cleanBy;
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

	public String getCleanByName() {
		return cleanByName;
	}

	public void setCleanByName(String cleanByName) {
		this.cleanByName = cleanByName;
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

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getEquipmentName() {
		return EquipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		EquipmentName = equipmentName;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}

	public String getCleanStatus() {
		return cleanStatus;
	}

	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public UUID getQcRefId() {
		return qcRefId;
	}

	public void setQcRefId(UUID qcRefId) {
		this.qcRefId = qcRefId;
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

	public String getSampleTypeName() {
		return sampleTypeName;
	}

	public void setSampleTypeName(String sampleTypeName) {
		this.sampleTypeName = sampleTypeName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
