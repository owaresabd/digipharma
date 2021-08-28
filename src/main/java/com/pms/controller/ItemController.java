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
import com.pms.configure.bean.PageViewInfo;
import com.pms.model.ItemInfo;
import com.pms.service.CountryService;
import com.pms.service.GenericService;
import com.pms.service.ItemService;
import com.pms.service.ItemTypeService;
import com.pms.service.ManufacturerService;
import com.pms.service.UnitService;

@Controller
@RequestMapping(Router.ROOT_PATH_ITEM_INFO)
public class ItemController {

	@Autowired
	private ItemTypeService itemTypeService;
	@Autowired
	private GenericService genericService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CountryService countryService;

	@GetMapping(Router.ITEM_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("manufacturerInfos", manufacturerService.getAll("Y"));
		modelAndView.addObject("typeInfos", itemTypeService.getAll("Y"));
		modelAndView.addObject("genericInfos", genericService.getAll("Y"));
		modelAndView.addObject("unitInfos", unitService.getAll("Y"));
		modelAndView.addObject("countryInfos", countryService.getAll());
		modelAndView.addObject("infos", itemService.getAll(null));
		modelAndView.setViewName(PageViewInfo.ITEM_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = Router.ITEM_SAVE_INFO)
	public String saveItemInfo(@Valid @ModelAttribute("itemInfo") ItemInfo info) {
		itemService.saveOrUpdate(info);
		return "redirect:"+Router.ROOT_PATH_ITEM_INFO+Router.ITEM_LIST_INFO;
	}

	
}
