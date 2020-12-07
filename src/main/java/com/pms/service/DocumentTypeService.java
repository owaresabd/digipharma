package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DocumentTypeInfo;
import com.pms.repository.DocumentTypeRepository;

@Service
public class DocumentTypeService {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	public List<DocumentTypeInfo> getAll(String status) {
		return documentTypeRepository.findAll(status);
	}

	
	public void saveDocumentTypeInfos(DocumentTypeInfo info) {
		documentTypeRepository.saveDocumentTypeInfos(info);
	}

	public boolean validateTypeName(Map<String, String[]> requestMap) {
		String typeName = requestMap.get("typeName")[0];
		List<DocumentTypeInfo> entityTransList = documentTypeRepository.validateDocumentTypeName(typeName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
