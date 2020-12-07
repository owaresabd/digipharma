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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOGBOOK:: ALLOTTED SAMPLE FOR ANALYST VALIDATION</span>
		
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
								<th class="align-center" style="width: 100px;">ANALYST NAME</th>
								<th class="align-center" style="width: 100px;">DOCUMENT CODE</th>
								<th class="align-center" style="width: 100px;">SAMPLE NAME/CODE</th>
								<th class="align-center" style="width: 100px;">ARN NO</th>
								<th class="align-center" style="width: 100px;">BATCH</th>
								<th class="align-center" style="width: 100px;">TEST MATHOD NAME</th>
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
								<td class="align-center">${info.analystName}</td>
								<td class="align-center">${info.documentCode}</td>
								<td class="align-center">${info.productName}</td>
								<td class="align-center">${info.qcRefName}</td>
								<td class="align-center">${info.batchNo}</td>
								<td class="align-left">${info.testMethodName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									
				                      <input type="hidden" id="row_id" value="${info.id}">
									 <input type="hidden" id="r_analyst_id" value="${info.analystId}">
									 <input type="hidden" id="r_product_id" value="${info.productId}">
									 <input type="hidden" id="r_test_method_id" value="${info.testMethodId}">
								     <input type="hidden" id="r_batch_no" value="${info.batchNo}">
									 <input type="hidden" id="r_document_code" value="${info.documentCode}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcRefId}">
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
<div class="modal fade" id="alloteeSampleModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book::Allotted Sample for Analyst Validation</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-MC-033</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td height="24">Revision No.</td>
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
                 <form method="post" id="alloteeSampleInfoForm" onkeypress="if (event.keyCode == 13) { return false; }">
                 
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 			<div class="row">
	                         <div class="col-md-2 align-right top">
                            	<label for="qc_ref_id">ARN NO. :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group"><!-- onchange="materialInfoByArn(this.id)"-->
                                <select id="qc_ref_id" name="qcRefId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Reference-</option>
			                        <c:forEach var="info" items="${arnInfos}">
			                           	<option value="${info.id }">${info.arnNo}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
                       	<div class="col-md-2 align-right top">
                            	<label for="analyst_id">ANALYST NAME :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<select id="analyst_id" name="analystId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select Analyst-</option>
				                        <c:forEach var="info" items="${sampleByList}">
				                           	<option value="${info.empId }">${info.empName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
						</div>
					  	<div class="row">
	                 	<div class="col-md-2 align-right top">
                            	<label for="document_code">DOCUMENT CODE :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
	                                <input type="text" id="document_code" name="documentCode" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-2 align-right top">
                            	<label for="product_id">PRODUCT NAME :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<select id="product_id" name="productId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select PRODUCT-</option>
				                        <c:forEach var="info" items="${materialList}">
				                           	<option value="${info.id }">${info.materialName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
						</div>
					  	<div class="row">
	                 	<div class="col-md-2 align-right top">
                            	<label for="batch_no">BATCH NO :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
	                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                  		     <div class="col-md-2 align-right top">
                            	<label for="test_method_id">TEST METHOD NAME :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<select id="test_method_id" name="testMethodId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select Test Method Name-</option>
				                        <c:forEach var="info" items="${methodList}">
				                           	<option value="${info.id }">${info.methodName}</option>
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
	
	$( "#recordDate" ).datepicker({
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
	$("#analyst_id").val("").trigger('change.select2');
	$("#product_id").val("").trigger('change.select2');
	$("#test_method_id").val("").trigger('change.select2');
	$("#document_code").val("");
	$("#batch_no").val("");
	$("#remarks").val("");
    $("#qc_ref_id").val("").trigger('change.select2');
	$("#alloteeSampleModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var id 			= $(el).closest("tr").find("#row_id").val();
	var analystId 	= $(el).closest("tr").find("#r_analyst_id").val();
	var productId = $(el).closest("tr").find("#r_product_id").val();
	var testMethodId = $(el).closest("tr").find("#r_test_method_id").val();
	var documentCode 	= $(el).closest("tr").find("#r_document_code").val();
	var batchNo 	= $(el).closest("tr").find("#r_batch_no").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var qcRefId    = $(el).closest("tr").find("#r_qc_ref_id").val();	
		
	$("#id").val(id);
	$("#analyst_id").val(analystId).trigger('change.select2');
	$("#product_id").val(productId).trigger('change.select2');
	$("#test_method_id").val(testMethodId).trigger('change.select2');
	$("#document_code").val(documentCode);
	$("#batch_no").val(batchNo);
	$("#remarks").val(remarks);
	$("#qc_ref_id").val(qcRefId).trigger('change.select2');
		
	$("#alloteeSampleModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#alloteeSampleInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#alloteeSampleInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/saveAllottedSample",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#alloteeSampleModal").modal('hide');
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

</script>
