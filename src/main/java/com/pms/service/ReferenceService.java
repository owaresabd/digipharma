package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ReferenceInfo;
import com.pms.repository.ReferenceRepository;

@Service
public class ReferenceService {

	@Autowired
	private ReferenceRepository referenceRepository;

	public List<ReferenceInfo> getAll(String status) {
		return referenceRepository.findAll(status);
	}

	

	public void saveReferenceInfos(ReferenceInfo referenceInfo) {
		referenceRepository.saveReferenceInfos(referenceInfo);
	}

	public boolean validateReferenceNo(Map<String, String[]> requestMap) {
		String referenceNo = requestMap.get("referenceNo")[0];
		List<ReferenceInfo> dataList = referenceRepository.validateReferenceNo(referenceNo);
		if (dataList.size() == 0) {
			return true;
		}

		return false;
	}
}
