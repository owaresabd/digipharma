package com.pms.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.MediumReagentInfo;
import com.pms.model.SurgicalProductInfo;
import com.pms.repository.SurgicalProductRepository;

@Service
public class SurgicalProductService {

	@Autowired
	private SurgicalProductRepository surgicalProductRepository;

	public List<SurgicalProductInfo> getAll(String status, String typeCode) {
		return surgicalProductRepository.findAll(status, typeCode);
	}

	public void saveSurgicalProductInfos(SurgicalProductInfo info, String Type) {

		if (Type.equals("O")) {

			surgicalProductRepository.saveSurgicalProductInfos(info);
		} else {
			surgicalProductRepository.saveReagentInfos(info);

		}

	}

	public List<MediumReagentInfo> getMediumReagent(String status) {
		return surgicalProductRepository.findMediumReagent(status);
	}

	public void saveMediumReagentInfos(MediumReagentInfo info) {

		surgicalProductRepository.saveMediumReagentInfos(info);

	}
	public MediumReagentInfo getBatchInfo(UUID id) {
		MediumReagentInfo info = surgicalProductRepository.getBatchInfo(id);
		return info;
	}

}
