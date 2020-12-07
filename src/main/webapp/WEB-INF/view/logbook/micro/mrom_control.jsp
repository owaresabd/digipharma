<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">MEDIA, REAGENT &amp; OTHER MATERIALS RECEVING &amp; STOCK CONTROL INFO</span>
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
								<th class="align-center" style="width: 100px;">NAME OF DISINFECTANT</th>
								<th class="align-center" style="width: 100px;">STORAGE TEMP</th>
								<th class="align-center" style="width: 110px;">DATE</th>
								<th class="align-center" style="width: 100px;">PREPARED BY</th>
								<th class="align-center" style="width: 100px;">CHECKED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.disinfecName}</td>
								<td class="align-center">${info.storeTemp}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-left">${info.prepareByName}</td>
								<td class="align-left">${info.checkByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_disinfec_name" value="${info.disinfecName}">
									<input type="hidden" id="r_amt_disinfec" value="${info.amtDisinfec}">
									<input type="hidden" id="r_amt_distilled" value="${info.amtDistilled}">
									<input type="hidden" id="r_total_amt" value="${info.totalAmt}">
									<input type="hidden" id="r_start_time" value="${info.startTime}">
									<input type="hidden" id="r_end_time" value="${info.endTime}">
									<input type="hidden" id="r_store_temp" value="${info.storeTemp}">
									<input type="hidden" id="r_record_date" value="${info.recordDate}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									<input type="hidden" id="r_prepare_by" value="${info.prepareBy}">
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
<div class="modal fade" id="disinfectantSolInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Media, Reagent &amp; Other Materials Receving &amp; Stock Control Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-MC-004</td>
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
                 <form method="post" id="disinfectantSolInfoForm" modelAttribute="disinfectantSolInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="disinfec_name">Name of Material :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="disinfec_name" name="disinfecName" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="amt_disinfec">Amount of Used Disinfectant Agent :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="amt_disinfec" name="amtDisinfec" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="amt_distilled">Amount of Used Distilled Water :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="amt_distilled" name="amtDistilled" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-2 align-right top">
                            	<label for="total_amt">Total Amount of Preparation :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="total_amt" name="totalAmt" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 			
	                 		<div class="col-md-2 align-right top">
                            	<label for="record_date">Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="record_date" name="recordDate" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="start_time">Filtration Start Time :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="start_time" name="startTime" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="end_time">Filtration End Time :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="end_time" name="endTime" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-2 align-right top">
                            	<label for="store_temp">Storage Temp (°C) :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="store_temp" name="storeTemp" value="" class="form-control" style="width: 30%;" autocomplete="off">
                           	</div>
                            </div>
                 			
	                 		<div class="col-md-2 align-right top">
                            	<label for="exp_date">Expiry Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="exp_date" name="expDate" value="" class="form-control" style="width: 30%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="prepare_by">Prepared By :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                            	<select  id="prepare_by" name="prepareBy" class="js-example-theme-single form-control" style="width: 100%;">			                        <option value="">-Select Employee-</option>
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
	
	$( "#record_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#exp_date" ).datepicker({
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
	$("#disinfec_name").val("");
	$("#amt_disinfec").val("");
	$("#amt_distilled").val("");
	$("#total_amt").val("");
	$("#store_temp").val("");
	$('#record_date').datepicker('setDate', new Date());
	$('#exp_date').datepicker('setDate', '');
	$("#start_time").val("");
	$("#end_time").val("");	
	$("#prepare_by").val("").trigger('change.select2');
	$("#check_by").val("").trigger('change.select2');
	$("#remarks").val("");
    $("#disinfectantSolInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var disinfecName = $(el).closest("tr").find("#r_disinfec_name").val();
	var amtDisinfec	 = $(el).closest("tr").find("#r_amt_disinfec").val();
	var amtDistilled = $(el).closest("tr").find("#r_amt_distilled").val();
	var totalAmt	 = $(el).closest("tr").find("#r_total_amt").val();
	var startTime	 = $(el).closest("tr").find("#r_start_time").val();
	var endTime		 = $(el).closest("tr").find("#r_end_time").val();
	var storeTemp	 = $(el).closest("tr").find("#r_store_temp").val();
	var recordDate 	 = $(el).closest("tr").find("#r_record_date").val();
	var expDate	 	 = $(el).closest("tr").find("#r_exp_date").val();
	var prepareBy	 = $(el).closest("tr").find("#r_prepare_by").val();
	var checkBy 	 = $(el).closest("tr").find("#r_check_by").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#disinfec_name").val(disinfecName);
	$("#amt_disinfec").val(amtDisinfec);
	$("#amt_distilled").val(amtDistilled);
	$("#total_amt").val(totalAmt);
	$("#store_temp").val(storeTemp);
	if (recordDate == "") {
		$('#record_date').datepicker('setDate', new Date());
	} else {
		$('#record_date').datepicker('setDate', convertMmDate(recordDate));
	}
	if (expDate == "") {
		$('#exp_date').datepicker('setDate', '');
	} else {
		$('#exp_date').datepicker('setDate', convertMmDate(expDate));
	}
	$("#start_time").val(startTime);
	$("#end_time").val(endTime);	
	$("#prepare_by").val(prepareBy).trigger('change.select2');
	$("#check_by").val(checkBy).trigger('change.select2');
	$("#remarks").val(remarks);
		
	$("#disinfectantSolInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#disinfectantSolInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#disinfectantSolInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/savePrepStoreDisinfectantInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#disinfectantSolInfoModal").modal('hide');
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
	var ajaxURL = "${pageContext.request.contextPath}/logbook_micro/disinfectant-solution-print";
	
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
