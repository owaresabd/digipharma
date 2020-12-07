package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DepartmentInfo;
import com.pms.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<DepartmentInfo> getAll(String status) {
		return departmentRepository.findAll(status);
	}

	public List<DepartmentInfo> getAllItemsDeprtment() {
		return departmentRepository.findAllItemsDeprtment();
	}

	public void saveDepartmentInfos(DepartmentInfo departmentInfo) {
		departmentRepository.saveDepartmentInfos(departmentInfo);
	}

	public boolean validateDeptNo(Map<String, String[]> requestMap) {
		String deptNo = requestMap.get("deptNo")[0];
		List<DepartmentInfo> entityTransDtlsList = departmentRepository.validateDeptNo(deptNo);
		if (entityTransDtlsList.size() == 0) {
			return true;
		}

		return false;
	}
}
