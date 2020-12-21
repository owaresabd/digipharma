package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.ControllerInfo;
import com.pms.configure.bean.PageViewInfo;
import com.pms.model.DepartmentInfo;
import com.pms.service.DepartmentService;

@Controller
@RequestMapping(ControllerInfo.ROOT_PATH_DEPARTMENT_INFO)
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping(ControllerInfo.DEPARTMENT_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("deptList", departmentService.getAll(null));
		modelAndView.setViewName(PageViewInfo.DEPARTMENT_INFO);
		return modelAndView;
	}

	
	@PostMapping(value = ControllerInfo.DEPARTMENT_SAVE_INFO)
	public String saveDepartmentInfo(@Valid @ModelAttribute("departmentInfo") DepartmentInfo info) {
		departmentService.saveOrUpdate(info);
		return "redirect:"+ControllerInfo.ROOT_PATH_DEPARTMENT_INFO+ControllerInfo.DEPARTMENT_LIST_INFO;
		
	}

	
}
