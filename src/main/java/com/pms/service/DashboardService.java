package com.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.DashboardInfo;
import com.pms.repository.DashboardRepository;

@Service
public class DashboardService {

	@Autowired
	private DashboardRepository dashboardRepository;

	public DashboardInfo getDashboardInfo() {
		return dashboardRepository.getDashboardInfo();
	}

	public DashboardInfo getAccessInfo() {
		return dashboardRepository.getAccessInfo();
	}
	public List<DashboardInfo> getMonthlyTestInfos() {
		return dashboardRepository.getMonthlyTestInfos();
	}
	public List<DashboardInfo> getTypeWiseTestInfos() {
		return dashboardRepository.getTypeWiseTestInfos();
	}

}
