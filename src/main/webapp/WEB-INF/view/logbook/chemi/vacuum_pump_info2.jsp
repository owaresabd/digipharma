<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">

<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">VACUUM PUMP LOGBOOK INFO</span>
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
					<c:if test="${not empty infos}">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right m-r-10" id="btnPrint" onClick="printReport(this)" data-toggle="tooltip" title="Print">
						<i class="material-icons">print</i>
					</button>
					</c:if>
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 110px;">DATE</th>
								<th class="align-center" style="width: 110px;">TIME</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center">PURPOSE</th>
								<th class="align-center" style="width: 100px;">OPERATED BY</th>
								<th class="align-center" style="width: 100px;">VERIFIED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-center">${info.recordTime}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">${info.purpose}</td>
								<td class="align-left">${info.operateByName}</td>
								<td class="align-left">${info.verifyByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
									    <c:when test="${info.isVerify =='Y'}">
											<span class="badge bg-green">Verified</span>
									    </c:when>
									<c:otherwise>
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_purpose" value="${info.purpose}">
									<input type="hidden" id="r_record_date" value="${info.recordDate}">
									<input type="hidden" id="r_record_time" value="${info.recordTime}">
									<input type="hidden" id="r_operate_by" value="${info.operateBy}">
									<input type="hidden" id="r_verify_by" value="${info.verifyBy}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
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
<div class="modal fade" id="vacuumPumpLogInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Vacuum Pump Logbook Info </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-010</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 Jan 2020</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="vacuumPumpLogInfoForm" modelAttribute="vacuumPumpLogInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="equipment_id">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentId}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
                 		</div>
                 		
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="purpose">PURPOSE OF USE :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                                <input type="text" id="purpose" name="purpose" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="record_date">DATE OF RECORD :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                                <input type="text" id="record_date" name="recordDate" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="record_time">TIME OF RECORD :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                                <input type="text" id="record_time" name="recordTime" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="operate_by">OPERATED BY :</label>
                            </div>
                            <div class="col-md-8">
                           	<div class="form-group">
                                <select  id="operate_by" name="operateBy" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="verify_by">VERIFIED BY :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                            	<select  id="verify_by" name="verifyBy" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-8 m-t-15">
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
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>		
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

$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#record_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$('#record_time').mdtimepicker({
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
    $('#referenceNo').keyup(function() {
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

function add(el) {
	$("#id").val("");
	$("#purpose").val("");
	$('#record_date').datepicker('setDate', new Date());
	$("#record_time").val("");
	$("#operate_by").val("").trigger('change.select2');
	$("#verify_by").val("").trigger('change.select2');
	$("#remarks").val("");
    $("#vacuumPumpLogInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id = $(el).closest("tr").find("#row_id").val();
	var purpose = $(el).closest("tr").find("#r_purpose").val();
	var recordDate = $(el).closest("tr").find("#r_record_date").val();
	var recordTime = $(el).closest("tr").find("#r_record_time").val();
	var operateBy = $(el).closest("tr").find("#r_operate_by").val();
	var verifyBy = $(el).closest("tr").find("#r_verify_by").val();
	var remarks = $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#purpose").val(purpose);
	$('#record_date').datepicker('setDate', convertMmDate(recordDate));
	$("#record_time").val(recordTime);
	$("#operate_by").val(operateBy).trigger('change.select2');
	$("#verify_by").val(verifyBy).trigger('change.select2');
	$("#remarks").val(remarks);
		
	$("#vacuumPumpLogInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#vacuumPumpLogInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#vacuumPumpLogInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveVacuumPumpInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#vacuumPumpLogInfoModal").modal('hide');
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
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

function printReport(el) {
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/vacuum-pump-log-print";
	
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
