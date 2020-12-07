package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.ManufacturerInfo;
import com.pms.service.ManufacturerService;

@Controller
@RequestMapping("/manufacture")
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;
	
	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("manufactureList", manufacturerService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/manufacturer_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-manufacture-info", method = RequestMethod.POST)
	public ModelAndView saveManufactureInfo(@Valid @ModelAttribute("manufacturInfo") ManufacturerInfo manufacturerInfo) {
		ModelAndView modelAndView = new ModelAndView();
		manufacturerService.saveManufacturerInfos(manufacturerInfo);
		modelAndView.addObject("manufactureList", manufacturerService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/manufacturer_info");
		return modelAndView;
	}

	
	
	
}
