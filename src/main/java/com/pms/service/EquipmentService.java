package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.EquipMaintenInfo;
import com.pms.model.EquipmentInfo;
import com.pms.model.FrequencyInfo;
import com.pms.model.LogBenchtopReciprocalShakerInfo;
import com.pms.model.LogColumnRcvDistInfo;
import com.pms.model.LogElectricOvenInfo;
import com.pms.model.LogNoteBookControlInfo;
import com.pms.model.LogRefrigeratorInfo;
import com.pms.model.LogSpectrophotometerInfo;
import com.pms.model.LogTempeHumidityRecordInfo;
import com.pms.model.LogVacuumPumpInfo;
import com.pms.model.LogWaterPurificationInfo;
import com.pms.repository.EquipmentRepository;


@Service
public class EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	public List<EquipmentInfo> findAll(String status) {
		return equipmentRepository.findAll(status);
	}
	
	public List<EquipMaintenInfo> getMaintenanceScheduleDetails(String id) {
		return equipmentRepository.findMaintenanceScheduleDetails(id);
	}
	
	public List<LogVacuumPumpInfo> getLogDistinctVaccumEquipment(String status) {
		return equipmentRepository.getLogDistinctVaccumEquipment(status);
	}
	
	public List<LogSpectrophotometerInfo> findLogDistinctEquipmentForUv(String status) {
		return equipmentRepository.findLogDistinctEquipmentForUv(status);
	}
	

	public List<LogWaterPurificationInfo> getLogDistinctEquipment(String status) {
		return equipmentRepository.findLogDistinctEquipment(status);
	}

	
	public List<LogElectricOvenInfo> findLogDistinctEquipmentForOven(String status) {
		return equipmentRepository.findLogDistinctEquipmentForOven(status);
	}
	

	public List<LogTempeHumidityRecordInfo> findLogDistinctEquipmentTemHumidity(String status) {
		return equipmentRepository.findLogDistinctEquipmentTemHumidity(status);
	}
	public List<LogRefrigeratorInfo> findLogDistinctEquipmentRefrigeratorTemp(String status) {
		return equipmentRepository.findLogDistinctEquipmentRefrigeratorTemp(status);
	}
	

	
	
	
	public List<LogRefrigeratorInfo> findLogDistinctRefrigeratorEquipment(String status) {
		return equipmentRepository.findLogDistinctRefrigeratorEquipment(status);
	}
	public List<LogColumnRcvDistInfo> findLogDistinctColRcvEquipment(String status) {
		return equipmentRepository.findLogDistinctColRcvEquipment(status);
	}

public List<LogBenchtopReciprocalShakerInfo> findLogDistinctEquipmentForBeanchtop(String status) {
		return equipmentRepository.findLogDistinctEquipmentForBeanchtop(status);
	}
	
	

/*	public List<LogWaterPurificationInfo> getLogDistinctEquipmentDeHumidifier(String status) {
		return equipmentRepository.findLogDistinctEquipmentDeHumidifier(status);
	}*/

	public List<LogWaterPurificationInfo> getLogDistinctEquipmentTOC(String status) {
		return equipmentRepository.findLogDistinctEquipmentTOC(status);
	}

	public List<LogWaterPurificationInfo> getLogDistinctEquipmentSampleLabel(String status) {
		return equipmentRepository.findLogDistinctEquipmentSampleLabel(status);
	}

	public List<LogWaterPurificationInfo> getLogDistinctEquipmentAbsorption(String status) {
		return equipmentRepository.findLogDistinctEquipmentAbsorption(status);
	}

	public List<LogWaterPurificationInfo> getLogDistinctEquipmentDesicator(String status) {
		return equipmentRepository.findLogDistinctEquipmentDesicator(status);
	}

	public List<LogWaterPurificationInfo> getLogDistinctEquipmentDryCabinet(String status) {
		return equipmentRepository.findLogDistinctEquipmentDryCabinet(status);
	}
	
	public List<LogWaterPurificationInfo> getLogDistinctVerificationDissolution(String status) {
		return equipmentRepository.findLogVerificationDissolution(status);
	}
	public List<LogWaterPurificationInfo> getLogDistinctHPLC(String status) {
		return equipmentRepository.findLogHPLC(status);
	}

	public List<EquipmentInfo> equipmentType(String status) {
		return equipmentRepository.equipmentType(status);
	}
	public List<EquipmentInfo> equipmentForDistribution(String status,String deptCode) {
		return equipmentRepository.equipmentForDistribution(status,deptCode);
	}

	public List<EquipMaintenInfo> getEquipMaintenInfos() {
		return equipmentRepository.findEquipMaintenInfos();
	}
	public List<EquipMaintenInfo> getEquipNotificationInfos() {
		return equipmentRepository.getEquipNotificationInfos();
	}

	public List<EquipMaintenInfo> getEquipMaintenLogInfos() {
		return equipmentRepository.findEquipMaintenLogInfos();
	}

	public void saveEquipmentInfos(Map<String, String[]> requestMap, UUID idRandom) {
		EquipmentInfo info = new EquipmentInfo();

		if (requestMap.get("id")[0].equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		}

		info.setEquipmentName(requestMap.get("equipmentName")[0]);
		info.setEquipmentType(requestMap.get("equipmentType")[0]);
		info.setModelNo(requestMap.get("modelNo")[0]);
		info.setCapacity(requestMap.get("capacity")[0]);
		info.setSourceManufac(requestMap.get("sourceManufac")[0]);
		info.setLocalAgentName(requestMap.get("localAgentName")[0]);
		info.setCurrLocation(requestMap.get("currLocation")[0]);
		info.setLcModelNo(requestMap.get("lcModelNo")[0]);
		info.setSerialNo(requestMap.get("serialNo")[0]);
		info.setMsModelNo(requestMap.get("msModelNo")[0]);
		info.setMsSerialNo(requestMap.get("msSerialNo")[0]);
		if (requestMap.get("manufacturingDate")[0].trim().equals("")) {
			info.setManufacturingDate(null);
		} else {
			info.setManufacturingDate((Date) Utility.getSqlDate(requestMap.get("manufacturingDate")[0]));
		}
		info.setPumpModelNo(requestMap.get("pumpModelNo")[0]);
		info.setDetectorModelNo(requestMap.get("detectorModelNo")[0]);
		info.setSoftwareName(requestMap.get("softwareName")[0]);
		info.setSoftwareVersionNo(requestMap.get("softwareVersionNo")[0]);
		info.setSoftwareFirmwareNo(requestMap.get("softwareFirmwareNo")[0]);
		info.setElectricPower(requestMap.get("electricPower")[0]);
		info.setNitrogenConsumption(requestMap.get("nitrogenConsumption")[0]);
		if (requestMap.get("calibrationDate")[0].trim().equals("")) {
			info.setCalibrationDate(null);
		} else {
			info.setCalibrationDate((Date) Utility.getSqlDate(requestMap.get("calibrationDate")[0]));
		}
		if (requestMap.get("nextCalibrationDate")[0].trim().equals("")) {
			info.setNextCalibrationDate(null);
		} else {
			info.setNextCalibrationDate((Date) Utility.getSqlDate(requestMap.get("nextCalibrationDate")[0]));
		}
		if (requestMap.get("installQualificDate")[0].trim().equals("")) {
			info.setInstallQualificDate(null);
		} else {
			info.setInstallQualificDate((Date) Utility.getSqlDate(requestMap.get("installQualificDate")[0]));
		}
		if (requestMap.get("operationQualificDate")[0].trim().equals("")) {
			info.setOperationQualificDate(null);
		} else {
			info.setOperationQualificDate((Date) Utility.getSqlDate(requestMap.get("operationQualificDate")[0]));
		}
		if (requestMap.get("performanceQualificDate")[0].trim().equals("")) {
			info.setPerformanceQualificDate(null);
		} else {
			info.setPerformanceQualificDate((Date) Utility.getSqlDate(requestMap.get("performanceQualificDate")[0]));
		}

		info.setQualiBy(requestMap.get("qualiBy")[0]);
		info.setEquipmentId(requestMap.get("equipmentId")[0]);
		info.setBrand(requestMap.get("brand")[0]);
		info.setAcceptCriteria(requestMap.get("acceptCriteria")[0]);
		info.setCalibrationInterval(requestMap.get("calibrationInterval")[0]);
		info.setEvidenceVerification(requestMap.get("evidenceVerification")[0]);
		info.setAdjustment(requestMap.get("adjustment")[0]);
		info.setReferenceMaterial(requestMap.get("referenceMaterial")[0]);
		if (!requestMap.get("resultCalibration")[0].equals("")) {
			info.setResultCalibration(requestMap.get("resultCalibration")[0]);
		}
		equipmentRepository.saveEquipmentInfos(info, idRandom);
	}

	public void saveEquipMaintenInfo(Map<String, String[]> requestMap, UUID idRandom) {
		EquipMaintenInfo info = new EquipMaintenInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		String typeId = requestMap.get("typeId")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (typeId.trim().equals("")) {
			info.setTypeId(null);
		} else {
			info.setTypeId(UUID.fromString(requestMap.get("typeId")[0]));
		}
		if (!requestMap.get("lastSchedule")[0].equals("") && !requestMap.get("lastSchedule")[0].equals(null)) {
			info.setLastSchedule((Date) Utility.getSqlDate(requestMap.get("lastSchedule")[0]));
		} else {
			info.setLastSchedule(null);
		}

		info.setFreqType(requestMap.get("freqType")[0]);
		info.setFreqDuration(requestMap.get("freqDuration")[0]);

		if (requestMap.get("freqType")[0].equals("D")) {
			String date = requestMap.get("lastSchedule")[0];
			int nbDays = 1;
			info.setNextSchedule((Date) Utility.addDays(date, nbDays));

		} else if (requestMap.get("freqType")[0].equals("M")) {
			String date = requestMap.get("lastSchedule")[0];
			int nbMonths = Integer.parseInt(requestMap.get("freqDuration")[0]);
			info.setNextSchedule((Date) Utility.addMonths(date, nbMonths));

		} else if (requestMap.get("freqType")[0].equals("Y")) {
			String date = requestMap.get("lastSchedule")[0];
			int years = Integer.parseInt(requestMap.get("freqDuration")[0]);
			info.setNextSchedule((Date) Utility.addYears(date, years));

		} else if (requestMap.get("freqType")[0].equals("C")) {
			String date = requestMap.get("lastSchedule")[0];
			int days = Integer.parseInt(requestMap.get("freqDuration")[0]);
			info.setNextSchedule((Date) Utility.addDays(date, days));

		}else {
			info.setNextSchedule(null);
		}
		if (!requestMap.get("attachmentNm")[0].equals("")) {
			info.setAttachmentNm(requestMap.get("attachmentNm")[0]);
		}
		info.setRemarks(requestMap.get("remarks")[0]);
		equipmentRepository.saveEquipMaintenInfo(info, idRandom);

	}

	public EquipmentInfo geEquipmentInfoById(UUID id) {

		return equipmentRepository.geEquipmentInfoById(id);
	}

	public List<FrequencyInfo> getEquipment(String status) {
		return equipmentRepository.findEquipment(status);
	}

	public List<FrequencyInfo> maintenanceByEquipment(Map<String, String[]> requestMap) {
		UUID equipmentId = UUID.fromString(requestMap.get("equipmentId")[0]);
		return equipmentRepository.maintenanceByEquipment(equipmentId);
	}

	public FrequencyInfo getFrequenceTypeById(UUID equpId, UUID typeId) {
		return equipmentRepository.findFrequenceTypeById(equpId, typeId);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentPhMeter(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentPhMeter(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentKarlFischer(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentKarlFischer(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentBiologicalSafety(String equipmentId,
			String cleanStatus, String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentBiologicalSafety(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentMuffleFurnace(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentMuffleFurnace(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentMeltingPoint(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentMeltingPoint(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentAutomaticPolarization(String equipmentId,
			String cleanStatus, String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentAutomaticPolarization(equipmentId, cleanStatus,
				verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentBiochemicalOxyzen(String equipmentId,
			String cleanStatus, String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentBiochemicalOxyzen(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentFumeHood(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentFumeHood(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentLaboratoryHeater(String equipmentId,
			String cleanStatus, String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentLaboratoryHeater(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentGasChromatography(String equipmentId,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentGasChromatography(equipmentId, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentRefractometer(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentRefractometer(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentFtir(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentFtir(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDIssolution(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentDIssolution(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentSonicator(String equipmentId, String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentSonicator(equipmentId, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentTempRefrigerator(String equipmentId,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentTempRefrigerator(equipmentId, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDAtaBackup(String equipmentId, String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentDAtaBackup(equipmentId, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentAnalytical(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentAnalytical(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentConductivity(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentConductivity(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogNoteBookControlInfo> findLogDistinctEmployeeNoteBook(String employeeId, String receiveStatus,
			String returnStatus) {
		return equipmentRepository.findLogDistinctEmployeeNoteBook(employeeId, receiveStatus, returnStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentMoisture(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentMoisture(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDisIntegration(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentDisIntegration(equipmentId, cleanStatus, verifyStatus);
	}
	
	public List<LogWaterPurificationInfo> getLogDistinctEquipmentDeHumidifier(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return equipmentRepository.findLogDistinctEquipmentDeHumidifier(equipmentId, cleanStatus, verifyStatus);
	}
	//For Micro
	public List<CommonInfo> findLogDistinctEquipmentMCInfos(String equipmentId, String checkedStatus,String verifiedStatus,	String tableName) {
		return equipmentRepository.findLogDistinctEquipmentMCInfos(equipmentId, checkedStatus,verifiedStatus,tableName);
	}

	public List<EquipmentInfo> findUnassignedEquipment() {
		return equipmentRepository.findUnassignedEquipment();
	}
	public List<EquipmentInfo> findAssignedEquipment(Map<String, String[]> requestMap) {
		String departmentId = requestMap.get("departmentId")[0];
		return equipmentRepository.findAssignedEquipment(departmentId);
	}
	public void addEquipmentMapping(Map<String, String[]> map) {
		UUID equipmentId = UUID.fromString(map.get("equipmentId")[0]);
		String departmentId = map.get("departmentId")[0];
		equipmentRepository.saveEquipmentMapping(equipmentId, departmentId);
	}
	// equipmentId,  eveningStatus,  cleanedStatus, checkedStatus 
	public List<CommonInfo> findLogDistinctEquipmentMC5Infos(String equipmentId, String eveningStatus, String cleanedStatus,String checkedStatus ,	String tableName) {
			return equipmentRepository.findLogDistinctEquipmentMC5Infos(equipmentId,  eveningStatus,  cleanedStatus, checkedStatus ,tableName);
		}
	public List<CommonInfo> findLogDistinctEquipmentMC4Infos(String equipmentId, String cleanedStatus,String checkedStatus ,String tableName) {
		return equipmentRepository.findLogDistinctEquipmentMC4Infos(equipmentId, cleanedStatus, checkedStatus ,tableName);

	}
	

	public void removeEquipmentMapping(Map<String, String[]> map) {
		UUID equipmentId = UUID.fromString(map.get("equipmentId")[0]);
		String departmentId = map.get("departmentId")[0];
		equipmentRepository.deleteEquipmentMapping(equipmentId, departmentId);
	}
}
