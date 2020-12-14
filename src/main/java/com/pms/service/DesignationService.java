package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DesignationInfo;
import com.pms.model.User;
import com.pms.repository.DesignationRepository;


@Service
@Transactional
public class DesignationService {
	@Autowired
	private IUserService userService;
	@Autowired
	private DesignationRepository designationRepository;

	public List<DesignationInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return designationRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getDesignationName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return designationRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getDesignationName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	

	public void saveOrUpdate(DesignationInfo info) {
		User user = userService.getCurrentUser();
		if (info.getId() == null ) {
			info.setCompanyId(user.getCompanyId());
			info.setCreatedBy(user.getId());

		} else {
			info.setUpdatedBy(user.getId());
		}
		designationRepository.save(info);
		
	}
	

	/*
	 * public boolean validateDesignationNo(Map<String, String[]> requestMap) {
	 * String designationNo = requestMap.get("designationNo")[0];
	 * List<DesignationInfo> entityTransDtlsList =
	 * designationRepository.validateDesignationNo(designationNo); if
	 * (entityTransDtlsList.size() == 0) { return true; }
	 * 
	 * return false; }
	 */
}
