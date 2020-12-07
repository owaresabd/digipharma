package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.LocationInfo;
import com.pms.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public List<LocationInfo> getAll(String status) {
		return locationRepository.findAll(status);
	}

	

	public void saveLocationInfos(LocationInfo info) {
		locationRepository.saveLocationInfos(info);
	}

	public boolean validateTypeNo(Map<String, String[]> requestMap) {
		String locationId = requestMap.get("locationId")[0];
		List<LocationInfo> entityTransDtlsList = locationRepository.validateTypeNo(locationId);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
