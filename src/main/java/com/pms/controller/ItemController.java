package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.ItemTypeInfo;
import com.pms.service.ItemTypeService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemTypeService itemTypeService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", itemTypeService.getAll(null));
		modelAndView.setViewName("setup/item_type_info");
		return modelAndView;
	}

	

	@PostMapping(value = "/save-item-type")
	public String saveItemTypes(@Valid @ModelAttribute("itemTypeInfo") ItemTypeInfo info) {
		itemTypeService.saveOrUpdate(info);
		return "redirect:/item_type/maintain";
	}

	
}
