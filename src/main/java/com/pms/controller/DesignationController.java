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
import com.pms.model.DesignationInfo;
import com.pms.service.DesignationService;


@Controller
@RequestMapping("/designation")
public class DesignationController {

	@Autowired
	private DesignationService designationService;

	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("designationList", designationService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/designation_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-designation-info", method = RequestMethod.POST)
	public ModelAndView saveDesignationInfo(@Valid @ModelAttribute("designationInfo") DesignationInfo designationInfo) {
		ModelAndView modelAndView = new ModelAndView();
		designationService.saveDesignationInfos(designationInfo);
		modelAndView.addObject("designationList", designationService.getAll(null));
		modelAndView.setViewName("inventory_management/setup_info/designation_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-designationNo/{designationNo}", method = RequestMethod.GET)
	public WSResponse validateDesignationNo(@PathVariable("designationNo") String designationNo) {
		Map<String, String[]> map = new HashMap<>();
		map.put("designationNo", new String[] { (designationNo).toString().trim() });
		boolean bool = designationService.validateDesignationNo(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
