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
@Table(name = "lims_log_out_of_trend_infos")
public class LogOutofTrend {

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
	
	
	@Transient
	private Timestamp verifiedAt;
	
	@Transient
	private String hiddenValCkbx;
		
	@Column(name = "product_id")
	private UUID productId;
	@Transient
	private String productName;
	
	@Transient
	private String batchNo;
	
	@Transient
	private String ootNo;
	
	@Transient
	private String test;
	
	@Type(type = "date")
	@Column(name = "oot_observation_date")
	private Date ootObservationDate;

	@Type(type = "date")
	@Column(name = "investigation_date")
	private Date investigationDate;
	
	@Type(type = "date")
	@Column(name = "class_out_date")
	private Date classOutDate;
		

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

	
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
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

	public String getOotNo() {
		return ootNo;
	}

	public void setOotNo(String ootNo) {
		this.ootNo = ootNo;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getOotObservationDate() {
		return ootObservationDate;
	}

	public void setOotObservationDate(Date ootObservationDate) {
		this.ootObservationDate = ootObservationDate;
	}

	public Date getInvestigationDate() {
		return investigationDate;
	}

	public void setInvestigationDate(Date investigationDate) {
		this.investigationDate = investigationDate;
	}

	public Date getClassOutDate() {
		return classOutDate;
	}

	public void setClassOutDate(Date classOutDate) {
		this.classOutDate = classOutDate;
	}
	
}
