<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">AREA CLEANING CLEAN LOGBOOK INFO</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 110px;">DATE OF CLEANING</th>
								<th class="align-center" style="width: 110px;">START TIME</th>
								<th class="align-center" style="width: 110px;">END TIME</th>
								<th class="align-center" style="width: 100px;">CLEANING AGENT</th>
								<th class="align-center" style="width: 110px;">EXPIRY DATE OF CLEANING AGENT</th>
								<th class="align-center" style="width: 100px;">CLEANING EQUIPMENT</th>
								<th class="align-center" style="width: 100px;">CLEANED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${areaCleanInfos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.cleaningDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-center">${info.startTime}</td>
								<td class="align-center">${info.endTime}</td>
								<td class="align-center">${info.agentName}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.cleaningExpDate}" var="expDateVal" />
	                              	<c:out value="${expDateVal}"/>
								</td>
								<td class="align-center">${info.cleaningEquipment}</td>
								<td class="align-left">${info.cleanByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
								<a class="btn-edit btn btn-xs" onclick="editClean(this)"><i class="material-icons">mode_edit</i></a>
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_agent_id" value="${info.agentId}">
									<input type="hidden" id="r_cleaning_equipment_id" value="${info.cleaningEquipID}">
									<input type="hidden" id="r_start_time" value="${info.startTime}">
									<input type="hidden" id="r_end_time" value="${info.endTime}">
									<input type="hidden" id="r_cleaning_date" value="${info.cleaningDate}">
									<input type="hidden" id="r_exp_date" value="${info.cleaningExpDate}">
									<input type="hidden" id="r_clean_by" value="${info.cleanBy}">
									<input type="hidden" id="r_verify_by" value="${info.verifyBy}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									
									<input type="hidden" id="r_clean_by" value="${info.cleanBy}">								
									<input type="hidden" id="r_agent_name" value="${info.agentName}">
									<input type="hidden" id="r_cleaning_equipment_name" value="${info.cleaningEquipment}">
									
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
<div class="modal fade" id="areaCleanInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog  modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book: Area Cleaning </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-GN-018</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="areaCleanInfoForm" modelAttribute="areaCleanInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="agent_id">Cleaning Agent Name :</label>
                            </div>
                     	    <div class="col-md-3">
                           	<div class="form-group">
                           	  <input type="text" id="agent_name" name="Input" value="" disabled="disabled"/>
                           	  <!--<select  id="agent_id" name="" onchange="expDateSetup(this.value,'exp_date','agent')" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Agent-</option>
			                        <c:forEach var="info" items="${cleaningAgentInfos}">
			                           	<option value="${info.id }@@@${info.expDate }">${info.agentName}</option>
			                        </c:forEach>
			                    </select>-->
                           	</div>
                            </div>
                            <div class="col-md-3 align-right top">
                            	<label for="cleaning_equipment_id">Cleaning Equipment :</label>
                            </div>
                            <div class="col-md-3">
							     	<div class="form-group">
									<input type="text" id="cleaning_equipment_name" name="" value="" disabled="disabled"/>
                               <!-- <select  id="cleaning_equipment_id" name="cleaningEquipID" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Agent-</option>
			                        <c:forEach var="info" items="${equipInfos}">
			                           	<option value="${info.id }">${info.equipmentName}</option>
			                        </c:forEach>
			                    </select>-->
                           	</div>
                      
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="start_time">Start Time :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="start_time" name="startTime" value="" class="form-control" style="width: 80%;" disabled="disabled">
                           	</div>
                            </div>
                            
                            <div class="col-md-3 align-right top">
                            	<label for="end_time">End Time :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="end_time" name="endTime" value="" class="form-control" style="width: 80%;" disabled="disabled">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-3 align-right top">
                            	<label for="cleaning_date">Date of Cleaning :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="cleaning_date" name="cleaningDate" value="" class="form-control" style="width: 80%;" disabled="disabled">
                           	</div>
                            </div>
	                 		
	                 		<div class="col-md-3 align-right top">
                            	<label for="exp_date">Expiry Date of Cleaning :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="exp_date" name="cleaningExpDate" value="" class="form-control" style="width: 80%;" disabled="disabled">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="clean_by">CLEANED BY :</label>
                            </div>
                            <div class="col-md-3">
                           	<div class="form-group">
                                <select  id="clean_by" name="cleanBy" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.empId }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
	             		</div>
                 	
                 		<div class="row">
	                 		<div class="col-md-3 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-9 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" disabled="disabled" placeholder="Description goes here......." ></textarea>
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
$(".js-example-theme-single").select2({
    theme: "classic"
});		


function expDateSetup(val, dt1, val2){
         var valSplt=val.split('@@@');	
		 $('#'+val2).val($.trim(valSplt[0]))
		 $('#'+dt1).val(convertMmDate(valSplt[1]));
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
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$(function() {
    $('#ehd_no').keyup(function() {
        this.value = this.value.toUpperCase();
    });
    
});

$('#ehd_no').keyup(function() {
	var ehdNo = $("#ehd_no").val();
	$.get( "${pageContext.request.contextPath}/logbook_chemi/validate-ehdNo/" + ehdNo, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate EHD ID Available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
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

/*function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

	$("#productCode").val(data.materialCode);
	$('#productUnitId').val(data.unitId).trigger('change.select2');

	});
	}*/


function editClean(el) {
	var Id = $(el).closest("tr").find("#row_id").val();
	//var agentId = $(el).closest("tr").find("#r_agent_id").val();
	var startTime = $(el).closest("tr").find("#r_start_time").val();
	var endTime = $(el).closest("tr").find("#r_end_time").val();
	var cleaningDate = $(el).closest("tr").find("#r_cleaning_date").val();
	var expDate = $(el).closest("tr").find("#r_exp_date").val();
	var remarks = $(el).closest("tr").find("#r_remarks").val();
	var cleanBy = $(el).closest("tr").find("#r_clean_by").val();
	var agentName = $(el).closest("tr").find("#r_agent_name").val();
    var cleaningEquipName = $(el).closest("tr").find("#r_cleaning_equipment_name").val();
	
	$("#id").val(Id);
	
	$("#agent_name").val(agentName);
//	$("#agent").val(agentId);
	
	//$("#agent").val(agentId);
	//$("#agent_id").val(agentId+"@@@"+expDate).trigger('change.select2');
	//$("#cleaning_equipment_id").val(cleaningEquip).trigger('change.select2');
	$("#cleaning_equipment_name").val(cleaningEquipName).trigger('change.select2');
	
	$("#clean_by").val(cleanBy).trigger('change.select2');
	$("#start_time").val(startTime);
	$("#end_time").val(endTime);
	$('#cleaning_date').datepicker('setDate', convertMmDate(cleaningDate));
	$('#exp_date').datepicker('setDate', convertMmDate(expDate));
	$("#remarks").val(remarks);
		
	$("#areaCleanInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#areaCleanInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#areaCleanInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/cleanAreaClean",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#areaCleanInfoModal").modal('hide');
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
