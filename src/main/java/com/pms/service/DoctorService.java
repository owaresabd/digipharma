package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DoctorInfo;
import com.pms.model.User;
import com.pms.repository.DoctorRepository;

@Service
@Transactional
public class DoctorService {
	@Autowired
	private IUserService userService;
	@Autowired
	private DoctorRepository doctorRepository;

	public List<DoctorInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return doctorRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getDoctorName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return doctorRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getDoctorName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	

	public void saveOrUpdate(DoctorInfo info) {
		User user = userService.getCurrentUser();
		info.setCompanyId(user.getCompanyId());
		if (info.getId() == null ) {
			info.setCreatedBy(user.getId());

		} else {
			info.setUpdatedBy(user.getId());
		}
		doctorRepository.save(info);
		
	}
	

	
}
