package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.WhCssInfo;
import com.pms.model.WhRequestDetailsInfo;
import com.pms.model.WhRequestInfo;
import com.pms.repository.WhRequestRepository;

@Service
public class WhRequestService {

	@Autowired
	private WhRequestRepository whRequestRepository;

	public List<WhRequestInfo> getAll(String status) {
		return whRequestRepository.findAll(status);
	}

	

	public void saveWhRequestInfos(Map<String, String[]> requestMap, UUID idRandom) {
		WhRequestInfo info = new WhRequestInfo();
		String msId = requestMap.get("id")[0];
		String rcvUnitId = requestMap.get("rcvUnitId")[0];
		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		
		info.setReqType(UUID.fromString(requestMap.get("reqType")[0]));
		info.setInvoiceNo(requestMap.get("invoiceNo")[0]);
		info.setChalanNo(requestMap.get("chalanNo")[0]);
		info.setLcNo(requestMap.get("lcNo")[0]);
		info.setFromDeptNo(UUID.fromString(requestMap.get("fromDeptNo")[0]));
		info.setToDeptNo(UUID.fromString(requestMap.get("toDeptNo")[0]));
		info.setGrNo(requestMap.get("grNo")[0]);
		info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		info.setMaterialCode(requestMap.get("materialCode")[0]);
		info.setMaterialTypeId(UUID.fromString(requestMap.get("materialTypeId")[0]));
		info.setRcvQty(requestMap.get("rcvQty")[0]);
		if (rcvUnitId.trim().equals("")) {
			info.setRcvUnitId(null);
		} else {
			info.setRcvUnitId(UUID.fromString(rcvUnitId.toString()));
		}
		info.setManufactureId(UUID.fromString(requestMap.get("manufactureId")[0]));
		info.setManufactureName(requestMap.get("manufactureName")[0]);
		info.setCountryId(UUID.fromString(requestMap.get("countryId")[0]));
		info.setSampleDetails(requestMap.get("sampleDetails")[0]);
		info.setStatus("P");
		info.setProviderType(requestMap.get("providerType")[0]);
		whRequestRepository.saveWhRequestInfos(info, idRandom);

	}
	public void saveWsRequestInfos(Map<String, String[]> requestMap, UUID idRandom) {
		WhRequestInfo info = new WhRequestInfo();
		String msId = requestMap.get("id")[0];
		String rcvUnitId = requestMap.get("rcvUnitId")[0];
		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		info.setWsRequestId(UUID.fromString(requestMap.get("wsRequestId")[0]));
		info.setKeptAmount(requestMap.get("keptAmount")[0]);
		info.setReqType(UUID.fromString(requestMap.get("reqType")[0]));
		info.setInvoiceNo(requestMap.get("invoiceNo")[0]);
		info.setChalanNo(requestMap.get("chalanNo")[0]);
		info.setLcNo(requestMap.get("lcNo")[0]);
		info.setFromDeptNo(UUID.fromString(requestMap.get("fromDeptNo")[0]));
		info.setToDeptNo(UUID.fromString(requestMap.get("toDeptNo")[0]));
		info.setGrNo(requestMap.get("grNo")[0]);
		info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		info.setMaterialCode(requestMap.get("materialCode")[0]);
		info.setMaterialTypeId(UUID.fromString(requestMap.get("materialTypeId")[0]));
		info.setRcvQty(requestMap.get("rcvQty")[0]);
		if (rcvUnitId.trim().equals("")) {
			info.setRcvUnitId(null);
		} else {
			info.setRcvUnitId(UUID.fromString(rcvUnitId.toString()));
		}
		info.setManufactureId(UUID.fromString(requestMap.get("manufactureId")[0]));
		info.setManufactureName(requestMap.get("manufactureName")[0]);
		info.setCountryId(UUID.fromString(requestMap.get("countryId")[0]));
		info.setSampleDetails(requestMap.get("sampleDetails")[0]);
		info.setStatus("P");
		info.setProviderType(requestMap.get("providerType")[0]);
		whRequestRepository.saveWsRequestInfos(info, idRandom);

	}
	
	public void saveWhRequestDetail(Map<String, String[]> requestMap, UUID idRandom) {
		WhRequestDetailsInfo info = new WhRequestDetailsInfo();
		String mstId =requestMap.get("id")[0];
		String[] mfgDate = (String[]) requestMap.get("mfgDate[]");
		String[] expDate = (String[]) requestMap.get("expDate[]");
		String[] batchLot = (String[]) requestMap.get("batchLot[]");
		String[] batchNo = (String[]) requestMap.get("batchNo[]");
		String[] quantity = (String[]) requestMap.get("quantity[]");
		String[] unitId = (String[]) requestMap.get("unitId[]");
		String[] noOfDrums = (String[]) requestMap.get("noOfDrums[]");
		if(!mstId.isEmpty()) {
			List<WhRequestDetailsInfo> whReqDetailsList = this.getWhRequestDetailsById(UUID.fromString(mstId));
			  if(!whReqDetailsList.isEmpty()) {
				  whRequestRepository.deleteWhRequestDetailInfos(UUID.fromString(mstId)); 
				  
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
				
				info.setGrNo(requestMap.get("grNo")[0]);
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
				info.setNoOfDrums(noOfDrums[i]);
				whRequestRepository.saveWhRequestDetail(info);

			}
		}
		
	}
	public List<WhRequestDetailsInfo> getWhRequestDetailsById(UUID whRequestId) {
		return whRequestRepository.getWhRequestDetailsById(whRequestId);
	}
	public WhRequestInfo getWhRequestInfoById(UUID id) {
		return whRequestRepository.getWhRequestInfoById(id);
	}
	public void saveCssRequestInfos(Map<String, String[]> requestMap) {
		WhCssInfo info = new WhCssInfo();
		
		String msId = requestMap.get("whRequestId")[0];
		
		info.setWhRequestId(UUID.fromString(msId));
		info.setStatus("P");
		whRequestRepository.saveCssRequestInfos(info);

	}
	
}
