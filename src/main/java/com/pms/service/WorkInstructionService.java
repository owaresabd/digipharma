package com.pms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.WorkInstructionInfo;
import com.pms.model.WorkInstructionRefInfo;
import com.pms.repository.WorkInstructionRepository;

@Service
public class WorkInstructionService {
	@Autowired
	private WorkInstructionRepository workInstructionRepository;

	public List<WorkInstructionInfo> getAll(String status) {
		return workInstructionRepository.findAll(status);
	}
	public List<WorkInstructionRefInfo> getRefAll(String status) {
		return workInstructionRepository.findRefAll(status);
	}
	public List<WorkInstructionInfo> getUdNo(UUID insId) {
		return workInstructionRepository.findUdNo(insId);
	}
	public List<WorkInstructionInfo> getAllWorkInstruction() {
		return workInstructionRepository.findAllWorkInstruction();
	}

	public void saveWorkInstructionInfos(UUID idRandom,WorkInstructionInfo instructionInfo) {
		workInstructionRepository.saveWorkInstructionInfos(instructionInfo, idRandom);
	}
	public void saveWorkInstructionRefInfos(UUID idRandom,WorkInstructionRefInfo instructionRefInfo) {
		workInstructionRepository.saveWorkInstructionRefInfos(instructionRefInfo, idRandom);
	}
	public void saveWorkInstructionFile(Map<String, String[]> map, String fileName, byte[] bytes) throws IOException {
		WorkInstructionInfo info = new WorkInstructionInfo();
		
		
		info.setId(UUID.fromString(map.get("id")[0]));
		info.setInstructionName(map.get("instructionName")[0]);
		info.setRemarks(map.get("remarks")[0]);
		info.setStatus(map.get("status")[0]);
		info.setFileContent(bytes);
		info.setFileName(fileName);
		workInstructionRepository.saveWorkInstructionFile(info);
	}
	
	public boolean validateInstNo(Map<String, String[]> requestMap) {
		String instNo = requestMap.get("instNo")[0];
		List<WorkInstructionInfo> dataList = workInstructionRepository.validateInstNo(instNo);
		if (dataList.size() == 0) {
			return true;
		}
		return false;
	}

	public boolean validateInstructionName(Map<String, String[]> requestMap) {
		String instructionName = requestMap.get("instructionName")[0];
		List<WorkInstructionInfo> entityTransList = workInstructionRepository.validateInstructionName(instructionName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
	
	
	
}
