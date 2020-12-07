package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.TestMethodInfo;
import com.pms.model.TestMethodRefInfo;
import com.pms.repository.TestMethodRepository;

@Service
public class TestMethodService {

	@Autowired
	private TestMethodRepository testMethodRepository;

	public List<TestMethodInfo> getAll(String status) {
		return testMethodRepository.findAll(status);
	}
	public List<TestMethodRefInfo> getRefAll(String status) {
		return testMethodRepository.findRefAll(status);
	}
	public List<TestMethodInfo> getUdNo(UUID udMethodId) {
		return testMethodRepository.findUdNo(udMethodId);
	}
	public void saveTestMethodInfos(UUID idRandom,TestMethodInfo methodInfo) {

		testMethodRepository.saveTestMethodInfos(methodInfo, idRandom);
	}
	public void saveTestMethodRefInfos(UUID idRandom,TestMethodRefInfo methodRefInfo) {

		testMethodRepository.saveTestMethodRefInfos(methodRefInfo, idRandom);
	}
	public boolean validateMethodName(Map<String, String[]> requestMap) {
		String methodName = requestMap.get("methodName")[0];
		List<TestMethodInfo> entityTransList = testMethodRepository.validateMethodName(methodName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
	public boolean validateMethodNo(Map<String, String[]> requestMap) {
		String methodNo = requestMap.get("methodNo")[0];
		List<TestMethodInfo> dataList = testMethodRepository.validateMethodNo(methodNo);
		if (dataList.size() == 0) {
			return true;
		}

		return false;
	}
}
