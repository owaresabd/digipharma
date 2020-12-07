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
@Table(name = "log_mc_analyst_validation_infos")
public class LogMCAnalystValidationInfo {

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
	
	@Column(name = "verified_by")
	private UUID verifyBy;

	@Column(name = "dispensed_by")
	private UUID dispensedBy;

	@Transient
	private String dispensedByName;

	@Transient
	private String verifyName;

	@Column(name = "verified_status")
	private String isVerify;
	
	@Transient
	private Timestamp verifiedAt;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private String hiddenValCkbx;
	
	@Transient
	private UUID ProductId;
	
	@Transient
	private String productName;
		
		
	@Column(name = "analyst_id")
	private UUID analystId;
	@Transient
	private String analystName;
	
	@Type(type = "date")
	@Column(name = "date_of_competency")
	private Date dateOfCompetency;
	
	@Type(type = "date")
	@Column(name = "next_date_of_competency")
	private Date nextDateOfCompetency;
	
	@Transient
	private String areaOfCompetency;

	@Transient
	private String status;
	
	@Transient
	private String udAnalystId;
	

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
	
	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
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

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
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

	public Date getDateOfCompetency() {
		return dateOfCompetency;
	}

	public void setDateOfCompetency(Date dateOfCompetency) {
		this.dateOfCompetency = dateOfCompetency;
	}

	public Date getNextDateOfCompetency() {
		return nextDateOfCompetency;
	}

	public void setNextDateOfCompetency(Date nextDateOfCompetency) {
		this.nextDateOfCompetency = nextDateOfCompetency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAreaOfCompetency() {
		return areaOfCompetency;
	}

	public void setAreaOfCompetency(String areaOfCompetency) {
		this.areaOfCompetency = areaOfCompetency;
	}

	public String getUdAnalystId() {
		return udAnalystId;
	}

	public void setUdAnalystId(String udAnalystId) {
		this.udAnalystId = udAnalystId;
	}

	public UUID getProductId() {
		return ProductId;
	}

	public void setProductId(UUID productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
		
}
