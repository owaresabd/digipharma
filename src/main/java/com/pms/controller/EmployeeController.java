package com.pms.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.service.DesignationService;
import com.pms.service.EmployeeService;



@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private DesignationService designationService;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("employeeInfos", employeeService.getAll(null));
		modelAndView.addObject("desigInfos", designationService.getAll("Y"));
		modelAndView.setViewName("setup/employee_info");
		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/save-employee-info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView saveEmployeeInfo(HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> requestMap = request.getParameterMap();
		employeeService.saveEmployeeInfos(requestMap);
		modelAndView.addObject("employeeInfos", employeeService.getAll(null));
		modelAndView.addObject("desigInfos", designationService.getAll("Y"));
		modelAndView.setViewName("setup/employee_info");
		return modelAndView;
	}
	
	
	
	
	

}
