<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">CLIENT ENTRY</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/client/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/client/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 100px;">CLIENT ID</th>
								<th class="align-left">CLIENT NAME</th>
								<th class="align-left">EMAIL</th>
								<th class="align-center" style="width: 120px;">MOBILE NO</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 75px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${clientList}">
							<tr>
								<td class="field_type_code align-center">${info.udClientId}</td>
								<td class="field_type_name">${info.clientName}</td>
								<td class="field_type_name">${info.email}</td>
								<td class="field_type_name  align-center">${info.mobileNo}</td>
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
									<input type="hidden" id="r_ud_client_id" value="${info.udClientId}">
									<input type="hidden" id="r_client_name" value="${info.clientName}">
									<input type="hidden" id="r_address" value="${info.address}">
									<input type="hidden" id="r_mobile_no" value="${info.mobileNo}">
									<input type="hidden" id="r_email" value="${info.email}">
									<input type="hidden" id="r_status" value="${info.status}">
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
<div class="modal fade" id="clientInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">CLIENT INFO</h4>
                 </div>
                 <form method="post" id="clientInfoForm" modelAttribute="clientInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="ud_client_id">CLIENT ID :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <input type="text" id="ud_client_id" name="udClientId" value="" class="form-control" placeholder="Client ID" required="required" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="client_name">CLIENT NAME :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                            	<input type="text" id="client_name" name="clientName" value="" class="form-control" placeholder="Client Name" required="required" autocomplete="off">
                            </div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="address">ADDRESS :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                            	<input type="text" id="address" name="address" value="" class="form-control" placeholder="Client Address" autocomplete="off">
                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="mobile_no">MOBILE NO :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                            	<input type="text" id="mobile_no" name="mobileNo" value="" class="form-control" placeholder="Mobile No" autocomplete="off">
                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="email">EMAIL :</label>
                            </div>
                            <div class="col-md-8">
                            <div class="form-group">
                            	<input type="text" id="email" name="email" value="" class="form-control" placeholder="Email Address" autocomplete="off">
                            </div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right">
                            	<label for="status">ACTIVITY STATUS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="demo-checkbox">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            </div>
                 		</div>
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
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

$(function() {
    $('#ud_client_id').keyup(function() {
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

$('#ud_client_id').change(function() {
	var clientId = $("#ud_client_id").val();
	$.get( "${pageContext.request.contextPath}/client/validate-clientId/" + clientId, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate Client ID code available!");
	    	$('#ud_client_id').focus();
		} else {
			$(".alert-code").empty().addClass("hidden");
			$('#ud_client_id').blur();
			console.log("no duplicate code");
		}
	});
});

function add(el) {
	$("#id").val("");
	$("#ud_client_id").val("");
	$("#ud_client_id").removeAttr('readonly');
	$("#client_name").val("");
	$("#address").val("");
	$("#mobile_no").val("");
	$("#email").val("");
	$("#activity_status").prop('checked', true);
	$('#status').val('Y');
	$('.check-status').text('Active ?');
    $("#clientInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id = $(el).closest("tr").find("#row_id").val();
	var udClientId = $(el).closest("tr").find("#r_ud_client_id").val();
	var clientName = $(el).closest("tr").find("#r_client_name").val();
	var address = $(el).closest("tr").find("#r_address").val();
	var mobileNo = $(el).closest("tr").find("#r_mobile_no").val();
	var email = $(el).closest("tr").find("#r_email").val();
	var status = $(el).closest('tr').find("#r_status").val();
	
	$("#id").val(Id);
	$("#ud_client_id").val(udClientId);
	$("#ud_client_id").attr('readonly', 'readonly');
	$("#client_name").val(clientName);
	$("#address").val(address);
	$("#mobile_no").val(mobileNo);
	$("#email").val(email);
	
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
	
	$("#clientInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#clientInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#clientInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/client/save-client-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#clientInfoModal").modal('hide');
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
    }  else {
    	sweetAlert("Cancelled", "", "error", 0, false);
    }

    });
});


	
</script>
