package com.pms.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class CultureItemInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "culture_item_name")
	private String cultureItemName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;

	@Column(name = "culture_item_type_id")
	private UUID cultureItemTypeId;

	@Column(name = "atcc_no")
	private String atccNo;

	
	@Transient
	private String cultureItemTypeName;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCultureItemName() {
		return cultureItemName;
	}

	public void setCultureItemName(String cultureItemName) {
		this.cultureItemName = cultureItemName;
	}

	public UUID getCultureItemTypeId() {
		return cultureItemTypeId;
	}

	public void setCultureItemTypeId(UUID cultureItemTypeId) {
		this.cultureItemTypeId = cultureItemTypeId;
	}


	public String getAtccNo() {
		return atccNo;
	}

	public void setAtccNo(String atccNo) {
		this.atccNo = atccNo;
	}

	public String getCultureItemTypeName() {
		return cultureItemTypeName;
	}

	public void setCultureItemTypeName(String cultureItemTypeName) {
		this.cultureItemTypeName = cultureItemTypeName;
	}

	
}
