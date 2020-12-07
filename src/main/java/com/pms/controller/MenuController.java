package com.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pms.configure.bean.WSResponse;
import com.pms.model.Module;
import com.pms.model.Privilege;
import com.pms.model.Suite;
import com.pms.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/*********************************************
	 * START :: Suite Modification Business Logic
	 ***************************************/

	@RequestMapping("/suite")
	public ModelAndView suite() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.setViewName("application_settings/menu_settings/suites");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/suiteDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getSuiteDetails(@PathVariable("id") String id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		Suite jsonList = menuService.getBySuiteId(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-suites", method = RequestMethod.POST)
	public ModelAndView saveSuites(@Valid @ModelAttribute("suite") Suite suite) {
		ModelAndView modelAndView = new ModelAndView();
		menuService.saveOrUpdateSuites(suite);
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.setViewName("application_settings/menu_settings/suites");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-suites/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroySuite(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		menuService.deleteSuite(map);
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.setViewName("application_settings/menu_settings/suites");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-suiteCode/{suiteCode}", method = RequestMethod.GET)
	public WSResponse validateSuiteCode(@PathVariable("suiteCode") String suiteCode) {
		Map<String, String[]> map = new HashMap<>();
		map.put("suiteCode", new String[] { (suiteCode).toString().trim() });
		boolean bool = menuService.hasSuiteCode(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}

	/**********************************************
	 * END :: Suite Modification Business Logic
	 ****************************************/

	/********************************************
	 * START :: Module Modification Business Logic
	 ***************************************/

	@RequestMapping("/module")
	public ModelAndView module() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("modules", menuService.getAllModules());
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.setViewName("application_settings/menu_settings/modules");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/moduleDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getModuleDetails(@PathVariable("id") String id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		Module jsonList = menuService.getByModuleId(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/moduleList/{suiteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getModuleList(@PathVariable("suiteId") String suiteId) {
		Map<String, String[]> map = new HashMap<>();
		map.put("suiteId", new String[] { (suiteId).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		List<Module> jsonList = menuService.getModuleBySuiteId(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-modules", method = RequestMethod.POST)
	public ModelAndView saveModules(@Valid @ModelAttribute("module") Module module) {
		ModelAndView modelAndView = new ModelAndView();
		menuService.saveOrUpdateModules(module);
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.addObject("modules", menuService.getAllModules());
		modelAndView.setViewName("application_settings/menu_settings/modules");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-modules/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroyModule(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		menuService.deleteModule(map);
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.addObject("modules", menuService.getAllModules());
		modelAndView.setViewName("application_settings/menu_settings/modules");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-moduleCode/{suiteId}/{moduleCode}", method = RequestMethod.GET)
	public WSResponse validateModuleCode(@PathVariable("suiteId") String suiteId,
			@PathVariable("moduleCode") String moduleCode) {
		Map<String, String[]> map = new HashMap<>();
		map.put("suiteId", new String[] { (suiteId).toString().trim() });
		map.put("moduleCode", new String[] { (moduleCode).toString().trim() });
		boolean bool = menuService.hasModuleCode(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}

	/**********************************************
	 * END :: Module Modification Business Logic
	 ***************************************/

	/*******************************************
	 * START :: Privilege Modification Business Logic
	 *************************************/

	@RequestMapping("/privilege")
	public ModelAndView privilege() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.addObject("privileges", menuService.getAllPrivileges());
		modelAndView.setViewName("application_settings/menu_settings/privileges");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/privilegeDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPrivilegeDetails(@PathVariable("id") String id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		Privilege jsonList = menuService.getByPrivilegeId(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/privilegeList/{suiteId}/{moduleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getModuleList(@PathVariable("suiteId") String suiteId, @PathVariable("moduleId") String moduleId) {
		Map<String, String[]> map = new HashMap<>();
		map.put("suiteId", new String[] { (suiteId).toString().trim() });
		map.put("moduleId", new String[] { (moduleId).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		List<Privilege> jsonList = menuService.getBySuiteModuleId(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-privileges", method = RequestMethod.POST)
	public ModelAndView savePrivileges(@Valid @ModelAttribute("privilege") Privilege privilege) {
		ModelAndView modelAndView = new ModelAndView();
		menuService.saveOrUpdatePrivileges(privilege);
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.addObject("privileges", menuService.getAllPrivileges());
		modelAndView.setViewName("application_settings/menu_settings/privileges");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-privileges/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroyPrivilege(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		menuService.deletePrivilege(map);
		modelAndView.addObject("suites", menuService.getAllSuites());
		modelAndView.addObject("privileges", menuService.getAllPrivileges());
		modelAndView.setViewName("application_settings/menu_settings/privileges");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-privilegeCode/{suiteId}/{moduleId}/{privilegeCode}", method = RequestMethod.GET)
	public WSResponse validatePrivilegeCode(@PathVariable("suiteId") String suiteId,
			@PathVariable("moduleId") String moduleId, @PathVariable("privilegeCode") String privilegeCode) {
		Map<String, String[]> map = new HashMap<>();
		map.put("suiteId", new String[] { (suiteId).toString().trim() });
		map.put("moduleId", new String[] { (moduleId).toString().trim() });
		map.put("privilegeCode", new String[] { (privilegeCode).toString().trim() });
		boolean bool = menuService.hasPrivilegeCode(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}

	/********************************************
	 * END :: Privilege Modification Business Logic
	 **************************************/
}
