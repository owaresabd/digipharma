package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.SamplingInfo;
import com.pms.repository.CssRequestRepository;

@Service
public class CssRequestService {

	@Autowired
	private CssRequestRepository cssRequestRepository;

	public List<CommonInfo> getAll() {
		return cssRequestRepository.findAll();
	}
	
	public CommonInfo getCssReqDetailsInfo(UUID id) {
		return cssRequestRepository.getCssReqDetailsInfo(id);
	}

	public void saveSampleInfos(Map<String, String[]> requestMap) {
		SamplingInfo info = new SamplingInfo();
		
		info.setCssRequestId(UUID.fromString(requestMap.get("cssRequestId")[0]));
		info.setClientName(requestMap.get("clientName")[0]);
		info.setClientId(requestMap.get("clientId")[0]);
		info.setMethodId(UUID.fromString(requestMap.get("methodId")[0]));
		info.setSampleProcedure(requestMap.get("sampleProcedure")[0]);
		//info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));
		info.setSamplingBy(UUID.fromString(requestMap.get("samplingBy")[0]));
		info.setStorageConId(UUID.fromString(requestMap.get("storageConId")[0]));
		info.setStorageCondition(requestMap.get("storageCondition")[0]);
		if(!requestMap.get("areaBoothId")[0].equals("") && requestMap.get("areaBoothId")[0] !=null) {
			info.setAreaBoothId(UUID.fromString(requestMap.get("areaBoothId")[0]));	
		}else {
			info.setAreaBoothId(null);
		}
		if(!requestMap.get("equipmentId")[0].equals("") && requestMap.get("equipmentId")[0] !=null) {
			info.setEquipmentId(UUID.fromString(requestMap.get("equipmentId")[0]));	
		}else {
			info.setEquipmentId(null);
		}
		info.setWorkInsId(UUID.fromString(requestMap.get("workInsId")[0]));
		info.setPrecautionTaken(requestMap.get("precautionTaken")[0]);
		if (requestMap.get("samplingDate")[0].equals("")) {
			info.setSamplingDate(null);
		} else {
			info.setSamplingDate((Date) Utility.getSqlDate(requestMap.get("samplingDate")[0]));
		}
		
		info.setRemarks(requestMap.get("remarks")[0]);
		
		cssRequestRepository.saveSampleInfos(info);

	}
	public List<CommonInfo> findReports(String isWorkingStandard) {
		return cssRequestRepository.findReports(isWorkingStandard);
	}
	public List<CommonInfo> findWsReports() {
		return cssRequestRepository.findWsReports();
	}
	public List<CommonInfo> findWsInfos() {
		return cssRequestRepository.findWsInfos();
	}
	
	public void updateTestDate(Map<String, String[]> requestMap) {
		CommonInfo info=new  CommonInfo();
		String[] resultId = (String[]) requestMap.get("resultId[]");
		String[] testDate = (String[]) requestMap.get("testDate[]");
		
		for (int i = 0; i < resultId.length; i++) {
		
		info.setResultId(UUID.fromString(resultId[i].trim()));
		info.setReportDate((Date) Utility.getSqlDate(testDate[i].trim()));
		cssRequestRepository.updateTestDate(info);
		}

	}
	
	
}
