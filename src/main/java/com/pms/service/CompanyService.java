package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Company;
import com.pms.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	public List<Company> getAllComInfo(UUID companyId) {
		return companyRepository.findCompanyInfo(companyId);
	}

	public Company getById(UUID id) {
		return companyRepository.findById(id);
	}

	public void manageCompanies(Company company) {
		companyRepository.manageCompanies(company);
	}

	public UUID delete(Map<String, String[]> requestMap) {
		UUID id = UUID.fromString(requestMap.get("id")[0]);
		return companyRepository.delete(id);
	}
}
