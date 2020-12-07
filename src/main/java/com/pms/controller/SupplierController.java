package com.pms.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pms.configure.bean.WSResponse;
import com.pms.model.SupplierInfo;
import com.pms.service.SupplierService;



@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", supplierService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/supplierInfo");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/supplierDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getSupplierDetails(@PathVariable("id") String id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		SupplierInfo jsonList = supplierService.getById(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-suppliers", method = RequestMethod.POST)
	public ModelAndView saveSuppliers(@Valid @ModelAttribute("supplierInfo") SupplierInfo supplierInfo) {
		ModelAndView modelAndView = new ModelAndView();
		supplierService.saveSupplierInfos(supplierInfo);
		modelAndView.addObject("infos", supplierService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/supplierInfo");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-suppliers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroySuppliers(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		supplierService.delete(map);
		modelAndView.addObject("infos", supplierService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/supplierInfo");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-supplierCode/{supplierCode}", method = RequestMethod.GET)
	public WSResponse validateSupplierCode(@PathVariable("supplierCode") String supplierCode) {
		Map<String, String[]> map = new HashMap<>();
		map.put("supplierCode", new String[] { (supplierCode).toString().trim() });
		boolean bool = supplierService.hasSupplierCode(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
