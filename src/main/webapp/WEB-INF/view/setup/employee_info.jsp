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
									<input type="hidden" id="e_status" value="${info.status}">
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
                    
                    <!-- Tab panes -->
                    
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
										<select  id=desigId name="desigId" class="js-example-theme-single form-control" style="width: 100%;" required="required" >
			                        	<option></option>
			                        <c:forEach var="desigInfo" items="${desigInfos}">
			                           	<option value="${desigInfo.id }">${desigInfo.designationName}</option>
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
									<span><b>Status</b></span>
									<div class="demo-checkbox">
										<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
										<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
										<input type="hidden" id="status" name="status" value="N">
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
	$("#activity_status").prop('checked', true);
	$('.check-status').text('Active ?');
	$('#status').val('Y');
	
	
    $("#employeeInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
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
	var status = $(el).closest('tr').find("#e_status").val();
	
	$("#id").val(id);
	$("#udEmpNo").val(udEmpNo);
	$("#udEmpNo").attr('readonly', 'readonly');
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
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
	
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



var aTable = $('#empTable').DataTable({
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

$('#empTable_length, #empTable_info, #empTable_paginate, #empTable_filter').hide();



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





</script>