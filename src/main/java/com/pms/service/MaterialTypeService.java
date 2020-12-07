package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.MaterialTypeInfo;
import com.pms.repository.MaterialTypeRepository;

@Service
public class MaterialTypeService {

	@Autowired
	private MaterialTypeRepository materialTypeRepository;

	public List<MaterialTypeInfo> getAll(String status) {
		return materialTypeRepository.findAll(status);
	}

	

	public void saveMaterialTypeInfos(MaterialTypeInfo materialTypeInfo) {
		materialTypeRepository.saveMaterialTypeInfos(materialTypeInfo);
	}

	public boolean validateTypeNo(Map<String, String[]> requestMap) {
		String typeNo = requestMap.get("typeNo")[0];
		List<MaterialTypeInfo> entityTransDtlsList = materialTypeRepository.validateTypeNo(typeNo);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
