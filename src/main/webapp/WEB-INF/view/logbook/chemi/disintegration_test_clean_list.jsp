<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: DISSOLUTION CLEAN INFO</span>
		
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
								<th class="align-center" style="width: 100px;">DATE</th>
								<th class="align-center" style="width: 100px;">TIME</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 180px;">ARN NO.</th>
								<th class="align-center" style="width: 180px;">LOT NO</th>
								<th class="align-center" style="width: 180px;">SAMPLE TYPE</th>
								<th class="align-center" style="width: 180px;">RESULT</th>
								<th class="align-center" style="width: 200px;">CLEAN BY</th>
								<th class="align-center" style="width: 180px;">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center"><fmt:formatDate var="recordDate" value="${info.recordDate}" pattern="dd-MMM-yyyy"/>
								${recordDate}</td>
								<td class="align-center">${info.recordTime}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">
								<c:choose>
							    	<c:when test="${(info.qcReference !=null) && (not empty info.qcReference)}">
									${info.qcReference}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose></td>
								<td class="align-center">${info.lotNo}</td>
								<td class="align-center"><c:choose>
							    	<c:when test="${(info.sampleTypeName !=null) && (not empty info.sampleTypeName)}">
									${info.sampleTypeName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose></td>
								<td class="align-right">${info.result}</td>
								<td class="align-left">${info.cleanByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
									<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">Verified</span>
									</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="editClean(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
									 <input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_sample_type_id" value="${info.sampleTypeId}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcReferenceId}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_result" value="${info.result}">
									
									<input type="hidden" id="r_qc_reference" value="${info.qcReference}">
									<input type="hidden" id="r_equipment_name" value="${info.equipmentName}">
									<input type="hidden" id="r_clean_by" value="${info.cleanBy}">								
									<input type="hidden" id="r_sample_type_name" value="${info.sampleTypeName}">																	
																	
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

<div class="modal fade" id="disIntegrationInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Disintegration Test Apparatus Logbook Info </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-CM-033</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">01 DEC 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                        <form method="post" id="disINtegrationAnalyzerInfoForm" modelAttribute="multiParameterInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 	   	<div class="col-md-2 align-right top">
                            	<label for="done_by">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
							<input type="text" id="equipment_name" name="" value="" class="form-control" style="width: 100%;" readonly="">
                           
     							<!--<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                       	<option value="">-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>-->
								
                           	</div>
                            </div>
	                 	</div>
					    <div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="qc_ref_id">ARN NO. :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
							
							<input type="text" id="qc_reference" name="" value="" class="form-control" style="width: 100%;" readonly="">
                               <!-- <select onchange="materialInfoByArn(this.id)" id="qc_ref_id" name="qcRefId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    </select>-->
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="sample_type_list">Type Of Sample :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
							<input type="text" id="sample_type_name" name="" value="" class="form-control" style="width: 100%;" readonly="">
                               <!-- <select  id="sample_type_list" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
			                        <option value="">-Select Type-</option>
			                        <c:forEach var="info" items="${typeList}">
			                           	<option value="${info.id }">${info.typeName}</option>
			                        </c:forEach>
			                    </select>
			                    <input type="hidden" id="sample_type_id" name="sampleTypeId" value=""/>-->
                           	</div>
                            </div>
                 		</div>
						<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="batch_no">Lot No :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="lot_no" name="lotNo" value="" class="form-control" style="width: 100%;" readonly="">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="ph">Result :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                  <input type="text" id="result" name="result" value="" class="form-control" style="width: 100%;"  readonly="">
                                	</div>
                            </div>
                 		</div>
       		            <div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="clean_by">CLEANED BY :</label>
                            </div>
                            <div class="col-md-4">
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
	                 		<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">Remarks :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250"  readonly="" placeholder="Description goes here......." ></textarea>
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
	
	$( "#operate_date" ).datepicker({
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
function materialInfoByArn(id){
	var arnNo = $("#"+id+" option:selected").text();
	$.get( "${pageContext.request.contextPath}/logbook_chemi/getMaterialInfoByArn/" + arnNo, 
	function( data ) {
		$("#sample_type_id").val(data.materialTypeId);
		$('#sample_type_list').val(data.materialTypeId).trigger('change.select2');
	});
}

function editClean(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var equipmentId  = $(el).closest("tr").find("#r_equipment_id").val();
	var sampleTypeId = $(el).closest("tr").find("#r_sample_type_id").val();
	var qcRefId 	 = $(el).closest("tr").find("#r_qc_ref_id").val();
	var result 	     = $(el).closest("tr").find("#r_result").val();
	var lotNo		 = $(el).closest("tr").find("#r_lot_no").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
	
	var qcReference 	 = $(el).closest("tr").find("#r_qc_reference").val();
	var equipmentName 	 = $(el).closest("tr").find("#r_equipment_name").val();
	var cleanBy 	 = $(el).closest("tr").find("#r_clean_by").val();
	var sampleTypeName 	 = $(el).closest("tr").find("#r_sample_type_name").val();
		
	$("#id").val(Id);
	$("#qc_reference").val(qcReference);
	$("#equipment_name").val(equipmentName);
	$("#clean_by").val(cleanBy).trigger('change.select2');
	$("#sample_type_name").val(sampleTypeName);
	//$("#equipment_id").val(equipmentId).trigger('change.select2');
	//$("#qc_ref_id").val(qcRefId).trigger('change.select2');
	//$("#sample_type_list").val(sampleTypeId).trigger('change.select2');
	//$("#sample_type_id").val(sampleTypeId);
	$("#lot_no").val(lotNo);
	$("#result").val(result);
	$("#remarks").val(remarks);
		
	$("#disIntegrationInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#disINtegrationAnalyzerInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#disINtegrationAnalyzerInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/cleanDisintegrationTest",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#disIntegrationInfoModal").modal('hide');
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
