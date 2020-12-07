package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.LabItemSetupInfo;
import com.pms.repository.LabItemRepository;

@Service
public class LabItemService {

	@Autowired
	private LabItemRepository labItemRepository;

	public List<LabItemSetupInfo> getLabItemSetupInfos(String status) {
		return labItemRepository.getLabItemSetupInfos(status);
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
	public void saveLabItemSetupInfo(Map<String, String[]> requestMap) {
		LabItemSetupInfo info = new LabItemSetupInfo();
		String msId = requestMap.get("id")[0];
		info.setId(provideStrToUUID(msId));
		info.setItemName(requestMap.get("itemName")[0]);
		info.setStatus(requestMap.get("status")[0]);
		labItemRepository.saveLabItemSetupInfo(info);
	}

	
}
