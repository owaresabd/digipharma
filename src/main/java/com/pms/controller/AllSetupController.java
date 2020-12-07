package com.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.service.AllSetupService;
import com.pms.service.DepartmentService;
import com.pms.service.LocationService;

@Controller
@RequestMapping("/tempHumiditySetup")
public class AllSetupController {

	@Autowired
	private AllSetupService allSetupService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private LocationService locationService;

	

	@RequestMapping("/maintain")
	public ModelAndView tempHumiditySetupInfos() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("deptInfos", departmentService.getAll("Y"));
		modelAndView.addObject("locationInfos", locationService.getAll("Y"));
		modelAndView.addObject("equipInfos", allSetupService.getEquipments("Y"));
		modelAndView.addObject("infos", allSetupService.getTempHumiditySetupInfos());
		modelAndView.setViewName("setup/temp_humidity/temp_humidity_info");
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveTempHumiditySetupInfos")
	public String saveLogBookSetupInfos(HttpServletRequest request) {
		Map<String, String[]> requestMap = request.getParameterMap();
		allSetupService.saveTempHumiditySetupInfo(requestMap);
		return "redirect:/tempHumiditySetup/maintain";
	}

	
	
}
