<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<style>
/* .modal-header {
    padding: 5px !important;
    } */
</style>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">EQUIPMENT INFO</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/equipment/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/equipment/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="addEquipment(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="countryList">
						<thead>
							<tr>
								<th class="align-left" style="width: 120px;">EQUIPMNET ID</th>
								<th class="align-left">NAME OF EQUIPMNET</th>
								<th class="align-left">MODEL</th>
								<th class="align-left" style="width: 160px;">SOURCE/MANUFACTURE</th>
								<th class="align-left">CURRENT LOCATION</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${equipInfos}">
							<tr>
								<td class="align-left">${info.equipmentId}</td>
								<td class="align-left">${info.equipmentName}</td>
								<td class="align-left">${info.modelNo}</td>
								<td class="align-left">${info.sourceManufac}</td>
								<td class="align-left">${info.currLocation}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="s_equipmentId" value="${info.equipmentId}">
									<input type="hidden" id="s_equipmentName" value="${info.equipmentName}">
									<input type="hidden" id="s_equipmentType" value="${info.equipmentType}">
									<input type="hidden" id="s_brand" value="${info.brand}">
									<input type="hidden" id="s_modelNo" value="${info.modelNo}">
									<input type="hidden" id="s_capacity" value="${info.capacity}">
									<input type="hidden" id="s_sourceManufac" value="${info.sourceManufac}">
									<input type="hidden" id="s_localAgentName" value="${info.localAgentName}">
									<input type="hidden" id="s_currLocation" value="${info.currLocation}">
									<input type="hidden" id="s_lcModelNo" value="${info.lcModelNo}">
									<input type="hidden" id="s_serialNo" value="${info.serialNo}">
									<input type="hidden" id="s_msModelNo" value="${info.msModelNo}">
									<input type="hidden" id="s_msSerialNo" value="${info.msSerialNo}">
									<input type="hidden" id="s_manufacturingDate" value="${info.manufacturingDate}">
									<input type="hidden" id="s_pumpModelNo" value="${info.pumpModelNo}">
									<input type="hidden" id="s_detectorModelNo" value="${info.detectorModelNo}">
									<input type="hidden" id="s_softwareName" value="${info.softwareName}">
									<input type="hidden" id="s_softwareVersionNo" value="${info.softwareVersionNo}">
									<input type="hidden" id="s_softwareFirmwareNo" value="${info.softwareFirmwareNo}">
									<input type="hidden" id="s_electricPower" value="${info.electricPower}">
									<input type="hidden" id="s_nitrogenConsumption" value="${info.nitrogenConsumption}">
									<input type="hidden" id="s_calibrationDate" value="${info.calibrationDate}">
									<input type="hidden" id="s_nextCalibrationDate" value="${info.nextCalibrationDate}">
									<input type="hidden" id="s_installQualificDate" value="${info.installQualificDate}">
									<input type="hidden" id="s_operationQualificDate" value="${info.operationQualificDate}">
									<input type="hidden" id="s_performanceQualificDate" value="${info.performanceQualificDate}">
									<input type="hidden" id="s_qualiBy" value="${info.qualiBy}">
									<input type="hidden" id="s_acceptCriteria" value="${info.acceptCriteria}">
									<input type="hidden" id="s_calibrationInterval" value="${info.calibrationInterval}">
									<input type="hidden" id="s_evidenceVerification" value="${info.evidenceVerification}">
									<input type="hidden" id="s_adjustment" value="${info.adjustment}">
									<input type="hidden" id="s_referenceMaterial" value="${info.referenceMaterial}">
									<input type="hidden" id="s_resultCalibration" value="${info.resultCalibration}">
									
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									<a class="btn-edit btn btn-xs" onclick="equipmentInfoPrint(this)"><i class="material-icons">print</i></a>
									<%-- <a class="btn-edit btn btn-xs" onclick="whRequestInfoPrint('${info.id}','${info.udWhReqNo}')"><i class="material-icons">print</i></a> --%>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="equipmentEntryModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 <table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Equipment Information</td>
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
                <form method="post" id="equipmentInfoForm" enctype="multipart/form-data" onkeypress="if (event.keyCode == 13) { return false; }">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h1 class="panel-title">General Information</h1>
							</div>

							<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<span><b>EQUIPMENT ID :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="equipment_id" name="equipmentId" value="" class="form-control" required="required"  autocomplete="off">
									</div>
								</div>
								<div class="col-md-4">
									<span><b>NAME OF EQUIPMENT :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="equipmentName" name="equipmentName" value="" class="form-control" required="required" autocomplete="off">
									</div>
								</div>
								<div class="col-md-4">
									<span><b>EQUIPMENT TYPE :</b> <span style="color:red">*</span></span>
									<div class="form-group">
		                                <select id="equipmentType" name="equipmentType" class="js-example-theme-single form-control" required="required"  style="width: 100%;">
					                        	<option></option>
					                        	<option value="1">Analyzer</option>
					                        	<option value="2">Monitoring </option>
					                        	<option value="3">Sampling</option>
					                        	<option value="4">Storage</option>
												<option value="5">Cleaning</option>
					                        
					                	</select>
	                            	</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 m-t--10">
									<span><b>CAPACITY (IF HAVE) :</b></span>
									<div class="form-group">
										<input type="text" id="capacity" name="capacity" value="" class="form-control" autocomplete="off">
									</div>
								</div>
								<div class="col-md-4 m-t--10">
									<span><b>BRAND :</b></span>
									<div class="form-group">
										<input type="text" id="brand" name="brand" value="" class="form-control">
									</div>
								</div>
								<div class="col-md-4 m-t--10">
									<span><b>MODEL NO :</b></span>
									<div class="form-group">
										<input type="text" id="modelNo" name="modelNo" value="" class="form-control"  autocomplete="off">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 m-t--10">
									<span><b> SOURCE/ MANUFACTURER :</b></span>
									<div class="form-group">
										<input type="text" id="sourceManufac" name="sourceManufac" value="" class="form-control" autocomplete="off">
									</div>
								</div>
								<div class="col-md-4 m-t--10">
									<span><b>LOCAL AGENT NAME :</b></span>
									<div class="form-group">
										<input type="text" id="localAgentName" name="localAgentName" value="" class="form-control" autocomplete="off">
									</div>
								</div>
								<div class="col-md-4 m-t--10">
									<span><b>CURRENT LOCATION :</b> <span style="color:red">*</span></span>
									<div class="form-group">
										<input type="text" id="currLocation" name="currLocation" value="" class="form-control" required="required" autocomplete="off" >
									</div>
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
											<div class="form-group">
												<input type="text" id="serialNo" name="serialNo" value="" class="form-control"   autocomplete="off">
											</div>
										</div>
										<div class="col-md-3">
											<span><b>L/C MODEL NO :</b></span>
											<div class="form-group">
												<input type="text" id="lcModelNo" name="lcModelNo" value="" class="form-control" autocomplete="off">
											</div>
										</div>
										<div class="col-md-3">
											<span><b>MS MODEL NO :</b></span>

											<div class="form-group">
												<input type="text" id="msModelNo" name="msModelNo" value="" class="form-control" autocomplete="off">

											</div>
										</div>
										<div class="col-md-3">
											<span><b>SERIAL NO :</b></span>
											<div class="form-group">
												<input type="text" id="msSerialNo" name="msSerialNo" value="" class="form-control" autocomplete="off">

											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-3">
											<span><b> MANUFACTURING DATE :</b></span>
											<div class="form-group">
												<input type="text" id="manufacturingDate" name="manufacturingDate" value="" 
												 class="form-control dates" style="width: 40%;"  autocomplete="off" >
											</div>
										</div>
										<div class="col-md-3">
											<span><b>PUMP MODEL NO. :</b></span>
											<div class="form-group">
												<input type="text" id="pumpModelNo" name="pumpModelNo" value="" 
												 class="form-control" placeholder="Pump model no" autocomplete="off" >
											</div>
										</div>
										<div class="col-md-3">
											<span><b>DETECTOR MODEL NO. :</b></span>
											<div class="form-group">
												<input type="text" id="detectorModelNo" name="detectorModelNo" value="" class="form-control">
											</div>
										</div>
										<div class="col-md-3">
											<span><b>SOFTWARE NAME :</b></span>
											<div class="form-group">
												<input type="text" id="softwareName" name="softwareName" value="" class="form-control">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<span><b> SOFTWARE VERSION NO :</b></span>
											<div class="form-group">
												<input type="text" id="softwareVersionNo" name="softwareVersionNo" value="" class="form-control" autocomplete="off" >
											</div>
										</div>
										<div class="col-md-3">
											<span><b>SOFTWARE FIRMWARE NO. :</b></span>
											<div class="form-group">
												<input type="text" id="softwareFirmwareNo" name="softwareFirmwareNo" value="" class="form-control"  autocomplete="off" >
											</div>
										</div>
										<div class="col-md-3">
											<span><b>ELECTRIC POWER :</b></span>
											<div class="form-group">
												<input type="text" id="electricPower" name="electricPower" value="" class="form-control number">
											</div>
										</div>
										<div class="col-md-3">
											<span><b>NITROGEN CONSUMPTION :</b></span>
											<div class="form-group">
												<input type="text" id="nitrogenConsumption" name="nitrogenConsumption" value="" class="form-control number">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4 class="panel-title">Qualification Status</h4>
							</div>
							<div id="collapseOne_1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne_1" aria-expanded="true" style="">
								<div class="panel-body">
									
									<div class="row">
										<div class="col-md-6">
										<span><b>REFERENCE MATERIAL :</b></span>
											<div class="form-group">
												<!-- <input type="text" id="referenceMaterial" name="referenceMaterial" value="" class="form-control" autocomplete="off"> -->
												<textarea rows="2" id="referenceMaterial" name="referenceMaterial" class="form-control" placeholder="Reference Material description goes here......."></textarea>
											</div>
										</div>
										<div class="col-md-6">
										<span><b>ADJUSTMENT :</b></span>
											<div class="form-group">
												<!-- <input type="text" id="adjustment" name="adjustment" value="" class="form-control" autocomplete="off"> -->
												<textarea rows="2" id="adjustment" name="adjustment" class="form-control" placeholder="Adjustment description goes here......."></textarea>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<span><b>ACCEPTANCE CRITERIA :</b></span>
											<div class="form-group">
												<textarea rows="2" id="accept_criteria" name="acceptCriteria" class="form-control" placeholder="Acceptance Criteria description goes here......."></textarea>
												<!-- <input type="text" id="accept_criteria" name="acceptCriteria" value="" class="form-control" style="width: 100%;" autocomplete="off"> -->
											</div>
										</div>
										<div class="col-md-6">
										<span><b>EVIDENCE OF VERIFICATION :</b></span>
											<div class="form-group">
												<!-- <input type="text" id="evidenceVerification" name="evidenceVerification" value="" class="form-control" autocomplete="off"> -->
												<textarea rows="2" id="evidenceVerification" name="evidenceVerification" class="form-control" placeholder="Evidence of Verification description goes here......."></textarea>
											</div>
										</div>
										
									</div>
									<div class="row">
										<div class="col-md-3">
											<span><b>CALIBRATION DATE :</b></span>
											<div class="form-group">
												<input type="text" id="calibrationDate" name="calibrationDate" value="" style="width: 50%;" class="form-control dates input-sm"  autocomplete="off">
											</div>
										</div>
										<div class="col-md-3">
											<span><b>NEXT CALIBRATION DATE :</b></span>
											<div class="form-group">
												<input type="text" id="nextCalibrationDate" name="nextCalibrationDate" value=""
													class="form-control dates" style="width: 50%;"  autocomplete="off">
											</div>
										</div>
										<div class="col-md-3">
										<span><b>CALIBRATION INTERVAL :</b></span>
											<div class="form-group">
												<input type="text" id="calibrationInterval" name="calibrationInterval" value="" class="form-control" style="width: 50%;" autocomplete="off">
											</div>
										</div>
										<div class="col-md-3">
										<span><b>RESULT OF CALIBRATION :</b></span>
											<div class="form-group">
												<!-- <input type="text" id="resultCalibration" name="resultCalibration" value="" class="form-control" autocomplete="off"> -->
											<div class="input-group">
												<span class="input-group-btn">
													<span class="btn btn-default btn-file">
														Browse… <input type="file" id="img_calibration" name="img_calibration" value="" onchange="readURL(this);">
													</span>
												</span>
												<input type="text" id="calibration_image" name="resultCalibration" class="form-control" readonly="readonly">
												<input type="hidden" id="e_image" name="e_image">
											</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<span><b>OPERATIONAL QUALIFICATION <br>DATE :</b></span>
											<div class="form-group">
												<input type="text" id="operationQualificDate" name="operationQualificDate" value=""
													class="form-control dates" style="width: 50%;" autocomplete="off" >
											</div>
										</div>
										<div class="col-md-3">
											<span><b>PERFORMANCE QUALIFICATION <br>DATE :</b></span>
											<div class="form-group">
												<input type="text" id="performanceQualificDate" name="performanceQualificDate" value=""
													class="form-control dates" style="width: 50%;"  autocomplete="off" >
											</div>
										</div>
										<div class="col-md-3">
											<span><b>INSTALLATION QUALIFICATION <br>DATE :</b></span>
											<div class="form-group">
												<input type="text" id="installQualificDate" name="installQualificDate"
													value="" style="width: 50%;" class="form-control dates" autocomplete="off">
											</div>
										</div>
										<div class="col-md-3">
											<span><b>QUALIFICATION<br>DONE BY :</b></span>
											<div class="form-group">
												<input type="text" id="qualiBy" name="qualiBy" value="" class="form-control">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
	                <div class="modal-footer" style="background-color: #cff0f5;">
	                	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i>
							<span>SAVE</span>
						</button>
	                </div>
                 </form>
             </div>
         </div>
     </div>
     
     <div class="modal fade" id="equipmentViewModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
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
        reader.readAsDataURL(input.files[0]);
    }
}

$( function() {
    $( ".dates" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
   
});
$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search from list.."
});

$( function() {
	 $('#equipment_id').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
});

function view(el) {
	
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/equipment/getEquipmentInfoById/" + id ,
		success : function(data) {
			$('#equipmentViewModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			
			
			}
		});
}


function addEquipment(el) {
	$("#id").val("");
	$("#equipment_id").val("");
	$("#equipmentName").val("");
	$("#equipmentType").val("").trigger('change.select2');
	$("#brand").val("");
	$("#modelNo").val("");
	$("#capacity").val("");
	$("#sourceManufac").val("");
	$("#localAgentName").val("");
	$("#currLocation").val("");
	$("#lcModelNo").val("");
	$("#serialNo").val("");
	$("#msModelNo").val("");
	$("#msSerialNo").val("");
	$("#manufacturingDate").datepicker("setDate", '');
	$("#pumpModelNo").val("");
	$("#detectorModelNo").val("");
	$("#softwareName").val("");
	$("#softwareVersionNo").val("");
	$("#softwareFirmwareNo").val("");
	$("#electricPower").val("");
	$("#nitrogenConsumption").val("");
	$("#calibrationDate").datepicker("setDate", '');
	$("#nextCalibrationDate").datepicker("setDate", '');
	$("#installQualificDate").datepicker("setDate", '');
	$("#operationQualificDate").datepicker("setDate", '');
	$("#performanceQualificDate").datepicker("setDate", '');
	$("#qualiBy").val("");
	$("#accept_criteria").val("");
	$("#calibrationInterval").val("");
	$("#evidenceVerification").val("");
	$("#adjustment").val("");
	$("#referenceMaterial").val("");
	$("#img_calibration").attr('src', "");
	$("#calibration_image").val("");
	$("#e_image").val("");
	
	$("#remarks_detail").val("");
	$("#remarks").val("");
    $("#equipmentEntryModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id = $(el).closest("tr").find("#row_id").val();
	var equipmentId=  $(el).closest("tr").find("#s_equipmentId").val();
	var equipmentName =  $(el).closest("tr").find("#s_equipmentName").val();
	var equipmentType =  $(el).closest("tr").find("#s_equipmentType").val();
	var brand =  $(el).closest("tr").find("#s_brand").val();
	var modelNo= $(el).closest("tr").find("#s_modelNo").val();
	var capacity=  $(el).closest("tr").find("#s_capacity").val();
	var sourceManufac=  $(el).closest("tr").find("#s_sourceManufac").val();
	var localAgentName= $(el).closest("tr").find("#s_localAgentName").val();
	var currLocation=  $(el).closest("tr").find("#s_currLocation").val();
	var lcModelNo= $(el).closest("tr").find("#s_lcModelNo").val();
	var serialNo= $(el).closest("tr").find("#s_serialNo").val();
	var msModelNo= $(el).closest("tr").find("#s_msModelNo").val();
	var msSerialNo= $(el).closest("tr").find("#s_msSerialNo").val();
	var manufacturingDate=  $(el).closest("tr").find("#s_manufacturingDate").val();
	var pumpModelNo= $(el).closest("tr").find("#s_pumpModelNo").val();
	var detectorModelNo= $(el).closest("tr").find("#s_detectorModelNo").val();
	var softwareName= $(el).closest("tr").find("#s_softwareName").val();
	var softwareVersionNo= $(el).closest("tr").find("#s_softwareVersionNo").val();
	var softwareFirmwareNo= $(el).closest("tr").find("#s_softwareFirmwareNo").val();
	var electricPower = $(el).closest("tr").find("#s_electricPower").val();
	var nitrogenConsumption = $(el).closest("tr").find("#s_nitrogenConsumption").val();
	var calibrationDate = $(el).closest("tr").find("#s_calibrationDate").val();
	var nextCalibrationDate = $(el).closest("tr").find("#s_nextCalibrationDate").val();
	var installQualificDate = $(el).closest("tr").find("#s_installQualificDate").val();
	var operationQualificDate = $(el).closest("tr").find("#s_operationQualificDate").val();
	var performanceQualificDate = $(el).closest("tr").find("#s_performanceQualificDate").val();
	var qualiBy = $(el).closest("tr").find("#s_qualiBy").val();
	var acceptCriteria =  $(el).closest("tr").find("#s_acceptCriteria").val();
	var calibrationInterval = $(el).closest("tr").find("#s_calibrationInterval").val();
	var evidenceVerification = $(el).closest("tr").find("#s_evidenceVerification").val();
	var adjustment = $(el).closest("tr").find("#s_adjustment").val();
	var referenceMaterial = $(el).closest("tr").find("#s_referenceMaterial").val();
	var resultCalibration = $(el).closest("tr").find("#s_resultCalibration").val();
	
	$("#id").val(Id);
	$("#equipment_id").val(equipmentId);
	$("#equipmentName").val(equipmentName);
	$('#equipmentType').val(equipmentType).trigger('change.select2');
	$("#brand").val(brand);
	$("#modelNo").val(modelNo);
	$("#capacity").val(capacity);
	$("#sourceManufac").val(sourceManufac);
	$("#localAgentName").val(localAgentName);
	$("#currLocation").val(currLocation);
	$("#lcModelNo").val(lcModelNo);
	$("#serialNo").val(serialNo);
	$("#msModelNo").val(msModelNo);
	$("#msSerialNo").val(msSerialNo);
	if (manufacturingDate != "") {
	 $('#manufacturingDate').datepicker('setDate', convertMmDate(manufacturingDate));
	}
	$("#pumpModelNo").val(pumpModelNo);
	$("#detectorModelNo").val(detectorModelNo);
	$("#softwareName").val(softwareName);
	$("#softwareVersionNo").val(softwareVersionNo);
	$("#softwareFirmwareNo").val(softwareFirmwareNo);
	$("#electricPower").val(electricPower);
	$("#nitrogenConsumption").val(nitrogenConsumption);
	if (calibrationDate != "") {
		 $('#calibrationDate').datepicker('setDate', convertMmDate(calibrationDate));
		}
	if (nextCalibrationDate != "") {
		 $('#nextCalibrationDate').datepicker('setDate', convertMmDate(nextCalibrationDate));
	}
	if (installQualificDate != "") {
		 $('#installQualificDate').datepicker('setDate', convertMmDate(installQualificDate));
	}
	if (operationQualificDate != "") {
		 $('#operationQualificDate').datepicker('setDate', convertMmDate(operationQualificDate));
	}
	if (performanceQualificDate != "") {
		 $('#performanceQualificDate').datepicker('setDate', convertMmDate(performanceQualificDate));
	}
	$("#qualiBy").val(qualiBy);
	$("#accept_criteria").val(acceptCriteria);
	$("#calibrationInterval").val(calibrationInterval);
	$("#evidenceVerification").val(evidenceVerification);
	$("#adjustment").val(adjustment);
	$("#referenceMaterial").val(referenceMaterial);
	$("#img_calibration").val("");
	$("#img_calibration").attr('src', "${pageContext.request.contextPath}/image/equipment_info_doc/result_calibration/"+Id+"-"+resultCalibration);
	$("#calibration_image").val(resultCalibration);
	$("#e_image").val(Id+"-"+resultCalibration);
	
	$("#equipmentEntryModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#equipmentInfoForm").submit(function(event){
	event.preventDefault();				
	var formData = new FormData($("#equipmentInfoForm")[0]);
    //var formData = $("#equipmentInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/equipment/save-equipment-infos",
	        type: 'POST',
	        data: formData,
	        enctype: 'multipart/form-data',
	        async: false,
	        processData: false,
            contentType: false,
            cache: false,
	        success: function (data) {			 
	        	$("#equipmentEntryModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "error", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    }
    	}  else {
    		sweetAlert("Cancelled", "", "error", 0, false);
    	}

    	});
    });
    
function equipmentInfoPrint(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	var udId = $(el).closest("tr").find("#s_equipmentId").val();
	var ajaxURL = "${pageContext.request.contextPath}/equipment/equipment-info-print?equipmentId="+id+'&udEquipId='+udId;
	
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