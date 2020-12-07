package com.pms.model;

import java.util.Date;
import java.util.UUID;

public class CommonInfo {
	
private UUID id;
private UUID cssRequestId;
private String udCssNo;
private String companyCode;
private String typeCode;
private UUID whRequestId;
private UUID wsRequestId;
private UUID reqBy;
private String reqByName;
private String reqDate;
private String status;
private UUID reqType;
private String isChemical;
private String isMicrobiological;
private String chemiSamAmt;
private String microSamAmt;
private String totalAmount;
private String totalSamAmt;
private String totalRetAmt;
private String reqTypeName;
private Date reportDate;
private Date effectivDate;
private Date receiveDate;
private Date chemiDistDate;
private Date microDistDate;
private Date distributionAt;
private String receiveAt;
private String testDate;
private String revisionNo;
private String formNo;
private String invoiceNo;
private String chalanNo;
private String lcNo;
private String keptAmount;
private UUID fromDeptNo;
private String fromDeptName;
private UUID toDeptNo;
private String toDeptName;
private String grNo;
private UUID materialId;
private String materialName;
private String materialCode;
private UUID materialTypeId;
private String materialTypeName;
private String rcvQty;
private String manufactureName;
private Date mfgDate;
private Date expDate;
private String mfgResultDate;
private String expResultDate;
private String batchNo;
private String batchSize;
private Date batchDate;
private String lotNo;
private String shelfLife;
private UUID countryId;
private UUID sampleId;
private String countryName;
private String sampleDetails;
private String udSampleNo;
private String clientName;
private String clientId;
private String methodName;
private UUID storageConId;
private String  storageCondition;
private String sampleProcedure;
private String boothName;
private String equipmentName;
private String workInstName;
private String precautionTaken;
private Date samplingDate;
private String samplingByName;
private String note;
private String remarks;
private String opinion;
private String isDecision;
private String sampleRcvDesc;
private String stChemiQty;
private String stMicroQty;
private String stqty;
private String stUnitName;
private String stRoomName;
private String stEquipmentName;
private String stRackName;
private String retqty;
private String retUnitName;
private String retRoomName;
private String retEquipmentName;
private String retRackName;
private String arnNo;
private UUID sampleRcvId;
private String testParameterNo;
private UUID testParameterId;
private String testParameterName;
private String specification;
private UUID referenceId;
private String referenceName;
private UUID testMethodId;
private String testMethodName;
private String equipment_id;
private UUID analystId;
private String analystEmpId;
private String analystName;
private String resultStatus;
private String testResult;
private UUID testResultId;
private String unitName;
private String packSize;
private String isAccept;
private String takenTime;
private String docLocation;
private String whUnitName;
private String matUnitName;
private String maxId;
private UUID testUnitId;
private String testUnitName;
private UUID matStorageConId;
private String matStorageCon;
private String matSamProcedure;
private UUID matMethodId;
private String distributionBy;
private String testReportNo;
private UUID unitId;
private UUID resultId;
private String prmId;
private String prmNm;
private String fd;
private String ld;
private String isWorkingStandard;
private String isAnalystRcv;
private UUID cleanBy;


public UUID getStorageConId() {
	return storageConId;
}
public void setStorageConId(UUID storageConId) {
	this.storageConId = storageConId;
}
private UUID reagentId;
private String reagentName;
private UUID rcvUnitId;
private String rcvUnitName;
private String issueQty;
private UUID issueUnitId;
private String issueUnitName;
private String stockQty;
private Date schFromDate;
private Date schToDate;
private String providerType;


public String getOpinion() {
	return opinion;
}
public void setOpinion(String opinion) {
	this.opinion = opinion;
}
public UUID getTestResultId() {
	return testResultId;
}
public void setTestResultId(UUID testResultId) {
	this.testResultId = testResultId;
}
public UUID getId() {
	return id;
}
public void setId(UUID id) {
	this.id = id;
}
public UUID getWhRequestId() {
	return whRequestId;
}
public void setWhRequestId(UUID whRequestId) {
	this.whRequestId = whRequestId;
}
public UUID getReqBy() {
	return reqBy;
}
public void setReqBy(UUID reqBy) {
	this.reqBy = reqBy;
}
public String getReqByName() {
	return reqByName;
}
public void setReqByName(String reqByName) {
	this.reqByName = reqByName;
}
public String getReqDate() {
	return reqDate;
}
public void setReqDate(String reqDate) {
	this.reqDate = reqDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public UUID getReqType() {
	return reqType;
}
public void setReqType(UUID reqType) {
	this.reqType = reqType;
}
public String getReqTypeName() {
	return reqTypeName;
}
public void setReqTypeName(String reqTypeName) {
	this.reqTypeName = reqTypeName;
}
public Date getEffectivDate() {
	return effectivDate;
}
public void setEffectivDate(Date effectivDate) {
	this.effectivDate = effectivDate;
}
public String getRevisionNo() {
	return revisionNo;
}
public void setRevisionNo(String revisionNo) {
	this.revisionNo = revisionNo;
}
public String getFormNo() {
	return formNo;
}
public void setFormNo(String formNo) {
	this.formNo = formNo;
}
public String getInvoiceNo() {
	return invoiceNo;
}
public void setInvoiceNo(String invoiceNo) {
	this.invoiceNo = invoiceNo;
}
public String getChalanNo() {
	return chalanNo;
}
public void setChalanNo(String chalanNo) {
	this.chalanNo = chalanNo;
}
public String getLcNo() {
	return lcNo;
}
public void setLcNo(String lcNo) {
	this.lcNo = lcNo;
}
public UUID getFromDeptNo() {
	return fromDeptNo;
}
public void setFromDeptNo(UUID fromDeptNo) {
	this.fromDeptNo = fromDeptNo;
}
public String getFromDeptName() {
	return fromDeptName;
}
public void setFromDeptName(String fromDeptName) {
	this.fromDeptName = fromDeptName;
}
public UUID getToDeptNo() {
	return toDeptNo;
}
public void setToDeptNo(UUID toDeptNo) {
	this.toDeptNo = toDeptNo;
}
public String getToDeptName() {
	return toDeptName;
}
public void setToDeptName(String toDeptName) {
	this.toDeptName = toDeptName;
}
public String getGrNo() {
	return grNo;
}
public void setGrNo(String grNo) {
	this.grNo = grNo;
}

public String getMaterialName() {
	return materialName;
}
public void setMaterialName(String materialName) {
	this.materialName = materialName;
}
public String getMaterialCode() {
	return materialCode;
}
public void setMaterialCode(String materialCode) {
	this.materialCode = materialCode;
}
public UUID getMaterialTypeId() {
	return materialTypeId;
}
public void setMaterialTypeId(UUID materialTypeId) {
	this.materialTypeId = materialTypeId;
}
public String getMaterialTypeName() {
	return materialTypeName;
}
public void setMaterialTypeName(String materialTypeName) {
	this.materialTypeName = materialTypeName;
}
public String getRcvQty() {
	return rcvQty;
}
public void setRcvQty(String rcvQty) {
	this.rcvQty = rcvQty;
}
public String getManufactureName() {
	return manufactureName;
}
public void setManufactureName(String manufactureName) {
	this.manufactureName = manufactureName;
}
public UUID getCountryId() {
	return countryId;
}
public void setCountryId(UUID countryId) {
	this.countryId = countryId;
}
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
public String getSampleDetails() {
	return sampleDetails;
}
public void setSampleDetails(String sampleDetails) {
	this.sampleDetails = sampleDetails;
}
public String getIsChemical() {
	return isChemical;
}
public void setIsChemical(String isChemical) {
	this.isChemical = isChemical;
}
public String getIsMicrobiological() {
	return isMicrobiological;
}
public void setIsMicrobiological(String isMicrobiological) {
	this.isMicrobiological = isMicrobiological;
}
public String getChemiSamAmt() {
	return chemiSamAmt;
}
public void setChemiSamAmt(String chemiSamAmt) {
	this.chemiSamAmt = chemiSamAmt;
}
public String getMicroSamAmt() {
	return microSamAmt;
}
public void setMicroSamAmt(String microSamAmt) {
	this.microSamAmt = microSamAmt;
}
public String getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(String totalAmount) {
	this.totalAmount = totalAmount;
}
public String getTotalSamAmt() {
	return totalSamAmt;
}
public void setTotalSamAmt(String totalSamAmt) {
	this.totalSamAmt = totalSamAmt;
}
public String getTotalRetAmt() {
	return totalRetAmt;
}
public void setTotalRetAmt(String totalRetAmt) {
	this.totalRetAmt = totalRetAmt;
}
public String getUdCssNo() {
	return udCssNo;
}
public void setUdCssNo(String udCssNo) {
	this.udCssNo = udCssNo;
}
public String getUdSampleNo() {
	return udSampleNo;
}
public void setUdSampleNo(String udSampleNo) {
	this.udSampleNo = udSampleNo;
}
public String getClientName() {
	return clientName;
}
public void setClientName(String clientName) {
	this.clientName = clientName;
}
public String getClientId() {
	return clientId;
}
public void setClientId(String clientId) {
	this.clientId = clientId;
}
public String getMethodName() {
	return methodName;
}
public void setMethodName(String methodName) {
	this.methodName = methodName;
}
public String getStorageCondition() {
	return storageCondition;
}
public void setStorageCondition(String storageCondition) {
	this.storageCondition = storageCondition;
}
public String getSampleProcedure() {
	return sampleProcedure;
}
public void setSampleProcedure(String sampleProcedure) {
	this.sampleProcedure = sampleProcedure;
}
public String getBoothName() {
	return boothName;
}
public void setBoothName(String boothName) {
	this.boothName = boothName;
}
public String getEquipmentName() {
	return equipmentName;
}
public void setEquipmentName(String equipmentName) {
	this.equipmentName = equipmentName;
}
public String getWorkInstName() {
	return workInstName;
}
public void setWorkInstName(String workInstName) {
	this.workInstName = workInstName;
}
public String getPrecautionTaken() {
	return precautionTaken;
}
public void setPrecautionTaken(String precautionTaken) {
	this.precautionTaken = precautionTaken;
}
public Date getSamplingDate() {
	return samplingDate;
}
public void setSamplingDate(Date samplingDate) {
	this.samplingDate = samplingDate;
}
public String getSamplingByName() {
	return samplingByName;
}
public void setSamplingByName(String samplingByName) {
	this.samplingByName = samplingByName;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public Date getReceiveDate() {
	return receiveDate;
}
public void setReceiveDate(Date receiveDate) {
	this.receiveDate = receiveDate;
}
public Date getChemiDistDate() {
	return chemiDistDate;
}
public void setChemiDistDate(Date chemiDistDate) {
	this.chemiDistDate = chemiDistDate;
}
public Date getMicroDistDate() {
	return microDistDate;
}
public void setMicroDistDate(Date microDistDate) {
	this.microDistDate = microDistDate;
}
public String getSampleRcvDesc() {
	return sampleRcvDesc;
}
public void setSampleRcvDesc(String sampleRcvDesc) {
	this.sampleRcvDesc = sampleRcvDesc;
}
public String getStChemiQty() {
	return stChemiQty;
}
public void setStChemiQty(String stChemiQty) {
	this.stChemiQty = stChemiQty;
}
public String getStMicroQty() {
	return stMicroQty;
}
public void setStMicroQty(String stMicroQty) {
	this.stMicroQty = stMicroQty;
}
public String getStqty() {
	return stqty;
}
public void setStqty(String stqty) {
	this.stqty = stqty;
}
public String getStUnitName() {
	return stUnitName;
}
public void setStUnitName(String stUnitName) {
	this.stUnitName = stUnitName;
}
public String getStRoomName() {
	return stRoomName;
}
public void setStRoomName(String stRoomName) {
	this.stRoomName = stRoomName;
}
public String getStEquipmentName() {
	return stEquipmentName;
}
public void setStEquipmentName(String stEquipmentName) {
	this.stEquipmentName = stEquipmentName;
}
public String getStRackName() {
	return stRackName;
}
public void setStRackName(String stRackName) {
	this.stRackName = stRackName;
}
public String getRetqty() {
	return retqty;
}
public void setRetqty(String retqty) {
	this.retqty = retqty;
}
public String getRetUnitName() {
	return retUnitName;
}
public void setRetUnitName(String retUnitName) {
	this.retUnitName = retUnitName;
}
public String getRetRoomName() {
	return retRoomName;
}
public void setRetRoomName(String retRoomName) {
	this.retRoomName = retRoomName;
}
public String getRetEquipmentName() {
	return retEquipmentName;
}
public void setRetEquipmentName(String retEquipmentName) {
	this.retEquipmentName = retEquipmentName;
}
public String getRetRackName() {
	return retRackName;
}
public void setRetRackName(String retRackName) {
	this.retRackName = retRackName;
}
public String getArnNo() {
	return arnNo;
}
public void setArnNo(String arnNo) {
	this.arnNo = arnNo;
}
public String getTestParameterNo() {
	return testParameterNo;
}
public void setTestParameterNo(String testPparameterNo) {
	this.testParameterNo = testPparameterNo;
}

public String getTestParameterName() {
	return testParameterName;
}
public void setTestParameterName(String testParameterName) {
	this.testParameterName = testParameterName;
}
public String getSpecification() {
	return specification;
}
public void setSpecification(String specification) {
	this.specification = specification;
}

public String getReferenceName() {
	return referenceName;
}
public void setReferenceName(String referenceName) {
	this.referenceName = referenceName;
}

public String getTestMethodName() {
	return testMethodName;
}
public void setTestMethodName(String testMethodName) {
	this.testMethodName = testMethodName;
}
public String getEquipment_id() {
	return equipment_id;
}
public void setEquipment_id(String equipment_id) {
	this.equipment_id = equipment_id;
}

public String getAnalystName() {
	return analystName;
}
public void setAnalystName(String analystName) {
	this.analystName = analystName;
}
public String getResultStatus() {
	return resultStatus;
}
public void setResultStatus(String resultStatus) {
	this.resultStatus = resultStatus;
}
public UUID getSampleRcvId() {
	return sampleRcvId;
}
public void setSampleRcvId(UUID sampleRcvId) {
	this.sampleRcvId = sampleRcvId;
}
public UUID getTestParameterId() {
	return testParameterId;
}
public void setTestParameterId(UUID testParameterId) {
	this.testParameterId = testParameterId;
}
public UUID getReferenceId() {
	return referenceId;
}
public void setReferenceId(UUID referenceId) {
	this.referenceId = referenceId;
}
public UUID getTestMethodId() {
	return testMethodId;
}
public void setTestMethodId(UUID testMethodId) {
	this.testMethodId = testMethodId;
}
public UUID getAnalystId() {
	return analystId;
}
public void setAnalystId(UUID analystId) {
	this.analystId = analystId;
}
public String getAnalystEmpId() {
	return analystEmpId;
}
public void setAnalystEmpId(String analystEmpId) {
	this.analystEmpId = analystEmpId;
}
public String getTestResult() {
	return testResult;
}
public void setTestResult(String testResult) {
	this.testResult = testResult;
}
public String getUnitName() {
	return unitName;
}
public void setUnitName(String unitName) {
	this.unitName = unitName;
}
public String getPackSize() {
	return packSize;
}
public void setPackSize(String packSize) {
	this.packSize = packSize;
}
public String getIsAccept() {
	return isAccept;
}
public void setIsAccept(String isAccept) {
	this.isAccept = isAccept;
}

public String getReceiveAt() {
	return receiveAt;
}
public void setReceiveAt(String receiveAt) {
	this.receiveAt = receiveAt;
}
public String getTestDate() {
	return testDate;
}
public void setTestDate(String testDate) {
	this.testDate = testDate;
}
public String getTakenTime() {
	return takenTime;
}
public void setTakenTime(String takenTime) {
	this.takenTime = takenTime;
}
public UUID getSampleId() {
	return sampleId;
}
public void setSampleId(UUID sampleId) {
	this.sampleId = sampleId;
}
	public String getIsDecision() {
	return isDecision;
}
public void setIsDecision(String isDecision) {
	this.isDecision = isDecision;

}


public String getBatchNo() {
	return batchNo;
}
public void setBatchNo(String batchNo) {
	this.batchNo = batchNo;
}
public Date getMfgDate() {
	return mfgDate;
}
public void setMfgDate(Date mfgDate) {
	this.mfgDate = mfgDate;
}
public Date getExpDate() {
	return expDate;
}
public void setExpDate(Date expDate) {
	this.expDate = expDate;
}
public String getMfgResultDate() {
	return mfgResultDate;
}
public void setMfgResultDate(String mfgResultDate) {
	this.mfgResultDate = mfgResultDate;
}
public String getExpResultDate() {
	return expResultDate;
}
public void setExpResultDate(String expResultDate) {
	this.expResultDate = expResultDate;
}
public String getDocLocation() {
	return docLocation;
}
public void setDocLocation(String docLocation) {
	this.docLocation = docLocation;
}
public String getWhUnitName() {
	return whUnitName;
}
public void setWhUnitName(String whUnitName) {
	this.whUnitName = whUnitName;
}
public String getMatUnitName() {
	return matUnitName;
}
public void setMatUnitName(String matUnitName) {
	this.matUnitName = matUnitName;
}
public String getMaxId() {
	return maxId;
}
public void setMaxId(String maxId) {
	this.maxId = maxId;
}
public UUID getTestUnitId() {
	return testUnitId;
}
public void setTestUnitId(UUID testUnitId) {
	this.testUnitId = testUnitId;
}
public String getTestUnitName() {
	return testUnitName;
}
public void setTestUnitName(String testUnitName) {
	this.testUnitName = testUnitName;
}
public UUID getMatStorageConId() {
	return matStorageConId;
}
public void setMatStorageConId(UUID matStorageConId) {
	this.matStorageConId = matStorageConId;
}
public String getMatStorageCon() {
	return matStorageCon;
}
public void setMatStorageCon(String matStorageCon) {
	this.matStorageCon = matStorageCon;
}
public String getMatSamProcedure() {
	return matSamProcedure;
}
public void setMatSamProcedure(String matSamProcedure) {
	this.matSamProcedure = matSamProcedure;
}
public UUID getMatMethodId() {
	return matMethodId;
}
public void setMatMethodId(UUID matMethodId) {
	this.matMethodId = matMethodId;
}
public String getDistributionBy() {
	return distributionBy;
}
public void setDistributionBy(String distributionBy) {
	this.distributionBy = distributionBy;
}
public Date getDistributionAt() {
	return distributionAt;
}
public void setDistributionAt(Date distributionAt) {
	this.distributionAt = distributionAt;
}
public String getCompanyCode() {
	return companyCode;
}
public void setCompanyCode(String companyCode) {
	this.companyCode = companyCode;
}
public String getTestReportNo() {
	return testReportNo;
}
public void setTestReportNo(String testReportNo) {
	this.testReportNo = testReportNo;
}
public String getBatchSize() {
	return batchSize;
}
public void setBatchSize(String batchSize) {
	this.batchSize = batchSize;
}
public Date getBatchDate() {
	return batchDate;
}
public void setBatchDate(Date batchDate) {
	this.batchDate = batchDate;
}
public String getLotNo() {
	return lotNo;
}
public void setLotNo(String lotNo) {
	this.lotNo = lotNo;
}
public String getShelfLife() {
	return shelfLife;
}
public void setShelfLife(String shelfLife) {
	this.shelfLife = shelfLife;
}
public UUID getUnitId() {
	return unitId;
}
public void setUnitId(UUID unitId) {
	this.unitId = unitId;
}
public String getTypeCode() {
	return typeCode;
}
public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
}
public UUID getReagentId() {
	return reagentId;
}
public void setReagentId(UUID reagentId) {
	this.reagentId = reagentId;
}
public String getReagentName() {
	return reagentName;
}
public void setReagentName(String reagentName) {
	this.reagentName = reagentName;
}
public UUID getRcvUnitId() {
	return rcvUnitId;
}
public void setRcvUnitId(UUID rcvUnitId) {
	this.rcvUnitId = rcvUnitId;
}
public String getRcvUnitName() {
	return rcvUnitName;
}
public void setRcvUnitName(String rcvUnitName) {
	this.rcvUnitName = rcvUnitName;
}
public String getIssueQty() {
	return issueQty;
}
public void setIssueQty(String issueQty) {
	this.issueQty = issueQty;
}
public UUID getIssueUnitId() {
	return issueUnitId;
}
public void setIssueUnitId(UUID issueUnitId) {
	this.issueUnitId = issueUnitId;
}
public String getIssueUnitName() {
	return issueUnitName;
}
public void setIssueUnitName(String issueUnitName) {
	this.issueUnitName = issueUnitName;
}
public String getStockQty() {
	return stockQty;
}
public void setStockQty(String stockQty) {
	this.stockQty = stockQty;
}
public Date getSchFromDate() {
	return schFromDate;
}
public void setSchFromDate(Date schFromDate) {
	this.schFromDate = schFromDate;
}
public Date getSchToDate() {
	return schToDate;
}
public void setSchToDate(Date schToDate) {
	this.schToDate = schToDate;
}
public String getProviderType() {
	return providerType;
}
public void setProviderType(String providerType) {
	this.providerType = providerType;
}
public UUID getCssRequestId() {
	return cssRequestId;
}
public void setCssRequestId(UUID cssRequestId) {
	this.cssRequestId = cssRequestId;
}
public Date getReportDate() {
	return reportDate;
}
public void setReportDate(Date reportDate) {
	this.reportDate = reportDate;
}
public UUID getResultId() {
	return resultId;
}
public void setResultId(UUID resultId) {
	this.resultId = resultId;
}
public String getPrmId() {
	return prmId;
}
public void setPrmId(String prmId) {
	this.prmId = prmId;
}
public String getPrmNm() {
	return prmNm;
}
public void setPrmNm(String prmNm) {
	this.prmNm = prmNm;
}
public String getFd() {
	return fd;
}
public void setFd(String fd) {
	this.fd = fd;
}
public String getLd() {
	return ld;
}
public void setLd(String ld) {
	this.ld = ld;
}
public String getIsWorkingStandard() {
	return isWorkingStandard;
}
public void setIsWorkingStandard(String isWorkingStandard) {
	this.isWorkingStandard = isWorkingStandard;
}
public UUID getWsRequestId() {
	return wsRequestId;
}
public void setWsRequestId(UUID wsRequestId) {
	this.wsRequestId = wsRequestId;
}
public UUID getMaterialId() {
	return materialId;
}
public void setMaterialId(UUID materialId) {
	this.materialId = materialId;
}
public String getIsAnalystRcv() {
	return isAnalystRcv;
}
public void setIsAnalystRcv(String isAnalystRcv) {
	this.isAnalystRcv = isAnalystRcv;
}
public String getKeptAmount() {
	return keptAmount;
}
public void setKeptAmount(String keptAmount) {
	this.keptAmount = keptAmount;
}
public UUID getCleanBy() {
	return cleanBy;
}
public void setCleanBy(UUID cleanBy) {
	this.cleanBy = cleanBy;
}



}
