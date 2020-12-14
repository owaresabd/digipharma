package com.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.CustomerTypeInfo;
import com.pms.service.CustomerTypeService;

@Controller
@RequestMapping("/customer_type")
public class CustomerTypeController {

	@Autowired
	private CustomerTypeService customerTypeService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", customerTypeService.getAll(null));
		modelAndView.setViewName("setup/customer_type_info");
		return modelAndView;
	}

	

	@PostMapping(value = "/save-customer-type")
	public String add(@ModelAttribute("customerTypeInfo") CustomerTypeInfo info) {
		customerTypeService.saveOrUpdate(info);
		return "redirect:/customer_type/maintain";
	}

	
}
