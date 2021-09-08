package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.EmployeeInfo;
import com.pms.service.DesignationService;
import com.pms.service.EmployeeService;



@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private DesignationService designationService;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("employeeInfos", employeeService.getAll(null));
		modelAndView.addObject("desigInfos", designationService.getAll("Y"));
		modelAndView.setViewName("setup/employee_info");
		return modelAndView;
	}
	
	
	@PostMapping(value = "/save-employee-info")
	public ModelAndView saveEmployeeInfo(@Valid @ModelAttribute("employeeInfo") EmployeeInfo info) {
		ModelAndView modelAndView = new ModelAndView();
		employeeService.saveOrUpdate(info);
		modelAndView.addObject("employeeInfos", employeeService.getAll(null));
		modelAndView.addObject("desigInfos", designationService.getAll("Y"));
		modelAndView.setViewName("setup/employee_info");
		return modelAndView;
	}
	
	
	
	
	

}
