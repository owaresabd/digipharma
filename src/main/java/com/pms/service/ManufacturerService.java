package com.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ManufacturerInfo;
import com.pms.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	public List<ManufacturerInfo> getAll(String status) {
		return manufacturerRepository.findAll(status);
	}

	public void saveManufacturerInfos(ManufacturerInfo manufacturerInfo) {
		manufacturerRepository.saveManufacturerInfos(manufacturerInfo);
	}

	
	
}
