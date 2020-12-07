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
@Table(name = "lims_reagent_issue_infos")
public class ReagentIssueInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "reagent_id")
	private UUID reagentId;
	
	@Transient
	private String reagentName;
	
	@Column(name = "ud_issue_no")
	private String udIssueNo;
	
	@Type(type = "date")
	@Column(name = "issue_date")
	private Date issueDate;
	
	@Column(name = "stock_qty")
	private String stockQty;
	
	@Column(name = "issue_qty")
	private String issueQty;
	
	@Column(name = "issue_unit_id")
	private UUID issueUnitId;
	
	@Transient
	private String issueUnitName;
	
	@Column(name = "issue_by")
	private UUID issueBy;
	
	@Transient
	private String issueName;
		
	@Column(name = "verify_by")
	private UUID verifyBy;
	
	@Transient
	private String verifyName;
	
	@Column(name = "lab_type")
	private String labType;
	
	@Column(name = "remarks")
	private String remarks;
	
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getReagentId() {
		return reagentId;
	}

	public void setReagentId(UUID reagentId) {
		this.reagentId = reagentId;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
	}

	public String getUdIssueNo() {
		return udIssueNo;
	}

	public void setUdIssueNo(String udIssueNo) {
		this.udIssueNo = udIssueNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getStockQty() {
		return stockQty;
	}

	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}

	public String getIssueQty() {
		return issueQty;
	}

	public void setIssueQty(String issueQty) {
		this.issueQty = issueQty;
	}

	public UUID getIssueUnitId() {
		return issueUnitId;
	}

	public void setIssueUnitId(UUID issueUnitId) {
		this.issueUnitId = issueUnitId;
	}

	public String getIssueUnitName() {
		return issueUnitName;
	}

	public void setIssueUnitName(String issueUnitName) {
		this.issueUnitName = issueUnitName;
	}

	public UUID getIssueBy() {
		return issueBy;
	}

	public void setIssueBy(UUID issueBy) {
		this.issueBy = issueBy;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getLabType() {
		return labType;
	}

	public void setLabType(String labType) {
		this.labType = labType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
