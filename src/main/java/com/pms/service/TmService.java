package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.TmInfo;
import com.pms.repository.TmRepository;

@Service
public class TmService {

	@Autowired
	private TmRepository tmRepository;

	public List<CommonInfo> getAll() {
		return tmRepository.findAll();
	}
	
	public List<CommonInfo> getApprovedList() {
		return tmRepository.findApprovedList();
	}
	
	public CommonInfo getSampleApprovedInfo(UUID id) {
		return tmRepository.findSampleApprovedInfo(id);
	}
	
	public List<CommonInfo> getVerifiedChdList(UUID sampleRcvId) {
		return tmRepository.findVerifiedChdList(sampleRcvId);
	}
	
	public void saveResultInfo(Map<String, String[]> requestMap) {
		 TmInfo info = new TmInfo();
		  String sampleId = requestMap.get("sampleId")[0];
		  String sampleRcvId = requestMap.get("sampleRcvId")[0];
		  String remarks = requestMap.get("remarks")[0];
		  String note = requestMap.get("note")[0];
		  
		  if(requestMap.containsKey("sampleId")) {
		  info.setSampleId(UUID.fromString(sampleId));
		  info.setSampleRcvId(UUID.fromString(sampleRcvId));
		  info.setRemarks(remarks);
		  info.setNote(note);
		  
		  tmRepository.saveResultVerifyByTmInfo(info);
	
		  }
	}
	
	
}
