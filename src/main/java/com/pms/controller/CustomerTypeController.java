package com.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.ControllerPathInfo;
import com.pms.configure.bean.PagePathInfo;
import com.pms.model.CustomerTypeInfo;
import com.pms.service.CustomerTypeService;

@Controller
@RequestMapping(ControllerPathInfo.ROOT_PATH_CUSTOMER_TYPE_INFO)
public class CustomerTypeController {

	@Autowired
	private CustomerTypeService customerTypeService;

	@GetMapping(ControllerPathInfo.ROOT_PATH_CUSTOMER_TYPE_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", customerTypeService.getAll(null));
		modelAndView.setViewName(PagePathInfo.PAGE_CUSTOMER_TYPE_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = "/save-customer-type")
	public String add(@ModelAttribute("customerTypeInfo") CustomerTypeInfo info) {
		customerTypeService.saveOrUpdate(info);
		return "redirect:"+ControllerPathInfo.ROOT_PATH_CUSTOMER_TYPE_INFO+ControllerPathInfo.ROOT_PATH_CUSTOMER_TYPE_LIST_INFO;
	}

	
}
