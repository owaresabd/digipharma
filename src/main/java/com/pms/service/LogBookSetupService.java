package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.EquipmentInfo;
import com.pms.model.LogBookSetupInfo;
import com.pms.repository.LogBookSetupRepository;

@Service
public class LogBookSetupService {

	@Autowired
	private LogBookSetupRepository logBookSetupRepository;

	public List<LogBookSetupInfo> getLogBookSetupInfos(String status,String isApllicable) {
		return logBookSetupRepository.findLogBookSetupInfos(status, isApllicable);
	}
	
	public boolean validateDocumentNo(Map<String, String[]> requestMap) {
		String name = requestMap.get("documentNo")[0];
		List<LogBookSetupInfo> entityTransList = logBookSetupRepository.validateDocumentNo(name);
		if (entityTransList.size() == 0) {
			return true;
		}
		return false;
	}
	
	public void saveLogBookSetupInfo(Map<String, String[]> requestMap) {
		LogBookSetupInfo info = new LogBookSetupInfo();
		String msId 		= requestMap.get("id")[0];
		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		if(!requestMap.get("effectiveDate")[0].equals("") && !requestMap.get("effectiveDate")[0].equals(null)) {
			info.setEffectiveDate((Date) Utility.getSqlDate(requestMap.get("effectiveDate")[0]));
		}else {
			info.setEffectiveDate(null);
		}
		info.setLogbookId(requestMap.get("logbookId")[0]);
		info.setLogbookName(requestMap.get("logbookName")[0]);
		info.setDocumentNo(requestMap.get("documentNo")[0]);
		info.setRevisionNo(requestMap.get("revisionNo")[0]);
		info.setLogbookType(requestMap.get("logbookType")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setStatus(requestMap.get("status")[0]);
		info.setIsApplicable(requestMap.get("isApplicable")[0]);
		logBookSetupRepository.saveLogBookSetupInfo(info);

	}
	
	public List<EquipmentInfo> findUnassignedEquipment(Map<String, String[]> requestMap) {
		UUID logbookId = UUID.fromString(requestMap.get("logbookId")[0]);
		return logBookSetupRepository.findUnassignedEquipment(logbookId);
	}
	public List<EquipmentInfo> findAssignedEquipment(Map<String, String[]> requestMap) {
		UUID logbookId = UUID.fromString(requestMap.get("logbookId")[0]);
		return logBookSetupRepository.findAssignedEquipment(logbookId);
	}
	public void addEquipmentMapping(Map<String, String[]> map) {
		UUID equipmentId = UUID.fromString(map.get("equipmentId")[0]);
		UUID logbookId = UUID.fromString(map.get("logbookId")[0]);
		logBookSetupRepository.saveEquipmentMapping(equipmentId, logbookId);
	}

	public void removeEquipmentMapping(Map<String, String[]> map) {
		UUID equipmentId = UUID.fromString(map.get("equipmentId")[0]);
		UUID logbookId = UUID.fromString(map.get("logbookId")[0]);
		logBookSetupRepository.deleteEquipmentMapping(equipmentId, logbookId);
	}
}
