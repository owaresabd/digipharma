<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">SUPPLIER ENTRY</span>
    	<%-- <div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/service/fuelmaintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/service/fuelmaintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div> --%>
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
								<th class="align-center">SUPPLIER CODE</th>		
								<th class="align-left">SUPPLIER NAME</th>
								<th class="align-left" style="width: 135px;">CONTACT PERSON</th>
								<th class="align-leftr" style="width: 135px;">DESIGNATION</th>
								<th class="align-center" style="width: 100px;">MOBILE</th>
								<th class="align-left" style="width: 100px;">EMAIL</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_fuel_type align-center">${info.supplierCode}</td>
								<td class="field_fuel_qty">${info.supplierName}</td>
								<td class="field_fuel_expense">${info.contactPerson}</td>
								<td class="field_fuel_load_date ">${info.designation}</td>
								<td class="field_fuel_qty align-center">${info.mobile}</td>
								<td class="field_fuel_expense">${info.email}</td>
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
									<input type="hidden" id="f_supp_code" value="${info.supplierCode}">
									<input type="hidden" id="f_supp_name" value="${info.supplierName}">
									<input type="hidden" id="f_address" value="${info.address}">
									<input type="hidden" id="f_person" value="${info.contactPerson}">
									<input type="hidden" id="f_desig" value="${info.designation}">
									<input type="hidden" id="f_phone" value="${info.phoneNumber}">
									<input type="hidden" id="f_mobile" value="${info.mobile}">
									<input type="hidden" id="f_email" value="${info.email}">
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
	
	<div class="modal fade" id="supplierInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">SUPPLIER INFO</h4>
                 </div>
                 <form method="post" id="supplierInfoForm" modelAttribute="supplierInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>SUPPLIER CODE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="supplierCode" maxlength="10" name="supplierCode" value="" class="form-control" placeholder="Supplier Code"  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>SUPPLIER NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="supplierName" name="supplierName" value="" class="form-control" placeholder="Supplier name"  autocomplete="off" required>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-12 m-t--10">
                            	<span><b>ADDRESS :</b></span>
                            	<div class="form-group">
                                	<textarea rows="2" id="address_detail" class="form-control" placeholder="Address here......." required></textarea>
                                	<input type="hidden" id="address" name="address" value="">
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>CONTACT PERSON :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="contactPerson" name="contactPerson" value="" class="form-control" placeholder="Contact Person"  autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>DESIGNATION :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="designation" name="designation" value="" class="form-control" placeholder="Designation"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>PHONE NUMBER :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="phoneNumber" name="phoneNumber" value="" class="form-control" placeholder="Phone Number"  autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>MOBILE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="mobile" name="mobile" value="" class="form-control" placeholder="Mobile"  autocomplete="off" required>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>EMAIL :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="email" name="email" value="" class="form-control" placeholder="Email"  autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
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

$(function() {
    $('#supplierCode').keyup(function() {
        this.value = this.value.toUpperCase();
    });
});
$('#address_detail').keyup(function() {
	$("#address").val(this.value);
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

$('#supplierCode').keyup(function() {
	var supplierCode = $("#supplierCode").val();
	$.get( "${pageContext.request.contextPath}/supplier/validate-supplierCode/" + supplierCode, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate brake type code available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
	});
});

function add(el) {
	
	
	$("#id").val("");
	$("#supplierCode").val("");
	$("#supplierName").val("");
	$("#address_detail").val("");
	$("#address").val("");
	$("#contactPerson").val("");
	$("#designation").val("");
	$("#phoneNumber").val("");
	$("#mobile").val("");
	$("#email").val("");
	$("#activity_status").prop('checked', false);
	$('#status').val('N'); 
    $("#supplierInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	
	var Id = $(el).closest("tr").find("#row_id").val();
	var supplierCode = $(el).closest("tr").find("#f_supp_code").val();
	var supplierName = $(el).closest("tr").find("#f_supp_name").val();
	var address = $(el).closest("tr").find("#f_address").val();
	var contactPerson=$(el).closest("tr").find("#f_person").val();
	var designation=$(el).closest("tr").find("#f_desig").val();
	var phoneNumber=$(el).closest("tr").find("#f_phone").val();
	var mobile=$(el).closest("tr").find("#f_mobile").val();
	var email=$(el).closest("tr").find("#f_email").val();
	var status = $(el).closest('tr').find("#f_status").val();
	
	$("#id").val(Id);
	$("#supplierCode").val(supplierCode);
	$("#supplierName").val(supplierName);
	$("#address_detail").val(address);
	$("#address").val(address);
	$("#contactPerson").val(contactPerson);
	$("#designation").val(designation);
	$("#phoneNumber").val(phoneNumber);
	$("#mobile").val(mobile);
	$("#email").val(email);
	
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#supplierInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#supplierInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#supplierInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/supplier/save-suppliers",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#supplierInfoModal").modal('hide');
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

function del(el) {
	var id = $(el).closest('tr').find("#row_id").val();
	swal({
        title: "Are you sure?",
        text: "You will not be able to recover this data!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/supplier/delete-suppliers/" + id ,
				success : function(data) {
					$("#view_page").html(data);
					sweetAlert("Deleted!", "Deleted Successfully", "success", 1000, false);
				},
				error: function(){
					sweetAlert("Failed!", "Something going wrong.", "fail", 1000, false);
			  	}
			});
        } else {
        	sweetAlert("Cancelled", "Your data is safe :)", "error", 1000, false);
        }
    });
}
	
</script>