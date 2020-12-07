package com.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.FrequencyInfo;
import com.pms.repository.FrequencyRepository;

@Service
public class FrequencyService {

	@Autowired
	private FrequencyRepository frequencyRepository;

	public List<FrequencyInfo> getAll(String status) {
		return frequencyRepository.findAll(status);
	}

	
	public void saveFrequencyInfos(FrequencyInfo info) {
		frequencyRepository.saveFrequencyInfos(info);
	}

	
}
