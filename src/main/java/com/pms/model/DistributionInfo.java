package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class DistributionInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID sampleRcvId;
	
	@Column(name = "sample_id")
	private UUID sampleId;
	
	@Column(name = "test_parameter_no")
	private String distParameterNo;
	
	@Column(name = "test_parameter_id")
	private UUID distParameterId;
	
	@Column(name = "specification")
	private String distSpecification;
	
	
	@Column(name = "reference_id")
	private UUID distReferenceId;
	
	@Column(name = "test_method_id")
	private UUID distMethodId;
	
	@Column(name = "test_unit_id")
	private UUID distTestUnitId;
	
	@Transient
	private String distMethodName;
	
	@Column(name = "ecuipment_id")
	private String distEcuipmentId;
	
	@Column(name = "lod")
	private String distLod;
	
	@Column(name = "analyst_id")
	private UUID distAnalystId;
	
	@Column(name = "status")
	private String status;

	public UUID getSampleRcvId() {
		return sampleRcvId;
	}

	public void setSampleRcvId(UUID sampleRcvId) {
		this.sampleRcvId = sampleRcvId;
	}
	
	public UUID getSampleId() {
		return sampleId;
	}

	public void setSampleId(UUID sampleId) {
		this.sampleId = sampleId;
	}

	public String getDistParameterNo() {
		return distParameterNo;
	}

	public void setDistParameterNo(String distParameterNo) {
		this.distParameterNo = distParameterNo;
	}

	public UUID getDistParameterId() {
		return distParameterId;
	}

	public void setDistParameterId(UUID distParameterId) {
		this.distParameterId = distParameterId;
	}


	public UUID getDistReferenceId() {
		return distReferenceId;
	}

	public void setDistReferenceId(UUID distReferenceId) {
		this.distReferenceId = distReferenceId;
	}

	public UUID getDistMethodId() {
		return distMethodId;
	}

	public void setDistMethodId(UUID distMethodId) {
		this.distMethodId = distMethodId;
	}

	public String getDistMethodName() {
		return distMethodName;
	}

	public void setDistMethodName(String distMethodName) {
		this.distMethodName = distMethodName;
	}

	


	public UUID getDistAnalystId() {
		return distAnalystId;
	}

	public void setDistAnalystId(UUID distAnalystId) {
		this.distAnalystId = distAnalystId;
	}

	public String getDistSpecification() {
		return distSpecification;
	}

	public void setDistSpecification(String distSpecification) {
		this.distSpecification = distSpecification;
	}

	public String getDistEcuipmentId() {
		return distEcuipmentId;
	}

	public void setDistEcuipmentId(String distEcuipmentId) {
		this.distEcuipmentId = distEcuipmentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getDistTestUnitId() {
		return distTestUnitId;
	}

	public void setDistTestUnitId(UUID distTestUnitId) {
		this.distTestUnitId = distTestUnitId;
	}

	public String getDistLod() {
		return distLod;
	}

	public void setDistLod(String distLod) {
		this.distLod = distLod;
	}

	
	
	

	
	

}
