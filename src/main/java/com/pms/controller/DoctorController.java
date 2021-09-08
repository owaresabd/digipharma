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
import com.pms.service.DoctorService;



@Controller
@RequestMapping(Router.ROOT_PATH_DOCTOR_INFO )
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping(Router.DOCTOR_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("infos", doctorService.getAll(null));
		modelAndView.setViewName(PageInfo.DOCTOR_INFO);
		return modelAndView;
	}
	
	
	@PostMapping(value = Router.PATIENT_SAVE_INFO)
	public ModelAndView saveOrUpdateDoctorInfo(@Valid @ModelAttribute("patientInfo") DoctorInfo info) {
		
		ModelAndView modelAndView = new ModelAndView();
		doctorService.saveOrUpdate(info);
		modelAndView.addObject("infos", doctorService.getAll(null));
		modelAndView.setViewName(PageInfo.DOCTOR_INFO);
		return modelAndView;
	}

	
	
	
	
	

}
