<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">HPLC LOGBOOK INFO</span>
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
								<th class="align-center" style="width: 70px;">DATE</th>
								<th class="align-center" style="width: 120px;">AR No.</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 100px;">LOT NO</th>
								<th class="align-center" style="width: 110px;">START RUN TIME</th>
								<th class="align-center" style="width: 100px;">END RUN TIME</th>
								<th class="align-center" style="width: 100px;">COLUMN CODE</th>
									<th class="align-center" style="width: 100px;">OPERATED BY</th>
								<th class="align-center" style="width: 100px;">VERIFIED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-center">
								<c:choose>
							    	<c:when test="${(info.qcRefName !=null) && (not empty info.qcRefName)}">
									${info.qcRefName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>	
									</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">${info.lotNo}</td>
								<td class="align-left">${info.runStartTime}</td>
								<td class="align-left">${info.runEndTime}</td>
								<td class="align-left">
								<c:choose>
							    	<c:when test="${(info.columnName !=null) && (not empty info.columnName)}">
									${info.columnName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>	
								</td>
								<td class="align-left">${info.operateByName}</td>
								<td class="align-left">${info.verifyByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
								<c:choose>
									<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">Verified</span>
									</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcRefId}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_record_date" value="${info.recordDate}">
									<input type="hidden" id="r_column_id" value="${info.columnId}">
									<input type="hidden" id="r_run_start_time" value="${info.runStartTime}">
									<input type="hidden" id="r_run_end_time" value="${info.runEndTime}">
									<input type="hidden" id="r_first_mp_ratio" value="${info.firstMpRatio}">
									<input type="hidden" id="r_first_start_time" value="${info.firstStartTime}">
									<input type="hidden" id="r_first_end_time" value="${info.firstEndTime}">
									<input type="hidden" id="r_second_mp_ratio" value="${info.secondMpRatio}">
									<input type="hidden" id="r_second_start_time" value="${info.secondStartTime}">
									<input type="hidden" id="r_second_end_time" value="${info.secondEndTime}">
									<input type="hidden" id="r_total_time" value="${info.totalTime}">
									<input type="hidden" id="r_operate_by" value="${info.operateBy}">
									<input type="hidden" id="r_verify_by" value="${info.verifyBy}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
						    		<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
								
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
<div class="modal fade" id="multiParameterInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: HPLC Logbook Info </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-CM-015</td>
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
                 <form method="post" id="multiParameterInfoForm" modelAttribute="multiParameterInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
							 <div class="col-md-4">
							<span><b>EQUIPMENT ID  :</b></span>
                           	<div class="form-group">
     							<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                       	<option value="">-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>								
                           	</div>
                            </div>
						    <div class="col-md-4">
                            	<span><b>AR N0. :</b></span>
                            	<div class="form-group">
	                               <select  id="qc_ref_id" name="qcRefId" class="js-example-theme-single form-control" style="width: 100%;" >
			                         <option value="">-N/A-</option>
									 <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    	</select>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>LOT No :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="lot_no"  name="lotNo" value="" class="form-control" required="required" style="width: 30%;"  autocomplete="off">
                            	</div>
                            </div>
							</div>
                 		<div class="row">
                        
                            <div class="col-md-4">
                            	<span><b>DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="record_date" name="recordDate" value="" class="form-control dates" style="width: 30%;" required="required" autocomplete="off">
                            	</div>
                            </div>
								    <div class="col-md-4">
                            	<span><b>COLUMN NAME :</b></span>
                            	<div class="form-group">
	                               <select  id="column_id" name="columnId" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-N/A-</option>
			                        <c:forEach var="info" items="${columnInfos}">
			                           	<option value="${info.id }">${info.columnIdNew} ( ${info.colName} )</option>
			                        </c:forEach>
			                    	</select>
                            	</div>
                            </div>
                   
                 		   
                
                            <div class="col-md-4">
                            	<span><b>Run Time Start :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="run_start_time" name="runStartTime" value="" class="form-control" style="width: 30%;" required="required" autocomplete="off">
                            	</div>
                            </div>
							</div>
                 		<div class="row">
                          <div class="col-md-4">
                            	<span><b>Run Time End :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="run_end_time" name="runEndTime" value="" class="form-control" style="width: 30%;" required="required" autocomplete="off">
                            	</div>
                            </div>
                          <div class="col-md-4">
                            	<span><b>1st Step MP Ratio :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="first_mp_ratio" name="firstMpRatio" value="" class="form-control" style="width: 30%;" required="required" autocomplete="off">
                            	</div>
                            </div>
                          <div class="col-md-4">
                            	<span><b>1st Step Time Start :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="first_start_time" name="firstStartTime" value="" class="form-control" style="width: 30%;"  autocomplete="off">
                            	</div>
                            </div>
						</div>
                 		<div class="row">                        
                            <div class="col-md-4">
                            	<span><b>1st Step Time End :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="first_end_time" name="firstEndTime" value="" class="form-control" style="width: 30%;"  autocomplete="off">
                            	</div>
                            </div>
                 		      <div class="col-md-4">
                            	<span><b>2nd Step MP Ratio :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="second_mp_ratio" name="secondMpRatio" value="" class="form-control" style="width: 30%;" required="required" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>2nd Step Time Start :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="second_start_time" name="secondStartTime" value="" class="form-control" style="width: 30%;"  autocomplete="off">
                            	</div>
                            </div>
                           </div>
                 		<div class="row">
                         <div class="col-md-4">
                            	<span><b>2nd Step Time End :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="second_end_time" name="secondEndTime" value="" class="form-control" style="width: 30%;"  autocomplete="off">
                            	</div>
                            </div>
                 		     <div class="col-md-4">
                            	<span><b>Total Analysis Time :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="total_time" name="totalTime" value="" class="form-control" style="width: 30%;" required="required" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                 			<div class="col-md-12">
                            	<span><b>Remarks :</b></span>
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" placeholder="Description goes here......." ></textarea>
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
	
	$( "#record_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$('#run_start_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
	    });
	$('#run_end_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
	    });
	$('#first_start_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
	    });
	$('#first_end_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
	    });
	$('#second_start_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
	    });
	$('#second_end_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
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

function add(el) {
	$("#id").val("");
	$("#equipment_id").val("").trigger('change.select2');
	$("#qc_ref_id").val("").trigger('change.select2');
	$("#column_id").val("").trigger('change.select2');
	$("#lot_no").val("");
	$('#record_date').datepicker('setDate', new Date());
	$("#run_start_time").val("");
	$("#run_end_time").val("");
	$("#first_mp_ratio").val("");
	$("#first_start_time").val("");
	$("#first_end_time").val("");
	$("#second_mp_ratio").val("");
	$("#second_start_time").val("");
	$("#second_end_time").val("");
	$("#total_time").val("");
	$("#remarks").val("");
    $("#multiParameterInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var equipmentId    = $(el).closest("tr").find("#r_equipment_id").val();
	var qcRefId 	 = $(el).closest("tr").find("#r_qc_ref_id").val();
	var lotNo	 	 = $(el).closest("tr").find("#r_lot_no").val();
	var recordDate   = $(el).closest("tr").find("#r_record_date").val();
	var colId 	     = $(el).closest("tr").find("#r_column_id").val();
	var runStartTime 	= $(el).closest("tr").find("#r_run_start_time").val();
	var runEndTime 		= $(el).closest("tr").find("#r_run_end_time").val();
	var firstMpRatio 	= $(el).closest("tr").find("#r_first_mp_ratio").val();
	var firstStartTime 	= $(el).closest("tr").find("#r_first_start_time").val();
	var firstEndTime 	= $(el).closest("tr").find("#r_first_end_time").val();
	var secondMpRatio	= $(el).closest("tr").find("#r_second_mp_ratio").val();
	var secondStartTime	= $(el).closest("tr").find("#r_second_start_time").val();
	var secondEndTime 	= $(el).closest("tr").find("#r_second_end_time").val();
	var totalTime 		= $(el).closest("tr").find("#r_total_time").val();
	var operateBy 	 	= $(el).closest("tr").find("#r_operate_by").val();
	var verifyBy 	 	= $(el).closest("tr").find("#r_verify_by").val();
	var remarks 	 	= $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#equipment_id").val(equipmentId).trigger('change.select2');
	$("#qc_ref_id").val(qcRefId).trigger('change.select2');
	$("#column_id").val(colId).trigger('change.select2');
	$("#lot_no").val(lotNo);
	$('#record_date').datepicker('setDate', convertMmDate(recordDate));
	$("#run_start_time").val(runStartTime);
	$("#run_end_time").val(runEndTime);
	$("#first_mp_ratio").val(firstMpRatio);
	$("#first_start_time").val(firstStartTime);
	$("#first_end_time").val(firstEndTime);
	$("#second_mp_ratio").val(secondMpRatio);
	$("#second_start_time").val(secondStartTime);
	$("#second_end_time").val(secondEndTime);
	$("#total_time").val(totalTime);
	$("#remarks").val(remarks);
		
	$("#multiParameterInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#multiParameterInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#multiParameterInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveHplcInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#multiParameterInfoModal").modal('hide');
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

$(".js-example-theme-single").select2({
	    theme: "classic"
	});
	
</script>
