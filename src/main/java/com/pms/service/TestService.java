package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.TestInfo;
import com.pms.model.User;
import com.pms.repository.TestRepository;

@Service
@Transactional
public class TestService {
	@Autowired
	private IUserService userService;
	@Autowired
	private TestRepository testRepository;

	public List<TestInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return testRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getTestName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return testRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getTestName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	 public List<TestInfo> findById(UUID id) {

	    return testRepository.findById(id).stream().collect(Collectors.toList());
	}

	public void saveOrUpdate(TestInfo info) {
		User user = userService.getCurrentUser();
		System.out.println(user.getCompanyId());
		if (info.getId() == null ) {
			info.setCompanyId(user.getCompanyId());
			info.setCreatedBy(user.getId());

		} else {
			info.setUpdatedBy(user.getId());
			info.setCompanyId(user.getCompanyId());
		}
		testRepository.save(info);
		
	}
	

}
