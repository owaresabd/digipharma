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
@Table(name = "lims_material_microbiological_infos")
public class MaterialMicrobiologicalInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID microChildId;
	
	@Column(name = "material_id")
	private UUID microMaterialId;
	
	@Column(name = "parameter_no")
	private String microParameterNo;
	
	@Column(name = "parameter_id")
	private UUID microParameterId;
	
	@Transient
	private String microParameterName;
	
	@Column(name = "specification")
	private String microSpecification;
	
	@Column(name = "reference_id")
	private UUID microReferenceId;
	
	@Transient
	private String microReferenceName;
	
	@Column(name = "method_id")
	private UUID microMethodId;
	
	@Transient
	private String microMethodName;
	
	@Column(name = "test_unit_id")
	private UUID microTestUnitId;
	
	@Transient
	private String testUnitName;
	
	@Column(name = "sample_amount")
	private String microSampleAmount;
	
	@Column(name = "unit_id")
	private UUID microUnitId;
	
	@Transient
	private String unitName;
	
	@Column(name = "lod")
	private String microLod;
	
	public UUID getMicroChildId() {
		return microChildId;
	}

	public void setMicroChildId(UUID microChildId) {
		this.microChildId = microChildId;
	}

	public UUID getMicroMaterialId() {
		return microMaterialId;
	}

	public void setMicroMaterialId(UUID microMaterialId) {
		this.microMaterialId = microMaterialId;
	}

	public String getMicroParameterNo() {
		return microParameterNo;
	}

	public void setMicroParameterNo(String microParameterNo) {
		this.microParameterNo = microParameterNo;
	}

	public UUID getMicroParameterId() {
		return microParameterId;
	}

	public void setMicroParameterId(UUID microParameterId) {
		this.microParameterId = microParameterId;
	}

	public String getMicroSpecification() {
		return microSpecification;
	}

	public void setMicroSpecification(String microSpecification) {
		this.microSpecification = microSpecification;
	}

	public UUID getMicroReferenceId() {
		return microReferenceId;
	}

	public void setMicroReferenceId(UUID microReferenceId) {
		this.microReferenceId = microReferenceId;
	}

	

	public UUID getMicroMethodId() {
		return microMethodId;
	}

	public void setMicroMethodId(UUID microMethodId) {
		this.microMethodId = microMethodId;
	}

	

	public UUID getMicroTestUnitId() {
		return microTestUnitId;
	}

	public void setMicroTestUnitId(UUID microTestUnitId) {
		this.microTestUnitId = microTestUnitId;
	}

	public UUID getMicroUnitId() {
		return microUnitId;
	}

	public void setMicroUnitId(UUID microUnitId) {
		this.microUnitId = microUnitId;
	}

	public String getMicroSampleAmount() {
		return microSampleAmount;
	}

	public void setMicroSampleAmount(String microSampleAmount) {
		this.microSampleAmount = microSampleAmount;
	}

	public String getMicroParameterName() {
		return microParameterName;
	}

	public void setMicroParameterName(String microParameterName) {
		this.microParameterName = microParameterName;
	}

	public String getMicroReferenceName() {
		return microReferenceName;
	}

	public void setMicroReferenceName(String microReferenceName) {
		this.microReferenceName = microReferenceName;
	}

	public String getMicroMethodName() {
		return microMethodName;
	}

	public void setMicroMethodName(String microMethodName) {
		this.microMethodName = microMethodName;
	}

	public String getTestUnitName() {
		return testUnitName;
	}

	public void setTestUnitName(String testUnitName) {
		this.testUnitName = testUnitName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMicroLod() {
		return microLod;
	}

	public void setMicroLod(String microLod) {
		this.microLod = microLod;
	}

	
	
	

}
