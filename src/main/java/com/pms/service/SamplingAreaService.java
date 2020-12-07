package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.SamplingAreaInfo;
import com.pms.repository.SamplingAreaRepository;

@Service
public class SamplingAreaService {

	@Autowired
	private SamplingAreaRepository samplingAreaRepository;

	public List<SamplingAreaInfo> getAll(String status) {
		return samplingAreaRepository.findAll(status);
	}

	

	public void saveSamplingAreaInfos(SamplingAreaInfo samplingAreaInfo) {
		samplingAreaRepository.saveSamplingAreaInfos(samplingAreaInfo);
	}

	public boolean validateAreaName(Map<String, String[]> requestMap) {
		String areaName = requestMap.get("areaName")[0];
		List<SamplingAreaInfo> areaList = samplingAreaRepository.validateAreaName(areaName);
		if (areaList.size() == 0) {
			return true;
		}

		return false;
	}
}
