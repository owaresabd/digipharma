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
@Table(name = "lims_work_standard_req_infos")
public class WorkingStandardRequestInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	
	
	@Column(name = "material_id")
	private UUID materialId;
	
	@Transient
	private String materialName;
	
	@Transient
	private String unitName;
	
	@Transient
	private String materialCode;
	@Transient
	private UUID materialTypeId;
	@Transient
	private String materialTypeName;
	
	@Column(name = "kept_amount")
	private String keptAmount;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "status")
	private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeptAmount() {
		return keptAmount;
	}

	public void setKeptAmount(String keptAmount) {
		this.keptAmount = keptAmount;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
	

}
