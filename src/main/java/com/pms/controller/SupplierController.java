package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.SupplierInfo;
import com.pms.service.SupplierService;



@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", supplierService.getAll(null));
		modelAndView.setViewName("setup/supplierInfo");
		return modelAndView;
	}

	

	@PostMapping(value = "/save-suppliers")
	public ModelAndView saveSuppliers(@Valid @ModelAttribute("supplierInfo") SupplierInfo supplierInfo) {
		ModelAndView modelAndView = new ModelAndView();
		supplierService.saveSupplierInfos(supplierInfo);
		modelAndView.addObject("infos", supplierService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/supplierInfo");
		return modelAndView;
	}

	
}
