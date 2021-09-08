package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.PatientInfo;
import com.pms.model.User;
import com.pms.repository.PatientRepository;

@Service
@Transactional
public class PatientService {
	@Autowired
	private IUserService userService;
	@Autowired
	private PatientRepository patientRepository;

	public List<PatientInfo> getAll() {
		User user = userService.getCurrentUser();
		
			return patientRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getPatientName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		
	}

	

	public void saveOrUpdate(PatientInfo info) {
		User user = userService.getCurrentUser();
		info.setCompanyId(user.getCompanyId());
		
		if (info.getId() == null ) {
			info.setCreatedBy(user.getId());

		} else {
			info.setUpdatedBy(user.getId());
		}
		patientRepository.save(info);
		
	}
	

}
