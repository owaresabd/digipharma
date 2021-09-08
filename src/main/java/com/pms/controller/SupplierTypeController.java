package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.SupplierTypeInfo;
import com.pms.service.SupplierTypeService;

@Controller
@RequestMapping("/supplier_type")
public class SupplierTypeController {

	@Autowired
	private SupplierTypeService supplierTypeService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", supplierTypeService.getAll(null));
		modelAndView.setViewName("setup/supplier_type_info");
		return modelAndView;
	}

	

	@PostMapping(value = "/save-supplier-type")
	public ModelAndView add(@Valid @ModelAttribute("supplierTypeInfo") SupplierTypeInfo info) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		supplierTypeService.saveOrUpdate(info);
		modelAndView.addObject("infos", supplierTypeService.getAll(null));
		modelAndView.setViewName("setup/supplier_type_info");
		return modelAndView;
	}

	
}
