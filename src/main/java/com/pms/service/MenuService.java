package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Module;
import com.pms.model.Privilege;
import com.pms.model.Suite;
import com.pms.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	/*********************************************
	 * START :: Suite Modification Business Logic
	 ***************************************/

	public List<Suite> getAllSuites() {
		return menuRepository.findAllSuites();
	}

	public Suite getBySuiteId(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return menuRepository.findBySuiteId(id);
	}

	public void saveOrUpdateSuites(Suite suite) {
		menuRepository.saveOrUpdateSuites(suite);
	}

	public UUID deleteSuite(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return menuRepository.deleteSuite(id);
	}

	public boolean hasSuiteCode(Map<String, String[]> requestMap) {
		String suiteCode = requestMap.get("suiteCode")[0];
		List<Suite> entityTransDtlsList = menuRepository.validateSuiteCode(suiteCode);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

	/**********************************************
	 * END :: Suite Modification Business Logic
	 ****************************************/

	/********************************************
	 * START :: Module Modification Business Logic
	 ***************************************/

	public List<Module> getAllModules() {
		return menuRepository.findAllModules();
	}

	public Module getByModuleId(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return menuRepository.findByModuleId(id);
	}

	public List<Module> getModuleBySuiteId(Map<String, String[]> requestMap) {
		UUID suiteId = UUID.fromString(requestMap.get("suiteId")[0]);
		return menuRepository.findModuleBySuiteId(suiteId);
	}

	public void saveOrUpdateModules(Module module) {
		menuRepository.saveOrUpdateModules(module);
	}

	public UUID deleteModule(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return menuRepository.deleteModule(id);
	}

	public boolean hasModuleCode(Map<String, String[]> requestMap) {
		UUID suiteId = UUID.fromString(requestMap.get("suiteId")[0]);
		String moduleCode = requestMap.get("moduleCode")[0];
		List<Module> entityTransDtlsList = menuRepository.validateModuleCode(suiteId, moduleCode);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

	/**********************************************
	 * END :: Module Modification Business Logic
	 ***************************************/

	/*******************************************
	 * START :: Privilege Modification Business Logic
	 *************************************/

	public List<Privilege> getAllPrivileges() {
		return menuRepository.findAllPrivileges();
	}

	public List<Privilege> findPrivilgeWithoutCompany(Map<String, String[]> requestMap) {
		UUID companyId = UUID.fromString(requestMap.get("companyId")[0]);
		return menuRepository.findPrivilgeWithoutCompany(companyId);
	}

	public Privilege getByPrivilegeId(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return menuRepository.findByPrivilegeId(id);
	}

	public List<Privilege> getBySuiteModuleId(Map<String, String[]> requestMap) {
		UUID suiteId = UUID.fromString(requestMap.get("suiteId")[0]);
		UUID moduleId = UUID.fromString(requestMap.get("moduleId")[0]);
		return menuRepository.findBySuiteModuleId(suiteId, moduleId);
	}

	public void saveOrUpdatePrivileges(Privilege privilege) {
		menuRepository.saveOrUpdatePrivileges(privilege);
	}

	public UUID deletePrivilege(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return menuRepository.deletePrivilege(id);
	}

	public boolean hasPrivilegeCode(Map<String, String[]> requestMap) {
		UUID suiteId = UUID.fromString(requestMap.get("suiteId")[0]);
		UUID moduleId = UUID.fromString(requestMap.get("moduleId")[0]);
		String privilegeCode = requestMap.get("privilegeCode")[0];
		List<Privilege> entityTransDtlsList = menuRepository.validatePrivilegeCode(suiteId, moduleId, privilegeCode);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

	/********************************************
	 * END :: Privilege Modification Business Logic
	 **************************************/
}
