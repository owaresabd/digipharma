package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lims_test_parameter_infos")
public class ParameterInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "test_parameter_no")
	private String testParameterNo;

	@Column(name = "test_parameter_name")
	private String testParameterName;
	
	@Column(name = "iso_scope ")
	private String isoScope ;
	
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

	

	public String getTestParameterNo() {
		return testParameterNo;
	}

	public void setTestParameterNo(String testParameterNo) {
		this.testParameterNo = testParameterNo;
	}

	public String getTestParameterName() {
		return testParameterName;
	}

	public void setTestParameterName(String testParameterName) {
		this.testParameterName = testParameterName;
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

	public String getIsoScope() {
		return isoScope;
	}

	public void setIsoScope(String isoScope) {
		this.isoScope = isoScope;
	}

}
