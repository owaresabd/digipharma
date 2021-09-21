package com.pms.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pms.configure.bean.PageInfo;
import com.pms.configure.bean.Router;
import com.pms.model.TestInfo;
import com.pms.service.TestService;


@Controller
@RequestMapping(Router.ROOT_PATH_TEST_INFO)
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping(Router.TEST_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", testService.getAll(null));
		modelAndView.setViewName(PageInfo.TEST_INFO);
		return modelAndView;
	}

	
   
	@PostMapping(value = Router.TEST_SAVE_INFO)
	public ModelAndView saveOrUpdateTestInfo(@Valid @ModelAttribute("testInfo") TestInfo info) {
		testService.saveOrUpdate(info);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", testService.getAll(null));
		modelAndView.setViewName(PageInfo.TEST_INFO);
		
		return modelAndView;
	}
	
	@ResponseBody
	@GetMapping(value =Router.INFO_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getItemInfos(@PathVariable("id") UUID id) {
		GsonBuilder gson = new GsonBuilder();
		Gson g = gson.create();
		List<TestInfo> info = testService.findById(id);
		return g.toJson(info);
	}

}
