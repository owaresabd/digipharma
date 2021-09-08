package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CustomerInfo;
import com.pms.model.User;
import com.pms.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private IUserService userService;
	@Autowired
	private CustomerRepository customerRepository;

	public List<CustomerInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return customerRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getCustomerName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return customerRepository.findAll().stream()
					.filter(info -> info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getCustomerName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	public void saveOrUpdate(CustomerInfo info) {
		User user = userService.getCurrentUser();
		if (info.getId() == null) {
			info.setCompanyId(user.getCompanyId());
			info.setCreatedBy(user.getId());

		} else {
			info.setCompanyId(user.getCompanyId());
			info.setUpdatedBy(user.getId());
		}
		customerRepository.save(info);

	}

	
}
