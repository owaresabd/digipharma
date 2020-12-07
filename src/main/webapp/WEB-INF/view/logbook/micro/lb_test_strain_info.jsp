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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: LB-TEST STRAIN INFO</span>
		
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
								<th class="align-center" style="width: 110px;">NAME OF WORKING CULTURE</th>
								<th class="align-center" style="width: 100px;">START TIME</th>
								<th class="align-center" style="width: 100px;">END TIME</th>
								<th class="align-center" style="width: 100px;">ATCC NO.</th>
								<th class="align-center" style="width: 100px;"> TOTAL SUSPENSION VOLUE (ML)</th>
								<th class="align-center" style="width: 100px;">RESULT (CFU/ML) P1</th>
								<th class="align-center" style="width: 100px;">RESULT (CFU/ML) P2</th>
								<th class="align-center" style="width: 100px;">AVERAGE (CFU/ML)</th>
								<th class="align-center" style="width: 100px;">STORAGE CONDITION (&deg;C)</th>
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
								<td class="align-center">${info.cultureName}</td>
								<td class="align-center">${info.startTime}</td>
								<td class="align-center">${info.endTime}</td>
								<td class="align-left">${info.atccNo}</td>
								<td class="align-left">${info.totalSuspensionVolue}</td>
								<td class="align-left">${info.resultP1}</td>
								<td class="align-left">${info.resultP2}</td>
								<td class="align-left">${info.averageResult}</td>
								<td class="align-left">${info.storageCondition}</td>
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
									<input type="hidden" id="r_total_suspension_volue" value="${info.totalSuspensionVolue}">
									<input type="hidden" id="r_culture_id" value="${info.cultureId}">
									<input type="hidden" id="r_result_p1" value="${info.resultP1}">
									<input type="hidden" id="r_result_p2" value="${info.resultP2}">
									<input type="hidden" id="r_average_result" value="${info.averageResult}">
									<input type="hidden" id="r_atcc_no" value="${info.atccNo}">
									<input type="hidden" id="r_storage_condition" value="${info.storageCondition}">
									
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
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book :: LB-Test Strain Record</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-MC-006</td>
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
	                 		<div class="col-md-6 align-right top">
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
	                                <input type="text" id="atcc_no" name="atccNo" value="" class="form-control"  autocomplete="off"  required="required">
                            	</div>
                            </div>
                  	  		<div class="col-md-3 align-right top">
                            	<label for="total_suspension_volue">TOTAL SUSPENSION VOLUE (ML) :</label>
                            </div>
                        	<div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="total_suspension_volue" name="totalSuspensionVolue" value="" class="form-control"  autocomplete="off"  required="required">
                            	</div>
                            </div>
                    	</div>
				<div class="row">
             				<div class="col-md-3 align-right top">
                            	<label for="result_p1"> RESULT (CFU/ML) P1:</label>
                            </div>
                        	<div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="result_p1" name="resultP1" value="" onkeyup="sumAverage()" class="form-control"  autocomplete="off"  required="required">
                            	</div>
                            </div>
                  			<div class="col-md-3 align-right top">
                            	<label for="result_p2"> RESULT (CFU/ML) P2:</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="result_p2" name="resultP2" value="" onkeyup="sumAverage()" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                  		  </div>
               	        <div class="row">
	         	 			<div class="col-md-3 align-right top">
                            	<label for="average_result"> AVERAGE (CFU/ML) RESULT :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="average_result" name="averageResult" value="" class="form-control"   autocomplete="off" required="required">
                            	</div>
                            </div>
                  	        <div class="col-md-3 align-right top">
                            	<label for="start_time">START TIME :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="start_time" name="startTime" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                           	</div>
                            </div>
							 </div>
               	        <div class="row">
	        
                 			<div class="col-md-3 align-right top">
                            	<label for="end_time">END TIME :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="end_time" name="endTime" value="" class="form-control" style="width: 40%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 	        <div class="col-md-3 align-right top">
                            	<label for="storage_condition"> STORAGE CONDITION (&deg;C):</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
						<input type="text" id="storage_condition" name="storageCondition" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
	                        
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
		
});
function add(el) {
	$("#id").val("");
	$("#culture_id").val("").trigger('change.select2');
	$("#atcc_no").val("");
	$("#start_time").val("");
	$("#end_time").val("");
	$("#remarks").val("");
	$("#total_suspension_volue").val("");
	$("#result_p1").val("");
	$("#result_p2").val("");
	$("#average_result").val("");
	$("#storage_condition").val("");
				
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
	var cultureId    = $(el).closest("tr").find("#r_culture_id").val();
	var atccNo    = $(el).closest("tr").find("#r_atcc_no").val();	
	var totalSuspensionVolue    = $(el).closest("tr").find("#r_total_suspension_volue").val();
	var resultP1    = $(el).closest("tr").find("#r_result_p1").val();
	var resultP2    = $(el).closest("tr").find("#r_result_p2").val();
	var averageResult    = $(el).closest("tr").find("#r_average_result").val();
	var storageCondition    = $(el).closest("tr").find("#r_storage_condition").val();
	
		
	$("#id").val(Id);
	$("#start_time").val(startTime);
	$("#end_time").val(endTime);
	$("#remarks").val(remarks);
	$("#atcc_no").val(atccNo);
	$("#culture_id").val(cultureId+"@@@"+atccNo).trigger('change.select2');
	$("#culture").val(cultureId);	
	$("#total_suspension_volue").val(totalSuspensionVolue);
	$("#result_p1").val(resultP1);
	$("#result_p2").val(resultP2);
	$("#average_result").val(averageResult);
	$("#storage_condition").val(storageCondition);	
		
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/savelbTestStrainInfo",
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

function sumAverage(){
    var first=parseFloat($("#result_p1").val());
    var second=parseFloat($("#result_p2").val());
	var avg=+(first+second).toFixed(2)/2;
		$("#average_result").val(avg);
}
	
</script>
