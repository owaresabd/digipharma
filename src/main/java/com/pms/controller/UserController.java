package com.pms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import com.pms.model.EmployeeInfo;
import com.pms.model.User;
import com.pms.service.CompanyService;
import com.pms.service.EmployeeService;
import com.pms.service.IUserService;
import com.pms.service.RoleService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private IUserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/maintain")
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.addObject("users", userService.getAll());
		modelAndView.addObject("companyId", user.getCompanyId());
		modelAndView.addObject("employeeInfos", employeeService.getAll("Y"));
		modelAndView.setViewName("application_settings/user_settings/user");
		return modelAndView;
	}
	
	@RequestMapping("/profile")
	public ModelAndView profile() {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser();
		
		if(user.getIsEmployee() != null) {
			EmployeeInfo info=null;
			//EmployeeInfo info=employeeService.getEmployeeInfoById(user.getEmployeeId());
			modelAndView.addObject("info", info);
			modelAndView.setViewName("application_settings/user_settings/employee_profile");	
		}else {
			modelAndView.addObject("info", user);
			modelAndView.setViewName("application_settings/user_settings/user_profile");	
		}
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getEmployeeInfoById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getEmployeeInfoById(@PathVariable(value = "id") UUID id) {
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		EmployeeInfo info=null;
		//EmployeeInfo info=employeeService.getEmployeeInfoById(id);
		return g.toJson(info);
	}
	@RequestMapping("/changepassword")
	public ModelAndView privilege() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("application_settings/user_settings/changepassword");
		return modelAndView;
	}
	
	@RequestMapping("/changepasswordInd")
	public ModelAndView changepasswordInd() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("application_settings/user_settings/changepasswordInd");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/userByCompany/{companyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPrivilegeList(@PathVariable(value = "companyId") UUID companyId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();

		Map<String, String[]> search = new HashMap<>();
		search.put("companyId", new String[] { (companyId).toString().trim() });
		List<User> availPrivList = userService.findDoc(search);
		map.put("entityList", g.toJson(availPrivList));
		return g.toJson(availPrivList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-users", method = RequestMethod.POST)
	public ModelAndView saveUsers(@Valid @ModelAttribute("user") User user) {
		ModelAndView modelAndView = new ModelAndView();
		userService.saveOrUpdateUsers(user);
		User emp = userService.getCurrentUser();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.addObject("users", userService.getAll());
		modelAndView.addObject("companyId", emp.getCompanyId());
		modelAndView.addObject("employeeInfos", employeeService.getAll("Y"));
		modelAndView.setViewName("application_settings/user_settings/user");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroy(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		userService.delete(map);
		User emp = userService.getCurrentUser();
		modelAndView.addObject("company", companyService.getAll());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.addObject("users", userService.getAll());
		modelAndView.addObject("companyId", emp.getCompanyId());
		modelAndView.addObject("employeeInfos", employeeService.getAll("Y"));
		modelAndView.setViewName("application_settings/user_settings/user");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-new-password", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSResponse saveNewPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> map = new HashMap<>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		map.put("username", new String[] { (username).toString().trim() });
		map.put("password", new String[] { (password).toString().trim() });
		int i = userService.changePassword(map);
		if (i > 0) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/validate-code/{username}", method = RequestMethod.GET)
	public WSResponse validateUsername(@PathVariable("username") String username) {
		Map<String, String[]> map = new HashMap<>();
		map.put("username", new String[] { (username).toString().trim() });
		boolean bool = userService.hasUsername(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/validate-employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public WSResponse validateEmployee(HttpServletRequest request) {
		Map<String, String[]> map = new HashMap<>();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		map.put("username", new String[] { (username).toString().trim() });
		map.put("email", new String[] { (email).toString().trim() });
		boolean bool = userService.hasEmployee(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/validate-password", method = RequestMethod.GET)
	public WSResponse validatePassword(HttpServletRequest request) {
		String pass = request.getParameter("password").trim();
		String username = request.getParameter("username").trim();
		User user = userService.findUserForPassword(username);

		if (BCrypt.checkpw(pass, user.getPassword())) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
