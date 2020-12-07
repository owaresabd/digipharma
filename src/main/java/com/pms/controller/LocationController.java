package com.pms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.WSResponse;
import com.pms.model.LocationInfo;
import com.pms.service.LocationService;

@Controller
@RequestMapping("/location_setup")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("locationList", locationService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/location_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-location-info", method = RequestMethod.POST)
	public ModelAndView saveLocationInfo(@Valid @ModelAttribute("locationInfo") LocationInfo locationInfo) {
		ModelAndView modelAndView = new ModelAndView();
		locationService.saveLocationInfos(locationInfo);
		modelAndView.addObject("locationList", locationService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/location_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-typeNo/{locationId}", method = RequestMethod.GET)
	public WSResponse validateTypeNo(@PathVariable("locationId") String locationId) {
		Map<String, String[]> map = new HashMap<>();
		map.put("locationId", new String[] { (locationId).toString().trim() });
		boolean bool = locationService.validateTypeNo(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
