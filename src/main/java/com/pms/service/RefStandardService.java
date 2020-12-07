package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.RefStandSetupInfo;
import com.pms.model.RefStandardInfo;
import com.pms.model.RefStandardIssueInfo;
import com.pms.repository.RefStandardRepository;

@Service
public class RefStandardService {

	@Autowired
	private RefStandardRepository refStandardRepository;

	
	public List<RefStandardInfo> getAll(String status) {
		return refStandardRepository.findAll(status);
	}
	public List<RefStandardInfo> getRefNotificationInfos() {
		return refStandardRepository.findRefNotificationInfos();
	}
	
	public List<RefStandardIssueInfo> getAllReferenceIssue() {
		return refStandardRepository.findAllReferenceIssue();
	}
	
	public List<RefStandSetupInfo> getRefStandSetupInfo(String status) {
		return refStandardRepository.findRefStandSetupInfo(status);
	}
	
	public RefStandardInfo getReferenceById(UUID id) {
		RefStandardInfo info = refStandardRepository.findReferenceById(id);
		return info;
	}
	
	public void saveRefStandSetupInfo(RefStandSetupInfo info) {
		refStandardRepository.saveRefStandSetupInfo(info);
	}
	
	public void saveRefStandardInfos(Map<String, String[]> requestMap) {
		RefStandardInfo info=new RefStandardInfo();
		String msId 			= requestMap.get("id")[0];
		String refStandardId 	= requestMap.get("refStandardId")[0];
		String sourceSupplierId = requestMap.get("sourceSupplierId")[0];
		String storageId 		= requestMap.get("storageCondition")[0];
		String unitId 			= requestMap.get("unitId")[0];
		
		if (msId.trim().equals("")) {
				info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		if (refStandardId.equals("") ) {
			info.setRefStandardId(null);
	     }else {
	    	 info.setRefStandardId(UUID.fromString(requestMap.get("refStandardId")[0])); 
	     }
		if (sourceSupplierId.equals("") ) {
			info.setSourceSupplierId(null);
	     }else {
	    	 info.setSourceSupplierId(UUID.fromString(requestMap.get("sourceSupplierId")[0])); 
	     }
		if (unitId.trim().equals("")) {
			info.setUnitId(null);
	     }else {
	    	 info.setUnitId(UUID.fromString(requestMap.get("unitId")[0])); 
	     }
		if (storageId.equals("") ) {
			info.setStorageCondition(null);
	     }else {
	    	 info.setStorageCondition(UUID.fromString(requestMap.get("storageCondition")[0])); 
	     }
		
		info.setBatchNo(requestMap.get("batchNo")[0]);
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setIdType(requestMap.get("idType")[0]);
		info.setCatNo(requestMap.get("catNo")[0]);
		info.setRcvQty(requestMap.get("rcvQty")[0]);
		info.setPotency(requestMap.get("potency")[0]);
		if( requestMap.get("rcvDate")[0]!=null && !requestMap.get("rcvDate")[0].equals("")) {
			info.setRcvDate((Date) Utility.getSqlDate(requestMap.get("rcvDate")[0]));
		}else {
			info.setRcvDate(null);
		}
		if(requestMap.get("validDate")[0] !=null  && !requestMap.get("validDate")[0].equals("")) {
			info.setValidDate((Date) Utility.getSqlDate(requestMap.get("validDate")[0]));
		}else {
			info.setValidDate(null);
		}
		
		refStandardRepository.saveRefStandardInfo(info);
	}
	
	public void saveRefStandardIssueInfos(Map<String, String[]> requestMap) {
		RefStandardIssueInfo info=new RefStandardIssueInfo();
		String msId 			= requestMap.get("id")[0];
		String refStandardChildId = requestMap.get("refStandardChildId")[0];
		String unitId 			= requestMap.get("unitId")[0];
		
		if (msId.trim().equals("")) {
				info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		if (refStandardChildId.equals("")) {
			info.setRefStandardChildId(null);
	     }else {
	    	 info.setRefStandardChildId(UUID.fromString(requestMap.get("refStandardChildId")[0])); 
	     }
		if (unitId.trim().equals("")) {
			info.setUnitId(null);
	     }else {
	    	 info.setUnitId(UUID.fromString(requestMap.get("unitId")[0])); 
	     }
		if(!requestMap.get("usedDate")[0].equals("") && !requestMap.get("usedDate")[0].equals(null)) {
			info.setUsedDate((Date) Utility.getSqlDate(requestMap.get("usedDate")[0]));
		}else {
			info.setUsedDate(null);
		}
		info.setUsedQty(requestMap.get("usedQty")[0]);
		info.setPurpose(requestMap.get("purpose")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		
		refStandardRepository.saveRefStandardIssueInfo(info);
	}
	
	public boolean validateName(Map<String, String[]> requestMap) {
		String name = requestMap.get("refStandardName")[0];
		List<RefStandSetupInfo> entityTransList = refStandardRepository.validateRefStandName(name);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
	
	
}
