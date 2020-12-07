package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.RackInfo;
import com.pms.repository.RackRepository;

@Service
public class RackService {

	@Autowired
	private RackRepository rackRepository;

	public List<RackInfo> getAll(String status) {
		return rackRepository.findAll(status);
	}

	

	public void saveRackInfos(RackInfo methodInfo) {
		rackRepository.saveRackInfos(methodInfo);
	}

	public boolean validateRackName(Map<String, String[]> requestMap) {
		String rackName = requestMap.get("rackName")[0];
		List<RackInfo> entityTransList = rackRepository.validateRackName(rackName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
