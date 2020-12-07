package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.pms.model.RolePrivilege;
import com.pms.model.User;

public interface IUserService {
	public User findUserByEmail(String email);

	public User getCurrentUser();

	public void saveUser(User user);

	public String getCurrentUserName();

	public User findUserByUsername(String username);

	public User getUserInfo(String userName);

	public List<User> getAll();

	public List<User> findDoc(Map<String, String[]> map);

	public User findById(UUID id);

	public boolean hasUsername(Map<String, String[]> requestMap);

	public List<User> findMultipleUser();

	public List<User> findUserByTypeId(UUID typeId, UUID companyId);

	public void saveOrUpdateUsers(User user);

	public UUID delete(Map<String, String[]> requestMap);

	public List<RolePrivilege> getAllMenu(UUID userId);

	public User findUserForPassword(String username);

	public boolean hasEmployee(Map<String, String[]> requestMap);

	public int changePassword(Map<String, String[]> requestMap);
}