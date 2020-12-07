<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">COLUMN RECEIVING &amp; DISTRIBUTION LOGBOOK INFO</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/reference/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/reference/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button>
					
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 100px;">COLUMN NAME</th>
								<th class="align-center" style="width: 110px;">COLUMN ID</th>
								<th class="align-center" style="width: 110px;">COMPOSITION</th>
								<th class="align-center" style="width: 110px;">COLUMN SIZE</th>
								<th class="align-center" style="width: 150px;">COLUMN STORAGE LOCATION</th>
								<th class="align-center" style="width: 150px;">COLUMN ISSUED BY & DATE</th>
								<th class="align-center" style="width: 100px;">PART NO</th>
								<th class="align-center" style="width: 100px;">COLUMN SERIAL NO</th>
								<th class="align-center" style="width: 100px;">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.colName}</td>
								<!--<td class="align-center">${info.colIdByName}</td>-->
								<td class="align-center">${info.columnIdNew}</td>
								
								<td class="align-center">${info.composition}</td>
								<td class="align-center">${info.colSize}</td>
								<td class="align-center">${info.equipmentByName}, ${info.roomByName}, ${info.rackByName}</td>
								<td class="align-center">
									<c:choose>
									    <c:when test="${info.isIssue =='Y'}">
											<span>${info.issueByName},</span>
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.issueDate}" var="dateVal" />
		                              		<c:out value="${dateVal}"/>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-green">Pending</span>									    </c:otherwise>
									</c:choose>								</td>
								<td class="align-center">${info.partNo}</td>
								<td class="align-center">${info.serialNo}</td>
								<td class="align-center">${info.remarks}
							    <input name="hidden" type="hidden" id="r_location_id" value="${info.locationId}" /></td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_col_id" value="${info.colId}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_serial_no" value="${info.serialNo}">
									<input type="hidden" id="r_part_no" value="${info.partNo}">
									<input type="hidden" id="r_room_id" value="${info.rommNo}">
									<input type="hidden" id="r_rack_id" value="${info.rackNo}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_column_id_new" value="${info.columnIdNew}">
								<c:choose>
									    <c:when test="${info.isIssue =='N'}">
											<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>										</c:when>
								</c:choose>
								&nbsp
								<c:choose>
									    <c:when test="${info.isIssue =='N'}">
											<a class="btn-edit btn btn-xs" onclick="issue(this)"><i class="material-icons">near_me</i></a>										</c:when>
										 <c:otherwise>
									        <span class="badge bg-red">ISSUED</span>									    </c:otherwise>
								</c:choose>								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<!------------------------ Start: Create Product Color Types Modal -------------------->
<div class="modal fade" id="columnRcvDistInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog md modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Column Receiving &amp; Distribution Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-013</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OTC 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="columnRcvDistInfoForm" modelAttribute="columnRcvDistInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 			<div class="row">
									<div class="col-md-2 align-right top">
		                            	<label for="column _id">CLOUMN NAME :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group"> 
											<select  id="col_id" name="colId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select column Name.--</option>
				                        	<c:forEach var="column" items="${columnInfos}">
				                           	<option value="${column.id }">${column.columnName}</option>
				                        	</c:forEach>
				                        	</select>
									</div>
									</div>
			                        <div class="col-md-2 align-right top">
		                            	<label for="serial_no">COLUMN ID :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="column_id_new" name="columnIdNew" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		    				</div>
                 			<div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="serial_no">COLUMN SERIAL NO :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="serial_no" name="serialNo" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            <div class="col-md-2 align-right top">
		                            	<label for="part_no">PART NO:</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="part_no" name="partNo" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
									</div>
						<div class="row">
								<div class="col-md-4 align-right top">
	                            	<label for="equipment_id" style="color:#CC3366;"><u>COLUMN STORAGE LOCATION</u> :</label>
	                            </div>
	                   </div>
							<div class="row">
								<div class="col-md-2  top">
	                            	<label for="equipment_id">EQUIPMENT ID :</label>
	                            </div>
	                            <div class="col-md-4">
		                            <div class="form-group">
										<select  id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Equipment ID--</option>
				                        	<c:forEach var="equpInfo" items="${storageEquipInfos}">
				                           	<option value="${equpInfo.id }">${equpInfo.equipmentId}</option>
				                        	</c:forEach>
				                        </select>
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="room_id">ROOM ID :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
											<select  id="room_no" name="rommNo" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Room No--</option>
				                        	<c:forEach var="roomInfo" items="${roomList}">
				                           	<option value="${roomInfo.id }">${roomInfo.roomName}</option>
				                        	</c:forEach>
				                        	</select>
									</div>
								</div>
								</div>
									<div class="row">
									<div class="col-md-2 align-right top">
		                            	<label for="rack_id">RACK NO :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group">
											<select  id="rack_no" name="rackNo" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Rack No.--</option>
				                        	<c:forEach var="rackInfo" items="${rackList}">
				                           	<option value="${rackInfo.id }">${rackInfo.rackName}</option>
				                        	</c:forEach>
				                        	</select>
									</div>
									</div>
							</div>
                 			
									
		                    <div class="row">
		                            
		                            <div class="col-md-2 align-right m-t-20">
			                            <label for="remarks">Remarks :</label>
			                        </div>
			                            <div class="col-md-10 m-t-15">
			                            	<div class="form-group">
			                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="220" placeholder="Description goes here......." ></textarea>
				                            </div>
			                            </div>
		                 		</div>
	                 </div>
	                 
	                 
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" style="margin-right: 50%" class="btn bg-red btn-sm waves-effect pull-right m-r-20"  data-dismiss="modal">
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
     
     
     <div class="modal fade" id="columnRcvDistIssueModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog md modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Column Receiving &amp; Distribution Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-013</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OTC 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="columnRcvDistIssueForm">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id_issue" name="id" value=""/>
                 		
                 		
                 			<div class="row">
								<div class="col-md-2 align-right top">
	                            	<label for="equipment_id_issue">EQUIPMENT ID :</label>
	                            </div>
	                            <div class="col-md-4">
		                            <div class="form-group">
										<select  id="equipment_id_issue" disabled="disabled" class="js-example-theme-single form-control" style="width: 100%;" >
				                        	<option>--Select Equipment ID--</option>
				                        	<c:forEach var="equpInfo" items="${storageEquipInfos}">
				                           	<option value="${equpInfo.id }">${equpInfo.equipmentId}</option>
				                        	</c:forEach>
				                        </select>
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="room_id">ROOM ID :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
											<select  id="room_no_issue" disabled="disabled" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Room No--</option>
				                        	<c:forEach var="roomInfo" items="${roomList}">
				                           	<option value="${roomInfo.id }">${roomInfo.roomName}</option>
				                        	</c:forEach>
				                        	</select>
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="rack_id">RACK NO :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group">
											<select  id="rack_no_issue" disabled="disabled" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Rack No.--</option>
				                        	<c:forEach var="rackInfo" items="${rackList}">
				                           	<option value="${rackInfo.id }">${rackInfo.rackName}</option>
				                        	</c:forEach>
				                        	</select>
									</div>
									</div>
									
									<div class="col-md-2 align-right top">
		                            	<label for="column _id">CLOUMN NAME :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group"> 
											<select  id="col_id_issue" disabled="disabled" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select column No.--</option>
				                        	<c:forEach var="column" items="${columnInfos}">
				                           	<option value="${column.id }">${column.columnName}</option>
				                        	</c:forEach>
				                        	</select>
									</div>
									</div>
								</div>
                 		<div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="serial_no">COLUMN ID :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="column_id_new_issue" disabled="disabled" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                     </div>
		                 		<div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="serial_no">COLUMN SERIAL NO :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="serial_no_issue" disabled="disabled" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            
		                            <div class="col-md-2 align-right top">
		                            	<label for="part_no">PART NO:</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="part_no_issue" disabled="disabled" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            
		                            <div class="col-md-2 align-right top">
		                            	<label for="column _id">LOCATION:</label>
		                            </div>
	                          	  	<div class="col-md-10">
		                            <div class="form-group"> 
											 <select  id="location_id" name="locationId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
							                        <option value="">-Select Location-</option>
							                        <c:forEach var="info" items="${locationInfos}">
							                           	<option value="${info.id}">${info.locationName}</option>
							                        </c:forEach>
			                    			</select>
									</div>
									</div>
		                          
		                 		</div>
	                 </div>
	                 
	                 
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" style="margin-right: 50%" class="btn bg-red btn-sm waves-effect pull-right m-r-20"  data-dismiss="modal">
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
</style>
		
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>		
<script>

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

$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#col_rcv_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#issue_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#receive_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#open_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#expire_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
		
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$(function() {
    $('#ehd_no').keyup(function() {
        this.value = this.value.toUpperCase();
    });
    
});

$('#ehd_no').keyup(function() {
	var ehdNo = $("#ehd_no").val();
	$.get( "${pageContext.request.contextPath}/logbook_chemi/validate-ehdNo/" + ehdNo, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate EHD ID Available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
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

function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

	$("#productCode").val(data.materialCode);
	$('#productUnitId').val(data.unitId).trigger('change.select2');

	});
	}

function add(el) {
	$("#id").val("");
	$("#serialNo").val("");
	$("#partNo").val("");
	$("#colId").val("").trigger('change.select2');
	$("#equipmentId").val("").trigger('change.select2');
	$("#rommNo").val("").trigger('change.select2');
	$("#rackNo").val("").trigger('change.select2');
	$("#locationId").val("").trigger('change.select2');
	$("#column_id_new").val("");
	
	
	/* $('#col_rcv_date').datepicker('setDate', new Date()); */
	$("#remarks").val("");
    $("#columnRcvDistInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function issue(el){
	
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var colId		= $(el).closest("tr").find("#r_col_id").val();
	var equID 	= $(el).closest("tr").find("#r_equipment_id").val();
	var rooNo 	= $(el).closest("tr").find("#r_room_id").val();
	var racNo = $(el).closest("tr").find("#r_rack_id").val();
	var paNo 		= $(el).closest("tr").find("#r_part_no").val();
	var seNo 	= $(el).closest("tr").find("#r_serial_no").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var locId   = $(el).closest("tr").find("#r_location_id").val();
	var columnIdNew   = $(el).closest("tr").find("#r_column_id_new").val();
	//alert(columnIdNew);
	console.log(Id);
	$("#id_issue").val(Id);
	$("#col_id_issue").val(colId).trigger('change.select2');
	$("#equipment_id_issue").val(equID).trigger('change.select2');
	$("#room_no_issue").val(rooNo).trigger('change.select2');
	$("#rack_no_issue").val(racNo).trigger('change.select2'); 
	$("#location_id").val(locId).trigger('change.select2');
	$("#serial_no_issue").val(seNo);
	$("#part_no_issue").val(paNo);
	$("#remarks").val(remarks);
	$("#column_id_new_issue").val(columnIdNew);
		
	$("#columnRcvDistIssueModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

function addIssue(el) {
	$("#id").val("");
	$("#locationId").val("").trigger('change.select2');
    $("#columnRcvDistIssueModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

$("#columnRcvDistIssueForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#columnRcvDistIssueForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/issueColumnReceiveDistribute",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#columnRcvDistIssueModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "error", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 3000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

function edit(el) {
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var colId		= $(el).closest("tr").find("#r_col_id").val();
	var equID 	= $(el).closest("tr").find("#r_equipment_id").val();
	var rooNo 	= $(el).closest("tr").find("#r_room_id").val();
	var racNo = $(el).closest("tr").find("#r_rack_id").val();
	var paNo 		= $(el).closest("tr").find("#r_part_no").val();
	var seNo 	= $(el).closest("tr").find("#r_serial_no").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var columnIdNew   = $(el).closest("tr").find("#r_column_id_new").val();
	
	$("#id").val(Id);
	$("#col_id").val(colId).trigger('change.select2');
	$("#equipment_id").val(equID).trigger('change.select2');
	$("#room_no").val(rooNo).trigger('change.select2');
	$("#rack_no").val(racNo).trigger('change.select2'); 
	$("#serial_no").val(seNo);
	$("#part_no").val(paNo);
	$("#remarks").val(remarks);
	$("#column_id_new").val(columnIdNew);
		
	$("#columnRcvDistInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#columnRcvDistInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#columnRcvDistInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveColumnReceiveDistribute",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#columnRcvDistInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "error", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 3000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

function printReport(el) {
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/column-receive-log-print";
	
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
