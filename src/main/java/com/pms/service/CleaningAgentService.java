package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CleaningAgentInfo;
import com.pms.repository.CleaningAgentRepository;

@Service
public class CleaningAgentService {

	@Autowired
	private CleaningAgentRepository cleaningAgentRepository;

	public List<CleaningAgentInfo> getCleaningAgentInfos(String status) {
		return cleaningAgentRepository.findCleaningAgentInfos(status);
	}
	
	
	
	public void saveCleaningAgentInfo(Map<String, String[]> requestMap) {
		CleaningAgentInfo info = new CleaningAgentInfo();
		String msId 		= requestMap.get("id")[0];
	
		info.setId(checkNotNullString(msId) == true ? provideStrToUUID(msId) : null);
		info.setAgentId(requestMap.get("agentId")[0]);
		info.setAgentName(requestMap.get("agentName")[0]);
		if(!requestMap.get("manufacDate")[0].equals(null) && !requestMap.get("manufacDate")[0].equals("")) {
			info.setManufacDate((Date) Utility.getSqlDate(requestMap.get("manufacDate")[0]));
		}else {
			info.setManufacDate(null);
		}
		if(!requestMap.get("expDate")[0].equals(null) && !requestMap.get("expDate")[0].equals("")) {
			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		}else {
			info.setExpDate(null);
		}
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setStatus(requestMap.get("status")[0]);
		cleaningAgentRepository.saveCleaningAgentInfo(info);
	}

	
	
	public static UUID provideStrToUUID(String stringId) {
		UUID  uuid= UUID.fromString(stringId.toString());
		return uuid;
	}
	
	public static boolean checkNotNullString(String stringValue) {
		if (stringValue.trim().equals("") == true) {
			return false;
		} else if ( stringValue == null || stringValue.isEmpty()) {
				return false;
		}
		return true;
	}
}
