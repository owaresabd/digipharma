package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.MaintenanceTypeInfo;
import com.pms.repository.MaintenanceTypeRepository;

@Service
public class MaintenanceTypeService {

	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;

	public List<MaintenanceTypeInfo> getAll(String status) {
		return maintenanceTypeRepository.findAll(status);
	}

	
	public void saveMaintenanceTypeInfos(MaintenanceTypeInfo info) {
		maintenanceTypeRepository.saveMaintenanceTypeInfos(info);
	}

	public boolean validateMaintenanceTypeName(Map<String, String[]> requestMap) {
		String typeName = requestMap.get("typeName")[0];
		List<MaintenanceTypeInfo> entityTransList = maintenanceTypeRepository.validateMaintenanceType(typeName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
