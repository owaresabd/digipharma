package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.DisposeMstInfo;
import com.pms.model.DisposeProductInfo;
import com.pms.model.DisposeSampleInfo;
import com.pms.repository.DisposeRepository;

@Service
public class DisposeService {

	@Autowired
	private DisposeRepository disposeRepository;
	
	public List<DisposeMstInfo> getAll() {
		return disposeRepository.findAll();
	}
	public DisposeMstInfo getDisposeInfoByID(UUID id) {
		DisposeMstInfo info = disposeRepository.getDisposeInfoByID(id);
		return info;
	}
	public List<CommonInfo> getSampleInfos() {
		return disposeRepository.findSampleInfos();
	}

	public void saveDisposeMstInfo(Map<String, String[]> requestMap, UUID idRandom) {
		DisposeMstInfo info = new DisposeMstInfo();
		String msId = requestMap.get("id")[0];
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}

		info.setEmployeeId(UUID.fromString(requestMap.get("employeeId")[0]));
		info.setIsProduct(requestMap.get("isProduct")[0]);
		info.setIsSample(requestMap.get("isSample")[0]);
		info.setRemarks(requestMap.get("remarks")[0]);
		disposeRepository.saveDisposeMstInfo(info, idRandom);

	}

	public void saveSampleDisposeInfo(Map<String, String[]> requestMap, UUID idRandom) {
		DisposeSampleInfo info = new DisposeSampleInfo();
		String mstId = requestMap.get("id")[0];
		String[] sampleId = (String[]) requestMap.get("sampleId[]");
		String[] materialId = (String[]) requestMap.get("materialId[]");
		String[] quantity = (String[]) requestMap.get("quantity[]");
		String[] unitId = (String[]) requestMap.get("sampleUnitId[]");

		if (!mstId.isEmpty()) {
			List<DisposeSampleInfo> sampleInfos = this.getDisposeSampleInfos(idRandom);
			if (!sampleInfos.isEmpty()) {
				disposeRepository.deleteDisposeSampleInfos(idRandom);

			}
			
		}
		
		if (requestMap.containsKey("sampleId[]")) {
			for (int i = 0; i < sampleId.length; i++) {

				info.setDisposeId(idRandom);
				info.setSampleId(UUID.fromString(sampleId[i]));
				info.setMaterialId(UUID.fromString(materialId[i]));

				info.setQuantity(quantity[i]);

				info.setSampleUnitId(UUID.fromString(unitId[i]));

				disposeRepository.saveDisposeSampleInfo(info);
			}
		}
	}

	public void saveDisposeProductInfo(Map<String, String[]> requestMap, UUID idRandom) {
		DisposeProductInfo info = new DisposeProductInfo();

		String mstId = requestMap.get("id")[0];
		String[] productId = (String[]) requestMap.get("productId[]");
		String[] productCode = (String[]) requestMap.get("productCode[]");
		String[] quantity = (String[]) requestMap.get("proQuantity[]");
		String[] unitId = (String[]) requestMap.get("productUnitId[]");

		if (!mstId.isEmpty()) {
			List<DisposeProductInfo> sampleInfos = this.getDisposeProductInfos(idRandom);
			if (!sampleInfos.isEmpty()) {
				disposeRepository.deleteDisposeProductInfos(idRandom);
			}
		}
		if (requestMap.containsKey("productId[]")) {
			for (int i = 0; i < productId.length; i++) {

				info.setDisposeId(idRandom);
				

				info.setProductId(UUID.fromString(productId[i]));
				info.setProductCode(productCode[i]);

				info.setQuantity(quantity[i]);

				info.setProductUnitId(UUID.fromString(unitId[i]));

				disposeRepository.saveDisposeProductInfo(info);
			}
		}
	}

	public List<DisposeSampleInfo> getDisposeSampleInfos(UUID disposeId) {
		return disposeRepository.getDisposeSampleInfos(disposeId);
	}

	public List<DisposeProductInfo> getDisposeProductInfos(UUID disposeId) {
		return disposeRepository.getDisposeProductInfos(disposeId);
	}
	public CommonInfo getSampleInfoById(UUID id) {
		CommonInfo info = disposeRepository.findSampleInfoById(id);
		return info;
	}
}
