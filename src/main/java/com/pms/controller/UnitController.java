package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.UnitInfo;
import com.pms.service.UnitService;

@Controller
@RequestMapping("/unit")
public class UnitController {

	@Autowired
	private UnitService unitService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", unitService.getAll(null));
		modelAndView.setViewName("setup/unit_info");
		return modelAndView;
	}

	@PostMapping(value = "/save-unit-info")
	public ModelAndView add(@Valid @ModelAttribute("unitInfo") UnitInfo info) {
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		unitService.saveOrUpdate(info);
		modelAndView.addObject("infos", unitService.getAll(null));
		modelAndView.setViewName("setup/unit_info");
		return modelAndView;
	}

}
