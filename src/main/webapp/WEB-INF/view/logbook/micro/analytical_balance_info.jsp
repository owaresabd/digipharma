<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">ANALYTICAL BALANCE LOGBOOK INFO</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/reference/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/reference/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
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
								<th class="align-center" style="width: 100px;">DATE</th>
								<th class="align-center" style="width: 100px;">TIME</th>
								<th class="align-center" style="width: 100px;">Daily Check/ Intermediate Check</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 180px;">ARN NO </th>
								<th class="align-center" style="width: 100px;">LOT NO</th>
								<th class="align-center" style="width: 100px;">SAMPLE TYPE</th>
								<th class="align-center" style="width: 100px;">QTY</th>
								<th class="align-center" style="width: 100px;">DONE BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center"><fmt:formatDate var="recordDate" value="${info.recordDate}" pattern="dd-MMM-yyyy"/>
								   ${recordDate}
								</td>
								<td class="align-center">${info.recordTime}</td>
								<td class="align-center">
									<c:choose>
									<c:when test="${info.checkType =='N'}">
									 N/A
									</c:when>
									<c:when test="${info.checkType =='I'}">
									Intermediate
									</c:when>
									<c:when test="${info.checkType =='D'}">
									 Daily
									</c:when>
									<c:when test="${info.checkType =='B'}">
									 Both
									</c:when>
									            
								</c:choose>
								</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">${info.qcRefName}</td>
								<td class="align-center">${info.lotNo}</td>
								<td class="align-center">${info.sampleTypeName}</td>
								<td class="align-center">${info.qty}</td>
								<td class="align-center">${info.doneByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
									<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">Verified</span>
									</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
								    <input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcRefId}">
									<input type="hidden" id="r_sample_type_id" value="${info.sampleTypeId}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_qty" value="${info.qty}">
									<input type="hidden" id="r_check_type" value="${info.checkType}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<div class="modal fade" id="analyticalBalInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Analytical Balance Logbook Info </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-MC-012</td>
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
                 <form method="post" id="analyticalBalInfoForm" modelAttribute="analyticalBalInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
						 	<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="done_by">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
     							<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                       	<option value="">-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>
								
                           	</div>
                            </div>
							<div class="col-md-2 align-right top">
                            	<label for="check_type">Check Type :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="check_type" name="checkType" class="js-example-theme-single form-control" style="width: 100%;">
			                       <option value="N">N/A</option>
			                        <option value="I">Intermediate</option>
			                        <option value="D">Daily</option>
			                        <option value="B">Both</option>
			                    </select>
                           	</div>
                            </div>
							
	                 	</div>
                
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="qc_ref_id">ARN NO. :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select onchange="materialInfoByArn(this.id)" id="qc_ref_id" name="qcRefId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="sample_type_list">Type Of Sample :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="sample_type_list" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
			                        <option value="">-Select Type-</option>
			                        <c:forEach var="info" items="${typeList}">
			                           	<option value="${info.id }">${info.typeName}</option>
			                        </c:forEach>
			                    </select>
			                    <input type="hidden" id="sample_type_id" name="sampleTypeId" value=""/>
                           	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="lot_no">Lot No :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="lot_no" name="lotNo" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="qty">Quantity :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="qty" name="qty" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">Remarks :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" placeholder="Description goes here......." ></textarea>
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

function materialInfoByArn(id){
	var arnNo = $("#"+id+" option:selected").text();
	$.get( "${pageContext.request.contextPath}/logbook_chemi/getMaterialInfoByArn/" + arnNo, 
	function( data ) {
		
		$("#sample_type_id").val(data.materialTypeId);
		$('#sample_type_list').val(data.materialTypeId).trigger('change.select2');
	});
}

function add(el) { 
	$("#id").val("");
	$("#qc_ref_id").val("").trigger('change.select2');
	$("#sample_type_list").val("").trigger('change.select2');
	$("#sample_type_id").val("");
	$("#equipment_id").val("").trigger('change.select2');
	$("#lot_no").val("");
	$("#qty").val("");
	$("#check_type").val("N").trigger('change.select2');
	$("#remarks").val("");
   
    $("#analyticalBalInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var qcRefId 	 = $(el).closest("tr").find("#r_qc_ref_id").val();
	var sampleTypeId = $(el).closest("tr").find("#r_sample_type_id").val();
	var lotNo		 = $(el).closest("tr").find("#r_lot_no").val();
	var qty 		 = $(el).closest("tr").find("#r_qty").val();
	var checkType 	 = $(el).closest("tr").find("#r_check_type").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
	var equipmentId    = $(el).closest("tr").find("#r_equipment_id").val();
	
		
	$("#id").val(Id);
	$("#qc_ref_id").val(qcRefId).trigger('change.select2');
	$("#sample_type_list").val(sampleTypeId).trigger('change.select2');
	$("#sample_type_id").val(sampleTypeId);
	$("#lot_no").val(lotNo);
	$("#qty").val(qty);
	$("#check_type").val(checkType).trigger('change.select2');
	$("#remarks").val(remarks);
	$("#equipment_id").val(equipmentId).trigger('change.select2');
		
	$("#analyticalBalInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#analyticalBalInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#analyticalBalInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/saveAnalyticalBalanceInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#analyticalBalInfoModal").modal('hide');
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
