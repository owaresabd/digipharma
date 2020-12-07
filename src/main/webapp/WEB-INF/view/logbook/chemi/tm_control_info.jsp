<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: TM CONTROL</span>
		
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
								<th class="align-center" style="width: 100px;">DATE </th>
								<th class="align-center" style="width: 110px;">ARN NO</th>
								<th class="align-center" style="width: 200px;">USED BY</th>
								<th class="align-center" style="width: 200px;">RETURN BY</th>
								<th class="align-center" style="width: 100px;">RETURN DATE</th>
								<th class="align-center" style="width: 200px;">CONTROLLED  BY</th>
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
								<td class="align-center">${info.qcReference}</td>
								
								<td class="align-left">${info.usedByName}</td>
								<td class="align-left">
								${info.returnByName}</td>
								<td class="align-left">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.returnDate}" var="retVal" />
	                             <c:out value="${retVal}"/>
	                             </td>
								<td class="align-left">${info.controlByName}</td>
								
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_qc_reference" value="${info.qcReferenceId}">
									<input type="hidden" id="r_record_date" value="${info.recordDate}">
									<input type="hidden" id="r_used_by" value="${info.usedBy}">
									<input type="hidden" id="r_return_by" value="${info.returnBy}">
									<input type="hidden" id="r_return_date" value="${info.returnDate}">
									<input type="hidden" id="r_controlled_by" value="${info.controlledBy}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
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
<div class="modal fade" id="kftInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book :: TM Control</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-007</td>
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
                 <form method="post" id="kftInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="record_date">DATE OF RECORD :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                                <input type="text" id="recordDate" name="recordDate" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="qc_reference">ARN NO :</label>
                            </div>
                            <div class="col-md-8">
                           	<div class="form-group">
                                <select  id="qcReferenceId" name="qcReferenceId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
                 		</div>
                 		
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="operate_by">USED BY :</label>
                            </div>
                            <div class="col-md-8">
                           	<div class="form-group">
                                <select  id="usedBy" name="usedBy" class="js-example-theme-single form-control" style="width: 100%;" required="required">
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
                            	<label for="clean_by">RETURNED BY :</label>
                            </div>
                            <div class="col-md-8">
                           	<div class="form-group">
                                <select  id="returnBy" name="returnBy" class="js-example-theme-single form-control" style="width: 100%;" required="required">
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
                            	<label for="record_date">RETURN DATE :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                                <input type="text" id="returnDate" name="returnDate" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="verify_by">CONTROLLED BY :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                            	<select  id="controlledBy" name="controlledBy" class="js-example-theme-single form-control" style="width: 100%;">
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
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
});
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
	
	$( "#recordDate").datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#returnDate").datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	
});




function add(el) {
	$("#id").val("");
	$("#qcReferenceId").val("").trigger('change.select2');
	$('#recordDate').datepicker('setDate', new Date());
	$("#usedBy").val("").trigger('change.select2');
	$("#returnBy").val("").trigger('change.select2');
	$('#returnDate').datepicker('setDate', new Date());
	$("#controlledBy").val("").trigger('change.select2');
	$("#remarks").val("");
    $("#kftInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 		   = $(el).closest("tr").find("#row_id").val();
	var qcRefId    = $(el).closest("tr").find("#r_qc_reference").val();
	var recordDate = $(el).closest("tr").find("#r_record_date").val();
	var usedBy  = $(el).closest("tr").find("#r_used_by").val();
	var returnBy    = $(el).closest("tr").find("#r_return_by").val();
	var returnDate = $(el).closest("tr").find("#r_return_date").val();
	var controlledBy   = $(el).closest("tr").find("#r_controlled_by").val();
	var remarks    = $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#qcReferenceId").val(qcRefId).trigger('change.select2');
	$('#recordDate').datepicker('setDate', convertMmDate(recordDate));
	$("#usedBy").val(usedBy).trigger('change.select2');
	$("#returnBy").val(returnBy).trigger('change.select2');
	$('#returnDate').datepicker('setDate', convertMmDate(returnDate));
	$("#controlledBy").val(controlledBy).trigger('change.select2');
	$("#remarks").val(remarks);
		
	$("#kftInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#kftInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#kftInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveTmControllInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$("#kftInfoModal").modal('hide');
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
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/tmcontrol-log-print";
	
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
