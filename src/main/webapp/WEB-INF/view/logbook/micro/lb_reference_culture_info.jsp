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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: LB-REFERENCE CULTURE RECIVING INFO</span>
		
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
								<th class="align-center" style="width: 110px;">SUPPLIER NAME</th>
								<th class="align-center" style="width: 100px;">CULTURE NAME</th>
								<th class="align-center" style="width: 100px;">START TIME</th>
								<th class="align-center" style="width: 100px;">END TIME</th>
								<th class="align-center" style="width: 100px;">ATCC NO.</th>
								<th class="align-center" style="width: 100px;">BATCH NO</th>
								<th class="align-center" style="width: 100px;">CERTIFICATE AVAILABLE</th>
								<th class="align-center" style="width: 100px;">EXPIRY DATE</th>
								<th class="align-center" style="width: 100px;">VERIFICATION DATE</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center"><fmt:formatDate var="recordDate" value="${info.recordDate}" pattern="dd-MMM-yyyy"/>
								${recordDate}</td>
								<td class="align-center">${info.supplierName}</td>
								<td class="align-center">${info.cultureName}</td>
								
								<td class="align-center">${info.startTime}</td>
								<td class="align-center">${info.endTime}</td>
								<td class="align-left">${info.atccNo}</td>
								<td class="align-left">${info.batchNo}</td>
								<td class="align-left">${info.certificateAvailable}</td>
								<td class="align-center"><fmt:formatDate var="expiryDate" value="${info.expiryDate}" pattern="dd-MMM-yyyy"/>
								${expiryDate}</td>
								<td class="align-center"><fmt:formatDate var="verificationDate" value="${info.verificationDate}" pattern="dd-MMM-yyyy"/>
								${verificationDate}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
								<c:choose>
									<c:when test="${info.checkedStatus =='Y'}">
									<span class="badge bg-green">Checked</span>
								</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_record_date" value="${info.recordDate}">
									<input type="hidden" id="r_start_time" value="${info.startTime}">
									<input type="hidden" id="r_end_time" value="${info.endTime}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">									
									<input type="hidden" id="r_supplier_id" value="${info.supplierId}">
									<input type="hidden" id="r_culture_id" value="${info.cultureId}">
									<input type="hidden" id="r_certificate_available" value="${info.certificateAvailable}">
									<input type="hidden" id="r_verification_date" value="${info.verificationDate}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_atcc_no" value="${info.atccNo}">
									<input type="hidden" id="r_expiry_date" value="${info.expiryDate}">
									
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
<div class="modal fade" id="lbReferencerInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog  modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book :: Reference  Culture Receving Record</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-MC-005</td>
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
                 <form method="post" id="lbReferenceCultureForm" >
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="supplier_id">MANUFACTURER :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
	                            	<select id="supplier_id" name="supplierId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select MANUFACTURER-</option>
				                        <c:forEach var="info" items="${manufactureList}">
				                           	<option value="${info.id }">${info.sourceName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
							<div class="col-md-3 align-right top">
                            	<label for="culture_id">REFERENCE MASTER/SEED CULTURE NAME :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
								   <input type="hidden" id="culture" name="cultureId" value=""/>
	                            	<select id="culture_id" name="" onchange="atccCultureSetup(this.value,'atcc_no','culture')" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select LOCATION-</option>
				                        <c:forEach var="info" items="${cultureItemsIfos}">
				                           	<option value="${info.id}@@@${info.atccNo}">${info.cultureItemName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
             	</div>
				<div class="row">
             	     	<div class="col-md-3 align-right top">
                            	<label for="atcc_no">ATCC NO :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="atcc_no" name="atccNo" value="" class="form-control"  autocomplete="off" readonly="" required="required">
                            	</div>
                            </div>
                  		  </div>
               	        <div class="row">
	          				<div class="col-md-3 align-right top">
                            	<label for="batch_no">BATCH NO:</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                  		  	<div class="col-md-3 align-right top">
                            	<label for="certificate_available">CERTIFICATE AVAILABLE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="certificate_available" name="certificateAvailable" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                  		</div>
               
							
							
                     	<div class="row">
	                    		<div class="col-md-3 align-right top">
                            	<label for="start_time">START TIME :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="start_time" name="startTime" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 				<div class="col-md-3 align-right top">
                            	<label for="end_time">END TIME :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="end_time" name="endTime" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
						<div class="row">
                 	        <div class="col-md-3 align-right top">
                            	<label for="expiry_date">EXPIRY DATE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
							<input type="text" id="expiry_date" name="expiryDate" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
	                        	</div>
                            </div>
					        <div class="col-md-3 align-right top">
                            	<label for="verification_date">VERIFICATION DATE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
							<input type="text" id="verification_date" name="verificationDate" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
	                        	</div>
                            </div>
							</div>
                     
                 		<div class="row">
	                 		<div class="col-md-3 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-9 m-t-15">
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
    theme: "classic",
    placeholder: "Select from list.."
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
		$( "#expiry_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#verification_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
});
function add(el) {
	$("#id").val("");
	$("#supplier_id").val("").trigger('change.select2');
	$("#culture_id").val("").trigger('change.select2');
	$("#start_time").val("");
	$("#end_time").val("");
	$("#remarks").val("");
	$("#expiry_date").val("");
	$("#verification_date").val("");
	$("#certificate_available").val("");
	$("#verification_date").val("");
	$("#batch_no").val("");
	$("#atcc_no").val("");
				
    $("#lbReferencerInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 		   = $(el).closest("tr").find("#row_id").val();
	var startTime = $(el).closest("tr").find("#r_start_time").val();
	var endTime = $(el).closest("tr").find("#r_end_time").val();
	var remarks    = $(el).closest("tr").find("#r_remarks").val();
	
	var supplierId    = $(el).closest("tr").find("#r_supplier_id").val();
	var expiryDate = $(el).closest("tr").find("#r_expiry_date").val();
	var verificationDate = $(el).closest("tr").find("#r_verification_date").val();
	var remarks    = $(el).closest("tr").find("#r_remarks").val();
	
	var certificateAvailable    = $(el).closest("tr").find("#r_certificate_available").val();
	var batchNo    = $(el).closest("tr").find("#r_batch_no").val();
	var cultureId    = $(el).closest("tr").find("#r_culture_id").val();
	var atccNo    = $(el).closest("tr").find("#r_atcc_no").val();
		
	$("#id").val(Id);
	$("#supplier_id").val(supplierId).trigger('change.select2');
	$("#start_time").val(startTime);
	$("#end_time").val(endTime);
	$("#remarks").val(remarks);
	$("#expiry_date").val(expiryDate);
	
	$("#certificate_available").val(certificateAvailable);
	$("#verification_date").val(verificationDate);
	$("#batch_no").val(batchNo);
	$("#atcc_no").val(atccNo);
	$("#culture_id").val(cultureId+"@@@"+atccNo).trigger('change.select2');
	$("#culture").val(cultureId);
	
	
	
		
	$("#lbReferencerInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#lbReferenceCultureForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#lbReferenceCultureForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/savelbReferenceCultureInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$("#lbReferencerInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "error", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

function atccCultureSetup(val, dt1, val2){
         var valSplt=val.split('@@@');	
		 if(valSplt!="-Select LOCATION-"){
			 $('#'+val2).val($.trim(valSplt[0]))
			 $('#'+dt1).val($.trim(valSplt[1]));
		 }else{
			 $('#'+val2).val("")
			 $('#'+dt1).val("");
		 
		 }
}
	
</script>
