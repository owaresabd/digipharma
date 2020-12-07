<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<link href="${pageContext.request.contextPath}/vendor/bootstrap-datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>

<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">VERIFICATION OF DISSOLUTION MACHINE INFO</span>
		
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
								<th class="align-center" style="width: 50px;">SL NO</th>
								<th class="align-left" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 100px;">AR NO.</th>
								<th class="align-left" style="width: 100px;">DONE BY</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.equipmentName}</td>
								<td class="align-center">${info.arnNo}</td>
								<td class="align-left">${info.operateByName}</td>
								<td class="align-center">
									<c:choose>
							    	<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">VERIFIED</span>
							    	</c:when>    
								    <c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
								    </c:otherwise>
									</c:choose>							
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcReferenceId}">
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

<!------------------------ Start: Create Product Color Types Modal -------------------->
<div class="modal fade" id="verificationDissolutionInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-xxl" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book: Verification of Dissolution Machine </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-018</td>
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
                 <form method="post" id="verificationDessoLogInfoForm" modelAttribute="desiccatorLogInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-1 align-left top">
                            	<label for="qc_ref_id">AR No. :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                               <select  id="qc_ref_id" name="qcReferenceId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
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
	                 	
                 		</div>
                 
							<div class="row">
                        	<div class="col-md-12">
                           	<div class="form-group clockPickerDiv">
                            	<table id="reqTable" class="table table-bordered table-striped table-hover"> 
									<thead>
										<tr>
											<th class="align-center" style="width: 130px;">Vessel No.</th>
											<th class="align-center" style="width: 120px;">Temperature Verification Before Test(&deg;C)</th>
											<th class="align-center" style="width: 100px;">Temperature Verification After Test(&deg;C)</th>
											<th class="align-center" style="width: 100px;">Sample Input Time</th>
											<th class="align-center" style="width: 70px;">Sample Withdrawal Time</th>
											<th class="align-center" style="width: 60px;">Remarks</th>
											<th class="align-center" style="width: 80px;">####</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="align-center">
											<input type="hidden" id="child_id" value=""/>
											<input type="hidden" id="master_id" value=""/>
											<input style="width: 100%;" type="text" id="vesselNo"  />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="tempBeforeTest" class="number"  />
											</td>
											<td class="align-center">
											
												<input style="width: 100%;" type="text" id="tempAfterTest" class="number"  />
											</td>
											<td class="align-center" >
									<input type="text" id="sampleInputTime" name="sampleInputTime" value="" class="form-control clockpicker" style="width: 100%;" autocomplete="off" >
                      
											</td>
										<td class="align-center">
			<input type="text" id="sampleWithdrawalTime" name="sampleWithdrawalTime" value="" class="form-control clockpicker" style="width: 100%;" autocomplete="off" >																				</td>
											<td class="align-center">
											<input style="width: 100%;" type="text" id="remarks"  value=""/>
											</td>
												<td class="align-center">
												<button type="button" id="addBtn" class="btn bg-blue btn-xs waves-effect" onclick="addRow(this)"> 
													<i class="material-icons">gamepad</i>
												</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
                           	</div>
                   	</div>
                	
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
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
.dropdown-menu {
    position: fixed !important;
    top: 85% !important;
}
.btn:not(.btn-link):not(.btn-circle) span {
    top: -2px;
    margin-left: 3px;
  	color: blue !important;
}
.bootstrap-datetimepicker-widget table td span {
    width: 24px !important;
    height: 24px !important;
    line-height: 24px !important;
    }
    .bootstrap-datetimepicker-widget table td {
    height: 24px !important;
    line-height: 24px !important;
    width: 24px !important;
}
    
    
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
 <script src="${pageContext.request.contextPath}/vendor/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>	
	
<script>
$(".js-example-theme-single").select2({
    theme: "classic"
});
function pageReload(link){
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
	var link =  "${pageContext.request.contextPath}/logbook_chemi/verificationDissolutionInfo";
	$("#verificationDissolutionInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
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


$(function(){
	$('#sampleInputTime').datetimepicker({
	    format: 'hh:mm:ss A'
	});
	$('#sampleWithdrawalTime').datetimepicker({
	    format: 'hh:mm:ss A'
	});

	});


$('.number').keypress(function (event) {
    return isNumber(event, this)
});

var aTable = $('#reqTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
	 "oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});
$('#reqTable_length, #reqTable_info, #reqTable_paginate, #reqTable_filter').hide();


function add(el) { 
	$("#id").val("");
	$("#qc_ref_id").val("").trigger('change.select2');
	$("#equipment_id").val("").trigger('change.select2');
	$("#remarks").val("");
    $("#batch_no").val("");
	$("#verificationDissolutionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id = $(el).closest("tr").find("#row_id").val();
	var qcRefId = $(el).closest("tr").find("#r_qc_ref_id").val();
	var equipmentId  = $(el).closest("tr").find("#r_equipment_id").val();
	$("#id").val(Id);
	$("#qc_ref_id").val(qcRefId).trigger('change.select2');
	$("#equipment_id").val(equipmentId).trigger('change.select2');
	$("#remarks").val("");
	
	$("#verificationDissolutionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
	 verification_discussionDetailsById(Id);//For Get Child List 
};

function verification_discussionDetailsById(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/logbook_chemi/getVerificationDissolutionDetails?id=" + id,
		dataType : 'json',
		success : function(data) {
			$("#reqTable").find("tr:gt(1)").remove();
			var requestList = data.length;
			if (requestList > 0) {
				 showRequestList(data);
			} 
		}
	});
}

$("#verificationDessoLogInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#verificationDessoLogInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveVerificationDissolution",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#verificationDissolutionInfoModal").modal('hide');
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
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/desiccator-log-print";
	
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

function addRow(el){
	var id		 = $("#child_id").val();
	var masterId	 = $("#master_id").val();
	var tempBeforeTest  = $("#tempBeforeTest").val();
	var tempAfterTest = $("#tempAfterTest").val();
	var sampleInputTime  = $("#sampleInputTime").val();
	var vesselNo = $("#vesselNo").val();
	var sampleWithdrawalTime= $("#sampleWithdrawalTime").val();
	var remarks= $("#remarks").val();

	var html = '' 
		    + '<input type="hidden" id="i_chd_id"  name="chdId[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_master_id"  name="masterId[]" value="'+masterId+'"/>'
			+ '<input type="hidden" id="i_vessel_no"  name="vesselNo[]" value="'+vesselNo+'"/>'
			+ '<input type="hidden" id="i_temp_before_test" name="tempBeforeTest[]"  value="'+tempBeforeTest+'"/>'
			+ '<input type="hidden" id="i_temp_after_test" name="tempAfterTest[]" value="'+tempAfterTest+'"/>'
			+ '<input type="hidden" id="i_sample_input_time"  name="sampleInputTime[]" value="'+sampleInputTime+'"/>'
			+ '<input type="hidden" id="i_sample_withdrawal_time"  name="sampleWithdrawalTime[]" value="'+sampleWithdrawalTime+'"/>'
			+ '<input type="hidden" id="i_remarks" name="remarks[]" value="'+remarks+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRow(this)"><i class="material-icons">mode_edit</i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		
		if( tempBeforeTest != '' && sampleInputTime != '' && tempAfterTest != '' && vesselNo != '' && sampleWithdrawalTime != '' ){
		var appendRow=true;
		
		if(appendRow=true){
		var rowNode = aTable.row.add([vesselNo,tempBeforeTest, tempAfterTest,  sampleInputTime, sampleWithdrawalTime, remarks ,   html]).draw( false ).node();
		aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		$(rowNode).find("td:eq(3)").css({"text-align": "center"});
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
		$(rowNode).find("td:eq(6)").css({"text-align": "center"});
		$("#child_id").val('');
		$("#master_id").val('');
		$("#tempBeforeTest").val('');
		$("#sampleInputTime").val('');
		$("#tempAfterTest").val('');
		$("#vesselNo").val('');
		$("#sampleWithdrawalTime").val('');
		$("#remarks").val('');
		}
		
		}else{
			sweetAlert("Failed!", "Please enter all row value!", "warning", 5000, false);
		}
}	



function editRow(el) {
	var id 			= $(el).closest("tr").find("#i_chd_id").val();
	var masterId 			= $(el).closest("tr").find("#i_master_id").val();
	var vesselNo 	= $(el).closest("tr").find("#i_vessel_no").val();
	var tempAfterTest 	= $(el).closest("tr").find("#i_temp_after_test").val();
	var tempBeforeTest 	= $(el).closest("tr").find("#i_temp_before_test").val();
	var sampleInputTime 	= $(el).closest("tr").find("#i_sample_input_time").val();
	var sampleWithdrawalTime 	= $(el).closest("tr").find("#i_sample_withdrawal_time").val();
	var remarks 		= $(el).closest("tr").find("#i_remarks").val();
	
	$("#child_id").val(id);
	$("#master_id").val(masterId);
	$("#vesselNo").val(vesselNo);
	$("#tempAfterTest").val(tempAfterTest);
	$("#tempBeforeTest").val(tempBeforeTest);
	$("#sampleInputTime").val(sampleInputTime);
	$("#sampleWithdrawalTime").val(sampleWithdrawalTime);
	$("#remarks").val(remarks);
	
	var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
};


function remove(el){

//New
var id 	= $(el).closest("tr").find("#i_chd_id").val();
	alert(id);
    var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/deleteChildVerificationDiscussionById?id="+id;
	//alert();
		$.ajax({
			async : false,
			url : ajaxURL ,
			success : function(result) {
				var dataList = data.length;
				$("#serviceDiv").html(result);
			}
		});
	//New


	var tbl = $(el).closest('table').DataTable();
    var tr 	= $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
}


function showRequestList(data) {
	for (var i = 0; i < data.length; i++) {
	
		var id = data[i].chdId || "";
		var masterId = data[i].masterId || "";
		var vesselNo = data[i].vesselNo || "";
		var tempBeforeTest = data[i].tempBeforeTest || "";
		var tempAfterTest = data[i].tempAfterTest || "";
		var sampleInputTime = data[i].sampleInputTime || "";
		var sampleWithdrawalTime =data[i].sampleWithdrawalTime || "";
		var remarks =data[i].remarks || "";
		
		
	
		var html = '' 
			+ '<input type="hidden" id="i_chd_id" name="chdId[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_master_id"  name="masterId[]" value="'+masterId+'"/>'
			+ '<input type="hidden" id="i_sample_input_time"  name="sampleInputTime[]" value="'+sampleInputTime+'"/>'
			+ '<input type="hidden" id="i_temp_before_test" name="tempBeforeTest[]" value="'+tempBeforeTest+'"/>'
			+ '<input type="hidden" id="i_temp_after_test"  name="tempAfterTest[]" value="'+tempAfterTest+'"/>'
			+ '<input type="hidden" id="i_sample_withdrawal_time" name="sampleWithdrawalTime[]" value="'+sampleWithdrawalTime+'"/>'
			+ '<input type="hidden" id="i_vessel_no" name="vesselNo[]" value="'+vesselNo+'"/>'
			+ '<input type="hidden" id="i_remarks" name="remarks[]" value="'+remarks+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRow(this)"><i class="material-icons">mode_edit</i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		
		var rowNode = aTable.row.add([vesselNo, tempBeforeTest,tempAfterTest, sampleInputTime, sampleWithdrawalTime, remarks, html]).draw( false ).node();
		aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		$(rowNode).find("td:eq(3)").css({"text-align": "center"});
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
		$(rowNode).find("td:eq(6)").css({"text-align": "center"});
	}
}

</script>
