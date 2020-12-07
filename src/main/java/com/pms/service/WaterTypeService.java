package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.WaterTypeInfo;
import com.pms.repository.WaterTypeRepository;

@Service
public class WaterTypeService {

	@Autowired
	private WaterTypeRepository waterTypeRepository;

	public List<WaterTypeInfo> getAll(String status) {
		return waterTypeRepository.findAll(status);
	}

	
	public void saveWaterTypeInfos(WaterTypeInfo info) {
		waterTypeRepository.saveWaterTypeInfos(info);
	}

	public boolean validateTypeName(Map<String, String[]> requestMap) {
		String typeName = requestMap.get("typeName")[0];
		List<WaterTypeInfo> entityTransList = waterTypeRepository.validateWaterTypeName(typeName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
