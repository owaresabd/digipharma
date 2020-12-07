<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;"> MAINTENANCE FREQUENCY</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/frequency/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/frequency/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-left">EQUIPMENT ID</th>
								<th class="align-left">MAINTENANCE TYPE</th>
								<th class="align-left">FREQUENCY</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 75px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							 <tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_equipment_name">${info.equipmentName}</td>
								<td class="field_type_name">${info.typeName}</td>
								<td class="field_freq_type_name">${info.freqTypeName}</td>
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
									<input type="hidden" id="row_equipment_Id" value="${info.equipmentId}">
									<input type="hidden" id="row_type_id" value="${info.typeId}">
									<input type="hidden" id="row_freq_type" value="${info.freqType}">
									<input type="hidden" id="row_freq_duration" value="${info.freqDuration}">
									<input type="hidden" id="r_status" value="${info.status}">
									<input type="hidden" id="r_notify_time" value="${info.notifyTime}">
									
									
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
<div class="modal fade" id="frequencyInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel"> MAINTENANCE FREQUENCY</h4>
                 </div>
                 <form method="post" id="frequencyInfoForm" modelAttribute="frequencyInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<select id="equipmentId" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>Select Equipment</option>
				                        <c:forEach var="equipInfo" items="${equipInfos}">
				                           	<option value="${equipInfo.id }">${equipInfo.equipmentId}</option>
				                        </c:forEach>
				                        </select>
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">MAINTENANCE TYPE :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<select id="typeId" name="typeId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>Select Maintenance Type</option>
				                        <c:forEach var="typeInfo" items="${typeInfos}">
				                           	<option value="${typeInfo.id }">${typeInfo.typeName}</option>
				                        </c:forEach>
				                        </select>
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">FREQUENCY :</label>
                            </div>
                            <div class="col-md-6">
	                            
	                            <input name="freqType" type="radio" id="radio_1" value="D" class="freqRadio" checked="checked">
                                <label for="radio_1">Daily</label>
                                <input name="freqType" type="radio" id="radio_2" value="M" class="freqRadio">
                                <label for="radio_2">Monthly</label>
                                <input name="freqType" type="radio" id="radio_3" value="Y" class="freqRadio">
                                <label for="radio_3">Yearly</label>
                                <input name="freqType" type="radio" id="radio_4" value="C" class="freqRadio">
                                <label for="radio_4">Custom(Days)</label>
                            
	                           
                            </div>
                            <div class="col-md-2" id="freqDurationDiv" style="display: none;">
                            <input type="text" id="freqDuration" name="freqDuration" maxlength="2" value="" class="form-control"  autocomplete="off" >
                            
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">NOTIFICATION TIME :</label>
                            </div>
                            <div class="col-md-2">
	                            <div class="form-group">
	                            <input type="text" id="notifyTime" name="notifyTime" maxlength="2" value="" class="form-control"  autocomplete="off" >
	                            </div>
                            </div>
                            <div class="col-md-1 align-left">
                            	<label >DAYS</label>
                            </div>
                            
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right">
                            	<label for="status">ACTIVITY STATUS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="demo-checkbox">
									<input type="checkbox" id="activity_status" checked="checked" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Active ?</span></b></label>
									<input type="hidden" id="status" name="status" value="Y">
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
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>		
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>		
<script>

$(".js-example-theme-single").select2({
	escapeMarkup: function (text) { return text; },
    theme: "classic",
	placeholder: "Select from list"
	 
});
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

$("input[name='freqType']").click(function(){
    var radioValue = $("input[name='freqType']:checked").val();
    if(radioValue=='M' || radioValue=='Y' || radioValue=='C'){
    	$("#freqDurationDiv").css({ display: "block" });
    	$("#freqDuration").val('');
        
    }else{
    	
    	$("#freqDurationDiv").css({ display: "none" });
    }
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
    $('#type_name').keyup(function() {
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
	$('#equipmentId').val('').trigger('change.select2'); 
	$('#typeId').val('').trigger('change.select2'); 
	$('#radio_1').prop('checked',true);
	$('#freqDuration').val('');
	$('#notifyTime').val('');
	$("#freqDurationDiv").css({ display: "none" });
	$("#activity_status").prop('checked', true);
	$('#status').val('Y');
    $("#frequencyInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) { 
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var equipmentId	= $(el).closest("tr").find("#row_equipment_Id").val();
	var typeId 		= $(el).closest('tr').find("#row_type_id").val();
	var freqType 	= $(el).closest('tr').find("#row_freq_type").val();
	var freqDuration = $(el).closest('tr').find("#row_freq_duration").val();
	var status 		= $(el).closest('tr').find("#r_status").val();
	var notifyTime 		= $(el).closest('tr').find("#r_notify_time").val();
	
	
	$("#id").val(Id);
	$('#equipmentId').val(equipmentId).trigger('change.select2'); 
	$('#typeId').val(typeId).trigger('change.select2');
	$('#notifyTime').val(notifyTime);
	$("input[name=freqType][value=" + freqType + "]").attr('checked', 'checked');
	if(freqType=='D'){
		$("#freqDurationDiv").css({ display: "none" });
		$("#freqDuration").val('');
		$('#radio_1').prop('checked',true);
		
	}else if(freqType=='M'){
		$('#radio_2').prop('checked',true);
		$("#freqDurationDiv").css({ display: "block" });
		$("#freqDuration").val(freqDuration);
	}else if(freqType=='Y'){
		$('#radio_3').prop('checked',true);
		$("#freqDurationDiv").css({ display: "block" });
		$("#freqDuration").val(freqDuration);
	}else if(freqType=='C'){
		$('#radio_4').prop('checked',true);
		$("#freqDurationDiv").css({ display: "block" });
		$("#freqDuration").val(freqDuration);
	}else{
		
		$("#freqDurationDiv").css({ display: "block" });
		$("#freqDuration").val(freqDuration);
	}
	
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#frequencyInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#frequencyInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#frequencyInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/frequency/saveFrequencyInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#frequencyInfoModal").modal('hide');
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
