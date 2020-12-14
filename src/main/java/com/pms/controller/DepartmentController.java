package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.model.DepartmentInfo;
import com.pms.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/maintain")
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("deptList", departmentService.getAll(null));
		modelAndView.setViewName("setup/dept_info");
		return modelAndView;
	}

	
	@PostMapping(value = "/save-department-info")
	public String saveDepartmentInfo(@Valid @ModelAttribute("departmentInfo") DepartmentInfo info) {
		departmentService.saveOrUpdate(info);
		return "redirect:/department/maintain";
		
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/validate-deptNo/{deptNo}", method =
	 * RequestMethod.GET) public WSResponse validateDeptNo(@PathVariable("deptNo")
	 * String deptNo) { Map<String, String[]> map = new HashMap<>();
	 * map.put("deptNo", new String[] { (deptNo).toString().trim() }); boolean bool
	 * = departmentService.validateDeptNo(map); if (!bool) { return new
	 * WSResponse("true", null, null, null, null, null); } else { return new
	 * WSResponse("false", null, null, null, null, null); } }
	 */
}
