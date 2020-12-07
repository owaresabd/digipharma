package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.UserTypes;
import com.pms.repository.RoleRepositoy;

@Service
public class RoleService {

	@Autowired
	private RoleRepositoy roleRepository;

	public List<UserTypes> getAll() {
		return roleRepository.findAll();
	}

	public UserTypes getById(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return roleRepository.findById(id);
	}

	public List<UserTypes> findDoc(Map<String, String[]> map) {
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		List<UserTypes> searchResult = roleRepository.findDocs(companyId);
		return searchResult;
	}

	public void saveRoles(UserTypes role) {
		roleRepository.saveRoles(role);
	}

	public UUID delete(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return roleRepository.delete(id);
	}

	public boolean hasRoleCode(Map<String, String[]> requestMap) {
		UUID companyId = UUID.fromString(requestMap.get("companyId")[0]);
		String roleCode = requestMap.get("roleCode")[0];
		List<UserTypes> entityTransDtlsList = roleRepository.validateRoleCode(companyId, roleCode);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

	/*
	 * public List<UserTypes> findRoleWithoutMultiUser(Map<String, String[]>
	 * requestMap){ UUID userId = UUID.fromString(requestMap.get("userId")[0]); UUID
	 * companyId = UUID.fromString(requestMap.get("companyId")[0]); return
	 * roleRepository.findRoleWithoutMultiUser(userId, companyId); }
	 */
}
