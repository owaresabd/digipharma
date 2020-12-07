<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">COLUMN PERFORMANCE PENDING RECORD LOGBOOK INFO</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>

        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
				<!--<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button>-->
					
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 100px;">COLUMN CODE NO.</th>
								<th class="align-center" style="width: 110px;">DATE</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT  ID NO.</th>
								<th class="align-center" style="width: 110px;">AR NO.</th>
								<!--<th class="align-center" style="width: 150px;">TP</th>
								<th class="align-center" style="width: 150px;">TF</th>
								<th class="align-center" style="width: 100px;">% RSD</th>
								<th class="align-center" style="width: 100px;">REMARKS</th>-->
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.colName}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="recordDate" />
		                              		<c:out value="${recordDate}"/>
								</td>
								<td class="align-center">${info.equipmentByName}</td>
								<td class="align-center">
								<c:choose>
							    	<c:when test="${(info.arnName !=null) && (not empty info.arnName)}">
									${info.arnName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>	
								</td>
								<!--<td class="align-center">${info.tp}</td>
								<td class="align-center">${info.tf}</td>
								<td class="align-center">${info.rsd}</td>
								<td class="align-center">${info.remarks}</td>-->
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_col_id" value="${info.colId}">
									<input type="hidden" id="r_col_name" value="${info.colName}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_equipment_by_name" value="${info.equipmentByName}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcRefId}">
									<input type="hidden" id="r_arn_name" value="${info.arnName}">
									<input type="hidden" id="r_tp" value="${info.tp}">
									<input type="hidden" id="r_tf" value="${info.tf}">
									<input type="hidden" id="r_rsd" value="${info.rsd}">
									<input type="hidden" id="r_record_date" value="${info.recordDate}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
								&nbsp
								<a class="btn-edit btn btn-xs" onclick="columnRecord(this)"><i class="material-icons">near_me</i></a>
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
    
     <div class="modal fade" id="columnPerformancePendingModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog md modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Column Performance Record Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-028</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">19 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="columnPerformancePendingForm">
                 		<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 			<div class="row">
								<div class="col-md-2 align-right top">
	                            	<label for="column_code_name">COLUMN CODE NO. :</label>
	                            </div>
	                            <div class="col-md-4">
		                       <div class="form-group">
							  <input type="text" disabled="disabled" id="column_code_name" name="" class="form-control" style="width: 100%;" >
		                      <input type="hidden"  id="col_id" name="colId" class="form-control" style="width: 100%;" >
							  <input type="hidden"  id="equipment_id" name="equipmentId" class="form-control" style="width: 100%;" >
							  <input type="hidden"  id="qc_ref_id" name="qcRefId" class="form-control" style="width: 100%;" >
							   	</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="room_id">DATE :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
								    <input type="text" disabled="disabled" id="date_record" name="dateRecord" value="" class="form-control" style="width: 100%;" >
									</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="equipment_by_name">EQUIPMENT ID NO.:</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group">
								   <input type="text" disabled="disabled" id="equipment_by_name" name="" value="" class="form-control" style="width: 100%;" autocomplete="off">
									
									</div>
									</div>
									
									<div class="col-md-2 align-right top">
		                            	<label for="column _id">AR NO. :</label>
		                            </div>
	                          	  	<div class="col-md-4">
		                            <div class="form-group"> 
									<input type="text" disabled="disabled" id="arn_name" name="" value="" class="form-control" style="width: 100%;" autocomplete="off">
									</div>
									</div>
								</div>
                 		
		                 	   <div class="row">
		                            <div class="col-md-2 align-right top">
		                            	<label for="tp">TP :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="tp" name="tp" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            <div class="col-md-2 align-right top">
		                            	<label for="tf">TF :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="tf" name="tf" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                            
		                        </div>
		                        <div class="row">
								    <div class="col-md-2 align-right top">
		                            	<label for="rsd">RSD :</label>
		                            </div>
		                            <div class="col-md-4">
		                            <div class="form-group">
		                                <input type="text" id="rsd" name="rsd" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
		                           	</div>
		                            </div>
		                  
		                            <div class="col-md-2 align-right top">
			                            <label for="remarks">Remarks :</label>
			                        </div>
			                       <div class="col-md-4">
			                            	<div class="form-group">
			                                	<textarea rows="2"  id="remarks" name="remarks" class="form-control" maxlength="220" placeholder="Description goes here......." ></textarea>
				                            </div>
			                            </div>
							  	</div>
	                 </div>
	             
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" style="margin-right: 50%" class="btn bg-red btn-sm waves-effect pull-right m-r-20"  data-dismiss="modal">
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


$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$(function() {
    $('#ehd_no').keyup(function() {
        this.value = this.value.toUpperCase();
    });
    
});

function columnRecord(el){
	
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var colId		= $(el).closest("tr").find("#r_col_id").val();
	var colName		= $(el).closest("tr").find("#r_col_name").val();
	var equipmentId		= $(el).closest("tr").find("#r_equipment_id").val();
	var equipmentByName		= $(el).closest("tr").find("#r_equipment_by_name").val();
	var qcRefId		= $(el).closest("tr").find("#r_qc_ref_id").val();
	var arnName		= $(el).closest("tr").find("#r_arn_name").val();
	var recordDate = $(el).closest("tr").find("#r_record_date").val();

	$("#id").val(Id);
	$("#col_id ").val(colId);
	$("#column_code_name ").val(colName);
	$("#equipment_id").val(equipmentId);
	$("#equipment_by_name").val(equipmentByName);
	$("#qc_ref_id").val(qcRefId);
	$("#arn_name").val(arnName);
	$('#date_record').val(convertMmDate(recordDate));
		
	$("#columnPerformancePendingModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};


$("#columnPerformancePendingForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#columnPerformancePendingForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveColumnPerformanceRecord",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#columnPerformancePendingModal").modal('hide');
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
