package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.ControllerPathInfo;
import com.pms.configure.bean.PagePathInfo;
import com.pms.model.CustomerInfo;
import com.pms.service.CustomerService;
import com.pms.service.CustomerTypeService;



@Controller
@RequestMapping(ControllerPathInfo.ROOT_PATH_CUSTOMER_INFO)
public class CustomerController {
	
	@Autowired
	private CustomerTypeService CustomerTypeService;
	@Autowired
	private CustomerService customerService;

	@GetMapping(ControllerPathInfo.ROOT_PATH_CUSTOMER_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", customerService.getAll(null));
		modelAndView.addObject("typeInfos", CustomerTypeService.getAll("Y"));
		modelAndView.setViewName(PagePathInfo.PAGE_CUSTOMER_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = ControllerPathInfo.ROOT_PATH_CUSTOMER_SAVE_INFO)
	public String saveCustomers(@Valid @ModelAttribute("customerInfo") CustomerInfo customerInfo) {
		customerService.saveOrUpdate(customerInfo);
		return "redirect:"+ControllerPathInfo.ROOT_PATH_CUSTOMER_INFO+ControllerPathInfo.ROOT_PATH_CUSTOMER_LIST_INFO;
	}

	
}
