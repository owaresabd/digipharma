package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.SpecialityInfo;
import com.pms.model.User;
import com.pms.repository.SpecialityRepository;

@Service
@Transactional
public class SpecialityService {
	@Autowired
	private IUserService userService;
	@Autowired
	private SpecialityRepository specialityRepository;

	public List<SpecialityInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return specialityRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getSpecialityName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return specialityRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getSpecialityName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	

	public void saveOrUpdate(SpecialityInfo info) {
		User user = userService.getCurrentUser();
		if (info.getId() == null ) {
			info.setCompanyId(user.getCompanyId());
			info.setCreatedBy(user.getId());

		} else {
			info.setCompanyId(user.getCompanyId());
			info.setUpdatedBy(user.getId());
		}
		specialityRepository.save(info);
		
	}
	

	
}
