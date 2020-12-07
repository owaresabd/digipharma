package com.pms.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.configure.bean.Utility;
import com.pms.model.EmployeeInfo;
import com.pms.repository.EmployeeRepository;



@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeInfo> getAll(String status) {
		return employeeRepository.findAll(status);
	}

	public void saveEmployeeInfos(Map<String, String[]> requestMap) {
		EmployeeInfo info = new EmployeeInfo();
		String msId = requestMap.get("id")[0];

		if (msId.trim().equals("")) {
			info.setId(null);
		} else {
			info.setId(UUID.fromString(msId.toString()));
		}

		info.setUdEmpNo(requestMap.get("udEmpNo")[0]);
		info.setEmpName(requestMap.get("empName")[0]);
		info.setDesigId(UUID.fromString(requestMap.get("desigId")[0]));
		info.setFatherName(requestMap.get("fatherName")[0]);
		info.setMotherName(requestMap.get("motherName")[0]);
		info.setDob((Date) Utility.getSqlDate(requestMap.get("dob")[0]));
		info.setGenderId(requestMap.get("genderId")[0]);
		info.setBloodGroup(requestMap.get("bloodGroup")[0]);
		info.setMaritalStatus(requestMap.get("maritalStatus")[0]);
		info.setAddress(requestMap.get("address")[0]);
		info.setNid(requestMap.get("nid")[0]);
		info.setMobileNo(requestMap.get("mobileNo")[0]);
		info.setEmail(requestMap.get("email")[0]);
		info.setQualification(requestMap.get("qualification")[0]);
		info.setJoiningDate((Date) Utility.getSqlDate(requestMap.get("joiningDate")[0]));

		info.setStatus(requestMap.get("status")[0]);

		employeeRepository.saveEmployeeInfos(info);

	}

	public boolean validateUdEmpNo(Map<String, String[]> requestMap) {
		String udEmpNo = requestMap.get("udEmpNo")[0];
		List<EmployeeInfo> entityTransDtlsList = employeeRepository.validateUdEmpNo(udEmpNo);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}

}
