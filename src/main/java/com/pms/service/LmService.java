package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.LmInfo;
import com.pms.repository.LmRepository;

@Service
public class LmService {

	@Autowired
	private LmRepository lmRepository;

	public List<CommonInfo> getAll() {
		return lmRepository.findAll();
	}
	
	public List<CommonInfo> getVerifiedChdList(UUID sampleRcvId) {
		return lmRepository.findVerifiedChdList(sampleRcvId);
	}
	
	
	public void saveResultInfo( UUID idRandom,Map<String, String[]> requestMap) {
		LmInfo info = new LmInfo();
		  String sampleId = requestMap.get("sampleId")[0];
		  String sampleRcvId = requestMap.get("sampleRcvId")[0];
		  String opinion = requestMap.get("opinion")[0];
		  String remarks = requestMap.get("remarks")[0];
		  String[] resultId = (String[]) requestMap.get("resultId[]");
		  String[] isDecision = (String[]) requestMap.get("isDecision[]");
		  
		  if(requestMap.containsKey("sampleId")) {
		  info.setSampleId(UUID.fromString(sampleId));
		  info.setSampleRcvId(UUID.fromString(sampleRcvId));
		  info.setOpinion(opinion);
		  info.setRemarks(remarks);


		  
		  
		  lmRepository.saveResultVerifyByLmInfo(idRandom,info);
		  for (int i = 0; i < resultId.length; i++) {
			 
			  lmRepository.updateResultVerifyByLmChdInfo(UUID.fromString(resultId[i]),isDecision[i]);
			  
		  }
		  
	
		  }
	}
	
	
}
