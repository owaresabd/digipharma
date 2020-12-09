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

import com.pms.service.ApplicationUserService;
import com.pms.service.CompanyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pms.model.ApplicationUser;
import com.pms.model.User;

@Controller
@RequestMapping("/appuser")
public class ApplicationUserController {

	@Autowired
	private ApplicationUserService applicationUserService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping("/maintain")
	public ModelAndView privilege() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.setViewName("application_settings/user_settings/appuser");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/unassigneduser/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPrivilegeList(@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();

		Map<String, String[]> search = new HashMap<>();
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<User> userList = applicationUserService.findUserWithoutAppUser(search);
		map.put("userList", g.toJson(userList));
		return g.toJson(userList);
	}

	@ResponseBody
	@RequestMapping(value = "/assigneduser/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRolePrivilegeList(@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		Map<String, String[]> search = new HashMap<>();
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<ApplicationUser> appUserList = applicationUserService.findDoc(search);
		map.put("appUserList", g.toJson(appUserList));
		return g.toJson(appUserList);
	}

	@RequestMapping(value = "/save-company-user/{userId}/{companyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addRolePrivilege(@PathVariable(value = "userId") UUID userId,
			@PathVariable(value = "companyId") UUID companyId) {

		Map<String, String[]> search = new HashMap<>();
		search.put("userId", new String[] { (userId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });

		applicationUserService.addApplicationUsers(search);
	}

	@RequestMapping(value = "/remove-company-user/{userId}/{companyId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void removeRolePrivilege(@PathVariable(value = "userId") UUID userId,
			@PathVariable(value = "companyId") UUID companyId) {

		Map<String, String[]> search = new HashMap<>();
		search.put("userId", new String[] { (userId).toString().trim() });
		search.put("companyId", new String[] { (companyId).toString().trim() });

		applicationUserService.removeApplicationUsers(search);
	}
}
