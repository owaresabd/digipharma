<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
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
	    <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK :: NOTE BOOK CONTROL</span>
    	
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button>
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="countryList">
						<thead>
							<tr>
							  <th class="align-center" style="width: 70px;">SL NO</th>
							  <th class="align-center" style="width: 100px;">DATE</th>
							  <th class="align-center" style="width: 100px;">TIME</th>
							  <th class="align-left">NAME OF THE EMPLOYEE</th>
							  <th class="align-left">DESIGNATION </th>
							  <th class="align-left">NOTE BOOK NO</th>
							  <th class="align-center" style="width: 100px;">ISSUED BY </th>
							  <th class="align-left">REMARKS</th>
							  <th class="align-center" style="width: 110px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center"><fmt:formatDate var="issueDate" value="${info.issueDate}" pattern="dd-MMM-yyyy"/>
								${info.issueDate}</td>
								<td class="align-center">${info.recordTime}</td>
								<td class="align-left">
									${info.employeeName}							
								</td>
								<td class="align-left">${info.designationName}</td>
								<td class="align-left">${info.noteBookNo}</td>
								<td class="align-center"><fmt:formatDate var="operateDate" value="${info.issueDate}" pattern="dd-MMM-yyyy"/>
								 ${info.issueByNm}</td>
								<td class="align-left">${info.remarks} </td>
								<td class="align-center">
								<c:choose>
									<c:when test="${info.returnStatus =='Y'}">
									<span class="badge bg-green">Returned</span>
									</c:when><c:when test="${info.receiveStatus =='Y'}">
									<span class="badge bg-green">Received</span>
									</c:when>
									
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
									<input type="hidden" id="m_id" value="${info.id}">
									<input type="hidden" id="m_employee_id" value="${info.employeeId}">
									<input type="hidden" id="m_designation_id" value="${info.designationId}">
									<input type="hidden" id="m_note_book_no" value="${info.noteBookNo}">
									<input type="hidden" id="m_remarks" value="${info.remarks}">
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" style="overflow: auto;" id="noteBookInfoModal"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             	<button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             	<div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">LOG BOOK ::NOTE BOOK CONTROL</td>
                 	<td>Document No.</td>
                 	<td colspan="2">LB-DIL-CM-026</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">19 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                </div>
                 <form method="post" id="noteBookInfoForm" onkeypress="if (event.keyCode == 13) { return false; }">  
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
				<div class="row">
                            <div class="col-md-6">
                            	<span><b>NAME OF THE EMPLOYEE :</b></span>
                            	<div class="form-group">
                            	<select id="employeeId" name="employeeId" class="js-example-theme-single form-control" style="width:100%" required>
								       <option></option>
								        <c:forEach var="empInfo" items="${employeeInfos}">
				                           	<option value="${empInfo.id }">[${empInfo.udEmpNo}] ${empInfo.empName}</option>
				                        </c:forEach>
								    </select>
	                        	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>DESIGNATION :</b></span>
                            	<div class="form-group">
	                                 <select id="designationId" name="designationId" class="js-example-theme-single form-control" style="width:100%" required>
								                        	<option></option>
								                        <c:forEach var="desigInfo" items="${desigInfos}">
			                           	<option value="${desigInfo.id }">${desigInfo.desigName}</option>
			                        </c:forEach>
								 </select>
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>NOTE BOOK NO  :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="noteBookNo" name="noteBookNo" value="" class="form-control"  required="required" autocomplete="off">
				            	</div>
                            </div>
                        </div>
                 	
                 		<div class="row">
                            <div class="col-md-12">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="remarks" name="remarks" value="" class="form-control" autocomplete="off">
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
     <div class="modal fade" id="disposeViewModal"   role="dialog" data-backdrop="static" data-keyboard="false" >
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
	var link =  "${pageContext.request.contextPath}/logbook_chemi/noteBookControl";
	$("#noteBookInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
});


$(function() {
    $('.uperrcase').keyup(function() {
        this.value = this.value.toUpperCase();
    });
});




$('.number').keypress(function (event) {
    return isNumber(event, this)
});


$( function() {
    $( ".dates" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
});
function add(el) {
	$("#id").val("");
	$('#employeeId').val("").trigger('change.select2');
	$('#designationId').val("").trigger('change.select2');
	$("#noteBookNo").val("");
	$('#issueBy').val("").trigger('change.select2');
	$("#issueDate").val("");
	$('#receiveBy').val("").trigger('change.select2');
	$("#receiveDate").val("");
	$('#returnBy').val("").trigger('change.select2');
	$("#returnDate").val("");
	$("#remarks").val("");
	
	$("#noteBookInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	
	
	var id 			= $(el).closest("tr").find("#m_id").val();
	var employeeId 	= $(el).closest("tr").find("#m_employee_id").val();
	var designationId 	= $(el).closest("tr").find("#m_designation_id").val();
	var noteBookNo 	= $(el).closest("tr").find("#m_note_book_no").val();
	var remarks 	= $(el).closest("tr").find("#m_remarks").val();
	
	$("#id").val(id);
	$("#employeeId").val(employeeId).trigger('change.select2');
	$('#designationId').val(designationId).trigger('change.select2');
	$("#noteBookNo").val(noteBookNo);
	$("#remarks").val(remarks);
	
    
	$("#noteBookInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    
}

$("#noteBookInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#noteBookInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveNoteBookControl",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#noteBookInfoModal").modal('hide');
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


function printReport(el) {
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/notebook-log-print";
	
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