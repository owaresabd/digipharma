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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: REFRIGERATOR TEMPERATURE RECORD INFO</span>
		
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
								<th class="align-center" style="width: 110px;">DATE </th>
								<th class="align-center" style="width: 110px;">TIME</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 180px;">TEMPERATURE (&deg;C) BEFORE CORRECT</th>
								<th class="align-center" style="width: 180px;">TEMPERATURE (&deg;C) AFTER CORRECT</th>
								<th class="align-center" style="width: 200px;">DONE BY</th>
								<th class="align-center" style="width: 200px;">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-left">${info.recordTime}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center">${info.tempBefore}</td>
								<td class="align-center">${info.tempAfter}</td>
								<td class="align-left">${info.testedByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
									<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">Verified</span>
									</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_temp_before" value="${info.tempBefore}">
								<!--	<input type="hidden" id="r_temp_after" value="${info.tempAfter}">-->
									
									
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
<div class="modal fade" id="dissolutionInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog  modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book: Refrigerator Temperature Record</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-025</td>
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
                 <form method="post" id="tempRefrigInfoForm" >
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
				                        	<option>-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.equipmentId }">${info.equipmentByName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
                 			<div class="col-md-3 align-right top">
                            	<label for="lot_no">TEMP (&deg;C) BEFORE CORRECT :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="temp_before" name="tempBefore" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                            
                          <!--  <div class="col-md-3 align-right top">
                            	<label for="qty">TEMP (&deg;C) AFTER CORRECT :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="temp_after" name="tempAfter" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>-->
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
/*function pageReload(link){
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
	var link =  "${pageContext.request.contextPath}/refrigeratorSetup/maintainChemi";
	$("#dissolutionInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});
*/
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
    $( ".dates" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
    $('#recordTime').mdtimepicker({
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



function add(el) {
	$("#id").val("");
	$("#equipment_id").val("").trigger('change.select2');
	$("#remarks").val("");
	$("#temp_before").val("");
	//$("#temp_after").val("");
    $("#dissolutionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 		   = $(el).closest("tr").find("#row_id").val();
	var equipmentId = $(el).closest("tr").find("#r_equipment_id").val();
	var remarks    = $(el).closest("tr").find("#r_remarks").val();
	var tempBefore    = $(el).closest("tr").find("#r_temp_before").val();
	//var tempAfter    = $(el).closest("tr").find("#r_temp_after").val();
		
	$("#id").val(Id);
	$("#equipment_id").val(equipmentId).trigger('change.select2');
	$("#remarks").val(remarks);
	$("#temp_before").val(tempBefore);
	//$("#temp_after").val(tempAfter);
			
	$("#dissolutionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#tempRefrigInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#tempRefrigInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveRefrigeratorTempInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$("#dissolutionInfoModal").modal('hide');
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
