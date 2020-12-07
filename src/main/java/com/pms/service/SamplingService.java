package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.QcInfo;
import com.pms.model.SamplingInfo;
import com.pms.repository.SamplingRepository;

@Service
public class SamplingService {

	@Autowired
	private SamplingRepository samplingRepository;

	public List<SamplingInfo> getAll() {
		return samplingRepository.findAll();
	}
	
	public List<SamplingInfo> getSentSampleList() {
		return samplingRepository.sentSampleList();
	}
	
	public SamplingInfo getSampleDetailsInfo(UUID id) {
		return samplingRepository.getSampleDetailsInfo(id);
	}
	public void saveQCReqInfos(Map<String, String[]> requestMap) {
		QcInfo info = new QcInfo();
		
		info.setSampleId(UUID.fromString(requestMap.get("sampleId")[0]));
		info.setConditionType(requestMap.get("conditionType")[0]);
		if(requestMap.get("conditionType")[0].equals("A")) {
		info.setAbnormalType(requestMap.get("abnormalType")[0]);
		info.setAbnormalDesc(requestMap.get("abnormalDesc")[0]);
		}else {
			info.setAbnormalType("");
			info.setAbnormalDesc("");
		}
		
		info.setCustomerDesc(requestMap.get("customerDesc")[0]);
		info.setSampleDesc(requestMap.get("sampleDesc")[0]);
		info.setUncertinity(requestMap.get("uncertinity")[0]);
		info.setIsDecision(requestMap.get("isDecision")[0]);
		samplingRepository.saveQCReqInfos(info);

	}
	
	
	
	
}
