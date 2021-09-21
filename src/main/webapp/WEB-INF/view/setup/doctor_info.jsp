<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">DOCTOR LIST</span>
    	
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="supplierList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;">SL#</th>
								<th class="align-center">DOCTOR NAME</th>		
								<th class="align-left">BMDC NO</th>
								<th class="align-left" style="width: 135px;">DESIGNATION</th>
								<th class="align-leftr" style="width: 135px;">SPECIALITY</th>
								<th class="align-center" style="width: 100px;">CONSULTATION FEE</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_fuel_type align-center">${info.doctorName}</td>
								<td class="field_fuel_qty">${info.bmdcNo}</td>
								<td class="field_fuel_expense">${info.designationInfo.designationName}</td>
								<td class="field_fuel_load_date ">${info.specialityInfo.specialityName}</td>
								<td class="field_fuel_qty align-center">${info.primaryConsultFee}</td>
								<td class="field_fuel_load_date align-center">
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
									<input type="hidden" id="f_name" value="${info.doctorName}">
									<input type="hidden" id="f_qualification" value="${info.qualification}">
									<input type="hidden" id="f_bmdc" value="${info.bmdcNo}">
									<input type="hidden" id="f_designation" value="${info.designationInfo.id}">
									<input type="hidden" id="f_department" value="${info.departmentInfo.id}">
									<input type="hidden" id="f_speciality" value="${info.specialityInfo.id}">
									<input type="hidden" id="f_primary_fee" value="${info.primaryConsultFee}">
									<input type="hidden" id="f_followup_fee" value="${info.followupConsultFee}">
									<input type="hidden" id="f_status" value="${info.status}">
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
	
	<div class="modal fade" id="doctorInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">DOCTOR INFORMATION</h4>
                 </div>
                 <form method="post" id="doctorInfoForm" modelAttribute="doctorInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>DOCTOR NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="doctorName" name="doctorName" value="" class="form-control upper"   autocomplete="off" required>
                            	</div>
                            </div>
	                 		<div class="col-md-6">
                            	<span><b>QUALIFICATION :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="qualification" maxlength="10" name="qualification" value="" class="form-control"   autocomplete="off" required>
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>DESIGNATION :</b></span>
                            	<div class="form-group">
	                                <select  id="designationId" name="designationInfo" class="js-example-theme-single form-control" style="width: 100%;" required="required" >
			                        	<option></option>
			                        <c:forEach var="info" items="${designationInfos}">
			                           	<option value="${info.id }">${info.designationName}</option>
			                        </c:forEach>
			                        </select>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>BMDC NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="bmdcNo" name="bmdcNo" value="" class="form-control upper"   autocomplete="off">
                            	</div>
                            </div>
	                 		
                            
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>DEPARTMENT :</b></span>
                            	<div class="form-group">
	                                <select  id="departmentId" name="departmentInfo" class="js-example-theme-single form-control" style="width: 100%;" required="required" >
			                        	<option></option>
			                        <c:forEach var="info" items="${departmentInfos}">
			                           	<option value="${info.id }">${info.departmentName}</option>
			                        </c:forEach>
			                        </select>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>SPECIALITY :</b></span>
                            	<div class="form-group">
	                                <select  id="specialityId" name="specialityInfo" class="js-example-theme-single form-control" style="width: 100%;" required="required" >
			                        	<option></option>
			                        <c:forEach var="info" items="${specialityInfos}">
			                           	<option value="${info.id }">${info.specialityName}</option>
			                        </c:forEach>
			                        </select>
                            	</div>
                            </div>
	                 		
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>PRIMARY CONSULTATION FEE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="primaryConsultFee" maxlength="4" style="width: 30%;" name="primaryConsultFee" value="" class="form-control"   autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>FOLLOWUP CONSULTATION FEE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="followupConsultFee" name="followupConsultFee" maxlength="4" style="width: 30%;" value="" class="form-control"   autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>ACTIVITY STATUS</b></span>
                            	<div class="form-group">
	                                <div class="demo-checkbox">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            	</div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
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

.top {
	margin-top: 5px !important;
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

input {
    height: 28px !important;
}

.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    vertical-align: middle !important;
}

[type="checkbox"] + label {
    height: 10px;
    font-size: 12px;
}

.dates {
    background-color: mintcream;
   	border: 1px solid #c8c7cc;
   	border-radius: 5px !important;
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

$(function() {
    $('.upper').keyup(function() {
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
	$("#doctorName").val("");
	$("#qualification").val("");
	$("#bmdcNo").val("");
	$("#designationId").select2("val", "");
	$("#departmentId").select2("val", "");
	$("#specialityId").select2("val", "");
	$("#primaryConsultFee").val("");
	$("#followupConsultFee").val("");
	
	$("#activity_status").prop('checked', false);
	$('#status').val('N'); 
    $("#doctorInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	
	var id = $(el).closest("tr").find("#row_id").val();
	var name = $(el).closest("tr").find("#f_name").val();
	var qualification = $(el).closest("tr").find("#f_qualification").val();
	var bmdc = $(el).closest("tr").find("#f_bmdc").val();
	var designationId = $(el).closest("tr").find("#f_designation").val();
	var departmentId=$(el).closest("tr").find("#f_department").val();
	var specialityId=$(el).closest("tr").find("#f_speciality").val();
	var primaryConsultFee=$(el).closest("tr").find("#f_primary_fee").val();
	var followupConsultFee=$(el).closest("tr").find("#f_followup_fee").val();
	var status = $(el).closest('tr').find("#f_status").val();


	$("#id").val(id);
	$("#doctorName").val(name);
	$("#qualification").val(qualification);
	$("#bmdcNo").val(bmdc);
	$("#designationId").select2("val", designationId);
	$("#departmentId").select2("val", departmentId);
	$("#specialityId").select2("val", specialityId);
	$("#primaryConsultFee").val(primaryConsultFee);
	$("#followupConsultFee").val(followupConsultFee);
	
	
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#doctorInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#doctorInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#doctorInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/doctor/save-doctor-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#doctorInfoModal").modal('hide');
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
});


	
</script>