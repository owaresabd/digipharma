<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">COLUMN LOGBOOK INFO</span>
		
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
								<th class="align-center" style="width: 100px;">COLUMN ID</th>
								<th class="align-center" style="width: 100px;">COLUMN NAME</th>
								<th class="align-center" style="width: 110px;">PART NO</th>
								<th class="align-center" style="width: 110px;">SERIAL NO</th>
								<th class="align-center" style="width: 100px;">OPENING DATE</th>
								<th class="align-center" style="width: 100px;">VERIFIED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.colId}</td>
								<td class="align-center">${info.colName}</td>
								<td class="align-center">${info.partNo}</td>
								<td class="align-center">${info.serialNo}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.openDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-left">${info.verifyByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_col_id" value="${info.colId}">
									<input type="hidden" id="r_col_name" value="${info.colName}">
									<input type="hidden" id="r_col_size" value="${info.colSize}">
									<input type="hidden" id="r_composition" value="${info.composition}">
									<input type="hidden" id="r_part_no" value="${info.partNo}">
									<input type="hidden" id="r_serial_no" value="${info.serialNo}">
									<input type="hidden" id="r_location" value="${info.location}">
									<input type="hidden" id="r_theore_plate" value="${info.theorePlate}">
									<input type="hidden" id="r_open_date" value="${info.openDate}">
									<input type="hidden" id="r_expire_date" value="${info.expireDate}">
									<input type="hidden" id="r_done_by" value="${info.doneBy}">
									<input type="hidden" id="r_verify_by" value="${info.verifyBy}">
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
<div class="modal fade" id="columnInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog md modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Column Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-013</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">17 OTC 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="columnInfoForm" modelAttribute="columnInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="col_id">Column ID :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="col_id" name="colId" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="col_name">Column Name :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="col_name" name="colName" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="col_size">Column size :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="col_size" name="colSize" value="" class="form-control" style="width: 50%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="location">Location :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="location" name="location" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="part_no">Part No :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="part_no" name="partNo" value="" class="form-control" style="width: 50%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="serial_no">Serial No :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="serial_no" name="serialNo" value="" class="form-control" style="width: 50%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                            <div class="col-md-2 align-right top">
                            	<label for="composition">Composition :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="composition" name="composition" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="theore_plate">Theoretical Plate :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="theore_plate" name="theorePlate" value="" class="form-control" style="width: 100%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="open_date">Opening Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="open_date" name="openDate" value="" class="form-control" style="width: 50%;" autocomplete="off">
                           	</div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="expire_date">Expired Date :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="expire_date" name="expireDate" value="" class="form-control" style="width: 50%;" autocomplete="off">
                           	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="done_by">Done By :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                            	<select  id="done_by" name="doneBy" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                            </div>
                            </div>
	                 		
	                 		<div class="col-md-2 align-right top">
                            	<label for="verify_by">Verified By :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                            	<select  id="verify_by" name="verifyBy" class="js-example-theme-single form-control" style="width: 100%;">
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
                            	<label for="remarks">REMARKS :</label>
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
	
	$( "#open_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
	$( "#expire_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
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

function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

	$("#productCode").val(data.materialCode);
	$('#productUnitId').val(data.unitId).trigger('change.select2');

	});
	}

function add(el) {
	$("#id").val("");
	$("#col_id").val("");
	$("#col_name").val("");
	$("#col_size").val("");
	$("#composition").val("");
	$("#part_no").val("");
	$("#serial_no").val("");
	$("#location").val("");
	$("#theore_plate").val("");
	$("#done_by").val("").trigger('change.select2');
	$("#verify_by").val("").trigger('change.select2');
	$('#open_date').datepicker('setDate', new Date());
	$('#expire_date').datepicker('setDate', '');
	$("#remarks").val("");
    $("#columnInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var colId		= $(el).closest("tr").find("#r_col_id").val();
	var colName 	= $(el).closest("tr").find("#r_col_name").val();
	var colSize 	= $(el).closest("tr").find("#r_col_size").val();
	var composition = $(el).closest("tr").find("#r_composition").val();
	var partNo 		= $(el).closest("tr").find("#r_part_no").val();
	var serialNo 	= $(el).closest("tr").find("#r_serial_no").val();
	var location 	= $(el).closest("tr").find("#r_location").val();
	var theorePlate = $(el).closest("tr").find("#r_theore_plate").val();
	var doneBy 		= $(el).closest("tr").find("#r_done_by").val();
	var verifyBy 	= $(el).closest("tr").find("#r_verify_by").val();
	var openDate 	= $(el).closest("tr").find("#r_open_date").val();
	var expireDate 	= $(el).closest("tr").find("#r_expire_date").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#col_id").val(colId);
	$("#col_name").val(colName);
	$("#col_size").val(colSize);
	$("#composition").val(composition);
	$("#part_no").val(partNo);
	$("#serial_no").val(serialNo);
	$("#location").val(location);
	$("#theore_plate").val(theorePlate);
	$("#done_by").val(doneBy).trigger('change.select2');
	$("#verify_by").val(verifyBy).trigger('change.select2');
	$('#open_date').datepicker('setDate', convertMmDate(openDate));
	$('#expire_date').datepicker('setDate', convertMmDate(expireDate));
	$("#remarks").val(remarks);
		
	$("#columnInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#columnInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#columnInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveColumnInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#columnInfoModal").modal('hide');
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
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/column-log-print";
	
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
