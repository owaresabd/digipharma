<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">PRODUCTION REQUEST INFO</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;"> SL #</th>
							    <th class="align-left"> REQUEST NO.</th>
								<th class="align-left">NAME OF MATERIALS </th>
								<th class="align-left">REQUEST TYPE </th>
								<th class="align-center">STATUS</th>
								<th class="align-center">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${productionList}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_type_name">${info.udWhReqNo}</td>
								<td class="field_type_name">${info.materialName}</td>
								<td class="field_type_status align-left" >${info.reqTypeName}</td>
								
								<td class="field_type_status align-center">
									<c:choose>
									    <c:when test="${info.status =='P'}">
											<span class="badge bg-orange">Pending</span>
									    </c:when>
									    <c:when test="${info.status =='C'}">
											<span class="badge bg-green">Request Sent</span>
									    </c:when>     
									    
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="s_reqType" value="${info.reqType}">
									<input type="hidden" id="s_fromDeptNo" value="${info.fromDeptNo}">
									<input type="hidden" id="s_toDeptNo" value="${info.toDeptNo}">
									<input type="hidden" id="s_materialId" value="${info.materialId}">
									<input type="hidden" id="s_materialName" value="${info.materialName}">
									<input type="hidden" id="s_materialCode" value="${info.materialCode}">
									<input type="hidden" id="s_materialTypeId" value="${info.materialTypeId}">
									<input type="hidden" id="s_sampleDetails" value="${info.sampleDetails}">   
									<c:if test="${info.status ne 'C'}">
									 <a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									 </c:if>
									
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
									<a class="btn-edit btn btn-xs" onclick="productionInfoPrint('${info.id}','${info.udWhReqNo}')"><i class="material-icons">print</i></a>
									<c:if test="${info.status ne 'C'}">
									 <a class="btn-edit btn btn-xs" onclick="sendCss(this)"><i class="material-icons">input</i></a>
									 </c:if>
									
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
<div class="modal fade" id="productionInfoModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Production Request Info</td>
                 	<td>Document No.</td>
				    <td colspan="2">FM-PR-003</td>
                 	</tr>
                 	<!-- <tr style="border: 2px solid #ddd;">                 	
                 	<td>Document No.</td>
                 	<td>FM-PR-003</td>
                 	</tr> -->  
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

                <form method="post" id="productionInfoForm" modelAttribute="productionInfo">
                 	<div class="modal-body ">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<input type="hidden" id="provider_type" name="providerType" value="PD"/>
                 		<!-- <div class="row">
	                 		<div class="col-md-4">
                            	<span><b>FORM NO. :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="formNo" readonly="readonly" name="formNo" value="" class="form-control uppercase" placeholder="Form No"  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>REVISION NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="revisionNo" readonly="readonly" name="revisionNo" value=""  class="form-control" placeholder="Revision No"  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>EFFECTIVE DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="effectivDate" readonly="readonly" name="effectivDate" value="" style="width:40%" class="form-control"   autocomplete="off">
                            	</div>
                            </div>
                 		</div> -->
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
                            	<span><b>NAME OF PRODUCT :</b></span>
                            	<div class="form-group">
                            		<select onchange="materialInfoById(this.id)" id="materialId" name="materialId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="matInfo" items="${materialList}">
				                           	<option value="${matInfo.id}">${matInfo.materialName}</option>
				                        </c:forEach>
				                    </select>
	                                
                            	</div>
                            </div>
                             <div class="col-md-4">
                            	<span><b>PRODUCTS CODE NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="materialCode" name="materialCode" value="" class="form-control" placeholder="material Code" readonly="readonly">
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>PRODUCT TYPE :</b></span>
                            	<div class="form-group">
	                                <select id="materialTypeId" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        	<option></option>
				                        <c:forEach var="typeInfo" items="${typeList}">
				                           	<option value="${typeInfo.id }">${typeInfo.typeName}</option>
				                        </c:forEach>
				                	</select>
				                	<input type="hidden" id="materialType_id" name="materialTypeId" value=""/>
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
											<th class="align-center" style="width: 70px;">Batch Size</th>
											<th class="align-center" style="width: 60px;">Unit</th>
											<th class="align-center" style="width: 70px;">Pack Size</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="align-center">
											<input type="hidden" id="child_id" name="childId[]" value=""/>
												<input style="width: 100%;" type="text" id="batchNo" name="batchNo[]" class="align-center" readonly="readonly"/>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="batchLot" name="batchLot[]" class="align-center" readonly="readonly"/>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="mfgDate" name="mfgDate[]" class="align-center" readonly="readonly"/>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="expDate" name="expDate[]" class="align-center" readonly="readonly"/>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="qty" name="quantity[]" class="align-center" placeholder="qty" readonly="readonly"/>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="unit_name" name="" class="align-center" readonly="readonly"/>
												<input style="width: 100%;" type="hidden" id="unitId" name="unitId[]"/>
											<%-- <select id="unitId" name="unitId[]" class="js-example-theme-single form-control" style="width: 100%;">
				                        		<option></option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 			</select> --%>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="packSize" name="packSize[]" class="align-center" placeholder="Pack Size" readonly="readonly"/>
											</td>
										</tr>
									</tbody>
								</table>
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
     
     <div class="modal fade" id="productionViewInfoModal" style="overflow: auto;"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
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
	var link =  "${pageContext.request.contextPath}/production/maintain";
	$("#productionInfoModal").modal('hide');
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

$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
    /* $( "#mfgDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
        //$(this).blur();
    });
    $( "#expDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    }); */
    
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
	 $("#id").val("");
	 $('#reqType').val('').trigger('change.select2'); 
	 //$('#fromDeptNo').val('').trigger('change.select2');
	 //$('#toDeptNo').val('').trigger('change.select2');
	 $('#fromDeptNo').val('3cdde084-aef1-4062-a700-48aca2552667').trigger('change.select2');
	 $('#toDeptNo').val('6c836e05-8a89-4fd9-bb76-9b4ecfe43ecd').trigger('change.select2'); 
	 $("#fromDeptNoHidden").val("3cdde084-aef1-4062-a700-48aca2552667");
	 $("#toDeptNoHidden").val("6c836e05-8a89-4fd9-bb76-9b4ecfe43ecd");
	 
	 /* $('#fromDeptNo').val('c1d5f18f-b9c4-4a5f-9a47-42511e4a0812').trigger('change.select2');
	 $('#toDeptNo').val('6c836e05-8a89-4fd9-bb76-9b4ecfe43ecd').trigger('change.select2'); 
	 $("#fromDeptNoHidden").val("c1d5f18f-b9c4-4a5f-9a47-42511e4a0812");
	 $("#toDeptNoHidden").val("6c836e05-8a89-4fd9-bb76-9b4ecfe43ecd"); */
	 //$('#materialId').val('').trigger('change.select2');
	 $("#materialCode").val("");
	 $('#materialTypeId').val('').trigger('change.select2');
	 $("#materialType_id").val("");
	 $("#sampleDetails").val("");
	 $("#child_id").val('');
	 $("#mfgDate").val('');
	 $("#expDate").val('');
	 $("#batchLot").val('');
	 $("#batchNo").val('');
	 $("#qty").val('');
	 $('#unitId').val('');
	 $('#unit_name').val('');
	 $('#packSize').val('');
	 //aTable.rows().remove().draw();
	//$.fn.modal.Constructor.prototype.enforceFocus = function() {};
	$("#productionInfoModal").modal();
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
	var materialId=$(el).closest("tr").find("#s_materialId").val();
	var materialNm=$(el).closest("tr").find("#s_materialName").val();
	var materialCode=$(el).closest("tr").find("#s_materialCode").val();
	var materialTypeId=$(el).closest("tr").find("#s_materialTypeId").val();
	var sampleDetails=$(el).closest("tr").find("#s_sampleDetails").val();
	
	 $("#id").val(id);
	 $('#reqType').val(reqType).trigger('change.select2'); 
	 $('#fromDeptNo').val(fromDeptNo).trigger('change.select2');
	 $('#toDeptNo').val(toDeptNo).trigger('change.select2');
	 $("#fromDeptNoHidden").val(fromDeptNo);
	 $("#toDeptNoHidden").val(toDeptNo);
	 var opt = document.createElement('option');
	   opt.value = materialId;
	   opt.innerHTML = materialNm;
	 $('#materialId').append(opt);
	 $('select#materialId').val(materialId).select2();
	 //$('#materialId').val(materialId).trigger('change.select2');
	 $("#materialCode").val(materialCode);
	 $('#materialTypeId').val(materialTypeId).trigger('change.select2');
	 $("#materialType_id").val(materialTypeId);
	 $("#sampleDetails").val(sampleDetails);
	
	//$.fn.modal.Constructor.prototype.enforceFocus = function() {};
	$("#productionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
    
    whRequestDetailsById(id);
};
function whRequestDetailsById(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/production/whRequestDetailsById?whRequestId=" + id,
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
//$.get( "${pageContext.request.contextPath}/material/getMaterialInfoById/" + materialId,
function materialInfoById(id){
	var materialId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/production/getBatchInfoById/" + materialId, 
	function( data ) {
		
		$("#materialCode").val(data.materialCode);
		$('#materialTypeId').val(data.materialTypeId).trigger('change.select2');
		 $("#materialType_id").val(data.materialTypeId);
		 $("#batchNo").val(data.batchNo);
		 $("#batchLot").val(data.lotNo);
		 $("#mfgDate").val(convertMmDate(data.mfgDate));
		 $("#expDate").val(convertMmDate(data.expDate));
		 $("#qty").val(data.batchSize);
		 $('#unitId').val(data.unitId);
		 $('#unit_name').val(data.unitName);
		 $('#packSize').val(data.packSize);
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
		var packSize =data[i].packSize || "";
		
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
		$("#child_id").val(chdId);
		//$('#mfgDate').datepicker("setDate", dates );
		//$('#expDate').datepicker("setDate", expireDate );
		$('#mfgDate').val(dates );
		$('#expDate').val(expireDate );
		$("#batchLot").val(batchLot);
		$("#batchNo").val(batchNo);
		$("#qty").val(qty);
		$('#unitId').val(unitId);
		$('#unit_name').val(unitName);
		$('#packSize').val(packSize);
	}
}

$("#productionInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#productionInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/production/save-production-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#productionInfoModal").modal('hide');
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
function view(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/production/get-whRequest-info/" + id ,
		success : function(data) {			
			$('#productionViewInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$(".md").css("display", "none");
			
			}
		});
}

function sendCss(el) {
	
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/production/get-whRequest-info/" + id ,
		success : function(data) {			
			$('#productionViewInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$(".close-footer").css("display", "none");
			
			}
		});
}

function productionInfoPrint(id,udId) {
	var ajaxURL = "${pageContext.request.contextPath}/production/request-info-print?requestId="+id+'&udRequestId='+udId;
	
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
