package com.pms.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.Role;
import com.pms.model.RolePrivilege;
import com.pms.model.User;
import com.pms.repository.IRoleRepository;
import com.pms.repository.IUserRepository;
import com.pms.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserRepository mainRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<User> getAll() {
		return mainRepository.findAll();
	}

	@Override
	public void saveOrUpdateUsers(User user) {
		mainRepository.saveOrUpdateUsers(user);
	}

	@Override
	public UUID delete(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return mainRepository.delete(id);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUserName(this.getCurrentUserName());
	}

	@Override
	public User findUserForPassword(String username) {
		return mainRepository.findByUsername(username);
	}

	@Override
	public User getCurrentUser() {
		return userRepository.findByUserName(this.getCurrentUserName());
	}

	@Override
	public List<User> findDoc(Map<String, String[]> map) {
		UUID companyId = UUID.fromString(map.get("companyId")[0]);
		List<User> searchResult = mainRepository.findDocs(companyId);
		return searchResult;
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		user.setCreatedBy("Admin");
		user.setCreatedDate(Utility.getCurrentTimeStamp());
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setRoleId(userRole.getId());
		userRepository.save(user);
	}

	@Override
	public String getCurrentUserName() {
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

		return currentUserName;
	}

	public User getUserInfo(String username) {
		return mainRepository.findByUsername(username);
	}

	@Override
	public User findById(UUID id) {
		return mainRepository.findById(id);
	}

	@Override
	public boolean hasUsername(Map<String, String[]> requestMap) {
		String username = requestMap.get("username")[0];
		List<User> entityTransDtlsList = mainRepository.validateUsername(username);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

	public List<User> getSysUser() {
		return mainRepository.getSysUser();
	}

	@Override
	public List<User> findMultipleUser() {
		return mainRepository.getMultipleUser();
	}

	@Override
	public List<User> findUserByTypeId(UUID typeId, UUID companyId) {
		return mainRepository.getUserByTypeId(typeId, companyId);
	}

	@Override
	public List<RolePrivilege> getAllMenu(UUID typeId) {
		return mainRepository.getAllMenu(typeId);
	}

	@Override
	public boolean hasEmployee(Map<String, String[]> requestMap) {
		String username = requestMap.get("username")[0];
		String email = requestMap.get("email")[0];
		List<User> entityTransDtlsList = mainRepository.validateEmployee(username, email);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

	@Override
	public int changePassword(Map<String, String[]> requestMap) {
		String username = requestMap.get("username")[0];
		String password = requestMap.get("password")[0];
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		User user = new User();

		user.setUserName(username);
		user.setPassword(hashedPassword);

		return mainRepository.changePassword(user);
	}

}