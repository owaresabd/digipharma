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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: HOT AIR OVEN CLEAN INFO</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 50px;">SL NO</th>
								<th class="align-center" style="width: 80px;">DATE</th>
								<th class="align-center" style="width: 100px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 80px;">MATERIAL NAME</th>
								<th class="align-center" style="width: 80px;">HEATING CYCLE</th>
								<th class="align-center" style="width: 80px;">TOTAL CYCLE</th>
								<th class="align-center" style="width: 60px;">STER. START TIME</th>
								<th class="align-center" style="width: 60px;">STER. END TIME</th>
								<th class="align-center" style="width: 140px;">CLEANED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 60px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center"><fmt:formatDate var="recordDate" value="${info.recordDate}" pattern="dd-MMM-yyyy"/>
								${recordDate}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">${info.materialName}</td>
								
								<td class="align-center">${info.heatingCycle}</td>
								<td class="align-center">${info.totalCycle}</td>
								<td class="align-center">${info.sterStartTime}</td>
								<td class="align-center">${info.sterEndTime}</td>
								<td class="align-center">${info.cleanedByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
								<a class="btn-edit btn btn-xs" onclick="editClean(this)"><i class="material-icons">mode_edit</i></a>
									<input type="hidden" id="row_id" value="${info.id}">
									<input name="hidden" type="hidden" id="r_record_date" value="${info.recordDate}" />
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_material_id" value="${info.materialId}">
									<input type="hidden" id="r_heating_cycle" value="${info.heatingCycle}">
									<input type="hidden" id="r_total_cycle" value="${info.totalCycle}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_indicator_tape" value="${info.indicatorTape}">
									<input type="hidden" id="r_ster_start_time" value="${info.sterStartTime}">
									<input type="hidden" id="r_ster_end_time" value="${info.sterEndTime}">
						
									<input type="hidden" id="r_material_name" value="${info.materialName}">
									<input type="hidden" id="r_equipment_name" value="${info.equipmentName}">
									<input type="hidden" id="r_clean_by" value="${info.cleanedBy}">						
									
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
<div class="modal fade" id="autoClaveInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog  modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book :: Hot Air Oven</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-MC-024</td>
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
                 <form method="post" id="autoCleveInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 			<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="equipment_id">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
							      <input type="text" id="equipment_name" value="" class="form-control" readonly="">
                          
	                            <!--<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>-->
	                            </div>
                            </div>
					  		<div class="col-md-2 align-right top">
                            	<label for="equipment_id">MATERIALS TO BE SETRILIZED :</label>
                            </div>
                               <div class="col-md-4">
                           	<div class="form-group">
							    <input type="text" id="material_name" value="" class="form-control" readonly="">
                                <!--<select  id="material_id" name="materialId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Sample-</option>
			                        <c:forEach var="info" items="${materialList}">
			                           	<option value="${info.id }">${info.materialName}</option>
			                        </c:forEach>
			                    </select>-->
                           	</div>
                            </div>
                    	</div>
                     	<div class="row">
	                    		<div class="col-md-2 align-right top">
                            	<label for="start_time">STRI. START TIME :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="ster_start_time" name="sterStartTime" value="" class="form-control" style="width: 30%;"  readonly="">
                           	</div>
                            </div>
                 				<div class="col-md-2 align-right top">
                            	<label for="end_time">STRI. END TIME :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="ster_end_time" name="sterEndTime" value="" class="form-control" style="width: 30%;" autocomplete="off"  readonly="">
                           	</div>
                            </div>
                 		</div>
                 	 	<div class="row">
	                    <div class="col-md-2 align-right top">
                            	<label for="location">HEATING CYCLE :</label>
                            </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="heating_cycle" name="heatingCycle" value="" class="form-control" style="width: 100%;"  readonly="">
                           	</div>
                            </div>
              		    <div class="col-md-2 align-right top">
                            	<label for="location">TOTAL CYCLE :</label>
                            </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="total_cycle" name="totalCycle" value="" class="form-control" style="width: 100%;"  readonly="">
                           	</div>
                            </div>
              		  </div>
                 	 	<div class="row">
	                    <div class="col-md-2 align-right top">
                            	<label for="location">INDICATOR BATCH NO :</label>
                            </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control" style="width: 100%;" readonly="">
                           	</div>
                            </div>
              		    <div class="col-md-2 align-right top">
                            	<label for="location">INSERTION OF INDICATOR TAPE :</label>
                            </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="indicator_tape" name="indicatorTape" value="" class="form-control" style="width: 100%;" readonly="">
                           	</div>
                            </div>
              		  </div>
              	    	   <div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="clean_by">CLEANED BY :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="clean_by" name="cleanBy" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.empId }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
	             		</div>
             
                 		<div class="row">
	                 		<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" readonly="readonly" placeholder="Description goes here......." ></textarea>
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
});


function editClean(el) {
	var Id 		   = $(el).closest("tr").find("#row_id").val();
	var sterStartTime = $(el).closest("tr").find("#r_ster_start_time").val();
	var sterEndTime = $(el).closest("tr").find("#r_ster_end_time").val();
	var remarks    = $(el).closest("tr").find("#r_remarks").val();
	var equipmentId    = $(el).closest("tr").find("#r_equipment_id").val();
	var itemId    = $(el).closest("tr").find("#r_material_id").val();
	var heatingCycle    = $(el).closest("tr").find("#r_heating_cycle").val();
	var totalCycle    = $(el).closest("tr").find("#r_total_cycle").val();
	var batchNo    = $(el).closest("tr").find("#r_batch_no").val();
	var indicatorTape    = $(el).closest("tr").find("#r_indicator_tape").val();
	
	var materialName    = $(el).closest("tr").find("#r_material_name").val();
	var equipmentName    = $(el).closest("tr").find("#r_equipment_name").val();
	var cleanBy    = $(el).closest("tr").find("#r_clean_by").val();
	
		
	$("#id").val(Id);
	
	$("#material_name").val(materialName);
	$("#equipment_name").val(equipmentName);
	$("#clean_by").val(cleanBy).trigger('change.select2');
	//$("#equipment_id").val(equipmentId).trigger('change.select2');
	//$("#material_id").val(itemId).trigger('change.select2');
	$("#ster_start_time").val(sterStartTime);
	$("#ster_end_time").val(sterEndTime);
	$("#heating_cycle").val(heatingCycle);
	$("#total_cycle").val(totalCycle);
	$("#batch_no").val(batchNo);
	$("#indicator_tape").val(indicatorTape);	
	$("#remarks").val(remarks);
		
	$("#autoClaveInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#autoCleveInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#autoCleveInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/cleanHotAirOven",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$("#autoClaveInfoModal").modal('hide');
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

	
</script>
