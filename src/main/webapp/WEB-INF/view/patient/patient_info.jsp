<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">PATIENT LIST</span>
    	
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
								<th class="align-left" style="width: 250px;">PATIENT NAME</th>		
								<th class="align-left" style="width: 150px;">MOBILE NO.</th>
								<th class="align-center" style="width: 150px;">DoB</th>
								<th class="align-leftr" style="width: 135px;">GENDER</th>
								<th class="align-center" style="width: 150px;">BLOOD GROUP</th>
								<th class="align-left" style="width: 150px;">NATIONAL ID</th>
								<th class="align-center" style="width: 50px;">AGE</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_fuel_type align-left">${info.patientName}</td>
								<td class="field_fuel_qty align-left">${info.mobileNo}</td>
								<td class="field_fuel_expense align-center">
								<fmt:formatDate var="dob" value="${info.dob}" pattern="dd.MM.yyyy"/>
								${dob}</td>
								<td class="field_fuel_load_date ">
								  <c:choose>
									    <c:when test="${info.genderId =='M'}">
											<span class="badge bg-green">Male</span>
									    </c:when> 
									    <c:when test="${info.genderId =='F'}">
											<span class="badge bg-green">Female</span>
									    </c:when>   
									    <c:otherwise>
									        <span class="badge bg-red">Other</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="field_fuel_expense align-center">${info.bloodGroup}</td>
								<td class="field_fuel_expense align-left">${info.nid}</td>
								
								<td class="field_fuel_expense align-center">${info.age}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="f_name" value="${info.patientName}">
									<input type="hidden" id="f_fname" value="${info.fatherName}">
									<input type="hidden" id="f_mname" value="${info.motherName}">
									<input type="hidden" id="f_mobile" value="${info.mobileNo}">
									<fmt:formatDate var="dob" value="${info.dob}" pattern="yyyy-MM-dd"/>
									<input type="hidden" id="f_dob" value="${dob}">
									<input type="hidden" id="f_age" value="${info.age}">
									<input type="hidden" id="f_gender" value="${info.genderId}">
									<input type="hidden" id="f_blood_group" value="${info.bloodGroup}">
									<input type="hidden" id="f_marital_status" value="${info.maritalStatus}">
									<input type="hidden" id="f_nid" value="${info.nid}">
									<input type="hidden" id="f_address" value="${info.address}">
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
	
	<div class="modal fade" id="patientInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">PATIENT INFORMATION</h4>
                 </div>
                 <form method="post" id="patientInfoForm" modelAttribute="patientInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>PATIENT NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="patientName" name="patientName" value="" class="form-control upper"   autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>FATHER'S NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="fatherName" name="fatherName" value="" class="form-control upper"   autocomplete="off" required>
                            	</div>
                            </div>
	                 		<div class="col-md-4">
                            	<span><b>MOTHER'S NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="motherName" maxlength="10" name="motherName" value="" class="form-control upper"   autocomplete="off" required>
                            	</div>
                            </div>
	                 		
                            
                 		</div>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>MOBILE NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="mobileNo" maxlength="10" name="mobileNo" value="" class="form-control"   autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>DATE OF BIRTH :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="dob" name="dob" value="" class="form-control"   autocomplete="off" required>
                            	</div>
                            </div>
	                 		<div class="col-md-4">
                            	<span><b>AGE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="age" maxlength="10" name="age" value="" class="form-control"   autocomplete="off" required>
                            	</div>
                            </div>
	                 		
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>GENDER :</b></span>
                            	<div class="form-group">
	                                <select id="genderId" name="genderId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
		                        	<option value="">SELECT GENDER</option>
		                        	<option value="M">MALE</option>
		                        	<option value="F">FEMALE</option>
		                        	
		                        	</select>
                            	</div>
                            </div>
	                 		<div class="col-md-4">
                            	<span><b>BLOOD GROUP :</b></span>
                            	<div class="form-group">
	                                <select id="bloodGroup" name="bloodGroup" class="js-example-theme-single form-control" style="width: 100%;">
			                        	<option value="">SELECT BLOOD GROUP</option>
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
                            	<span><b>MARITAL STATUS :</b></span>
                            	<div class="form-group">
	                                <select id="maritalStatus" name="maritalStatus" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option value="">SELECT MARITAL STATUS</option>
			                        	<option value="1">UNMARRIED</option>
			                        	<option value="2">MARRIED</option>
		                        	</select>
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
	                 		
	                 		<div class="col-md-4">
                            	<span><b>NATIONAL ID :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="nid" maxlength="10" name="nid" value="" class="form-control"  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-8">
                            	<span><b>ADDRESS :</b></span>
                            	<div class="form-group">
                                	<textarea rows="4" id="address" name="address" class="form-control"></textarea>
                                	
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
$(".js-example-theme-single").select2({
	 
    theme: "classic"
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
    
    $( "#dob" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
});



function add(el) {
	
	
	$("#id").val("");
	$("#patientName").val("");
	$("#fatherName").val("");
	$("#motherName").val("");
	$("#mobileNo").val("");
	$("#dob").val("");
	$("#age").val("");
	$("#genderId").select2("val", "");
	$("#bloodGroup").select2("val", "");
	$("#maritalStatus").select2("val", "");
	$("#nid").val("");
	$("#address").val("");
	
	$("#patientInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {

	
	
	var id = $(el).closest("tr").find("#row_id").val();
	var patientName = $(el).closest("tr").find("#f_name").val();
	var fatherName = $(el).closest("tr").find("#f_fname").val();
	var motherName = $(el).closest("tr").find("#f_mname").val();
	var mobileNo = $(el).closest("tr").find("#f_mobile").val();
	var dob=$(el).closest("tr").find("#f_dob").val();
	var age=$(el).closest("tr").find("#f_age").val();
	var genderId=$(el).closest("tr").find("#f_gender").val();
	var bloodGroup=$(el).closest("tr").find("#f_blood_group").val();
	var maritalStatus=$(el).closest("tr").find("#f_marital_status").val();
	var nid=$(el).closest("tr").find("#f_nid").val();
	var address = $(el).closest('tr').find("#f_address").val();
	
	$("#id").val(id);
	$("#patientName").val(patientName);
	$("#fatherName").val(fatherName);
	$("#motherName").val(motherName);
	$("#mobileNo").val(mobileNo);
	$("#dob").val(convertMmDate(dob));
	$("#age").val(age);
	$("#genderId").select2("val", genderId);
	$("#bloodGroup").select2("val", bloodGroup);
	$("#maritalStatus").select2("val", maritalStatus);
	$("#nid").val(nid);
	$("#address").val(address);
	
	
	$("#patientInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#patientInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#patientInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/patient/save-patient-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#patientInfoModal").modal('hide');
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