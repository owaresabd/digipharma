package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.SupplierInfo;
import com.pms.repository.SupplierRepository;


@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public List<SupplierInfo> getAll(String status) {
		return supplierRepository.findAll(status);
	}

	public SupplierInfo getById(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return supplierRepository.findById(id);
	}

	public void saveSupplierInfos(SupplierInfo supplierInfo) {
		supplierRepository.saveSupplierInfos(supplierInfo);
	}

	public UUID delete(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return supplierRepository.delete(id);
	}

	public boolean hasSupplierCode(Map<String, String[]> requestMap) {
		String supplierCode = requestMap.get("supplierCode")[0];
		List<SupplierInfo> entityTransDtlsList = supplierRepository.validateSupplierCode(supplierCode);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
