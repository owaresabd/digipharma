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
	public String saveOrUpdateTestInfo(@Valid @ModelAttribute("testInfo") TestInfo info) {
		testService.saveOrUpdate(info);
		return "redirect:"+Router.ROOT_PATH_TEST_INFO+Router.TEST_LIST_INFO;
	}

}
