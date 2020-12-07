package com.pms.model;

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
@Table(name = "lims_log_ehd_infos")
public class LogEhdInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "ehd_id_no")
	private String ehdNo;
	
	@Type(type = "date")
	@Column(name = "issue_date")
	private Date issueDate;

	@Column(name = "issue_time")
	private String issueTime;

	@Column(name = "return_time")
	private String returnTime;
	
	@Column(name = "return_by")
	private UUID returnBy;
	
	@Column(name = "receive_by")
	private UUID receiveBy;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Transient
	private String returnByName;

	@Transient
	private String receiveByName;

	@Transient
	private String verifyByName;

	@Column(name = "remarks")
	private String remarks;
	
	
	@Transient
	private String verifyStatus;
	
	@Transient
	private String receiveStatus;	
	
	@Transient
	private String returnStatus;

	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEhdNo() {
		return ehdNo;
	}

	public void setEhdNo(String ehdNo) {
		this.ehdNo = ehdNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public UUID getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(UUID returnBy) {
		this.returnBy = returnBy;
	}

	public UUID getReceiveBy() {
		return receiveBy;
	}

	public void setReceiveBy(UUID receiveBy) {
		this.receiveBy = receiveBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getReturnByName() {
		return returnByName;
	}

	public void setReturnByName(String returnByName) {
		this.returnByName = returnByName;
	}

	public String getReceiveByName() {
		return receiveByName;
	}

	public void setReceiveByName(String receiveByName) {
		this.receiveByName = receiveByName;
	}

	public String getVerifyByName() {
		return verifyByName;
	}

	public void setVerifyByName(String verifyByName) {
		this.verifyByName = verifyByName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

		
}
