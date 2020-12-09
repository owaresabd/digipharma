package com.pms.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.CountryInfo;

@RestController
@RequestMapping("/country")
public class CountryController {

	
	@RequestMapping("/maintain")
	public ModelAndView maintainCountry() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-country-infos", method = RequestMethod.POST)
	public ModelAndView saveCountryInfos(@Valid @ModelAttribute("countryInfo") CountryInfo countryInfo) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("warehouse_dashboard/setup_info/country_info");
		return modelAndView;
	}

}
