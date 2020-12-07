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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOGBOOK:: ANALYST VALIDATION</span>
		
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
								<th class="align-center" style="width: 110px;">ANALYST NAME</th>
								<th class="align-center" style="width: 110px;">ANALYST ID</th>
								<th class="align-center" style="width: 100px;">AREA OF COMPETENCY</th>
								<th class="align-center" style="width: 100px;">DATE OF COMPETENCY</th>
								<th class="align-center" style="width: 100px;">NEXT DATE OF COMPETENCY</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 100px;">APPROVED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.analystName}</td>
								<td class="align-center">${info.udAnalystId}</td>
								<td class="align-center">${info.areaOfCompetency}</td>
								<td class="align-center"><fmt:formatDate var="dateOfCompetency" value="${info.dateOfCompetency}" pattern="dd-MMM-yyyy"/>
								${dateOfCompetency}</td>
								<td class="align-center"><fmt:formatDate var="nextDateOfCompetency" value="${info.nextDateOfCompetency}" pattern="dd-MMM-yyyy"/>
								${nextDateOfCompetency}</td>
								<td class="align-center">${info.status}</td>
								<td class="align-center">${info.dispensedByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									
									<c:choose>
									    <c:when test="${info.isVerify =='Y'}">
											<span class="badge bg-green">Verified</span>
									    </c:when>    
									<c:otherwise>
										<input type="hidden" id="row_id" value="${info.id}">
										<input type="hidden" id="r_analyst_id" value="${info.analystId}">
										<input type="hidden" id="r_date_of_competency" value="${info.dateOfCompetency}">
										<input type="hidden" id="r_area_of_competency" value="${info.areaOfCompetency}">
										<input type="hidden" id="r_next_date_of_competency" value="${info.nextDateOfCompetency}">
										<input type="hidden" id="r_remarks" value="${info.remarks}">
										<input type="hidden" id="r_ud_analyst_id" value="${info.udAnalystId}">
										<input type="hidden" id="r_status" value="${info.status}">
										
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
<div class="modal fade" id="AnalystValidationInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book::Analyst Validation</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-CM-022</td>
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
                 <form method="post" id="analystValidationInfoForm" onkeypress="if (event.keyCode == 13) { return false; }">
                 
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 	    <div class="col-md-3 align-right top">
                            	<label for="analyst_id">ANALYST NAME :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
								 <input type="hidden" id="analyst_id" name="analystId" value="" class="form-control"  autocomplete="off" required="required">
	                            	<select id="analyst_id_temp" name="" onchange="getData(this.value)" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select Analyst-</option>
				                        <c:forEach var="info" items="${sampleByList}">
				                           	<option value="${info.empId }*${info.udEmpNo }">${info.empName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
								<div class="col-md-3 align-right top">
                            	<label for="ud_analyst_id">ANALYST ID :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" readonly="" id="ud_analyst_id" name="udAnalystId" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                       </div>
                 		<div class="row">
		                    <div class="col-md-3 align-right top">
                            	<label for="area_of_competency">AREA OF COMPETENCY :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="area_of_competency" name="areaOfCompetency" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                     
							<div class="col-md-3 align-right top">
                            	<label for="date_of_competency">DATE OF COMPETENCY :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
							<input type="text" id="date_of_competency" name="dateOfCompetency" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
                           
	                        	</div>
                            </div>
                     	</div>
						
						<div class="row">
		                    <div class="col-md-3 align-right top">
                            	<label for="next_date_of_competency">NEXT DATE OF COMPETENCY :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
							<input type="text" id="next_date_of_competency" name="nextDateOfCompetency" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
	                        	</div>
                            </div>
                     
							<div class="col-md-3 align-right top">
                            	<label for="status">STATUS :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="status" name="status" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                     	</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-3 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-9 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" placeholder="Description goes here......." ></textarea>
	                            </div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
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
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
});
$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#date_of_competency" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
		$( "#next_date_of_competency" ).datepicker({
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
	$("#analyst_id_temp").val("").trigger('change.select2');
	$("#analyst_id").val("");
	$("#date_of_competency").val("");
	$("#area_of_competency").val("");
	$("#next_date_of_competency").val("");
	$("#ud_analyst_id").val("");
	$("#status").val("");
	$("#remarks").val("");
    $("#AnalystValidationInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var id 			= $(el).closest("tr").find("#row_id").val();
	var analystId = $(el).closest("tr").find("#r_analyst_id").val();
	var dateOfCompetency 	= $(el).closest("tr").find("#r_date_of_competency").val();
	var areaOfCompetency 	= $(el).closest("tr").find("#r_area_of_competency").val();
	var nextDateOfCompetency 		= $(el).closest("tr").find("#r_next_date_of_competency").val();
	var udAnalystId 	= $(el).closest("tr").find("#r_ud_analyst_id").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var status 	= $(el).closest("tr").find("#r_status").val();
	var analystTempId=analystId+"*"+udAnalystId
	
	$("#id").val(id);
	$("#analyst_id_temp").val(analystTempId).trigger('change.select2');
	$("#analyst_id").val(analystId);
	$("#date_of_competency").val(dateOfCompetency);
	$("#area_of_competency").val(areaOfCompetency);
	$("#next_date_of_competency").val(nextDateOfCompetency);
	$("#ud_analyst_id").val(udAnalystId);
	$("#remarks").val(remarks);
	$("#status").val(status);
		
	$("#AnalystValidationInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#analystValidationInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#analystValidationInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveAnalystValidation",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#AnalystValidationInfoModal").modal('hide');
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
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

function getData(val){
	var valSplt=val.split('*');	
	$("#analyst_id").val($.trim(valSplt[0]));
	$("#ud_analyst_id").val($.trim(valSplt[1]));
}

</script>
