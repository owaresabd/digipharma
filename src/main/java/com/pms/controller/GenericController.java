package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.GenericInfo;
import com.pms.service.GenericService;

@Controller
@RequestMapping("/generic")
public class GenericController {

	@Autowired
	private GenericService genericService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", genericService.getAll(null));
		modelAndView.setViewName("setup/generic_info");
		return modelAndView;
	}

	@PostMapping(value = "/save-generic-info")
	public ModelAndView add(@Valid @ModelAttribute("genericInfo") GenericInfo info) {
		ModelAndView modelAndView = new ModelAndView();
		
		genericService.saveOrUpdate(info);
		modelAndView.addObject("infos", genericService.getAll(null));
		modelAndView.setViewName("setup/generic_info");
		return modelAndView;
	}

}
