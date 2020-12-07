<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">REFRIGERATOR LOGBOOK INFO</span>
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
								<th class="align-center" style="width: 100px;">NAME OF ITEM</th>
								<th class="align-center" style="width: 120px;">AR No.</th>
								<th class="align-center" style="width: 100px;">EQUIPMENT NAME</th>
								<th class="align-center" style="width: 100px;">SAMPLE TYPE</th>
								<th class="align-center" style="width: 110px;">DATE (In Chamber)</th>
								<th class="align-center" style="width: 50px;">REMAIN QUANTITY</th>
								<th class="align-center" style="width: 100px;">DISCARDED QUANTITY</th>
								<th class="align-center" style="width: 100px;">DISCARDED QUANTITY UNIT</th>
								
								
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">DISCARDED</th>
								<!-- <th class="align-center" style="width: 100px;">UPDATE</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.materialName}</td>
								<td class="align-center">${info.qcRefName}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">${info.sampleTypeName}</td><td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.inDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-center">${info.qty}</td>
								<td class="align-center">${info.discardQty}</td>
								<td class="align-center">${info.disUnitName}</td>
								<td class="align-center">${info.remarks} </td>
								
								
								<td class="align-center">
									<c:choose>
									    <c:when test="${info.isVerify == 'Y'}">
											<span class="badge bg-green">Verified</span>
									    </c:when>
									<c:otherwise>
									<a type="button" class="bg-blue btn-edit btn btn-xs" onclick="keepout(this)"  title="keep out">
										<i class="material-icons">games</i>
									</a>
									<input type="hidden" id="row_id_out" value="${info.id}">
									<input type="hidden" id="r_sample_name_out" value="${info.sampleName}">
									<input type="hidden" id="r_sample_type_out" value="${info.sampleTypeName}">
									<input type="hidden" id="r_qc_ref_out" value="${info.qcRefName}">
									<input type="hidden" id="r_equipment_out" value="${info.equipmentName}">
									<input type="hidden" id="r_qty_out" value="${info.qty}">
									<input type="hidden" id="r_dis_unit_id" value="${info.disUnitId}">
									<input type="hidden" id="r_material_name" value="${info.materialName}">
									
									</c:otherwise>
									</c:choose>
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
<div class="modal fade" id="refrigeratorInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Refrigerator</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-CM-012</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="refrigeratorInfoForm" modelAttribute="refrigeratorInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="sample_name_id">Name Of Items :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select onchange="sampleInfoById(this.id)" id="sample_type_id" name="sampleNameId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Sample-</option>
			                        <c:forEach var="info" items="${agentInfos}">
			                           	<option value="${info.id}">${info.productName}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
                            <div class="col-md-2 align-right top">
                            	<label for="sample_type_list">Type Of Sample :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="sample_type_name" name="sampleTypeId" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Type-</option>
			                        <c:forEach var="info" items="${typeList}">
			                           	<option value="${info.id}">${info.typeName}</option>
			                        </c:forEach>
			                    </select>
			                   <!--  <input type="hidden" id="sample_type_id" name="sampleTypeId" value=""/> -->
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="qc_ref_id">AR No. :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="qc_ref_id" name="qcRefId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
                            
	                 		<div class="col-md-2 align-right top">
                            	<label for="equipment_id">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option value="">-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentId}</option>
				                        </c:forEach>
				                    </select>
                            </div>
                 		</div>
                 		
                 		<div class="col-md-2 align-right top">
                            	<label for="qty">Quantity :</label>
                            </div>
                            <div class="form-group">
                                <input type="text" id="qty" name="qty" value="" class="form-control" style="width: 30%; margin-left: 18.5%" autocomplete="off" required="required">
                           	</div>
                           	
                           	<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">Remarks :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="230" placeholder="Description goes here......." ></textarea>
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
     
     
     <div class="modal fade" id="refrigeratorOutModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Refrigerator Logbook Info </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-CM-012</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="refrigeratorInfoOutForm" modelAttribute="refrigeratorInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id_out" name="idOut" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="sample_name_id">Name Of Items :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
			                    <input type="text" id="sample_type_id_out" value="" class="form-control" style="border: none;" autocomplete="off" disabled="disabled">
                           	</div>
                            </div>
                            <div class="col-md-2 align-right top">
                            	<label for="sample_type_list">Type Of Sample :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
			                   <input type="text" id="sample_type_name_out" value="" class="form-control" style="border: none;" autocomplete="off" disabled="disabled">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="qc_ref_id">Q.C Ref No :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                           		<input type="text" id="qc_ref_id_out" value="" class="form-control" style="border: none;" autocomplete="off" disabled="disabled">
                           	</div>
                            </div>
                            
	                 		<div class="col-md-2 align-right top">
                            	<label for="equipment_id">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	
				                    <input type="text" id="equipment_id_out" value="" class="form-control" style="border: none;" autocomplete="off" disabled="disabled">
                            </div>
                 		</div>
                 			</div>
                 		<div class="row">
	                 
                 		      <div class="col-md-2 align-right top">
                            	<label for="qty">Quantity :</label>
                            </div>
                             <div class="col-md-4">
	                       <div class="form-group">
                            <input type="hidden" id="qty_out_in" name="qtyOut" value=""/>
                            <input type="text" id="qty_out" class="form-control" style="width: 30%; margin-left: " autocomplete="off"  disabled="disabled">
                          	</div>
							</div>
                      
                           	<div class="col-md-2 align-right top">
                            	<label for="qty">Discarded Quantity :</label>
                            </div>
                           <div class="col-md-2">
						    <div class="form-group">
                                <input type="text" id="discard_qty_out" name="discardQtyOut" value="" class="form-control number" style="width: 100%; margin-left: " autocomplete="off" required="required">
                           	</div>
							</div>
							<div class="col-md-2">
                            <div class="form-group">
	                                <select id="disUnitId" name="disUnitId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id}">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
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
	
	$( "#in_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#out_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#discard_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
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

function sampleInfoById(id){
	var sampleId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/logbook_chemi/getSampleInfoById/" + sampleId, 
	function( data ) {
		
		$("#sample_type_id").val(data.materialTypeId);
		$('#sample_type_list').val(data.materialTypeId).trigger('change.select2');
	});
}

function discardedQuantity(el) { 
	$("#id_out").val("");
	$("#discard_qty_out").val("");
	$("#qty_out_in").val("");
	
    $("#refrigeratorOutModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function add(el) { 
	$("#id").val("");
	$("#disUnitId").val("").trigger('change.select2');
	$("#sample_name_id").val("").trigger('change.select2');
	$("#sample_type_list").val("").trigger('change.select2');
	$("#qc_ref_id").val("").trigger('change.select2');
	$("#equipment_id").val("").trigger('change.select2')
	$("#qty").val("");
	$("#remarks").val("");
    $("#refrigeratorInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var sampleNameId = $(el).closest("tr").find("#r_sample_name_id").val();
	var sampleTypeId = $(el).closest("tr").find("#r_sample_type_id").val();
	var qcRefId 	 = $(el).closest("tr").find("#r_qc_ref_id").val();
	var equiepmetId = $(el).closest("tr").find("#r_equipment_id").val();
	var inDate 		 = $(el).closest("tr").find("#r_in_date").val();
	var outDate		 = $(el).closest("tr").find("#r_out_date").val();
	var discardDate	 = $(el).closest("tr").find("#r_discard_date").val();
	var qty 		 = $(el).closest("tr").find("#r_qty").val();
	var discardQty 	 = $(el).closest("tr").find("#r_discard_qty").val();
	var discardBy 	 = $(el).closest("tr").find("#r_discard_by").val();
	var verifyBy 	 = $(el).closest("tr").find("#r_verify_by").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
	var disUnitId 	 = $(el).closest("tr").find("#r_dis_unit_id").val();
		
	$("#id").val(Id);
	$("#disUnitId").val(disUnitId).trigger('change.select2');
	$("#sample_type_id").val(sampleNameId).trigger('change.select2');
	$("#sample_type_name").val(sampleTypeId).trigger('change.select2');
	$("#qc_ref_id").val(qcRefId).trigger('change.select2');
	$("#equipment_id").val(equiepmetId).trigger('change.select2');
	$("#qty").val(qty);
	$('#in_date').datepicker('setDate', convertMmDate(inDate));
	$('#out_date').datepicker('setDate', convertMmDate(outDate));
	$('#discard_date').datepicker('setDate', convertMmDate(discardDate));
	$("#discard_qty").val(discardQty);
	$("#discard_by").val(discardBy).trigger('change.select2');
	$("#verify_by").val(verifyBy).trigger('change.select2');
	$("#remarks").val(remarks);
		
	$("#refrigeratorInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};


function keepout(el) {
	var Id 			 = $(el).closest("tr").find("#row_id_out").val();
	var sampleNameId = $(el).closest("tr").find("#r_sample_name_out").val();
	var sampleTypeId = $(el).closest("tr").find("#r_sample_type_out").val();
	var qcRefId 	 = $(el).closest("tr").find("#r_qc_ref_out").val();
	var equiepmetId = $(el).closest("tr").find("#r_equipment_out").val();
	var qty 		 = $(el).closest("tr").find("#r_qty_out").val();
	var materialName 	 = $(el).closest("tr").find("#r_material_name").val();
	
	$("#sample_type_id_out").val(materialName);
	$("#id_out").val(Id);
	//$("#sample_type_id_out").val(sampleNameId).trigger('change.select2');
	$("#sample_type_name_out").val(sampleTypeId).trigger('change.select2');
	$("#qc_ref_id_out").val(qcRefId).trigger('change.select2');
	$("#equipment_id_out").val(equiepmetId).trigger('change.select2');
	$("#qty_out").val(qty);
	$("#qty_out_in").val(qty);
	
		
	$("#refrigeratorOutModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};


$("#refrigeratorInfoOutForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#refrigeratorInfoOutForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/discardedQuantityRefrigeratorInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#refrigeratorOutModal").modal('hide');
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

$("#refrigeratorInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#refrigeratorInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/keepInRefrigeratorInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#refrigeratorInfoModal").modal('hide');
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