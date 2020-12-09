package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DesignationInfo;
import com.pms.repository.DesignationRepository;


@Service
public class DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	public List<DesignationInfo> getAll(String status) {
		return designationRepository.findAll(status);
	}

	

	public void saveDesignationInfos(DesignationInfo designationInfo) {
		designationRepository.saveDesignationInfos(designationInfo);
	}

	public boolean validateDesignationNo(Map<String, String[]> requestMap) {
		String designationNo = requestMap.get("designationNo")[0];
		List<DesignationInfo> entityTransDtlsList = designationRepository.validateDesignationNo(designationNo);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
