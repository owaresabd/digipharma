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
@Table(name = "lims_ref_standard_details_infos")
public class RefStandardIssueInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "ref_standard_id")
	private UUID refStandardChildId;
	
	@Transient
	private String refStandardName;
	
	@Type(type = "date")
	@Column(name = "used_date")
	private Date usedDate;
	
	@Column(name = "used_qty")
	private String usedQty;
	
	@Column(name = "purpose")
	private String purpose;
	
	@Column(name = "used_by")
	private UUID usedBy;
	
	@Column(name = "unit_id")
	private UUID unitId;
	
	@Transient
	private String unitName;
	
	@Column(name = "remarks")
	private String remarks;
	
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getRefStandardChildId() {
		return refStandardChildId;
	}

	public void setRefStandardChildId(UUID refStandardChildId) {
		this.refStandardChildId = refStandardChildId;
	}

	public String getRefStandardName() {
		return refStandardName;
	}

	public void setRefStandardName(String refStandardName) {
		this.refStandardName = refStandardName;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public String getUsedQty() {
		return usedQty;
	}

	public void setUsedQty(String usedQty) {
		this.usedQty = usedQty;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public UUID getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(UUID usedBy) {
		this.usedBy = usedBy;
	}

	public UUID getUnitId() {
		return unitId;
	}

	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
