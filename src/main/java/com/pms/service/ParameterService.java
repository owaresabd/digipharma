package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ParameterInfo;
import com.pms.repository.ParameterRepository;

@Service
public class ParameterService {

	@Autowired
	private ParameterRepository parameterRepository;

	public List<ParameterInfo> getAll(String status) {
		return parameterRepository.findAll(status);
	}

	public List<ParameterInfo> getAllParameterInfoById(UUID id) {
		return parameterRepository.findParameterInfoById(id);
	}
	
	public void saveParameterInfos(ParameterInfo parameterInfo) {
		parameterRepository.saveParameterInfos(parameterInfo);
	}

	public boolean validateTestParameterNo(Map<String, String[]> requestMap) {
		String testParameterNo = requestMap.get("testParameterNo")[0];
		List<ParameterInfo> dataList = parameterRepository.validateTestParameterNo(testParameterNo);
		if (dataList.size() == 0) {
			return true;
		}
		return false;
	}

	
	
}
