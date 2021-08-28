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
import com.pms.model.ItemTypeInfo;
import com.pms.service.ItemTypeService;

@Controller
@RequestMapping(Router.ROOT_PATH_ITEM_TYPE_INFO)
public class ItemTypeController {

	@Autowired
	private ItemTypeService itemTypeService;

	@GetMapping(Router.ITEM_TYPE_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", itemTypeService.getAll(null));
		modelAndView.setViewName(PageViewInfo.ITEM_TYPE_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = Router.ITEM_TYPE_SAVE_INFO)
	public String saveItemTypes(@Valid @ModelAttribute("itemTypeInfo") ItemTypeInfo info) {
		itemTypeService.saveOrUpdate(info);
		return "redirect:"+Router.ROOT_PATH_ITEM_TYPE_INFO+Router.ITEM_TYPE_LIST_INFO;
	}

	
}
