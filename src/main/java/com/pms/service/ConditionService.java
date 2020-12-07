package com.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.StorageInfo;
import com.pms.repository.ConditionRepository;

@Service
public class ConditionService {

	@Autowired
	private ConditionRepository conditionRepository;

	public List<StorageInfo> getAll(String status) {
		return conditionRepository.findAll(status);
	}

	
	public void saveConditionInfos(StorageInfo storageInfo) {
		conditionRepository.saveConditionInfos(storageInfo);
	}
		
	
}
