package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CompanyPrivilege;
import com.pms.repository.CompanyPrivilegeRepository;

@Service
public class CompanyPrivilegeService {

	@Autowired
	private CompanyPrivilegeRepository companyPrivilegeRepository;

	public List<CompanyPrivilege> findDoc(Map<String, String[]> map) {
		UUID id = UUID.fromString(map.get("companyId")[0]);
		List<CompanyPrivilege> searchResult = companyPrivilegeRepository.findDocs(id);
		return searchResult;
	}

	public List<CompanyPrivilege> findPrivilgeWithoutRole(Map<String, String[]> requestMap) {
		UUID typeId = UUID.fromString(requestMap.get("typeId")[0]);
		UUID companyId = UUID.fromString(requestMap.get("companyId")[0]);
		return companyPrivilegeRepository.findPrivilgeWithoutRole(typeId, companyId);
	}

	public void addCompanyPrivilges(Map<String, String[]> map) {
		UUID privId = UUID.fromString(map.get("privId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		companyPrivilegeRepository.saveCompanyPrivileges(privId, companyId);
	}

	public void removeCompanyPrivilges(Map<String, String[]> map) {
		UUID privId = UUID.fromString(map.get("privId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		companyPrivilegeRepository.deleteCompanyPrivilge(privId, companyId);
	}
}
