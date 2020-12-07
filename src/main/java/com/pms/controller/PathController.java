package com.pms.controller;

import java.util.Calendar;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class PathController {

	@ResponseBody
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Map<String, Object> model) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("index");
		return modelAndView;

	}

	public String getCurrentMonth() {
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		Calendar cal = Calendar.getInstance();
		String month = monthName[cal.get(Calendar.MONTH)];
		int year = cal.get(Calendar.YEAR);
		String months = month + " - " + year;

		return months;
	}

}
