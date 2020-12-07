package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ColumnSetupInfo;
import com.pms.repository.ColumnSetupRepository;

@Service
public class ColumnSetupService {

	@Autowired
	private ColumnSetupRepository columnSetupRepository;

	public List<ColumnSetupInfo> getColumnSetupInfos() {
		return columnSetupRepository.findColumnSetupInfos();
	}
	
	public List<ColumnSetupInfo> getActiveColumnSetupInfos(String status) {
		return columnSetupRepository.findColumnSetupInfos().stream().filter(col -> col.getStatus().equals(status)).collect(Collectors.toList());
	}
	
	
	public void saveColumnSetupInfo(Map<String, String[]> requestMap) {
		ColumnSetupInfo info = new ColumnSetupInfo();
		String msId 		= requestMap.get("id")[0];
	
		info.setId(checkNotNullString(msId) == true ? provideStrToUUID(msId) : null);
		info.setColumnId(requestMap.get("columnId")[0]);
		info.setColumnName(requestMap.get("columnName")[0]);
		info.setComposition(requestMap.get("composition")[0]);
		info.setColumnSize(requestMap.get("columnSize")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		info.setStatus(requestMap.get("status")[0]);
		columnSetupRepository.saveColumnSetupInfo(info);
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
