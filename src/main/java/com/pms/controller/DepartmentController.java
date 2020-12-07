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
import com.pms.model.DepartmentInfo;
import com.pms.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("deptList", departmentService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/dept_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/save-department-info", method = RequestMethod.POST)
	public ModelAndView saveDepartmentInfo(@Valid @ModelAttribute("departmentInfo") DepartmentInfo departmentInfo) {
		ModelAndView modelAndView = new ModelAndView();
		departmentService.saveDepartmentInfos(departmentInfo);
		modelAndView.addObject("deptList", departmentService.getAll(null));
		modelAndView.setViewName("warehouse_dashboard/setup_info/dept_info");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/validate-deptNo/{deptNo}", method = RequestMethod.GET)
	public WSResponse validateDeptNo(@PathVariable("deptNo") String deptNo) {
		Map<String, String[]> map = new HashMap<>();
		map.put("deptNo", new String[] { (deptNo).toString().trim() });
		boolean bool = departmentService.validateDeptNo(map);
		if (!bool) {
			return new WSResponse("true", null, null, null, null, null);
		} else {
			return new WSResponse("false", null, null, null, null, null);
		}
	}
}
