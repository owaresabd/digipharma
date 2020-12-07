<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<style>
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
}
.table > tbody > tr > td, .table > tfoot > tr > td {
    font-size: none !important;
}
.bg-cyan {
  background-color: #30acbb !important;
  color: #fff; 
  }
  
</style>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK :: STERILE PREPARED MEDIUM STOCK CONTROL</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/dispose/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/dispose/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="countryList">
						<thead>
							<tr>
							  <th class="align-center" style="width: 70px;">SL NO</th>
							  <th class="align-left">NAME OF MEDIUM</th>
							  <th class="align-left">Storage Quantity </th>
							  <th class="align-left">Lab. Batch No.</th>
							  <th class="align-center" style="width: 80px;">Exp. Date </th>
							  <th class="align-center">Pack Size</th>
							  <th class="align-center">Storage By</th>
							  <th class="align-center">Issue Date</th>
							  <th class="align-center">Purpose</th>
							  <th class="align-center">Issued By</th>
							  <th class="align-center">Stock Quantity</th>
							  <th class="align-center">Checked By</th>
							  <th class="align-center">Remarks</th>
							  <th class="align-center" style="width: 110px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.mediumName}</td>
								<td class="align-left">
								${info.storageQty}
									
								</td>
								<td class="align-left">${info.labBatcNo}</td>
								 <td class="align-left">
									<fmt:formatDate pattern="dd-MM-yyyy" value="${info.expDate}" var="expDate"/>
	                            	<c:out value="${expDate}"/>							
								
								</td> 
								<td class="align-left">${info.packSize}</td>
								<td class="align-left">${info.storageByName}</td>
								<td class="align-left">${info.issueDate}</td>
								<td class="align-left">${info.purpose}</td>
								<td class="align-left">${info.issuedByName}</td>
								<td class="align-left">${info.stockQty}</td>
								<td class="align-left">${info.checkedByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_medium_name" value="${info.mediumName}">
									<input type="hidden" id="r_refrigerator_id" value="${info.refrigeratorId}">
									<input type="hidden" id="r_storage_type" value="${info.storageType}">
									<input type="hidden" id="r_storage_condition" value="${info.storageCondition}">
									<input type="hidden" id="r_storage_qty" value="${info.storageQty}">
									<input type="hidden" id="r_lab_batch_no" value="${info.labBatcNo}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									<input type="hidden" id="r_pack_size" value="${info.packSize}">
									<input type="hidden" id="r_storage_by" value="${info.storageBy}">
									<input type="hidden" id="r_issue_date" value="${info.issueDate}">
									<input type="hidden" id="r_issue_qty" value="${info.issuedQty}">
									<input type="hidden" id="r_purpose" value="${info.purpose}">
									<input type="hidden" id="r_issued_by" value="${info.issuedBy}">
									<input type="hidden" id="r_stock_qty" value="${info.stockQty}">
									<input type="hidden" id="r_checked_by" value="${info.checkedBy}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">				
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>&nbsp;
									
									
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" style="overflow: auto;" id="sterilePreparedMediumInfoModal"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             	<button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             	<div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: LOG BOOK ::  STERILE PREPARED MEDIUM STOCK CONTROL</td>
                 	<td>Document No.</td>
                 	<td colspan="2">LB-DIL-MC-020</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 Oct 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                </div>
                 <form method="post" id="sterilePreparedForm" onkeypress="if (event.keyCode == 13) { return false; }">  
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 	<div class="row">
                            <div class="col-md-6">
                            	<span><b>Name of Medium :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="nameOfMedium" name="nameOfMedium" value=""  class="form-control"  required="required" autocomplete="off">
                            	</div>
                            </div>                            
                            <div class="col-md-6">
                            	<span><b>Types of Storage Container :</b></span>
                            	<div class="form-group">
                            	 <select  id="typesOfStorage" name="typesOfStorage" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                           	<option value="P">Petridish</option>
			                        	<option value="V">Vial</option>
			                        	<option value="T">Test Tube</option>
			                       </select>
	                               
				                   
                            	</div>
                            </div>
                 		</div>	
                 		
                 		<div class="row">
                 		
                            <div class="col-md-4">
                            	<span><b>Refrigerator ID :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="refrigeratorId" name="refrigeratorId" value=""  class="form-control"  required="required" autocomplete="off">
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>Storage Condition (°C) :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="storageCondition" name="storageCondition" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Storage Date  :</b></span>
                            	<div class="form-group">
	                               <input type="text"  id="storageDate" name="storageDate" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                 		</div>
                 		
                 		
				<div class="row">                            
                            <div class="col-md-4">
                            	<span><b>Storage Quantity :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="storageQuantity" name="storageQuantity" value=""  class="form-control"  required="required" autocomplete="off">
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>Lab. Batch No.  :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="labBatchNo" name="labBatchNo" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>Exp. Date :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="expDate" name="expDate" value=""  class="form-control"  required="required" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">                            
                             <div class="col-md-4">
                            	<span><b>Pack Size  :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="packSize" name="packSize" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Storage By  :</b></span>
                            	<div class="form-group">
	                                <select  id="storageBy" name="storageBy" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Issue Date  :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="issueDate" name="issueDate" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">                            
                            <div class="col-md-4">
                            	<span><b>Issued Quantity :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="issuedQty" name="issuedQty" value=""  class="form-control"  required="required" autocomplete="off">
                            	</div>
                            </div>
                            
                                 <div class="col-md-4">
                            	<span><b>Purpose  :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="purpose" name="purpose" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Issued By :</b></span>
                            	<div class="form-group">
	                                <select  id="issuedBy" name="issuedBy" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                            	</div>
                            </div>
                            
                 		</div>		
						<div class="row">
                            <div class="col-md-4">
                            	<span><b>Stock Quantity  :</b></span>
                            	<div class="form-group">
	                               <input type="text" id="stockQty" name="stockQty" value=""  class="form-control"  required="required" autocomplete="off">
				                   
                            	</div>
                            </div>
                                 <div class="col-md-4">
                            	<span><b>Checked By  :</b></span>
                            	<div class="form-group">
	                               <select  id="checkedBy" name="checkedBy" class="js-example-theme-single form-control" style="width: 100%;">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.id }">${info.empName}</option>
			                        </c:forEach>
			                       </select>				                   
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Remarks :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="remarks" name="remarks" value=""  class="form-control"  required="required" autocomplete="off">
                            	</div>
                            </div>
                            
                 		</div>	
                 		
                 		
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
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
     <div class="modal fade" id="disposeViewModal"   role="dialog" data-backdrop="static" data-keyboard="false" >
         <div id="modalId" class="modal-dialog modal-lg" role="document" aria-hidden="true">
             <div class="modal-content">
                 
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

input {
    height: 28px !important;
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
function pageReload(link){
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
	var link =  "${pageContext.request.contextPath}/logbook_micro/sterilePreparedMedium";
	$("#sterilePreparedMediumInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
});


$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$( "#storageDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#expDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	$( "#issueDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    $('.uperrcase').keyup(function() {
        this.value = this.value.toUpperCase();
    });
});


$('#sample_status').change(function() {
	if (this.checked) {
		$('#isSample').val('Y');
	}else{
		$('#isSample').val('N');
		
	}
});
$('#product_status').change(function() {
	if (this.checked) {
		$('#isProduct').val('Y');
	}else{
		$('#isProduct').val('N');
		
	}
});


$('.number').keypress(function (event) {
    return isNumber(event, this)
});

var aTable = $('#sampleTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
	 "oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#sampleTable_length, #sampleTable_info, #sampleTable_paginate, #sampleTable_filter').hide();

var bTable = $('#productTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
	 "oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#productTable_length, #productTable_info, #productTable_paginate, #productTable_filter').hide();



function getSampleDetailsInfo(){
	var sampleId=$("#sampleId").val()
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/dispose/getSampleDetailsInfo?sampleId=" +sampleId,
		dataType : 'json',
		success : function(data) {
			
				var matId = data.MaterialId;
				var unitId = data.unitId;
				$('#materialId').val(matId);
				$('#material_list').val(matId).trigger('change.select2');
				$('#sampleUnitId').val(unitId).trigger('change.select2');
			
		}
	});
}


function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

		$("#productCode").val(data.materialCode);
		$('#productUnitId').val(data.unitId).trigger('change.select2');
		
	});
}
function addRowSample(el){
	var isSample=$('#sample_status').prop('checked');
	
	if(isSample==true){
	var sampleId=$("#sampleId").val();
	var sampleName = $('#sampleId option:selected').text();
	var materialId=$("#materialId").val();
	var materialName = $('#material_list option:selected').text();
	var quantity = $("#quantity").val();
	var sampleUnitId = $("#sampleUnitId").val();
	var sampleUnitName =$('#sampleUnitId option:selected').text();
	
	var html = '' 
			+ '<input type="hidden" id="i_sample_id"  name="sampleId[]" value="'+sampleId+'"/>'
			+ '<input type="hidden" id="i_material_id" name="materialId[]"  value="'+materialId+'"/>'
			+ '<input type="hidden" id="i_quantity" name="quantity[]" value="'+quantity+'"/>'
			+ '<input type="hidden" id="i_unit_id" name="sampleUnitId[]" value="'+sampleUnitId+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRowSample(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			
			
			if (apendRow == true) {
	
			var rowNode = aTable.row.add([sampleName, materialName,quantity,sampleUnitName, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(1)").css({"class": "isExist"});

		$('#sampleId').val('').trigger('change.select2');
		$('#material_list').val("").trigger('change.select2');
		$('#materialId').val("");
		$("#quantity").val('');
		$('#sampleUnitId').val("").trigger('change.select2');
		$(".md").prop("disabled", false); 
				
		} 
		
		}else{
			sweetAlert("Warning!", "Please Select Sample Checkbox.", "warning", 1000, false);	
			
		}
}

function editRowSample(el) {
	var sampleId	= $(el).closest("tr").find("#i_sample_id").val();
	var materialId	= $(el).closest("tr").find("#i_material_id").val();
	var quantity 	= $(el).closest("tr").find("#i_quantity").val();
	var unitId 		= $(el).closest("tr").find("#i_unit_id").val();
	

	$('#sampleId').val(sampleId).trigger('change.select2');
	$('#material_list').val(materialId).trigger('change.select2');
	$('#materialId').val(materialId);
	$("#quantity").val(quantity);
	$("#sampleUnitId").val(unitId).trigger('change.select2');
	
	var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
  
}


function addRowProduct(el){
	var isProduct=$('#product_status').prop('checked');
	
	if(isProduct==true){
	var productId   =$("#productId").val();
	var productName = $('#productId option:selected').text();
	var productCode =$("#productCode").val();
    var productQty 	= $("#productQty").val();
	var unitId 		= $("#productUnitId").val();
	var untiName 	= $('#productUnitId option:selected').text();
	
	var html = '' 
			+ '<input type="hidden" id="r_product_id"  name="productId[]" value="'+productId+'"/>'
			+ '<input type="hidden" id="r_product_code" name="productCode[]"  value="'+productCode+'"/>'
			+ '<input type="hidden" id="r_quantity"  name="proQuantity[]" value="'+productQty+'"/>'
			+ '<input type="hidden" id="r_unit_id" name="productUnitId[]" value="'+unitId+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRowProduct(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			
			if (apendRow == true) {
			
			var rowNode = bTable.row.add([productName, productCode,productQty,untiName, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			bTable.draw();
			bTable.page('last').draw(false);
			$('#productId').val('');
			$('#productCode').val('');
			$("#productQty").val('');
			$('#productUnitId').val("").trigger('change.select2');
			$(".md").prop("disabled", false); 
		
			} 
			
	}else{
		sweetAlert("Warning!", "Please Select Product Checkbox.", "warning", 1000, false);
		
	}
}

function editRowProduct(el) {
	var productId				= $(el).closest("tr").find("#r_product_id").val();
	var productCode 	= $(el).closest("tr").find("#r_product_code").val();
	var quantity 	= $(el).closest("tr").find("#r_quantity").val();
	var unitId 	= $(el).closest("tr").find("#r_unit_id").val();
	
	$('#productId').val(productId).trigger('change.select2');
	$("#productCode").val(productCode);
	$("#productQty").val(quantity);
	$('#productUnitId').val(unitId).trigger('change.select2');
	
	var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
   
}

function add(el) {
	$("#id").val("");
	
	/* $('#sample_status').prop('checked', false);
	$("#isSample").val("N");
	$('#sampleId').val("");
	$('#material_list').val("").trigger('change.select2');
	$('#materialId').val("");
	$("#quantity").val("");
	$('#sampleUnitId').val("").trigger('change.select2');
	
	$('#product_status').prop('checked', false);
	$("#isProduct").val("N");
	$('#productId').val("");
	$("#productCode").val("");
	$("#productQty").val("");
	$('#productUnitId').val("").trigger('change.select2');
	$('#employeeId').val("").trigger('change.select2'); */
	$('#storageDate').datepicker('setDate', '');
	$('#expDate').datepicker('setDate', '');
	$('#issueDate').datepicker('setDate', '');
	$("#remarks").val("");
	
	
	
	
    $("#sterilePreparedMediumInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var mediumName   = $(el).closest("tr").find("#r_medium_name").val();
	var storageQty	 = $(el).closest("tr").find("#r_storage_qty").val();
	var refrigeratorId= $(el).closest("tr").find("#r_refrigerator_id").val();
	var storageType   = $(el).closest("tr").find("#r_storage_type").val();
	var storageCondition = $(el).closest("tr").find("#r_storage_condition").val();	
	var storageDate	 = $(el).closest("tr").find("#r_storage_date").val();
	var labBatchNo   = $(el).closest("tr").find("#r_lab_batch_no").val();
	var expDate	     = $(el).closest("tr").find("#r_exp_date").val();
	var packSize	 = $(el).closest("tr").find("#r_pack_size").val();
	var storageBy    = $(el).closest("tr").find("#r_storage_by").val();
	var issueDate	 = $(el).closest("tr").find("#r_issue_date").val();
	var issueQty	 = $(el).closest("tr").find("#r_issue_qty").val();	
	var purpose 	 = $(el).closest("tr").find("#r_purpose").val();
	var issuedBy     = $(el).closest("tr").find("#r_issued_by").val();
	var stockQty	 = $(el).closest("tr").find("#r_stock_qty").val();
	var checkBy 	 = $(el).closest("tr").find("#r_checked_by").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
		
	$("#id").val(Id);
	$("#nameOfMedium").val(mediumName);
	$("#refrigeratorId").val(refrigeratorId);
	$("#typesOfStorage").val(storageType).trigger('change.select2');
	$("#storageCondition").val(storageCondition);
	$("#storageBy").val(storageBy).trigger('change.select2');
	$("#issuedBy").val(issuedBy).trigger('change.select2');
	$("#checkedBy").val(checkBy).trigger('change.select2');
	$("#storageQuantity").val(storageQty);
	if (storageDate == "") {
		$('#storageDate').datepicker('setDate', new Date());
	} else {
		$('#storageDate').datepicker('setDate', convertMmDate(storageDate));
	}
	if (expDate == "") {
		$('#expDate').datepicker('setDate', '');
	} else {
		$('#expDate').datepicker('setDate', convertMmDate(expDate));
	}
	if (issueDate == "") {
		$('#issueDate').datepicker('setDate', '');
	} else {
		$('#issueDate').datepicker('setDate', convertMmDate(issueDate));
	}
	$("#labBatchNo").val(labBatchNo);
	$("#packSize").val(packSize);	
	$("#purpose").val(purpose);
	$("#issuedQty").val(issueQty);
	$("#stockQty").val(stockQty);
	$("#remarks").val(remarks);
		
	$("#sterilePreparedMediumInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
    
}
function getSampleDisposeInfo(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/dispose/getDisposeSampleInfos?id=" + id,
		dataType : 'json',
		success : function(data) {
			
			
			 if ( data.length > 0) {
				 sampleDisposeList(data);
			} 
		}
	});
}
function sampleDisposeList(data) {
	
	for (var i = 0; i < data.length; i++) {
		var sampleId=data[i].sampleId || "";
		var udSampleNo = data[i].udSampleNo || "";
		var materialId=data[i].materialId || "";
		var materialName =data[i].materialName || "";
		var quantity = data[i].quantity || "";
		var sampleUnitId = data[i].sampleUnitId || "";
		var sampleUnitName =data[i].unitName || "";
		
		var html = '' 
				+ '<input type="hidden" id="i_sample_id"  name="sampleId[]" value="'+sampleId+'"/>'
				+ '<input type="hidden" id="i_material_id" name="materialId[]"  value="'+materialId+'"/>'
				+ '<input type="hidden" id="i_quantity" name="quantity[]" value="'+quantity+'"/>'
				+ '<input type="hidden" id="i_unit_id" name="sampleUnitId[]" value="'+sampleUnitId+'"/>'
				+ '<a class="btn-edit btn btn-xs" onclick="editRowSample(this)"><i class="material-icons">mode_edit</i></a>';
			var apendRow = true;
			
			if (apendRow == true) {
	
			var rowNode = aTable.row.add([udSampleNo, materialName,quantity,sampleUnitName, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			
			aTable.draw();
		
		    aTable.page('last').draw(false);
		
		
	}
}
}

function getProductDisposeInfo(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/dispose/getDisposeProductInfos?id=" + id,
		dataType : 'json',
		success : function(data) {
			if (data.length > 0) {
				productDisposeList(data);
			} 
		}
	});
}
function productDisposeList(data) {
	
	for (var i = 0; i < data.length; i++) {
		
		var productId   =data[i].productId || "";
		var productName = data[i].productName || "";
		var productCode =data[i].productCode || "";
	    var productQty 	= data[i].quantity || "";
		var unitId 		= data[i].productUnitId || "";
		var untiName 	= data[i].unitName || "";
		
		var html = '' 
				+ '<input type="hidden" id="r_product_id"  name="productId[]" value="'+productId+'"/>'
				+ '<input type="hidden" id="r_product_code" name="productCode[]"  value="'+productCode+'"/>'
				+ '<input type="hidden" id="r_quantity"  name="proQuantity[]" value="'+productQty+'"/>'
				+ '<input type="hidden" id="r_unit_id" name="productUnitId[]" value="'+unitId+'"/>'
				+ '<a class="btn-edit btn btn-xs" onclick="editRowProduct(this)"><i class="material-icons">mode_edit</i></a>';
				var apendRow = true;
				
				if (apendRow == true) {
				
				var rowNode = bTable.row.add([productName, productCode,productQty,untiName, html]).draw( false ).node();
			$( rowNode ).addClass('isExist');
			bTable.draw();
			bTable.page('last').draw(false);
			
		
	}
	}
}

$("#sterilePreparedForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#sterilePreparedForm").serialize();
    swal({
        title: "Are you sure?",
        text: "Yes, submit this form!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, submit this form!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
    		url : "${pageContext.request.contextPath}/logbook_micro/saveSterilePreparedMediumInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#disposeInfoModal").modal('hide');
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
        	sweetAlert("Cancelled", "", "error", 0, false);
        }
        });
});

function view(el) {
	var id = $(el).closest("tr").find("#m_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/dispose/getDisposeDetailsInfo/" + id ,
		success : function(data) {
			$('#disposeViewModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			}
		});
}

function disposeInfoPrint(id,udDisposeNo) {
	var ajaxURL = "${pageContext.request.contextPath}/dispose/dispose-report?disposeId="+id+'&udDisposeNo='+udDisposeNo;
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