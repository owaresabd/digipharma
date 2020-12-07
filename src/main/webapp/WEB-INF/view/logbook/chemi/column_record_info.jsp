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

        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
				<!--<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button>-->
					
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
									        <span class="badge bg-green">PENDING</span>
									    </c:otherwise>
									</c:choose>
									
								</td>
								<td class="align-center">${info.partNo}</td>
								<td class="align-center">${info.serialNo}</td>
								<td class="align-center">${info.remarks}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_col_id" value="${info.colId}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_serial_no" value="${info.serialNo}">
									<input type="hidden" id="r_part_no" value="${info.partNo}">
									<input type="hidden" id="r_room_id" value="${info.rommNo}">
									<input type="hidden" id="r_rack_id" value="${info.rackNo}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_location_id" value="${info.locationId}">
									
									<!--equipmentByName     roomByName  rackByName colName-->
								    <input type="hidden" id="r_equipment_by_name" value="${info.equipmentByName}">
									<input type="hidden" id="r_room_by_name" value="${info.roomByName}">
									<input type="hidden" id="r_rack_by_name" value="${info.rackByName}">
									<input type="hidden" id="r_col_name" value="${info.colName}">
									<input type="hidden" id="r_theoretical_plate" value="${info.theoriticalPlate}">
									
								<!--<c:choose>
									    <c:when test="${info.columnRecordStatus =='Y'}&&${info.isRecordVerify =='N'}">
											<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
										</c:when>
								</c:choose>-->
							&nbsp
									
								<c:choose>
									    <c:when test="${info.columnRecordStatus =='N'}"> 
											<a class="btn-edit btn btn-xs" onclick="columnRecord(this)"><i class="material-icons">near_me</i></a>
										</c:when>
										<c:when test="${info.isRecordVerify =='Y'}"> 
									        <span class="badge bg-red">Verified</span>
										</c:when>
										 <c:otherwise>
										 <a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									        <!--	&nbsp
												<span class="badge bg-red">DONE</span>-->
									    </c:otherwise>
								</c:choose>
								</td>
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
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Column Record Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-016</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OCT 2019</td>
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
	                            	<label for="equipment_id">EQUIPMENT ID :</label>
	                            </div>
	                            <div class="col-md-4">
		                            <div class="form-group">
							  <input type="text" disabled="disabled" id="equipment_by_name" name="equipment_by_name" class="form-control" style="width: 100%;" >
		                       	</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="room_id">ROOM ID :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
								    <input type="text" disabled="disabled" id="room_by_name" name="room_by_name" value="" class="form-control" style="width: 100%;" >
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="rack_id">RACK NO :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group">
								<input type="text" disabled="disabled" id="rack_by_name" name="rack_by_name" value="" class="form-control" style="width: 100%;" autocomplete="off">
									</div>
									</div>
									
									<div class="col-md-2 align-right top">
		                            	<label for="column _id">CLOUMN ID :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group"> 
									<input type="text" disabled="disabled" id="col_name" name="col_name" value="" class="form-control" style="width: 100%;" autocomplete="off">
									</div>
									</div>
								</div>
                 		
		                 		<div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="serial_no">COLUMN SERIAL NO :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" disabled="disabled" id="serial_no" name="serialNo" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            
		                            <div class="col-md-2 align-right top">
		                            	<label for="part_no">PART NO:</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" disabled="disabled" id="part_no" name="partNo" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
									</div>
		                        <div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="serial_no">THEORETICAL PLATE :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="theoretical_plate" name="theoreticalPlate" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            
		                        </div>
		                        <div class="row">
		                            <div class="col-md-2 align-right m-t-20">
			                            <label for="remarks">Remarks :</label>
			                        </div>
			                            <div class="col-md-10 m-t-15">
			                            	<div class="form-group">
			                                	<textarea rows="2" disabled="disabled" id="remarks" name="remarks" class="form-control" maxlength="220" placeholder="Description goes here......." ></textarea>
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
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Column Record Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-016</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="columnRcvDistIssueForm">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id_record" name="id" value=""/>
                 			<div class="row">
								<div class="col-md-2 align-right top">
	                            	<label for="equipment_id_issue">EQUIPMENT ID :</label>
	                            </div>
	                            <div class="col-md-4">
		                            <div class="form-group">
									 <input type="text" disabled="disabled" id="equipment_by_name_record" name="equipment_by_name" class="form-control" style="width: 100%;" >
	
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="room_id">ROOM ID :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
									    <input type="text" disabled="disabled" id="room_by_name_record" name="room_by_name" value="" class="form-control" style="width: 100%;" >
	
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="rack_id">RACK NO :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group">
								<input type="text" disabled="disabled" id="rack_by_name_record" name="rack_by_name" value="" class="form-control" style="width: 100%;" >
							

									</div>
									</div>
									
									<div class="col-md-2 align-right top">
		                            	<label for="column _id">CLOUMN ID :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group"> 
									<input type="text" disabled="disabled" id="col_name_record" name="col_name" value="" class="form-control" style="width: 100%;" autocomplete="off">

									</div>
									</div>
								</div>
                         		<div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="serial_no">COLUMN SERIAL NO :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="serial_no_record" disabled="disabled" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		          			        <div class="col-md-2 align-right top">
		                            	<label for="part_no">PART NO:</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input name="text" type="text" disabled="disabled" class="form-control" id="part_no_record" style="width: 100%;"  />
		                           	</div>
		                            </div>
		                </div>
                 	    <div class="row">
                            <div class="col-md-2 align-right top">
		           				<label for="serial_no">THEORETICAL PLATE :</label>
                                 </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                         <input type="text" id="theoretical_plate_record" name="theoreticalPlateRecord" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
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


function columnRecord(el){
	
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var colId		= $(el).closest("tr").find("#r_col_id").val();
	var equID 	= $(el).closest("tr").find("#r_equipment_id").val();
	var rooNo 	= $(el).closest("tr").find("#r_room_id").val();
	var racNo = $(el).closest("tr").find("#r_rack_id").val();
	var paNo 		= $(el).closest("tr").find("#r_part_no").val();
	var seNo 	= $(el).closest("tr").find("#r_serial_no").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
    var equipmentByName = $(el).closest("tr").find("#r_equipment_by_name").val();
	var roomByName 	= $(el).closest("tr").find("#r_room_by_name").val();
	var rackByName 	= $(el).closest("tr").find("#r_rack_by_name").val();
	var colName   = $(el).closest("tr").find("#r_col_name").val();
	var theoreticalPlate   = $(el).closest("tr").find("#r_theoretical_plate").val();
	
	
	$("#id_record").val(Id);
	$("#serial_no_record").val(seNo);
	$("#part_no_record").val(paNo);
	$("#remarks_record").val(remarks);
	$("#equipment_by_name_record").val(equipmentByName);
	$("#room_by_name_record").val(roomByName);
	$("#rack_by_name_record").val(rackByName);
	$("#col_name_record").val(colName);
	$("#theoretical_plate_record").val(theoreticalPlate);
		
	$("#columnRcvDistIssueModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};


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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveColumnRecord",
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
    var equipmentByName = $(el).closest("tr").find("#r_equipment_by_name").val();
	var roomByName 	= $(el).closest("tr").find("#r_room_by_name").val();
	var rackByName 	= $(el).closest("tr").find("#r_rack_by_name").val();
	var colName   = $(el).closest("tr").find("#r_col_name").val();
	var theoreticalPlate   = $(el).closest("tr").find("#r_theoretical_plate").val();
	
	
	$("#id").val(Id);
	$("#col_id").val(colId).trigger('change.select2');
	$("#equipment_id").val(equID).trigger('change.select2');
	$("#room_no").val(rooNo).trigger('change.select2');
	$("#rack_no").val(racNo).trigger('change.select2'); 
	$("#serial_no").val(seNo);
	$("#part_no").val(paNo);
	$("#remarks").val(remarks);
	$("#equipment_by_name").val(equipmentByName);
	$("#room_by_name").val(roomByName);
	$("#rack_by_name").val(rackByName);
	$("#col_name").val(colName);
	$("#theoretical_plate").val(theoreticalPlate);
	
	
		
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
	     //url : "${pageContext.request.contextPath}/logbook_chemi/saveColumnReceiveDistribute",
	       url : "${pageContext.request.contextPath}/logbook_chemi/updateColumnRecord",
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
