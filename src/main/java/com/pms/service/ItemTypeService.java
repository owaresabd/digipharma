package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ItemTypeInfo;
import com.pms.repository.ItemTypeRepository;

@Service
public class ItemTypeService {

	@Autowired
	private ItemTypeRepository itemTypeRepository;

	public List<ItemTypeInfo> getAll(String status) {
		return itemTypeRepository.findAll(status);
	}

	public ItemTypeInfo getById(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return itemTypeRepository.findById(id);
	}

	public void saveItemTypeInfos(ItemTypeInfo itemTypeInfo) {
		itemTypeRepository.saveItemTypeInfos(itemTypeInfo);
	}

	public UUID delete(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return itemTypeRepository.delete(id);
	}

	public boolean hasItemTypeCode(Map<String, String[]> requestMap) {
		String udItemTypeNo = requestMap.get("udItemTypeNo")[0];
		List<ItemTypeInfo> entityTransDtlsList = itemTypeRepository.validateUdItemTypeNo(udItemTypeNo);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
