package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.SamplingBoothInfo;
import com.pms.repository.SamplingBoothRepository;

@Service
public class SamplingBoothService {

	@Autowired
	private SamplingBoothRepository samplingBoothRepository;

	public List<SamplingBoothInfo> getAll(String status) {
		return samplingBoothRepository.findAll(status);
	}

	

	public void saveSamplingBoothInfos(SamplingBoothInfo samplingBoothInfo) {
		samplingBoothRepository.saveSamplingBoothInfos(samplingBoothInfo);
	}

	public boolean validateBoothName(Map<String, String[]> requestMap) {
		String boothName = requestMap.get("boothName")[0];
		List<SamplingBoothInfo> boothList = samplingBoothRepository.validateBoothName(boothName);
		if (boothList.size() == 0) {
			return true;
		}

		return false;
	}
}
