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
	   <span style="text-shadow: 2px 2px 2px #aaa;">TEMPERATURE REFRIGERATOR MORNING RECORD LOGBOOK INFO</span>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button>					
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList" style="">
						<thead>
							<tr>
								<td class="align-center" style="width: 70px;" rowspan="3">SL NO</td>
								<td class="align-center" style="width: 150px;" rowspan="3">EQUIPMENT ID</td>
								<td class="align-center" style="width: 100px;" rowspan="3">DATE</td>
								<td class="align-center" style="width: 100px;" colspan="3">OBSERVED TEMPERATURE (°C)</td>
								<td class="align-center" style="width: 100px;" rowspan="3">REMARKS</td>
								<th class="align-center" style="width: 100px;" rowspan="3">ACTION</th>
						    </tr>
							<tr>
								<td class="align-center" colspan="2">MORNING <br />(08:30 AM to 09 :30 AM)</td>
								<td class="align-center" style="width: 100px; border: 2px solid #ddd;" rowspan="2">RECORDED BY</td>
					    	</tr>
							<tr>
							<td class="align-center" style="width: 100px;">TIME</td>
							<td class="align-center" style="width: 100px;">TEMP. (°C)</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-center">${info.morningTime}</td>
								<td class="align-center">${info.morningTemp}</td>
								<td class="align-center">${info.doneByName}</td>
								
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
										<c:when test="${info.checkedStatus =='Y'}">
											<span class="badge bg-green">Checked</span>
									    </c:when>
										  	<c:when test="${info.cleanedStatus =='Y'}">
											<span class="badge bg-green">Cleaned</span>
									    </c:when>
										  <c:when test="${info.eveningStatus =='Y'}">
											<span class="badge bg-green">Evening Entry</span>
									    </c:when>
									<c:otherwise>
									<input type="hidden" id="r_id" value="${info.id}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_morning_temp" value="${info.morningTemp}">
									<input type="hidden" id="r_morning_time" value="${info.morningTime}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>
									</c:choose>
									
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
<div class="modal fade" id="TempRefriMorningRecordModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Temperature and Cleaning Record of Incubator</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-MC-026</td>
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
                 <form method="post" id="refrtempMorningRecordForm" modelAttribute="refrigeratorInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
	                 		<div class="row">
		                 		<div class="col-md-3 align-right top">
	                            	<label for="equipment_id">EQUIPMENT ID :</label>
	                            </div>
	                            <div class="col-md-3">
		                            <div class="form-group">
		                            	<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
					                        	<option value="">-Select Equipment-</option>
					                        <c:forEach var="info" items="${equipInfos}">
					                           	<option value="${info.equipmentId}">${info.equipmentByName}</option>
					                        </c:forEach>
					                    </select>
	                            </div>
	                 		</div>
	            			</div>
	            			<div class="row">
	                 			<div class="col-md-3 align-right top">
	                            	<label for="qty">MORNING TIME :</label>
	                            </div>
	                            <div class="col-md-3">
		                          <div class="form-group">
		                           <input type="text" id="morning_time" name="morningTime" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
                           	   	</div>
	                           	</div>

	            				<div class="col-md-3 align-right top">
	                            	<label for="qty">MORNING TEMPERATURE(°C) :</label>
	                            </div>
	                            <div class="col-md-3">
	                            <div class="form-group">
	                                <input type="text" id="morning_temp" name="morningTemp" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
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
	
    $('#morning_time').mdtimepicker({
	      defaultTime: '09:00 AM',
	      dynamic: false,
	      dropdown: true,
	      scrollbar: true,
	      minuteStep: 1
	    });
   
});

function add(el) { 
	$("#id").val("");
	$("#equipment_id").val("").trigger('change.select2')
	$("#morning_time").val("");
	$("#morning_temp").val("");
    $("#remarks").val("");
    $("#TempRefriMorningRecordModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
}); 
function edit(el) {
	var Id 			 = $(el).closest("tr").find("#r_id").val();
	var eId = $(el).closest("tr").find("#r_equipment_id").val();
	var morningTime = $(el).closest("tr").find("#r_morning_time").val();
	var morningTemp		 = $(el).closest("tr").find("#r_morning_temp").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
	$("#equipment_id").val(eId).trigger('change');
	$("#id").val(Id);
	$("#morning_time").val(morningTime);
	$("#morning_temp").val(morningTemp);
	$("#remarks").val(remarks);
	
	$("#TempRefriMorningRecordModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#refrtempMorningRecordForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#refrtempMorningRecordForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/saveTempRefMorningRecord",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#TempRefriMorningRecordModal").modal('hide');
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