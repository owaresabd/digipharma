package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.RetentionSampleRecvInfo;
import com.pms.repository.RetentionRepository;

@Service
public class RetentionService {

	@Autowired
	private RetentionRepository retentionRepository;

	public List<CommonInfo> getAll() {
		return retentionRepository.findAll();
	}
	
	public CommonInfo getRetentionInfo(UUID id) {
		return retentionRepository.getRetentionInfo(id);
	}
	
	public void saveSampleRcvInfo(Map<String, String[]> requestMap) {
		RetentionSampleRecvInfo info = new RetentionSampleRecvInfo();
		//String retUnitId = requestMap.get("retUnitId")[0];
		
		info.setRetReqId(UUID.fromString(requestMap.get("retReqId")[0]));
		info.setSampleDesc(requestMap.get("sampleDesc")[0]);
		info.setRetQty(requestMap.get("retQty")[0]);
		info.setRetUnitId(UUID.fromString(requestMap.get("retUnitId")[0]));
		info.setRetEquId(UUID.fromString(requestMap.get("retEquId")[0]));
		info.setRetRoomId(UUID.fromString(requestMap.get("retRoomId")[0]));
		info.setRetRackId(UUID.fromString(requestMap.get("retRackId")[0]));
		info.setSampleId(UUID.fromString(requestMap.get("sampleId")[0]));
		
		retentionRepository.saveSampleRcvInfo(info);
	}
	
	public List<CommonInfo> receivedList() {
		return retentionRepository.receivedList();
	}
	
	public CommonInfo getSampleReceiveInfo(UUID id) {
		return retentionRepository.getSampleReceiveInfo(id);
	}
	
}
