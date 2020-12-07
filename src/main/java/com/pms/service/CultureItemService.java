package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CultureItemInfo;
import com.pms.repository.CultureItemRepository;

@Service
public class CultureItemService {

	@Autowired
	private CultureItemRepository cultureItemRepository;

	public List<CultureItemInfo> getAll(String status) {
		return cultureItemRepository.findAll(status);
	}
	
	public void saveCultureItemInfos(CultureItemInfo info) {
		cultureItemRepository.saveCultureItemInfos(info);
	}

	public boolean validateName(Map<String, String[]> requestMap) {
		String Name = requestMap.get("Name")[0];
		
		List<CultureItemInfo> entityTransList = cultureItemRepository.validateCultureItemName(Name);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
