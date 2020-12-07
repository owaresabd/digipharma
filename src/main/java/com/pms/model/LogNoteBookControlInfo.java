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
@Table(name = "lims_log_notebook_infos")
public class LogNoteBookControlInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "employee_id")
	private UUID employeeId;
	
	@Transient
	private String employeeName;
	
	@Column(name = "designation_id")
	private UUID designationId;
	
	@Transient
	private String designationName;
	
	
	@Column(name = "note_book_no")
	private String noteBookNo;
	
	
	@Column(name = "issue_by")
	private UUID issueBy;
	
		 
	@Type(type = "date")
	@Column(name = "issue_date")
	private Date issueDate;
	
	
	@Column(name = "receive_by")
	private UUID receiveBy;
	
	
	@Type(type = "date")
	@Column(name = "receive_date")
	private Date receiveDate;
	
	
	
	@Column(name = "return_by")
	private UUID returnBy;
	
	@Type(type = "date")
	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private String receiveStatus;
	@Transient
	private String returnStatus;
	
	@Transient
	private String issueByNm;
	@Transient
	private String receiveByNm;
	@Transient
	private String returnByNm;
	
	@Transient
	private String recordTime;
	
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}

	public UUID getDesignationId() {
		return designationId;
	}

	public void setDesignationId(UUID designationId) {
		this.designationId = designationId;
	}

	public String getNoteBookNo() {
		return noteBookNo;
	}

	public void setNoteBookNo(String noteBookNo) {
		this.noteBookNo = noteBookNo;
	}

	public UUID getIssueBy() {
		return issueBy;
	}

	public void setIssueBy(UUID issueBy) {
		this.issueBy = issueBy;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public UUID getReceiveBy() {
		return receiveBy;
	}

	public void setReceiveBy(UUID receiveBy) {
		this.receiveBy = receiveBy;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public UUID getReturnBy() {
		return returnBy;
	}

	public void setReturnBy(UUID returnBy) {
		this.returnBy = returnBy;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getIssueByNm() {
		return issueByNm;
	}

	public void setIssueByNm(String issueByNm) {
		this.issueByNm = issueByNm;
	}

	public String getReceiveByNm() {
		return receiveByNm;
	}

	public void setReceiveByNm(String receiveByNm) {
		this.receiveByNm = receiveByNm;
	}

	public String getReturnByNm() {
		return returnByNm;
	}

	public void setReturnByNm(String returnByNm) {
		this.returnByNm = returnByNm;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
}
