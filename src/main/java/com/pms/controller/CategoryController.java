package com.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.service.DepartmentService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	
	@Autowired
	private DepartmentService departmentService;
	
	
	

	@RequestMapping("/infos")
	public ModelAndView infos() {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveTempHumiditySetupInfos")
	public String saveLogBookSetupInfos(HttpServletRequest request) {
		Map<String, String[]> requestMap = request.getParameterMap();
		return "redirect:/tempHumiditySetup/maintain";
	}

	
	
}
