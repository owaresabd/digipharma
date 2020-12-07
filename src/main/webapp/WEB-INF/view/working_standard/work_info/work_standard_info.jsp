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
	    <span style="text-shadow: 2px 2px 2px #aaa;">WORKING STANDARD INFORMATION</span>
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
								<th class="align-left">NAME OF THE WORKING STANDARD</th>
								<th class="align-center" style="width: 140px;">DATE OF PREPARATION</th>
								<th class="align-center" style="width: 130px;">VALID UP TO</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.workStandardName}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.dateOfPrep}" var="dateOfPrep" />
								${dateOfPrep}
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.validToDate}" var="validToDate" />
								${validToDate}
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="row_standard_name" value="${info.workStandardName}">
									<input type="hidden" id="row_ref_stand_id" value="${info.refStandId}">
									<input type="hidden" id="row_ws_request_id" value="${info.wsRequestId}">
									<input type="hidden" id="row_arn_no" value="${info.arnNo}">
									<input type="hidden" id="row_no_vial" value="${info.noOfVial}">
									<input type="hidden" id="row_vial_amount" value="${info.vialAmount}">
									<input type="hidden" id="row_prep_date" value="${info.dateOfPrep}">
									<input type="hidden" id="row_valid_to" value="${info.validToDate}">
									<input type="hidden" id="row_assay_desc" value="${info.assayDesc}">
									<input type="hidden" id="row_condition" value="${info.storageCondition}">
									
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
	
	<div class="modal fade" id="standardInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Working Standard Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-CM-020</td>
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
                 
                 <form method="post" id="standardInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">NAME OF THE REF STANDARD:</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
                            	<select  id="ref_stand_id" name="refStandId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                       	<option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${refStandinfos}">
			                           	<option value="${info.id }">${info.refStandardName}</option>
			                        </c:forEach>
	                			</select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">NAME OF THE WORKING STANDARD:</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
                            	<select  id="wsRequestId" name="wsRequestId" onchange="wsDetailInfo(this.id);" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                       	<option value="">Select Working Standard</option>
			                        <c:forEach var="info" items="${wsInfos}">
			                           	<option value="${info.wsRequestId }">${info.materialName}</option>
			                        </c:forEach>
	                			</select>
	                                <input type="hidden" id="workStandardName" name="workStandardName" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">AR NO:</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <input type="text" id="arnNo" readonly="readonly" name="arnNo" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">No. of Vial Prepared:</label>
                            </div>
                            <div class="col-md-2">
                            	<div class="form-group">
	                                <input type="text" id="noOfVial" name="noOfVial" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">Amount in Per Vial:</label>
                            </div>
                            <div class="col-md-2">
                            	<div class="form-group">
	                                <input type="text" id="vialAmount" name="vialAmount" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">Date of Preparation:</label>
                            </div>
                            <div class="col-md-3">
                            	<div class="form-group">
	                                <input type="text" id="dateOfPrep" name="dateOfPrep" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">Valid Up to:</label>
                            </div>
                            <div class="col-md-3">
                            	<div class="form-group">
	                                <input type="text" id="validToDate" name="validToDate" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">Assay (As Such Basis):</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <input type="text" id="assayDesc" name="assayDesc" value="" class="form-control"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">Storage Condition:</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
                            	<select  id="storageCondition" name="storageCondition" class="js-example-theme-single form-control" style="width: 100%;" required="required">
		                       	<option></option>
			                	<c:forEach var="info" items="${conditionList}">
			                    	<option value="${info.id }">${info.storageDesc}</option>
			                    </c:forEach>
	                			</select>
	                                
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
	var link ="${pageContext.request.contextPath}/working_standard/maintain";
	$("#standardInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});


$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	 $( "#dateOfPrep" ).datepicker({
			format: "d-M-yyyy",
	        todayHighlight: true,
	        autoclose: true
	    });
	
    $( "#validToDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
});



$('.number').keypress(function (event) {
    return isNumber(event, this)
});






function add(el) {
	$("#id").val("");
	$("#ref_stand_id").val("").trigger('change.select2');
	$("#wsRequestId").val("").trigger('change.select2');
	$("#workStandardName").val('');
	$("#arnNo").val('');
	$("#noOfVial").val('');
	$("#vialAmount").val('');
	$("#dateOfPrep").val('');
	$("#validToDate").val('');
	$("#assayDesc").val('');
	$("#storageCondition").val("").trigger('change.select2');
	
	$("#standardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var refStandId	= $(el).closest("tr").find("#row_ref_stand_id").val();
	var wsRequestId	= $(el).closest("tr").find("#row_ws_request_id").val();
	var standardName = $(el).closest("tr").find("#row_standard_name").val();
	var arnNo		= $(el).closest('tr').find("#row_arn_no").val();
	var vialNo		= $(el).closest('tr').find("#row_no_vial").val();
	var vialAmount	= $(el).closest('tr').find("#row_vial_amount").val();
	var prepDate 	= $(el).closest("tr").find("#row_prep_date").val();
	var validTo 	= $(el).closest("tr").find("#row_valid_to").val();
	var assayDesc 	= $(el).closest("tr").find("#row_assay_desc").val();
	var condition 	= $(el).closest("tr").find("#row_condition").val();
	
	
	$("#id").val(Id);
	$("#ref_stand_id").val(refStandId).trigger('change.select2');
	var opt = document.createElement('option');
	    opt.value = wsRequestId;
	    opt.innerHTML = standardName;
	    $('#wsRequestId').append(opt); 
	$("#wsRequestId").val(wsRequestId).trigger('change.select2');
	$("#workStandardName").val(standardName);
	$("#arnNo").val(arnNo);
	$("#noOfVial").val(vialNo);
	$("#vialAmount").val(vialAmount);
	$("#dateOfPrep").val(convertMmDate(prepDate));
	$("#validToDate").val(convertMmDate(validTo));
	$("#assayDesc").val(assayDesc);
	$("#storageCondition").val(condition).trigger('change.select2');
	
		
	$("#standardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};
function wsDetailInfo(id){
	var wsId=$("#"+id+"").val();
	//alert(wsId);
	$.get( "${pageContext.request.contextPath}/working_standard/getWsDetailInfo/" + wsId, 
	function( data ) {
		$("#workStandardName").val(data.materialName);
		$('#arnNo').val(data.arnNo);
		
	});
}
$("#standardInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#standardInfoForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/working_standard/saveStandardInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#standardInfoModal").modal('hide');
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