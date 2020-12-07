package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.RequestTypeInfo;
import com.pms.repository.RequestTypeRepository;

@Service
public class RequestTypeService {

	@Autowired
	private RequestTypeRepository requestTypeRepository;

	public List<RequestTypeInfo> getAll(String status) {
		return requestTypeRepository.findAll(status);
	}

	

	public void saveRequestTypeInfos(RequestTypeInfo requestTypeInfo) {
		requestTypeRepository.saveRequestTypeInfos(requestTypeInfo);
	}

	public boolean validateTypeNo(Map<String, String[]> requestMap) {
		String typeNo = requestMap.get("typeNo")[0];
		List<RequestTypeInfo> entityTransDtlsList = requestTypeRepository.validateTypeNo(typeNo);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
