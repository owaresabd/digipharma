package com.pms.model;



import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lims_test_method_ref_infos")
public class TestMethodRefInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "method_id")
	private UUID methodId;
	
	@Transient
	private String methodName;
	
	@Column(name = "method_ud_id")
	private String methodUdId;
	
	@Column(name = "revision_no")
	private String revisionNo;
	//@DateTimeFormat(pattern = "dd-MMM-yyyy")
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "method_doc_name")
	private String testDocName;

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

	public String getTestDocName() {
		return testDocName;
	}

	public void setTestDocName(String testDocName) {
		this.testDocName = testDocName;
	}

	public UUID getMethodId() {
		return methodId;
	}

	public void setMethodId(UUID methodId) {
		this.methodId = methodId;
	}

	public String getMethodUdId() {
		return methodUdId;
	}

	public void setMethodUdId(String methodUdId) {
		this.methodUdId = methodUdId;
	}

	public String getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}




}
