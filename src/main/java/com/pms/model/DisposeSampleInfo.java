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
@Table(name = "lims_dispose_sample_infos")
public class DisposeSampleInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "dispose_id")
	private UUID disposeId;
	
	@Column(name = "sample_id")
	private UUID sampleId ;
	
	@Transient
	private String udSampleNo;
	
	@Column(name = "material_id")
	private UUID materialId;
	
	@Transient
	private String materialName;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "unit_id")
	private UUID sampleUnitId;
	
	@Transient
	private String unitName;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getDisposeId() {
		return disposeId;
	}

	public void setDisposeId(UUID disposeId) {
		this.disposeId = disposeId;
	}

	public UUID getSampleId() {
		return sampleId;
	}

	public void setSampleId(UUID sampleId) {
		this.sampleId = sampleId;
	}

	public UUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(UUID materialId) {
		this.materialId = materialId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public UUID getSampleUnitId() {
		return sampleUnitId;
	}

	public void setSampleUnitId(UUID sampleUnitId) {
		this.sampleUnitId = sampleUnitId;
	}

	
	public String getUdSampleNo() {
		return udSampleNo;
	}

	public void setUdSampleNo(String udSampleNo) {
		this.udSampleNo = udSampleNo;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	
}
