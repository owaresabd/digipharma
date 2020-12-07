package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.DistributionInfo;
import com.pms.model.QcSampleRecvInfo;
import com.pms.repository.DistributionRepository;
import com.pms.repository.ResultRepository;

@Service
public class DistributionService {

	@Autowired
	private DistributionRepository distributionRepository;
	@Autowired
	private ResultRepository resultRepository;

	public List<CommonInfo> getAll() {
		return distributionRepository.findAll();
	}

	public List<CommonInfo> getDistributedList() {
		return distributionRepository.findDistributedList();
	}

	public List<CommonInfo> findDistributedChdList(UUID id, String paramType) {
		return resultRepository.findDistributedChdList(id, paramType);
	}

	public CommonInfo getSampleReceiveInfo(UUID id) {
		return distributionRepository.getSampleReceiveInfo(id);
	}

	public void saveSampleRcvInfo(@Valid QcSampleRecvInfo qcSampleRecvInfo) {
		distributionRepository.saveSampleRcvInfo(qcSampleRecvInfo);
	}

	public void saveDistributionInfo(Map<String, String[]> requestMap) {
		DistributionInfo info = new DistributionInfo();

		String mstId = requestMap.get("id")[0];
		String sampleId = requestMap.get("sampleId")[0];
		// String[] id = (String[]) requestMap.get("chemiChildId[]");
		String[] distParameterNo = (String[]) requestMap.get("distParameterNo");
		String[] distParameterId = (String[]) requestMap.get("distParameterId");
		String[] distSpecification = (String[]) requestMap.get("distSpecification");
		String[] distReferenceId = (String[]) requestMap.get("distReferenceId");
		String[] distMethodId = (String[]) requestMap.get("distMethodId");
		String[] distAnalystId = (String[]) requestMap.get("distAnalystId");
		String[] distEcuipmentId = (String[]) requestMap.get("distEcuipmentId");
		String[] distTestUnitId = (String[]) requestMap.get("distTestUnitId");
		String[] distLod = (String[]) requestMap.get("distLod");

		if (requestMap.containsKey("distParameterNo")) {
			for (int i = 0; i < distParameterNo.length; i++) {

				info.setSampleRcvId(UUID.fromString(mstId));
				info.setSampleId(UUID.fromString(sampleId));
				info.setDistParameterNo(distParameterNo[i]);
				info.setDistParameterId(UUID.fromString(distParameterId[i]));
				info.setDistSpecification(distSpecification[i]);

				if (distReferenceId[i] != null && !distReferenceId[i].isEmpty()) {
					info.setDistReferenceId(UUID.fromString(distReferenceId[i]));
				} else {
					info.setDistReferenceId(null);

				}
				if (distMethodId[i] != null && !distMethodId[i].isEmpty()) {
					info.setDistMethodId(UUID.fromString(distMethodId[i]));
				} else {
					info.setDistMethodId(null);

				}
				if (distTestUnitId[i] != null && !distTestUnitId[i].isEmpty()) {
					info.setDistTestUnitId(UUID.fromString(distTestUnitId[i]));
				} else {
					info.setDistTestUnitId(null);

				}
				info.setDistEcuipmentId(distEcuipmentId[i]);

				if (distAnalystId[i] != null && !distAnalystId[i].isEmpty()) {
					info.setDistAnalystId(UUID.fromString(distAnalystId[i]));
				} else {
					info.setDistAnalystId(null);

				}
				info.setDistLod(distLod[i]);
				distributionRepository.saveDistributionInfo(info);
			}
			distributionRepository.updateDistributionInfo(UUID.fromString(mstId));
		}
	}

}
