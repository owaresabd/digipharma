package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.DesignationInfo;
import com.pms.service.DesignationService;

@Controller
@RequestMapping("/designation")
public class DesignationController {

	@Autowired
	private DesignationService designationService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("designationList", designationService.getAll(null));
		modelAndView.setViewName("setup/designation_info");
		return modelAndView;
	}

	@PostMapping(value = "/save-designation-info")
	public ModelAndView saveDesignationInfo(@Valid @ModelAttribute("designationInfo") DesignationInfo info) {
		
		ModelAndView modelAndView = new ModelAndView();
		designationService.saveOrUpdate(info);

		modelAndView.addObject("designationList", designationService.getAll(null));
		modelAndView.setViewName("setup/designation_info");
		return modelAndView;
	}

}
