package com.pms.model;

import java.sql.Timestamp;
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
@Table(name = "lims_log_allotted_sample_infos")
public class LogAllottedSampleInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Type(type = "date")
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_time")
	private String recordTime;
		
	@Column(name = "dispensed_by")
	private UUID dispensedBy;

	@Transient
	private String dispensedByName;

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private Timestamp verifiedAt;
	
	@Transient
	private String hiddenValCkbx;
	
	@Column(name = "analyst_id")
	private UUID analystId;
	
	@Transient
	private String analystName;
	
	@Transient
	private String documentCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Transient
	private String batchNo;
	
	@Column(name = "test_method_name")
	private String testMethodName;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public UUID getDispensedBy() {
		return dispensedBy;
	}

	public void setDispensedBy(UUID dispensedBy) {
		this.dispensedBy = dispensedBy;
	}

	public String getDispensedByName() {
		return dispensedByName;
	}

	public void setDispensedByName(String dispensedByName) {
		this.dispensedByName = dispensedByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public String getHiddenValCkbx() {
		return hiddenValCkbx;
	}

	public void setHiddenValCkbx(String hiddenValCkbx) {
		this.hiddenValCkbx = hiddenValCkbx;
	}

	public UUID getAnalystId() {
		return analystId;
	}

	public void setAnalystId(UUID analystId) {
		this.analystId = analystId;
	}

	public String getAnalystName() {
		return analystName;
	}

	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}

	public String getDocumentCode() {
		return documentCode;
	}

	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}
	
	
}