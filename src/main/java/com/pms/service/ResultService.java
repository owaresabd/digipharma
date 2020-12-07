package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.ResultInfo;
import com.pms.repository.ResultRepository;

@Service
public class ResultService {

	@Autowired
	private ResultRepository resultRepository;

	public List<CommonInfo> getAll() {
		return resultRepository.findAll();
	}
	public List<CommonInfo> getAllSingle() {
		return resultRepository.findAllSingle();
	}
	public void receiveByAnalyst(Map<String, String[]> map) {
		UUID sampleId = UUID.fromString(map.get("sampleId")[0]);
		UUID analystId = UUID.fromString(map.get("analystId")[0]);
		resultRepository.receiveByAnalyst(sampleId, analystId);
	}
	public List<CommonInfo> getAllResultInputedSingle() {
		return resultRepository.findAllResultInputedSingle();
	}
	
	public CommonInfo getDistributionInfo(UUID id) {
		return resultRepository.getDistributionInfo(id);
	}
	public CommonInfo getDistributionSingleInfo(UUID id,UUID anId) {
		return resultRepository.getDistributionSingleInfo(id,anId);
	}
	public List<CommonInfo> getDistributionInfosSingle(UUID sampleId,UUID analystId) {
		return resultRepository.getDistributionInfosSingle(sampleId,analystId);
	}
	public List<CommonInfo> getSubmittedList(String paramType) {
		return resultRepository.findSubmittedList(paramType);
	}
	public List<CommonInfo> getAcceptedList(String paramType) {
		return resultRepository.findAcceptedList(paramType);
	}
	public List<CommonInfo> getSubmittedChdList(UUID sampleRcvId, String paramType) {
		return resultRepository.findSubmittedChdList(sampleRcvId, paramType);
	}
	public List<CommonInfo> getAnalystSubmittedChdList(UUID sampleId, UUID analystId) {
		return resultRepository.findAnalystSubmittedChdList(sampleId, analystId);
	}
	
	public CommonInfo getTestResultDetailsInfo(UUID id) {
		return resultRepository.getTestResultDetailsInfo(id);
	}
	

	 
	
	public void saveResultInfoBack(UUID idRandom,String fileName,Map<String, String[]> requestMap) {
		ResultInfo info = new ResultInfo();
		
		  String mstId =requestMap.get("id")[0];
		  String unitId = requestMap.get("unitId")[0];
		  String testResult = requestMap.get("testResult")[0];
		  String remarks = requestMap.get("remarks")[0];
		  
		  if(requestMap.containsKey("unitId")) {
		  info.setDistributionId(UUID.fromString(mstId));
		  info.setTestResult(testResult);
		  if(!unitId.equals("") && !unitId.equals(null)) {
		  info.setUnitId(UUID.fromString(unitId));
		  }else {
			  info.setUnitId(null);  
			  
		  }
		  info.setRemarks(remarks);
			if(fileName !=null) {
				info.setDocLocation(fileName);	
			 }else {
				 		 
			 }
		  
		  
		  resultRepository.saveResultInfo(info);
	
		  }
	}
	
	public void saveResultInfo(ResultInfo info) {
		
		  
		  resultRepository.saveResultInfo(info);
		  
	}
	
	public void saveResultAcceptInfo(UUID id) {	  
		  
		  resultRepository.saveResultAcceptInfo(id);
	
		  }
	
	
}
