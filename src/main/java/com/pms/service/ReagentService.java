package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.ReagentIssueInfo;
import com.pms.model.ReagentReceiveInfo;
import com.pms.repository.ReagentRepository;

@Service
public class ReagentService {

	@Autowired
	private ReagentRepository reagentRepository;

	
	public List<ReagentReceiveInfo> getAll(String status) {
		return reagentRepository.findAll(status);
	}
	
	public List<ReagentIssueInfo> getAllIssue(String status) {
		return reagentRepository.findAllIssue(status);
	}
	
	public List<CommonInfo> getStockInfos() {
		return reagentRepository.findStockInfos();
	}
	
	public void saveReceiveInfos(Map<String, String[]> requestMap) {
		
		ReagentReceiveInfo info=new ReagentReceiveInfo();
		String msId = requestMap.get("id")[0];
		String reagentId = requestMap.get("reagentId")[0];
		String receiveUnitId = requestMap.get("receiveUnitId")[0];
		//String receiveBy = requestMap.get("receiveBy")[0];
		String manufacturerId = requestMap.get("manufacturerId")[0];
		String countryId = requestMap.get("countryId")[0];
		
		if (msId.trim().equals("")) {
				info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		if (reagentId.equals("")) {
			info.setReagentId(null);
	     }else {
	    	 info.setReagentId(UUID.fromString(requestMap.get("reagentId")[0])); 
	     }
		if (receiveUnitId.equals("")) {
			info.setReceiveUnitId(null);
	     }else {
	    	 info.setReceiveUnitId(UUID.fromString(requestMap.get("receiveUnitId")[0])); 
	     }
		
		if (manufacturerId.equals("")) {
			info.setManufacturerId(null);
	     }else {
	    	 info.setManufacturerId(UUID.fromString(requestMap.get("manufacturerId")[0])); 
	     }
		if (countryId.trim().equals("")) {
			info.setCountryId(null);
	     }else {
	    	 info.setCountryId(UUID.fromString(requestMap.get("countryId")[0])); 
	     }
		
		info.setReceiveQty(requestMap.get("receiveQty")[0]);
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setPackSize(requestMap.get("packSize")[0]);
		info.setPurity(requestMap.get("purity")[0]);
		
		if(!requestMap.get("receiveDate")[0].equals("") && !requestMap.get("receiveDate")[0].equals(null)) {
			info.setReceiveDate((Date) Utility.getSqlDate(requestMap.get("receiveDate")[0]));
		}else {
			info.setReceiveDate(null);
		}
		if(!requestMap.get("mfgDate")[0].equals("") && !requestMap.get("mfgDate")[0].equals(null)) {
			info.setMfgDate((Date) Utility.getSqlDate(requestMap.get("mfgDate")[0]));
		}else {
			info.setMfgDate(null);
		}
		if(!requestMap.get("expDate")[0].equals("") && !requestMap.get("expDate")[0].equals(null)) {
			info.setExpDate((Date) Utility.getSqlDate(requestMap.get("expDate")[0]));
		}else {
			info.setExpDate(null);
		}
		
		reagentRepository.saveReceiveInfo(info);
	}
	
	
	public void saveIssueInfos(Map<String, String[]> requestMap) {
		
		ReagentIssueInfo info=new ReagentIssueInfo();
		String msId = requestMap.get("id")[0];
		String reagentId = requestMap.get("reagentId")[0];
		String issueUnitId = requestMap.get("issueUnitId")[0];
		
		if (msId.trim().equals("")) {
				info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		if (reagentId.equals("")) {
			info.setReagentId(null);
	     }else {
	    	 info.setReagentId(UUID.fromString(requestMap.get("reagentId")[0])); 
	     }
		if (issueUnitId.equals("")) {
			info.setIssueUnitId(null);
	     }else {
	    	 info.setIssueUnitId(UUID.fromString(requestMap.get("issueUnitId")[0])); 
	     }
		
		info.setStockQty(requestMap.get("stockQty")[0]);
		info.setIssueQty(requestMap.get("issueQty")[0]);
		info.setLabType(requestMap.get("labType")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		
		reagentRepository.saveIssueInfo(info);
	}
	
	public CommonInfo getReagentInfoById(UUID id) {
		CommonInfo info = reagentRepository.findReagentInfoById(id);
		return info;
	}
	
	
}
