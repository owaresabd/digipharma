package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.WhCssInfo;
import com.pms.model.WhRequestDetailsInfo;
import com.pms.model.WhRequestInfo;
import com.pms.repository.ProductionRepository;

@Service
public class ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	public List<WhRequestInfo> getAll(String status) {
		return productionRepository.findAll(status);
	}

	public CommonInfo getBatchInfoById(UUID id) {
		CommonInfo info = productionRepository.getBatchInfoById(id);
		return info;
	}
	public List<MaterialInfo> getAllProduct(String type,String status) {
		return productionRepository.findAllProduct(type,status);
	}
	public void saveProductionInfos(Map<String, String[]> requestMap, UUID idRandom) {
		WhRequestInfo info = new WhRequestInfo();
		String msId = requestMap.get("id")[0];
		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		
		info.setReqType(UUID.fromString(requestMap.get("reqType")[0]));
		info.setFromDeptNo(UUID.fromString(requestMap.get("fromDeptNo")[0]));
		info.setToDeptNo(UUID.fromString(requestMap.get("toDeptNo")[0]));
		info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		info.setMaterialCode(requestMap.get("materialCode")[0]);
		info.setMaterialTypeId(UUID.fromString(requestMap.get("materialTypeId")[0]));
		info.setSampleDetails(requestMap.get("sampleDetails")[0]);
		info.setStatus("P");
		info.setProviderType(requestMap.get("providerType")[0]);
		productionRepository.saveProductionInfos(info, idRandom);

	}
	public void saveProductionDetail(Map<String, String[]> requestMap, UUID idRandom) {
		WhRequestDetailsInfo info = new WhRequestDetailsInfo();
		String mstId =requestMap.get("id")[0];
		String materialId =requestMap.get("materialId")[0];
		String[] mfgDate = (String[]) requestMap.get("mfgDate[]");
		String[] expDate = (String[]) requestMap.get("expDate[]");
		String[] batchLot = (String[]) requestMap.get("batchLot[]");
		String[] batchNo = (String[]) requestMap.get("batchNo[]");
		String[] quantity = (String[]) requestMap.get("quantity[]");
		String[] unitId = (String[]) requestMap.get("unitId[]");
		String[] packSize = (String[]) requestMap.get("packSize[]");
		if(!mstId.isEmpty()) {
			List<WhRequestDetailsInfo> whReqDetailsList = this.getWhRequestDetailsById(UUID.fromString(mstId));
			  if(!whReqDetailsList.isEmpty()) {
				  productionRepository.deleteWhRequestDetailInfos(UUID.fromString(mstId)); 
				  
			  }
	      }
		//requestMap.containsKey() is used for param is  exists or not 
		if(requestMap.containsKey("mfgDate[]")) {
			
			for (int i = 0; i < mfgDate.length; i++) {
				
				if (mstId.trim().equals("")) {
					info.setWhRequestId(idRandom);
				} else {
					info.setWhRequestId(UUID.fromString(mstId));
				}
				
				if (mfgDate[i].trim().equals("")) {
					info.setMfgDate(null);
				} else {
					info.setMfgDate((Date) Utility.getSqlDate(mfgDate[i]));
				}
				if (expDate[i].trim().equals("")) {
					info.setExpDate(null);
				} else {
					info.setExpDate((Date) Utility.getSqlDate(expDate[i]));
				}
				
				info.setBatchLot(batchLot[i]);
				info.setBatchNo(batchNo[i]);
				info.setQuantity(quantity[i]);
				info.setUnitId(UUID.fromString(unitId[i]));
				info.setPackSize(packSize[i]);
				productionRepository.saveWhRequestDetail(info);
				productionRepository.updateBatchInfo(batchNo[i].trim(),UUID.fromString(materialId));

			}
		}
		
	}
	public List<WhRequestDetailsInfo> getWhRequestDetailsById(UUID whRequestId) {
		return productionRepository.getWhRequestDetailsById(whRequestId);
	}
	public WhRequestInfo getWhRequestInfoById(UUID id) {
		return productionRepository.getWhRequestInfoById(id);
	}
	
	public void saveCssRequestInfos(Map<String, String[]> requestMap) {
		WhCssInfo info = new WhCssInfo();
		
		String msId = requestMap.get("whRequestId")[0];
		
		info.setWhRequestId(UUID.fromString(msId));
		info.setStatus("P");
		productionRepository.saveCssRequestInfos(info);

	}
	
}
