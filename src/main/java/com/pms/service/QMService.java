package com.pms.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.QMInfo;
import com.pms.repository.QMRepository;

@Service
public class QMService {
	@Autowired
	private QMRepository qmRepository;

	
	public List<QMInfo> getAll(String status) {
		return qmRepository.findAll(status);
	}
	
	
	
	
	public void saveQmInfo(UUID idRandom,QMInfo info) {
		qmRepository.saveQmInfo(idRandom,info );
	}
	  
	/*
	 * public boolean validateInstructionName(Map<String, String[]> requestMap) {
	 * String instructionName = requestMap.get("instructionName")[0];
	 * List<WorkInstructionInfo> entityTransList =
	 * workInstructionRepository.validateInstructionName(instructionName); if
	 * (entityTransList.size() == 0) { return true; }
	 * 
	 * return false; }
	 */
	
	
}
