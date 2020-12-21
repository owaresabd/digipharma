package com.pms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.service.CompanyService;
import com.pms.configure.bean.ControllerInfo;
import com.pms.model.Company;

@Controller
@RequestMapping(ControllerInfo.ROOT_PATH_COMPANY_INFO)
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping("/manage")
	public ModelAndView manage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companies", companyService.getAll());
		modelAndView.setViewName("application_settings/user_settings/company");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/manage-company", method = RequestMethod.POST)
	public ModelAndView manageCompany(@Valid @ModelAttribute("company") Company company) {
		companyService.manageCompanies(company);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("companies", companyService.getAll());
		modelAndView.setViewName("application_settings/user_settings/company");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroy(@PathVariable("id") UUID id, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });

		companyService.delete(map);
		modelAndView.addObject("companies", companyService.getAll());
		modelAndView.setViewName("application_settings/user_settings/company");
		return modelAndView;
	}
}
