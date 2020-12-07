package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.CountryInfo;
import com.pms.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping("/maintain")
	public ModelAndView maintainCountry() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", countryService.getAllCountry(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/country_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-country-infos", method = RequestMethod.POST)
	public ModelAndView saveCountryInfos(@Valid @ModelAttribute("countryInfo") CountryInfo countryInfo) {
		ModelAndView modelAndView = new ModelAndView();
		countryService.saveCountryInfos(countryInfo);
		modelAndView.addObject("infos", countryService.getAllCountry(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/country_info");
		return modelAndView;
	}

}
