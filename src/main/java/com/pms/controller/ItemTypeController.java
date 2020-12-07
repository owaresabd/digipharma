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
import com.pms.model.ItemTypeInfo;
import com.pms.service.ItemTypeService;

@Controller
@RequestMapping("/itemType")
public class ItemTypeController {

	@Autowired
	private ItemTypeService itemTypeService;

	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", itemTypeService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/itemType");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/itemTypeDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getItemTypeDetails(@PathVariable("id") String id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		ItemTypeInfo jsonList = itemTypeService.getById(map);
		return g.toJson(jsonList);
	}

	@ResponseBody
	@RequestMapping(value = "/save-item-type", method = RequestMethod.POST)
	public ModelAndView saveItemTypes(@Valid @ModelAttribute("itemTypeInfo") ItemTypeInfo itemTypeInfo) {
		ModelAndView modelAndView = new ModelAndView();
		itemTypeService.saveItemTypeInfos(itemTypeInfo);
		modelAndView.addObject("infos", itemTypeService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/itemType");
		return modelAndView;
	}

	@RequestMapping(value = "/delete-item-type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView destroyItemTypes(@PathVariable("id") UUID id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String[]> map = new HashMap<>();
		map.put("id", new String[] { (id).toString().trim() });
		itemTypeService.delete(map);
		modelAndView.addObject("infos", itemTypeService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/itemType");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-udItemTypeNo/{udItemTypeNo}", method = RequestMethod.GET)
	public WSResponse validateUdItemTypeNo(@PathVariable("udItemTypeNo") String udItemTypeNo) {
		Map<String, String[]> map = new HashMap<>();
		map.put("udItemTypeNo", new String[] { (udItemTypeNo).toString().trim() });
		boolean bool = itemTypeService.hasItemTypeCode(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
