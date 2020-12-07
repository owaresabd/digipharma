<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<style>
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
}
.table > tbody > tr > td, .table > tfoot > tr > td {
    font-size: none !important;
}
.bg-cyan {
  background-color: #30acbb !important;
  color: #fff; 
  }
  
</style>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">MATERIAL INFO</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/material/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/material/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="countryList">
						<thead>
							<tr>
							  <th class="align-center" style="width: 70px;">SL NO</th>
							  <th class="align-left">MATERIAL NAME</th>
							  <th class="align-left" style="width: 150px;">MATERIAL CODE</th>
							  <th class="align-left" style="width: 150px;">MATERIAL TYPE</th>
							  <th class="align-center" style="width: 80px;">STATUS</th>
							  <th class="align-center" style="width: 110px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="matInfo" items="${materialList}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${matInfo.materialName}</td>
								<td class="align-left">${matInfo.materialCode}</td>
								<td class="align-left">${matInfo.materialTypeName}</td>
								<td class="align-center" width="100px">
									<c:choose>
									    <c:when test="${matInfo.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="m_id" value="${matInfo.id}">
									<input type="hidden" id="m_materialName" value="${matInfo.materialName}">
									<input type="hidden" id="m_materialCode" value="${matInfo.materialCode}">
									<input type="hidden" id="m_materialTypeId" value="${matInfo.materialTypeId}">
									<input type="hidden" id="m_isChemical" value="${matInfo.isChemical}">
									<input type="hidden" id="m_isMicrobiological" value="${matInfo.isMicrobiological}">
									<input type="hidden" id="m_chemicalSampleAmt" value="${matInfo.chemicalSampleAmt}">
									<input type="hidden" id="m_chemicalRetentionAmt" value="${matInfo.chemicalRetentionAmt}">
									<input type="hidden" id="m_chemicalTotal" value="${matInfo.chemicalTotal}">
									<input type="hidden" id="m_microSampleAmt" value="${matInfo.microSampleAmt}">
									<input type="hidden" id="m_microRetentionAmt" value="${matInfo.microRetentionAmt}">
									<input type="hidden" id="m_microTotal" value="${matInfo.microTotal}">
									<input type="hidden" id="m_totalAmt" value="${matInfo.totalAmt}">
									<input type="hidden" id="m_unitId" value="${matInfo.unitId}">
									<input type="hidden" id="m_unitName" value="${matInfo.unitName}">
									<input type="hidden" id="m_status" value="${matInfo.status}">
									<input type="hidden" id="m_storageConId" value="${matInfo.storageConId}">
									<input type="hidden" id="m_storageCondition" value="${matInfo.storageCondition}">
									<input type="hidden" id="m_matSamProcedure" value="${matInfo.matSamProcedure}">
									<input type="hidden" id="m_matMethodId" value="${matInfo.matMethodId}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>&nbsp;
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>&nbsp;
									<a class="btn-edit btn btn-xs" onclick="materialInfoPrint('${matInfo.id}','${matInfo.materialCode}')"><i class="material-icons">print</i></a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" style="overflow: auto;" id="materialInfoModal"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-xl" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="modal-header bg-cyan">
				    <table class="table table-bordered" style="border: 2px solid #ddd;">
				    	<tbody style="border: 2px solid #ddd;">
				    	<tr>
				    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
				    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
				    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Material Information</td>
				    	<td>Document No.</td>
				    	<td colspan="2">FM-DIL-GN-070</td>
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
                 <form method="post" id="materialInfoForm">   <!-- onkeypress="if (event.keyCode == 13) { return false; }" -->
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>MATERIAL NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="materialName" name="materialName" value="" class="form-control" required="required" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>MATERIAL CODE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="materialCode" name="materialCode" value="" class="form-control uperrcase" required="required"  autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>MATERIAL TYPE  :</b></span>
                            	<div class="form-group">
	                                <select id="materialTypeId" name="materialTypeId" class="js-example-theme-single form-control" required="required" style="width: 100%;">
				                        	<option value="">--Select One--</option>
				                        <c:forEach var="info" items="${typeList}">
				                           	<option value="${info.id }">${info.typeName}</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
	                                <!-- <select  id="storageCondition" name="storageCondition" class="js-example-theme-single form-control" style="width: 100%;" required="required"> -->
	                                <select  id="storageConId" name="storageConId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="info" items="${conditionList}">
				                           	<option value="${info.id }">${info.storageDesc}</option>
				                        </c:forEach>
					                </select>
					                <input type="hidden" id="storageCondition" name="storageCondition" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-3"> 
                            	<span><b>SAMPLING PLAN :</b></span>
                            	<div class="form-group">
	                                <select  id=matMethodId name="matMethodId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option value="">--Select One--</option>
				                        <c:forEach var="methodInfo" items="${samMethodList}">
				                           	<option value="${methodInfo.id }">${methodInfo.methodName}</option>
				                        </c:forEach>
					                </select>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>SAMPLING PROCEDURE  :</b></span>
                            	<div class="form-group">
	                                <select  id="matSamProcedure" name="matSamProcedure" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option value="">--Select One--</option>
			                        	<option value="N">Normal</option>
			                        	<option value="A">Aseptic</option>
			                        	<option value="0">N/A</option>
				                    </select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2">
                            	<label for="service_item">ANALYSIS :</label>
                            </div>
                            </div>
                 		<div class="row">
	                 		
                            <div class="col-md-12">
                            	<div class="form-group">
	                                <div class="demo-checkbox">
									<input type="checkbox" id="chemical_status" class="filled-in chk-col-green">
									<label for="chemical_status"><b><span>CHEMICAL</span></b></label>
									<input type="hidden" id="isChemical" name="isChemical" value="N">
								</div>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                         <div class="col-md-12">
                           <div class="form-group" style="overflow: auto;">
                            	<table id="chemicTable" class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th class="align-center" style="width: 100px;">Test Parameter No</th>
											<th class="align-center" style="width: 150px;">Test Parameter Name</th>
											<th class="align-center" style="width: 120px;">Specification</th>
											<th class="align-center" style="width: 100px;">Reference</th>
											<th class="align-center" style="width: 130px;">Test Method</th>
											<th class="align-center" style="width: 130px;">Test Unit</th>
											<th class="align-center" style="width: 130px;">LOD</th>
											<th class="align-center" style="width: 130px;">Sample Amount</th>
											<th class="align-center" style="width: 130px;">Unit</th>
											<th class="align-center" style="width: 50px;">####</th>
										</tr>
										<tr>
										<td class="align-center">
											<input type="hidden" id="chemiChildId" value=""/>
											<input style="width: 100%;" type="text" id="chemiParameterNo" class="form-control" readonly="readonly"/>
										</td>
										<td class="align-center"> 
											<select id="chemiParameterId" class="js-example-theme-single form-control" style="width: 100%;">
											<option></option>
											<c:forEach var="paramInfo" items="${paramList}">
												<option value="${paramInfo.id}">${paramInfo.testParameterName}</option>
											</c:forEach>
											</select>
											<input type="hidden" id="chemiParameter_id" name="" value=""/>
			                        		<input type="hidden" id="chemiParameter_name" name="" value=""/>
										</td>
										<td class="align-center">
											<textarea style="width: 100%; height: 30px;" rows="1" id="chemiSpecification"/></textarea> 
											<!-- <input style="width: 100%;" type="text" id="chemiSpecification"/> -->
										</td>
										<td class="align-center">
											<select id="chemiReferenceId"  class="js-example-theme-single form-control" style="width: 100%;">
											<option></option>
											<c:forEach var="refInfo" items="${referenceList}">
												<option value="${refInfo.id }">${refInfo.referenceName}</option>
											</c:forEach>
											</select>
										</td>
										<td class="align-center">
											<select id="chemiMethodId"  class="js-example-theme-single form-control" style="width: 100%;">
											<option></option>
											<c:forEach var="methodInfo" items="${methodList}">
												<option value="${methodInfo.id }">${methodInfo.methodName}</option>
											</c:forEach>
											</select>
										</td>
										<td class="align-center">
										<select id="chemiTestUnitId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
						                        <c:forEach var="unitInfo" items="${testUnitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
											
										</td>
										<td class="align-center">
											<input style="width: 100%;" type="text" id="chemiLod"/>
										</td>
										<td class="align-center">
											<input style="width: 100%;" type="text" id="chemiSampleAmount"/>
										</td>
										<td class="align-center">
										<select id="chemiUnitId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
										
										</td>
										<td class="align-center">
											<button type="button" id="addBtn" class="btn bg-blue btn-xs waves-effect" onclick="addRowChemical(this)"> 
												<i class="material-icons">gamepad</i>
											</button>
										</td>
									</tr>
									</thead>
									<tbody>
									
								</tbody>
							</table>
					</div>
                            </div>   
                            
                 		</div>
                 		<div class="row">
	                 		
                            <div class="col-md-10">
                            	<div class="form-group">
	                                <div class="demo-checkbox">
									<input type="checkbox" id="micro_status" class="filled-in chk-col-green">
									<label for="micro_status"><b><span >MICROBIOLOGICAL</span></b></label>
									<input type="hidden" id="isMicrobiological" name="isMicrobiological" value="N">
								</div>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                        <div class="col-md-12">
                        	<div class="form-group" style="overflow: auto;">
                            	<table id="microTable" class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th class="align-center" style="width: 100px;">Test Parameter No</th>
										<th class="align-center" style="width: 150px;">Test Parameter Name</th>
										<th class="align-center" style="width: 120px;">Specification</th>
										<th class="align-center" style="width: 100px;">Reference</th>
										<th class="align-center" style="width: 130px;">Test Method</th>
										<th class="align-center" style="width: 130px;">Test Unit</th>
										<th class="align-center" style="width: 130px;">LOD</th>
										<th class="align-center" style="width: 130px;">Sample Amount</th>
										<th class="align-center" style="width: 130px;">Unit</th>
										<th class="align-center" style="width: 50px;">####</th>
									</tr>
									<tr>
										<td class="align-center">
											<input type="hidden" id="microChildId" value=""/>
											<input style="width: 100%;" type="text" id="microParameterNo" class="form-control" readonly="readonly"/>
										</td>
										<td class="align-center">
											<select id="microParameterId" class="js-example-theme-single form-control" style="width: 100%;">
											<option></option>
											<c:forEach var="paramInfo" items="${paramList}">
												<option value="${paramInfo.id }">${paramInfo.testParameterName}</option>
											</c:forEach>
											</select>
											<input type="hidden" id="microParameter_id" name="" value=""/>
                        					<input type="hidden" id="microParameter_name" name="" value=""/>
										</td>
													
										<td class="align-center">
											<textarea style="width: 100%; height: 30px;" rows="1" id="microSpecification"></textarea>
										</td>
										<td class="align-center">
											<select id="microReferenceId" class="js-example-theme-single form-control" style="width: 100%;">
											<option></option>
											<c:forEach var="refInfo" items="${referenceList}">
												<option value="${refInfo.id }">${refInfo.referenceName}</option>
											</c:forEach>
											</select>
										</td>
										<td class="align-center">
											<select id="microMethodId"  class="js-example-theme-single form-control"
											style="width: 100%;">
											<option></option>
											<c:forEach var="methodInfo" items="${methodList}">
												<option value="${methodInfo.id }">${methodInfo.methodName}</option>
											</c:forEach>
											</select>
										</td>
										<td class="align-center">
										<select id="microTestUnitId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
						                        <c:forEach var="unitInfo" items="${testUnitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
											
										</td>
										<td class="align-center">
											<input style="width: 100%;" type="text" id="microLod"  />
										</td>
										<td class="align-center">
											<input style="width: 100%;" type="text" id="microSampleAmount"  />
										</td>
										<td class="align-center">
										<select id="microUnitId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
											
										</td>
										<td class="align-center">
											<button type="button" id="addBtn" class="btn bg-blue btn-xs waves-effect" onclick="addRowMicro(this)"> 
												<i class="material-icons">gamepad</i>
											</button>
										</td>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
					</div>
                  </div>   
              </div>
                 		<div class=" col-md-6">
                 		<div class="panel panel-primary">
							<div class="panel-heading">
								<h1 class="panel-title"><b>Sample Amount  ( Chemical )</b></h1>
							</div>

							<div class="panel-body">
								<div class="row">
									<div class="col-md-4">
										<span><b>Test Sample :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="chemicalSampleAmt" name="chemicalSampleAmt" value="" class="form-control" readonly="readonly" autocomplete="off">
                            	</div>

									</div>

									<div class="col-md-4">
										<span><b>Retention Sample :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="chemicalRetentionAmt" onkeyup="chemiTotalCalculation()" name="chemicalRetentionAmt" value="" class="form-control number"  autocomplete="off">
                            	</div>

									</div>
									<div class="col-md-4">
										<span><b>Total :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="chemicalTotal" name="chemicalTotal" value="" class="form-control" readonly="readonly" autocomplete="off">
                            	</div>

									</div>
								</div>


							</div>

						</div>
						</div>
						<div class="col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h1 class="panel-title"><b>Sample Amount  ( Microbiological  )</b></h1>
							</div>

							<div class="panel-body">
								<div class="row">
									<div class="col-md-4">
										<span><b>Test Sample :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="microSampleAmt" name="microSampleAmt" value="" class="form-control" readonly="readonly" autocomplete="off">
                            	</div>

									</div>

									<div class="col-md-4">
										<span><b>Retention Sample :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="microRetentionAmt"  name="microRetentionAmt" onkeyup="microTotalCalculation()" value="" class="form-control number"  autocomplete="off">
                            	</div>

									</div>
									<div class="col-md-4">
										<span><b>Total :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="microTotal" name="microTotal" value="" class="form-control" readonly="readonly" autocomplete="off">
                            	</div>

									</div>
								</div>

							</div>

						</div>
						</div>
						
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>TOTAL SAMPLE AMOUNT :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="totalAmt" style="width:40%" name="totalAmt" value="" class="form-control" readonly="readonly"  autocomplete="off" >&nbsp;<label id="unitLbl"></label>
	                                <input type="hidden" id="unitId" name="unitId" value="" >
	                                
                            	</div>
                            </div>
                            
                            <div class="col-md-6">
                            	<span><b>STATUS :</b></span>
                            	<div class="demo-checkbox m-t-5">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            </div>
                 		</div>
                 		
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i>
							<span><spring:message code="btn.save"/></span>
						</button>
	                 </div>
                 </form>
             </div>
         </div>
     </div>
     <div class="modal fade" id="materialViewModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true" >
         <div id="modalId" class="modal-dialog modal-xl" role="document" aria-hidden="true">
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

input {
    height: 28px !important;
}

</style>

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
function pageReload(link){
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
$(".load").on('click', function(e){
	var link =  "${pageContext.request.contextPath}/material/maintain";
	$("#materialInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
});

$(document).ready(function() {
    $(window).keydown(function(event){
    	if((event.keyCode == 13) && ($(event.target)[0]!=$("textarea")[0])) {
            event.preventDefault(); 
            return false;
        }
    });

});


var chemiTextarea = document.getElementById("chemiSpecification");
var chemiLimit = 800; //height limit
chemiTextarea.oninput = function() {
	chemiTextarea.style.height = "";
	chemiTextarea.style.height = Math.min(chemiTextarea.scrollHeight, chemiLimit) + "px";
};

var microTextarea = document.getElementById("microSpecification");
var limit = 800; //height limit
microTextarea.oninput = function() {
	microTextarea.style.height = "";
	microTextarea.style.height = Math.min(microTextarea.scrollHeight, limit) + "px";
};

$(function() {
    $('.uperrcase').keyup(function() {
        this.value = this.value.toUpperCase();
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
$('#chemical_status').change(function() {
	if (this.checked) {
		$('#isChemical').val('Y');
	}else{
		$('#isChemical').val('N');
		
	}
});
$('#micro_status').change(function() {
	if (this.checked) {
		$('#isMicrobiological').val('Y');
	}else{
		$('#isMicrobiological').val('N');
		
	}
});


$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$("#storageConId").on('change', function() {
	var id 		 = $('option:selected', this).val();
	var condText = $('option:selected', this).text();
	$("#storageCondition").val(condText);
});

$("#chemiParameterId").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#chemiParameter_id").val(id);
	$("#chemiParameter_name").val(name);
	$(".alert-code").empty().addClass("hidden");
	chemiParameterInfoById(id);
});

$("#microParameterId").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#microParameter_id").val(id);
	$("#microParameter_name").val(name);
	$(".alert-code").empty().addClass("hidden");
	microParameterInfoById(id);
});

var aTable = $('#chemicTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
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

$('#chemicTable_length, #chemicTable_info, #chemicTable_paginate, #chemicTable_filter').hide();

var bTable = $('#microTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
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

$('#microTable_length, #microTable_info, #microTable_paginate, #microTable_filter').hide();

function chemiParameterInfoById(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/material/parameterInfoById?parameterId=" + id,
		dataType : 'json',
		success : function(data) {
			for(var i=0; i<data.length; i++){
				var id = data[i].id;
				var parameterNo = data[i].testParameterNo;
				var parameterName = data[i].testParameterName;
								
				$("#chemiParameterNo").val(parameterNo);
				$('#chemiParameterId').val(id).trigger('change.select2');
				
			}
		}
	});
}

function microParameterInfoById(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/material/parameterInfoById?parameterId=" + id,
		dataType : 'json',
		success : function(data) {
			for(var i=0; i<data.length; i++){
				var id = data[i].id;
				var parameterNo = data[i].testParameterNo;
				var parameterName = data[i].testParameterName;
								
				$("#microParameterNo").val(parameterNo);
				$('#microParameterId').val(id).trigger('change.select2');
				
			}
		}
	});
}

function addRowChemical(el){
	var isChemical=$('#chemical_status').prop('checked');
	
	if(isChemical==true){
	var unitId=$("#unitId").val();
	var id=$("#id").val();
	var chdId=$("#chemiChildId").val();
	var chemiParameterNo = $("#chemiParameterNo").val();
	var chemiParameterId = $("#chemiParameterId").val();
	var chemiParameterNm = $('#chemiParameterId option:selected').text();
    var chemiSpecification = $("#chemiSpecification").val();
	var chemiReferenceId = $("#chemiReferenceId").val();
	var chemiReferenceNm = $('#chemiReferenceId option:selected').text();
	var chemiMethodId = $("#chemiMethodId").val();
	var chemiMethodNm = $('#chemiMethodId option:selected').text();
	var chemiTestUnitId = $("#chemiTestUnitId").val();
	var chemiTestUnitNm =$('#chemiTestUnitId option:selected').text();
	var chemiLod = $("#chemiLod").val();
	var chemiSampleAmount = $("#chemiSampleAmount").val();
	var chemiUnitId = $("#chemiUnitId").val();
	var chemiUnitNm =$('#chemiUnitId option:selected').text();
	if(chdId == ""){
		chdId="-1";
	} 

	var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_child_id"  name="chemiChildId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="i_chemiParameterNo" name="chemiParameterNo[]"  value="'+chemiParameterNo+'"/>'
			+ '<input type="hidden" id="i_chemiParameterId" name="chemiParameterId[]" value="'+chemiParameterId+'"/>'
			+ '<input type="hidden" id="i_chemiSpecification" name="chemiSpecification[]" value="'+chemiSpecification+'"/>'
			+ '<input type="hidden" id="i_chemiReferenceId" name="chemiReferenceId[]" value="'+chemiReferenceId+'"/>'
			+ '<input type="hidden" id="i_chemiMethodId" name="chemiMethodId[]" value="'+chemiMethodId+'"/>'
			+ '<input type="hidden" id="i_chemiTestUnitId" name="chemiTestUnitId[]" value="'+chemiTestUnitId+'"/>'
			+ '<input type="hidden" id="i_chemiLod" name="chemiLod[]" class="chemiLodClass" value="'+chemiLod+'"/>'
			+ '<input type="hidden" id="i_chemiSampleAmount" name="chemiSampleAmount[]" class="chemiSampleAmtClass" value="'+chemiSampleAmount+'"/>'
			+ '<input type="hidden" id="i_chemiUnitId" name="chemiUnitId[]" class="hasUnitId" value="'+chemiUnitId+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRowChe(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			if(unitId.length == 0 ){
				$("#unitId").val(chemiUnitId);
				$("#unitLbl").text(chemiUnitNm);
				
			}else{
				if(unitId !=chemiUnitId){
					apendRow = false;
					sweetAlert("Warning!", "Please Select Same Unit", "warning", 1000, false);	
					
				}	
				
			}
			
			if (apendRow == true) {
	
			var rowNode = aTable.row.add([chemiParameterNo, chemiParameterNm,chemiSpecification,chemiReferenceNm, chemiMethodNm, chemiTestUnitNm,chemiLod,chemiSampleAmount,chemiUnitNm, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(1)").css({"class": "isExist"});
		$("#chemiChildId").val('');
		$("#chemiParameterNo").val('');
		$('#chemiParameterId').val("").trigger('change.select2');
		$("#chemiSpecification").val('');
		chemiTextarea.style.height = "30px";
		$('#chemiReferenceId').val("").trigger('change.select2');
		$('#chemiMethodId').val("").trigger('change.select2');
		$('#chemiTestUnitId').val("").trigger('change.select2');
		$("#chemiLod").val('');
		$("#chemiSampleAmount").val('');
		$('#chemiUnitId').val("").trigger('change.select2');
		chemiSampleCalculation();
		$(".md").prop("disabled", false); 
				
		} 
		
		}else{
			sweetAlert("Warning!", "Please Select Chemical Checkbox.", "warning", 1000, false);	
			
		}
}

function editRowChe(el) {
	var chdId				= $(el).closest("tr").find("#i_child_id").val();
	var chemiParameterNo 	= $(el).closest("tr").find("#i_chemiParameterNo").val();
	var chemiParameterId 	= $(el).closest("tr").find("#i_chemiParameterId").val();
	var chemiSpecification 	= $(el).closest("tr").find("#i_chemiSpecification").val();
	var chemiReferenceId 	= $(el).closest("tr").find("#i_chemiReferenceId").val();
	var chemiMethodId 		= $(el).closest("tr").find("#i_chemiMethodId").val();
	var chemiTestUnitId 	= $(el).closest("tr").find("#i_chemiTestUnitId").val();
	var chemiLod	= $(el).closest("tr").find("#i_chemiLod").val();
	var chemiSampleAmount	= $(el).closest("tr").find("#i_chemiSampleAmount").val();
	var chemiUnitId 		= $(el).closest("tr").find("#i_chemiUnitId").val();
	
	$("#chemiChildId").val(chdId);
	$("#chemiParameterNo").val(chemiParameterNo);
	$('#chemiParameterId').val(chemiParameterId).trigger('change.select2');
	$("#chemiSpecification").val(chemiSpecification);
	$('#chemiReferenceId').val(chemiReferenceId).trigger('change.select2');
	$('#chemiMethodId').val(chemiMethodId).trigger('change.select2');
	$('#chemiTestUnitId').val(chemiTestUnitId).trigger('change.select2');
	$("#chemiLod").val(chemiLod);
	$("#chemiSampleAmount").val(chemiSampleAmount);
	$("#chemiUnitId").val(chemiUnitId).trigger('change.select2');
	chemiSampleReduCal(chemiSampleAmount);
	
	var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
    getTableRows();
}

function getTableRows(){
	var chemiVal='0';
	var microVal='0';
	
	if (!$("#chemicTable tbody tr.isExist").length){
		chemiVal='0';
		$("#chemicalSampleAmt").val('');
		$("#chemicalRetentionAmt").val('');
		$("#chemicalTotal").val('');
		
	}else{
		chemiVal='1';	
		
	}
	if (!$("#microTable tbody tr.isExist").length){
		microVal='0';
		$("#microSampleAmt").val('');
		$("#microRetentionAmt").val('');
		$("#microTotal").val('');
		
	}else{
		microVal='1';	
		
	}
	if(chemiVal=='0' && microVal=='0'){
		
		$("#chemicalSampleAmt").val('');
		$("#chemicalRetentionAmt").val('');
		$("#chemicalTotal").val('');
		$("#microSampleAmt").val('');
		$("#microRetentionAmt").val('');
		$("#microTotal").val('');
		$("#totalAmt").val('');
		$("#unitId").val('');
		$("#unitLbl").text('');
	}
	
	
	
}
function chemiSampleCalculation(){
	var sampleTotal = 0;
 	$('.chemiSampleAmtClass').each(function(){
 		sampleTotal += Number($(this).val());
    });
 	$('#chemicalSampleAmt').val(sampleTotal.toFixed(2));
	
 	chemiTotalCalculation();
}

function chemiSampleReduCal(amtVal){
	var chemicalSampleAmt=parseFloat($('#chemicalSampleAmt').val()).toFixed(2);
	var sampleTotal = parseFloat(chemicalSampleAmt) - parseFloat(amtVal);
	$('#chemicalSampleAmt').val(sampleTotal.toFixed(2));
	chemiTotalCalculation();
}

function microSampleReduCal(amtVal){
	var microSampleAmt=parseFloat($('#microSampleAmt').val()).toFixed(2);
	var sampleTotal = parseFloat(microSampleAmt) - parseFloat(amtVal);
	$('#microSampleAmt').val(sampleTotal.toFixed(2));
	microTotalCalculation();
}

function chemiTotalCalculation(){
	var chemicalSampleAmt=parseFloat($('#chemicalSampleAmt').val()).toFixed(2); 
    var chemicalRetentionAmt= parseFloat($('#chemicalRetentionAmt').val()).toFixed(2); 
	
	 if(isNaN(chemicalSampleAmt)){
		 chemicalSampleAmt=0;
	   }
      if(isNaN(chemicalRetentionAmt)){
    	  chemicalRetentionAmt=0;
        }
		
		  var totalAmt=parseFloat(chemicalRetentionAmt)+parseFloat(chemicalSampleAmt);
		  $('#chemicalTotal').val(parseFloat(totalAmt).toFixed(2));
		  totalAmount();
}
function microSampleCalculation(){
	var sampleTotal = 0;
 	$('.microSampleAmtClass').each(function(){
 		sampleTotal += Number($(this).val());
    });
 	$('#microSampleAmt').val(sampleTotal.toFixed(2));
	
 	microTotalCalculation();
}

function microTotalCalculation(){
	var microSampleAmt=parseFloat($('#microSampleAmt').val()).toFixed(2); 
    var microRetentionAmt= parseFloat($('#microRetentionAmt').val()).toFixed(2); 
	
	 if(isNaN(microSampleAmt)){
		 microSampleAmt=0;
	   }
      if(isNaN(microRetentionAmt)){
    	  microRetentionAmt=0;
        }
		
		  var totalAmt=parseFloat(microRetentionAmt)+parseFloat(microSampleAmt);
		  $('#microTotal').val(parseFloat(totalAmt).toFixed(2));
		  totalAmount();
}

function totalAmount(){
	var chemicalTotal=parseFloat($('#chemicalTotal').val()).toFixed(2); 
    var microTotal= parseFloat($('#microTotal').val()).toFixed(2); 
	
	 if(isNaN(chemicalTotal)){
		 chemicalTotal=0;
	   }
      if(isNaN(microTotal)){
    	  microTotal=0;
        }
		
		  var totalAmt=parseFloat(chemicalTotal)+parseFloat(microTotal);
		  $('#totalAmt').val(parseFloat(totalAmt).toFixed(2));
}

function addRowMicro(el){
	var isMicro=$('#micro_status').prop('checked');
	
	if(isMicro==true){
	var unitId				=$("#unitId").val();
	var id					= $("#id").val();
	var chdId				= $("#microChildId").val();
	var microParameterNo 	= $("#microParameterNo").val();
	var microParameterId 	= $("#microParameterId").val();
	var microParameterNm 	= $('#microParameterId option:selected').text();
    var microSpecification 	= $("#microSpecification").val();
	var microReferenceId 	= $("#microReferenceId").val();
	var microReferenceNm 	= $('#microReferenceId option:selected').text();
	var microMethodId 		= $("#microMethodId").val();
	var microMethodNm 		= $('#microMethodId option:selected').text();
	var microTestUnitId 	= $("#microTestUnitId").val();
	var microTestUnitNm 	= $('#microTestUnitId option:selected').text();
	var microLod 	= $("#microLod").val();
	var microSampleAmount 	= $("#microSampleAmount").val();
	var microUnitId 		= $("#microUnitId").val();
	var microUnitNm 		= $('#microUnitId option:selected').text();
	if(chdId == ""){
		chdId="-1";
	} 

	var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="r_child_id"  name="microChildId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="r_microParameterNo" name="microParameterNo[]"  value="'+microParameterNo+'"/>'
			+ '<input type="hidden" id="r_microParameterId"  name="microParameterId[]" value="'+microParameterId+'"/>'
			+ '<input type="hidden" id="r_microSpecification" name="microSpecification[]" value="'+microSpecification+'"/>'
			+ '<input type="hidden" id="r_microReferenceId"  name="microReferenceId[]" value="'+microReferenceId+'"/>'
			+ '<input type="hidden" id="r_microMethodId" name="microMethodId[]" value="'+microMethodId+'"/>'
			+ '<input type="hidden" id="r_microTestUnitId" name="microTestUnitId[]" value="'+microTestUnitId+'"/>'
			+ '<input type="hidden" id="r_microLod" name="microLod[]" class="microLodClass" value="'+microLod+'"/>'
			+ '<input type="hidden" id="r_microSampleAmount" name="microSampleAmount[]" class="microSampleAmtClass" value="'+microSampleAmount+'"/>'
			+ '<input type="hidden" id="r_microUnitId" name="microUnitId[]" class="hasUnitId" value="'+microUnitId+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRowMic(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			if(unitId.length == 0 ){
				$("#unitId").val(microUnitId);
				$("#unitLbl").text(microUnitNm);
				
			}else{
				if(unitId !=microUnitId){
					apendRow = false;
					sweetAlert("Warning!", "Please Select Same Unit", "warning", 1000, false);	
					
				}	
				
			}
			
			if (apendRow == true) {
			
			var rowNode = bTable.row.add([microParameterNo, microParameterNm,microSpecification,microReferenceNm, microMethodNm, microTestUnitNm,microLod,microSampleAmount,microUnitNm, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			bTable.draw();
			bTable.page('last').draw(false);
			//$(rowNode).find("td:eq(1)").css({"class": "isExist"});
			$("#microChildId").val('');
			$("#microParameterNo").val('');
			$('#microParameterId').val("").trigger('change.select2');
			$("#microSpecification").val('');
			microTextarea.style.height = "30px";
			$('#microReferenceId').val("").trigger('change.select2');
			$('#microMethodId').val("").trigger('change.select2');
			$('#microTestUnitId').val("").trigger('change.select2');
			$("#microLod").val('');
			$("#microSampleAmount").val('');
			$('#microUnitId').val("").trigger('change.select2');
			microSampleCalculation();
			$(".md").prop("disabled", false); 
		
			} 
			
	}else{
		sweetAlert("Warning!", "Please Select Microbiological Checkbox.", "warning", 1000, false);
		
	}
}

function editRowMic(el) {
	var chdId				= $(el).closest("tr").find("#r_child_id").val();
	var microParameterNo 	= $(el).closest("tr").find("#r_microParameterNo").val();
	var microParameterId 	= $(el).closest("tr").find("#r_microParameterId").val();
	var microSpecification 	= $(el).closest("tr").find("#r_microSpecification").val();
	var microReferenceId 	= $(el).closest("tr").find("#r_microReferenceId").val();
	var microMethodId 		= $(el).closest("tr").find("#r_microMethodId").val();
	var microTestUnitId 	= $(el).closest("tr").find("#r_microTestUnitId").val();
	var microLod	= $(el).closest("tr").find("#r_microLod").val();
	var microSampleAmount	= $(el).closest("tr").find("#r_microSampleAmount").val();
	var microUnitId 		= $(el).closest("tr").find("#r_microUnitId").val();
	$("#microChildId").val(chdId);
	$("#microParameterNo").val(microParameterNo);
	$('#microParameterId').val(microParameterId).trigger('change.select2');
	$("#microSpecification").val(microSpecification);
	$('#microReferenceId').val(microReferenceId).trigger('change.select2');
	$('#microMethodId').val(microMethodId).trigger('change.select2');
	$('#microTestUnitId').val(microTestUnitId).trigger('change.select2');
	$("#microLod").val(microSampleAmount);
	$("#microSampleAmount").val(microSampleAmount);
	$('#microUnitId').val(microUnitId).trigger('change.select2');
	microSampleReduCal(microSampleAmount);
	
	var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
    getTableRows();
}

function add(el) {
	$("#id").val("");
	$("#materialName").val("");
	$("#materialCode").val("");
	$('#materialTypeId').val("").trigger('change.select2');
	$("#storageConId").val("").trigger('change.select2');
	$("#storageCondition").val("");
	$("#matSamProcedure").val("").trigger('change.select2');
	$('#matMethodId').val("").trigger('change.select2');
	$("#chemiParameter_id").val("");
	$("#chemiParameter_name").val("");
	$("#microParameter_id").val("");
	$("#microParameter_name").val("");
	$('#chemical_status').prop('checked', false);
	$("#isChemical").val("N");
	$('#micro_status').prop('checked', false);
	$("#isMicrobiological").val("N");
	$("#chemicalSampleAmt").val("");
	$("#chemicalRetentionAmt").val("");
	$("#chemicalTotal").val("");
	$("#microSampleAmt").val("");
	$("#microRetentionAmt").val("");
	$("#microTotal").val("");
	$("#totalAmt").val("");
	$("#unitId").val("");
	$("#unitLbl").text("");
	$("#chemicTable").find("tr:gt(1)").remove();
	$("#microTable").find("tr:gt(1)").remove();
	//aTable.rows().remove().draw();
	//bTable.rows().remove().draw();
	
    $("#materialInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var id = $(el).closest("tr").find("#m_id").val();
	var materialName 	= $(el).closest("tr").find("#m_materialName").val();
	var materialCode 	= $(el).closest("tr").find("#m_materialCode").val();
	var materialTypeId 	= $(el).closest("tr").find("#m_materialTypeId").val();
	var isChemical 	= $(el).closest("tr").find("#m_isChemical").val();
	var isMicrobiological 	= $(el).closest("tr").find("#m_isMicrobiological").val();
	var chemicalSampleAmt 		= $(el).closest("tr").find("#m_chemicalSampleAmt").val();
	var chemicalRetentionAmt 	= $(el).closest('tr').find("#m_chemicalRetentionAmt").val();
	var chemicalTotal 		= $(el).closest("tr").find("#m_chemicalTotal").val();
	var microSampleAmt 		= $(el).closest("tr").find("#m_microSampleAmt").val();
	var microRetentionAmt 	= $(el).closest('tr').find("#m_microRetentionAmt").val();
	var microTotal 		= $(el).closest("tr").find("#m_microTotal").val();
	var totalAmt 		= $(el).closest("tr").find("#m_totalAmt").val();
	var unitId 		    = $(el).closest("tr").find("#m_unitId").val();
	var unitName 		= $(el).closest("tr").find("#m_unitName").val();
	var Status 			= $(el).closest('tr').find("#m_status").val();
	var storageConId 	= $(el).closest("tr").find("#m_storageConId").val();
	var storageCondition = $(el).closest("tr").find("#m_storageCondition").val();
	var matSamProcedure  = $(el).closest("tr").find("#m_matSamProcedure").val();
	var matMethodId 	= $(el).closest("tr").find("#m_matMethodId").val();
	
	$("#id").val(id);
	$("#materialName").val(materialName);
	$("#materialCode").val(materialCode);
	$('#materialTypeId').val(materialTypeId).trigger('change.select2');
	$("#storageConId").val(storageConId).trigger('change.select2');
	$("#storageCondition").val(storageCondition);
	$("#matSamProcedure").val(matSamProcedure).trigger('change.select2');
	$('#matMethodId').val(matMethodId).trigger('change.select2');
	if(isChemical=="Y"){
		$('#chemical_status').prop('checked', true);
		$("#isChemical").val("Y");	
		
	}else{
		$('#chemical_status').prop('checked', false);
		$("#isChemical").val("N");	
		
	}
	if(isMicrobiological=="Y"){
		$('#micro_status').prop('checked', true);
		$("#isMicrobiological").val("Y");	
		
	}else{
		$('#micro_status').prop('checked', false);
		$("#isMicrobiological").val("N");
	}
	
	$("#chemicalSampleAmt").val(chemicalSampleAmt);
	$("#chemicalRetentionAmt").val(chemicalRetentionAmt);
	$("#chemicalTotal").val(chemicalTotal);
	$("#microSampleAmt").val(microSampleAmt);
	$("#microRetentionAmt").val(microRetentionAmt);
	$("#microTotal").val(microTotal);
	$("#totalAmt").val(totalAmt);
	$("#unitId").val(unitId);
	$("#unitLbl").text(unitName);
	
	if(Status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#materialInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    getChemicalDetailsInfo(id);
    getMicrobiologicalDetailsInfo(id); 
}
function getChemicalDetailsInfo(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/material/getMaterialChemicalInfos?materialId=" + id,
		dataType : 'json',
		success : function(data) {
			
			var requestList = data.length;
			 if (requestList > 0) {
				 chemicalDetailsInfoList(data);
			} 
		}
	});
}
function chemicalDetailsInfoList(data) {
	
	for (var i = 0; i < data.length; i++) {
		var id=data[i].chemiMaterialId || "";
		var chdId=data[i].chemiChildId || "";
		var chemiParameterNo = data[i].chemiParameterNo || "";
		var chemiParameterId = data[i].chemiParameterId || "";
		var chemiParameterNm = data[i].chemiParameterName || "";
	    var chemiSpecification =data[i].chemiSpecification || "";
		var chemiReferenceId = data[i].chemiReferenceId || "";
		var chemiReferenceNm = data[i].chemiReferenceName || "";
		var chemiMethodId = data[i].chemiMethodId || "";
		var chemiMethodNm = data[i].chemiMethodName || "";
		var chemiTestUnitId = data[i].chemiTestUnitId || "";
		var testUnitName=data[i].testUnitName || "";
		var chemiLod = data[i].chemiLod || "";
		var chemiSampleAmount = data[i].chemiSampleAmount || "";
		var chemiUnitId = data[i].chemiUnitId || ""; 
		var chemiUnitNm=data[i].unitName || "";
		var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_child_id"  name="chemiChildId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="i_chemiParameterNo" name="chemiParameterNo[]"  value="'+chemiParameterNo+'"/>'
			+ '<input type="hidden" id="i_chemiParameterId"  name="chemiParameterId[]" value="'+chemiParameterId+'"/>'
			+ '<input type="hidden" id="i_chemiSpecification" name="chemiSpecification[]" value="'+chemiSpecification+'"/>'
			+ '<input type="hidden" id="i_chemiReferenceId"  name="chemiReferenceId[]" value="'+chemiReferenceId+'"/>'
			+ '<input type="hidden" id="i_chemiMethodId" name="chemiMethodId[]" value="'+chemiMethodId+'"/>'
			+ '<input type="hidden" id="i_chemiTestUnitId" name="chemiTestUnitId[]" value="'+chemiTestUnitId+'"/>'
			+ '<input type="hidden" id="i_chemiLod" name="chemiLod[]" class="chemiLodClass" value="'+chemiLod+'"/>'
			+ '<input type="hidden" id="i_chemiSampleAmount" name="chemiSampleAmount[]" class="chemiSampleAmtClass" value="'+chemiSampleAmount+'"/>'
			+ '<input type="hidden" id="i_chemiUnitId" name="chemiUnitId[]" class="hasUnitId" value="'+chemiUnitId+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRowChe(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			
			if (apendRow == true) {
	
			var rowNode = aTable.row.add([chemiParameterNo, chemiParameterNm,chemiSpecification,chemiReferenceNm, chemiMethodNm, testUnitName,chemiLod,chemiSampleAmount,chemiUnitNm, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			
			aTable.draw();
		
		    aTable.page('last').draw(false);
		
		
	}
}
}

function getMicrobiologicalDetailsInfo(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/material/getMaterialMicrobiologicalInfos?materialId=" + id,
		dataType : 'json',
		success : function(data) {
			var requestList = data.length;
			 if (requestList > 0) {
				 microbiologicalDetailsInfoList(data);
			} 
		}
	});
}
function microbiologicalDetailsInfoList(data) {
	
	for (var i = 0; i < data.length; i++) {
		
		var id = data[i].microMaterialId || "";
		var chdId=data[i].microChildId || "";
		var microParameterNo = data[i].microParameterNo || "";
		var microParameterId = data[i].microParameterId || "";
		var microParameterNm = data[i].microParameterName || "";
	    var microSpecification = data[i].microSpecification || "";
		var microReferenceId = data[i].microReferenceId || "";
		var microReferenceNm = data[i].microReferenceName || "";
		var microMethodId = data[i].microMethodId || "";
		var microMethodNm = data[i].microMethodName || "";
		var microTestUnitId = data[i].microTestUnitId || "";
		var microTestUnitNm = data[i].testUnitName || "";
		var microLod = data[i].microLod || "";
		var microSampleAmount = data[i].microSampleAmount || "";
		var microUnitId = data[i].microUnitId || "";
		var microUnitNm = data[i].unitName || "";
		
		
		var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="r_child_id"  name="microChildId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="r_microParameterNo" name="microParameterNo[]"  value="'+microParameterNo+'"/>'
			+ '<input type="hidden" id="r_microParameterId"  name="microParameterId[]" value="'+microParameterId+'"/>'
			+ '<input type="hidden" id="r_microSpecification" name="microSpecification[]" value="'+microSpecification+'"/>'
			+ '<input type="hidden" id="r_microReferenceId"  name="microReferenceId[]" value="'+microReferenceId+'"/>'
			+ '<input type="hidden" id="r_microMethodId" name="microMethodId[]" value="'+microMethodId+'"/>'
			+ '<input type="hidden" id="r_microTestUnitId" name="microTestUnitId[]" value="'+microTestUnitId+'"/>'
			+ '<input type="hidden" id="r_microLod" name="microLod[]" class="microLodClass" value="'+microLod+'"/>'
			+ '<input type="hidden" id="r_microSampleAmount" name="microSampleAmount[]" class="microSampleAmtClass" value="'+microSampleAmount+'"/>'
			+ '<input type="hidden" id="r_microUnitId" name="microUnitId[]" class="hasUnitId" value="'+microUnitId+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRowMic(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			
			if (apendRow == true) {
	
			var rowNode = bTable.row.add([microParameterNo, microParameterNm,microSpecification,microReferenceNm, microMethodNm, microTestUnitNm,microLod,microSampleAmount,microUnitNm, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			bTable.draw();
			bTable.page('last').draw(false);
			
		
	}
	}
}

$("#materialInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#materialInfoForm").serialize();
    swal({
        title: "Are you sure?",
        text: "Yes, submit this form!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, submit this form!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/material/save-material-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#materialInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "fail", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 0, false);
        }
        });
});

function view(el) {
	var id = $(el).closest("tr").find("#m_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/material/getMaterialDetailsInfo/" + id ,
		success : function(data) {
			$('#materialViewModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			}
		});
}

function materialInfoPrint(id,sampleNo) {
	var ajaxURL = "${pageContext.request.contextPath}/material/material-report?requestId="+id+'&sampleNo='+sampleNo;
	
	 $.ajax({
		type : "GET",
		url : ajaxURL,
		dataType : 'json',
		success : function(data) {
			var path = "${pageContext.request.contextPath}/report/" + data;
			window.open(path, '_blank');
			
		}
	}); 
}

</script>