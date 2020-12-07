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
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK: MEDIA,REAGENT AND OTHER MATERIALS RECEVING AND STOCK CONTROL</span>
		
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
								<th class="align-center" style="width: 110px;">DATE OF RECIVING</th>
								<th class="align-center" style="width: 100px;"> CODE NO.</th>
								<th class="align-center" style="width: 110px;">NAME OF MATERIAL</th>
								<th class="align-center" style="width: 100px;">BATCH/LOT NO.</th>
								<th class="align-center" style="width: 100px;">QUANTITY</th>
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
								<td class="align-center">${info.codeNo}</td>
								<td class="align-center">${info.materialsName}</td>
								<td class="align-center">${info.batchNo}</td>
								<td class="align-center">${info.quantity}&nbsp;${info.unitName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
									    <c:when test="${info.checkedStatus =='Y'}">
											<span class="badge bg-green">CHECKED</span>
									    </c:when>  
										<c:when test="${info.finishedStatus =='Y'}">
											<span class="badge bg-green">FINISHED</span>
									    </c:when>  
										<c:when test="${info.issuedStatus =='Y'}">
											<span class="badge bg-green">ISSUED</span>
									    </c:when>  
										<c:when test="${info.openingStatus =='Y'}">
											<span class="badge bg-green">OPENING</span>
									    </c:when> 
									<c:otherwise>
										<input type="hidden" id="row_id" value="${info.id}">
										<input type="hidden" id="r_code_no" value="${info.codeNo}">
										<input type="hidden" id="r_materials_id" value="${info.materialsId}">
										<input type="hidden" id="r_batch_no" value="${info.batchNo}">
										<input type="hidden" id="r_quantity" value="${info.quantity}">										
										<input type="hidden" id="r_country_origin_id" value="${info.countryOriginId}">
										<input type="hidden" id="r_supplier_id" value="${info.supplierId}">
										<input type="hidden" id="r_unit_id" value="${info.unitId}">
										<input type="hidden" id="r_exp_date" value="${info.expDate}">
										<input type="hidden" id="r_mfg_date" value="${info.mfgDate}">
										<input type="hidden" id="r_specification" value="${info.specification}">									
										<input type="hidden" id="r_remarks" value="${info.remarks}">	
																			
										<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
										&nbsp
								        <a class="btn-edit btn btn-xs" onclick="issueRecord(this)"><i class="material-icons">near_me</i></a>
								
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
<div class="modal fade" id="mediaReagentMaterialsInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book: Media,Reagent and Other Materials Receving and Stock Control </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-MC-004</td>
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
                 <form method="post" id="mediaReagentInfoForm" onkeypress="if (event.keyCode == 13) { return false; }">
                 
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>                 		
                 			<div class="row">
		                    <div class="col-md-3 align-right top">
                            	<label for="code_no">CODE NO. :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="code_no" name="codeNo" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
                     	
						    <div class="col-md-3 align-right top">
                            	<label for="materials_id">NAME OF MATERIAL :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
					         <select  id="materials_id" name="materialsId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option >-Select MATERIAL-</option>
			                        <c:forEach var="info" items="${materialList}">
			                           	<option value="${info.id }">${info.cultureItemName}</option>
			                        </c:forEach>
			                    </select>
                                </div>
                            </div>
					
					 	</div>
					
						
						<div class="row">
	       		    		<div class="col-md-3 align-right top">
                            	<label for="specification">SPECIFICATION :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="specification" name="specification" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
             				<div class="col-md-3 align-right top">
                            	<label for="batch_no">BATCH/LOT NO :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
							</div>
							<div class="row">
		            		<div class="col-md-3 align-right top">
                            	<label for="mfg_date">MFG. Date :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
							<input type="text" id="mfg_date" name="mfgDate" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
                           
	                        	</div>
                            </div>
                            <div class="col-md-3 align-right top">
                            	<label for="exp_date">EXP. DATE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
							<input type="text" id="exp_date" name="expDate" value="" class="form-control" style="width: 50%;" autocomplete="off" required="required">
	                        	</div>
                            </div>
                     
			          	</div>
                 		<div class="row">
	                 	    <div class="col-md-3 align-right top">
                            	<label for="supplier_id">MANUFACTURER/SUPPLIER NAME :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
							    	<select id="supplier_id" name="supplierId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select MANUFACTURER-</option>
				                        <c:forEach var="info" items="${manufactureList}">
				                           	<option value="${info.id }">${info.sourceName}</option>
				                        </c:forEach>
				                    </select>      </div>
                            </div>
				            <div class="col-md-3 align-right top">
                            	<label for="country_origin_id"> COUNTRY ORIGIN :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
						 <select id="country_origin_id" name="countryOriginId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                  	<option>-Select COUNTRY ORIGIN-</option>
				                        <c:forEach var="countryInfo" items="${countryList}">
				                       	<option value="${countryInfo.id }">${countryInfo.countryName}</option>
				                    </c:forEach>
				         </select>
                            </div>
                            </div>
						      </div>
                 				<div class="row">
	       		    
							<div class="col-md-3 align-right top">
                            	<label for="quantity">QUANTITY :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
	                                <input type="text" id="quantity" name="quantity" value="" class="form-control"  autocomplete="off" required="required">
                            	</div>
                            </div>
				 			<div class="col-md-3 align-right top">
                            	<label for="unit_id">UNIT :</label>
                            </div>
                            <div class="col-md-2">
                            <div class="form-group">
	                                <select id="unit_id" name="unitId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>-Select UNIT-</option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id}">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
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
	
	$( "#mfg_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
		$( "#exp_date" ).datepicker({
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
	$("#country_origin_id").val("").trigger('change.select2');
	$("#materials_id").val("").trigger('change.select2');
	$("#unit_id").val("").trigger('change.select2');
	$("#supplier_id").val("").trigger('change.select2');	
	$("#code_no").val("");
	$("#batch_no").val("");
	$("#quantity").val("");
	$("#analyst_id").val("");
	$("#exp_date").val("");
	$("#area_of_competency").val("");
	$("#mfg_date").val("");
	$("#specification").val("");
	$("#remarks").val("");					
	
    $("#mediaReagentMaterialsInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var id 			= $(el).closest("tr").find("#row_id").val();
	var codeNo = $(el).closest("tr").find("#r_code_no").val();
	var batchNo 	= $(el).closest("tr").find("#r_batch_no").val();
	var quantity 	= $(el).closest("tr").find("#r_quantity").val();
	var countryOriginId  = $(el).closest("tr").find("#r_country_origin_id").val();
	var supplierId  = $(el).closest("tr").find("#r_supplier_id").val();
	
	
	var materialsId  = $(el).closest("tr").find("#r_materials_id").val();	
	var unitId 	= $(el).closest("tr").find("#r_unit_id").val();
	var expDate 	= $(el).closest("tr").find("#r_exp_date").val();
	var mfgDate 	= $(el).closest("tr").find("#r_mfg_date").val();
	var specification 	= $(el).closest("tr").find("#r_specification").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(id);
	$("#code_no").val(codeNo);
	$("#batch_no").val(batchNo);
	$("#quantity").val(quantity);
	$("#country_origin_id").val(countryOriginId).trigger('change.select2');
	$("#materials_id").val(materialsId).trigger('change.select2');
	$("#unit_id").val(unitId).trigger('change.select2');
	$("#supplier_id").val(supplierId).trigger('change.select2');
//	$("#exp_date").val(expDate);
//	$("#mfg_date").val(mfgDate);
    $('#exp_date').datepicker('setDate', convertMmDate(expDate));
	$('#mfg_date').datepicker('setDate', convertMmDate(mfgDate));
	
	
	$("#specification").val(specification);
	$("#remarks").val(remarks);
		
	$("#mediaReagentMaterialsInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#mediaReagentInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#mediaReagentInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_micro/saveMediaReagentMaterials",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#mediaReagentMaterialsInfoModal").modal('hide');
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


function issueRecord(el) {
var id  = $(el).closest("tr").find("#row_id").val();
	  var ajaxURL = "${pageContext.request.contextPath}/logbook_micro/issueMediaReagentRecord?id=" + id;
	 $.ajax({
		type : "GET",
		url : ajaxURL,
		dataType : 'json',
		success : function(data) {
		$("#view_page").html("");
		sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
		}
	});
	
}

</script>
