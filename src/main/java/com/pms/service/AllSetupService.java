package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.EquipmentInfo;
import com.pms.model.IncubatorSetupInfo;
import com.pms.model.RefrigeratorTempChemiSetupInfo;
import com.pms.model.TempAndHumiditySetupInfo;
import com.pms.repository.AllSetupRepository;

@Service
public class AllSetupService {

	@Autowired
	private AllSetupRepository allSetupRepository;

	public List<TempAndHumiditySetupInfo> getTempHumiditySetupInfos() {
		return allSetupRepository.getTempHumiditySetupInfos();
	}
	public List<IncubatorSetupInfo> getIncubatorSetupInfos() {
		return allSetupRepository.getIncubatorSetupInfos();
	}
	public List<IncubatorSetupInfo> getRefrigeratorSetupInfos() {
		return allSetupRepository.getRefrigeratorSetupInfos();
	}
	public List<RefrigeratorTempChemiSetupInfo> getRefrigeratorTempChemiSetupInfos(String status, String equipmentId) {
		return allSetupRepository.getRefrigeratorTempChemiSetupInfos(status, equipmentId);
	}
	public RefrigeratorTempChemiSetupInfo getRefrigeratorTempChemiSetupInfo(String status, String equipmentId) {
		return allSetupRepository.getRefrigeratorTempChemiSetupInfo(status, equipmentId);
	}
	public List<TempAndHumiditySetupInfo> getTempHmdtyStupInfosByStatus(String status, String equipmentId) {
		return allSetupRepository.getTempHmdtyStupInfosByStatus(status, equipmentId);
	}
	public List<IncubatorSetupInfo> getIncubetarSetupInfosByStatus(String status, String equipmentId) {
		return allSetupRepository.getIncubetarSetupInfosByStatus(status, equipmentId);
	}
	public List<IncubatorSetupInfo> getRefrigeratorSetupInfosByStatus(String status, String equipmentId) {
		return allSetupRepository.getRefrigeratorSetupInfosByStatus(status, equipmentId);
	}
	public List<EquipmentInfo> getEquipments(String status) {
		return allSetupRepository.findEquipments(status);
	}
	public void saveTempHumiditySetupInfo(Map<String, String[]> requestMap) {
		TempAndHumiditySetupInfo info = new TempAndHumiditySetupInfo();
		String msId = requestMap.get("id")[0];
		String eqId = requestMap.get("equipmentId")[0];
		String locId = requestMap.get("locationId")[0];
		String depId = requestMap.get("departmentId")[0];
	
		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(eqId));
		info.setLocationId(provideStrToUUID(locId));
		info.setDepartmentId(provideStrToUUID(depId));
		info.setCorrectionValFrTemp(requestMap.get("correctionValFrTemp")[0]);
		info.setCorrectionValFrHumidity(requestMap.get("correctionValFrHumidity")[0]);
		info.setStatus(requestMap.get("status")[0]);
		allSetupRepository.saveTempHumiditySetupInfo(info);
	}
	public void saveIncubatorTempSetupInfo(Map<String, String[]> requestMap) {
		IncubatorSetupInfo info = new IncubatorSetupInfo();
		String msId = requestMap.get("id")[0];
		String eqId = requestMap.get("equipmentId")[0];
		
		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(eqId));
		info.setTemperature(requestMap.get("temperature")[0]);
		info.setTolerance(requestMap.get("tolerance")[0]);
		info.setStatus(requestMap.get("status")[0]);
		allSetupRepository.saveIncubatorTempSetupInfo(info);
	}
	public void saveRefrigeratorTempSetupInfo(Map<String, String[]> requestMap) {
		IncubatorSetupInfo info = new IncubatorSetupInfo();
		String msId = requestMap.get("id")[0];
		String eqId = requestMap.get("equipmentId")[0];
		
		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(eqId));
		info.setTemperature(requestMap.get("temperature")[0]);
		info.setTolerance(requestMap.get("tolerance")[0]);
		info.setStatus(requestMap.get("status")[0]);
		allSetupRepository.saveRefrigeratorTempSetupInfo(info);
	}
	public void saveRefrigeratorTempChemiSetupInfo(Map<String, String[]> requestMap) {
		RefrigeratorTempChemiSetupInfo info = new RefrigeratorTempChemiSetupInfo();
		String msId = requestMap.get("id")[0];
		String eqId = requestMap.get("equipmentId")[0];
		
		info.setId(provideStrToUUID(msId));
		info.setEquipmentId(provideStrToUUID(eqId));
		info.setTemperature(requestMap.get("temperature")[0]);
		info.setStatus(requestMap.get("status")[0]);
		allSetupRepository.saveRefrigeratorTempChemiSetupInfo(info);
	}
//	
//	public boolean validateDocumentNo(Map<String, String[]> requestMap) {
//		String name = requestMap.get("documentNo")[0];
//		List<LogBookSetupInfo> entityTransList = logBookSetupRepository.validateDocumentNo(name);
//		if (entityTransList.size() == 0) {
//			return true;
//		}
//		return false;
//	}
//	

	public static UUID provideStrToUUID(String stringId) {
		boolean isEmpty = true;
		if (stringId.trim().equals("") == true) {
			isEmpty = false;
		} else if (stringId == null || stringId.isEmpty()) {
			isEmpty = false;
		}
		return isEmpty ? UUID.fromString(stringId.toString()) : null;
	}

}
