<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">EMPLOYEE PROFILE</h4>
                 </div>
                <form method="post"  >
                 	<div class="modal-body">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">General Information</h1>
							</div>

							<div class="panel-body">
								<div class="col-md-9">
									<div class="col-md-4">
										<span><b>EMPLOYEE ID :</b></span>
										<div class="form-group">${info.udEmpNo}</div>
									</div>
									
									<div class="col-md-4">
										<span><b>EMPLOYEE NAME :</b></span>
										<div class="form-group">${info.empName}</div>
									</div>
									<div class="col-md-4">
										<span><b>DESIGNATION :</b></span>
										<div class="form-group">${info.desigName}</div>
									</div>
								
									<div class="col-md-4">
										<span><b> FATHER'S NAME :</b></span>
										<div class="form-group">${info.fatherName}</div>
									</div>
									<div class="col-md-4">
										<span><b>MOTHER'S NAME :</b></span>
										<div class="form-group">${info.motherName}</div>
									</div>
									<div class="col-md-4">
										<span><b>DATE OF BIRTH :</b></span>
										<div class="form-group">
										<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.dob}" var="dob"/>
	                              			${dob}
										</div>
									</div>
								
									<div class="col-md-4">
										<span><b> GENDER :</b></span>
										<div class="form-group">
										<c:choose>
										    <c:when test="${info.genderId eq 'M'}">Male</c:when>
										    <c:when test="${info.genderId eq 'F'}">Female</c:when>     
									    </c:choose>
										</div>
									</div>
									<div class="col-md-4">
										<span><b>BLOOD GROUP :</b></span>
										<div class="form-group">${info.bloodGroup}</div>
									</div>
									<div class="col-md-4">
										<span><b>MARITAL STATUS :</b></span>
										<div class="form-group">
										<c:choose>
										    <c:when test="${info.maritalStatus eq '1'}">Unmarried</c:when>
										    <c:when test="${info.maritalStatus eq '2'}">Married</c:when>     
									    </c:choose>
	                              		</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
									<label>EMPLOYEE'S PICTURE :</label>
									<img src="${pageContext.request.contextPath}/image/employee_info_doc/${info.id}-${info.employeeImage}" id='view-img-upload' height="120" alt="No image" 
                  							 onerror="this.src='${pageContext.request.contextPath}/image/employee_info_doc/default-image.png';"/>
									
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> ADDRESS :</b></span>
										<div class="form-group">${info.address}</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> NID :</b></span>
										<div class="form-group">${info.nid}</div>
									</div>
									<div class="col-md-4">
										<span><b>MOBILE NO :</b></span>
										<div class="form-group">${info.mobileNo}</div>
									</div>
									<div class="col-md-4">
										<span><b>EMAIL :</b></span>
										<div class="form-group">${info.email}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> QUALIFICATION :</b></span>
										<div class="form-group">${info.qualification}</div>
									</div>
									<div class="col-md-4">
										<span><b>JOINING DATE :</b></span>
										<div class="form-group">
										<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.joiningDate}" var="joiningDate" />
	                              			${joiningDate}
										</div>
									</div>
									<div class="col-md-4">
										<span><b>EXPERIENCE :</b></span>
										<div class="form-group">${info.experience}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> POSITION :</b></span>
										<div class="form-group">${info.positionName}</div>
									</div>
									<div class="col-md-4">
										<span><b>GRADE :</b></span>
										<div class="form-group">${info.gradeName}</div>
									</div>
									<div class="col-md-4">
										<span><b> REGISTRATION NO :</b></span>
										<div class="form-group">${info.regNo}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b>REPORTING TO :</b></span>
										<div class="form-group">${info.reportingName}</div>
									</div>
									<div class="col-md-4">
										<span><b> SUPERVISOR'S SUPERIOR :</b></span>
										<div class="form-group">${info.superiorName}</div>
									</div>
									<div class="col-md-4">
										<span><b>SUPERVISOR :</b></span>
										<div class="form-group">${info.supervisorName}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b>SUBORDINATE :</b></span>
										<div class="form-group">${info.subordinateName}</div>
									</div>
									<div class="col-md-4">
										<span><b> RELATIONSHIP :</b></span>
										<div class="form-group">
										${info.relationship}
											
										</div>
									</div>
									<div class="col-md-4">
										<span><b> ACTIVITY STATUS :</b></span>
										<div class="form-group">
										<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
										</c:choose>
											
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Education Information</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="educationTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 80px;">Degree</th>
													<th class="align-center" style="width: 80px;">Board</th>
													<th class="align-center" style="width: 150px;">Passing Year</th>
													<th class="align-center" style="width: 150px;">Result</th>
													<th class="align-center" style="width: 100px;">Institute</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="info" items="${educationInfos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 80px;">${info.examName}</td>
												<td class="align-center" style="width: 80px;">${info.board}</td>
												<td class="align-center" style="width: 150px;">${info.passingYear}</td>
												<td class="align-center" style="width: 150px;">${info.examResult}</td>
												<td class="align-center" style="width: 100px;">${info.institute}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Qualification Information</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="qualificationTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 40px;">SL NO</th>
													<th class="align-center" style="width: 80px;">Name of Qualification</th>
													<th class="align-center" style="width: 160px;">Qualification Description</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="info" items="${qualifyInfos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 40px;">${counter.count}</td>
												<td class="align-center" style="width: 80px;">${info.qualiName}</td>
												<td class="align-center" style="width: 160px;">${info.qualiDescrip}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Training Information</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="trainingTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 80px;">SL NO</th>
													<th class="align-center" style="width: 80px;">Date</th>
													<th class="align-center" style="width: 150px;">Course</th>
													<th class="align-center" style="width: 150px;">Training Agency</th>
													<th class="align-center" style="width: 100px;">Assessment</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="trainingInfo" items="${infos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 80px;">${counter.count}</td>
												<td class="align-center" style="width: 80px;">
												<fmt:formatDate pattern="dd-MMM-yyyy" value="${trainingInfo.trainDate}" var="trainDate" />
                              					${trainDate}
												</td>
												<td class="align-center" style="width: 150px;">${trainingInfo.courseTitle}</td>
												<td class="align-center" style="width: 150px;">${trainingInfo.courseInst}</td>
												<td class="align-center" style="width: 100px;">${trainingInfo.assesment}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Skills Information</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="skillsTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 40px;">SL NO</th>
													<th class="align-center" style="width: 80px;">Title</th>
													<th class="align-center" style="width: 160px;">Description</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="info" items="${skillsInfos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 40px;">${counter.count}</td>
												<td class="align-center" style="width: 80px;">${info.skillTitle}</td>
												<td class="align-center" style="width: 160px;">${info.skillDescrip}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Technical Knowledge Information</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="technicalTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 40px;">SL NO</th>
													<th class="align-center" style="width: 80px;">Title</th>
													<th class="align-center" style="width: 160px;">Description</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="info" items="${technicalInfos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 40px;">${counter.count}</td>
												<td class="align-center" style="width: 80px;">${info.tecTitle}</td>
												<td class="align-center" style="width: 160px;">${info.tecDescrip}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Experience Information</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="experienceTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 40px;">SL NO</th>
													<th class="align-center" style="width: 80px;">Duration</th>
													<th class="align-center" style="width: 80px;">Designation</th>
													<th class="align-center" style="width: 160px;">Job Description</th>
													<th class="align-center" style="width: 160px;">Name of Company</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="info" items="${experienceInfos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 40px;">${counter.count}</td>
												<td class="align-center" style="width: 80px;">${info.experienceDuration}</td>
												<td class="align-center" style="width: 80px;">${info.experienceDesign}</td>
												<td class="align-center" style="width: 160px;">${info.experienceDescrip}</td>
												<td class="align-center" style="width: 160px;">${info.companyName}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">Attach File</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne_1"
								aria-expanded="true" style="">
								<div class="panel-body">
									<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="attachTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 60px;">SL NO</th>
													<th class="align-center" style="width: 150px;">File Name</th>
													<th class="align-center" style="width: 100px;">Size</th>
													<th class="align-center" style="width: 60px;">Action</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="attachInfo" items="${attachInfos}" varStatus="counter">
											<tr>
												<td class="align-center" style="width: 60px;">${counter.count}</td>
												<td class="align-center" style="width: 150px;">${attachInfo.fileName}</td>
												<td class="align-center" style="width: 100px;">${attachInfo.fileSize}</td>
												<td class="align-center" style="width: 60px;">
													<a href="${pageContext.request.contextPath}/image/employee_info_doc/${attachInfo.empId}-${attachInfo.fileName}" download="${attachInfo.fileName}" 
													class="btn btn-xs btn-success waves-effect"><i class="fa fa-download"></i></a>
												</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
								</div>
							</div>
						</div>

					</div>
	                 <div class="modal-footer close-footer" style="background-color: #ced9dc;">
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