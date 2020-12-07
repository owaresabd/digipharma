package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.WorkingStandardInfo;
import com.pms.model.WorkingStandardIssueInfo;
import com.pms.model.WorkingStandardRequestInfo;
import com.pms.repository.CssRequestRepository;
import com.pms.repository.WorkingStandardRepository;

@Service
public class WorkingStandardService {

	@Autowired
	private WorkingStandardRepository workingStandardRepository;
	@Autowired
	private CssRequestRepository cssRequestRepository;
	public List<WorkingStandardInfo> getAll() {
		return workingStandardRepository.findAll();
	}
	
	

	public List<WorkingStandardRequestInfo> getAllWsInfos(String status) {
		return workingStandardRepository.findAllWsInfos(status);
	}
	public void saveWorkStandardInfo(WorkingStandardInfo info) {
		workingStandardRepository.saveWorkStandardInfo(info);
	}
	
	public WorkingStandardInfo getWorkStandardById(UUID id) {
		WorkingStandardInfo info = workingStandardRepository.findWorkStandardById(id);
		return info;
	}
	
	public CommonInfo getWsDetailInfo(UUID id) {
		CommonInfo info = cssRequestRepository.findWsDetailInfo(id);
		return info;
	}
	
	
	public List<WorkingStandardIssueInfo> getAllWorkIssue() {
		return workingStandardRepository.findAllWorkIssue();
	}
	
	public void saveWorkStandardIssueInfos(Map<String, String[]> requestMap) {
		WorkingStandardIssueInfo info=new WorkingStandardIssueInfo();
		String msId 			= requestMap.get("id")[0];
		String workStandardChildId = requestMap.get("workStandardChildId")[0];
		
		if (msId.trim().equals("")) {
				info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		if (workStandardChildId.equals("")) {
			info.setWorkStandardChildId(null);
	     }else {
	    	 info.setWorkStandardChildId(UUID.fromString(requestMap.get("workStandardChildId")[0])); 
	     }
		if(!requestMap.get("openingDate")[0].equals(null) && !requestMap.get("openingDate")[0].equals("")) {
			info.setOpeningDate((Date) Utility.getSqlDate(requestMap.get("openingDate")[0]));
		}else {
			info.setOpeningDate(null);
		}
		if(!requestMap.get("usedDate")[0].equals(null) && !requestMap.get("usedDate")[0].equals("")) {
			info.setUsedDate((Date) Utility.getSqlDate(requestMap.get("usedDate")[0]));
		}else {
			info.setUsedDate(null);
		}
		if(!requestMap.get("validityDate")[0].equals(null) && !requestMap.get("validityDate")[0].equals("")) {
			info.setValidityDate((Date) Utility.getSqlDate(requestMap.get("validityDate")[0]));
		}else {
			info.setValidityDate(null);
		}
		info.setVialNo(requestMap.get("vialNo")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		
		workingStandardRepository.saveWorkStandardIssueInfo(info);
	}
	public void saveWsRequestInfos(Map<String, String[]> requestMap) {
		WorkingStandardRequestInfo info=new WorkingStandardRequestInfo();
		String msId 			= requestMap.get("id")[0];
		if (msId.trim().equals("")) {
				info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		
	    	 info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
	    
	    info.setKeptAmount(requestMap.get("keptAmount")[0]);
	    info.setRemarks(requestMap.get("remarks")[0]);
		info.setStatus("P");
		
		workingStandardRepository.saveWsRequestInfos(info);
	}

	
}
