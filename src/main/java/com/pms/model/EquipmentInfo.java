package com.pms.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "lims_equipment_infos")
public class EquipmentInfo {

	@Id
	@Column(name = "id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "equipment_name")
	private String equipmentName;
	
	@Column(name = "equipment_type")
	private String equipmentType;

	@Column(name = "model_no")
	private String modelNo;
	
	@Column(name = "capacity")
	private String capacity;
	
	@Column(name = "source_manufacturer")
	private String sourceManufac;
	
	@Column(name = "local_agent_name")
	private String localAgentName;
	
	@Column(name = "curr_location")
	private String currLocation;
	
	@Column(name = "lc_model_no")
	private String lcModelNo;

	@Column(name = "serial_no")
	private String serialNo;
	
	@Column(name = "ms_model_no")
	private String msModelNo;
	
	@Column(name = "ms_serial_no")
	private String msSerialNo;
	
	@Type(type = "date")
	@Column(name = "manufacturing_date")
	private Date manufacturingDate;
	
	@Column(name = "pump_model_no")
	private String pumpModelNo;
	
	@Column(name = "detector_model_no")
	private String detectorModelNo;
	
	@Column(name = "software_name")
	private String softwareName;
	
	@Column(name = "software_version_no")
	private String softwareVersionNo;
	
	@Column(name = "software_firmware_no")
	private String softwareFirmwareNo;
	
	@Column(name = "electric_power")
	private String electricPower;
	
	@Column(name = "nitrogen_consumption")
	private String nitrogenConsumption;
	
	@Type(type = "date")
	@Column(name = "calibration_date")
	private Date calibrationDate;
	
	@Type(type = "date")
	@Column(name = "next_calibration_date")
	private Date nextCalibrationDate;
	
	@Type(type = "date")
	@Column(name = "install_qualific_date")
	private Date installQualificDate;
	
	@Type(type = "date")
	@Column(name = "operation_qualific_date")
	private Date operationQualificDate;
	
	@Type(type = "date")
	@Column(name = "performance_qualific_date")
	private Date performanceQualificDate;
	
	@Column(name = "quali_by")
	private String qualiBy;
	
	@Column(name = "equipment_id")
	private String equipmentId;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "accept_criteria")
	private String acceptCriteria;
	
	@Column(name = "calibration_interval")
	private String calibrationInterval;
	
	@Column(name = "evidence_verification")
	private String evidenceVerification;
	
	@Column(name = "adjustment")
	private String adjustment;
	
	@Column(name = "reference_material")
	private String referenceMaterial;
	
	@Column(name = "result_calibration")
	private String resultCalibration;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getSourceManufac() {
		return sourceManufac;
	}

	public void setSourceManufac(String sourceManufac) {
		this.sourceManufac = sourceManufac;
	}

	public String getLocalAgentName() {
		return localAgentName;
	}

	public void setLocalAgentName(String localAgentName) {
		this.localAgentName = localAgentName;
	}

	public String getLcModelNo() {
		return lcModelNo;
	}

	public void setLcModelNo(String lcModelNo) {
		this.lcModelNo = lcModelNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMsModelNo() {
		return msModelNo;
	}

	public void setMsModelNo(String msModelNo) {
		this.msModelNo = msModelNo;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public String getPumpModelNo() {
		return pumpModelNo;
	}

	public void setPumpModelNo(String pumpModelNo) {
		this.pumpModelNo = pumpModelNo;
	}

	public String getDetectorModelNo() {
		return detectorModelNo;
	}

	public void setDetectorModelNo(String detectorModelNo) {
		this.detectorModelNo = detectorModelNo;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwareVersionNo() {
		return softwareVersionNo;
	}

	public void setSoftwareVersionNo(String softwareVersionNo) {
		this.softwareVersionNo = softwareVersionNo;
	}

	public String getSoftwareFirmwareNo() {
		return softwareFirmwareNo;
	}

	public void setSoftwareFirmwareNo(String softwareFirmwareNo) {
		this.softwareFirmwareNo = softwareFirmwareNo;
	}

	public String getElectricPower() {
		return electricPower;
	}

	public void setElectricPower(String electricPower) {
		this.electricPower = electricPower;
	}

	public String getNitrogenConsumption() {
		return nitrogenConsumption;
	}

	public void setNitrogenConsumption(String nitrogenConsumption) {
		this.nitrogenConsumption = nitrogenConsumption;
	}

	public Date getCalibrationDate() {
		return calibrationDate;
	}

	public void setCalibrationDate(Date calibrationDate) {
		this.calibrationDate = calibrationDate;
	}

	public Date getNextCalibrationDate() {
		return nextCalibrationDate;
	}

	public void setNextCalibrationDate(Date nextCalibrationDate) {
		this.nextCalibrationDate = nextCalibrationDate;
	}

	public Date getInstallQualificDate() {
		return installQualificDate;
	}

	public void setInstallQualificDate(Date installQualificDate) {
		this.installQualificDate = installQualificDate;
	}

	public Date getOperationQualificDate() {
		return operationQualificDate;
	}

	public void setOperationQualificDate(Date operationQualificDate) {
		this.operationQualificDate = operationQualificDate;
	}

	public Date getPerformanceQualificDate() {
		return performanceQualificDate;
	}

	public void setPerformanceQualificDate(Date performanceQualificDate) {
		this.performanceQualificDate = performanceQualificDate;
	}
	
	public String getCurrLocation() {
		return currLocation;
	}

	public void setCurrLocation(String currLocation) {
		this.currLocation = currLocation;
	}

	public String getMsSerialNo() {
		return msSerialNo;
	}

	public void setMsSerialNo(String msSerialNo) {
		this.msSerialNo = msSerialNo;
	}

	public String getQualiBy() {
		return qualiBy;
	}

	public void setQualiBy(String qualiBy) {
		this.qualiBy = qualiBy;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAcceptCriteria() {
		return acceptCriteria;
	}

	public void setAcceptCriteria(String acceptCriteria) {
		this.acceptCriteria = acceptCriteria;
	}

	public String getCalibrationInterval() {
		return calibrationInterval;
	}

	public void setCalibrationInterval(String calibrationInterval) {
		this.calibrationInterval = calibrationInterval;
	}

	public String getEvidenceVerification() {
		return evidenceVerification;
	}

	public void setEvidenceVerification(String evidenceVerification) {
		this.evidenceVerification = evidenceVerification;
	}

	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

	public String getReferenceMaterial() {
		return referenceMaterial;
	}

	public void setReferenceMaterial(String referenceMaterial) {
		this.referenceMaterial = referenceMaterial;
	}

	public String getResultCalibration() {
		return resultCalibration;
	}

	public void setResultCalibration(String resultCalibration) {
		this.resultCalibration = resultCalibration;
	}

	

}
