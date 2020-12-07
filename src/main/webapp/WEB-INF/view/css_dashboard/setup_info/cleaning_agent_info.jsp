<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">CLEANING AGENT INFO</span>
		
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
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 100px;">AGENT ID</th>
								<th class="align-center" style="width: 190px;">AGENT NAME</th>
								<th class="align-center" style="width: 110px;">MFG. DATE</th>
								<th class="align-center" style="width: 110px;">EXP. DATE</th>
								<th class="align-center" style="width: 150px;">REMARKS</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 75px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.agentId}</td>
								<td class="align-center">${info.agentName}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.manufacDate}" var="manufacDate" />
								${manufacDate}
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.expDate}" var="expDate" />
								${expDate}
								</td>
								<td class="align-center">${info.remarks}</td>
								<td class="field_type_status align-center" width="100px">
									<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">ACTIVE</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">INACTIVE</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_agent_id" value="${info.agentId}">
									<input type="hidden" id="r_agent_name" value="${info.agentName}">
									<input type="hidden" id="r_mfg_date" value="${info.manufacDate}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									<input type="hidden" id="r_status" value="${info.status}">
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
<div class="modal fade" id="agentInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                  <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">CLEANING AGENT INFO</h4>
                 </div>
             
                 <form method="post" id="agentInfoForm" modelAttribute="logBookInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="logbook_name">AGENT ID:</label>
                            </div>
                            <div class="col-md-9">
                            <div class="form-group">
                                <input type="text" id="agentId" name="agentId" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="document_no">AGENT NAME :</label>
                            </div>
                            <div class="col-md-9">
                            <div class="form-group">
                                <input type="text" id="agentName" name="agentName" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="revision_no">MFG. DATE :</label>
                            </div>
                            <div class="col-md-9">
                            <div class="form-group">
                                <input type="text" id="manufacDate" name="manufacDate" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="revision_no">EXP. DATE :</label>
                            </div>
                            <div class="col-md-9">
                            <div class="form-group">
                                <input type="text" id="expDate" name="expDate" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-3 align-right">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-9">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" placeholder="Description goes here......." ></textarea>
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3 align-right">
                            	<label for="status">ACTIVITY STATUS :</label>
                            </div>
                            <div class="col-md-9">
                            	<div class="demo-checkbox">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
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
	
	$( "#manufacDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#expDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	
	
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
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
	$("#agentId").val("");
	$("#agentName").val("");
	$("#manufacDate").val("");
	$("#expDate").val("");
	$("#remarks").val("");
	$("#activity_status").prop('checked', true);
	$('#status').val('Y');
	$('.check-status').text('Active ?');
	
    $("#agentInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var agentId = $(el).closest("tr").find("#r_agent_id").val();
	var agentName 	= $(el).closest("tr").find("#r_agent_name").val();
	var mfgDate 	= $(el).closest("tr").find("#r_mfg_date").val();
	var expDate 	= $(el).closest("tr").find("#r_exp_date").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var status 		= $(el).closest("tr").find("#r_status").val();
		
	$("#id").val(Id);
	$("#agentId").val(agentId);
	$("#agentName").val(agentName);
	$("#manufacDate").val(convertMmDate(mfgDate));
	$("#expDate").val(convertMmDate(expDate));
	$("#remarks").val(remarks);
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
	
	$("#agentInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#agentInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#agentInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/cleaningAgent/saveCleaningAgentInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#agentInfoModal").modal('hide');
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

	
</script>
