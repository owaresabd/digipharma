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
	    <span style="text-shadow: 2px 2px 2px #aaa;">REFERENCE STANDARD ISSUE INFO</span>
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
								<th class="align-center" style="width: 80px;">USED QTY</th>
								<th class="align-center" style="width: 130px;">USED DATE</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${referenceIssueInfos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.refStandardName}</td>
								<td class="align-center">${info.usedQty}&nbsp;${info.unitName}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.usedDate}" var="usedDate" />
	                              <c:out value="${usedDate}"/>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_reference_list" value="${info.refStandardChildId}">
									<input type="hidden" id="r_used_date" value="${info.usedDate}">
									<input type="hidden" id="r_used_qty" value="${info.usedQty}">
									<input type="hidden" id="r_unit_id" value="${info.unitId}">
									<input type="hidden" id="r_purpose" value="${info.purpose}">
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
	
	<div class="modal fade" id="refStandardIssueInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Reference Standard Issue Info</td>
                 	<td>Document No.</td>
                 	<td colspan="2">LB-DIL-CM-019</td>
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
                 
                 <form method="post" id="refStandardIssueForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>NAME OF REFERENCE STANDARD :</b></span>
                            	<div class="form-group">
	                                <select  id="reference_list" onchange="referenceInfoById(this.id)" name="refStandardChildId" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        <option value="">-Select Reference-</option>
				                        <c:forEach var="info" items="${infos}">
				                           	<option value="${info.refStandardId }">${info.refStandardName}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-3">
                            	<span><b>SOURCE/ SUPPLIER :</b></span>
                            	<div class="form-group">
	                                <label id="source_name"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>BATCH NO :</b></span>
                            	<div class="form-group">
	                                <label id="batch_no"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>LOT NO :</b></span>
                            	<div class="form-group">
	                                <label id="lot_no"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>CAT NO/PART NO/ ID NO :</b></span>
                            	<div class="form-group">
	                                <label id="cat_no"></label>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-3">
                            	<span><b>RECEIVED QUANTITY :</b></span>
                            	<div class="form-group">
	                                <label id="rcv_qty"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>RECEIVING DATE :</b></span>
                            	<div class="form-group">
	                                <label id="rcv_date"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>VALID UP TO :</b></span>
                            	<div class="form-group">
	                                <label id="valid_to_date"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>POTENCY :</b></span>
                            	<div class="form-group">
	                                <label id="potency"></label>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-8">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
	                                <label id="storage_list"></label>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                 			<div class="col-md-2">
                            	<span><b>USED DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="used_date" name="usedDate" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-2">
                            	<span><b>USED QTY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="used_qty" name="usedQty" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-2">
                            	<span><b>UNIT :</b></span> 
                            	<div class="form-group">
	                                <select id="unit_list" class="js-example-theme-single form-control" style="width: 70%;" disabled="disabled" required="required">
				                        <option></option>
				                        <c:forEach var="unitInfo" items="${unitList}">
				                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
				                        </c:forEach>
				                 	</select>
                            	</div>
                            	<input type="hidden" id="unit_id" name="unitId" value=""/>
                            </div>
                            <div class="col-md-6">
                            	<span><b>PURPOSE OF USE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="purpose" name="purpose" value="" class="form-control" style="width: 100%;" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-12">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="remarks" name="remarks" value="" class="form-control" style="width: 100%;" autocomplete="off">
                            	</div>
                            </div>
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
	var link ="${pageContext.request.contextPath}/ref_standard/refStandardIssue";
	$("#refStandardIssueInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});


$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#used_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$('#used_date').datepicker('setDate', new Date()); 
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

function referenceInfoById(id){
	var referId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/ref_standard/getReferenceInfoById/" + referId, 
	function( data ) {
		$("#source_name").text(data.sourceSupplierName);
		$("#batch_no").text(data.batchNo);
		$("#lot_no").text(data.lotNo);
		$("#batch_no").text(data.batchNo);
		$("#lot_no").text(data.lotNo);
		$("#cat_no").text(data.catNo);
		$("#rcv_qty").text(data.rcvQty+' '+data.unitName);
		$('#rcv_date').text(convertMmDate(data.rcvDate));
		$("#valid_to_date").text(convertMmDate(data.validDate));
		$("#storage_list").text(data.storageCondName);
		$("#potency").text(data.potency);
		$("#unit_id").val(data.unitId);
		$("#unit_list").val(data.unitId).trigger('change.select2');
	});
}

function add(el) {
	$("#id").val("");
	$("#source_name").text('');
	$("#batch_no").text('');
	$("#lot_no").text('');
	$("#batch_no").text('');
	$("#lot_no").text('');
	$("#cat_no").text('');
	$("#rcv_qty").text('');
	$("#unit_id").val('');
	$("#unit_list").val('').trigger('change.select2');
	$('#rcv_date').text('');
	$("#valid_to_date").text('');
	$("#storage_list").text('');
	$("#potency").text('');
	$("#reference_list").val("").trigger('change.select2');
	$("#unit_id").val("").trigger('change.select2');
	$("#used_date").datepicker('setDate', new Date());
	$("#used_qty").val("");
	$("#purpose").val("");
	$("#remarks").val("");
	
    $("#refStandardIssueInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var refStandardId 	= $(el).closest("tr").find("#r_reference_list").val();
	var usedDate		= $(el).closest('tr').find("#r_used_date").val();
	var usedQty 		= $(el).closest('tr').find("#r_used_qty").val();
	var unitId 			= $(el).closest("tr").find("#r_unit_id").val();
	var purpose			= $(el).closest("tr").find("#r_purpose").val();
	var remarks			= $(el).closest("tr").find("#r_remarks").val();
	
	$("#id").val(Id);
	$("#reference_list").val(refStandardId).trigger('change.select2');
	$("#used_date").datepicker('setDate', convertMmDate(usedDate));
	$("#used_qty").val(usedQty);
	$("#unit_id").val(unitId);
	$("#unit_list").val(unitId).trigger('change.select2');
	$("#purpose").val(purpose);
	$("#remarks").val(remarks);
	
	$("#refStandardIssueInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#refStandardIssueForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#refStandardIssueForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/ref_standard/save-refStandard-issue-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#refStandardIssueInfoModal").modal('hide');
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