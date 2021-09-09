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
import com.pms.model.ExpenseTypeInfo;
import com.pms.service.ExpenseTypeService;

@Controller
@RequestMapping(Router.ROOT_PATH_EXPENSE_TYPE_INFO)
public class ExpenseTypeController {

	@Autowired
	private ExpenseTypeService expenseTypeService;

	@GetMapping(Router.EXPENSE_TYPE_LIST_INFO)
	public ModelAndView maintain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", expenseTypeService.getAll(null));
		modelAndView.setViewName(PageInfo.EXPENSE_TYPE_INFO);
		return modelAndView;
	}

	

	@PostMapping(value = Router.EXPENSE_TYPE_SAVE_INFO)
	public ModelAndView saveExpenseTypes(@Valid @ModelAttribute("expenseTypeInfo") ExpenseTypeInfo info) {
		expenseTypeService.saveOrUpdate(info);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("infos", expenseTypeService.getAll(null));
		modelAndView.setViewName(PageInfo.EXPENSE_TYPE_INFO);
		return modelAndView;
	}
	
	
}
