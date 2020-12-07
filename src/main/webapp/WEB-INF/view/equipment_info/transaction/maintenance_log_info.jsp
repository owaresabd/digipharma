<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<style>
/* .modal-header {
    padding: 5px !important;
    } */
</style>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">MAINTENANCE LOG INFO</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/equipment/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/equipment/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<c:if test="${not empty infos}">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right m-r-10" id="btnPrint" onClick="printReport(this)" data-toggle="tooltip" title="Print">
						<i class="material-icons">print</i>
					</button>
					</c:if>
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="countryList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-left" style="width: 120px;">EQUIPMNET ID</th>
								<th class="align-left">EQUIPMNET NAME</th>
								<th class="align-left" style="width: 120px;">MAINTENANCE TYPE</th>
								<th class="align-center">CURRENT LOCATION</th>
								<th class="align-center" style="width: 110px;">SCHEDULE</th>
								<th class="align-center">ATTACHMENT</th>
								<th class="align-center" style="width: 100px;">REMARKS</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.equipmentName}</td>
								<td class="align-left">${info.equipFullName}</td>
								<td class="align-left">${info.typeName}</td>
								<td class="align-center">${info.currLocation}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.lastSchedule}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-center">
									<c:choose>
									    <c:when test="${not empty (info.attachmentNm)}">
											<a href="${pageContext.request.contextPath}/image/equipment_info_doc/equip_maintenance/${info.id}-${info.attachmentNm}" 
											class="btn btn-xs btn-success waves-effect"><i class="fa fa-download"></i></a>
									    </c:when>
									    <c:otherwise>
									        <span class="badge bg-orange">Empty</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">${info.remarks}</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="equipMaintenInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 <table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Maintenance Log Information</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">FM-DIL-GN-071</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td style="text-align:center; width:35px">00</td>
				    <td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 Jan 2020</td>
                 	</tr>             	
                 	</tbody>
                 </table>
                   
             </div>
             <form method="post" id="equipMaintenInfoForm" enctype="multipart/form-data" onkeypress="if (event.keyCode == 13) { return false; }">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<select id="equipment_list" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipmentList}">
				                           	<option value="${info.equipmentId }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="equipment_id" name="equipmentId" value=""/>
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="">MAINTENANCE TYPE :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<select id="type_list" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        <option>-Select Maintenance Type-</option>
				                    </select>
				                    <input type="hidden" id="type_id" name="typeId" value=""/>
				                    <input type="hidden" id="freq_type" name="freqType" value=""/>
				                    <input type="hidden" id="freq_duration" name="freqDuration" value=""/>
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="last_schedule">LAST SCHEDULE :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                                <input type="text" id="last_schedule" name="lastSchedule" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                 		</div>
                 		<!-- <div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="">NEXT SCHEDULE :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                                <input type="text" id="next_schedule" name="nextSchedule" value="" class="form-control" style="width: 30%;" autocomplete="off" readonly="readonly">
                            	</div>
                            </div>
                 		</div> -->
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="">ATTACHMENT :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                                <div class="input-group">
										<span class="input-group-btn">
											<span class="btn btn-default btn-file">
												Browse… <input type="file" id="img_attachment" name="img_attachment" value="" onchange="readURL(this);">
											</span>
										</span>
										<input type="text" id="attachment_image" name="attachmentNm" class="form-control" readonly="readonly">
										<input type="hidden" id="e_image" name="e_image">
									</div>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                            	<textarea rows="2" id="remarks" name="remarks" maxlength="250" class="form-control" placeholder="Description goes here......." ></textarea>
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
							<span>SAVE</span>
						</button>
	                </div>
                 </form>
             </div>
         </div>
     </div>
     
     <div class="modal fade" id="equipmentViewModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
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

$(document).on('change', '.btn-file :file', function() {
	var input = $(this),
		label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [label]);
});

$('.btn-file :file').on('fileselect', function(event, label) {
	    
	    var input = $(this).parents('.input-group').find(':text'),
	        log = label;
	    
	    if( input.length ) {
	        input.val(log);
	    } else {
	        if( log ) alert(log);
	    }
    
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.readAsDataURL(input.files[0]);
    }
}

$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#last_schedule" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
    $( "#next_schedule" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
});

function setMaintenanceInfo(id){
	$("#type_list").empty().trigger('');
	$.get( "${pageContext.request.contextPath}/equipment/maintenanceByEquipment/" + id, 
	function( data ) {
		$('#type_list').append('<option value=""></option>');
		for (var i = 0; i < data.length; i++) {
			$('#type_list').append('<option value="'+data[i].typeId+'">'+data[i].typeName+'</option>');
		}
	});
}

function setFrequenceType(equpId,typeId){
	
	$.get( "${pageContext.request.contextPath}/equipment/frequenceTypeById?equpId="+equpId+'&typeId='+typeId, 
	function( data ) {
		$("#freq_type").val(data.freqType);
		$("#freq_duration").val(data.freqDuration);
	});
}

$("#equipment_list").on('change', function(){
	var id = $('option:selected', this).val();
	var text = $('option:selected', this).text();
	$("#equipment_id").val(id);
	$('#type_id').val('');
	$('#type_list').val('').trigger('change.select2');
	$(".alert").empty().addClass("hidden");
	setMaintenanceInfo(id);
});

$("#type_list").on('change', function() {
	var typeId = $('option:selected', this).val();
	var equpId = $("#equipment_id").val();
	$("#type_id").val(typeId);
	setFrequenceType(equpId,typeId);
});

$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search from list.."
});

function addEquipment(el) {
	$("#id").val("");
	$("#equipment_id").val("");
	$("#equipment_list").val('').trigger('change.select2');
	$('#type_id').val('');
	$('#type_list').val('').trigger('change.select2');
	$("#freq_type").val("");
	$("#freq_duration").val("");
	$("#last_schedule").datepicker("setDate", new Date());
	$("#next_schedule").datepicker("setDate", '');
	$("#img_attachment").attr('src', "");
	$("#attachment_image").val("");
	$("#e_image").val("");
	$("#remarks").val("");
	
    $("#equipMaintenInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var equipmentId	= $(el).closest("tr").find("#r_equipmentId").val();
	var typeId		= $(el).closest("tr").find("#r_typeId").val();
	var typeName	= $(el).closest("tr").find("#r_typeName").val();
	var freqType	= $(el).closest("tr").find("#r_freqType").val();
	var freqDuration = $(el).closest("tr").find("#r_freqDuration").val();
	var lastSchedule = $(el).closest("tr").find("#r_lastSchedule").val();
	var nextSchedule = $(el).closest("tr").find("#r_nextSchedule").val();
	var attachmentNm= $(el).closest("tr").find("#r_attachment_image").val();
	var remarks		= $(el).closest("tr").find("#r_remarks").val();
	
	$("#id").val(Id);
	$("#equipment_id").val(equipmentId);
	$("#equipment_list").val(equipmentId).trigger('change.select2');
	$('#type_id').val(typeId);
	$('#type_list').empty();
	$('#type_list').append('<option value="'+typeId+'">'+typeName+'</option>');
	$('#type_list').val(typeId).trigger('change.select2');
	$("#freq_type").val(freqType);
	$("#freq_duration").val(freqDuration);
	if (lastSchedule == "") {
		$('#last_schedule').datepicker('setDate', new Date());
	} else {
		$('#last_schedule').datepicker('setDate', convertMmDate(lastSchedule));
	}
	if (nextSchedule == "") {
		$('#next_schedule').datepicker('setDate', '');
	} else {
		$('#next_schedule').datepicker('setDate', convertMmDate(nextSchedule));
	}
	$("#img_attachment").val("");
	$("#img_attachment").attr('src', "${pageContext.request.contextPath}/image/equipment_info_doc/equip_maintenance/"+Id+"-"+attachmentNm);
	$("#attachment_image").val(attachmentNm);
	$("#e_image").val(Id+"-"+attachmentNm);
	$("#remarks").val(remarks);
	
	$("#equipMaintenInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#equipMaintenInfoForm").submit(function(event){
	event.preventDefault();
	var formData = new FormData($("#equipMaintenInfoForm")[0]);
    //var formData = $("#equipMaintenInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/equipment/save-equipment-mainten-infos",
	        type: 'POST',
	        data: formData,
	        enctype: 'multipart/form-data',
	        async: false,
	        processData: false,
            contentType: false,
            cache: false,
	        success: function (data) {				 
	        	$("#equipMaintenInfoModal").modal('hide');
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

function printReport(el) {
	var ajaxURL = "${pageContext.request.contextPath}/equipment/equipment-maintenance-print";
	
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
    
function equipmentInfoPrint(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	var udId = $(el).closest("tr").find("#s_equipmentId").val();
	var ajaxURL = "${pageContext.request.contextPath}/equipment/equipment-info-print?equipmentId="+id+'&udEquipId='+udId;
	
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