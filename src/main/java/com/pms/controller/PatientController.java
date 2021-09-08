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
import com.pms.model.PatientInfo;
import com.pms.service.PatientService;



@Controller
@RequestMapping(Router.ROOT_PATH_PATIENT_INFO )
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping(Router.PATIENT_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("info", patientService.getAll());
		modelAndView.setViewName(PageInfo.PATIENT_INFO);
		return modelAndView;
	}
	
	
	@PostMapping(value = Router.PATIENT_SAVE_INFO)
	public ModelAndView saveOrUpdatePatientInfo(@Valid @ModelAttribute("patientInfo") PatientInfo info) {
		
		ModelAndView modelAndView = new ModelAndView();
		patientService.saveOrUpdate(info);
		modelAndView.addObject("info", patientService.getAll());
		modelAndView.setViewName(PageInfo.PATIENT_INFO);
		return modelAndView;
	}

	
	
	
	
	

}
