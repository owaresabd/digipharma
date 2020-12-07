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
@Table(name = "lims_log_column_rcv_dist_infos")
public class LogColumnRcvDistInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "col_id")
	private UUID colId;

	@Column(name = "equipment_id")
	private UUID equipmentId;

	@Column(name = "room_no")
	private UUID rommNo;

	@Column(name = "rack_no")
	private UUID rackNo;

	@Column(name = "part_no")
	private String partNo;

	@Column(name = "serial_no")
	private String serialNo;

	@Column(name = "is_issue")
	private String isIssue;

	@Column(name = "issue_by")
	private UUID issueBy;

	@Type(type = "date")
	@Column(name = "issue_date")
	private Date issueDate;

	@Column(name = "col_rcv_by")
	private UUID colRcvBy;

	@Type(type = "date")
	@Column(name = "col_rcv_date")
	private Date colRcvDate;

	@Column(name = "is_receive")
	private String isReceive;

	@Column(name = "receive_by")
	private UUID receiveBy;

	@Type(type = "date")
	@Column(name = "receive_date")
	private Date receiveDate;

	@Column(name = "location_id")
	private UUID locationId;

	@Column(name = "open_date")
	private String openDate;

	@Column(name = "expire_date")
	private String expireDate;

	@Column(name = "is_verify")
	private String isVerify;

	@Column(name = "verify_by")
	private UUID verifyBy;

	@Column(name = "verified_at")
	private Timestamp verifiedAt;

	@Column(name = "update_by")
	private UUID updateBy;

	@Column(name = "update_at")
	private Timestamp updateAt;

	@Column(name = "company_id")
	private UUID companyId;

	@Column(name = "remarks")
	private String remarks;

	@Transient
	private String locationByName;

	@Transient
	private String locByName;

	@Transient
	private String colName;

	@Transient
	private String colIdByName;

	@Transient
	private String composition;

	@Transient
	private String colSize;

	@Transient
	private String colRcvByName;

	@Transient
	private String issueByName;

	@Transient
	private String receiveByName;

	@Transient
	private String checkByName;

	@Transient
	private String equipmentByName;

	@Transient
	private String roomByName;

	@Transient
	private String rackByName;

	@Transient
	private String verifyByName;
	
	@Transient
	private String theoriticalPlate;
	@Transient
	private UUID columnRecordBy;
	@Transient
	private String columnRecordStatus;
	@Transient
	private Timestamp columnRecordAt;
	@Transient
	private String columnRecordByName;
	
	@Transient
	private String columnIdNew;
	
	
	
	@Transient
	private String isRecordVerify;
	@Transient
	private String recordVerifyByName;
	

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getLocationByName() {
		return locationByName;
	}

	public void setLocationByName(String locationByName) {
		this.locationByName = locationByName;
	}

	public String getIsIssue() {
		return isIssue;
	}

	public void setIsIssue(String isIssue) {
		this.isIssue = isIssue;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getColId() {
		return colId;
	}

	public void setColId(UUID colId) {
		this.colId = colId;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public UUID getColRcvBy() {
		return colRcvBy;
	}

	public void setColRcvBy(UUID colRcvBy) {
		this.colRcvBy = colRcvBy;
	}

	public Date getColRcvDate() {
		return colRcvDate;
	}

	public void setColRcvDate(Date colRcvDate) {
		this.colRcvDate = colRcvDate;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getColRcvByName() {
		return colRcvByName;
	}

	public void setColRcvByName(String colRcvByName) {
		this.colRcvByName = colRcvByName;
	}

	public String getIssueByName() {
		return issueByName;
	}

	public void setIssueByName(String issueByName) {
		this.issueByName = issueByName;
	}

	public String getReceiveByName() {
		return receiveByName;
	}

	public void setReceiveByName(String receiveByName) {
		this.receiveByName = receiveByName;
	}

	public String getCheckByName() {
		return checkByName;
	}

	public void setCheckByName(String checkByName) {
		this.checkByName = checkByName;
	}

	public String getLocByName() {
		return locByName;
	}

	public void setLocByName(String locByName) {
		this.locByName = locByName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColIdByName() {
		return colIdByName;
	}

	public void setColIdByName(String colIdByName) {
		this.colIdByName = colIdByName;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getColSize() {
		return colSize;
	}

	public void setColSize(String colSize) {
		this.colSize = colSize;
	}

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public UUID getRommNo() {
		return rommNo;
	}

	public void setRommNo(UUID rommNo) {
		this.rommNo = rommNo;
	}

	public UUID getRackNo() {
		return rackNo;
	}

	public void setRackNo(UUID rackNo) {
		this.rackNo = rackNo;
	}

	public String getEquipmentByName() {
		return equipmentByName;
	}

	public void setEquipmentByName(String equipmentByName) {
		this.equipmentByName = equipmentByName;
	}

	public String getRoomByName() {
		return roomByName;
	}

	public void setRoomByName(String roomByName) {
		this.roomByName = roomByName;
	}

	public String getRackByName() {
		return rackByName;
	}

	public void setRackByName(String rackByName) {
		this.rackByName = rackByName;
	}

	public UUID getCompanyId() {
		return companyId;
	}

	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}

	public String getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public Timestamp getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Timestamp verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public UUID getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(UUID updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getVerifyByName() {
		return verifyByName;
	}

	public void setVerifyByName(String verifyByName) {
		this.verifyByName = verifyByName;
	}

	public String getTheoriticalPlate() {
		return theoriticalPlate;
	}

	public void setTheoriticalPlate(String theoriticalPlate) {
		this.theoriticalPlate = theoriticalPlate;
	}

	public UUID getColumnRecordBy() {
		return columnRecordBy;
	}

	public void setColumnRecordBy(UUID columnRecordBy) {
		this.columnRecordBy = columnRecordBy;
	}

	public String getColumnRecordStatus() {
		return columnRecordStatus;
	}

	public void setColumnRecordStatus(String columnRecordStatus) {
		this.columnRecordStatus = columnRecordStatus;
	}

	public Timestamp getColumnRecordAt() {
		return columnRecordAt;
	}

	public void setColumnRecordAt(Timestamp columnRecordAt) {
		this.columnRecordAt = columnRecordAt;
	}

	public String getColumnRecordByName() {
		return columnRecordByName;
	}

	public void setColumnRecordByName(String columnRecordByName) {
		this.columnRecordByName = columnRecordByName;
	}

	public String getIsRecordVerify() {
		return isRecordVerify;
	}

	public void setIsRecordVerify(String isRecordVerify) {
		this.isRecordVerify = isRecordVerify;
	}

	public String getRecordVerifyByName() {
		return recordVerifyByName;
	}

	public void setRecordVerifyByName(String recordVerifyByName) {
		this.recordVerifyByName = recordVerifyByName;
	}

	public String getColumnIdNew() {
		return columnIdNew;
	}

	public void setColumnIdNew(String columnIdNew) {
		this.columnIdNew = columnIdNew;
	}


}
