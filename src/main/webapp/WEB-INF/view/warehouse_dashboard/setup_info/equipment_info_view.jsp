<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>

<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="modal-header bg-cyan">
				    <table class="table table-bordered" style="border: 2px solid #ddd;">
				    	<tbody style="border: 2px solid #ddd;">
				    	<tr>
				    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
				    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
				    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Equipment Information</td>
				    	<td>Document No.</td>
				    	<td colspan="2">FM-DIL-GN-071</td>
				    	</tr>
				    	<tr style="border: 2px solid #ddd;">
				    	<td>Revision No.</td>
				    	<td style="text-align:center; width:35px">00</td>
				    	<td>Page 1 of 1</td>
				    	</tr>
				    	<tr style="border: 2px solid #ddd;">                 	
				    	<td>Effective Date</td>
				    	<td colspan="2">15 Jan 2020</td>
				    	</tr>             	
				    	</tbody>
				    </table>
				</div>

                <form method="post"  >
                 	<div class="modal-body">
                 		
                 		<input type="hidden" id="id" name="id" value=""/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h1 class="panel-title">General Information</h1>
							</div>

						<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<span><b>EQUIPMENT ID :</b></span>
									<div class="form-group">${info.equipmentId}</div>
								</div>
								<div class="col-md-4">
									<span><b>NAME OF EQUIPMENT :</b></span>
									<div class="form-group">${info.equipmentName}</div>
								</div>
								<div class="col-md-4">
									<span><b>EQUIPMENT TYPE :</b></span>
									<div class="form-group">
									<c:choose>
									    <c:when test="${info.equipmentType eq '1'}">Analyzer</c:when>
									    <c:when test="${info.equipmentType eq '2'}">Maintenance</c:when>
									    <c:when test="${info.equipmentType eq '3'}">Sampling</c:when>
									    <c:when test="${info.equipmentType eq '4'}">Storage</c:when>
									</c:choose>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<span><b>CAPACITY (IF HAVE) :</b></span>
									<div class="form-group">${info.capacity}</div>
								</div>
								<div class="col-md-4">
									<span><b>BRAND :</b></span>
									<div class="form-group">${info.brand}</div>
								</div>
								<div class="col-md-4">
									<span><b>MODEL NO :</b></span>
									<div class="form-group">${info.modelNo}</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<span><b> SOURCE/ MANUFACTURER :</b></span>
									<div class="form-group">${info.sourceManufac}</div>
								</div>
								<div class="col-md-4">
									<span><b>LOCAL AGENT NAME :</b></span>
									<div class="form-group">${info.localAgentName}</div>
								</div>
								<div class="col-md-4">
									<span><b>CURRENT LOCATION :</b></span>
									<div class="form-group">${info.currLocation}</div>
								</div>
							</div>
						</div>

						</div>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4 class="panel-title">Description of Equipments</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-3">
											<span><b>SERIAL NO :</b></span>
											<div class="form-group">${info.serialNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>L/C MODEL NO :</b></span>
											<div class="form-group">${info.lcModelNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>MS MODEL NO :</b></span>
											<div class="form-group">${info.msModelNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>SERIAL NO :</b></span>
											<div class="form-group">${info.msSerialNo}</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-3">
											<span><b> MANUFACTURING DATE :</b></span>
											<div class="form-group">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.manufacturingDate}" var="manufacturingDate" />
	                              			<c:out value="${manufacturingDate}"/>
											</div>
										</div>
										<div class="col-md-3">
											<span><b>PUMP MODEL NO. :</b></span>
											<div class="form-group">${info.pumpModelNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>DETECTOR MODEL NO. :</b></span>
											<div class="form-group">${info.detectorModelNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>SOFTWARE NAME :</b></span>
											<div class="form-group">${info.softwareName}</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<span><b> SOFTWARE VERSION NO :</b></span>
											<div class="form-group">${info.softwareVersionNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>SOFTWARE FIRMWARE NO. :</b></span>
											<div class="form-group">${info.softwareFirmwareNo}</div>
										</div>
										<div class="col-md-3">
											<span><b>ELECTRIC POWER :</b></span>
											<div class="form-group">${info.electricPower}</div>
										</div>
										<div class="col-md-3">
											<span><b>NITROGEN CONSUMPTION :</b></span>
											<div class="form-group">${info.nitrogenConsumption}</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4 class="panel-title">Qualification Status</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1" aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<span><b>REFERENCE MATERIAL :</b></span>
											<div class="form-group">${info.referenceMaterial}</div>
										</div>
										<div class="col-md-6">
											<span><b>ADJUSTMENT :</b></span>
											<div class="form-group">${info.adjustment}</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<span><b>ACCEPTANCE CRITERIA :</b></span>
											<div class="form-group">${info.acceptCriteria}</div>
										</div>
										<div class="col-md-6">
											<span><b>EVIDENCE OF VERIFICATION :</b></span>
											<div class="form-group">${info.evidenceVerification}</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<span><b>CALIBRATION DATE :</b></span>
											<div class="form-group">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.calibrationDate}" var="calibrationDate" />
	                              			<c:out value="${calibrationDate}"/>
											</div>
										</div>
										<div class="col-md-3">
											<span><b>NEXT CALIBRATION DATE :</b></span>
											<div class="form-group">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.nextCalibrationDate}" var="nextCalibrationDate" />
	                              			<c:out value="${nextCalibrationDate}"/>
											</div>
										</div>
										<div class="col-md-3">
											<span><b>CALIBRATION INTERVAL :</b></span>
											<div class="form-group">${info.calibrationInterval}</div>
										</div>
										<div class="col-md-3">
											<span><b>RESULT OF CALIBRATION :</b></span>
											<div class="form-group">File
											<c:choose>
											    <c:when test="${not empty (info.resultCalibration)}">
													<a href="${pageContext.request.contextPath}/image/equipment_info_doc/result_calibration/${info.id}-${info.resultCalibration}" download="${info.resultCalibration}" 
													class="btn btn-xs btn-success waves-effect"><i class="fa fa-download"></i></a>
											    </c:when>
											    <c:otherwise>
											        <span class="badge bg-orange">Empty</span>
											    </c:otherwise>
											</c:choose>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<span><b> OPERATIONAL QUALIFICATION <br>DATE :</b></span>
											<div class="form-group">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.operationQualificDate}" var="operationQualificDate" />
	                              			<c:out value="${operationQualificDate}"/>
											</div>
										</div>
										<div class="col-md-3">
											<span><b>PERFORMANCE QUALIFICATION <br>DATE :</b></span>
											<div class="form-group">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.performanceQualificDate}" var="performanceQualificDate" />
	                              			<c:out value="${performanceQualificDate}"/>
											</div>
										</div>
										<div class="col-md-3">
											<span><b>INSTALLATION QUALIFICATION <br>DATE :</b></span>
											<div class="form-group">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.installQualificDate}" var="installQualificDate" />
	                              			<c:out value="${installQualificDate}"/>	
											</div>
										</div>
										<div class="col-md-3">
											<span><b>QUALIFICATION <br>DONE BY :</b></span>
											<div class="form-group">${info.qualiBy}</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
	                 <div class="modal-footer close-footer" style="background-color: #cff0f5;">
						<button type="button" class="btn bg-red btn-sm  pull-right m-r-10"  data-dismiss="modal">
							<span>CLOSE</span>
						</button>
	                 </div>
                 </form>
             

<style>
.mod-cl {
	color: transparent;
	opacity: 1;
}

.alert-code {
	color: white;
}

html {
  overflow-y: scroll;
}

body {
    padding-right:0px !important;
    margin-right:0px !important;
}

body.modal-open {
    overflow: auto;
}

input, textarea {
   	border: 1px solid #c8c7cc;
   	border-radius: 4px !important;
}

.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    vertical-align: middle !important;
}

[type="checkbox"] + label {
    height: 10px;
    font-size: 12px;
}

input {
    height: 28px !important;
}

</style>

