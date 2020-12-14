package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.ManufacturerInfo;
import com.pms.service.ManufacturerService;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;
	
	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", manufacturerService.getAll(null));
		modelAndView.setViewName("setup/manufacturer_info");
		return modelAndView;
	}

	@PostMapping(value = "/save-manufacturer-info")
	public String  saveManufactureInfo(@Valid @ModelAttribute("manufacturerInfo") ManufacturerInfo info) {
		manufacturerService.saveOrUpdate(info);
		return "redirect:/manufacturer/maintain";
	}

	
	
	
}
