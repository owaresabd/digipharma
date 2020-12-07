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
@Table(name = "lims_material_chemical_infos")
public class MaterialChemicalInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID chemiChildId;
	
	@Column(name = "material_id")
	private UUID chemiMaterialId;
	
	@Column(name = "parameter_no")
	private String chemiParameterNo;
	
	@Column(name = "parameter_id")
	private UUID chemiParameterId;
	
	@Transient
	private String chemiParameterName;
	
	@Column(name = "specification")
	private String chemiSpecification;
	
	@Column(name = "reference_id")
	private UUID chemiReferenceId;
	
	@Transient
	private String chemiReferenceName;
	
	@Column(name = "method_id")
	private UUID chemiMethodId;
	
	@Transient
	private String chemiMethodName;
	
	@Column(name = "test_unit_id")
	private UUID chemiTestUnitId;
	
	@Transient
	private String testUnitName;
	
	@Column(name = "sample_amount")
	private String chemiSampleAmount;
	
	@Column(name = "unit_id")
	private UUID chemiUnitId;
	
	@Transient
	private String unitName;
	
	@Column(name = "lod")
	private String chemiLod;
	
		public UUID getChemiChildId() {
		return chemiChildId;
	}

	public void setChemiChildId(UUID chemiChildId) {
		this.chemiChildId = chemiChildId;
	}

	public UUID getChemiMaterialId() {
		return chemiMaterialId;
	}

	public void setChemiMaterialId(UUID chemiMaterialId) {
		this.chemiMaterialId = chemiMaterialId;
	}

	public String getChemiParameterNo() {
		return chemiParameterNo;
	}

	public void setChemiParameterNo(String chemiParameterNo) {
		this.chemiParameterNo = chemiParameterNo;
	}

	public UUID getChemiParameterId() {
		return chemiParameterId;
	}

	public void setChemiParameterId(UUID chemiParameterId) {
		this.chemiParameterId = chemiParameterId;
	}

	public String getChemiSpecification() {
		return chemiSpecification;
	}

	public void setChemiSpecification(String chemiSpecification) {
		this.chemiSpecification = chemiSpecification;
	}

	public UUID getChemiReferenceId() {
		return chemiReferenceId;
	}

	public void setChemiReferenceId(UUID chemiReferenceId) {
		this.chemiReferenceId = chemiReferenceId;
	}

	

	public UUID getChemiMethodId() {
		return chemiMethodId;
	}

	public void setChemiMethodId(UUID chemiMethodId) {
		this.chemiMethodId = chemiMethodId;
	}

	

	public String getChemiSampleAmount() {
		return chemiSampleAmount;
	}

	public void setChemiSampleAmount(String chemiSampleAmount) {
		this.chemiSampleAmount = chemiSampleAmount;
	}

	public String getChemiParameterName() {
		return chemiParameterName;
	}

	public void setChemiParameterName(String chemiParameterName) {
		this.chemiParameterName = chemiParameterName;
	}

	public String getChemiReferenceName() {
		return chemiReferenceName;
	}

	public void setChemiReferenceName(String chemiReferenceName) {
		this.chemiReferenceName = chemiReferenceName;
	}

	public String getChemiMethodName() {
		return chemiMethodName;
	}

	public void setChemiMethodName(String chemiMethodName) {
		this.chemiMethodName = chemiMethodName;
	}

	public UUID getChemiTestUnitId() {
		return chemiTestUnitId;
	}

	public void setChemiTestUnitId(UUID chemiTestUnitId) {
		this.chemiTestUnitId = chemiTestUnitId;
	}

	public UUID getChemiUnitId() {
		return chemiUnitId;
	}

	public void setChemiUnitId(UUID chemiUnitId) {
		this.chemiUnitId = chemiUnitId;
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

	public String getChemiLod() {
		return chemiLod;
	}

	public void setChemiLod(String chemiLod) {
		this.chemiLod = chemiLod;
	}

	

}
