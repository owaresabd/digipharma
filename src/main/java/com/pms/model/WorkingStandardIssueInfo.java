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
@Table(name = "lims_work_standard_details_infos")
public class WorkingStandardIssueInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "work_standard_id")
	private UUID workStandardChildId;
	
	@Transient
	private String workStandardName;
	
	@Column(name = "vial_no")
	private String vialNo;
	
	@Type(type = "date")
	@Column(name = "opening_date")
	private Date openingDate;
	
	@Type(type = "date")
	@Column(name = "used_date")
	private Date usedDate;
	
	@Type(type = "date")
	@Column(name = "validity_date")
	private Date validityDate;
	
	@Column(name = "used_by")
	private UUID usedBy;
	
	@Column(name = "remarks")
	private String remarks;
	
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getWorkStandardChildId() {
		return workStandardChildId;
	}

	public void setWorkStandardChildId(UUID workStandardChildId) {
		this.workStandardChildId = workStandardChildId;
	}

	public String getWorkStandardName() {
		return workStandardName;
	}

	public void setWorkStandardName(String workStandardName) {
		this.workStandardName = workStandardName;
	}

	public String getVialNo() {
		return vialNo;
	}

	public void setVialNo(String vialNo) {
		this.vialNo = vialNo;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public UUID getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(UUID usedBy) {
		this.usedBy = usedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
