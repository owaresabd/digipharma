package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.RolePrivilege;
import com.pms.repository.RolePrivilegeRepository;

@Service
public class RolePrivilegeService {

	@Autowired
	private RolePrivilegeRepository rolePrivilegeRepository;

	public List<RolePrivilege> findDoc(Map<String, String[]> map) {
		UUID typeId = UUID.fromString(map.get("typeId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		List<RolePrivilege> searchResult = rolePrivilegeRepository.findDocs(typeId, companyId);
		return searchResult;
	}

	public void addRolePrivilges(Map<String, String[]> map) {
		UUID typeId = UUID.fromString(map.get("typeId")[0]);
		UUID privId = UUID.fromString(map.get("privId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		rolePrivilegeRepository.saveRolePrivileges(privId, typeId, companyId);
	}

	public void removeRolePrivilges(Map<String, String[]> map) {
		UUID typeId = UUID.fromString(map.get("typeId")[0]);
		UUID privId = UUID.fromString(map.get("privId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		rolePrivilegeRepository.deleteRolePrivilge(privId, typeId, companyId);
	}
}
