<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">COMPARATIVE DISSOLUTION STUDY RESULT ENTRY LOGBOOK INFO</span>
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
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 100px;">PRODUCT NAME</th>
								<th class="align-center" style="width: 100px;">BATCH NO.</th>
								<th class="align-center" style="width: 100px;">MANUFACTURER NAME</th>
								<th class="align-center" style="width: 100px;">RECEIVED BY AND DATE </th>
								<th class="align-center" style="width: 100px;">ANALYST BY</th>
								<th class="align-center" style="width: 100px;">RESULT</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">${info.productName}</td>
								<td class="align-center">${info.batchNo}</td>
								<td class="align-center">${info.manufacturerName}</td>
								<td class="align-center">${info.createdyByName}</td>
								<td class="align-center">${info.analystByName}</td>
								<td class="align-center">${info.resultComp}</td>
								<td class="align-left">
								 ${info.remarks}
								 </td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_manufacturer_id" value="${info.manufacturerId}">
									<input type="hidden" id="r_manufacturer_name" value="${info.manufacturerName}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_product_name" value="${info.productName}">
									<input type="hidden" id="r_result" value="${info.resultComp}">
									<input type="hidden" id="r_analyst_by" value="${info.analystBy}">
									<input type="hidden" id="r_analyst_by_name" value="${info.analystByName}">
								
									<c:choose>
									    <c:when test="${info.resultComp ==null}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									    </c:when>    
									    <c:otherwise>
								<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									  	&nbsp
								<a class="btn-edit btn btn-xs" onclick="resultEntry(this)"><i class="material-icons">near_me</i></a>
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
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Comparative Dissolution Study Logbook Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-049</td>
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
                 <form method="post" id="comparativeDissoResultForm">
                 		<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 			<div class="row">
								<div class="col-md-2 align-right top">
	                            	<label for="column_code_name">PRODUCT NAME. :</label>
	                            </div>
	                            <div class="col-md-4">
		                       <div class="form-group">
							  <input type="text" disabled="disabled" id="product_name" name="" class="form-control" style="width: 100%;" >
		                      <input type="hidden"  id="col_id" name="colId" class="form-control" style="width: 100%;" >
							   	</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="manufacturer_name">MANUFACTURER NAME :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
								    <input type="text" disabled="disabled" id="manufacturer_name" name="" value="" class="form-control" style="width: 100%;" >
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2 align-right top">
		                            	<label for="batch_no">BATCH NO.:</label>
		                            </div>
	                        	<div class="col-md-4">
		                            <div class="form-group">
								   <input type="text" disabled="disabled" id="batch_no" name="" value="" class="form-control" style="width: 100%;" autocomplete="off">
									
									</div>
									</div>
								<div class="col-md-2 align-right top">
                            	<label for="analyst_by_name">ANALYST NAME :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
                                 <input type="text" disabled="disabled" id="analyst_by_name" name="" value="" class="form-control" style="width: 100%;" autocomplete="off">
				               </div>
                            </div>
				     </div>
					 		<div class="row">
							<div class="col-md-2 align-right top">
                            	<label for="result_entry">ENTRY :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
	                                <input type="text" id="result_entry" name="resultEntry" value="" class="form-control"  autocomplete="off" required="required">
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

     <div class="modal fade" id="compDisModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book: Comparative Dissolution Study</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-CM-049</td>
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
                 <form method="post" id="comoareDissolutionDutyForm" onkeypress="if (event.keyCode == 13) { return false; }">
                  		<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id_edit" name="id" value=""/>
                 			<div class="row">
								<div class="col-md-2 align-right top">
	                            	<label for="column_code_name">PRODUCT NAME. :</label>
	                            </div>
	                            <div class="col-md-4">
		                       <div class="form-group">
							  <input type="text" disabled="disabled" id="product_name_edit" name="" class="form-control" style="width: 100%;" >
		                      <input type="hidden"  id="col_id" name="colId" class="form-control" style="width: 100%;" >
							   	</div>
								</div>
									<div class="col-md-2 align-right top">
		                            	<label for="manufacturer_name">MANUFACTURER NAME :</label>
		                            </div>
	                          	  <div class="col-md-4">
		                            <div class="form-group">
								    <input type="text" disabled="disabled" id="manufacturer_name_edit" name="" value="" class="form-control" style="width: 100%;" >
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2 align-right top">
		                            	<label for="batch_no">BATCH NO.:</label>
		                            </div>
	                        	<div class="col-md-4">
		                            <div class="form-group">
								   <input type="text" disabled="disabled" id="batch_no_edit" name="" value="" class="form-control" style="width: 100%;" autocomplete="off">
									
									</div>
									</div>
								<div class="col-md-2 align-right top">
                            	<label for="analyst_by_name">ANALYST NAME :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
                                 <input type="text" disabled="disabled" id="analyst_by_name_edit" name="" value="" class="form-control" style="width: 100%;" autocomplete="off">
				               </div>
                            </div>
				     </div>
					 		<div class="row">
							<div class="col-md-2 align-right top">
                            	<label for="result_entry">RESULT ENTRY :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
	                                <input type="text" id="result_entry_edit" name="resultEntry" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                          </div>
                    		<div class="row">
	                 		<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks_edit" name="remarks" class="form-control" maxlength="250" placeholder="Description goes here......." ></textarea>
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
                
                 <!--	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	        				<div class="col-md-2 align-right top">
                            	<label for="product_name">PRODUCT NAME :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
	                                <input type="text" id="product_name" name="productName" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
            				<div class="col-md-2 align-right top">
                            	<label for="batch_no">BATCH NO :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
	                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                  		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="manufacturer_id">MANUFACTURER :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<select id="manufacturer_id" name="manufacturerId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select LOCATION-</option>
				                        <c:forEach var="info" items="${manufactureList}">
				                           	<option value="${info.id }">${info.sourceName}</option>
				                        </c:forEach>
				                    </select>
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
	                 </div>-->
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

function resultEntry(el){
    var id 			= $(el).closest("tr").find("#row_id").val();
	var remarks = $(el).closest("tr").find("#r_remarks").val();
	var manufacturerName = $(el).closest("tr").find("#r_manufacturer_name").val();
	var batchNo 	= $(el).closest("tr").find("#r_batch_no").val();
	var productName 	= $(el).closest("tr").find("#r_product_name").val();
	var analystByName 	= $(el).closest("tr").find("#r_analyst_by_name").val();
	var result 	= $(el).closest("tr").find("#r_result").val();
	
	
	
	$("#id").val(id);
	$("#manufacturer_name").val(manufacturerName);
	$("#batch_no").val(batchNo);
	$("#product_name").val(productName);
	$("#remarks").val(remarks);
	$("#analyst_by_name").val(analystByName);
	$("#result_entry").val(result);
	
		
	$("#columnPerformancePendingModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};


$("#comparativeDissoResultForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#comparativeDissoResultForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveDistributeComparativeStudyResult?updateFlag=V",
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


function edit(el) {
	 var id 	= $(el).closest("tr").find("#row_id").val();
	var remarks = $(el).closest("tr").find("#r_remarks").val();
	var manufacturerName = $(el).closest("tr").find("#r_manufacturer_name").val();
	var batchNo 	= $(el).closest("tr").find("#r_batch_no").val();
	var productName 	= $(el).closest("tr").find("#r_product_name").val();
	var analystId 	= $(el).closest("tr").find("#r_analyst_by").val();
	var result 	= $(el).closest("tr").find("#r_result").val();
	var analystByName 	= $(el).closest("tr").find("#r_analyst_by_name").val();
	
	$("#id_edit").val(id);
	$("#manufacturer_name_edit").val(manufacturerName);
	$("#batch_no_edit").val(batchNo);
	$("#product_name_edit").val(productName);
	$("#remarks_edit").val(remarks);
	$("#analyst_id_edit").val(analystId).trigger('change.select2');
	$("#result_entry_edit").val(result);
	$("#analyst_by_name_edit").val(analystByName);
	
		
	$("#compDisModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#comoareDissolutionDutyForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#comoareDissolutionDutyForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveDistributeComparativeStudyResult?updateFlag=E",
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
