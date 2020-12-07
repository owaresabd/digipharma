package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.EquipmentInfo;
import com.pms.model.LogAllottedSampleInfo;
import com.pms.model.LogAnalystValidationInfo;
import com.pms.model.LogAnalyticalBalanceInfo;
import com.pms.model.LogAreaCleanInfo;
import com.pms.model.LogAtomicAbsorptionInfo;
import com.pms.model.LogAutomaticPolarimeterInfo;
import com.pms.model.LogBenchtopReciprocalShakerInfo;
import com.pms.model.LogBiochemicalOxygenInfo;
import com.pms.model.LogBiologicalSafetyInfo;
import com.pms.model.LogColumnInfo;
import com.pms.model.LogColumnPerformanceRecordInfo;
import com.pms.model.LogColumnRcvDistInfo;
import com.pms.model.LogComparativeDissolutionDutyInfo;
import com.pms.model.LogDataBackupInfo;
import com.pms.model.LogDeHumidifierInfo;
import com.pms.model.LogDesiccatorInfo;
import com.pms.model.LogDisintegrationTestInfo;
import com.pms.model.LogDissolutionInfo;
import com.pms.model.LogDryCabinetInfo;
import com.pms.model.LogEhdInfo;
import com.pms.model.LogElectricOvenInfo;
import com.pms.model.LogFilterChangeInfo;
import com.pms.model.LogFtirInfo;
import com.pms.model.LogFumeHoodInfo;
import com.pms.model.LogGasChromatographyInfo;
import com.pms.model.LogHplcInfo;
import com.pms.model.LogKarlFischerInfo;
import com.pms.model.LogLaboratoryHeaterInfo;
import com.pms.model.LogMeltingPointInfo;
import com.pms.model.LogMoistureAnalyzerInfo;
import com.pms.model.LogMuffleFurnaceInfo;
import com.pms.model.LogMultiParameterInfo;
import com.pms.model.LogNoteBookControlInfo;
import com.pms.model.LogOutofTrend;
import com.pms.model.LogPhMeterInfo;
import com.pms.model.LogRefractometerInfo;
import com.pms.model.LogRefrigeratorInfo;
import com.pms.model.LogSampleLabelInfo;
import com.pms.model.LogSonicatorBathInfo;
import com.pms.model.LogSpectrophotometerInfo;
import com.pms.model.LogTOCInfo;
import com.pms.model.LogTempHumidityInfo;
import com.pms.model.LogTempRefrigeratorInfo;
import com.pms.model.LogTempeHumidityRecordInfo;
import com.pms.model.LogTmControlInfo;
import com.pms.model.LogVacuumPumpInfo;
import com.pms.model.LogVerificationDissolutionChdInfo;
import com.pms.model.LogVerificationDissolutionInfo;
import com.pms.model.LogWaterPurificationInfo;
import com.pms.model.LogWaterSamplingInfo;
import com.pms.model.RefrigeratorTempChemiSetupInfo;
import com.pms.model.TempAndHumiditySetupInfo;
import com.pms.repository.AllSetupRepository;
import com.pms.repository.LogChemiRepository;

@Service
public class LogChemiService {

	@Autowired
	private LogChemiRepository chemiRepository;

	@Autowired
	private AllSetupRepository allSetupRepository;
	
	@Autowired
	private AllSetupService allSetupService;
	
	
	public List<EquipmentInfo> findAllLogEquipment(String logbookCode) {
		return chemiRepository.findAllLogEquipment(logbookCode);
	}
	public List<CommonInfo> getQcRefInfos() {
		return chemiRepository.findQcRefInfos();
	}

	public List<LogDataBackupInfo> getDataBackupInfos(String verifyStatus) {
		return chemiRepository.findDataBackupInfos( verifyStatus);
	}

	public List<LogEhdInfo> getEhdInfos(String receiveStatus,String returnStatus, String verifyStatus) {
		return chemiRepository.findEhdInfos(receiveStatus, returnStatus, verifyStatus);
	}

	public List<LogDesiccatorInfo> getDesiccatorInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findDesiccatorInfos(eqipId, verifyStatus);
	}

	public List<LogDryCabinetInfo> getDryCabinetInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findDryCabinetInfos(eqipId, verifyStatus);
	}

	public List<LogVerificationDissolutionInfo> getVerificationDissolutionInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findVerificationDissolutionInfos(eqipId, verifyStatus);
	}
	public List<LogVerificationDissolutionChdInfo> getVerificationDissolutionChildInfos(UUID id) {
		return chemiRepository.getVerificationDissolutionChildInfos(id);
	}
	public List<LogAreaCleanInfo> getAreaCleanInfos(String cleanStatus, String verifyStatus) {
		return chemiRepository.findAreaCleanInfos(cleanStatus, verifyStatus);
	}

	public List<LogMultiParameterInfo> getMultiParamInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		return chemiRepository.findMultiParamInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogMoistureAnalyzerInfo> findMoistureAnalyzerInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findMoistureParamInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogDisintegrationTestInfo> findDisIntegrationInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findDisintegrationIfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogHplcInfo> getHplcInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findHplcInfos(eqipId, verifyStatus);
	}
	public List<CommonInfo> getSampleReceivingInfos(String id, String verifyStatus) {
		return chemiRepository.findSampleReceivingInfos(id, verifyStatus);
	}

	public List<LogVacuumPumpInfo> getVacuumPumpInfos() {
		return chemiRepository.findVacuumPumpInfos();
	}

	public List<LogFilterChangeInfo> getFilterChangeInfos() {
		return chemiRepository.findFilterChangeInfos();
	}

	public List<LogPhMeterInfo> getPhMeterInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		return chemiRepository.findPhMeterInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogDissolutionInfo> getDissolutionInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		return chemiRepository.findDissolutionInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogSonicatorBathInfo> getSonicatorBathInfos(String equipmentId, String verifyStatus) {
		return chemiRepository.findSonicatorBathInfos(equipmentId, verifyStatus);
	}

	public List<LogTempRefrigeratorInfo> getTempRefrigeratorInfos(String equipmentId, String verifyStatus) {
		return chemiRepository.findTempRefrigeratorInfos(equipmentId, verifyStatus);
	}

	public List<LogKarlFischerInfo> getKarlFischerTitratorInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findKarlFischerTitratorInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogBiologicalSafetyInfo> findBiologicalSafetyInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findBiologicalSafetyInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogMuffleFurnaceInfo> findMuffleFuranceInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findMuffleFuranceInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogMeltingPointInfo> findMeltingPointInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findMeltingPointInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogAutomaticPolarimeterInfo> findAutomaticPolarimeterInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findAutomaticPolarimeterInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogBiochemicalOxygenInfo> findBiochemicalOxygenInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findBiochemicalOxygenInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogFumeHoodInfo> findFumeHoodInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		return chemiRepository.findFumeHoodInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogLaboratoryHeaterInfo> findLaboratoryHeaterInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findLaboratoryHeaterInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogGasChromatographyInfo> findGasChromatographyInfos(String equipmentId, String verifyStatus) {
		return chemiRepository.findGasChromatographyInfos(equipmentId, verifyStatus);
	}

	public List<LogRefractometerInfo> findRefractometerInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findRefractometerInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogTmControlInfo> getTmControllInfos() {
		return chemiRepository.findTmControllInfos();
	}

	public List<LogSpectrophotometerInfo> getSpectrophotometerInfos() {
		return chemiRepository.findSpectrophotometerInfos();

	}

	public List<LogSpectrophotometerInfo> getUvVerifiedPendingInfos(String eqipId, String verifyStatus) {
		return chemiRepository.getUvVerifiedPendingInfos(eqipId, verifyStatus);
	}

	public void verifyUvSpectroPhotoMeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
                 chemiRepository.verifyUvSpectroPhotoMeter(info);

			}
		}

	}

	public List<LogBenchtopReciprocalShakerInfo> getBenchtopReciprocalShakerVerifiedPendingInfos(String eqipId,
			String verifyStatus) {
		return chemiRepository.getBenchtopReciprocalShakerVerifiedPendingInfos(eqipId, verifyStatus);
	}

	public List<LogBenchtopReciprocalShakerInfo> findBenchtopReciprocalShakerInfos() {
		return chemiRepository.findBenchtopReciprocalShakerInfos();
	}

	public void verifyTempHumidityRecord(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogTempeHumidityRecordInfo info = new LogTempeHumidityRecordInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyTempHumidityRecord(info);

			}
		}
	}

	public void verifyElectricOven(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyElectricOven(info);
			}
		}
	}
	
	public void verifyColRcvDistribution(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.verifyColRcvDistribution(info);

			}
		}

	}

	public void verifyRefrigerator(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogRefrigeratorInfo info = new LogRefrigeratorInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyRefrigerator(info);

			}
		}

	}

	public void verifyBenchtopReciprocalShaker(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyBenchtopReciprocalShaker(info);
			}
		}
	}

	public void saveBenchtopReciprocalShaker(Map<String, String[]> requestMap) {
		LogBenchtopReciprocalShakerInfo info = new LogBenchtopReciprocalShakerInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];//--

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		
		if (qcReferenceId.equals("")) {
			info.setQcReferenceId(null);
		} else {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		}
		
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveBenchtopReciprocalShaker(info);

	}

	public List<LogFtirInfo> getFtirInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		return chemiRepository.findFtirInfos(equipmentId, cleanStatus, verifyStatus);
	}


	public List<LogElectricOvenInfo> getElectricOvenInfos() {
		return chemiRepository.findElectricOvenInfos();
	}

	public List<LogTempeHumidityRecordInfo> getTemperatureHumidityRecordInfos() {
		return chemiRepository.getTemperatureHumidityRecordInfos();
	}

	public List<LogTempeHumidityRecordInfo> tempHumidityRecordVerifyList() {
		return chemiRepository.tempHumidityRecordVerifyList();
	}

	public List<LogElectricOvenInfo> electricOvenVerifyList() {
		return chemiRepository.electricOvenVerifyList();
	}

	public List<LogElectricOvenInfo> getElectricOvenVerifiedPendingInfos(String eqipId, String verifyStatus) {
		return chemiRepository.getElectricOvenVerifiedPendingInfos(eqipId, verifyStatus);
	}

	public List<LogRefrigeratorInfo> getRefrigeratorVerifiedPendingInfos(String eqipId, String verifyStatus) {
		return chemiRepository.getRefrigeratorVerifiedPendingInfos(eqipId, verifyStatus);
	}

	public List<LogTempeHumidityRecordInfo> getTempHumidityRecordVerifiedPendingInfos(String eqipId,
			String verifyStatus) {
		return chemiRepository.getTempHumidityRecordVerifiedPendingInfos(eqipId, verifyStatus);
	}

	public List<LogRefrigeratorInfo> getRefrigeratorInfos() {
		return chemiRepository.findRefrigeratorInfos();
	}

	public List<LogAnalyticalBalanceInfo> getAnalyticalBalanceInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		return chemiRepository.findAnalyticalBalanceInfos(equipmentId, cleanStatus, verifyStatus);
	}

	public List<LogColumnRcvDistInfo> getColRcvRecordVerifiedPendingInfos(String equipmentId, String status) {
		return getColumnRcvDistInfos(equipmentId,null,null,null,null).stream().filter(st -> st.getIsVerify().equals(status)).collect(Collectors.toList());

	}

	public List<LogColumnRcvDistInfo> getColumnRcvDistInfos(String eqId,String isVerify, String isIssued, String isRecord, String isRecordVerify ) {
		return chemiRepository.findColumnRcvDistInfos(eqId,isVerify,isIssued,isRecord,isRecordVerify);
	}

	public List<LogColumnPerformanceRecordInfo> getColumnPerformancePendingRecordInfos( ) {
		return chemiRepository.findColumnPerformancePendingInfos();
	}
	public List<LogColumnPerformanceRecordInfo> getColumnPerformanceRecordInfos(String verifyStatus) {
		return chemiRepository.findColumnPerformanceRecordsInfos(verifyStatus);
	}
	
	public List<LogColumnRcvDistInfo> getColumnRcvDistIssuedInfos(String eqId) {
		return chemiRepository.findColumnRcvDistInfos(eqId, null,null,null,null).stream().filter(issue -> issue.getIsIssue().equals("Y"))
				.collect(Collectors.toList());
	}

	public List<LogColumnInfo> getColumnInfos() {
		return chemiRepository.findColumnInfos();
	}

	public List<LogTempHumidityInfo> getTempHumidityInfos() {
		return chemiRepository.findTempHumidityInfos();
	}

	public List<CommonInfo> getDateRangeList() {
		return chemiRepository.findDateRangeList();
	}

	public boolean validateEhdNo(Map<String, String[]> requestMap) {
		String name = requestMap.get("ehdNo")[0];
		List<LogEhdInfo> entityTransList = chemiRepository.validateEhdNo(name);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}

	public CommonInfo getSampleInfoById(UUID id) {
		CommonInfo info = chemiRepository.findSampleInfoById(id);
		return info;
	}

	public CommonInfo getMaterialInfoByArn(UUID arnNo) {
		CommonInfo info = chemiRepository.findMaterialInfoByArn(arnNo);
		return info;
	}

	public void saveDataBackupInfo(Map<String, String[]> requestMap) {
		LogDataBackupInfo info = new LogDataBackupInfo();
		String msId = requestMap.get("id")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		}

		if (!requestMap.get("backupDate")[0].equals("") && !requestMap.get("backupDate")[0].equals(null)) {
			info.setBackupDate((Date) Utility.getSqlDate(requestMap.get("backupDate")[0]));
		} else {
			info.setBackupDate(null);
		}
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setInstrumentName(requestMap.get("instrumentName")[0]);

		chemiRepository.saveDataBackupLogInfo(info);
	}

	public List<LogNoteBookControlInfo> getLogNoteBookControlInfos(String receiveStatus, String returnStatus) {
		return chemiRepository.findLogNoteBookControlInfos(receiveStatus, returnStatus);
	}

	public void saveNoteBookControl(Map<String, String[]> requestMap) {
		LogNoteBookControlInfo info = new LogNoteBookControlInfo();
		String msId = requestMap.get("id")[0];
		String employeeId = requestMap.get("employeeId")[0];
		String desigId = requestMap.get("designationId")[0];
		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (employeeId.trim().equals("")) {
			info.setEmployeeId(null);
		} else {
			info.setEmployeeId(UUID.fromString(requestMap.get("employeeId")[0]));
		}
		if (desigId.trim().equals("")) {
			info.setDesignationId(null);
		} else {
			info.setDesignationId(UUID.fromString(requestMap.get("designationId")[0]));
		}

		info.setNoteBookNo(requestMap.get("noteBookNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveNoteBookControl(info);

	}

	public void saveTmControlLogInfo(Map<String, String[]> requestMap) {
		LogTmControlInfo info = new LogTmControlInfo();

		if (requestMap.get("id")[0].equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(requestMap.get("id")[0].toString()));
		}
		if (requestMap.get("recordDate")[0] != null && !requestMap.get("recordDate")[0].isEmpty()) {
			info.setRecordDate((Date) Utility.getSqlDate(requestMap.get("recordDate")[0]));
		} else {
			info.setRecordDate(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		if (requestMap.get("usedBy")[0] != null && !requestMap.get("usedBy")[0].isEmpty()) {
			info.setUsedBy(UUID.fromString(requestMap.get("usedBy")[0]));

		} else {
			info.setUsedBy(null);
		}
		if (requestMap.get("returnBy")[0] != null && !requestMap.get("returnBy")[0].isEmpty()) {
			info.setReturnBy(UUID.fromString(requestMap.get("returnBy")[0]));

		} else {
			info.setReturnBy(null);
		}

		if (requestMap.get("returnDate")[0] != null && !requestMap.get("returnDate")[0].isEmpty()) {
			info.setReturnDate((Date) Utility.getSqlDate(requestMap.get("returnDate")[0]));
		} else {
			info.setReturnDate(null);
		}
		if (requestMap.get("controlledBy")[0] != null && !requestMap.get("controlledBy")[0].isEmpty()) {
			info.setControlledBy(UUID.fromString(requestMap.get("controlledBy")[0]));

		} else {
			info.setControlledBy(null);
		}

		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveTmControlLogInfo(info);

	}


	public void saveEhdInfo(Map<String, String[]> requestMap) {
		LogEhdInfo info = new LogEhdInfo();
		String msId = requestMap.get("id")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		info.setEhdNo(requestMap.get("ehdNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveEhdInfo(info);
	}

	public void saveAreaCleanInfo(Map<String, String[]> requestMap) {
		LogAreaCleanInfo info = new LogAreaCleanInfo();
		String msId = requestMap.get("id")[0];
		String agentId = requestMap.get("agentId")[0];
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (agentId.trim().equals("")) {
			info.setAgentId(null);
		} else {
			info.setAgentId(UUID.fromString(requestMap.get("agentId")[0]));
		}
		info.setCleaningEquipment(requestMap.get("cleaningEquipment")[0]);
		if (!requestMap.get("cleaningDate")[0].equals("") && !requestMap.get("cleaningDate")[0].equals(null)) {
			info.setCleaningDate((Date) Utility.getSqlDate(requestMap.get("cleaningDate")[0]));
		} else {
			info.setCleaningDate(null);
		}
		if (!requestMap.get("cleaningExpDate")[0].equals("") && !requestMap.get("cleaningExpDate")[0].equals(null)) {
			info.setCleaningExpDate((Date) Utility.getSqlDate(requestMap.get("cleaningExpDate")[0]));
		} else {
			info.setCleaningExpDate(null);
		}
		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveAreaCleanInfo(info);

	}

	public void saveMultiParameterInfo(Map<String, String[]> requestMap) {
		LogMultiParameterInfo info = new LogMultiParameterInfo();
		String msId = requestMap.get("id")[0];
		String productID = requestMap.get("productId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];

		String qcRefId = requestMap.get("qcRefId")[0];
		String sampleTypeId = requestMap.get("sampleTypeId")[0];
		

		if (sampleTypeId !=null && !sampleTypeId.isEmpty()) {
			info.setSampleTypeId(UUID.fromString(requestMap.get("sampleTypeId")[0]));
			
		} else {
			info.setSampleTypeId(null);
		}
		if (qcRefId !=null && !qcRefId.isEmpty()) {
			info.setQcRefId(UUID.fromString(requestMap.get("qcRefId")[0]));
			
		} else {
			info.setQcRefId(null);
		}

		if (equipmentId !=null && !equipmentId.isEmpty()) {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
			
		} else {
			info.setEquipmentId(null);
		}

		if (msId!=null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));
			
		} else {
			info.setId(null);
		}
		if (productID !=null && !productID.isEmpty()) {
			info.setMaterialId(UUID.fromString(requestMap.get("productId")[0]));
			
		} else {
			info.setMaterialId(null);
		}

		info.setConductivity(requestMap.get("conductivity")[0]);
		info.setTds(requestMap.get("tds")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveMultiParameterInfo(info);

	}

	public void saveMoistureInfo(Map<String, String[]> requestMap) {
		LogMoistureAnalyzerInfo info = new LogMoistureAnalyzerInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		String sampleTypeId = requestMap.get("sampleTypeId")[0];
		String qcRefId = requestMap.get("qcRefId")[0];

		if (sampleTypeId.trim().equals("")) {
			info.setSampleTypeId(null);
		} else {
			info.setSampleTypeId(UUID.fromString(requestMap.get("sampleTypeId")[0]));
		}
		if (qcRefId.trim().equals("")) {
			info.setQcRefId(null);
		} else {
			info.setQcRefId(UUID.fromString(requestMap.get("qcRefId")[0]));
		}
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		info.setResult(requestMap.get("result")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveMoistureAnalyzerInfo(info);

	}

	public void saveDidIntegrationInfo(Map<String, String[]> requestMap) {
		LogDisintegrationTestInfo info = new LogDisintegrationTestInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		String sampleTypeId = requestMap.get("sampleTypeId")[0];
		String qcRefId = requestMap.get("qcRefId")[0];

		if (sampleTypeId.trim().equals("")) {
			info.setSampleTypeId(null);
		} else {
			info.setSampleTypeId(UUID.fromString(requestMap.get("sampleTypeId")[0]));
		}
		if (qcRefId.trim().equals("")) {
			info.setQcRefId(null);
		} else {
			info.setQcRefId(UUID.fromString(requestMap.get("qcRefId")[0]));
		}
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		info.setResult(requestMap.get("result")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveDisIntegrationInfo(info);

	}

	public void saveHplcInfo(Map<String, String[]> requestMap) {
		LogHplcInfo info = new LogHplcInfo();
		String msId = requestMap.get("id")[0];
		String qcRefId = requestMap.get("qcRefId")[0];
		String columnId = requestMap.get("columnId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcRefId.trim().equals("")) {
			info.setQcRefId(null);
		} else {
			info.setQcRefId(UUID.fromString(requestMap.get("qcRefId")[0]));
		}
		if (columnId.trim().equals("")) {
			info.setColumnId(null);
		} else {
			info.setColumnId(UUID.fromString(requestMap.get("columnId")[0]));
		}
	
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (!requestMap.get("recordDate")[0].equals("") && !requestMap.get("recordDate")[0].equals(null)) {
			info.setRecordDate((Date) Utility.getSqlDate(requestMap.get("recordDate")[0]));
		} else {
			info.setRecordDate(null);
		}
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setRunStartTime(requestMap.get("runStartTime")[0]);
		info.setRunEndTime(requestMap.get("runEndTime")[0]);
		info.setFirstMpRatio(requestMap.get("firstMpRatio")[0]);
		info.setFirstStartTime(requestMap.get("firstStartTime")[0]);
		info.setFirstEndTime(requestMap.get("firstEndTime")[0]);
		info.setSecondMpRatio(requestMap.get("secondMpRatio")[0]);
		info.setSecondStartTime(requestMap.get("secondStartTime")[0]);
		info.setSecondEndTime(requestMap.get("secondEndTime")[0]);
		info.setTotalTime(requestMap.get("totalTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveHplcInfo(info);

	}

	public void saveVacuumPump(Map<String, String[]> requestMap) {
		LogVacuumPumpInfo info = new LogVacuumPumpInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];

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
		info.setPurpose(requestMap.get("purpose")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveVacuumPumpInfo(info);

	}

	public List<LogVacuumPumpInfo> findAllVacuumPumpVerifyList(String eqipId, String verifyStatus) {
		return chemiRepository.findAllVacuumPumpVerifyList(eqipId, verifyStatus);
	}

	public void verifyVaccumPump(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		

		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogVacuumPumpInfo info = new LogVacuumPumpInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyVaccumPump(info);

			}
		}

	}

	public void saveFilterChange(Map<String, String[]> requestMap) {
		LogFilterChangeInfo info = new LogFilterChangeInfo();
		String msId = requestMap.get("id")[0];
		String maintenType = requestMap.get("maintenType")[0];
		String operateBy = requestMap.get("operateBy")[0];
		String verifyBy = requestMap.get("verifyBy")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (maintenType.trim().equals("")) {
			info.setMaintenType(null);
		} else {
			info.setMaintenType(UUID.fromString(requestMap.get("maintenType")[0]));
		}
		if (operateBy.trim().equals("")) {
			info.setOperateBy(null);
		} else {
			info.setOperateBy(UUID.fromString(requestMap.get("operateBy")[0]));
		}
		if (verifyBy.trim().equals("")) {
			info.setVerifyBy(null);
		} else {
			info.setVerifyBy(UUID.fromString(requestMap.get("verifyBy")[0]));
		}
		if (!requestMap.get("recordDate")[0].equals("") && !requestMap.get("recordDate")[0].equals(null)) {
			info.setRecordDate((Date) Utility.getSqlDate(requestMap.get("recordDate")[0]));
		} else {
			info.setRecordDate(null);
		}
		info.setRecordTime(requestMap.get("recordTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveFilterChangeInfo(info);

	}

	public void savePhMeter(Map<String, String[]> requestMap) {
		LogPhMeterInfo info = new LogPhMeterInfo();
		String msId = requestMap.get("id")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcReferenceId.trim().equals("")) {
			info.setQcReferenceId(null);
		} else {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		}

		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setPhResult(requestMap.get("phResult")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.savePhMeterInfo(info);
	}

	public void saveFtir(Map<String, String[]> requestMap) {
		LogFtirInfo info = new LogFtirInfo();
		String msId = requestMap.get("id")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcReferenceId.trim().equals("")) {
			info.setQcReferenceId(null);
		} else {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		}
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveFtirInfo(info);

	}

	public void saveTempHumidityRecord(Map<String, String[]> requestMap) {
		LogTempeHumidityRecordInfo info = new LogTempeHumidityRecordInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		TempAndHumiditySetupInfo humiditySetupInfos=null;
		humiditySetupInfos = allSetupRepository.getTempHmdtyStupInfosByStatusAndEquId("Y",equipmentId);
		System.out.println(humiditySetupInfos.getCorrectionValFrHumidity());
		String correctHumidity = humiditySetupInfos.getCorrectionValFrHumidity();
		String correctTemp = humiditySetupInfos.getCorrectionValFrTemp();

		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(equipmentId));
		info.setBeforeRelativeHumidity(requestMap.get("beforeRelativeHumidity")[0]);
		info.setAfterRelativeHumidity(provideEquation(correctHumidity, requestMap.get("beforeRelativeHumidity")[0]));
		info.setBeforeTemp(requestMap.get("beforeTemp")[0]);
		info.setAfterTemp((provideEquation(correctTemp, requestMap.get("beforeTemp")[0])));
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveTempHumidityRecord(info);
	}

	private String provideEquation(String recourdValue, String currentVal) {
		String result = "";
		char equation = '+';
		for (int i = 0; i < recourdValue.length(); i++) {
			if (recourdValue.charAt(i) != '(' && recourdValue.charAt(i) != ')' && recourdValue.charAt(i) != '+'
					&& recourdValue.charAt(i) != '-' && recourdValue.charAt(i) != ' ') {
				result = result + recourdValue.charAt(i);
				System.out.println(recourdValue.charAt(i));
			}
			if (recourdValue.charAt(i) == '+' || recourdValue.charAt(i) == '-') {
				equation = recourdValue.charAt(i) == '+' ? '+' : '-';
			}
		}
		if (equation == '+') {
			Float.parseFloat(currentVal);
			String cResult = String.format("%.1f", (Float.parseFloat(currentVal) + Float.parseFloat(result)));
			return cResult;
		} else if (equation == '-') {
			String cResult = String.format("%.1f", (Float.parseFloat(currentVal) - Float.parseFloat(result)));
			return cResult;
		}
		return null;

	}

	public void saveElectricOven(Map<String, String[]> requestMap) {
		LogElectricOvenInfo info = new LogElectricOvenInfo();
		String msId = requestMap.get("id")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];

		info.setId(provideStrToUUID(msId));
		info.setQcReferenceId(provideStrToUUID(qcReferenceId));
		info.setEquipmentId(provideStrToUUID(equipmentId));
		info.setTemperature(requestMap.get("temperature")[0]);
		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveElectricOvenInfo(info);
	}

	public void discardedQuantityRefrigeratorInfos(Map<String, String[]> requestMap) {
		LogRefrigeratorInfo info = new LogRefrigeratorInfo();
		String msId = requestMap.get("idOut")[0];
		String diascardedQty = requestMap.get("discardQtyOut")[0];
		String qty = requestMap.get("qtyOut")[0];
		String unitId = requestMap.get("disUnitId")[0];
		info.setId(provideStrToUUID(msId));
		info.setDiscardQty(diascardedQty);
		info.setDisUnitId(provideStrToUUID(unitId));
		
		int remainQry = Integer.parseInt(qty) != 0 ? Integer.parseInt(qty) - Integer.parseInt(diascardedQty)
				: Integer.parseInt(qty);
		info.setQty(Integer.toString(remainQry));
		chemiRepository.discardedQuantityRefrigeratorInfos(info);

	}

	public void saveRefrigeratorInfo(Map<String, String[]> requestMap) {
		LogRefrigeratorInfo info = new LogRefrigeratorInfo();
		String msId = requestMap.get("id")[0];
		String sampleTypeId = requestMap.get("sampleTypeId")[0];
		String qcRefId = requestMap.get("qcRefId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		String unitId = requestMap.get("unitId")[0];
		
		String productId = requestMap.get("productId")[0];
		info.setMaterialId(provideStrToUUID(productId));
		
		
		info.setId(provideStrToUUID(msId));
		info.setSampleTypeId(provideStrToUUID(sampleTypeId));
		info.setQcRefId(provideStrToUUID(qcRefId));
		info.setEquipmentId(provideStrToUUID(equipmentId));
		info.setUnitId(provideStrToUUID(unitId));
		info.setQty(requestMap.get("qty")[0]);
	
		
		info.setRemarks(requestMap.get("remarks")[0]);
		
		chemiRepository.saveRefrigeratorInfo(info);

	}

	public void saveAnalyticalBalance(Map<String, String[]> requestMap) {
		LogAnalyticalBalanceInfo info = new LogAnalyticalBalanceInfo();
		String msId = requestMap.get("id")[0];
		String qcRefId = requestMap.get("qcRefId")[0];
		String sampleTypeId = requestMap.get("sampleTypeId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcRefId.trim().equals("")) {
			info.setQcRefId(null);
		} else {
			info.setQcRefId(UUID.fromString(requestMap.get("qcRefId")[0]));
		}
		if (sampleTypeId.trim().equals("")) {
			info.setSampleTypeId(null);
		} else {
			info.setSampleTypeId(UUID.fromString(requestMap.get("sampleTypeId")[0]));
		}

		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setQty(requestMap.get("qty")[0]);
		info.setCheckType(requestMap.get("checkType")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);

		chemiRepository.saveAnalyticalBalanceInfo(info);

	}

	public void saveDesiccatorLog(Map<String, String[]> requestMap) {
		LogDesiccatorInfo info = new LogDesiccatorInfo();
		String msId = requestMap.get("id")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcReferenceId.equals("")) {
			info.setQcReferenceId(null);
		} else {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		}
		if (requestMap.get("keepDate")[0] !=null && !requestMap.get("keepDate")[0].equals("") ) {
			info.setKeepDate((Date) Utility.getSqlDate(requestMap.get("keepDate")[0]));
		} else {
			info.setKeepDate(null);
		}
		if (requestMap.get("outDate")[0] !=null && !requestMap.get("outDate")[0].equals("") ) {
			info.setOutDate((Date) Utility.getSqlDate(requestMap.get("outDate")[0]));
		} else {
			info.setOutDate(null);
		}
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveDesiccatorInfo(info);

	}

	public void saveDryCabinet(Map<String, String[]> requestMap) {
		LogDryCabinetInfo info = new LogDryCabinetInfo();
		String msId = requestMap.get("id")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcReferenceId.trim().equals("")) {
			info.setQcReferenceId(null);
		} else {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		}
		if (!requestMap.get("keepDate")[0].equals("") && !requestMap.get("keepDate")[0].equals(null)) {
			info.setKeepDate((Date) Utility.getSqlDate(requestMap.get("keepDate")[0]));
		} else {
			info.setKeepDate(null);
		}
		if (!requestMap.get("outDate")[0].equals("") && !requestMap.get("outDate")[0].equals(null)) {
			info.setOutDate((Date) Utility.getSqlDate(requestMap.get("outDate")[0]));
		} else {
			info.setOutDate(null);
		}
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveDryCabinet(info);

	}

	public void saveVerificationDissolution(Map<String, String[]> requestMap, UUID idRandom) {
		LogVerificationDissolutionInfo info = new LogVerificationDissolutionInfo();
		String msId = requestMap.get("id")[0];
		String qcReferenceId = requestMap.get("qcReferenceId")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (qcReferenceId.trim().equals("")) {
			info.setQcReferenceId(null);
		} else {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		}

		chemiRepository.saveVerificationDissolution(info, idRandom);

	}

	public void saveColumnReceiveDistributeInfo(Map<String, String[]> requestMap) {
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
		String msId = requestMap.get("id")[0];
		String colId = requestMap.get("colId")[0];
		String equiId = requestMap.get("equipmentId")[0];
		String roomId = requestMap.get("rommNo")[0];
		String rack = requestMap.get("rackNo")[0];

		info.setId(provideStrToUUID(msId));
		info.setColId(provideStrToUUID(colId));
		info.setEquipmentId(provideStrToUUID(equiId));
		info.setRommNo(provideStrToUUID(roomId));
		info.setRackNo(provideStrToUUID(rack));
		info.setPartNo(requestMap.get("partNo")[0]);
		info.setSerialNo(requestMap.get("serialNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		
		info.setColumnIdNew(requestMap.get("columnIdNew")[0]);
		
		chemiRepository.saveColumnRcvDistInfo(info);

	}

	public void addColumnOpenExpire(Map<String, String[]> requestMap) {
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
		String msId = requestMap.get("openId")[0];
		
		info.setId(provideStrToUUID(msId));
		info.setOpenDate(requestMap.get("openDate")[0]);
		info.setExpireDate(requestMap.get("expireDate")[0]);
		chemiRepository.addColumnOpenExpire(info);

	}
	
	public void issueColumnReceiveDistribute(Map<String, String[]> requestMap) {
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();

		String locId = requestMap.get("locationId")[0];
		String msId = requestMap.get("id")[0];

		info.setId(provideStrToUUID(msId));
		info.setLocationId(provideStrToUUID(locId));
		chemiRepository.issueColumnReceiveDistribute(info);

	}
	public void columnRecord(Map<String, String[]> requestMap) {
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
		String msId = requestMap.get("id")[0];
		info.setTheoriticalPlate(requestMap.get("theoreticalPlateRecord")[0]);
		info.setId(provideStrToUUID(msId));
        chemiRepository.columnRecord(info);

	}
	public void saveColumnPerformancePendingRecord(Map<String, String[]> requestMap) {
		LogColumnPerformanceRecordInfo info = new LogColumnPerformanceRecordInfo();
		String msId = requestMap.get("id")[0];
		info.setTf(requestMap.get("tf")[0]);
		info.setTp(requestMap.get("tp")[0]);
		info.setRsd(requestMap.get("rsd")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setId(provideStrToUUID(msId));
	    chemiRepository.saveColumnPerformancePending(info);

	}
	public void saveComparativeDissolutionStudyPending(Map<String, String[]> requestMap) {
		LogComparativeDissolutionDutyInfo info = new LogComparativeDissolutionDutyInfo();
		String flag = requestMap.get("updateFlag")[0];
		String msId = requestMap.get("id")[0];
		String analystId = requestMap.get("analystId")[0];
		if (analystId.trim().equals("")) {
			info.setAnalystBy(null);
		} else {
			info.setAnalystBy(UUID.fromString(requestMap.get("analystId")[0]));
		}
		
		info.setId(provideStrToUUID(msId));
		info.setFlag(flag);
		chemiRepository.saveComparativeDissolutionStudyPending(info);

	}
	
	public void saveComparativeDissolutionStudyResultEntry(Map<String, String[]> requestMap) {
		LogComparativeDissolutionDutyInfo info = new LogComparativeDissolutionDutyInfo();
		String flag = requestMap.get("updateFlag")[0];
		String msId = requestMap.get("id")[0];
		info.setResultComp(requestMap.get("resultEntry")[0]);
    	info.setRemarks(requestMap.get("remarks")[0]);
    	
    	info.setFlag(flag);
		info.setId(provideStrToUUID(msId));
	    chemiRepository.saveComparativeDissolutionStudyResultEntry(info);

	}
	public void SaveComparativeDissolutionStudy(Map<String, String[]> requestMap) {
		LogComparativeDissolutionDutyInfo info = new LogComparativeDissolutionDutyInfo();
		String manufacturerID = requestMap.get("manufacturerId")[0];
		String msId = requestMap.get("id")[0];
		
		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}

		info.setProductName(requestMap.get("productName")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		if (manufacturerID.trim().equals("")) {
			info.setManufacturerId(null);
		} else {
			info.setManufacturerId(UUID.fromString(manufacturerID));
		}
	    chemiRepository.SaveComparativeDissolutionStudy(info);

	}
	public void updateColumnRecord(Map<String, String[]> requestMap) {
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
		String msId = requestMap.get("id")[0];
		info.setTheoriticalPlate(requestMap.get("theoreticalPlate")[0]);
		info.setId(provideStrToUUID(msId));
        chemiRepository.updateColumnRecord(info);

	}
	public void updateColumnPerformanceRecord(Map<String, String[]> requestMap) {
		LogColumnPerformanceRecordInfo info = new LogColumnPerformanceRecordInfo();
		String msId = requestMap.get("id")[0];
		info.setTf(requestMap.get("tf")[0]);
		info.setTp(requestMap.get("tp")[0]);
		info.setRsd(requestMap.get("rsd")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
        info.setId(provideStrToUUID(msId));
        Log.info(msId);
        System.out.println(msId);
        chemiRepository.updateColumnPerformanceRecord(info);

	}
	
	
	public void issueColumnReceiveDistributeAdd(String id) {
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
		info.setId(provideStrToUUID(id));
		chemiRepository.issueColumnReceiveDistributeAdd(info);

	}

	public void saveColumnLogInfo(Map<String, String[]> requestMap) {
		LogColumnInfo info = new LogColumnInfo();
		String msId = requestMap.get("id")[0];
		String doneBy = requestMap.get("doneBy")[0];
		String verifyBy = requestMap.get("verifyBy")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (doneBy.trim().equals("")) {
			info.setDoneBy(null);
		} else {
			info.setDoneBy(UUID.fromString(requestMap.get("doneBy")[0]));
		}
		if (verifyBy.trim().equals("")) {
			info.setVerifyBy(null);
		} else {
			info.setVerifyBy(UUID.fromString(requestMap.get("verifyBy")[0]));
		}
		if (!requestMap.get("openDate")[0].equals("") && !requestMap.get("openDate")[0].equals(null)) {
			info.setOpenDate((Date) Utility.getSqlDate(requestMap.get("openDate")[0]));
		} else {
			info.setOpenDate(null);
		}
		if (!requestMap.get("expireDate")[0].equals("") && !requestMap.get("expireDate")[0].equals(null)) {
			info.setExpireDate((Date) Utility.getSqlDate(requestMap.get("expireDate")[0]));
		} else {
			info.setExpireDate(null);
		}
		info.setColId(requestMap.get("colId")[0]);
		info.setColName(requestMap.get("colName")[0]);
		info.setColSize(requestMap.get("colSize")[0]);
		info.setComposition(requestMap.get("composition")[0]);
		info.setPartNo(requestMap.get("partNo")[0]);
		info.setSerialNo(requestMap.get("serialNo")[0]);
		info.setLocation(requestMap.get("location")[0]);
		info.setTheorePlate(requestMap.get("theorePlate")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveColumnInfo(info);

	}

	public void saveTempHumidity(Map<String, String[]> requestMap) {
		LogTempHumidityInfo info = new LogTempHumidityInfo();
		String msId = requestMap.get("id")[0];
		String doneBy = requestMap.get("doneBy")[0];
		String verifyBy = requestMap.get("verifyBy")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (doneBy.trim().equals("")) {
			info.setDoneBy(null);
		} else {
			info.setDoneBy(UUID.fromString(requestMap.get("doneBy")[0]));
		}
		if (verifyBy.trim().equals("")) {
			info.setVerifyBy(null);
		} else {
			info.setVerifyBy(UUID.fromString(requestMap.get("verifyBy")[0]));
		}
		if (!requestMap.get("recordDate")[0].equals("") && !requestMap.get("recordDate")[0].equals(null)) {
			info.setRecordDate((Date) Utility.getSqlDate(requestMap.get("recordDate")[0]));
		} else {
			info.setRecordDate(null);
		}
		info.setRecordTime(requestMap.get("recordTime")[0]);
		info.setBeforeTemp(requestMap.get("beforeTemp")[0]);
		info.setAfterTemp(requestMap.get("afterTemp")[0]);
		info.setRelHumidity(requestMap.get("relHumidity")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveTempHumidityInfo(info);

	}

	public List<LogWaterPurificationInfo> getLogWaterPurification(String eqipId, String verifyStatus) {
		return chemiRepository.findLogWaterPurification(eqipId, verifyStatus);
	}

	public List<LogAnalystValidationInfo> getLogAnalystValidation(String verifyStatus) {
		return chemiRepository.findLogAnalystValidation(verifyStatus);
	}

	public List<LogWaterSamplingInfo> getLogWaterSamplingInfo(String eqipId, String verifyStatus) {
		return chemiRepository.findLogWaterSamplingInfo(eqipId, verifyStatus);
	}
	public List<LogComparativeDissolutionDutyInfo> getLogComparativeDissolutionDutyInfo( String distribute_status ,String resulStatus) {
		return chemiRepository.findLogComparativeDissolutionDutyInfo( distribute_status ,resulStatus);
	}

	public List<LogAllottedSampleInfo> getLogAllottedSampleInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findLogAllottedSampleInfos(eqipId, verifyStatus);
	}
	
	public List<LogOutofTrend> getLogOutofTrendInfo(String eqipId, String verifyStatus) {
		return chemiRepository.findLogOutofTrend(eqipId, verifyStatus);
	}

	public List<LogDeHumidifierInfo> getLogDeHumidifierInfo(String eqipId, String cleanStatus, String verifyStatus) {
		return chemiRepository.findLogDeHumidifier(eqipId,cleanStatus, verifyStatus);
	}

	public List<LogTOCInfo> getLogTOCInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findLogTOCInfos(eqipId, verifyStatus);
	}

	public List<LogSampleLabelInfo> getLogSampleLabelInfos( String verifyStatus) {
		return chemiRepository.findLogSampleLabelInfos( verifyStatus);
	}

	public List<LogAtomicAbsorptionInfo> getLogAtomicAbsorptionInfos(String eqipId, String verifyStatus) {
		return chemiRepository.findLogAtomicAbsorptionInfos(eqipId, verifyStatus);
	}

	public List<LogWaterPurificationInfo> getLogWaterPurificationWithParam(String equipmentId) {
		return chemiRepository.findLogWaterPurificationWithParam(equipmentId);
	}

	public void saveWaterPurification(Map<String, String[]> requestMap) {
		LogWaterPurificationInfo info = new LogWaterPurificationInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		String unitId = requestMap.get("unitId")[0];

		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (unitId.trim().equals("")) {
			info.setUnitId(null);
		} else {
			info.setUnitId(UUID.fromString(requestMap.get("unitId")[0]));
		}

		info.setQuantity(requestMap.get("quantity")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveWaterPurification(info);
	}

	public void saveAnalystValidation(Map<String, String[]> requestMap) {
		LogAnalystValidationInfo info = new LogAnalystValidationInfo();
		String msId = requestMap.get("id")[0];
		String analystId = requestMap.get("analystId")[0];
		
		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}
		if (analystId.trim().equals("")) {
			info.setAnalystId(null);
		} else {
			info.setAnalystId(UUID.fromString(requestMap.get("analystId")[0]));
		}
		if (!requestMap.get("dateOfCompetency")[0].equals("") && !requestMap.get("dateOfCompetency")[0].equals(null)) {
			info.setDateOfCompetency((Date) Utility.getSqlDate(requestMap.get("dateOfCompetency")[0]));
		} else {
			info.setDateOfCompetency(null);
		}
		if (!requestMap.get("nextDateOfCompetency")[0].equals("")
				&& !requestMap.get("nextDateOfCompetency")[0].equals(null)) {
			info.setNextDateOfCompetency((Date) Utility.getSqlDate(requestMap.get("nextDateOfCompetency")[0]));
		} else {
			info.setNextDateOfCompetency(null);
		}
		
		info.setAreaOfCompetency(requestMap.get("areaOfCompetency")[0]);
		info.setStatus(requestMap.get("status")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setUdAnalystId(requestMap.get("udAnalystId")[0]);

		chemiRepository.saveAnalystValidation(info);
	}

	public void saveWaterSampling(Map<String, String[]> requestMap) {
		LogWaterSamplingInfo info = new LogWaterSamplingInfo();
		String msId = requestMap.get("id")[0];
		String locationId = requestMap.get("locationId")[0];
		String waterTypeId = requestMap.get("waterTypeId")[0];

		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}
		if (locationId.trim().equals("")) {
			info.setLocationId(null);
		} else {
			info.setLocationId(UUID.fromString(requestMap.get("locationId")[0]));
		}
		if (waterTypeId.trim().equals("")) {
			info.setWaterTypeId(null);
		} else {
			info.setWaterTypeId(UUID.fromString(requestMap.get("waterTypeId")[0]));
		}

		info.setSampleId(requestMap.get("sampleId")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveWaterSampling(info);
	}

	public void saveAlloteeSample(Map<String, String[]> requestMap) {
		LogAllottedSampleInfo info = new LogAllottedSampleInfo();
		String msId = requestMap.get("id")[0];
		String analystId = requestMap.get("analystId")[0];
		
		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}
		if (analystId.trim().equals("")) {
			info.setAnalystId(null);
		} else {
			info.setAnalystId(UUID.fromString(requestMap.get("analystId")[0]));
		}
		info.setProductName(requestMap.get("productName")[0]);
		info.setTestMethodName(requestMap.get("testMethodName")[0]);
		info.setDocumentCode(requestMap.get("documentCode")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveAlloteeSample(info);
	}
	public void saveOutOfTrend(Map<String, String[]> requestMap) {
		LogOutofTrend info = new LogOutofTrend();
		String msId = requestMap.get("id")[0];
		String productId = requestMap.get("productId")[0];
		
		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}
		if (productId.trim().equals("")) {
			info.setProductId(null);
		} else {
			info.setProductId(UUID.fromString(requestMap.get("productId")[0]));
		}
		
		if (!requestMap.get("ootObservationDate")[0].equals("") && !requestMap.get("ootObservationDate")[0].equals(null)) {
			info.setOotObservationDate((Date) Utility.getSqlDate(requestMap.get("ootObservationDate")[0]));
		} else {
			info.setOotObservationDate(null);
		}
		if (!requestMap.get("investigationDate")[0].equals("") && !requestMap.get("investigationDate")[0].equals(null)) {
			info.setInvestigationDate((Date) Utility.getSqlDate(requestMap.get("investigationDate")[0]));
		} else {
			info.setInvestigationDate(null);
		}
		
		if (!requestMap.get("classOutDate")[0].equals("") && !requestMap.get("classOutDate")[0].equals(null)) {
			info.setClassOutDate((Date) Utility.getSqlDate(requestMap.get("classOutDate")[0]));
		} else {
			info.setClassOutDate(null);
		}
		
		info.setOotNo(requestMap.get("ootNo")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setTest(requestMap.get("test")[0]);
		chemiRepository.saveOutOfTrend(info);
	}

	public void saveDeHumidifier(Map<String, String[]> requestMap) {
		LogDeHumidifierInfo info = new LogDeHumidifierInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveDeHumidifier(info);

	}
	public void cleanDeHumidifierInfo(Map<String, String[]> requestMap) {
		LogDeHumidifierInfo info = new LogDeHumidifierInfo();
		String msId = requestMap.get("id")[0];
		String cleanBy		= requestMap.get("cleanBy")[0];		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
				
		if (cleanBy.trim().equals("")) {
			info.setCleanBy(null);
		   }else {
		   	 info.setCleanBy(UUID.fromString(requestMap.get("cleanBy")[0])); 
		}
		chemiRepository.cleanDeHumidifierInfo(info);
	
	}

	public void saveTOCInfo(Map<String, String[]> requestMap) {
		LogTOCInfo info = new LogTOCInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		
		
		info.setSampleName(requestMap.get("sampleName")[0]);
		

		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}

		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		info.setResult(requestMap.get("result")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveTOC(info);

	}

	public void saveSampleLabelnfo(Map<String, String[]> requestMap) {
		LogSampleLabelInfo info = new LogSampleLabelInfo();
		String msId = requestMap.get("id")[0];
	//	String equipmentId = requestMap.get("equipmentId")[0];
		String sampleNameId = requestMap.get("sampleNameId")[0];
		String unitId = requestMap.get("unitId")[0];

		if (sampleNameId.trim().equals("")) {
			info.setSampleNameId(null);
		} else {
			info.setSampleNameId(UUID.fromString(requestMap.get("sampleNameId")[0]));
		}

		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}

		if (unitId.trim().equals("")) {
			info.setUnitId(null);
		} else {
			info.setUnitId(UUID.fromString(requestMap.get("unitId")[0]));
		}

/*		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}*/

		info.setQty(requestMap.get("qty")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveSampleLabel(info);

	}

	public void saveAtomicAbsorptionnfo(Map<String, String[]> requestMap) {
		LogAtomicAbsorptionInfo info = new LogAtomicAbsorptionInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		String sampleNameId = requestMap.get("sampleNameId")[0];

		if (sampleNameId.trim().equals("")) {
			info.setSampleNameId(null);
		} else {
			info.setSampleNameId(UUID.fromString(requestMap.get("sampleNameId")[0]));
		}

		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));

		} else {
			info.setId(null);
		}

		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setHno3No(requestMap.get("hno3No")[0]);
		info.setH2oNo(requestMap.get("h2oNo")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setFirstTime(requestMap.get("firstTime")[0]);
		info.setSecondTime(requestMap.get("secondTime")[0]);
		info.setTotalAnalystTime(requestMap.get("totalAnalystTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveAtomicAbsorption(info);

	}

	public void saveDissolutionInfo(Map<String, String[]> requestMap) {
		LogDissolutionInfo info = new LogDissolutionInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveDissolutionInfo(info);

	}

	public void saveSonicatorBathInfo(Map<String, String[]> requestMap) {
		LogSonicatorBathInfo info = new LogSonicatorBathInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveSonicatorBathInfo(info);

	}

	public void saveTempRefrigeratorInfo(Map<String, String[]> requestMap) {
		LogTempRefrigeratorInfo info = new LogTempRefrigeratorInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		
		//=========================Start================
		System.out.println(" Eaquipment Info : "+equipmentId);
		RefrigeratorTempChemiSetupInfo refrigeratorInfo=null;
		refrigeratorInfo = allSetupService.getRefrigeratorTempChemiSetupInfo("Y",equipmentId);
		String correctTemp = refrigeratorInfo.getTemperature();
        info.setTempAfter((provideEquation(correctTemp, requestMap.get("tempBefore")[0])));
    //End
	//	info.setTempAfter(requestMap.get("tempAfter")[0]);
		info.setTempBefore(requestMap.get("tempBefore")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveTempRefrigeratorInfo(info);

	}

	public void saveKarlFisherInfo(Map<String, String[]> requestMap) {
		LogKarlFischerInfo info = new LogKarlFischerInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setKftResult(requestMap.get("kftResult")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		chemiRepository.saveKarlFischerInfo(info);

	}

	public void saveBiologicalSafetyInfo(Map<String, String[]> requestMap) {
		LogBiologicalSafetyInfo info = new LogBiologicalSafetyInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		chemiRepository.saveBiologicalSafetyInfo(info);

	}

	public void saveMuffleFuranceInfo(Map<String, String[]> requestMap) {
		LogMuffleFurnaceInfo info = new LogMuffleFurnaceInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setTemperature(requestMap.get("temperature")[0]);
		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setResult(requestMap.get("result")[0]);
		chemiRepository.saveMuffleFurnaceInfo(info);

	}

	public void saveMeltingPointInfo(Map<String, String[]> requestMap) {
		LogMeltingPointInfo info = new LogMeltingPointInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setResult(requestMap.get("result")[0]);
		chemiRepository.saveMeltingPoint(info);

	}

	public void saveAutomaticPolarizationInfo(Map<String, String[]> requestMap) {
		LogAutomaticPolarimeterInfo info = new LogAutomaticPolarimeterInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setResult(requestMap.get("result")[0]);
		chemiRepository.saveAutomaticPolarization(info);

	}

	public void saveBiochemicalOxyzenInfo(Map<String, String[]> requestMap) {
		LogBiochemicalOxygenInfo info = new LogBiochemicalOxygenInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String sampleNameId = requestMap.get("sampleNameId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (sampleNameId.trim().equals("")) {
			info.setSampleNameId(null);
		} else {
			info.setSampleNameId(UUID.fromString(requestMap.get("sampleNameId")[0]));
		}

		if (!requestMap.get("startDate")[0].equals("") && !requestMap.get("startDate")[0].equals(null)) {
			info.setStartDate((Date) Utility.getSqlDate(requestMap.get("startDate")[0]));
		} else {
			info.setStartDate(null);
		}
		if (!requestMap.get("endDate")[0].equals("") && !requestMap.get("endDate")[0].equals(null)) {
			info.setEndDate((Date) Utility.getSqlDate(requestMap.get("endDate")[0]));
		} else {
			info.setEndDate(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setResult(requestMap.get("result")[0]);
		chemiRepository.saveBiochemicalOxyzen(info);

	}

	public void saveFumeHoodInfo(Map<String, String[]> requestMap) {
		LogFumeHoodInfo info = new LogFumeHoodInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setPurpose(requestMap.get("purpose")[0]);
		chemiRepository.saveFumeHood(info);

	}

	public void saveLaboratoryHeaterInfo(Map<String, String[]> requestMap) {
		LogLaboratoryHeaterInfo info = new LogLaboratoryHeaterInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setTemperature(requestMap.get("temperature")[0]);
		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		chemiRepository.saveLaboratoryHeaterInfo(info);

	}

	public void saveGasChromatographyInfo(Map<String, String[]> requestMap) {
		LogGasChromatographyInfo info = new LogGasChromatographyInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String columnId = requestMap.get("columnId")[0];
		if (columnId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setColumnId(UUID.fromString(requestMap.get("columnId")[0]));
		}

		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setTotalAnalystTime(requestMap.get("totalAnalystTime")[0]);

		chemiRepository.saveGasChromatographyInfo(info);

	}

	public void saveRefractometerInfo(Map<String, String[]> requestMap) {
		LogRefractometerInfo info = new LogRefractometerInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}

		info.setKftResult(requestMap.get("kftResult")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		chemiRepository.saveRefractometerInfo(info);

	}

	public void saveSpectrophotometerInfo(Map<String, String[]> requestMap) {
		LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();
		String equipmentId = requestMap.get("equipmentId")[0];

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}
		if (requestMap.get("qcReferenceId")[0] != null && !requestMap.get("qcReferenceId")[0].isEmpty()) {
			info.setQcReferenceId(UUID.fromString(requestMap.get("qcReferenceId")[0]));
		} else {
			info.setQcReferenceId(null);
		}
		if (equipmentId.trim().equals("")) {
			info.setEquipmentId(null);
		} else {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		}
		info.setRemarks(requestMap.get("remarks")[0]);
		chemiRepository.saveSpectrophotometerInfo(info);

	}

	public void verifyWaterPurification(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		
		LogWaterPurificationInfo info = new LogWaterPurificationInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyWaterPurification(info);

			}
		}

	}

	public void verifyAanalystValidation(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		LogAnalystValidationInfo info = new LogAnalystValidationInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyAanalystValidation(info);

			}
		}

	}
	
	public void verifyColumnRecord(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.verifyColumnRecord(info);

			}
		}

	}
	public void verifyColumnPerformanceRecord(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogColumnPerformanceRecordInfo info = new LogColumnPerformanceRecordInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyColumnPerformanceRecord(info);

			}
		}

	}

	public void verifyWaterSamplingInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogWaterSamplingInfo info = new LogWaterSamplingInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyWaterSamplingInfo(info);

			}
		}

	}

	public void verifyDeHumidifierInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogDeHumidifierInfo info = new LogDeHumidifierInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyDeHumidifierInfo(info);

			}
		}

	}

	public void verifyTOCInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogTOCInfo info = new LogTOCInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyTOCInfo(info);

			}
		}

	}

	public void verifySampleLabelInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogSampleLabelInfo info = new LogSampleLabelInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifySampleLabelInfo(info);

			}
		}

	}

	public void verifyAbsorptionSpectrometerInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogAtomicAbsorptionInfo info = new LogAtomicAbsorptionInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyAbsorptionSpectrometerInfo(info);
			}
		}
	}

	public void verifyAreaCleaningInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogAreaCleanInfo info = new LogAreaCleanInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyAreaCleaningInfo(info);

			}
		}

	}

	public void verifyDesiccator(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogDesiccatorInfo info = new LogDesiccatorInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyDesiccator(info);

			}
		}

	}

	public void verifyDryCabinet(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogDryCabinetInfo info = new LogDryCabinetInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyDryCabinet(info);

			}
		}

	}

	public void verifyVerificationDissolution(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogVerificationDissolutionInfo info = new LogVerificationDissolutionInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.verifyVerificationDissolution(info);

			}
		}

	}

	public void verifyHPLC(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogHplcInfo info = new LogHplcInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyHPLC(info);

			}
		}

	}
	public void verifySampleReceiving(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		CommonInfo info = new CommonInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.verifySampleReceiving(info);

			}
		}

	}


	public void verifyPhMeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		LogPhMeterInfo info = new LogPhMeterInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyPhMeter(info);

			}
		}

	}

	public void cleanPhMeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogPhMeterInfo info = new LogPhMeterInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanPhMeter(info);

			}
		}

	}

	public void cleanCarlFischer(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogKarlFischerInfo info = new LogKarlFischerInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanCarlFischer(info);

			}
		}

	}

	public void verifyKarlFischer(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		LogKarlFischerInfo info = new LogKarlFischerInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyKarlFischer(info);

			}
		}

	}

	public void cleanRefractometer(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogRefractometerInfo info = new LogRefractometerInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanRefractometer(info);

			}
		}

	}

	public void verifyBiologicalSafety(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogBiologicalSafetyInfo info = new LogBiologicalSafetyInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setRemarks(remarks[i]);
				info.setId(UUID.fromString(id[i]));
				chemiRepository.verifyBiologicalSafety(info);

			}
		}

	}

	public void cleanBiologicalSafety(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogBiologicalSafetyInfo info = new LogBiologicalSafetyInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanBiologicalSafety(info);

			}
		}

	}

	public void verifyRefractometer(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogRefractometerInfo info = new LogRefractometerInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
						
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyRefractometer(info);

			}
		}

	}

	public void cleanFtir(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogFtirInfo info = new LogFtirInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanFtir(info);

			}
		}

	}

	public void verifyAnalyticalBalance(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogAnalyticalBalanceInfo info = new LogAnalyticalBalanceInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyAnalyticalBalance(info);

			}
		}

	}

	public void cleanConductivityMeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMultiParameterInfo info = new LogMultiParameterInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanConductivityMeter(info);

			}
		}

	}

	public void verifyConductivityMeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		LogMultiParameterInfo info = new LogMultiParameterInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyConductivityMeter(info);

			}
		}

	}

	public void cleanMoistureAnalyzer(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMoistureAnalyzerInfo info = new LogMoistureAnalyzerInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanMoistureAnalyzer(info);

			}
		}

	}

	public void verifyMoistureAnalyzer(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMoistureAnalyzerInfo info = new LogMoistureAnalyzerInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyMoistureAnalyzer(info);

			}
		}

	}

	public void cleanDisIntegrationInfo(Map<String, String[]> requestMap) {
		LogDisintegrationTestInfo info = new LogDisintegrationTestInfo();
		String msId = requestMap.get("id")[0];
		String cleanBy		= requestMap.get("cleanBy")[0];		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
				
		if (cleanBy.trim().equals("")) {
			info.setCleanBy(null);
		   }else {
		   	 info.setCleanBy(UUID.fromString(requestMap.get("cleanBy")[0])); 
		}
		chemiRepository.cleanDisIntegrationInfo(info);
	}

	public void verifyDisIntegrationInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogDisintegrationTestInfo info = new LogDisintegrationTestInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyDisIntegrationInfo(info);
			}
		}

	}

	public void receiveNoteBook(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogNoteBookControlInfo info = new LogNoteBookControlInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.receiveNoteBook(info);
			}
		}

	}

	public void returnNoteBook(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		LogNoteBookControlInfo info = new LogNoteBookControlInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.returnNoteBook(info);
			}
		}

	}

	public void cleanAnalyticalBalance(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogAnalyticalBalanceInfo info = new LogAnalyticalBalanceInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanAnalyticalBalance(info);
			}
		}

	}

	public void verifyFtir(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		LogFtirInfo info = new LogFtirInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyFtir(info);
			}
		}

	}

	public void cleanDissolution(Map<String, String[]> requestMap) {
		LogDissolutionInfo info = new LogDissolutionInfo();
		String msId = requestMap.get("id")[0];
		String cleanBy		= requestMap.get("cleanBy")[0];		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
				
		if (cleanBy.trim().equals("")) {
			info.setCleanBy(null);
		   }else {
		   	 info.setCleanBy(UUID.fromString(requestMap.get("cleanBy")[0])); 
		}
		chemiRepository.cleanDissolution(info);
	}
	public void cleanAreaClean(Map<String, String[]> requestMap) {
		LogAreaCleanInfo info = new LogAreaCleanInfo();
		String msId = requestMap.get("id")[0];
		String cleanBy		= requestMap.get("cleanBy")[0];		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
				
		if (cleanBy.trim().equals("")) {
			info.setCleanBy(null);
		   }else {
		   	 info.setCleanBy(UUID.fromString(requestMap.get("cleanBy")[0])); 
		}
		chemiRepository.cleanAreaClean(info);
	}


	public void verifyDissolution(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		LogDissolutionInfo info = new LogDissolutionInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
		        chemiRepository.verifyDissolution(info);
			}
		}

	}

	public void verifySonicatorBath(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogSonicatorBathInfo info = new LogSonicatorBathInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifySonicatorBath(info);
			}
		}

	}

	public void verifyTempRefrigerator(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogTempRefrigeratorInfo info = new LogTempRefrigeratorInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyTempRefrigerator(info);
			}
		}

	}

	public void verifyDataBAckup(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogDataBackupInfo info = new LogDataBackupInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyDataBackup(info);
			}
		}

	}

	public void cleanMuffleFurnace(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMuffleFurnaceInfo info = new LogMuffleFurnaceInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanMuffleFurnace(info);
			}
		}

	}

	public void verifyMuffleFurnace(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMuffleFurnaceInfo info = new LogMuffleFurnaceInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyMuffleFurnace(info);
			}
		}

	}

	public void cleanMeltingPoint(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMeltingPointInfo info = new LogMeltingPointInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanMeltingPoint(info);
			}
		}
	}

	public void verifyMeltingPoint(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMeltingPointInfo info = new LogMeltingPointInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyMeltingPoint(info);
			}
		}
	}

	public void cleanAutomaticPolarimeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogAutomaticPolarimeterInfo info = new LogAutomaticPolarimeterInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanAutomaticPolarimeter(info);
			}
		}
	}

	public void verifyAutomaticPolarimeter(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogAutomaticPolarimeterInfo info = new LogAutomaticPolarimeterInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyAutomaticPolarimeter(info);

			}
		}
	}

	public void cleanBiochemicalOxygen(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogBiologicalSafetyInfo info = new LogBiologicalSafetyInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanBiochemicalOxygen(info);

			}
		}
	}

	public void verifyBiochemicalOxygen(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogBiologicalSafetyInfo info = new LogBiologicalSafetyInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyBiochemicalOxygen(info);

			}
		}
	}

	public void receiveEHD(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogEhdInfo info = new LogEhdInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.receiveEHD(info);

			}
		}
	}
	public void returnEHD(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogEhdInfo info = new LogEhdInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.returnEHD(info);

			}
		}
	}

	public void verifyEHD(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogEhdInfo info = new LogEhdInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...


		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyEHD(info);

			}
		}
	}

	public void cleanFumeHood(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogFumeHoodInfo info = new LogFumeHoodInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanFumeHood(info);

			}
		}
	}

	public void verifyFumeHood(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogFumeHoodInfo info = new LogFumeHoodInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyFumeHood(info);

			}
		}
	}

	public void cleanLaboratoryHeater(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogLaboratoryHeaterInfo info = new LogLaboratoryHeaterInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				chemiRepository.cleanLaboratoryHeater(info);

			}
		}
	}

	public void verifyLaboratoryHeater(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogLaboratoryHeaterInfo info = new LogLaboratoryHeaterInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyLaboratoryHeater(info);

			}
		}

	}

	public void verifyGasChromatography(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogGasChromatographyInfo info = new LogGasChromatographyInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");//For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				chemiRepository.verifyGasChromatography(info);

			}
		}

	}

	public static UUID provideStrToUUID(String stringId) {
		boolean isEmpty = true;
		if (stringId.trim().equals("") == true) {
			isEmpty = false;
		} else if (stringId == null || stringId.isEmpty()) {
			isEmpty = false;
		}
		return isEmpty ? UUID.fromString(stringId.toString()) : null;
	}

	public List<LogVerificationDissolutionChdInfo> getVerificationDissolutionDetailsById(UUID whRequestId) {
		return chemiRepository.getVerificationDissolutionDetailsById(whRequestId);
	}

	public void saveVerificationDissolutionDetail(Map<String, String[]> requestMap, UUID idRandom) {
		LogVerificationDissolutionChdInfo info = new LogVerificationDissolutionChdInfo();
		String[] chdId = (String[]) requestMap.get("chdId[]");
		String[] vesselNo = (String[]) requestMap.get("vesselNo[]");
		String[] tempBeforeTest = (String[]) requestMap.get("tempBeforeTest[]");
		String[] tempAfterTest = (String[]) requestMap.get("tempAfterTest[]");
		String[] sampleInputTime = (String[]) requestMap.get("sampleInputTime[]");
		String[] sampleWithdrawalTime = (String[]) requestMap.get("sampleWithdrawalTime[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		
			List<LogVerificationDissolutionChdInfo> list = this.getVerificationDissolutionChildInfos(idRandom);
			  if(!list.isEmpty()) {
				  chemiRepository.deleteVerificationDissolutionChdInfo(idRandom); 
				  
			  }
	     

		if (requestMap.containsKey("vesselNo[]")) {
			System.out.println("vesselNo : "+vesselNo.length);
			for (int i = 0; i < vesselNo.length; i++) {
				if (chdId[i].trim().equals("")) {
					info.setChdId(null);
				} else {
					info.setChdId(UUID.fromString(chdId[i].toString()));
				}
				info.setMasterId(UUID.fromString(idRandom.toString()));
				info.setVesselNo(vesselNo[i]);
				info.setTempBeforeTest(tempBeforeTest[i]);
				info.setTempAfterTest(tempAfterTest[i]);
				info.setSampleInputTime(sampleInputTime[i]);
				info.setSampleWithdrawalTime(sampleWithdrawalTime[i]);
				info.setRemarks(remarks[i]);
				chemiRepository.saveVerificationDissolutionDetails(info);

			}
		}

	}

	public void deleteVerificationDissolutionDetail(UUID idRandom) {
		chemiRepository.deleteVerifiDissolutionDetailsInfos(idRandom);
	}
}
