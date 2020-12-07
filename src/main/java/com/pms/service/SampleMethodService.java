package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.SamplingMethodInfo;
import com.pms.repository.SampleMethodRepository;

@Service
public class SampleMethodService {

	@Autowired
	private SampleMethodRepository sampleMethodRepository;

	public List<SamplingMethodInfo> getAll(String status) {
		return sampleMethodRepository.findAll(status);
	}

	public List<SamplingMethodInfo> getAllMethod() {
		return sampleMethodRepository.findAllMethod();
	}

	public void saveSamplingMethodInfos(SamplingMethodInfo methodInfo) {
		sampleMethodRepository.saveSamplingMethodInfos(methodInfo);
	}

	public boolean validateMethodName(Map<String, String[]> requestMap) {
		String methodName = requestMap.get("methodName")[0];
		List<SamplingMethodInfo> entityTransList = sampleMethodRepository.validateMethodName(methodName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
