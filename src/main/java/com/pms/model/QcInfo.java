package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_qc_req_infos")
public class QcInfo extends CommonInfo{

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "sample_id")
	private UUID sampleId;
	
	@Column(name = "ud_qc_no")
	private String udQcNo;
	
	@Column(name = "condition_type")
	private String conditionType;
	
	@Column(name = "abnormal_type")
	private String abnormalType;
	
	@Column(name = "abnormal_desc")
	private String abnormalDesc;
	
	@Column(name = "customer_desc")
	private String customerDesc;
	
	@Column(name = "sample_desc")
	private String sampleDesc;
	
	@Column(name = "uncertinity")
	private String uncertinity;
	
	@Column(name = "is_decision")
	private String isDecision;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getSampleId() {
		return sampleId;
	}

	public void setSampleId(UUID sampleId) {
		this.sampleId = sampleId;
	}

	

	public String getUdQcNo() {
		return udQcNo;
	}

	public void setUdQcNo(String udQcNo) {
		this.udQcNo = udQcNo;
	}

	public String getSampleDesc() {
		return sampleDesc;
	}

	public void setSampleDesc(String sampleDesc) {
		this.sampleDesc = sampleDesc;
	}

	public String getUncertinity() {
		return uncertinity;
	}

	public void setUncertinity(String uncertinity) {
		this.uncertinity = uncertinity;
	}

	public String getIsDecision() {
		return isDecision;
	}

	public void setIsDecision(String isDecision) {
		this.isDecision = isDecision;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getAbnormalType() {
		return abnormalType;
	}

	public void setAbnormalType(String abnormalType) {
		this.abnormalType = abnormalType;
	}

	public String getAbnormalDesc() {
		return abnormalDesc;
	}

	public void setAbnormalDesc(String abnormalDesc) {
		this.abnormalDesc = abnormalDesc;
	}

	public String getCustomerDesc() {
		return customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}
	
	
	
	

	

	
	
}
