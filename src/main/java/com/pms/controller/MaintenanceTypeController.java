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
import com.pms.model.MaintenanceTypeInfo;
import com.pms.service.MaintenanceTypeService;

@Controller
@RequestMapping("/maintenance_type")
public class MaintenanceTypeController {

	@Autowired
	private MaintenanceTypeService maintenanceTypeService;

	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", maintenanceTypeService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/maintenance_type");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/saveMaintenanceTypeInfo", method = RequestMethod.POST)
	public ModelAndView saveMaintenanceTypeInfo(@Valid @ModelAttribute("typeInfo") MaintenanceTypeInfo typeInfo) {
		ModelAndView modelAndView = new ModelAndView();
		maintenanceTypeService.saveMaintenanceTypeInfos(typeInfo);
		modelAndView.addObject("infos", maintenanceTypeService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/maintenance_type");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validateMaintenanceType/{typeName}", method = RequestMethod.GET)
	public WSResponse validateMaintenanceType(@PathVariable("typeName") String typeName) {
		Map<String, String[]> map = new HashMap<>();
		map.put("typeName", new String[] { (typeName).toString().trim() });
		boolean bool =maintenanceTypeService.validateMaintenanceTypeName(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
