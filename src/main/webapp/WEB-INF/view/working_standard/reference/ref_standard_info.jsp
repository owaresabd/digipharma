<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<style>
/* .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
} */
.table > tbody > tr > td, .table > tfoot > tr > td {
    font-size: none !important;
}
.bg-cyan {
  background-color: #30acbb !important;
  color: #fff; 
  }
  
</style>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">REFERENCE STANDARD INFORMATION</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/batch/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/batch/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="batchList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;">SL#</th>
								<th class="align-left">NAME OF THE REFERENCE STANDARD</th>
								<th class="align-center" style="width: 100px;">RECEIVED QTY</th>
								<th class="align-center" style="width: 130px;">RECEIVING DATE</th>
								<th class="align-center" style="width: 130px;">VALID DATE</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<c:choose>
							  <c:when test="${info.diffDays <= 180}">
							<tr class="bg-red">
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.refStandardName}</td>
								<td class="align-center">${info.rcvQty}&nbsp;${info.unitName}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.rcvDate}" var="rcvDate" />
	                              <c:out value="${rcvDate}"/>
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.validDate}" var="validDate" />
	                              <c:out value="${validDate}"/>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_ref_standard_id" value="${info.refStandardId}">
									<input type="hidden" id="r_ref_standard_name" value="${info.refStandardName}">
									<input type="hidden" id="r_rcv_date" value="${info.rcvDate}">
									<input type="hidden" id="r_source_list" value="${info.sourceSupplierId}">
									<input type="hidden" id="r_id_type" value="${info.idType}">
									<input type="hidden" id="r_cat_no" value="${info.catNo}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_potency" value="${info.potency}">
									<input type="hidden" id="r_rcv_qty" value="${info.rcvQty}">
									<input type="hidden" id="r_unit_id" value="${info.unitId}">
									<input type="hidden" id="r_valid_to_date" value="${info.validDate}">
									<input type="hidden" id="r_storage_list" value="${info.storageCondition}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
								</td>
							</tr>
							</c:when>
  
  <c:otherwise>
  <tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.refStandardName}</td>
								<td class="align-center">${info.rcvQty}&nbsp;${info.unitName}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.rcvDate}" var="rcvDate" />
	                              <c:out value="${rcvDate}"/>
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.validDate}" var="validDate" />
	                              <c:out value="${validDate}"/>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_ref_standard_id" value="${info.refStandardId}">
									<input type="hidden" id="r_ref_standard_name" value="${info.refStandardName}">
									<input type="hidden" id="r_rcv_date" value="${info.rcvDate}">
									<input type="hidden" id="r_source_list" value="${info.sourceSupplierId}">
									<input type="hidden" id="r_id_type" value="${info.idType}">
									<input type="hidden" id="r_cat_no" value="${info.catNo}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_potency" value="${info.potency}">
									<input type="hidden" id="r_rcv_qty" value="${info.rcvQty}">
									<input type="hidden" id="r_unit_id" value="${info.unitId}">
									<input type="hidden" id="r_valid_to_date" value="${info.validDate}">
									<input type="hidden" id="r_storage_list" value="${info.storageCondition}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
								</td>
							</tr>
  </c:otherwise>
</c:choose>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="refStandardInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             	<div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Reference Standard Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-CM-019</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 Jan 2020</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                </div>
                 
                 <form method="post" id="refStandardInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>NAME OF REFERENCE STANDARD :</b></span>
                            	<!-- <div class="form-group">
	                                <input type="text" id="ref_standard_name" name="refStandardName" value="" class="form-control" style="width: 100%;" autocomplete="off">
                            	</div> -->
                            	<div class="form-group">
	                                <select  id="ref_standard_id" name="refStandardId" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        <option value="">-Select Reference-</option>
				                        <c:forEach var="info" items="${refStandSetupInfos}">
				                           	<option value="${info.id }">${info.refStandardName}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                            
                            <div class="col-md-6">
                            	<span><b>SOURCE/ SUPPLIER :</b></span>
                            	<div class="form-group">
	                                <select  id="source_list" name="sourceSupplierId" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        <option value="">-Select Source-</option>
				                        <c:forEach var="info" items="${manufactureList}">
				                           	<option value="${info.id }">${info.sourceName}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-3">
                            	<span><b>BATCH NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control" style="width: 60%;" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>LOT NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="lot_no" name="lotNo" value="" class="form-control" style="width: 60%;" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>IDENTITY TYPE :</b></span>
                            	<div class="form-group">
	                                <select id="idType" name="idType" class="js-example-theme-single form-control" style="width:100%;" required="required">
				                        <option></option>
				                        <option value="C">CATEGORY</option>
				                         <option value="P">PART</option>
				                         <option value="I">ID</option>
				                       
				                 	</select>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>IDENTITY NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="cat_no" name="catNo" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-3">
                            	<span><b>RECV. QTY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="rcv_qty" name="rcvQty" value="" class="form-control" style="width: 60%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>UNIT :</b></span> 
                            	<div class="form-group">
	                                <select id="unit_id" name="unitId" class="js-example-theme-single form-control" style="width:60%;" required="required">
				                        <option></option>
				                        <c:forEach var="unitInfo" items="${unitList}">
				                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
				                        </c:forEach>
				                 	</select>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>POTENCY :</b></span> 
                            	<div class="form-group">
	                                <input type="text" id="potency" name="potency" value="" class="form-control" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-3">
                            	<span><b>RECV. DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="rcv_date" name="rcvDate" value="" class="form-control" style="width: 60%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>VALID UP TO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="valid_to_date" name="validDate" value="" class="form-control" style="width: 60%;" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
	                                <select  id="storage_list" name="storageCondition" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option value="">-Select Storage-</option>
				                        <c:forEach var="info" items="${conditionList}">
				                           	<option value="${info.id }">${info.storageDesc}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                            
                            
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span><spring:message code="btn.save"/></span>
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

input {
    height: 28px !important;
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

$(".load").on('click', function(e){
	var link ="${pageContext.request.contextPath}/ref_standard/referenceStandard";
	$("#refStandardInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});


$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#rcv_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    $( "#valid_to_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$("#reagent_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#reagent_id").val(value);
});

function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

		$("#receive_unit_name").val(data.unitName);	
		$("#receive_unit_id").val(data.unitId);
		//$("#unit_list").val(data.unitId).trigger('change.select2');
	});
}

function add(el) {
	$("#id").val("");
	$("#ref_standard_id").val('').trigger('change.select2');
	//$("#ref_standard_name").val('');
	$("#source_list").val("").trigger('change.select2');
	$("#batch_no").val("");
	$("#lot_no").val("");
	$("#idType").val("").trigger('change.select2');
	$("#cat_no").val("");
	$("#rcv_qty").val("");
	$("#unit_id").val("").trigger('change.select2');
	$('#rcv_date').datepicker('setDate', new Date());
	$("#valid_to_date").datepicker('setDate', '');
	$("#storage_list").val("").trigger('change.select2');
	$("#potency").val("");
		
    $("#refStandardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var refStandardId 	= $(el).closest("tr").find("#r_ref_standard_id").val();
	var rcvDate			= $(el).closest('tr').find("#r_rcv_date").val();
	var sourceId 		= $(el).closest('tr').find("#r_source_list").val();
	var idType			=$(el).closest('tr').find("#r_id_type").val();
	var catNo 			= $(el).closest("tr").find("#r_cat_no").val();
	var lotNo 			= $(el).closest("tr").find("#r_lot_no").val();
	var batchNo 		= $(el).closest("tr").find("#r_batch_no").val();
	var potency 		= $(el).closest("tr").find("#r_potency").val();
	var rcvQty 			= $(el).closest('tr').find("#r_rcv_qty").val();
	var unitId 			= $(el).closest("tr").find("#r_unit_id").val();
	var validDate 		= $(el).closest('tr').find("#r_valid_to_date").val();
	var storageId 		= $(el).closest('tr').find("#r_storage_list").val();
	
	$("#id").val(Id);
	$("#ref_standard_id").val(refStandardId).trigger('change.select2');
	$("#source_list").val(sourceId).trigger('change.select2');
	$("#batch_no").val(batchNo);
	$("#lot_no").val(lotNo);
	$("#idType").val(idType).trigger('change.select2');
	$("#cat_no").val(catNo);
	$("#rcv_qty").val(rcvQty);
	$("#unit_id").val(unitId).trigger('change.select2');
	$('#rcv_date').datepicker('setDate', convertMmDate(rcvDate));
	$("#valid_to_date").datepicker('setDate', convertMmDate(validDate));
	$("#storage_list").val(storageId).trigger('change.select2');
	$("#potency").val(potency);
	
	$("#refStandardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#refStandardInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#refStandardInfoForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/ref_standard/save-refStandard-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#refStandardInfoModal").modal('hide');
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