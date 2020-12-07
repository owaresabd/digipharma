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
@Table(name = "lims_sampling_infos")
public class SamplingInfo extends CommonInfo{

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Transient
	private String udSampleNo;
	
	@Column(name = "css_request_id")
	private UUID cssRequestId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "method_id")
	private UUID methodId;
	
	@Transient
	private String methodName;
	
	@Transient
	private String boothName;
	
	@Transient
	private String equipmentName;
	
	@Transient
	private String workInstName;
	
	@Column(name = "sample_procedure")
	private String sampleProcedure;
	
	@Column(name = "equipment_id")
	private UUID equipmentId;
	
	@Column(name = "sampling_by")
	private UUID samplingBy;
	
	@Transient
	private String samplingByName;
	
	@Column(name = "storage_con_id")
	private UUID storageConId;
	
	@Column(name = "storage_condition")
	private String storageCondition;
	
	@Column(name = "area_booth_id")
	private UUID areaBoothId;
	
	@Column(name = "work_ins_id")
	private UUID workInsId;
	
	@Column(name = "precaution_taken")
	private String precautionTaken;
	
	@Type(type = "date")
	@Column(name = "sampling_date")
	private Date samplingDate;
	
	@Column(name = "remarks")
	private String remarks;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getCssRequestId() {
		return cssRequestId;
	}

	public void setCssRequestId(UUID cssRequestId) {
		this.cssRequestId = cssRequestId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public UUID getMethodId() {
		return methodId;
	}

	public void setMethodId(UUID methodId) {
		this.methodId = methodId;
	}

	public String getSampleProcedure() {
		return sampleProcedure;
	}

	public void setSampleProcedure(String sampleProcedure) {
		this.sampleProcedure = sampleProcedure;
	}

	public UUID getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(UUID equipmentId) {
		this.equipmentId = equipmentId;
	}

	public UUID getStorageConId() {
		return storageConId;
	}

	public void setStorageConId(UUID storageConId) {
		this.storageConId = storageConId;
	}

	public String getStorageCondition() {
		return storageCondition;
	}

	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}

	public UUID getAreaBoothId() {
		return areaBoothId;
	}

	public void setAreaBoothId(UUID areaBoothId) {
		this.areaBoothId = areaBoothId;
	}

	public UUID getWorkInsId() {
		return workInsId;
	}

	public void setWorkInsId(UUID workInsId) {
		this.workInsId = workInsId;
	}

	public String getWorkInstName() {
		return workInstName;
	}

	public void setWorkInstName(String workInstName) {
		this.workInstName = workInstName;
	}

	public String getPrecautionTaken() {
		return precautionTaken;
	}

	public void setPrecautionTaken(String precautionTaken) {
		this.precautionTaken = precautionTaken;
	}

	public Date getSamplingDate() {
		return samplingDate;
	}

	public void setSamplingDate(Date samplingDate) {
		this.samplingDate = samplingDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public UUID getSamplingBy() {
		return samplingBy;
	}

	public void setSamplingBy(UUID samplingBy) {
		this.samplingBy = samplingBy;
	}

	public String getUdSampleNo() {
		return udSampleNo;
	}

	public void setUdSampleNo(String udSampleNo) {
		this.udSampleNo = udSampleNo;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getSamplingByName() {
		return samplingByName;
	}

	public void setSamplingByName(String samplingByName) {
		this.samplingByName = samplingByName;
	}

	
	
}
