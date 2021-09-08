package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.EmployeeInfo;
import com.pms.model.User;
import com.pms.repository.EmployeeRepository;



@Service
@Transactional
public class EmployeeService {
	@Autowired
	private IUserService userService;
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return employeeRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getEmpName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return employeeRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getEmpName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	public void saveOrUpdate(EmployeeInfo info) {
		User user = userService.getCurrentUser();
		info.setCompanyId(user.getCompanyId());
		if (info.getId() == null ) {
			
			info.setCreatedBy(user.getId());

		} else {
			info.setUpdatedBy(user.getId());
		}
		employeeRepository.save(info);
		
	}

	

}
