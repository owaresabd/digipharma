<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<style type="text/css">

        
textarea {
  resize: none;
}
</style>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">EMPLOYEE INFORMATION</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/employee/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/employee/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="driverList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL #</th>
								<th class="align-left" style="width: 100px;">EMPLOYEE ID</th>
								<th class="align-left">EMPLOYEE NAME</th>
								<th class="align-left" style="width: 150px;">DESIGNATION</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${employeeInfos}" varStatus="counter">
							<tr>
								<td class="field_driver_id align-center">${counter.count}</td>
								<td class="field_driver_id">${info.udEmpNo}</td>
								<td class="field_driver_name_en">${info.empName}</td>
								<td class="field_contact_no">${info.desigName}</td>
								<td class="field_type_status align-center" width="100px">
									<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
								
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="e_udEmpNo" value="${info.udEmpNo}">
									<input type="hidden" id="e_empName" value="${info.empName}">
									<input type="hidden" id="e_desigId" value="${info.desigId}">
									<input type="hidden" id="e_fatherName" value="${info.fatherName}">
									<input type="hidden" id="e_motherName" value="${info.motherName}">
									<input type="hidden" id="e_dob" value="${info.dob}">
									<input type="hidden" id="e_gender" value="${info.genderId}">
									<input type="hidden" id="e_bloodGroup" value="${info.bloodGroup}">
									<input type="hidden" id="e_maritalStatus" value="${info.maritalStatus}">
									<input type="hidden" id="e_address" value="${info.address}">
									<input type="hidden" id="e_nid" value="${info.nid}">
									<input type="hidden" id="e_mobileNo" value="${info.mobileNo}">
									<input type="hidden" id="e_email" value="${info.email}">
									<input type="hidden" id="e_qualification" value="${info.qualification}">
									<input type="hidden" id="e_joiningDate" value="${info.joiningDate}">
									<input type="hidden" id="e_experience" value="${info.experience}">
									<input type="hidden" id="e_regNo" value="${info.regNo}">
									<input type="hidden" id="e_position_id" value="${info.positionId}">
									<input type="hidden" id="e_gradeId" value="${info.gradeId}">
									<input type="hidden" id="e_reportingId" value="${info.reportingId}">
									<input type="hidden" id="e_relationship" value="${info.relationship}">
									<input type="hidden" id="e_superiorId" value="${info.superiorId}">
									<input type="hidden" id="e_supervisorId" value="${info.supervisorId}">
									<input type="hidden" id="e_subordinateId" value="${info.subordinateId}">
									<input type="hidden" id="e_employeeImage" value="${info.employeeImage}">
									<input type="hidden" id="e_employeeSign" value="${info.empSignature}">
									<input type="hidden" id="e_status" value="${info.status}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>||
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	 <div class="modal fade" id="employeeInfoModal"  role="dialog" style="overflow: auto !important;" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">EMPLOYEE INFORMATION</h4>
                 </div>
                <form method="post" id="employeeInfoForm" enctype="multipart/form-data" onkeypress="if (event.keyCode == 13) { return false; }">
              	<div class="modal-body div-content m-t--15">
                	<div class="alert-code alert-block alert-danger hidden"></div><br>
                 	<!-- Nav tabs -->
                    <ul class="nav nav-tabs tab-col-green tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#general_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment_ind</i> <span>GENERAL INFO</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#education_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment</i> <span>EDUCATION INFO</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#qualification_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment</i> <span>QUALIFICATION INFO</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#training_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">memory</i> <span>TRAINING INFO</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#skillTechnical_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment</i> <span>SKILLS &amp; TECHNICAL KNOWLEDGE INFO</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#experience_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment</i> <span>EXPERIENCE INFO</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#medical_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment</i> <span>MEDICAL RECORD</span>
                            </a>
                        </li>
                        <li role="presentation">
                            <a href="#document_info_with_icon_title" data-toggle="tab">
                                <i class="material-icons">assignment</i> <span>DOCUMENT INFO</span>
                            </a>
                        </li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
	                 	<div role="tabpanel" class="tab-pane active" id="general_info_with_icon_title">
	                 		<input type="hidden" id="id" name="id" value=""/>
							<div class="row">
								<div class="col-md-4">
									<span><b>Employee ID : </b><span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="udEmpNo" name="udEmpNo" value="" class="form-control upper" autocomplete="off" required="required">
									</div>
								</div>
										
								<div class="col-md-4">
									<span><b>Employee Name :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="empName" name="empName" value="" class="form-control upper"   autocomplete="off" required="required">
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Designation :</b> <span style="color:red">*</span></span>
	
									<div class="form-group">
										<select  id=desigId name="desigId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="desigInfo" items="${desigInfos}">
			                           	<option value="${desigInfo.id }">${desigInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
	
									</div>
								</div>
							</div>
	
							<div class="row">
								<div class="col-md-4">
									<span><b> Father's Name :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="fatherName" name="fatherName" value="" class="form-control" autocomplete="off" required="required">
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Mother's Name :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="motherName" name="motherName" value="" class="form-control" autocomplete="off" required="required">
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Date of Birth :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="dob" name="dob" value="" class="form-control number" required="required">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<span><b> Gender :</b> <span style="color:red">*</span></span>
									<div class="form-group">
									<select id="genderId" name="genderId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
		                        	<option></option>
		                        	<option value="M">Male</option>
		                        	<option value="F">Female</option>
		                        	
		                        	</select>
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Blood Group :</b></span>
									<div class="form-group">
									<select id="bloodGroup" name="bloodGroup" class="js-example-theme-single form-control" style="width: 100%;">
			                        	<option></option>
			                        	<option value="A+">A+</option>
			                        	<option value="A-">A-</option>
			                        	<option value="B+">B+</option>
			                        	<option value="B-">B-</option>
			                        	<option value="AB+">AB+</option>
			                        	<option value="AB-">AB-</option>
			                        	<option value="O+">O+</option>
			                        	<option value="O-">O-</option>
		                        	</select>
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Marital Status :</b> <span style="color:red">*</span></span>
									<div class="form-group">
									<select id="maritalStatus" name="maritalStatus" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        	<option value="1">Unmarried</option>
			                        	<option value="2">Married</option>
		                        	</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<span><b> Address :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<textarea style="resize:none" rows="3" id="address" name="address" class="form-control" placeholder="Description goes here......." required="required"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
							<div class="col-md-4">
									<span><b>NID :</b></span>
									<div class="form-group">
										<input type="text" id="nid" name="nid" value="" class="form-control number">
									</div>
								</div>
								<div class="col-md-4">
									<span><b> Mobile No.:</b></span>
									<div class="form-group">
										<input type="text" id="mobileNo" name="mobileNo" value="" class="form-control" autocomplete="off" >
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Email :</b></span>
									<div class="form-group">
										<input type="text" id="email" name="email" value="" class="form-control" autocomplete="off" >
									</div>
								</div>
								
							</div>
							<div class="row">
							<div class="col-md-4">
									<span><b>Qualification :</b></span>
									<div class="form-group">
										<input type="text" id="qualification" name="qualification" value="" class="form-control" autocomplete="off">
									</div>
								</div>
							<div class="col-md-4">
									<span><b>Joining Date :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="joiningDate" name="joiningDate" value="" class="form-control number" required="required">
									</div>
								</div>
								<div class="col-md-4">
									<span><b> Experience :</b></span>
									<div class="form-group">
										<input type="text" id="experience" name="experience" value="" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="row">
							<div class="col-md-4">
									<span><b>Position :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<select  id=positionId name="positionId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="positionInfo" items="${desigInfos}">
			                           	<option value="${positionInfo.id }">${positionInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
									</div>
								</div>
								<div class="col-md-4">
									<span><b> Grade :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<select  id=gradeId name="gradeId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="gradeInfo" items="${desigInfos}">
			                           	<option value="${gradeInfo.id }">${gradeInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Reporting To :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<select  id=reportingId name="reportingId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="reportInfo" items="${desigInfos}">
			                           	<option value="${reportInfo.id }">${reportInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
									</div>
								</div>
							</div>
							<div class="row">
							<div class="col-md-4">
									<span><b>Registration No :</b></span>
									<div class="form-group">
										<input type="text" id="regNo" name="regNo" value="" class="form-control number">
									</div>
								</div>
								
								
							</div>
							<div class="row">
								<div class="col-md-12">
									<span><b> Relationship :</b></span>
									<div class="form-group">
										<textarea rows="3" id="relationship" name="relationship" class="form-control" placeholder="Description goes here......." ></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<span><b>Supervisor's Superior :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<select  id=superiorId name="superiorId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="superiorInfo" items="${desigInfos}">
			                           	<option value="${superiorInfo.id }">${superiorInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
									</div>
								</div>
								<div class="col-md-4">
									<span><b> Supervisor :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<select  id=supervisorId name="supervisorId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="superInfo" items="${desigInfos}">
			                           	<option value="${superInfo.id }">${superInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
									</div>
								</div>
								<div class="col-md-4">
									<span><b>Subordinate :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<select  id=subordinateId name="subordinateId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="subInfo" items="${desigInfos}">
			                           	<option value="${subInfo.id }">${subInfo.desigName}</option>
			                        </c:forEach>
			                        </select>
									</div>
								</div>
							</div>
	
							<div class="row">
		                 		<div class="col-md-4 align-right">
	                            	<label for="status">Activity Status :</label>
	                            </div>
	                            <div class="col-md-8">
	                            	<div class="demo-checkbox">
										<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
										<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
										<input type="hidden" id="status" name="status" value="N">
									</div>
	                            </div>
	                 		</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="education_info_with_icon_title">
							<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">EDUCATION DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="educationTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 80px;">Degree</th>
													<th class="align-center" style="width: 150px;">Board</th>
													<th class="align-center" style="width: 80px;">Passing Year</th>
													<th class="align-center" style="width: 80px;">Result</th>
													<th class="align-center" style="width: 150px;">Institute</th>
													<th class="align-center" style="width: 60px;">
														<a class="btn-edit btn btn-xs" title="Add Row" onclick="eduAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
													</th>
												</tr>
											</thead>
											<tbody id="edu_tbl_body">
											
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
							</div>
						</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="qualification_info_with_icon_title">
							<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">QUALIFICATION DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="qualificationTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 60px;">Name of Qualification</th>
													<th class="align-center" style="width: 150px;">Qualification Description</th>
													<th class="align-center" style="width: 30px;">
														<a class="btn-edit btn btn-xs" title="Add Row" onclick="quaAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
													</th>
												</tr>
											</thead>
											<tbody id="qua_tbl_body">
											
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
							</div>
						</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="skillTechnical_info_with_icon_title">
							<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">SKILLS DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="skillTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 60px;">Title</th>
													<th class="align-center" style="width: 150px;">Description</th>
													<th class="align-center" style="width: 30px;">
														<a class="btn-edit btn btn-xs" title="Add Row" onclick="skillAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
													</th>
												</tr>
											</thead>
											<tbody id="skill_tbl_body">
											
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">TECHNICAL KNOWLEDGE DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="technicalTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 60px;">Title</th>
													<th class="align-center" style="width: 150px;">Description</th>
													<th class="align-center" style="width: 30px;">
														<a class="btn-edit btn btn-xs" title="Add Row" onclick="tecAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
													</th>
												</tr>
											</thead>
											<tbody id="tec_tbl_body">
											
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
							</div>
						</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="experience_info_with_icon_title">
							<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">EXPERIENCE DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="experienceTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 80px;">Duration</th>
													<th class="align-center" style="width: 150px;">Designation</th>
													<th class="align-center" style="width: 150px;">Job Description</th>
													<th class="align-center" style="width: 100px;">Name of Company</th>
													<th class="align-center" style="width: 60px;">
														<a class="btn-edit btn btn-xs" title="Add Row" onclick="expAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
													</th>
												</tr>
											</thead>
											<tbody id="exp_tbl_body">
											
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
							</div>
						</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="medical_info_with_icon_title">
							<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">MEDICAL DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
		                		</div>
							</div>
						</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="training_info_with_icon_title">
							<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">TRAINING DETAILS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
		                        	<div class="col-md-12">
		                           	<div class="form-group">
		                            	<table id="trainingTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 80px;">Date</th>
													<th class="align-center" style="width: 150px;">Course</th>
													<th class="align-center" style="width: 150px;">Training Agency</th>
													<th class="align-center" style="width: 100px;">Assessment</th>
													<th class="align-center" style="width: 60px;">
														<a class="btn-edit btn btn-xs" title="Add Row" onclick="addRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
													</th>
												</tr>
											</thead>
											<tbody id="tbl_posts_body">
											
											</tbody>
										</table>
									</div>
		                           	</div>
		                		</div>
							</div>
						</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="document_info_with_icon_title">
							<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label>Employee's Picture</label>
									<img src="${pageContext.request.contextPath}/image/employee_info_doc/default-image.png" id='img-upload' height="120" alt="No image" 
                  							 onerror="this.src='${pageContext.request.contextPath}/image/employee_info_doc/default-image.png';"/>
									<div class="input-group m-t-5">
										<span class="input-group-btn">
											<span class="btn btn-default btn-file">
												Browse… <input type="file" accept="image/*" id="img_employee" name="img_employee" value="" onchange="readURL(this);">
											</span>
										</span>
										<input type="text" id="employee_image" name="employeeImage" class="form-control" readonly="readonly">
										<input type="hidden" id="e_image" name="e_image">
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Employee's Signature</label>
									<img src="${pageContext.request.contextPath}/image/employee_info_doc/default-image.png" id='img-upload1' height="120" alt="No image" 
                  							 onerror="this.src='${pageContext.request.contextPath}/image/employee_info_doc/default-image.png';"/>
									<div class="input-group m-t-5">
										<span class="input-group-btn">
											<span class="btn btn-default btn-file">
												Browse… <input type="file" accept="image/*" id="img_signature" name="img_signature" value="" onchange="readURL1(this);">
											</span>
										</span>
										<input type="text" id="signature_image" name="empSignature" class="form-control" readonly="readonly">
										<input type="hidden" id="sig_image" name="sig_image">
									</div>
								</div>
							</div>
							
							</div>
		                    <div class="row">
							<div class="col-md-12">
								<span class="btn multi bg-green waves-effect">
									ATTACH FILE… <input type="file" id="multi_file" name="multiFiles[]" multiple="multiple" onchange="getFileSizeandName(this);" />
								</span>
								<table class="table table-bordered table-striped table-hover m-t-5" id="attachList">
									<thead>
										<tr>
											<th class="align-center">FILE NAME</th>
											<th class="align-center" style="width: 120px;">SIZE</th>
											<th class="align-center" style="width: 100px;">ACTION</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>
						
						</div>
					</div>
				</div>	
	                 <div class="modal-footer" style="background-color: #ced9dc;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span><spring:message code="btn.save"/></span>
						</button>
	                 </div>
                 </form>
             </div>
         </div>
     </div>
     <div class="modal fade" id="employeeViewInfoModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true" >
         <div id="modalId" class="modal-dialog modal-lg" role="document" aria-hidden="true">
             <div class="modal-content">
                 
             </div>
         </div>
     </div>
</div>
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

.div-content {
	min-height: 470px !important;
}

.btn-file, .multi {
    position: relative;
    overflow: hidden;
}
.btn-file input[type=file], .multi input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
}

#img-upload, #img-upload1, #img-upload2, #img-upload3 {
    width: 100%;
}

.btn:not(.btn-link):not(.btn-circle) i {
    top: 2px;
}

.dates {
    background-color: mintcream;
   	border: 1px solid #c8c7cc;
   	border-radius: 5px !important;
}
</style>
		
 <script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>
<script>
function chngLang(el){
	var link = $(el).attr('id');
	$.ajax({
		async : false,
		url : link ,
		success : function(result) {
			$("#view_page").html(result);
		},
		error: function(){
			console.log("this employee has no user id.");
	  	}
	});
}

$(".modal-header").on("mousedown", function(mousedownEvt) {
    var $draggable = $(this);
    var x = mousedownEvt.pageX - $draggable.offset().left,
        y = mousedownEvt.pageY - $draggable.offset().top;
    $("body").on("mousemove.draggable", function(mousemoveEvt) {
        $draggable.closest(".modal-dialog").offset({
            "left": mousemoveEvt.pageX - x,
            "top": mousemoveEvt.pageY - y
        });
    });
    $("body").one("mouseup", function() {
        $("body").off("mousemove.draggable");
    });
    $draggable.closest(".modal").one("bs.modal.hide", function() {
        $("body").off("mousemove.draggable");
    });
});

 $(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search from list.."
});
 
 
 $(document).on('change', '.btn-file :file', function() {
		var input = $(this),
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		input.trigger('fileselect', [label]);
	});

	$('.btn-file :file').on('fileselect', function(event, label) {
		    
		    var input = $(this).parents('.input-group').find(':text'),
		        log = label;
		    
		    if( input.length ) {
		        input.val(log);
		    } else {
		        if( log ) alert(log);
		    }
	    
	});
 
 function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        
	        reader.onload = function (e) {
	            $('#img-upload').attr('src', e.target.result);
	        }
	        
	        reader.readAsDataURL(input.files[0]);
	    }
	}
 function readURL1(inputSign) {
	    if (inputSign.files && inputSign.files[0]) {
	        var reader = new FileReader();
	        
	        reader.onload = function (m) {
	            $('#img-upload1').attr('src', m.target.result);
	        }
	        
	        reader.readAsDataURL(inputSign.files[0]);
	    }
	}
$( function() {
	 $('#udEmpNo').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	 $('#empName').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
    $("#dob").datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
    $( "#joiningDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
});

$('#activity_status').change(function() {
	if (this.checked) { 
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
});

function add(el) {
	$("#id").val("");
	$("#udEmpNo").val("");
	$("#udEmpNo").removeAttr('readonly');
	$("#empName").val("");
	$('#desigId').val("").trigger('change.select2');
	$("#fatherName").val("");
	$("#motherName").val("");
	$("#dob").val("");
	$('#genderId').val("").trigger('change.select2');
	$('#bloodGroup').val("").trigger('change.select2');
	$('#maritalStatus').val("").trigger('change.select2');
	$("#address").val("");
	$("#nid").val("");
	$("#mobileNo").val("");
	$("#email").val("");
	$("#qualification").val("");
	$("#joiningDate").val("");
	$("#experience").val("");
	$("#regNo").val("");
	$('#positionId').val("").trigger('change.select2');
	$('#gradeId').val("").trigger('change.select2');
	$('#reportingId').val("").trigger('change.select2');
	$("#relationship").val("");
	$('#superiorId').val("").trigger('change.select2');
	$('#supervisorId').val("").trigger('change.select2');
	$('#subordinateId').val("").trigger('change.select2');
	$("#activity_status").prop('checked', true);
	$('.check-status').text('Active ?');
	$('#status').val('Y');
	$("#img-upload").attr('src', "${pageContext.request.contextPath}/image/employee_info_doc/default-image.png");
	$("#employee_image").val("");
	$("#e_image").val("");
	$("#img-upload1").attr('src', "${pageContext.request.contextPath}/image/employee_info_doc/default-image.png");
	$("#img_signature").val("");
	$("#sig_image").val("");
	
	aTable.rows().remove().draw();
	bTable.rows().remove().draw();
	eduTable.rows().remove().draw();
	quaTable.rows().remove().draw();
	expTable.rows().remove().draw();
	skiTable.rows().remove().draw();
	tecTable.rows().remove().draw();
	
    $("#employeeInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}
function stringEscape(s) {
    return s ? s.replace(/\\/g,'\\\\').replace(/\n/g,'\\n').replace(/\t/g,'\\t').replace(/\v/g,'\\v').replace(/'/g,"\\'").replace(/"/g,'\\"').replace(/[\x00-\x1F\x80-\x9F]/g,hex) : s;
    function hex(c) { var v = '0'+c.charCodeAt(0).toString(16); return '\\x'+v.substr(v.length-2); }
}
function edit(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	var udEmpNo= $(el).closest('tr').find("#e_udEmpNo").val();
	var empName= $(el).closest('tr').find("#e_empName").val();
	var desigId= $(el).closest('tr').find("#e_desigId").val();
	var fatherName= $(el).closest('tr').find("#e_fatherName").val();
	var motherName=$(el).closest('tr').find("#e_motherName").val();
	var dob=$(el).closest('tr').find("#e_dob").val();
	var genderId=$(el).closest('tr').find("#e_gender").val();
	var bloodGroup=$(el).closest('tr').find("#e_bloodGroup").val();
	var maritalStatus= $(el).closest('tr').find("#e_maritalStatus").val();
	var address= $(el).closest('tr').find("#e_address").val();
	var nid=$(el).closest('tr').find("#e_nid").val();
	var mobileNo=$(el).closest('tr').find("#e_mobileNo").val();
	var email=$(el).closest('tr').find("#e_email").val();
	var qualification=$(el).closest('tr').find("#e_qualification").val();
	var joiningDate=$(el).closest('tr').find("#e_joiningDate").val();
	var experience=$(el).closest('tr').find("#e_experience").val();
	var regNo=$(el).closest('tr').find("#e_regNo").val();
	var positionId=$(el).closest('tr').find("#e_position_id").val();
	var gradeId=$(el).closest('tr').find("#e_gradeId").val();
	var reportingId=$(el).closest('tr').find("#e_reportingId").val();
	var relationship=$(el).closest('tr').find("#e_relationship").val();
	var superiorId=$(el).closest('tr').find("#e_superiorId").val();
	var supervisorId=$(el).closest('tr').find("#e_supervisorId").val();
	var subordinateId = $(el).closest('tr').find("#e_subordinateId").val();
	var employeeImage = $(el).closest('tr').find("#e_employeeImage").val();
	var employeeSignature = $(el).closest('tr').find("#e_employeeSign").val();
	var status = $(el).closest('tr').find("#e_status").val();
	
	$("#id").val(id);
	$("#udEmpNo").val(udEmpNo);
	$("#udEmpNo").attr('readonly', 'readonly');
	//$("#udEmpNo").prop("disabled", true);
	$("#empName").val(empName);
	$('#desigId').val(desigId).trigger('change.select2');
	$("#fatherName").val(fatherName);
	$("#motherName").val(motherName);
	$("#dob").val(convertMmDate(dob));
	$('#genderId').val(genderId).trigger('change.select2');
	$('#bloodGroup').val(bloodGroup).trigger('change.select2');
	$('#maritalStatus').val(maritalStatus).trigger('change.select2');
	$("#address").val(address);
	$("#nid").val(nid);
	$("#mobileNo").val(mobileNo);
	$("#email").val(email);
	$("#qualification").val(qualification);
	$("#joiningDate").val(convertMmDate(joiningDate));
	$("#experience").val(experience);
	$("#regNo").val(regNo);
	$('#positionId').val(positionId).trigger('change.select2');
	$('#gradeId').val(gradeId).trigger('change.select2');
	$('#reportingId').val(reportingId).trigger('change.select2');
	$("#relationship").val(relationship);
	$('#superiorId').val(superiorId).trigger('change.select2');
	$('#supervisorId').val(supervisorId).trigger('change.select2');
	$('#subordinateId').val(subordinateId).trigger('change.select2');
	$("#img-upload").attr('src', "${pageContext.request.contextPath}/image/employee_info_doc/"+id+"-"+employeeImage);
	$("#employee_image").val(employeeImage);
	$("#e_image").val(id+"-"+employeeImage);
	
	$("#img-upload1").attr('src', "${pageContext.request.contextPath}/image/employee_sign/"+id+"-"+employeeSignature);
	$("#signature_image").val(employeeSignature);
	$("#sig_image").val(id+"-"+employeeSignature);
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
	educationInfoEmpId(id);
	qualifyInfoEmpId(id);
	experienInfoEmpId(id);
	trainingInfoEmpId(id);
	attachInfoEmpId(id);
	skillsInfoEmpId(id);
	technicalInfoEmpId(id);
	$("#employeeInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
    
};

$('#udEmpNo').change(function() {
	var udEmpNo = $("#udEmpNo").val();
	$.get( "${pageContext.request.contextPath}/employee/validate-udEmpNo/" + udEmpNo, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate employee id  available!");
	    	$('#udEmpNo').focus();
		} else {
			$(".alert-code").empty().addClass("hidden");
			$('#udEmpNo').blur();
			console.log("no duplicate employee id");
		}
	});
});

$( function() {
    $( ".stDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
});

var aTable = $('#trainingTable').DataTable({
	"aaSorting" : [],
	"lengthMenu": [[10000], ["All"]],
	"ordering"	: false,
	"oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#trainingTable_length, #trainingTable_info, #trainingTable_paginate, #trainingTable_filter').hide();

var eduTable = $('#educationTable').DataTable({
	"aaSorting" : [],
	"lengthMenu": [[10000], ["All"]],
	"ordering"	: false,
	"oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#educationTable_length, #educationTable_info, #educationTable_paginate, #educationTable_filter').hide();

var quaTable = $('#qualificationTable').DataTable({
	"aaSorting" : [],
	"lengthMenu": [[10000], ["All"]],
	"ordering"	: false,
	"oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#qualificationTable_length, #qualificationTable_info, #qualificationTable_paginate, #qualificationTable_filter').hide();

var skiTable = $('#skillTable').DataTable({
	"aaSorting" : [],
	"lengthMenu": [[10000], ["All"]],
	"ordering"	: false,
	"oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#skillTable_length, #skillTable_info, #skillTable_paginate, #skillTable_filter').hide();

var tecTable = $('#technicalTable').DataTable({
	"aaSorting" : [],
	"lengthMenu": [[10000], ["All"]],
	"ordering"	: false,
	"oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#technicalTable_length, #technicalTable_info, #technicalTable_paginate, #technicalTable_filter').hide();

var expTable = $('#experienceTable').DataTable({
	"aaSorting" : [],
	"lengthMenu": [[10000], ["All"]],
	"ordering"	: false,
	"oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#experienceTable_length, #experienceTable_info, #experienceTable_paginate, #experienceTable_filter').hide();

var bTable = $('#attachList').DataTable({
	"aaSorting" : [[1, 'asc']],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
	 "oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No file available",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#attachList_length, #attachList_info, #attachList_paginate, #attachList_filter').hide();

function addRow(el){
	 
	var td2="<input style='width: 100%;' type='text' id='' name='trainDate[]' class='stDate' required='required'/>";
	var td3="<input style='width: 100%;' type='text' id='r_course' name='courseTitle[]' required='required'/>";
	var td4="<input style='width: 100%;' type='text' id='r_training_agency' name='courseInst[]' required='required'/>";
	var td5="<input style='width: 100%;' type='text' id='r_assessment' name='assesment[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="addRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = aTable.row.add([ td2, td3, td4, td5, html]).draw( false ).node();
	aTable.draw();
	aTable.page('last').draw(false);
	$(rowNode).find("td:eq(4)").css({"text-align": "center"});
	$(rowNode).find("td:eq(5)").css({"text-align": "center"});
	
	$( function() {
	    $( ".stDate" ).datepicker({
			format: "d-M-yyyy",
	        todayHighlight: true,
	        autoclose: true
	    });
	});
		
}

function eduAddRow(el){
	var td1="<input style='width: 100%;' type='text' name='examName[]' required='required'/>";
	var td2="<input style='width: 100%;' type='text' name='board[]' required='required'/>";
	var td3="<input style='width: 100%;' type='text' name='passingYear[]' required='required'/>";
	var td4="<input style='width: 100%;' type='text' name='examResult[]' required='required'/>";
	var td5="<input style='width: 100%;' type='text' name='institute[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="eduAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = eduTable.row.add([ td1, td2, td3, td4, td5, html]).draw( false ).node();
	eduTable.draw();
	eduTable.page('last').draw(false);
	$(rowNode).find("td:eq(4)").css({"text-align": "center"});
	$(rowNode).find("td:eq(5)").css({"text-align": "center"});
			
}

function quaAddRow(el){
	var td1="<input style='width: 100%;' type='text' name='qualiName[]' required='required'/>";
	var td2="<input style='width: 100%;' type='text' name='qualiDescrip[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="quaAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = quaTable.row.add([ td1, td2, html]).draw( false ).node();
	quaTable.draw();
	quaTable.page('last').draw(false);
	$(rowNode).find("td:eq(0)").css({"text-align": "center"});
	$(rowNode).find("td:eq(1)").css({"text-align": "center"});
	$(rowNode).find("td:eq(2)").css({"text-align": "center"});
			
}

function skillAddRow(el){
	var td1="<input style='width: 100%;' type='text' name='skillTitle[]' required='required'/>";
	var td2="<input style='width: 100%;' type='text' name='skillDescrip[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="skillAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = skiTable.row.add([ td1, td2, html]).draw( false ).node();
	skiTable.draw();
	skiTable.page('last').draw(false);
	$(rowNode).find("td:eq(0)").css({"text-align": "center"});
	$(rowNode).find("td:eq(1)").css({"text-align": "center"});
	$(rowNode).find("td:eq(2)").css({"text-align": "center"});
			
}

function tecAddRow(el){
	var td1="<input style='width: 100%;' type='text' name='tecTitle[]' required='required'/>";
	var td2="<input style='width: 100%;' type='text' name='tecDescrip[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="tecAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = tecTable.row.add([ td1, td2, html]).draw( false ).node();
	tecTable.draw();
	tecTable.page('last').draw(false);
	$(rowNode).find("td:eq(0)").css({"text-align": "center"});
	$(rowNode).find("td:eq(1)").css({"text-align": "center"});
	$(rowNode).find("td:eq(2)").css({"text-align": "center"});
			
}

function expAddRow(el){
	var td1="<input style='width: 100%;' type='text' name='experienceDuration[]' required='required'/>";
	var td2="<input style='width: 100%;' type='text' name='experienceDesign[]' required='required'/>";
	var td3="<input style='width: 100%;' type='text' name='experienceDescrip[]' required='required'/>";
	var td4="<input style='width: 100%;' type='text' name='companyName[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="expAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = expTable.row.add([ td1, td2, td3, td4, html]).draw( false ).node();
	expTable.draw();
	expTable.page('last').draw(false);
	$(rowNode).find("td:eq(0)").css({"text-align": "center"});
	$(rowNode).find("td:eq(1)").css({"text-align": "center"});
	$(rowNode).find("td:eq(2)").css({"text-align": "center"});
	$(rowNode).find("td:eq(3)").css({"text-align": "center"});
	$(rowNode).find("td:eq(4)").css({"text-align": "center"});
			
}
	
function remove(el){
	 var tbl = $(el).closest('table').DataTable();
	 var tr  = $(el).closest('tr');
	 tbl.row(tr).remove().draw(false);
}

function educationInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-educationInfo-empId/" + empId,
		success : function(data) {
			var rows = eduTable.rows().remove().draw();
			var educationList = data.length;
			 if (educationList > 0) {
					showEducationList(data);
				}else {
					eduTable.rows().remove().draw();
				} 
			}
		});
}

function showEducationList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var employeeId 	= data[i].employeeId || "";
		var examName 	= data[i].examName || "";
		var board = data[i].board || "";
		var passingYear 	= data[i].passingYear || "";
		var examResult 	= data[i].examResult || "";
		var institute 	= data[i].institute || "";
		
		var td1="<input style='width: 100%;' type='text' name='examName[]' value='"+examName+"' required='required'/>";
		var td2="<input style='width: 100%;' type='text' name='board[]' value='"+board+"' required='required'/>";
		var td3="<input style='width: 100%;' type='text' name='passingYear[]' value='"+passingYear+"' required='required'/>";
		var td4="<input style='width: 100%;' type='text' name='examResult[]' value='"+examResult+"' required='required'/>";
		var td5="<input style='width: 100%;' type='text' name='institute[]' value='"+institute+"' required='required'/>";
		var html = '' 
				+ '<a class="btn-edit btn btn-xs" onclick="eduAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
				+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		var rowNode = eduTable.row.add([ td1, td2, td3, td4, td5, html]).draw( false ).node();
		eduTable.draw();
		eduTable.page('last').draw(false);
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
	}
}

function qualifyInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-qualifyInfo-empId/" + empId,
		success : function(data) {
			var rows = quaTable.rows().remove().draw();
			var qualifyList = data.length;
			 if (qualifyList > 0) {
					showQualifyList(data);
				}else {
					quaTable.rows().remove().draw();
				} 
			}
		});
}

function showQualifyList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var employeeId 	= data[i].employeeId || "";
		var qualiName 	= data[i].qualiName || "";
		var qualiDescrip = data[i].qualiDescrip || "";
		
		var td1="<input style='width: 100%;' type='text' name='qualiName[]' value='"+qualiName+"' required='required'/>";
		var td2="<input style='width: 100%;' type='text' name='qualiDescrip[]' value='"+qualiDescrip+"' required='required'/>";
		var html = '' 
				+ '<a class="btn-edit btn btn-xs" onclick="quaAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
				+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		var rowNode = quaTable.row.add([ td1, td2, html]).draw( false ).node();
		quaTable.draw();
		quaTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
	}
}

function skillsInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-skillsInfo-empId/" + empId,
		success : function(data) {
			var rows = skiTable.rows().remove().draw();
			var skillsList = data.length;
			 if (skillsList > 0) {
					showSkillsList(data);
				}else {
					skiTable.rows().remove().draw();
				} 
			}
		});
}

function showSkillsList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var employeeId 	= data[i].employeeId || "";
		var skillTitle 	= data[i].skillTitle || "";
		var skillDescrip = data[i].skillDescrip || "";
		
		var td1="<input style='width: 100%;' type='text' name='skillTitle[]' value='"+skillTitle+"' required='required'/>";
		var td2="<input style='width: 100%;' type='text' name='skillDescrip[]' value='"+skillDescrip+"' required='required'/>";
		var html = '' 
				+ '<a class="btn-edit btn btn-xs" onclick="skillAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
				+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		var rowNode = skiTable.row.add([ td1, td2, html]).draw( false ).node();
		skiTable.draw();
		skiTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
	}
}

function technicalInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-technicalInfo-empId/" + empId,
		success : function(data) {
			var rows = tecTable.rows().remove().draw();
			var technicalList = data.length;
			 if (technicalList > 0) {
					showTechnicalList(data);
				}else {
					tecTable.rows().remove().draw();
				} 
			}
		});
}

function showTechnicalList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var employeeId 	= data[i].employeeId || "";
		var tecTitle 	= data[i].tecTitle || "";
		var tecDescrip  = data[i].tecDescrip || "";
		
		var td1="<input style='width: 100%;' type='text' name='tecTitle[]' value='"+tecTitle+"' required='required'/>";
		var td2="<input style='width: 100%;' type='text' name='tecDescrip[]' value='"+tecDescrip+"' required='required'/>";
		var html = '' 
				+ '<a class="btn-edit btn btn-xs" onclick="tecAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
				+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		var rowNode = tecTable.row.add([ td1, td2, html]).draw( false ).node();
		tecTable.draw();
		tecTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
	}
}

function experienInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-experienceInfo-empId/" + empId,
		success : function(data) {
			var rows = expTable.rows().remove().draw();
			var experienceList = data.length;
			 if (experienceList > 0) {
					showExperienceList(data);
				}else {
					expTable.rows().remove().draw();
				} 
			}
		});
}

function showExperienceList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var employeeId 	= data[i].employeeId || "";
		var experienceDuration 	= data[i].experienceDuration || "";
		var experienceDesign = data[i].experienceDesign || "";
		var experienceDescrip = data[i].experienceDescrip || "";
		var companyName 	= data[i].companyName || "";
		
		var td1="<input style='width: 100%;' type='text' name='experienceDuration[]' value='"+experienceDuration+"' required='required'/>";
		var td2="<input style='width: 100%;' type='text' name='experienceDesign[]' value='"+experienceDesign+"' required='required'/>";
		var td3="<input style='width: 100%;' type='text' name='experienceDescrip[]' value='"+experienceDescrip+"' required='required'/>";
		var td4="<input style='width: 100%;' type='text' name='companyName[]' value='"+companyName+"' required='required'/>";
		var html = '' 
				+ '<a class="btn-edit btn btn-xs" onclick="expAddRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
				+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		var rowNode = expTable.row.add([ td1, td2, td3, td4, html]).draw( false ).node();
		expTable.draw();
		expTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		$(rowNode).find("td:eq(3)").css({"text-align": "center"});
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
	}
}

function trainingInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-trainingInfo-empId/" + empId,
		success : function(data) {
			var rows = aTable.rows().remove().draw();
			var trainingList = data.length;
			 if (trainingList > 0) {
					showTrainingList(data);
				}else {
					aTable.rows().remove().draw();
				} 
			}
		});
}

function showTrainingList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var employeeId 	= data[i].employeeId || "";
		var trainDate 	= data[i].trainDate || "";
		var courseTitle = data[i].courseTitle || "";
		var courseInst 	= data[i].courseInst || "";
		var assesment 	= data[i].assesment || "";
		
		var td2="<input style='width: 100%;' type='text' id='' name='trainDate[]' class='stDate' value='"+convertMmDate(trainDate)+"' required='required'/>";
		var td3="<input style='width: 100%;' type='text' id='r_course' name='courseTitle[]' value='"+courseTitle+"' required='required'/>";
		var td4="<input style='width: 100%;' type='text' id='r_training_agency' name='courseInst[]' value='"+courseInst+"' required='required'/>";
		var td5="<input style='width: 100%;' type='text' id='r_assessment' name='assesment[]' value='"+assesment+"' required='required'/>";
		var html = '' 
				+ '<a class="btn-edit btn btn-xs" onclick="addRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
				+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		var rowNode = aTable.row.add([ td2, td3, td4, td5, html]).draw( false ).node();
		aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
		
		$( function() {
		    $( ".stDate" ).datepicker({
				format: "d-M-yyyy",
		        todayHighlight: true,
		        autoclose: true
		    });
		});
		
	}
}

function attachInfoEmpId(empId) {
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/get-attachInfo-empId/" + empId,
		success : function(data) {
			var rows = bTable.rows().remove().draw();
			var attachList = data.length;
			if (attachList > 0) {
					showAttachList(data);
				}else {
					bTable.rows().remove().draw();
				}
			}
		});
}

function showAttachList(data) {
	for (var i = 0; i < data.length; i++) {
		var id 			= data[i].id || "";
		var empId 	= data[i].empId || "";
		var filesizeInBytes = data[i].fileSize || "";
		var filesizeInMB = (filesizeInBytes / (1024*1024)).toFixed(2);
		var filename = data[i].fileName || "";
		var path = "${pageContext.request.contextPath}/image/employee_info_doc/" + empId + "-" + filename;

		var html = '' 
			+ '<a href="' + path + '" download="'+ filename +'" class="btn btn-xs btn-success waves-effect"><i class="fa fa-download"></i></a>&nbsp;'
			+ '<a class="btn btn-xs btn-danger waves-effect" onclick="del(this)"><i class="material-icons">delete_forever</i></a>'
			+ '<input type="hidden" id="file_name" name="fileName" class="file-name" value="'+ filename +'"/>'
			+ '<input type="hidden" id="file_size" name="fileSize" class="file-size" value="'+ filesizeInBytes +'"/>';
		var rowNode = bTable.row.add( [filename,filesizeInBytes,html] ).draw( false ).node();
		bTable.order([0, 'asc']).draw();
		bTable.page('last').draw(false);
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		
	}
}

function del(el){
    var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
    
    var fileName = tr.find("#file_name").val();
    var empId = $("#id").val();
    alert(empId+"#"+fileName);
	$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/employee/remove-attachment?fileName=" +fileName+ "&empId=" + empId,
		success : function() {
			console.log("remove success");
		},
		error: function(){
			console.log("this employee has no user id.");
	  	}
	});
}

$("#employeeInfoForm").submit(function(event){
	event.preventDefault();	
    var formData = new FormData($("#employeeInfoForm")[0]);
    swal({
        title: "Are you sure?",
        text: "You will submit this form!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, submit this form!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
        	url : "${pageContext.request.contextPath}/employee/save-employee-info",
            type: 'POST',
            data: formData,
            enctype: 'multipart/form-data',
            async: false,
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {				 
            	$("#employeeInfoModal").modal('hide');
    			$('.modal-backdrop').remove();
    			$("#view_page").html(data);
    			sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
            },
            error: function(){
            	sweetAlert("Failed!", "Something went wrong.", "fail", 1000, false);
    	  	}
        });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 3000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 0, false);
        }
        });
});

function view(el) {
	
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/getEmployeeInfoById/" + id ,
		success : function(data) {
			$('#employeeViewInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			}
		});
}

function getFileSizeandName(input){
	var empId = $("#id").val();
	if (empId != "") {
		saveAttachment();

		for(var i =0; i<input.files.length; i++){           
			var filesizeInBytes = input.files[i].size + " Bytes";
			var filesizeInMB = (filesizeInBytes / (1024*1024)).toFixed(2);
			var filename = input.files[i].name;
			var path = "${pageContext.request.contextPath}/image/employee_info_doc/" + empId + "-" + filename;
			
			var html = '' 
				+ '<a href="' + path + '" download="'+ filename +'" class="btn btn-xs btn-success waves-effect"><i class="fa fa-download"></i></a>&nbsp;'
				+ '<a class="btn btn-xs btn-danger waves-effect" onclick="del(this)"><i class="material-icons">delete_forever</i></a>'
				+ '<input type="hidden" id="file_name" name="fileName" class="file-name" value="'+ filename +'"/>'
				+ '<input type="hidden" id="file_size" name="fileSize" class="file-size" value="'+ filesizeInBytes +'"/>';
			var rowNode = bTable.row.add( [filename,filesizeInBytes,html] ).draw( false ).node();
			bTable.order([0, 'asc']).draw();
			bTable.page('last').draw(false);
			$(rowNode).find("td:eq(1)").css({"text-align": "center"});
			$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		}
	}else{
    	sweetAlert("Failed!", "Please save employee information first, then attach again.", "warning", 3000, false);
    }
	 
}

function saveAttachment(){
	var formData = new FormData($("#employeeInfoForm")[0]);
	$.ajax({	
    	url : "${pageContext.request.contextPath}/employee/save-attachment",
        type: 'POST',
        data: formData,
        enctype: 'multipart/form-data',
        async: false,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {				 
        	console.log("success");
        },
        error: function(){
        	console.log("failed");
	  	}
    });
	
}


</script>