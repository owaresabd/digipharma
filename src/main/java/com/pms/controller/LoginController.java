package com.pms.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.service.IUserService;
import com.pms.model.RolePrivilege;
import com.pms.model.User;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;
	
	
	
	@ResponseBody
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(Map<String, Object> model) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("login");

		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Map<String, Object> model) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser();
		
		if (user != null) {
			List<RolePrivilege> menuList = userService.getAllMenu(user.getTypeId());
			
			model.put("user", user);
			model.put("menuList", menuList);
			model.put("count", menuList.size());
			
			/*
			 * GsonBuilder gson = new GsonBuilder(); Gson g = gson.create();
			 * modelAndView.addObject("chartInfo",
			 * g.toJson(dashboardService.getMonthlyTestInfos()));
			 * modelAndView.addObject("pieChartInfo",
			 * g.toJson(dashboardService.getTypeWiseTestInfos()));
			 */
			
			
			modelAndView.setViewName("index");
			//return modelAndView;
		}

		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/blankPage", method = RequestMethod.GET)
	public ModelAndView basicForm() {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.getCurrentUser();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("blanks");
		return modelAndView;
	}

	public String getCurrentMonth() {
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		Calendar cal = Calendar.getInstance();
		String month = monthName[cal.get(Calendar.MONTH)];
		int year = cal.get(Calendar.YEAR);
		String months = month + " - " + year;

		return months;
	}
}
