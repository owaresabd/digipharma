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
@Table(name = "lims_work_standard_infos")
public class WorkingStandardInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "work_standard_name")
	private String workStandardName;
	
	@Column(name = "ref_stand_id")
	private UUID refStandId;
	
	@Column(name = "ws_request_id")
	private UUID wsRequestId;
	
	
	@Transient
	private String refStandName;

	@Column(name = "arn_no")
	private String arnNo;
	
	@Column(name = "no_of_vial")
	private String noOfVial;
	
	@Column(name = "amount_per_vial")
	private String vialAmount;

	@Type(type = "date")
	@Column(name = "date_of_prep")
	private Date dateOfPrep;

	@Type(type = "date")
	@Column(name = "valid_to_date")
	private Date validToDate;
	
	@Column(name = "assay_desc")
	private String assayDesc;
	
	@Column(name = "storage_condition")
	private UUID storageCondition;
	
	@Transient
	private String storageCondName;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getWorkStandardName() {
		return workStandardName;
	}

	public void setWorkStandardName(String workStandardName) {
		this.workStandardName = workStandardName;
	}

	public UUID getRefStandId() {
		return refStandId;
	}

	public void setRefStandId(UUID refStandId) {
		this.refStandId = refStandId;
	}

	public String getRefStandName() {
		return refStandName;
	}

	public void setRefStandName(String refStandName) {
		this.refStandName = refStandName;
	}

	public String getArnNo() {
		return arnNo;
	}

	public void setArnNo(String arnNo) {
		this.arnNo = arnNo;
	}

	public String getNoOfVial() {
		return noOfVial;
	}

	public void setNoOfVial(String noOfVial) {
		this.noOfVial = noOfVial;
	}

	public Date getDateOfPrep() {
		return dateOfPrep;
	}

	public void setDateOfPrep(Date dateOfPrep) {
		this.dateOfPrep = dateOfPrep;
	}

	public Date getValidToDate() {
		return validToDate;
	}

	public void setValidToDate(Date validToDate) {
		this.validToDate = validToDate;
	}

	public String getAssayDesc() {
		return assayDesc;
	}

	public void setAssayDesc(String assayDesc) {
		this.assayDesc = assayDesc;
	}

	public UUID getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(UUID storageCondition) {
		this.storageCondition = storageCondition;
	}

	public String getStorageCondName() {
		return storageCondName;
	}

	public void setStorageCondName(String storageCondName) {
		this.storageCondName = storageCondName;
	}

	public UUID getWsRequestId() {
		return wsRequestId;
	}

	public void setWsRequestId(UUID wsRequestId) {
		this.wsRequestId = wsRequestId;
	}

	public String getVialAmount() {
		return vialAmount;
	}

	public void setVialAmount(String vialAmount) {
		this.vialAmount = vialAmount;
	}

	

}
