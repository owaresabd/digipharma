package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CultureItemTypeInfo;
import com.pms.repository.CultureItemTypeRepository;

@Service
public class CultureItemTypeService {

	@Autowired
	private CultureItemTypeRepository cultureItemTypeRepository;

	public List<CultureItemTypeInfo> getAll(String status) {
		return cultureItemTypeRepository.findAll(status);
	}

	
	public void saveCultureItemTypeInfos(CultureItemTypeInfo info) {
		cultureItemTypeRepository.saveCultureItemTypeInfos(info);
	}

	public boolean validateTypeName(Map<String, String[]> requestMap) {
		String typeName = requestMap.get("typeName")[0];
		List<CultureItemTypeInfo> entityTransList = cultureItemTypeRepository.validateCultureItemTypeName(typeName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
