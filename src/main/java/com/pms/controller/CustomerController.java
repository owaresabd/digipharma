package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.Router;
import com.pms.configure.bean.PageInfo;
import com.pms.model.CustomerInfo;
import com.pms.service.CustomerService;
import com.pms.service.CustomerTypeService;



@Controller
@RequestMapping(Router.ROOT_PATH_CUSTOMER_INFO)
public class CustomerController {
	
	@Autowired
	private CustomerTypeService CustomerTypeService;
	@Autowired
	private CustomerService customerService;

	@GetMapping(Router.CUSTOMER_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", customerService.getAll(null));
		modelAndView.addObject("typeInfos", CustomerTypeService.getAll("Y"));
		modelAndView.setViewName(PageInfo.CUSTOMER_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = Router.CUSTOMER_SAVE_INFO)
	public String saveCustomers(@Valid @ModelAttribute("customerInfo") CustomerInfo customerInfo) {
		customerService.saveOrUpdate(customerInfo);
		return "redirect:"+Router.ROOT_PATH_CUSTOMER_INFO+Router.CUSTOMER_LIST_INFO;
	}

	
}
