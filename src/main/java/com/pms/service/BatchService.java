package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.BatchInfo;
import com.pms.model.MaterialInfo;
import com.pms.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository batchRepository;

	public List<BatchInfo> getAll(String status) {
		return batchRepository.findAll(status);
	}

	public void saveBatchInfos(Map<String, String[]> requestMap) {
		
		BatchInfo info=new BatchInfo();
		String msId = requestMap.get("id")[0];
			if (msId.trim().equals("")) {
			info.setId(null);
		     }else {
		    	 info.setId(UUID.fromString(requestMap.get("id")[0])); 
		     }
		info.setProductId(UUID.fromString(requestMap.get("productId")[0]));
		info.setBatchNo(requestMap.get("batchNo")[0]);
		/*
		 * if(!requestMap.get("batchDate")[0].equals("") &&
		 * !requestMap.get("batchDate")[0].equals(null)) { info.setBatchDate((Date)
		 * Utility.getSqlDate(requestMap.get("batchDate")[0])); }else {
		 * info.setBatchDate(null); }
		 */
		info.setBatchSize(requestMap.get("batchSize")[0]);
		info.setUnitId(UUID.fromString(requestMap.get("unitId")[0]));
		info.setLotNo(requestMap.get("lotNo")[0]);
		info.setShelfLife(requestMap.get("shelfLife")[0]);
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
		
		batchRepository.saveBatchInfos(info);
	}
	
	public List<MaterialInfo> getAllProduct(String type,String status) {
		return batchRepository.findAllProduct(type,status);
	}

}
