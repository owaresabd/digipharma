package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DisinfectantInfo;
import com.pms.repository.DisinfectantRepository;

@Service
public class DisinfectantService {

	@Autowired
	private DisinfectantRepository disinfectantRepository;

	public List<DisinfectantInfo> getAll(String status) {
		return disinfectantRepository.findAll(status);
	}

	
	public void saveDisinfectantInfos(DisinfectantInfo info) {
		disinfectantRepository.saveDisinfectantInfos(info);
	}

	public boolean validateTypeName(Map<String, String[]> requestMap) {
		String typeName = requestMap.get("typeName")[0];
		List<DisinfectantInfo> entityTransList = disinfectantRepository.validateDisinfectantName(typeName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
