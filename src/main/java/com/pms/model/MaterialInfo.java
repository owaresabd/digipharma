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
@Table(name = "lims_material_infos")
public class MaterialInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "material_name")
	private String materialName;

	@Column(name = "material_code")
	private String materialCode;
	
	@Column(name = "material_type_id")
	private UUID materialTypeId;
	
	@Column(name = "is_chemical")
	private String isChemical;
	
	@Column(name = "is_microbiological")
	private String isMicrobiological;
	
	@Column(name = "chemical_sample_amt")
	private String chemicalSampleAmt;
	
	@Column(name = "chemical_retention_amt")
	private String chemicalRetentionAmt;

	@Column(name = "chemical_total")
	private String chemicalTotal;
	
	@Column(name = "micro_sample_amt")
	private String microSampleAmt;
	
	@Column(name = "micro_retention_amt")
	private String microRetentionAmt;
	
	
	@Column(name = "micro_total")
	private String microTotal;
	
	@Column(name = "total_amt")
	private String totalAmt;
	
	@Column(name = "unit_id")
	private UUID unitId;
	
	@Transient
	private String unitName;
	@Transient
	private String typeCode;
	
	@Transient
	private String materialTypeName;
	
	@Transient
	private String samplePlanName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "storage_condition")
	private String storageCondition;

	@Column(name = "mat_sample_procedure")
	private String matSamProcedure;
	
	@Column(name = "mat_method_id")
	private UUID matMethodId;
	
	@Column(name = "storage_con_id")
	private UUID storageConId;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public UUID getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(UUID materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getIsChemical() {
		return isChemical;
	}

	public void setIsChemical(String isChemical) {
		this.isChemical = isChemical;
	}

	public String getIsMicrobiological() {
		return isMicrobiological;
	}

	public void setIsMicrobiological(String isMicrobiological) {
		this.isMicrobiological = isMicrobiological;
	}

	public String getChemicalSampleAmt() {
		return chemicalSampleAmt;
	}

	public void setChemicalSampleAmt(String chemicalSampleAmt) {
		this.chemicalSampleAmt = chemicalSampleAmt;
	}

	public String getChemicalRetentionAmt() {
		return chemicalRetentionAmt;
	}

	public void setChemicalRetentionAmt(String chemicalRetentionAmt) {
		this.chemicalRetentionAmt = chemicalRetentionAmt;
	}

	public String getChemicalTotal() {
		return chemicalTotal;
	}

	public void setChemicalTotal(String chemicalTotal) {
		this.chemicalTotal = chemicalTotal;
	}

	public String getMicroSampleAmt() {
		return microSampleAmt;
	}

	public void setMicroSampleAmt(String microSampleAmt) {
		this.microSampleAmt = microSampleAmt;
	}

	public String getMicroRetentionAmt() {
		return microRetentionAmt;
	}

	public void setMicroRetentionAmt(String microRetentionAmt) {
		this.microRetentionAmt = microRetentionAmt;
	}

	public String getMicroTotal() {
		return microTotal;
	}

	public void setMicroTotal(String microTotal) {
		this.microTotal = microTotal;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}

	public String getMatSamProcedure() {
		return matSamProcedure;
	}

	public void setMatSamProcedure(String matSamProcedure) {
		this.matSamProcedure = matSamProcedure;
	}

	public UUID getMatMethodId() {
		return matMethodId;
	}

	public void setMatMethodId(UUID matMethodId) {
		this.matMethodId = matMethodId;
	}

	public UUID getStorageConId() {
		return storageConId;
	}

	public void setStorageConId(UUID storageConId) {
		this.storageConId = storageConId;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getSamplePlanName() {
		return samplePlanName;
	}

	public void setSamplePlanName(String samplePlanName) {
		this.samplePlanName = samplePlanName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	

}
