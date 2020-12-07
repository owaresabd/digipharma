package com.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import com.pms.model.User;
import com.pms.model.UserTypes;
import com.pms.service.CompanyService;
import com.pms.service.IUserService;
import com.pms.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/maintain")
	public ModelAndView category() {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.addObject("companyId", user.getCompanyId());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.setViewName("application_settings/user_settings/role");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/roleDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAccountDetails(@PathVariable("id") String id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		UserTypes jsonList = roleService.getById(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/roleByCompany/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPrivilegeList(@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();

		Map<String, String[]> search = new HashMap<>();
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<UserTypes> availPrivList = roleService.findDoc(search);
		map.put("entityList", g.toJson(availPrivList));
		return g.toJson(availPrivList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-roles", method = RequestMethod.POST)
	public ModelAndView saveRoles(@Valid @ModelAttribute("userTypes") UserTypes userTypes) {
		ModelAndView modelAndView = new ModelAndView();
		roleService.saveRoles(userTypes);
		User user = userService.getCurrentUser();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.addObject("companyId", user.getCompanyId());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.setViewName("application_settings/user_settings/role");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-roles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroy(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		roleService.delete(map);
		User user = userService.getCurrentUser();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.addObject("companyId", user.getCompanyId());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.setViewName("application_settings/user_settings/role");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-code/{companyId}/{roleCode}", method = RequestMethod.GET)
	public WSResponse validateCode(@PathVariable("companyId") UUID companyId,
			@PathVariable("roleCode") String roleCode) {
		Map<String, String[]> map = new HashMap<>();
		map.put("companyId", new String[] { (companyId).toString().trim() });
		map.put("roleCode", new String[] { (roleCode).toString().trim() });
		boolean bool = roleService.hasRoleCode(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
