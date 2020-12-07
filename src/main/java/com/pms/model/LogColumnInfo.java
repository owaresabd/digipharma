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
@Table(name = "lims_log_column_infos")
public class LogColumnInfo {
	
	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "col_id")
	private String colId;
	
	@Column(name = "col_name")
	private String colName;
	
	@Column(name = "col_size")
	private String colSize;
	
	@Column(name = "composition")
	private String composition;
	
	@Column(name = "part_no")
	private String partNo;
	
	@Column(name = "serial_no")
	private String serialNo;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "theore_plate")
	private String theorePlate;
	
	@Type(type = "date")
	@Column(name = "open_date")
	private Date openDate;
	
	@Type(type = "date")
	@Column(name = "expire_date")
	private Date expireDate;

	@Column(name = "done_by")
	private UUID doneBy;
	
	@Column(name = "verify_by")
	private UUID verifyBy;

	@Column(name = "remarks")
	private String remarks;
	
	@Transient
	private String doneByName;

	@Transient
	private String verifyByName;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColSize() {
		return colSize;
	}

	public void setColSize(String colSize) {
		this.colSize = colSize;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTheorePlate() {
		return theorePlate;
	}

	public void setTheorePlate(String theorePlate) {
		this.theorePlate = theorePlate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public UUID getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(UUID doneBy) {
		this.doneBy = doneBy;
	}

	public UUID getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(UUID verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDoneByName() {
		return doneByName;
	}

	public void setDoneByName(String doneByName) {
		this.doneByName = doneByName;
	}

	public String getVerifyByName() {
		return verifyByName;
	}

	public void setVerifyByName(String verifyByName) {
		this.verifyByName = verifyByName;
	}

}
