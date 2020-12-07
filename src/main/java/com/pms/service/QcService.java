package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.QcSampleRecvInfo;
import com.pms.repository.QcRepository;

@Service
public class QcService {

	@Autowired
	private QcRepository qcRepository;

	public List<CommonInfo> getAll() {
		return qcRepository.findAll();
	}
	
	public CommonInfo getQcInfo(UUID id) {
		return qcRepository.getQcInfo(id);
	}
	
	public void saveSampleRcvInfo(Map<String, String[]> requestMap) {
		QcSampleRecvInfo info = new QcSampleRecvInfo();
		
		info.setQcReqId(UUID.fromString(requestMap.get("qcReqId")[0]));
		info.setSampleDesc(requestMap.get("sampleDesc")[0]);
		info.setStChemiQty(requestMap.get("stChemiQty")[0]);
		info.setStMicroQty(requestMap.get("stMicroQty")[0]);
		info.setStQty(requestMap.get("stQty")[0]);
		if(requestMap.get("stUnitId")[0] !=null && !requestMap.get("stUnitId")[0].isEmpty()) {
			info.setStUnitId(UUID.fromString(requestMap.get("stUnitId")[0]));	
		}else {
			
			info.setStUnitId(null);
}
		
		
		info.setStEquId(UUID.fromString(requestMap.get("stEquId")[0]));
		info.setStRoomId(UUID.fromString(requestMap.get("stRoomId")[0]));
		info.setStRackId(UUID.fromString(requestMap.get("stRackId")[0]));
		info.setSampleId(UUID.fromString(requestMap.get("sampleId")[0]));
		
		qcRepository.saveSampleRcvInfo(info);
	}
	
	public List<CommonInfo> receivedList() {
		return qcRepository.receivedList();
	}
	
}
