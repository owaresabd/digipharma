package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ApplicationUser;
import com.pms.model.User;
import com.pms.repository.ApplicationUserRepository;
import com.pms.repository.UserRepository;

@Service
public class ApplicationUserService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private UserRepository mainRepository;

	public List<ApplicationUser> findDoc(Map<String, String[]> map) {
		UUID id = UUID.fromString(map.get("companyId")[0]);
		List<ApplicationUser> searchResult = applicationUserRepository.findDocs(id);
		return searchResult;
	}

	public List<User> findUserWithoutAppUser(Map<String, String[]> requestMap) {
		UUID companyId = UUID.fromString(requestMap.get("companyId")[0]);
		return mainRepository.findUserWithoutAppUser(companyId);
	}

	public void addApplicationUsers(Map<String, String[]> map) {
		UUID userId = UUID.fromString(map.get("userId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		applicationUserRepository.saveApplicationUsers(userId, companyId);
	}

	public void removeApplicationUsers(Map<String, String[]> map) {
		UUID userId = UUID.fromString(map.get("userId")[0]);
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		applicationUserRepository.deleteApplicationUser(userId, companyId);
	}
}
