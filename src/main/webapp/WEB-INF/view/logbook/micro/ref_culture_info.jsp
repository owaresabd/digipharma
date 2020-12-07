<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">REFERENCE CULTURE RECEIVING RECORD INFO</span>
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
					<c:if test="${not empty infos}">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right m-r-10" id="btnPrint" onClick="printReport(this)" data-toggle="tooltip" title="Print">
						<i class="material-icons">print</i>
					</button>
					</c:if>
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 110px;">NAME OF MFG./ SUPPLIER</th>
								<th class="align-center" style="width: 100px;">ATCC NO</th>
								<th class="align-center" style="width: 110px;">RECEIVED DATE</th>
								<th class="align-center" style="width: 100px;">RECEIVED BY</th>
								<th class="align-center" style="width: 100px;">CHECKED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.mfgSupplierName}</td>
								<td class="align-center">${info.atccNo}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.receiveDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-left">${info.receiveByName}</td>
								<td class="align-left">${info.checkByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_mfg_supplier_id" value="${info.mfgSupplierId}">
									<input type="hidden" id="r_ref_seed_name" value="${info.refSeedName}">
									<input type="hidden" id="r_atcc_no" value="${info.atccNo}">
									<input type="hidden" id="r_batch_lot_no" value="${info.batchLotNo}">
									<input type="hidden" id="r_certi_avail" value="${info.certiAvail}">
									<input type="hidden" id="r_receive_date" value="${info.receiveDate}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									<input type="hidden" id="r_verify_date" value="${info.verifyDate}">
									<input type="hidden" id="r_receive_by" value="${info.receiveBy}">
									<input type="hidden" id="r_check_by" value="${info.checkBy}">
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

<!------------------------ Start: Create Product Color Types Modal -------------------->
<div class="modal fade" id="refCultureInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Reference Culture Receiving Record Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-MC-005</td>
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
                 <form method="post" id="refCultureInfoForm" modelAttribute="refCultureInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="mfg_supplier_id">Mfg./Supplier Name :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                            	<select  id="mfg_supplier_id" name="mfgSupplierId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Manufacture-</option>
			                        <c:forEach var="info" items="${manufactureList}">
			                           	<option value="${info.id }">${info.sourceName}</option>
			                        </c:forEach>
			                    </select>
                            </div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="ref_seed_name">Reference Master/Seed Culture Name :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="ref_seed_name" name="refSeedName" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-2 align-right top">
                            	<label for="atcc_no">ATCC No :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="atcc_no" name="atccNo" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                 			
	                 		<div class="col-md-2 align-right top">
                            	<label for="batch_lot_no">Batch/Lot No :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="batch_lot_no" name="batchLotNo" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="receive_date">Received Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="receive_date" name="receiveDate" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="exp_date">Expiry Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="exp_date" name="expDate" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-2 align-right top">
                            	<label for="certi_avail">Certificate Available :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="certi_avail" name="certiAvail" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                 			
	                 		<div class="col-md-2 align-right top">
                            	<label for="verify_date">Verification Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="verify_date" name="verifyDate" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="receive_by">Received By :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                            	<select  id="receive_by" name="receiveBy" class="js-example-theme-single form-control" style="width: 100%;">			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                            </div>
                            </div>
	                 		
	                 		<div class="col-md-2 align-right top">
                            	<label for="check_by">Checked By :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                            	<select  id="check_by" name="checkBy" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
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
	
	$( "#receive_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#exp_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#verify_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$('#start_time').mdtimepicker({
      defaultTime: '09:00 AM',
      dynamic: false,
      dropdown: true,
      scrollbar: true,
      minuteStep: 1
    });
	
	$('#end_time').mdtimepicker({
      defaultTime: '09:00 AM',
      dynamic: false,
      dropdown: true,
      scrollbar: true,
      minuteStep: 1
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
	$("#mfg_supplier_id").val("").trigger('change.select2');
	$("#ref_seed_name").val("");
	$("#atcc_no").val("");
	$("#batch_lot_no").val("");
	$('#receive_date').datepicker('setDate', new Date());
	$('#exp_date').datepicker('setDate', '');
	$('#verify_date').datepicker('setDate', '');
	$("#certi_avail").val("");
	$("#receive_by").val("").trigger('change.select2');
	$("#check_by").val("").trigger('change.select2');
	$("#remarks").val("");
	
    $("#refCultureInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var mfgSuppId 	 = $(el).closest("tr").find("#r_mfg_supplier_id").val();
	var refSeedName	 = $(el).closest("tr").find("#r_ref_seed_name").val();
	var atccNo		 = $(el).closest("tr").find("#r_atcc_no").val();
	var batchLotNo	 = $(el).closest("tr").find("#r_batch_lot_no").val();
	var certiAvail	 = $(el).closest("tr").find("#r_certi_avail").val();
	var receiveDate	 = $(el).closest("tr").find("#r_receive_date").val();
	var expDate		 = $(el).closest("tr").find("#r_exp_date").val();
	var verifyDate 	 = $(el).closest("tr").find("#r_verify_date").val();
	var receiveBy	 = $(el).closest("tr").find("#r_receive_by").val();
	var checkBy 	 = $(el).closest("tr").find("#r_check_by").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#mfg_supplier_id").val(mfgSuppId).trigger('change.select2');
	$("#ref_seed_name").val(refSeedName);
	$("#atcc_no").val(atccNo);
	$("#batch_lot_no").val(batchLotNo);
	$("#certi_avail").val(certiAvail);
	if (receiveDate == "") {
		$('#receive_date').datepicker('setDate', new Date());
	} else {
		$('#receive_date').datepicker('setDate', convertMmDate(receiveDate));
	}
	if (expDate == "") {
		$('#exp_date').datepicker('setDate', '');
	} else {
		$('#exp_date').datepicker('setDate', convertMmDate(expDate));
	}
	if (verifyDate == "") {
		$('#verify_date').datepicker('setDate', '');
	} else {
		$('#verify_date').datepicker('setDate', convertMmDate(verifyDate));
	}	
	$("#receive_by").val(receiveBy).trigger('change.select2');
	$("#check_by").val(checkBy).trigger('change.select2');
	$("#remarks").val(remarks);
		
	$("#refCultureInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#refCultureInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#refCultureInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/saveRefCultureInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#refCultureInfoModal").modal('hide');
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
	var ajaxURL = "${pageContext.request.contextPath}/logbook_micro/ref-culture-print";
	
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
