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
import com.pms.model.DoctorInfo;
import com.pms.service.DepartmentService;
import com.pms.service.DesignationService;
import com.pms.service.DoctorService;
import com.pms.service.SpecialityService;



@Controller
@RequestMapping(Router.ROOT_PATH_DOCTOR_INFO )
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DesignationService designationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SpecialityService specialityService;
	
	@GetMapping(Router.DOCTOR_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		
	    modelAndView.addObject("infos", doctorService.getAll(null));
	    modelAndView.addObject("designationInfos", designationService.getAll("Y"));
	    modelAndView.addObject("departmentInfos", departmentService.getAll("Y"));
	    modelAndView.addObject("specialityInfos", specialityService.getAll("Y"));
		modelAndView.setViewName(PageInfo.DOCTOR_INFO);
		return modelAndView;
	}
	
	
	@PostMapping(value = Router.PATIENT_SAVE_INFO)
	public ModelAndView saveOrUpdateDoctorInfo(@Valid @ModelAttribute("doctorInfo") DoctorInfo info) {
		
		ModelAndView modelAndView = new ModelAndView();
		doctorService.saveOrUpdate(info);
		 modelAndView.addObject("infos", doctorService.getAll(null));
		 modelAndView.addObject("designationInfos", designationService.getAll("Y"));
		 modelAndView.addObject("departmentInfos", departmentService.getAll("Y"));
		 modelAndView.addObject("specialityInfos", specialityService.getAll("Y"));
		modelAndView.setViewName(PageInfo.DOCTOR_INFO);
		return modelAndView;
	}

	
	
	
	
	

}
