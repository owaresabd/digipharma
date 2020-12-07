package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.LogDisinfectantSolInfo;
import com.pms.model.LogFumigationInfo;
import com.pms.model.LogMCAllottedSampleInfo;
import com.pms.model.LogMCAnalystValidationInfo;
import com.pms.model.LogMCAnalyticalBalanceInfo;
import com.pms.model.LogMCAutoClaveInfo;
import com.pms.model.LogMCColonyCounterInfo;
import com.pms.model.LogMCHotAirOvenInfo;
import com.pms.model.LogMCLaminarAirFlowInfo;
import com.pms.model.LogMCLbFumigationInfo;
import com.pms.model.LogMCLbPreparationInfo;
import com.pms.model.LogMCLbReferenceCultureInfo;
import com.pms.model.LogMCLbTestStrainInfo;
import com.pms.model.LogMCMediaReagentMaterialsInfo;
import com.pms.model.LogMCMicroscopeInfo;
import com.pms.model.LogMCPhMeterInfo;
import com.pms.model.LogMCTempHumPressRecordInfo;
import com.pms.model.LogMCTempIncubatorRecordInfo;
import com.pms.model.LogMCTempRefrigeratorRecordInfo;
import com.pms.model.LogMCWashDryerInfo;
import com.pms.model.LogMCWaterBathInfo;
import com.pms.model.LogMcMediumReagentInfo;
import com.pms.model.LogMcWaterSamplingInfo;
import com.pms.model.LogMcWeightingChdInfo;
import com.pms.model.LogMcWeightingMstInfo;
import com.pms.model.LogRefCultureInfo;
import com.pms.model.LogSterilePreparedInfo;
import com.pms.model.LogWaterBathInfo;
import com.pms.model.MaterialInfo;
import com.pms.repository.LogMicroRepository;

@Service
public class LogMicroService {

	@Autowired
	private LogMicroRepository microRepository;

	public List<MaterialInfo> getMaterialForSterilized(String type, String status) {
		return microRepository.findMaterialForSterilized(type, status);
	}

	public List<LogWaterBathInfo> getLogWaterBathInfos() {
		return microRepository.findLogWaterBathInfos();
	}

	public List<LogSterilePreparedInfo> getLogSterilePrepareInfos() {
		return microRepository.findLogSterilePrepareInfos();
	}

	public List<LogDisinfectantSolInfo> getDisinfectantSolInfos() {
		return microRepository.findDisinfectantSolInfos();
	}

	public List<LogRefCultureInfo> getRefCultureInfos() {
		return microRepository.findRefCultureInfos();
	}

	public void saveSterilePreparedMediumInfo(Map<String, String[]> requestMap) {
		LogSterilePreparedInfo info = new LogSterilePreparedInfo();
		String msId = requestMap.get("id")[0];
		String storageBy = requestMap.get("storageBy")[0];
		String issuedBy = requestMap.get("issuedBy")[0];
		String checkBy = requestMap.get("checkedBy")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (storageBy.trim().equals("")) {
			info.setStorageBy(null);
		} else {
			info.setStorageBy(UUID.fromString(requestMap.get("storageBy")[0]));
		}
		if (issuedBy.trim().equals("")) {
			info.setIssuedBy(null);
		} else {
			info.setIssuedBy(UUID.fromString(requestMap.get("issuedBy")[0]));
		}
		if (checkBy.trim().equals("")) {
			info.setCheckedBy(null);
		} else {
			info.setCheckedBy(UUID.fromString(requestMap.get("checkedBy")[0]));
		}

		if (!requestMap.get("storageDate")[0].equals("") && !requestMap.get("storageDate")[0].equals(null)) {
			info.setStorageDate((Date) Utility.getSqlDate(requestMap.get("storageDate")[0]));
		} else {
			info.setStorageDate(null);
		}
		if (!requestMap.get("expDate")[0].equals("") && !requestMap.get("expDate")[0].equals(null)) {
			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		} else {
			info.setExpDate(null);
		}
		if (!requestMap.get("issueDate")[0].equals("") && !requestMap.get("issueDate")[0].equals(null)) {
			info.setIssueDate((Date) Utility.getSqlDate(requestMap.get("issueDate")[0]));
		} else {
			info.setIssueDate(null);
		}

		info.setMediumName(requestMap.get("nameOfMedium")[0]);
		info.setStorageType(requestMap.get("typesOfStorage")[0]);
		info.setRefrigeratorId(requestMap.get("refrigeratorId")[0]);
		info.setStorageCondition(requestMap.get("storageCondition")[0]);
		info.setStorageQty(requestMap.get("storageQuantity")[0]);
		info.setLabBatcNo(requestMap.get("labBatchNo")[0]);
		info.setPackSize(requestMap.get("packSize")[0]);
		info.setIssuedQty(requestMap.get("issuedQty")[0]);
		info.setPurpose(requestMap.get("purpose")[0]);
		info.setStockQty(requestMap.get("stockQty")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveSterilePreparedMediumInfo(info);

	}

	public List<LogFumigationInfo> getFumigationInfos() {
		return microRepository.findFumigationInfos();
	}

	public void saveDisinfectantSolInfo(Map<String, String[]> requestMap) {
		LogDisinfectantSolInfo info = new LogDisinfectantSolInfo();
		String msId = requestMap.get("id")[0];
		String prepareBy = requestMap.get("prepareBy")[0];
		String checkBy = requestMap.get("checkBy")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (prepareBy.trim().equals("")) {
			info.setPrepareBy(null);
		} else {
			info.setPrepareBy(UUID.fromString(requestMap.get("prepareBy")[0]));
		}
		if (checkBy.trim().equals("")) {
			info.setCheckBy(null);
		} else {
			info.setCheckBy(UUID.fromString(requestMap.get("checkBy")[0]));
		}
		if (!requestMap.get("recordDate")[0].equals("") && !requestMap.get("recordDate")[0].equals(null)) {
			info.setRecordDate((Date) Utility.getSqlDate(requestMap.get("recordDate")[0]));
		} else {
			info.setRecordDate(null);
		}
		if (!requestMap.get("expDate")[0].equals("") && !requestMap.get("expDate")[0].equals(null)) {
			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		} else {
			info.setExpDate(null);
		}
		info.setDisinfecName(requestMap.get("disinfecName")[0]);
		info.setAmtDisinfec(requestMap.get("amtDisinfec")[0]);
		info.setAmtDistilled(requestMap.get("amtDistilled")[0]);
		info.setTotalAmt(requestMap.get("totalAmt")[0]);
		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setStoreTemp(requestMap.get("storeTemp")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveDisinfectantSolInfo(info);

	}

	public void saveFumigationInfo(Map<String, String[]> requestMap) {
		LogFumigationInfo info = new LogFumigationInfo();
		String msId = requestMap.get("id")[0];
		String doneBy = requestMap.get("doneBy")[0];
		String checkBy = requestMap.get("checkBy")[0];

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
		if (checkBy.trim().equals("")) {
			info.setCheckBy(null);
		} else {
			info.setCheckBy(UUID.fromString(requestMap.get("checkBy")[0]));
		}
		if (!requestMap.get("recordDate")[0].equals("") && !requestMap.get("recordDate")[0].equals(null)) {
			info.setRecordDate((Date) Utility.getSqlDate(requestMap.get("recordDate")[0]));
		} else {
			info.setRecordDate(null);
		}
		info.setLocation(requestMap.get("location")[0]);
		info.setRoomNo(requestMap.get("roomNo")[0]);
		info.setPotassium(requestMap.get("potassium")[0]);
		info.setFormal(requestMap.get("formal")[0]);
		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveFumigationInfo(info);

	}

	public void saveRefCultureInfo(Map<String, String[]> requestMap) {
		LogRefCultureInfo info = new LogRefCultureInfo();
		String msId = requestMap.get("id")[0];
		String mfgSupplierId = requestMap.get("mfgSupplierId")[0];
		String receiveBy = requestMap.get("receiveBy")[0];
		String checkBy = requestMap.get("checkBy")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if (mfgSupplierId.trim().equals("")) {
			info.setMfgSupplierId(null);
		} else {
			info.setMfgSupplierId(UUID.fromString(requestMap.get("mfgSupplierId")[0]));
		}
		if (receiveBy.trim().equals("")) {
			info.setReceiveBy(null);
		} else {
			info.setReceiveBy(UUID.fromString(requestMap.get("receiveBy")[0]));
		}
		if (checkBy.trim().equals("")) {
			info.setCheckBy(null);
		} else {
			info.setCheckBy(UUID.fromString(requestMap.get("checkBy")[0]));
		}
		if (!requestMap.get("receiveDate")[0].equals("") && !requestMap.get("receiveDate")[0].equals(null)) {
			info.setReceiveDate((Date) Utility.getSqlDate(requestMap.get("receiveDate")[0]));
		} else {
			info.setReceiveDate(null);
		}
		if (!requestMap.get("expDate")[0].equals("") && !requestMap.get("expDate")[0].equals(null)) {
			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		} else {
			info.setExpDate(null);
		}
		if (!requestMap.get("verifyDate")[0].equals("") && !requestMap.get("verifyDate")[0].equals(null)) {
			info.setVerifyDate((Date) Utility.getSqlDate(requestMap.get("verifyDate")[0]));
		} else {
			info.setVerifyDate(null);
		}
		info.setRefSeedName(requestMap.get("refSeedName")[0]);
		info.setAtccNo(requestMap.get("atccNo")[0]);
		info.setBatchLotNo(requestMap.get("batchLotNo")[0]);
		info.setCertiAvail(requestMap.get("certiAvail")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveRefCultureInfo(info);

	}

	// 20200722
	// Start========================================================================
	public List<LogMCColonyCounterInfo> getColonyCounterInfos(String equipmentId, String checkedStatus) {
		return microRepository.colonyCounterRowMapperInfos(equipmentId, checkedStatus);
	}

	public void saveColonyCounter(Map<String, String[]> requestMap) {
		LogMCColonyCounterInfo info = new LogMCColonyCounterInfo();
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
		microRepository.saveColonyCounter(info);

	}

	public void saveCheckedDynamicTable(Map<String, String[]> requestMap, String tableName) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");
		CommonInfo info = new CommonInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				microRepository.saveCheckedDynamicTable(info, tableName);

			}
		}
	}

	public void saveVerifiedDynamicTable(Map<String, String[]> requestMap, String tableName) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");// For Remarks...
		CommonInfo info = new CommonInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				microRepository.saveVerifiedDynamicTable(info, tableName);

			}
		}
	}

	public void saveOpenedDynamicTable(Map<String, String[]> requestMap, String tableName) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		CommonInfo info = new CommonInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				microRepository.saveOpenedDynamicTable(info, tableName);

			}
		}
	}
	public void saveFinishedDynamicTable(Map<String, String[]> requestMap, String tableName) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		CommonInfo info = new CommonInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				microRepository.saveFinishedDynamicTable(info, tableName);

			}
		}
	}

	public void saveIssuedDynamicTable(Map<String, String[]> requestMap, String tableName) {

		String msId = requestMap.get("id")[0];
		CommonInfo info = new CommonInfo();
		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));
		} else {
			info.setId(null);
		}
		microRepository.saveIssuedDynamicTable(info, tableName);
	}

	public void saveCleanedDynamicTable(Map<String, String[]> requestMap, String tableName) {
		CommonInfo info = new CommonInfo();
		String msId = requestMap.get("id")[0];
		String cleanBy = requestMap.get("cleanBy")[0];
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}

		if (cleanBy.trim().equals("")) {
			info.setCleanBy(null);
		} else {
			info.setCleanBy(UUID.fromString(requestMap.get("cleanBy")[0]));
		}
		microRepository.saveCleanedDynamicTable(info, tableName);
	}

	public void saveCleanedDynamicSelfTable(Map<String, String[]> requestMap, String tableName) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		String[] remarks = (String[]) requestMap.get("remarks[]");// For Remarks...
		CommonInfo info = new CommonInfo();

		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				microRepository.saveCleanedDynamicSelfTable(info, tableName);

			}
		}
	}

	// Microscope
	public List<LogMCMicroscopeInfo> getMicroscopeInfos(String equipmentId, String checkedStatus) {
		return microRepository.microscopeInfoRowMapperInfos(equipmentId, checkedStatus);
	}

	public void saveMicroscope(Map<String, String[]> requestMap) {
		LogMCMicroscopeInfo info = new LogMCMicroscopeInfo();
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
		microRepository.saveMicroscope(info);

	}
//Water Bath=================

	public List<LogMCWaterBathInfo> getWaterBathInfos(String equipmentId, String checkedStatus) {
		return microRepository.waterBathInfoRowMapperInfos(equipmentId, checkedStatus);
	}

	public void saveWaterBath(Map<String, String[]> requestMap) {
		LogMCWaterBathInfo info = new LogMCWaterBathInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String materialId = requestMap.get("materialId")[0];

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
		if (materialId.trim().equals("")) {
			info.setMaterialId(null);
		} else {
			info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setTemperature(requestMap.get("temperature")[0]);
		microRepository.saveWaterBath(info);

	}

	// Laminar Air Flow
	public List<LogMCLaminarAirFlowInfo> getLaminarAirFlowInfos(String equipmentId, String checkedStatus) {
		return microRepository.laminarAirFlowInfoRowMapperInfos(equipmentId, checkedStatus);
	}

	public void saveLaminarAirFlow(Map<String, String[]> requestMap) {
		LogMCLaminarAirFlowInfo info = new LogMCLaminarAirFlowInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String agentId = requestMap.get("agentId")[0];

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
		if (agentId.trim().equals("")) {
			info.setAgentId(null);
		} else {
			info.setAgentId(UUID.fromString(requestMap.get("agentId")[0]));
		}

		info.setLafOnTime(requestMap.get("lafOnTime")[0]);
		info.setLafOffTime(requestMap.get("lafOffTime")[0]);
		info.setUvOnTime(requestMap.get("uvOnTime")[0]);
		info.setUvOffTime(requestMap.get("uvOffTime")[0]);

		info.setRemarks(requestMap.get("remarks")[0]);
		info.setPurpose(requestMap.get("purpose")[0]);
		microRepository.saveLaminarAirFlow(info);

	}

	// Ph Meter
	public List<LogMCPhMeterInfo> getPhMeterInfos(String equipmentId, String verifyStatus) {
		return microRepository.findPhMeterInfos(equipmentId, verifyStatus);
	}

	public void savePhMeter(Map<String, String[]> requestMap) {
		LogMCPhMeterInfo info = new LogMCPhMeterInfo();
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

		info.setPhResult(requestMap.get("phResult")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setTemperature(requestMap.get("temperature")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		microRepository.savePhMeterInfo(info);
	}

	// Analytical Balance
	public List<LogMCAnalyticalBalanceInfo> getAnalyticalBalanceMCInfos(String equipmentId, String verifyStatus) {
		return microRepository.findAnalyticalBalanceInfos(equipmentId, verifyStatus);
	}

	public void saveAnalyticalBalanceMC(Map<String, String[]> requestMap) {
		LogMCAnalyticalBalanceInfo info = new LogMCAnalyticalBalanceInfo();
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

		microRepository.saveAnalyticalBalanceMCInfo(info);

	}

	// Analyst Validation
	public List<LogMCAnalystValidationInfo> getLogAnalystValidation(String verifyStatus) {
		return microRepository.findLogAnalystValidation(verifyStatus);
	}

	public void saveAnalystValidation(Map<String, String[]> requestMap) {
		LogMCAnalystValidationInfo info = new LogMCAnalystValidationInfo();
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
		microRepository.saveMCAnalystValidation(info);
	}

	// Allotted Sample for Analyst Validation
	public List<LogMCAllottedSampleInfo> getLogAllottedSampleInfo(String eqipId, String verifyStatus) {
		return microRepository.findLogAllottedSampleInfo(eqipId, verifyStatus);
	}

	public void saveAlloteeSample(Map<String, String[]> requestMap) {
		LogMCAllottedSampleInfo info = new LogMCAllottedSampleInfo();
		String msId = requestMap.get("id")[0];
		String analystId = requestMap.get("analystId")[0];
		String productId = requestMap.get("productId")[0];
		String testMethodId = requestMap.get("testMethodId")[0];
		String qcRefId = requestMap.get("qcRefId")[0];

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
		if (productId.trim().equals("")) {
			info.setProductId(null);
		} else {
			info.setProductId(UUID.fromString(requestMap.get("productId")[0]));
		}
		if (testMethodId.trim().equals("")) {
			info.setTestMethodId(null);
		} else {
			info.setTestMethodId(UUID.fromString(requestMap.get("testMethodId")[0]));
		}
		if (qcRefId.trim().equals("")) {
			info.setQcRefId(null);
		} else {
			info.setQcRefId(UUID.fromString(requestMap.get("qcRefId")[0]));
		}

		info.setDocumentCode(requestMap.get("documentCode")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveAlloteeSample(info);
	}

	// Incubator
	public static UUID provideStrToUUID(String stringId) {
		boolean isEmpty = true;
		if (stringId.trim().equals("") == true) {
			isEmpty = false;
		} else if (stringId == null || stringId.isEmpty()) {
			isEmpty = false;
		}
		return isEmpty ? UUID.fromString(stringId.toString()) : null;
	}

	public List<LogMCTempIncubatorRecordInfo> getTempIncubatorRecordInfos(String equipmentId, String eveningStatus,
			String cleanedStatus, String checkedStatus) {
		return microRepository.getTempIncubatorRecordInfos(equipmentId, eveningStatus, cleanedStatus, checkedStatus);
	}

	public void saveTempIncubatorMorningRecord(Map<String, String[]> requestMap) {
		LogMCTempIncubatorRecordInfo info = new LogMCTempIncubatorRecordInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(equipmentId));
		info.setMorningTemp(requestMap.get("morningTemp")[0]);
		info.setMorningTime(requestMap.get("morningTime")[0]);
		microRepository.saveTempIncubatorMorningRecord(info);
	}

	public void saveTempIncubatorEveningRecord(Map<String, String[]> requestMap) {
		LogMCTempIncubatorRecordInfo info = new LogMCTempIncubatorRecordInfo();
		String msId = requestMap.get("id")[0];
		String agentId = requestMap.get("agentId")[0];
		info.setId(provideStrToUUID(msId));
		info.setEveningTemp(requestMap.get("eveningTemp")[0]);
		info.setEveningTime(requestMap.get("eveningTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setAgentId(provideStrToUUID(agentId));
		microRepository.saveTempIncubatorEveningRecord(info);
	}

	// Refrigerator
	public List<LogMCTempRefrigeratorRecordInfo> getTempRefrigeratorRecordInfos(String equipmentId,
			String eveningStatus, String cleanedStatus, String checkedStatus) {
		return microRepository.getTempRefrigeratorRecordInfos(equipmentId, eveningStatus, cleanedStatus, checkedStatus);
	}

	public void saveTempRefrigeratorMorningRecord(Map<String, String[]> requestMap) {
		LogMCTempRefrigeratorRecordInfo info = new LogMCTempRefrigeratorRecordInfo();
		String msId = requestMap.get("id")[0];
		String equipmentId = requestMap.get("equipmentId")[0];
		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(equipmentId));
		info.setMorningTemp(requestMap.get("morningTemp")[0]);
		info.setMorningTime(requestMap.get("morningTime")[0]);
		microRepository.saveTempRefrigeratorMorningRecord(info);
	}

	public void saveTempRefrigeratorEveningRecord(Map<String, String[]> requestMap) {
		LogMCTempRefrigeratorRecordInfo info = new LogMCTempRefrigeratorRecordInfo();
		String msId = requestMap.get("id")[0];
		String agentId = requestMap.get("agentId")[0];
		info.setId(provideStrToUUID(msId));
		info.setEveningTemp(requestMap.get("eveningTemp")[0]);
		info.setEveningTime(requestMap.get("eveningTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setAgentId(provideStrToUUID(agentId));
		microRepository.saveTempRefrigeratorEveningRecord(info);
	}

	// ============Wash Dryer Start==============================================
	public List<LogMCWashDryerInfo> getWashDryerInfos(String equipmentId, String cleanedStatus, String checkedStatus) {
		return microRepository.washDryerRowMapperInfos(equipmentId, cleanedStatus, checkedStatus);
	}

	public void saveWashDryer(Map<String, String[]> requestMap) {
		LogMCWashDryerInfo info = new LogMCWashDryerInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String itemId = requestMap.get("itemId")[0];

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
		if (itemId.trim().equals("")) {
			info.setItemId(null);
		} else {
			info.setItemId(UUID.fromString(requestMap.get("itemId")[0]));
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveWashDryer(info);

	}

	// ============Autoclave Start==============================================
	public List<LogMCAutoClaveInfo> getAutoClaveInfos(String equipmentId, String cleanedStatus, String checkedStatus) {
		return microRepository.autoClaveRowMapperInfos(equipmentId, cleanedStatus, checkedStatus);
	}

	public void saveAutoClave(Map<String, String[]> requestMap) {
		LogMCAutoClaveInfo info = new LogMCAutoClaveInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String materialId = requestMap.get("materialId")[0];

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
		if (materialId.trim().equals("")) {
			info.setMaterialId(null);
		} else {
			info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		}

		info.setSterStartTime(requestMap.get("sterStartTime")[0]);
		info.setSterEndTime(requestMap.get("sterEndTime")[0]);
		info.setExhaStartTime(requestMap.get("exhaStartTime")[0]);
		info.setExhaEndTime(requestMap.get("exhaEndTime")[0]);
		info.setHeatingCycle(requestMap.get("heatingCycle")[0]);
		info.setTotalCycle(requestMap.get("totalCycle")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setIndicatorTape(requestMap.get("indicatorTape")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveAutoClave(info);

	}

	// ============Hot Air Oven==============================================
	public List<LogMCHotAirOvenInfo> getHotAirOvenInfos(String equipmentId, String cleanedStatus,
			String checkedStatus) {
		return microRepository.hotAirOvenRowMapperInfos(equipmentId, cleanedStatus, checkedStatus);
	}

	public void saveHotAirOven(Map<String, String[]> requestMap) {
		LogMCHotAirOvenInfo info = new LogMCHotAirOvenInfo();
		String equipmentId = requestMap.get("equipmentId")[0];
		String materialId = requestMap.get("materialId")[0];

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
		if (materialId.trim().equals("")) {
			info.setMaterialId(null);
		} else {
			info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		}

		info.setSterStartTime(requestMap.get("sterStartTime")[0]);
		info.setSterEndTime(requestMap.get("sterEndTime")[0]);
		info.setHeatingCycle(requestMap.get("heatingCycle")[0]);
		info.setTotalCycle(requestMap.get("totalCycle")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setIndicatorTape(requestMap.get("indicatorTape")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveHotAirOven(info);
		;
	}

	// LB_PREPARATION

	public List<LogMCLbPreparationInfo> getLbPreparationInfos(String checkedStatus) {
		return microRepository.lbPreparationRowMapperInfos(checkedStatus);
	}

	public void saveLbPreparation(Map<String, String[]> requestMap) {
		LogMCLbPreparationInfo info = new LogMCLbPreparationInfo();
		String disinfectantId = requestMap.get("disinfectantId")[0];
		if (disinfectantId.trim().equals("")) {
			info.setDisinfectantId(null);
		} else {
			info.setDisinfectantId(UUID.fromString(requestMap.get("disinfectantId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setAmountUsedWater(requestMap.get("amountUsedWater")[0]);
		info.setTotalAmountPreparation(requestMap.get("totalAmountPreparation")[0]);
		info.setAmountUsedDisinfectant(requestMap.get("amountUsedDisinfectant")[0]);

		if (!requestMap.get("expiryDate")[0].equals("") && !requestMap.get("expiryDate")[0].equals(null)) {
			info.setExpiryDate((Date) Utility.getSqlDate(requestMap.get("expiryDate")[0]));
		} else {
			info.setExpiryDate(null);
		}

		microRepository.saveLbPreparation(info);
		;

	}

	// LB FumigationInfo
	public List<LogMCLbFumigationInfo> getLbFumigationInfos(String checkedStatus) {
		return microRepository.lbFumigationRowMapperInfos(checkedStatus);
	}

	public void saveLbFumigation(Map<String, String[]> requestMap) {
		LogMCLbFumigationInfo info = new LogMCLbFumigationInfo();
		String roomId = requestMap.get("roomId")[0];
		String locationId = requestMap.get("locationId")[0];
		if (roomId.trim().equals("")) {
			info.setRoomId(null);
		} else {
			info.setRoomId(UUID.fromString(requestMap.get("roomId")[0]));
		}
		if (locationId.trim().equals("")) {
			info.setLocationId(null);
		} else {
			info.setLocationId(UUID.fromString(requestMap.get("locationId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setPotassiumPermanganate(requestMap.get("potassiumPermanganate")[0]);
		info.setFormaldehyde(requestMap.get("formaldehyde")[0]);

		microRepository.saveLbFumigation(info);
		;

	}

	// LB Reference Culture
	public List<LogMCLbReferenceCultureInfo> getLbReferenceCultureInfos(String checkedStatus) {
		return microRepository.lbReferenceCultureRowMapperInfos(checkedStatus);
	}

	public void saveLbReferenceCulture(Map<String, String[]> requestMap) {
		LogMCLbReferenceCultureInfo info = new LogMCLbReferenceCultureInfo();
		String supplierId = requestMap.get("supplierId")[0];
		String cultureId = requestMap.get("cultureId")[0];
		if (supplierId.trim().equals("")) {
			info.setSupplierId(null);
		} else {
			info.setSupplierId(UUID.fromString(requestMap.get("supplierId")[0]));
		}
		if (cultureId.trim().equals("")) {
			info.setCultureId(null);
		} else {
			info.setCultureId(UUID.fromString(requestMap.get("cultureId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setAtccNo(requestMap.get("atccNo")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setCertificateAvailable(requestMap.get("certificateAvailable")[0]);

		if (!requestMap.get("expiryDate")[0].equals("") && !requestMap.get("expiryDate")[0].equals(null)) {
			info.setExpiryDate((Date) Utility.getSqlDate(requestMap.get("expiryDate")[0]));
		} else {
			info.setExpiryDate(null);
		}
		if (!requestMap.get("verificationDate")[0].equals("") && !requestMap.get("verificationDate")[0].equals(null)) {
			info.setVerificationDate((Date) Utility.getSqlDate(requestMap.get("verificationDate")[0]));
		} else {
			info.setVerificationDate(null);
		}

		microRepository.saveLbReferenceCulture(info);
		;

	}

	// LB Test Strain

	public List<LogMCLbTestStrainInfo> getLbTestStrainInfos(String checkedStatus) {
		return microRepository.lbTestStrainRowMapperInfos(checkedStatus);
	}

	public void saveLbTestStrain(Map<String, String[]> requestMap) {
		LogMCLbTestStrainInfo info = new LogMCLbTestStrainInfo();
		String cultureId = requestMap.get("cultureId")[0];

		if (cultureId.trim().equals("")) {
			info.setCultureId(null);
		} else {
			info.setCultureId(UUID.fromString(requestMap.get("cultureId")[0]));
		}

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));
		} else {
			info.setId(null);
		}

		info.setStartTime(requestMap.get("startTime")[0]);
		info.setEndTime(requestMap.get("endTime")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setAtccNo(requestMap.get("atccNo")[0]);
		info.setTotalSuspensionVolue(requestMap.get("totalSuspensionVolue")[0]);
		info.setResultP1(requestMap.get("resultP1")[0]);
		info.setResultP2(requestMap.get("resultP2")[0]);
		info.setAverageResult(requestMap.get("averageResult")[0]);
		info.setStorageCondition(requestMap.get("storageCondition")[0]);
		// total_suspension_volue,result_p1,result_p2,average_result,storage_condition,
		microRepository.saveLbTestStrain(info);
		;

	}

	// Water Sampling
	public List<LogMcWaterSamplingInfo> getLogWaterSamplingInfo(String eqipId, String verifyStatus) {
		return microRepository.findLogWaterSamplingInfo(eqipId, verifyStatus);
	}

	public List<LogMcMediumReagentInfo> getLogMediumReagentInfos(String isWeight) {
		return microRepository.findMediumReagentInfos(isWeight);
	}

	public void saveWaterSampling(Map<String, String[]> requestMap) {
		LogMcWaterSamplingInfo info = new LogMcWaterSamplingInfo();
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
		microRepository.saveWaterSampling(info);
	}

	public void verifyWaterSamplingInfo(Map<String, String[]> requestMap) {
		String[] id = (String[]) requestMap.get("id[]");
		String[] isChecked = (String[]) requestMap.get("hiddenValCkbx[]");
		LogMcWaterSamplingInfo info = new LogMcWaterSamplingInfo();
		String[] remarks = (String[]) requestMap.get("remarks[]");// For Remarks...
		for (int i = 0; i < id.length; i++) {
			if (isChecked[i].equals("Y")) {
				info.setId(UUID.fromString(id[i]));
				info.setRemarks(remarks[i]);
				microRepository.verifyWaterSamplingInfo(info);

			}
		}

	}

	// Temp Humidity Pressure===============
	public List<LogMCTempHumPressRecordInfo> getTempHumPressRecordInfos(String eveningStatus, String cleanedStatus,
			String checkedStatus) {
		return microRepository.getTempHumPressRecordInfos(eveningStatus, cleanedStatus, checkedStatus);
	}

	public void saveTempHumPressMorningRecord(Map<String, String[]> requestMap) {
		LogMCTempHumPressRecordInfo info = new LogMCTempHumPressRecordInfo();
		String msId = requestMap.get("id")[0];
		info.setId(provideStrToUUID(msId));
		info.setMorningTime(requestMap.get("morningTime")[0]);

		info.setMorningTempBefCor(requestMap.get("morningTempBefCor")[0]);
		info.setMorningTempAftCor(requestMap.get("morningTempAftCor")[0]);
		info.setMorningHumBefCor(requestMap.get("morningHumBefCor")[0]);
		info.setMorningHumAftCor(requestMap.get("morningHumAftCor")[0]);
		info.setMorningPressure(requestMap.get("morningPressure")[0]);

		microRepository.saveTempHumPressMorningRecord(info);
	}

	public void saveTempHumPressEveningRecord(Map<String, String[]> requestMap) {
		LogMCTempHumPressRecordInfo info = new LogMCTempHumPressRecordInfo();
		String msId = requestMap.get("id")[0];
		info.setId(provideStrToUUID(msId));
		info.setEveningTime(requestMap.get("eveningTime")[0]);

		info.setEveningTempBefCor(requestMap.get("eveningTempBefCor")[0]);
		info.setEveningTempAftCor(requestMap.get("eveningTempAftCor")[0]);
		info.setEveningHumBefCor(requestMap.get("eveningHumBefCor")[0]);
		info.setEveningHumAftCor(requestMap.get("eveningHumAftCor")[0]);
		info.setEveningPressure(requestMap.get("eveningPressure")[0]);

		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveTempHumPressEveningRecord(info);
	}

	public void saveMediumReagentInfo(Map<String, String[]> requestMap) {
		LogMcMediumReagentInfo info = new LogMcMediumReagentInfo();

		if (requestMap.get("id")[0] != null && !requestMap.get("id")[0].isEmpty()) {
			info.setId(UUID.fromString(requestMap.get("id")[0]));

		} else {
			info.setId(null);
		}
		if (requestMap.containsKey("reagentId")) {
			info.setReagentId(UUID.fromString(requestMap.get("reagentId")[0]));
		} else {
			info.setReagentId(null);

		}
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setBatchSeqNo(Integer.parseInt(requestMap.get("batchSeqNo")[0]));
		info.setBatchSize(requestMap.get("batchSize")[0]);
		if (requestMap.get("prepDate")[0] != null && !requestMap.get("prepDate")[0].equals("")) {
			info.setPrepDate((Date) Utility.getSqlDate(requestMap.get("prepDate")[0]));
		} else {
			info.setPrepDate(null);
		}

		if (requestMap.get("expDate")[0] != null && !requestMap.get("expDate")[0].equals("")) {

			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		} else {
			info.setExpDate(null);
		}

		microRepository.saveMediumReagentInfo(info);
	}

	public void saveWeightingMstInfos(Map<String, String[]> requestMap) {
		LogMcWeightingMstInfo info = new LogMcWeightingMstInfo();

		info.setPreparationId(UUID.fromString(requestMap.get("preparationId")[0]));
		info.setBalanceId(UUID.fromString(requestMap.get("balanceId")[0]));

		microRepository.saveWeightingMstInfos(info);

	}
	public void saveWeightingChdInfos(Map<String, String[]> requestMap) {
		LogMcWeightingChdInfo info = new LogMcWeightingChdInfo();

		String prepartionId=requestMap.get("preparationId")[0];
		String[] codeNo = (String[]) requestMap.get("codeNo[]");
		String[] materialId = (String[]) requestMap.get("materialId[]");
		String[] batchNo = (String[]) requestMap.get("batchNo[]");
		String[] expDate = (String[]) requestMap.get("expDate[]");
		String[] quantity = (String[]) requestMap.get("quantity[]");
		String[] unitId = (String[]) requestMap.get("unitId[]");
		
		if(requestMap.containsKey("codeNo[]")) {
			
			for (int i = 0; i < codeNo.length; i++) {
				info.setPreparationId(UUID.fromString(prepartionId));
				info.setCodeNo(codeNo[i]);
				info.setMaterialId(UUID.fromString(materialId[i]));
				info.setBatchNo(batchNo[i]);
				info.setExpDate((Date) Utility.getSqlDate(expDate[i]));
				info.setNetWeight(Integer.parseInt(quantity[i]));
				info.setUnitId(UUID.fromString(unitId[i]));
				microRepository.saveWeightingChdInfos(info);

			}
		}

		

	}

	public List<LogMCMediaReagentMaterialsInfo> getLogMediaReagentMaterials(String issuedStatus, String openingStatus, String finishStatus,
			String checkedStatus) {
		return microRepository.findLogMediaReagentMaterials(issuedStatus, openingStatus, finishStatus, checkedStatus);
	}

	public void saveMediaReagentMaterials(Map<String, String[]> requestMap) {
		LogMCMediaReagentMaterialsInfo info = new LogMCMediaReagentMaterialsInfo();
		String msId = requestMap.get("id")[0];
		String materialsId = requestMap.get("materialsId")[0];
		String supplierId = requestMap.get("supplierId")[0];
		String countryOriginId = requestMap.get("countryOriginId")[0];
		String unitId = requestMap.get("unitId")[0];

		if (msId != null && !msId.isEmpty()) {
			info.setId(UUID.fromString(msId.toString()));
		} else {
			info.setId(null);
		}
		if (materialsId.trim().equals("")) {
			info.setMaterialsId(null);
		} else {
			info.setMaterialsId(UUID.fromString(requestMap.get("materialsId")[0]));
		}
		if (supplierId.trim().equals("")) {
			info.setSupplierId(null);
		} else {
			info.setSupplierId(UUID.fromString(requestMap.get("supplierId")[0]));
		}
		if (unitId.trim().equals("")) {
			info.setSupplierId(null);
		} else {
			info.setUnitId(UUID.fromString(requestMap.get("unitId")[0]));
		}
		if (countryOriginId.trim().equals("")) {
			info.setCountryOriginId(null);
		} else {
			info.setCountryOriginId(UUID.fromString(requestMap.get("countryOriginId")[0]));
		}
		if (!requestMap.get("mfgDate")[0].equals("") && !requestMap.get("mfgDate")[0].equals(null)) {
			info.setMfgDate((Date) Utility.getSqlDate(requestMap.get("mfgDate")[0]));
		} else {
			info.setMfgDate(null);
		}
		if (!requestMap.get("expDate")[0].equals("") && !requestMap.get("expDate")[0].equals(null)) {
			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		} else {
			info.setExpDate(null);
		}
		info.setCodeNo(requestMap.get("codeNo")[0]);
		info.setSpecification(requestMap.get("specification")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setQuantity(requestMap.get("quantity")[0]);
		// info.setUnit(requestMap.get("unitId")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		microRepository.saveMCMediaReagentMaterials(info);
	}

}
