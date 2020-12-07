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
	    <span style="text-shadow: 2px 2px 2px #aaa;">WORKING STANDARD ISSUE INFO</span>
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
								<th class="align-center" style="width: 80px;">VIAL NO</th>
								<th class="align-center" style="width: 130px;">VIAL OPENING DATE</th>
								<th class="align-center" style="width: 130px;">USED DATE</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${workIssueInfos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.workStandardName}</td>
								<td class="align-center">${info.vialNo}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.openingDate}" var="openingDate" />
	                              <c:out value="${openingDate}"/>
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.usedDate}" var="usedDate" />
	                              <c:out value="${usedDate}"/>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_reference_list" value="${info.workStandardChildId}">
									<input type="hidden" id="r_vial_no" value="${info.vialNo}">
									<input type="hidden" id="r_opening_date" value="${info.openingDate}">
									<input type="hidden" id="r_used_date" value="${info.usedDate}">
									<input type="hidden" id="r_validity_date" value="${info.validityDate}">
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
	
	<div class="modal fade" id="workStandardIssueInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Working Standard Issue Info</td>
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
                 
                 <form method="post" id="workStandardIssueForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>NAME OF WORKING STANDARD :</b></span>
                            	<div class="form-group">
	                                <select  id="reference_list" onchange="referenceInfoById(this.id)" name="workStandardChildId" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        <option value="">-Select Reference-</option>
				                        <c:forEach var="info" items="${infos}">
				                           	<option value="${info.id }">${info.workStandardName}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-3">
                            	<span><b>AR NO :</b></span>
                            	<div class="form-group">
	                                <label id="arn_no"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>NO OF VIAL :</b></span>
                            	<div class="form-group">
	                                <label id="no_of_vial"></label>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>ASSAY (AS SUCH BASIS) :</b></span>
                            	<div class="form-group">
	                                <label id="assay_desc"></label>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-3">
                            	<span><b>DATE OF PREP :</b></span>
                            	<div class="form-group">
	                                <label id="date_of_prep"></label>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>VALID UP TO :</b></span>
                            	<div class="form-group">
	                                <label id="valid_to_date"></label>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
	                                <label id="storage_list"></label>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>VIAL NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="vial_no" name="vialNo" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>VIAL OPENING DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="opening_date" name="openingDate" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>VIAL USED DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="used_date" name="usedDate" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>VIAL VALIDITY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="validity_date" name="validityDate" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
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
	var link ="${pageContext.request.contextPath}/working_standard/work_standard_details";
	$("#workStandardIssueInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});


$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$( "#opening_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#used_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#validity_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$('#opening_date').datepicker('setDate', new Date()); 
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

function referenceInfoById(id){
	var referId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/working_standard/getWorkStandardById/" + referId, 
	function( data ) {
		$("#arn_no").text(data.arnNo);
		$("#no_of_vial").text(data.noOfVial);
		$('#date_of_prep').text(convertMmDate(data.dateOfPrep));
		$("#valid_to_date").text(convertMmDate(data.validToDate));
		$("#storage_list").text(data.storageCondName);
		$("#assay_desc").text(data.assayDesc);
	});
}

function add(el) {
	$("#id").val("");
	$("#arn_no").text('');
	$("#no_of_vial").text('');
	$('#date_of_prep').text('');
	$("#valid_to_date").text('');
	$("#storage_list").text('');
	$("#assay_desc").text('');
	$("#reference_list").val("").trigger('change.select2');
	$("#opening_date").datepicker('setDate', new Date());
	$("#used_date").datepicker('setDate', '');
	$("#validity_date").datepicker('setDate', '');
	$("#vial_no").val("");
	$("#remarks").val("");
	
    $("#workStandardIssueInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var workStChildId	= $(el).closest("tr").find("#r_reference_list").val();
	var vialNo	 		= $(el).closest('tr').find("#r_vial_no").val();
	var openingDate		= $(el).closest('tr').find("#r_opening_date").val();
	var usedDate		= $(el).closest('tr').find("#r_used_date").val();
	var validityDate	= $(el).closest('tr').find("#r_validity_date").val();
	var remarks			= $(el).closest("tr").find("#r_remarks").val();
	
	$("#id").val(Id);
	$("#reference_list").val(workStChildId).trigger('change.select2');
	$("#vial_no").val(vialNo);
	$("#opening_date").datepicker('setDate', convertMmDate(openingDate));
	$("#used_date").datepicker('setDate', convertMmDate(usedDate));
	$("#validity_date").datepicker('setDate', convertMmDate(validityDate));
	$("#remarks").val(remarks);
	
	$("#workStandardIssueInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#workStandardIssueForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#workStandardIssueForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/working_standard/save-workStandard-issue-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#workStandardIssueInfoModal").modal('hide');
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