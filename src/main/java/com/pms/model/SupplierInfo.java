package com.pms.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "pms_supplier_infos")
public class SupplierInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "supplier_code")
	private String supplierCode;

	@Column(name = "supplier_name")
	private String supplierName;
	
	@Column(name = "supplier_type_id",nullable = true)
	private UUID supplierTypeId;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "designation")
	private String designation;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;
	
	@Column(name = "website")
	private String website;

	@Column(name = "status")
	private String status;
	
	@Column(name = "company_id",updatable=false)
	private UUID companyId;
	
	@Column(name = "created_by",updatable=false)
	@CreatedBy
	private UUID createdBy;
	
	@Column(name = "created_at", updatable=false)
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@Column(name = "updated_by",insertable=false)
	@CreatedBy
	private UUID updatedBy;
	
	@Column(name = "updated_at",insertable=false)
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "supplier_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SupplierTypeInfo supplierTypeInfo;
	
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public UUID getSupplierTypeId() {
		return supplierTypeId;
	}

	public void setSupplierTypeId(UUID supplierTypeId) {
		this.supplierTypeId = supplierTypeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SupplierTypeInfo getSupplierTypeInfo() {
		return supplierTypeInfo;
	}

	public void setSupplierTypeInfo(SupplierTypeInfo supplierTypeInfo) {
		this.supplierTypeInfo = supplierTypeInfo;
	}

	public UUID getCompanyId() {
		return companyId;
	}

	public void setCompanyId(UUID companyId) {
		this.companyId = companyId;
	}

	public UUID getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public UUID getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UUID updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	
}