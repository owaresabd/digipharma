package com.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pms.model.CompanyPrivilege;
import com.pms.model.RolePrivilege;
import com.pms.service.CompanyPrivilegeService;
import com.pms.service.CompanyService;
import com.pms.service.RolePrivilegeService;
import com.pms.service.RoleService;

@Controller
@RequestMapping("/roleprivilege")
public class RolePrivilegeController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	@Autowired
	private CompanyPrivilegeService companyPrivilegeService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping("/maintain")
	public ModelAndView privilege() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.setViewName("application_settings/privilege_authorization/roleprivileges");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/privilegeList/{typeId}/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPrivilegeList(@PathVariable(value = "typeId") UUID typeId,
			@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();

		Map<String, String[]> search = new HashMap<>();
		search.put("typeId", new String[] { (typeId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<CompanyPrivilege> availPrivList = companyPrivilegeService.findPrivilgeWithoutRole(search);
		map.put("entityList", g.toJson(availPrivList));
		return g.toJson(availPrivList);
	}

	@ResponseBody
	@RequestMapping(value = "/privilge/{typeId}/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRolePrivilegeList(@PathVariable(value = "typeId") UUID typeId,
			@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		Map<String, String[]> search = new HashMap<>();
		search.put("typeId", new String[] { (typeId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<RolePrivilege> rolePrivilegeList = rolePrivilegeService.findDoc(search);
		map.put("rolePrivList", g.toJson(rolePrivilegeList));
		return g.toJson(rolePrivilegeList);
	}

	@RequestMapping(value = "/save-role-privilge/{privId}/{typeId}/{companyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addRolePrivilege(@PathVariable(value = "privId") UUID privId,
			@PathVariable(value = "typeId") UUID typeId, @PathVariable(value = "companyId") UUID companyId,
			HttpServletRequest request) {

		Map<String, String[]> search = new HashMap<>();
		search.put("typeId", new String[] { (typeId).toString().trim() });
		search.put("privId", new String[] { (privId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });

		rolePrivilegeService.addRolePrivilges(search);
	}

	@RequestMapping(value = "/remove-role-privilge/{privId}/{typeId}/{companyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeRolePrivilege(@PathVariable(value = "privId") UUID privId,
			@PathVariable(value = "typeId") UUID typeId, @PathVariable(value = "companyId") UUID companyId,
			HttpServletRequest request) {

		Map<String, String[]> search = new HashMap<>();
		search.put("typeId", new String[] { (typeId).toString().trim() });
		search.put("privId", new String[] { (privId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });

		rolePrivilegeService.removeRolePrivilges(search);
	}
}
