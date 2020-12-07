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
import com.pms.model.PtSampleInfo;
import com.pms.model.WhRequestDetailsInfo;
import com.pms.model.WhRequestInfo;
import com.pms.repository.PtSampleRepository;

@Service
public class PtSampleService {

	@Autowired
	private PtSampleRepository ptSampleRepository;

	public List<CommonInfo> getAll() {
		return ptSampleRepository.findAll();
	}

	public List<MaterialInfo> getAllPtProduct(String status) {
		return ptSampleRepository.findAllPtProduct(status);
	}

	public void savePtSampleInfo(Map<String, String[]> requestMap, UUID idRandom) {
		PtSampleInfo info = new PtSampleInfo();
		String quantity = requestMap.get("quantity")[0];
		String unitId = requestMap.get("unitId")[0];
		info.setWhRequestId(idRandom);
		info.setReqTypeId(UUID.fromString(requestMap.get("reqTypeId")[0]));
		info.setFromDate((Date) Utility.getSqlDate(requestMap.get("fromDate")[0]));
		info.setToDate((Date) Utility.getSqlDate(requestMap.get("toDate")[0]));
		info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		info.setMaterialCode(requestMap.get("materialCode")[0]);
		info.setMaterialTypeId(UUID.fromString(requestMap.get("materialTypeId")[0]));
		info.setTypeCode(requestMap.get("typeCode")[0]);
		info.setSampleDetails(requestMap.get("sampleDetails")[0]);
		info.setQuantity(quantity);
		info.setUnitId(UUID.fromString(unitId));
		info.setStatus("C");
		ptSampleRepository.savePtSampleInfo(info);

	}
	
	public void updatePtSampleInfo(Map<String, String[]> requestMap) {
		
		PtSampleInfo info = new PtSampleInfo();
		
		UUID whRequestId = UUID.fromString(requestMap.get("whRequestId")[0]);
		UUID whRequestDetailId = UUID.fromString(requestMap.get("whRequestDetailId")[0]);
		UUID sampleId = UUID.fromString(requestMap.get("sampleId")[0]);
		UUID qcReqId = UUID.fromString(requestMap.get("qcReqId")[0]);
		
		// Below Code for primary key of very table 
		info.setWhRequestId(whRequestId);
		info.setWhRequestChdId(whRequestDetailId);
		info.setSampleId(sampleId);
		info.setQcReqId(qcReqId);
		
		String quantity = requestMap.get("quantity")[0];
		String unitId = requestMap.get("unitId")[0];
		info.setReqTypeId(UUID.fromString(requestMap.get("reqTypeId")[0]));
		info.setFromDate((Date) Utility.getSqlDate(requestMap.get("fromDate")[0]));
		info.setToDate((Date) Utility.getSqlDate(requestMap.get("toDate")[0]));
		info.setMaterialId(UUID.fromString(requestMap.get("materialId")[0]));
		info.setMaterialCode(requestMap.get("materialCode")[0]);
		info.setMaterialTypeId(UUID.fromString(requestMap.get("materialTypeId")[0]));
		info.setTypeCode(requestMap.get("typeCode")[0]);
		info.setSampleDetails(requestMap.get("sampleDetails")[0]);
		info.setQuantity(quantity);
		info.setUnitId(UUID.fromString(unitId));
		
		String batchLot = requestMap.get("batchLot")[0];
		String batchNo = requestMap.get("batchNo")[0];
		String mfgDate = requestMap.get("mfgDate")[0];
		String expDate = requestMap.get("expDate")[0];
		String packSize = requestMap.get("packSize")[0];
		
		info.setBatchLot(batchLot);
		info.setBatchNo(batchNo);
		
		if (mfgDate.trim().equals("")) {
			info.setMfgDate(null);
		} else {
			info.setMfgDate((Date) Utility.getSqlDate(mfgDate));
		}
		if (expDate.trim().equals("")) {
			info.setExpDate(null);
		} else {
			info.setExpDate((Date) Utility.getSqlDate(expDate));
		}
		
		info.setPackSize(packSize);
		if(!requestMap.get("storageConId")[0].equals(null) && !requestMap.get("storageConId")[0].isEmpty()) {
			info.setStorageConId(UUID.fromString(requestMap.get("storageConId")[0]));
			
		}else {
			info.setStorageConId(null);
			
			
		}
		
		info.setSupplierName(requestMap.get("supplierName")[0]);

		info.setRemarks(requestMap.get("remarks")[0]);
		
		ptSampleRepository.updatePtSampleInfo(info);
		
		
	}

	public void savePtSampleDetailsInfo(Map<String, String[]> requestMap, UUID whReqChdId, UUID whReqMstId) {
		PtSampleInfo info = new PtSampleInfo();

		String batchLot = requestMap.get("batchLot")[0];
		String batchNo = requestMap.get("batchNo")[0];
		String mfgDate = requestMap.get("mfgDate")[0];
		String expDate = requestMap.get("expDate")[0];
		String quantity = requestMap.get("quantity")[0];
		String unitId = requestMap.get("unitId")[0];
		String packSize = requestMap.get("packSize")[0];

		info.setWhRequestChdId(whReqChdId);
		info.setWhRequestId(whReqMstId);
		info.setBatchLot(batchLot);
		info.setBatchNo(batchNo);
		
		if (mfgDate.trim().equals("")) {
			info.setMfgDate(null);
		} else {
			info.setMfgDate((Date) Utility.getSqlDate(mfgDate));
		}
		if (expDate.trim().equals("")) {
			info.setExpDate(null);
		} else {
			info.setExpDate((Date) Utility.getSqlDate(expDate));
		}

		
		info.setQuantity(quantity);
		info.setUnitId(UUID.fromString(unitId));
		info.setPackSize(packSize);
		ptSampleRepository.savePtSampleDetailsInfo(info);

	}

	public void saveCssRequestInfos(Map<String, String[]> requestMap, UUID cssReqId,UUID whRequestId) {
		PtSampleInfo info = new PtSampleInfo();
		info.setCssReqId(cssReqId);
		info.setWhRequestId(whRequestId);
		info.setStatus("S");
		ptSampleRepository.saveCssRequestInfos(info);

	}

	public void saveSampleInfos(Map<String, String[]> requestMap,UUID sampleId,UUID cssReqId) {
		PtSampleInfo info = new PtSampleInfo();

		info.setSampleId(sampleId);
		info.setCssReqId(cssReqId);
		
		info.setStorageConId(UUID.fromString(requestMap.get("storageConId")[0]));
		info.setSupplierName(requestMap.get("supplierName")[0]);

		info.setRemarks(requestMap.get("remarks")[0]);
		info.setStatus("Q");
		ptSampleRepository.saveSampleInfos(info);

	}

	public void saveQCReqInfos(Map<String, String[]> requestMap,UUID qcReqId,UUID sampleId ) {
		PtSampleInfo info = new PtSampleInfo();

		info.setQcReqId(qcReqId);
		info.setSampleId(sampleId);
		info.setSampleDetails(requestMap.get("sampleDetails")[0]);
		info.setIsDecision("N");
		info.setStatus(requestMap.get("typeCode")[0]);
		ptSampleRepository.saveQCReqInfos(info);

	}
	public void sendQCReqInfos(Map<String, String[]> requestMap) {
		PtSampleInfo info = new PtSampleInfo();

		info.setQcReqId(UUID.fromString(requestMap.get("qcReqId")[0]));
		info.setStatus("P");
		ptSampleRepository.sendQCReqInfos(info);

	}

	public List<WhRequestDetailsInfo> getWhRequestDetailsById(UUID whRequestId) {
		return ptSampleRepository.getWhRequestDetailsById(whRequestId);
	}

	public WhRequestInfo getWhRequestInfoById(UUID id) {
		return ptSampleRepository.getWhRequestInfoById(id);
	}

}
