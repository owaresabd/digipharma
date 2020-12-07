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
@Table(name = "lims_wh_request_infos")
public class WhRequestInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private String udWhReqNo;
	
	@Transient
	private UUID wsRequestId;
	
	@Column(name = "req_type")
	private UUID reqType;
	
	@Transient
	private String reqTypeName;
	
	@Transient
	private String isWorkingStandard;
	
	@Type(type = "date")
	@Column(name = "effective_date")
	private Date effectivDate;
	
	@Column(name = "revision_no")
	private String revisionNo;
	
	@Column(name = "form_no")
	private String formNo;
	
	@Column(name = "invoice_no")
	private String invoiceNo;
	
	@Column(name = "chalan_no")
	private String chalanNo;
	
	@Column(name = "lc_no")
	private String lcNo;
	
	@Column(name = "from_dept_no")
	private UUID fromDeptNo;
	
	@Transient
	private String fromDeptName;
	
	@Column(name = "to_dept_no")
	private UUID toDeptNo;
	
	@Transient
	private String toDeptName;
	
	@Transient
	private String keptAmount;
	
	@Column(name = "gr_no")
	private String grNo;
	
	@Column(name = "material_id")
	private UUID materialId;
	
	@Transient
	private String materialName;
	
	@Column(name = "material_code")
	private String materialCode;
	
	@Column(name = "material_type_id")
	private UUID materialTypeId;
	
	@Transient
	private String materialTypeName;
	
	@Column(name = "rcv_qty")
	private String rcvQty;
	
	@Column(name = "unit_id")
	private UUID rcvUnitId;
	
	@Transient
	private String rcvUnitName;
	
	@Column(name = "manufacture_name")
	private String manufactureName;
	
	@Column(name = "manufacture_id")
	private UUID manufactureId;
	
	@Column(name = "country_id")
	private UUID countryId;
	
	@Transient
	private String countryName;
	
	@Column(name = "sample_details")
	private String sampleDetails;
    
	@Column(name = "req_status")
	private String status;
	
	@Column(name = "provider_type")
	private String providerType;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getReqType() {
		return reqType;
	}

	public void setReqType(UUID reqType) {
		this.reqType = reqType;
	}

	public Date getEffectivDate() {
		return effectivDate;
	}

	public void setEffectivDate(Date effectivDate) {
		this.effectivDate = effectivDate;
	}

	public String getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String form_no) {
		this.formNo = form_no;
	}

	

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getChalanNo() {
		return chalanNo;
	}

	public void setChalanNo(String chalanNo) {
		this.chalanNo = chalanNo;
	}

	public String getLcNo() {
		return lcNo;
	}

	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}

	public UUID getFromDeptNo() {
		return fromDeptNo;
	}

	public void setFromDeptNo(UUID fromDeptNo) {
		this.fromDeptNo = fromDeptNo;
	}

	public UUID getToDeptNo() {
		return toDeptNo;
	}

	public void setToDeptNo(UUID toDeptNo) {
		this.toDeptNo = toDeptNo;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public UUID getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(UUID materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getRcvQty() {
		return rcvQty;
	}

	public void setRcvQty(String rcvQty) {
		this.rcvQty = rcvQty;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public UUID getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(UUID manufactureId) {
		this.manufactureId = manufactureId;
	}

	public UUID getCountryId() {
		return countryId;
	}

	public void setCountryId(UUID countryId) {
		this.countryId = countryId;
	}

	public String getSampleDetails() {
		return sampleDetails;
	}

	public void setSampleDetails(String sampleDetails) {
		this.sampleDetails = sampleDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReqTypeName() {
		return reqTypeName;
	}

	public void setReqTypeName(String reqTypeName) {
		this.reqTypeName = reqTypeName;
	}

	public String getFromDeptName() {
		return fromDeptName;
	}

	public void setFromDeptName(String fromDeptName) {
		this.fromDeptName = fromDeptName;
	}

	public String getToDeptName() {
		return toDeptName;
	}

	public void setToDeptName(String toDeptName) {
		this.toDeptName = toDeptName;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public UUID getMaterialId() {
		return materialId;
	}

	public void setMaterialId(UUID materialId) {
		this.materialId = materialId;
	}

	public String getUdWhReqNo() {
		return udWhReqNo;
	}

	public void setUdWhReqNo(String udWhReqNo) {
		this.udWhReqNo = udWhReqNo;
	}

	public UUID getRcvUnitId() {
		return rcvUnitId;
	}

	public void setRcvUnitId(UUID rcvUnitId) {
		this.rcvUnitId = rcvUnitId;
	}

	public String getRcvUnitName() {
		return rcvUnitName;
	}

	public void setRcvUnitName(String rcvUnitName) {
		this.rcvUnitName = rcvUnitName;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public UUID getWsRequestId() {
		return wsRequestId;
	}

	public void setWsRequestId(UUID wsRequestId) {
		this.wsRequestId = wsRequestId;
	}

	public String getIsWorkingStandard() {
		return isWorkingStandard;
	}

	public void setIsWorkingStandard(String isWorkingStandard) {
		this.isWorkingStandard = isWorkingStandard;
	}

	public String getKeptAmount() {
		return keptAmount;
	}

	public void setKeptAmount(String keptAmount) {
		this.keptAmount = keptAmount;
	}

	

}
