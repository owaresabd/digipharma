package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.QPDocumentInfo;
import com.pms.model.QPInfo;
import com.pms.model.QmQpDocumentInfo;
import com.pms.repository.QPRepository;

@Service
public class QPService {
	@Autowired
	private QPRepository qpRepository;

	
	public List<QPInfo> getAll(String status) {
		return qpRepository.findAll(status);
	}
	public void saveQpInfo(UUID idRandom,QPInfo info) {
		qpRepository.saveQpInfo(idRandom,info );
	}
	  
	public void saveDocumentInfo(UUID idRandom,QPDocumentInfo info) {
		qpRepository.saveDocumentInfo(idRandom,info );
	}
	public List<QPDocumentInfo> getAllDocuments(String status) {
		return qpRepository.findAllDocuments(status);
	}
	
	public List<QPDocumentInfo> findUnassignedDocument(Map<String, String[]> requestMap) {
		UUID userid = UUID.fromString(requestMap.get("userId")[0]);
		return qpRepository.findUnassignedDocument(userid);
	}
	public List<QPDocumentInfo> findAssignedDocument(Map<String, String[]> requestMap) {
		UUID userId = UUID.fromString(requestMap.get("userId")[0]);
		return qpRepository.findAssignedDocument(userId);
	}
	public void addDocumentMapping(Map<String, String[]> map) {
		UUID documentId = UUID.fromString(map.get("documentId")[0]);
		UUID userId = UUID.fromString(map.get("userId")[0]);
		qpRepository.saveDocumentMapping(documentId, userId);
	}

	public void removeDocumentMapping(Map<String, String[]> map) {
		UUID documentId = UUID.fromString(map.get("documentId")[0]);
		UUID userId = UUID.fromString(map.get("userId")[0]);
		qpRepository.deleteDocumentMapping(documentId, userId);
	}
	public List<QmQpDocumentInfo> getQMByUserId() {
		return qpRepository.getQMByUserId();
	}
	public List<QmQpDocumentInfo> getQPByUserId() {
		return qpRepository.getQPByUserId();
	}
	public List<QmQpDocumentInfo> getQPDocumentByUserId() {
		return qpRepository.getQPDocumentByUserId();
	}
}
