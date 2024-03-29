package com.pms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CountryInfo;
import com.pms.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<CountryInfo> getAll() {
		return countryRepository.findAllCountry();
	}

	

}
