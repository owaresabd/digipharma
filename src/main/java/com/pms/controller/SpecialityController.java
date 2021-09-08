package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.PageInfo;
import com.pms.configure.bean.Router;
import com.pms.model.SpecialityInfo;
import com.pms.service.SpecialityService;

@Controller
@RequestMapping(Router.ROOT_PATH_SPECIALITY_INFO)
public class SpecialityController {

	@Autowired
	private SpecialityService specialityService;

	@GetMapping(Router.SPECIALITY_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", specialityService.getAll(null));
		modelAndView.setViewName(PageInfo.SPECIALITY_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = Router.SPECIALITY_SAVE_INFO)
	public ModelAndView saveSpecialityInfo(@Valid @ModelAttribute("specialityInfo") SpecialityInfo info) {
		specialityService.saveOrUpdate(info);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", specialityService.getAll(null));
		modelAndView.setViewName(PageInfo.SPECIALITY_INFO);
		return modelAndView;
	}
	
	
}
