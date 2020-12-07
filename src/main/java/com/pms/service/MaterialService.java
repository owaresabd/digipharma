package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.MaterialChemicalInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.MaterialMicrobiologicalInfo;
import com.pms.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	public List<MaterialInfo> getAll(String type,String status) {
		return materialRepository.findAll(type,status);
	}
	public List<MaterialInfo> findAllRawPackaging(String status) {
		return materialRepository.findAllRawPackaging(status);
	}
	public List<MaterialInfo> findAllWSRawMaterials(String status) {
		return materialRepository.findAllWSRawMaterials(status);
	}

	

	public void saveMaterialInfos(Map<String, String[]> requestMap, UUID idRandom) {
		MaterialInfo info = new MaterialInfo();
		String msId = requestMap.get("id")[0];
		String unitId = requestMap.get("unitId")[0];
		
		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}
		
		  info.setMaterialName(requestMap.get("materialName")[0]);
		  info.setMaterialCode(requestMap.get("materialCode")[0]);
		  info.setMaterialTypeId(UUID.fromString(requestMap.get("materialTypeId")[0]));
		  info.setIsChemical(requestMap.get("isChemical")[0]);
		  info.setIsMicrobiological(requestMap.get("isMicrobiological")[0]);
		  info.setChemicalSampleAmt(requestMap.get("chemicalSampleAmt")[0]);
		  info.setChemicalRetentionAmt(requestMap.get("chemicalRetentionAmt")[0]);
		  info.setChemicalTotal(requestMap.get("chemicalTotal")[0]);
		  info.setMicroSampleAmt(requestMap.get("microSampleAmt")[0]);
		  info.setMicroRetentionAmt(requestMap.get("microRetentionAmt")[0]);
		  info.setMicroTotal(requestMap.get("microTotal")[0]);
		  info.setTotalAmt(requestMap.get("totalAmt")[0]);
		  if(unitId.trim().equals("")) {
			  info.setUnitId(null);  
		  }else{
			  info.setUnitId(UUID.fromString(unitId));
			  
		  }
		  info.setStatus(requestMap.get("status")[0]); 
		  info.setStorageConId(UUID.fromString(requestMap.get("storageConId")[0]));
		  info.setStorageCondition(requestMap.get("storageCondition")[0]);
		  info.setMatSamProcedure(requestMap.get("matSamProcedure")[0]);
		  info.setMatMethodId(UUID.fromString(requestMap.get("matMethodId")[0]));
		  materialRepository.saveMaterialInfos(info, idRandom);
		 

	}
	public void saveChemicalDetailInfo(Map<String, String[]> requestMap, UUID idRandom) {
		MaterialChemicalInfo info = new MaterialChemicalInfo();
		
		  String mstId =requestMap.get("id")[0];
		  String[] id = (String[]) requestMap.get("chemiChildId[]");
		  String[] chemiParameterNo = (String[]) requestMap.get("chemiParameterNo[]");
		  String[] chemiParameterId = (String[]) requestMap.get("chemiParameterId[]"); 
		  String[] chemiSpecification = (String[]) requestMap.get("chemiSpecification[]"); 
		  String[] chemiReferenceId = (String[]) requestMap.get("chemiReferenceId[]"); 
		  String[] chemiMethodId = (String[]) requestMap.get("chemiMethodId[]");
		  String[] chemiTestUnitId = (String[]) requestMap.get("chemiTestUnitId[]");
		  String[] chemiLod = (String[]) requestMap.get("chemiLod[]");
		  String[] chemiSampleAmount = (String[]) requestMap.get("chemiSampleAmount[]");
		  String[] chemiUnitId = (String[]) requestMap.get("chemiUnitId[]");
		  
		  if(!mstId.isEmpty()) {
		  List<MaterialChemicalInfo> chemicalInfos = this.getMaterialChemicalInfos(UUID.fromString(mstId));
		  if(!chemicalInfos.isEmpty()) {
			  materialRepository.deleteChemicalInfos(UUID.fromString(mstId)); 
			  
		  }
		  }
		  if(requestMap.containsKey("chemiParameterNo[]")) {
		  for (int i = 0; i < chemiParameterNo.length; i++) {
		 
		  if (id[i].trim().equals("-1")) {
		  info.setChemiChildId(null); 
		  } else { 
			  info.setChemiChildId(UUID.fromString(id[i])); 
		  } 
		 
		  if (mstId.trim().equals("")) { 
			  info.setChemiMaterialId(idRandom); 
			}else {
				info.setChemiMaterialId(UUID.fromString(mstId)); 
		  }
		  info.setChemiParameterNo(chemiParameterNo[i]);
		  
		  if(!chemiParameterId[i].equals("") && chemiParameterId[i] !=null) {
			  info.setChemiParameterId(UUID.fromString(chemiParameterId[i])); 
		  }else {
			  info.setChemiParameterId(null); 
		  }
		  
		  info.setChemiSpecification(chemiSpecification[i]);
		  
		  if(!chemiReferenceId[i].equals("") && chemiReferenceId[i] !=null) {
			  info.setChemiReferenceId(UUID.fromString(chemiReferenceId[i])); 
		  }else {
			  info.setChemiReferenceId(null); 
		  }
		  if(!chemiMethodId[i].equals("") && chemiMethodId[i] !=null) {
			  info.setChemiMethodId(UUID.fromString(chemiMethodId[i])); 
		  }else {
			  info.setChemiMethodId(null); 
		  }
		  
		  if(!chemiTestUnitId[i].equals("") && chemiTestUnitId[i] !=null) {
			  info.setChemiTestUnitId(UUID.fromString(chemiTestUnitId[i])); 
		  }else {
			  info.setChemiTestUnitId(null); 
		  }
		  info.setChemiLod(chemiLod[i]);
		  info.setChemiSampleAmount(chemiSampleAmount[i]);
		  
		  if(!chemiUnitId[i].equals("") && chemiUnitId[i] !=null) {
			  info.setChemiUnitId(UUID.fromString(chemiUnitId[i])); 
		  }else {
			  info.setChemiUnitId(null); 
		  }
		  
		  materialRepository.saveMaterialChemicalInfo(info);
	}
		  }
	}
	public void saveMicrobiologicallDetailInfo(Map<String, String[]> requestMap, UUID idRandom) {
		MaterialMicrobiologicalInfo info = new MaterialMicrobiologicalInfo();
		
		 String mstId =requestMap.get("id")[0];
		  String[] id = (String[]) requestMap.get("microChildId[]");
		  String[] microParameterNo = (String[]) requestMap.get("microParameterNo[]");
		  String[] microParameterId = (String[]) requestMap.get("microParameterId[]"); 
		  String[] microSpecification = (String[]) requestMap.get("microSpecification[]"); 
		  String[] microReferenceId = (String[]) requestMap.get("microReferenceId[]"); 
		  String[] microMethodId = (String[]) requestMap.get("microMethodId[]");
		  String[] microTestUnitId = (String[]) requestMap.get("microTestUnitId[]");
		  String[] microLod = (String[]) requestMap.get("microLod[]");
		  String[] microSampleAmount = (String[]) requestMap.get("microSampleAmount[]");
		  String[] microUnitId = (String[]) requestMap.get("microUnitId[]");
		  //below line for delete 
		  if(!mstId.isEmpty()) {
		  List<MaterialMicrobiologicalInfo> microbiologicalInfos = this.getMaterialMicrobiologicalInfos(UUID.fromString(mstId));
		  if(!microbiologicalInfos.isEmpty()) {
			  materialRepository.deleteMicrobiologicalInfos(UUID.fromString(mstId)); 
			  
		  }}
		  
		  //end of delete 
		  if(requestMap.containsKey("microParameterNo[]")) {
		  for (int i = 0; i < microParameterId.length; i++) {
		 
		  if (id[i].trim().equals("-1")) {
		  info.setMicroChildId(null); 
		  } else { 
			  info.setMicroChildId(UUID.fromString(id[i])); 
		  } 
		 
		  if (mstId.trim().equals("")) { 
			  info.setMicroMaterialId(idRandom); 
			}else {
				info.setMicroMaterialId(UUID.fromString(mstId)); 
		  }
		  info.setMicroParameterNo(microParameterNo[i]);
		  if(!microParameterId[i].equals("") && microParameterId[i] !=null) {
			  info.setMicroParameterId(UUID.fromString(microParameterId[i])); 
		  }else {
			  info.setMicroParameterId(null); 
		  }
		  info.setMicroSpecification(microSpecification[i]);
		  if(!microReferenceId[i].equals("") && microReferenceId[i] !=null) {
			  info.setMicroReferenceId(UUID.fromString(microReferenceId[i])); 
		  }else {
			  info.setMicroReferenceId(null); 
		  }
		  if(!microMethodId[i].equals("") && microMethodId[i] !=null) {
			  info.setMicroMethodId(UUID.fromString(microMethodId[i])); 
		  }else {
			  info.setMicroMethodId(null); 
		  }
		  if(!microTestUnitId[i].equals("") && microTestUnitId[i] !=null) {
			  info.setMicroTestUnitId(UUID.fromString(microTestUnitId[i])); 
		  }else {
			  info.setMicroTestUnitId(null); 
		  }
		  info.setMicroLod(microLod[i]);
		  info.setMicroSampleAmount(microSampleAmount[i]);
		  if(!microUnitId[i].equals("") && microUnitId[i] !=null) {
			  info.setMicroUnitId(UUID.fromString(microUnitId[i])); 
		  }else {
			  info.setMicroUnitId(null); 
		  }
		  
		  materialRepository.saveMicrobiologicallDetailInfo(info);
	}
		  }
	}
	public MaterialInfo getMaterialInfoById(UUID id) {
		MaterialInfo info = materialRepository.getMaterialInfoById(id);
		return info;
	}
	public List<MaterialChemicalInfo> getMaterialChemicalInfos(UUID materialId) {
		return materialRepository.getMaterialChemicalInfos(materialId);
	}
	
	public List<MaterialMicrobiologicalInfo> getMaterialMicrobiologicalInfos(UUID materialId) {
		return materialRepository.getMaterialMicrobiologicalInfos(materialId);
	}
	
	
}
