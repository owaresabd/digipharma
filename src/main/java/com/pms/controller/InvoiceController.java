package com.pms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pms.configure.bean.Router;
import com.pms.model.SupplierInfo;
import com.pms.service.SupplierService;
import com.pms.service.SupplierTypeService;
import com.pms.service.TestService;



@Controller
@RequestMapping(Router.ROOT_PATH_INVOICE_INFO)
public class InvoiceController {
	
	@Autowired
	private SupplierTypeService supplierTypeService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private TestService testService;

	
	@GetMapping(Router.INVOICE_ENTRY_INFO)
	public ModelAndView invoiceEntry() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("invoiceNo", ("INV-"+System.currentTimeMillis()));
		modelAndView.addObject("testInfos", testService.getAll("Y"));
		modelAndView.setViewName("pms/billing/invoice_info");
		return modelAndView;
	}
	@GetMapping(Router.INVOICE_LIST_INFO)
	public ModelAndView invoiceList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("poNo", ("P"+System.currentTimeMillis()));
		modelAndView.addObject("infos", supplierService.getAll(null));
		modelAndView.addObject("typeInfos", supplierTypeService.getAll("Y"));
		modelAndView.setViewName("transaction/purchase_info");
		return modelAndView;
	}
	
	
	@PostMapping(value = Router.PURCHASE_SAVE_INFO)
	public String saveSuppliers(@Valid @ModelAttribute("supplierInfo") SupplierInfo supplierInfo) {
		supplierService.saveOrUpdate(supplierInfo);
		return "redirect:"+Router.ROOT_PATH_PURCHASE_INFO+Router.PURCHASE_LIST_INFO;
	}
	@GetMapping(Router.REPORT_INFO)
	public ModelAndView reports() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("suppInfos", supplierService.getAll(null));
		modelAndView.addObject("typeInfos", supplierTypeService.getAll("Y"));
		modelAndView.setViewName("transaction/purchase_reports");
		return modelAndView;
	}
	
}
