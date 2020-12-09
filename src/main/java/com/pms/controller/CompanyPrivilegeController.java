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

import com.pms.service.CompanyPrivilegeService;
import com.pms.service.CompanyService;
import com.pms.service.MenuService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pms.model.CompanyPrivilege;
import com.pms.model.Privilege;

@Controller
@RequestMapping("/companyprivilege")
public class CompanyPrivilegeController {

	@Autowired
	private CompanyPrivilegeService companyPrivilegeService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping("/maintain")
	public ModelAndView privilege() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.setViewName("application_settings/privilege_authorization/companyprivileges");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/privilegeList/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPrivilegeList(@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();

		Map<String, String[]> search = new HashMap<>();
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<Privilege> availPrivList = menuService.findPrivilgeWithoutCompany(search);
		map.put("entityList", g.toJson(availPrivList));
		return g.toJson(availPrivList);
	}

	@ResponseBody
	@RequestMapping(value = "/privilge/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRolePrivilegeList(@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		Map<String, String[]> search = new HashMap<>();
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<CompanyPrivilege> compPrivilegeList = companyPrivilegeService.findDoc(search);
		map.put("compPrivilegeList", g.toJson(compPrivilegeList));
		return g.toJson(compPrivilegeList);
	}

	@RequestMapping(value = "/save-company-privilge/{privId}/{companyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addRolePrivilege(@PathVariable(value = "privId") UUID privId,
			@PathVariable(value = "companyId") UUID companyId) {

		Map<String, String[]> search = new HashMap<>();
		search.put("privId", new String[] { (privId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });

		companyPrivilegeService.addCompanyPrivilges(search);
	}

	@RequestMapping(value = "/remove-company-privilge/{privId}/{companyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeRolePrivilege(@PathVariable(value = "privId") UUID privId,
			@PathVariable(value = "companyId") UUID companyId) {

		Map<String, String[]> search = new HashMap<>();
		search.put("privId", new String[] { (privId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });

		companyPrivilegeService.removeCompanyPrivilges(search);
	}
}
