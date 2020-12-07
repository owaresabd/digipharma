<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<style>
/* .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
} */
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
	    <span style="text-shadow: 2px 2px 2px #aaa;">WORKING STANDARD REQUEST LIST</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/batch/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/batch/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<!-- <button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br> -->
					
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="batchList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;">SL#</th>
								<th class="align-left">MATERIAL NAME</th>
								<th class="align-left" >MATERIAL CODE</th>
								<th class="align-left" >MATERIAL TYPE</th>
								<th class="align-center" >ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.materialName}</td>
								<td class="align-left">
									${info.materialCode}
								</td>
								<td class="align-left">
								${info.materialTypeName}
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="row_material_id" value="${info.materialId}">
									<input type="hidden" id="row_material_code" value="${info.materialCode}">
									<input type="hidden" id="row_type_id" value="${info.materialTypeId}">
									<input type="hidden" id="row_amount" value="${info.keptAmount}">
									<input type="hidden" id="row_remarks" value="${info.remarks}">
									<a class="btn-edit btn btn-xs" onClick="add(this)"><i class="material-icons">forward</i></a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="whRequestInfoModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                <button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="modal-header bg-cyan">
				    <table class="table table-bordered" style="border: 2px solid #ddd;">
				    	<tbody style="border: 2px solid #ddd;">
				    	<tr>
				    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
				    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
				    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Warehouse Request Information</td>
				    	<td>Document No.</td>
				    	<td colspan="2">FM-WH-001</td>
				    	</tr>
				    	<tr style="border: 2px solid #ddd;">
				    	<td>Revision No.</td>
				    	<td style="text-align:center; width:35px">00</td>
				    	<td>Page 1 of 1</td>
				    	</tr>
				    	<tr style="border: 2px solid #ddd;">                 	
				    	<td>Effective Date</td>
				    	<td colspan="2">15 Jan 2020</td>
				    	</tr>             	
				    	</tbody>
				    </table>
				</div>
               <form method="post" id="whRequestInfoForm" modelAttribute="whRequestInfo">
                 	<div class="modal-body ">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<input type="hidden" id="wsRequestId" name="wsRequestId" value=""/>
                 		<input type="hidden" id="provider_type" name="providerType" value="WH"/>
                 		
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>REQUEST TYPE :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="reqType" name="reqType" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="reqTypeInfo" items="${reqTypeList}">
				                           	<option value="${reqTypeInfo.id }">${reqTypeInfo.typeName}</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>FROM DEPARTMENT :</b></span>
                            	<div class="form-group">
	                                <select id="fromDeptNo" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        <option value=""></option>
				                        <c:forEach var="deptInfo" items="${deptList}">
				                           	<option value="${deptInfo.id }">${deptInfo.deptName }</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="fromDeptNoHidden" name="fromDeptNo" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TO DEPARTMENT :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="toDeptNo" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        <option value=""></option>
				                        <c:forEach var="deptInfo" items="${deptList}">
				                           	<option value="${deptInfo.id }">${deptInfo.deptName }</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="toDeptNoHidden" name="toDeptNo" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b> INVOICE NUMBER :</b></span>
                            	<div class="form-group">
	                                 <input type="text" id="invoiceNo" name="invoiceNo" value="" class="form-control uppercase" placeholder="Invoice No" autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>CHALAN NO. :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="chalanNo" name="chalanNo" value="" class="form-control uppercase" placeholder="Chalan No" autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>L/C NO. :</b></span>
                            	<div class="form-group">
	                                 <input type="text" id="lcNo" name="lcNo" value="" class="form-control uppercase" placeholder="L/C No" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>NAME OF MATERIALS :</b></span>
                            	<div class="form-group">
                            		<select  id="materialId" name="" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        	<option></option>
				                        <c:forEach var="matInfo" items="${materialList}">
				                           	<option value="${matInfo.id }">${matInfo.materialName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="material_id" name="materialId" value=""/>
	                                
                            	</div>
                            </div>
                             <div class="col-md-4">
                            	<span><b>MATERIALS CODE NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="materialCode" name="materialCode" value="" class="form-control" placeholder="material Code" readonly="readonly">
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>MATERIAL TYPE :</b></span>
                            	<div class="form-group">
	                                <select id="materialTypeId" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        	<option></option>
				                        <c:forEach var="typeInfo" items="${typeList}">
				                           	<option value="${typeInfo.id }">${typeInfo.typeName}</option>
				                        </c:forEach>
				                	</select>
				                	<input type="hidden" id="materialType_id" name="materialTypeId" value=""/>
				                	<input type="hidden" id="keptAmount" name="keptAmount" value=""/>
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>MANUFACTURER / SOURCE :</b></span>
                            	<div class="form-group">
	                                <select id="manufacture_id" name="manufactureId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="info" items="${manufactureList}">
				                           	<option value="${info.id }">${info.sourceName}</option>
				                        </c:forEach>
				                	</select>
	                                <input type="hidden" id="manufactureName" name="manufactureName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>COUNTRY/ ORIGIN :</b></span>
                            	<div class="form-group">
	                                <select id="countryId" name="countryId" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="countryInfo" items="${countryList}">
				                           	<option value="${countryInfo.id }">${countryInfo.countryName}</option>
				                        </c:forEach>
				                 	</select>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>GR NO. :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="grNo" name="grNo" value="" class="form-control uppercase" placeholder="GR No"  autocomplete="off" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                        	<div class="col-md-12">
                           	<div class="form-group">
                            	<table id="reqTable" class="table table-bordered table-striped table-hover"> 
									<thead>
										<tr>
											<th class="align-center" style="width: 130px;">Batch No.</th>
											<th class="align-center" style="width: 120px;">Lot No.</th>
											<th class="align-center" style="width: 100px;">Mfg. Date</th>
											<th class="align-center" style="width: 100px;">Exp. Date</th>
											<th class="align-center" style="width: 70px;">Qty</th>
											<th class="align-center" style="width: 60px;">Unit</th>
											<th class="align-center" style="width: 130px;">No. of Drums</th>
											<th class="align-center" style="width: 80px;">####</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="align-center">
											<input type="hidden" id="child_id" value=""/>
												<input style="width: 100%;" type="text" id="batchNo"  />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="batchLot"  />
											</td>
											<td class="align-center">
											
												<input style="width: 100%;" type="text" id="mfgDate"  />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="expDate"  />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="qty" class="number" placeholder="qty"/>
											</td>
											<td class="align-center">
											<select id="unitId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 			</select>
												
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="noOfDrums"  />
											</td>
											<td class="align-center">
												<button type="button" id="addBtn" class="btn bg-blue btn-xs waves-effect" onclick="addRow(this)"> 
													<i class="material-icons">gamepad</i>
												</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
                           	</div>
                           	<div class="col-md-4 m-t--10">
                            	<span><b>RCV. QTY :</b></span>
                            	<div class="form-group input-group">
	                                <input type="text" id="rcvQty" name="rcvQty" value="0" class="form-control number" style="width: 20%" readonly="readonly" autocomplete="off" >
	                                <label id="rcvUnitLbl" class="m-t-5"></label>
	                                <input type="hidden" id="rcvUnitId" name="rcvUnitId" value="" >
                            	</div>
                            </div>
                		</div>
                		<div class="row">
	                 		<div class="col-md-12">
                            	<span><b>SAMPLE DETAILS :</b></span>
                            	<div class="form-group">
	                                <textarea rows="2" id="sampleDetails" name="sampleDetails" class="form-control" placeholder="Sample  description goes here......."></textarea>
                            	</div>
                            </div>
                		</div>
	                 </div>
	                 
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span> 
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
	var link =  "${pageContext.request.contextPath}/warehouse/requestWsInfos";
	$("#whRequestInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
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

$(document).ready(function() {
    $(window).keydown(function(event){
        if((event.keyCode == 13) && ($(event.target)[0]!=$("textarea")[0])) {
            event.preventDefault();
            return false;
        }
    });

});

$(function() {
    $('.uppercase').keyup(function() {
        this.value = this.value.toUpperCase();
    });
    
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$("#manufacture_id").on('change', function() {
	var id 	 = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#manufactureName").val(name);
});

$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
    $( "#mfgDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    $( "#expDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
});

$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select from list"
	 
});
var aTable = $('#reqTable').DataTable({
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

$('#reqTable_length, #reqTable_info, #reqTable_paginate, #reqTable_filter').hide();

function remove(el){
	var qty 		= $(el).closest("tr").find("#i_qty").val();
	var rcvQty		= $("#rcvQty").val();
	var totalQty	= parseFloat(rcvQty) - parseFloat(qty);
	$("#rcvQty").val(totalQty);
	
    var tbl = $(el).closest('table').DataTable();
    var tr 	= $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
}

function addRow(el){
	var rcvUnitId=$("#rcvUnitId").val();
	var id		 = $("#id").val();
	var chdId	 = $("#child_id").val();
	var mfgDate  = $("#mfgDate").val();
	var expDate  = $("#expDate").val();
	var batchLot = $("#batchLot").val();
	var batchNo  = $("#batchNo").val();
	var qty 	 = $("#qty").val();
	var unitId 	 = $("#unitId").val();
	var unitNm 	 = $('#unitId option:selected').text();
	var noOfDrums =$("#noOfDrums").val();
	var rcvQty	 = $("#rcvQty").val();
	var totalQty = parseFloat(rcvQty)+parseFloat(qty);
	
	if(chdId == ""){
		chdId="-1";
	} 
	var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_child_id"  name="childId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="i_batch_no"  name="batchNo[]" value="'+batchNo+'"/>'
			+ '<input type="hidden" id="i_batch_lot" name="batchLot[]" value="'+batchLot+'"/>'
			+ '<input type="hidden" id="i_mfg_date" name="mfgDate[]"  value="'+mfgDate+'"/>'
			+ '<input type="hidden" id="i_exp_date"  name="expDate[]" value="'+expDate+'"/>'
			+ '<input type="hidden" id="i_qty" name="quantity[]" value="'+qty+'"/>'
			+ '<input type="hidden" id="i_unitId" name="unitId[]" value="'+unitId+'"/>'
			+ '<input type="hidden" id="i_unitNm" value="'+unitNm+'"/>'
			+ '<input type="hidden" id="i_no_of_drums" name="noOfDrums[]" value="'+noOfDrums+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRow(this)"><i class="material-icons">mode_edit</i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		
		if( mfgDate != '' && expDate != '' && batchLot != '' && batchNo != '' && qty != '' && unitNm != '' && noOfDrums != ''){
		var appendRow=true;
		if(rcvUnitId.length == 0 ){
			$("#rcvUnitId").val(unitId);
			$("#rcvUnitLbl").text(unitNm);
			
		}else{
			if(rcvUnitId !=unitId){
				appendRow = false;
				sweetAlert("Warning!", "Please Select Same Unit", "warning", 1000, false);	
				
			}	
			
		}
		if(appendRow=true){
		var rowNode = aTable.row.add([batchNo, batchLot, mfgDate, expDate, qty, unitNm, noOfDrums, html]).draw( false ).node();
		aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		$(rowNode).find("td:eq(3)").css({"text-align": "center"});
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
		$(rowNode).find("td:eq(6)").css({"text-align": "center"});
		$(rowNode).find("td:eq(7)").css({"text-align": "center"});
		$("#child_id").val('');
		$("#mfgDate").val('');
		$("#expDate").val('');
		$("#batchLot").val('');
		$("#batchNo").val('');
		$("#qty").val('');
		$('#unitId').val("").trigger('change.select2');
		$("#noOfDrums").val('');
		$("#rcvQty").val(totalQty);
		}
		
		}else{
			sweetAlert("Failed!", "Please enter all row value!", "warning", 5000, false);
		}
}

function editRow(el) {
	var id 			= $(el).closest("tr").find("#i_child_id").val();
	var mfgDate 	= $(el).closest("tr").find("#i_mfg_date").val();
	var expDate 	= $(el).closest("tr").find("#i_exp_date").val();
	var batchLot 	= $(el).closest("tr").find("#i_batch_lot").val();
	var batchNo 	= $(el).closest("tr").find("#i_batch_no").val();
	var unitId 		= $(el).closest("tr").find("#i_unitId").val();
	var unitNm 		= $(el).closest("tr").find("#i_unitNm").val();
	var qty 		= $(el).closest("tr").find("#i_qty").val();
	var noOfDrums 	= $(el).closest("tr").find("#i_no_of_drums").val();
	var rcvQty	 = $("#rcvQty").val();
	var totalQty = parseFloat(rcvQty) - parseFloat(qty);
	
	$("#child_id").val(id);
	$("#mfgDate").val(convertMmDate(mfgDate));
	$("#expDate").val(convertMmDate(expDate));
	$("#batchLot").val(batchLot);
	$("#batchNo").val(batchNo);
	$('#unitId').val(unitId).trigger('change.select2'); 
	$("#qty").val(qty);
	$("#noOfDrums").val(noOfDrums);
	$("#rcvQty").val(totalQty);
	
	var tbl = $(el).closest('table').DataTable();
    var tr = $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
};

function add(el) {
	var reqId 	    = $(el).closest("tr").find("#row_id").val();
	var materialId	= $(el).closest("tr").find("#row_material_id").val();
	var materialCode = $(el).closest("tr").find("#row_material_code").val();
	var materialTypeId		= $(el).closest('tr').find("#row_type_id").val();
	var amount		= $(el).closest('tr').find("#row_amount").val();
	 $("#id").val("");
	 $("#wsRequestId").val(reqId);
	 $('#reqType').val('').trigger('change.select2'); 
	 $('#fromDeptNo').val('f32e4ea4-576d-4b99-9b57-8cec61bc64ad').trigger('change.select2');
	 $('#toDeptNo').val('6c836e05-8a89-4fd9-bb76-9b4ecfe43ecd').trigger('change.select2'); 
	 $("#fromDeptNoHidden").val("f32e4ea4-576d-4b99-9b57-8cec61bc64ad");
	 $("#toDeptNoHidden").val("6c836e05-8a89-4fd9-bb76-9b4ecfe43ecd");
	 
	 $("#invoiceNo").val("");
	 $("#chalanNo").val("");
	 $("#lcNo").val("");
	 $('#materialId').val(materialId).trigger('change.select2');
	 $("#material_id").val(materialId);
	 $("#materialCode").val(materialCode);
	 $('#materialTypeId').val(materialTypeId).trigger('change.select2');
	 $("#materialType_id").val(materialTypeId);
	 $("#keptAmount").val(amount);
	 $("#rcvQty").val("0");
	 $("#rcvUnitId").val("");
	 $("#rcvUnitLbl").text("");
	 $('#manufacture_id').val('').trigger('change.select2');
	 $("#manufactureName").val("");
	 $('#countryId').val('').trigger('change.select2');
	 $("#grNo").val("");
	 $("#sampleDetails").val("");
	 //aTable.rows().remove().draw();
	//$.fn.modal.Constructor.prototype.enforceFocus = function() {};
	$("#whRequestInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	var reqType = $(el).closest("tr").find("#s_reqType").val();
	var fromDeptNo = $(el).closest("tr").find("#s_fromDeptNo").val();
	var toDeptNo = $(el).closest("tr").find("#s_toDeptNo").val();
	var invoiceNo=$(el).closest("tr").find("#s_invoiceNo").val();
	var chalanNo=$(el).closest("tr").find("#s_chalanNo").val();
	var lcNo=$(el).closest("tr").find("#s_lcNo").val();
	var materialId=$(el).closest("tr").find("#s_materialId").val();
	var materialCode=$(el).closest("tr").find("#s_materialCode").val();
	var materialTypeId=$(el).closest("tr").find("#s_materialTypeId").val();
	var rcvQty=$(el).closest("tr").find("#s_rcvQty").val();
	var unitId=$(el).closest("tr").find("#s_unitId").val();
	var unitName=$(el).closest("tr").find("#s_unitName").val();
	var manufactureId=$(el).closest("tr").find("#s_manufacture_id").val();
	var manufactureName=$(el).closest("tr").find("#s_manufactureName").val();
	var countryId=$(el).closest("tr").find("#s_countryId").val();
	var grNo=$(el).closest("tr").find("#s_grNo").val();
	var sampleDetails=$(el).closest("tr").find("#s_sampleDetails").val();
	
	 $("#id").val(id);
	 $('#reqType').val(reqType).trigger('change.select2'); 
	 $('#fromDeptNo').val(fromDeptNo).trigger('change.select2');
	 $('#toDeptNo').val(toDeptNo).trigger('change.select2');
	 $("#fromDeptNoHidden").val(fromDeptNo);
	 $("#toDeptNoHidden").val(toDeptNo);
	 $("#invoiceNo").val(invoiceNo);
	 $("#chalanNo").val(chalanNo);
	 $("#lcNo").val(lcNo);
	 $('#materialId').val(materialId).trigger('change.select2');
	 $("#materialCode").val(materialCode);
	 $('#materialTypeId').val(materialTypeId).trigger('change.select2');
	 $("#materialType_id").val(materialTypeId);
	 $("#rcvQty").val(rcvQty);
	 $("#rcvUnitId").val(unitId);
	 $("#rcvUnitLbl").text(unitName);
	 $('#manufacture_id').val(manufactureId).trigger('change.select2');
	 $("#manufactureName").val(manufactureName);
	 $('#countryId').val(countryId).trigger('change.select2');
	 $("#grNo").val(grNo);
	 $("#sampleDetails").val(sampleDetails);
	
	//$.fn.modal.Constructor.prototype.enforceFocus = function() {};
	$("#whRequestInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
    
    whRequestDetailsById(id);
};
function whRequestDetailsById(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/warehouse/whRequestDetailsById?whRequestId=" + id,
		dataType : 'json',
		success : function(data) {
			
			$("#reqTable").find("tr:gt(1)").remove();
			var requestList = data.length;
			 if (requestList > 0) {
				 showRequestList(data);
			} 
		}
	});
}


function showRequestList(data) {
	for (var i = 0; i < data.length; i++) {
		var id = data[i].whRequestId || "";
		var chdId = data[i].childId || "";
		var mfgDate = data[i].mfgDate || "";
		var expDate = data[i].expDate || "";
		var batchLot = data[i].batchLot || "";
		var batchNo = data[i].batchNo || "";
		var qty =data[i].quantity || "";
		var unitId =data[i].unitId || "";
		var unitName =data[i].unitName || "";
		var noOfDrums = data[i].noOfDrums || "";
		
		var dates;
		if(mfgDate === undefined){
			dates = "";	
		}else{
			dates = convertMmDate(mfgDate);	
		}
		var expireDate;
		if(expDate === undefined){
			expireDate = "";	
		}else{
			expireDate = convertMmDate(expDate);	
		}
		var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_child_id"  name="childId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="i_batch_no"  name="batchNo[]" value="'+batchNo+'"/>'
			+ '<input type="hidden" id="i_batch_lot" name="batchLot[]" value="'+batchLot+'"/>'
			+ '<input type="hidden" id="i_mfg_date" name="mfgDate[]"  value="'+dates+'"/>'
			+ '<input type="hidden" id="i_exp_date"  name="expDate[]" value="'+expireDate+'"/>'
			+ '<input type="hidden" id="i_qty" name="quantity[]" value="'+qty+'"/>'
			+ '<input type="hidden" id="i_unitId" name="unitId[]" value="'+unitId+'"/>'
			+ '<input type="hidden" id="i_unitNm" value="'+unitName+'"/>'
			+ '<input type="hidden" id="i_no_of_drums" name="noOfDrums[]" value="'+noOfDrums+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRow(this)"><i class="material-icons">mode_edit</i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		
		var rowNode = aTable.row.add([batchNo, batchLot, dates, expireDate, qty, unitName, noOfDrums, html]).draw( false ).node();
		aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		$(rowNode).find("td:eq(3)").css({"text-align": "center"});
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
		$(rowNode).find("td:eq(6)").css({"text-align": "center"});
		$(rowNode).find("td:eq(7)").css({"text-align": "center"});
	}
}

$("#whRequestInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#whRequestInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/warehouse/save-wsRequest-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#whRequestInfoModal").modal('hide');
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
});


</script>