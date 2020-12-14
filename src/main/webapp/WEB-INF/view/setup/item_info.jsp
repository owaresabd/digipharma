<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">ITEM INFO ENTRY</span>
		<%-- <div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div> --%>
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
								<th class="align-center" style="width: 60px;">SL#</th>
								<th class="align-left" style="width: 100px;">UD ITEM NO</th>
								<th class="align-left">ITEM NAME</th>
								<th class="align-left">ITEM GROUP</th>
								<th class="align-center">ITEM FLAG</th>
								<th class="align-center">PRICE</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 75px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_type_code">${info.udItemNo}</td>
								<td class="field_type_name">${info.itemName}</td>
								<td class="field_type_name">${info.groupName}</td>
								<td class="field_type_name align-center">
								<c:choose>
								    <c:when test="${info.itemFlag=='Y'}">
								        YES
								    </c:when>    
								    <c:otherwise>
								       NO
								    </c:otherwise>
								</c:choose>
								</td>
								<td class="field_type_name align-center">${info.unitPrice}</td>
								<td class="field_type_status align-center" width="100px">
									<c:choose>
									    <c:when test="${info.status =='Active'}">
											<span class="badge bg-green">${info.status}</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">${info.status}</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="s_item_no" value="${info.udItemNo}">
									<input type="hidden" id="s_item_name" value="${info.itemName}">
									<input type="hidden" id="s_item_flag" value="${info.itemFlag}">
									<input type="hidden" id="s_group_no" value="${info.groupNo}">
									<input type="hidden" id="s_group_nm" value="${info.groupName}">
									<input type="hidden" id="s_parent_no" value="${info.parentItemNo}">
									<input type="hidden" id="s_parent_nm" value="${info.parentItemName}">
									<input type="hidden" id="s_identify_type" value="${info.identifyType}">
									<input type="hidden" id="s_uom_no" value="${info.uomNo}">
									<input type="hidden" id="s_uom_nm" value="${info.uomName}">
									<input type="hidden" id="s_method_no" value="${info.methodNo}">
									<input type="hidden" id="s_method_nm" value="${info.methodName}">
									<input type="hidden" id="s_price" value="${info.unitPrice}">
									<input type="hidden" id="s_country_no" value="${info.countryNo}">
									<input type="hidden" id="s_country_nm" value="${info.countryName}">
									<input type="hidden" id="s_brand_nm" value="${info.brandName}">
									<input type="hidden" id="s_model_nm" value="${info.modelName}">
									<input type="hidden" id="s_supplier_no" value="${info.supplierNo}">
									<input type="hidden" id="s_supplier_nm" value="${info.supplierName}">
									<input type="hidden" id="s_batch" value="${info.batchRequired}">
									<input type="hidden" id="s_exp" value="${info.expireable}">
									<input type="hidden" id="s_replace" value="${info.replaceable}">
									<input type="hidden" id="s_repair" value="${info.repairable}">
									<input type="hidden" id="s_remarks" value="${info.remarks}">
									<input type="hidden" id="s_status" value="${info.status}">
									
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
<div class="modal fade" id="itemInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">ITEM INFO</h4>
                 </div>
                 <form method="post" id="itemInfoForm" modelAttribute="itemInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>ITEM NO. :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="udItemNo" maxlength="10" name="udItemNo" value="" class="form-control" placeholder="Item No."  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>ITEM NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="itemName" name="itemName" value="" class="form-control" placeholder="Item name"  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>ITEM FLAG :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="itemFlag" name="itemFlag" class="js-example-theme-single form-control" style="width: 100%;" required="required">
	                     			<option></option>
								    <option value="Y">YES</option>
								    <option value="N">NO</option>
	                     		</select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>GROUP NO. :</b></span>
                            	<div class="form-group">
	                                <select id="group_list"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="types" items="${typeInfos}">
				                           	<option value="${types.id }">${types.itemTypeName }</option>
				                        </c:forEach>
				                        </select>
				                        <input type="hidden" id="groupNo" name="groupNo"  value=""/>
				                        <input type="hidden" id="groupName" name="groupName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>PARENT ITEM NO. :</b></span>
                            	<div class="form-group">
	                                <select id="parent_list" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="parentList" items="${parentInfos}">
				                           	<option value="${parentList.id}">${parentList.itemName }</option>
				                        </c:forEach>
				                        </select>
				                        <input type="hidden" id="parentItemNo" name="parentItemNo" value=""/>
				                        <input type="hidden" id="parentItemName" name="parentItemName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>IDENTITY TYPE :</b></span>
                            	<div class="form-group">
	                                <select id="identifyType" name="identifyType" class="js-example-theme-single form-control" style="width: 100%;">
	                     			<option value=""></option>
								    <option value="G">GROUP</option>
								    <option value="U">UNIQUE</option>
	                     		</select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>UoM NO. :</b></span>
                            	<div class="form-group">
	                                <select id="uom_list" class="js-example-theme-single form-control" style="width: 100%;">
				                        <option value=""></option>
				                        <c:forEach var="uomInfo" items="${uomInfos}">
				                           	<option value="${uomInfo.id }">${uomInfo.uomName }</option>
				                        </c:forEach>
				                        </select>
				                        <input type="hidden" id="uomNo" name="uomNo" value=""/>
				                        <input type="hidden" id="uomName" name="uomName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>METHOD NO. :</b></span>
                            	<div class="form-group">
	                                <select id="method_list" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="methodInfo" items="${methodInfos}">
				                           	<option value="${methodInfo.id }">${methodInfo.methodName }</option>
				                        </c:forEach>
				                        </select>
				                        <input type="hidden" id="methodNo" name="methodNo" value=""/>
				                        <input type="hidden" id="methodName" name="methodName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>PRICE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="unitPrice" name="unitPrice" value="" class="form-control number" placeholder="Price" style="width: 50%;">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>ORIGIN/ COUNTRY. :</b></span>
                            	<div class="form-group">
	                                <select id="country_list" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="countryInfo" items="${countryInfos}">
				                           	<option value="${countryInfo.id }">${countryInfo.countryName}</option>
				                        </c:forEach>
				                        </select>
				                        <input type="hidden" id="countryNo" name="countryNo" value=""/>
				                        <input type="hidden" id="countryName" name="countryName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>BAND :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="brandName" name="brandName" value="" class="form-control" placeholder="Brand name"  autocomplete="off" >
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MODEL :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="modelName" name="modelName" value="" class="form-control" placeholder="Model name"  autocomplete="off" >
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>SUPPLIER :</b></span>
                            	<div class="form-group">
	                                <select id="supplier_list" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="supplierInfo" items="${supplierInfos}">
				                           	<option value="${supplierInfo.id }">${supplierInfo.supplierName}</option>
				                        </c:forEach>
				                        </select>
				                        <input type="hidden" id="supplierNo" name="supplierNo" value=""/>
				                        <input type="hidden" id="supplierName" name="supplierName" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-8">
                            	<span><b>DESCRIPTION :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="remarks" maxlength="10" name="remarks" value="" class="form-control" placeholder="Description goes here..."  autocomplete="off" >
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-2">
                            	<span><b>BATCH REQUIRED :</b></span>
                            	<div class="form-group">
	                                <select id="batchRequired" name="batchRequired" class="js-example-theme-single form-control" style="width: 100%;">
	                     			<option></option>
								    <option value="Y">YES</option>
								    <option value="N">NO</option>
	                     		</select>
                            	</div>
                            </div>
                            <div class="col-md-2">
                            	<span><b>EXPIREABLE :</b></span>
                            	<div class="form-group">
	                                <select id="expireable" name="expireable" class="js-example-theme-single form-control" style="width: 100%;">
	                     			<option></option>
								    <option value="Y">YES</option>
								    <option value="N">NO</option>
	                     		</select>
                            	</div>
                            </div>
                            <div class="col-md-2">
                            	<span><b>REPLACEABLE :</b></span>
                            	<div class="form-group">
	                                <select id="replaceable" name="replaceable" class="js-example-theme-single form-control" style="width: 100%;">
	                     			<option></option>
								    <option value="Y">YES</option>
								    <option value="N">NO</option>
	                     		</select>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>REPAIRABLE :</b></span>
                            	<div class="form-group">
	                                <select id="repairable" name="repairable" class="js-example-theme-single form-control" style="width: 100%;">
	                     			<option></option>
								    <option value="Y">YES</option>
								    <option value="N">NO</option>
	                     		</select>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>ACTIVITY STATUS</b></span>
                            	<div class="form-group">
	                                <div class="demo-checkbox">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="Inactive">
								</div>
                            	</div>
                            </div>
                 		</div>
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
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


$('.number').keypress(function (event) {
    return isNumber(event, this)
});


$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select from list",
	dropdownParent: $("#itemInfoModal")
});

$("#group_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#groupNo").val(id);
	$("#groupName").val(name);
	$('#parentItemNo').val('');
	$('#parentItemName').val('');
	$(".alert-code").empty().addClass("hidden");
	$('#parent_list').val('').trigger('change.select2');
	setParentItemInfo(id,'');
});
function setParentItemInfo(id, value){
	$("#parent_list").empty().trigger('');
	$.get( "${pageContext.request.contextPath}/itemInfo/mapping-parent-item-info/" + id, 
	function( data ) {
		$('#parent_list').append('<option value=""></option>');
		for (var i = 0; i < data.length; i++) {
			$('#parent_list').append('<option value="'+data[i].id+'">'+data[i].itemName+'</option>');
		}
		if(value.trim()!=''){
		$('#parent_list').val(value).trigger('change.select2');
		}
	});
}
$("#parent_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#parentItemNo").val(id);
	$("#parentItemName").val(name);
	$(".alert-code").empty().addClass("hidden");
});
$("#uom_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#uomNo").val(id);
	$("#uomName").val(name);
	$(".alert-code").empty().addClass("hidden");
});

$("#method_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#methodNo").val(id);
	$("#methodName").val(name);
	$(".alert-code").empty().addClass("hidden");
});
$("#country_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#countryNo").val(id);
	$("#countryName").val(name);
	$(".alert-code").empty().addClass("hidden");
});

$("#supplier_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#supplierNo").val(id);
	$("#supplierName").val(name);
	$(".alert-code").empty().addClass("hidden");
});

$(function() {
    $('#udItemNo').keyup(function() {
        this.value = this.value.toUpperCase();
    });
});

$('#activity_status').change(function() {
	if (this.checked) {
		$('#status').val('Active');
		$('.check-status').text('Active ?');
	}else{
		$('#status').val('Inactive');
		$('.check-status').text('Inactive ?');
	}
});

$('#udItemNo').keyup(function() {
	var udItemNo = $("#udItemNo").val();
	$.get( "${pageContext.request.contextPath}/itemInfo/validate-udItemNo/" + udItemNo, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate Item No. code available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
	});
});

function add(el) {
	 $("#id").val("");
	 $("#udItemNo").val("");
	 $("#itemName").val("");
	 $('#itemFlag').val('').trigger('change.select2'); 
	 $('#group_list').val('').trigger('change.select2'); 
	 $("#groupNo").val("");
	 $("#groupName").val("");
	 //$('#parent_list').val('').trigger('change.select2'); 
	 $("#parent_list").empty().trigger('');
	 $('#parent_list').append('<option value=""></option>');
	 $("#parentItemNo").val("");
	 $("#parentItemName").val("");
	 $('#identifyType').val('').trigger('change.select2');
	 $('#uom_list').val('').trigger('change.select2'); 
	 $("#uomNo").val("");
	 $("#uomName").val("");
	 $('#method_list').val('').trigger('change.select2'); 
	 $("#methodNo").val("");
	 $("#methodName").val("");
	 $("#unitPrice").val("");
	 $('#country_list').val('').trigger('change.select2'); 
	 $("#countryNo").val("");
	 $("#countryName").val("");
	 $("#brandName").val("");
	 $("#modelName").val("");
	 $('#supplier_list').val('').trigger('change.select2'); 
	 $("#supplierNo").val("");
	 $("#supplierName").val("");
	 $('#batchRequired').val('').trigger('change.select2'); 
	 $('#expireable').val('').trigger('change.select2'); 
	 $('#replaceable').val('').trigger('change.select2'); 
	 $('#repairable').val('').trigger('change.select2'); 
	 $("#remarks").val("");
	$("#activity_status").prop('checked', false);
	$('#status').val('Inactive'); 
    $("#itemInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id = $(el).closest("tr").find("#row_id").val();
	var udItemNo = $(el).closest("tr").find("#s_item_no").val();
	var itemName = $(el).closest("tr").find("#s_item_name").val();
	var itemFlag = $(el).closest("tr").find("#s_item_flag").val();
	var groupNo = $(el).closest("tr").find("#s_group_no").val();
	var groupName = $(el).closest("tr").find("#s_group_nm").val();
	var parentIitemNo = $(el).closest("tr").find("#s_parent_no").val();
	var parentIitemName=$(el).closest("tr").find("#s_parent_nm").val();
	var identifyType=$(el).closest("tr").find("#s_identify_type").val();
	var uomNo=$(el).closest("tr").find("#s_uom_no").val();
	var uomName=$(el).closest("tr").find("#s_uom_nm").val();
	var methodNo=$(el).closest("tr").find("#s_method_no").val();
	var methodName=$(el).closest("tr").find("#s_method_nm").val();
	var unitPrice=$(el).closest("tr").find("#s_price").val();
	var countryNo=$(el).closest("tr").find("#s_country_no").val();
	var countryName=$(el).closest("tr").find("#s_country_nm").val();
	var brandName=$(el).closest("tr").find("#s_brand_nm").val();
	var modelName=$(el).closest("tr").find("#s_model_nm").val();
	var supplierNo=$(el).closest("tr").find("#s_supplier_no").val();
	var supplierName=$(el).closest("tr").find("#s_supplier_nm").val();
	var batchRequired=$(el).closest("tr").find("#s_batch").val();
	var expireable=$(el).closest("tr").find("#s_exp").val();
	var replaceable=$(el).closest("tr").find("#s_replace").val();
	var repairable=$(el).closest("tr").find("#s_repair").val();
	var remarks = $(el).closest("tr").find("#s_remarks").val();
	var status = $(el).closest('tr').find("#s_status").val();
	
	
	 $("#id").val(Id);
	 $("#udItemNo").val(udItemNo);
	 $("#itemName").val(itemName);
	 $('#itemFlag').val(itemFlag).trigger('change.select2'); 
	 $('#group_list').val(groupNo).trigger('change.select2'); 
	 $("#groupNo").val(groupNo);
	 $("#groupName").val(groupName);
	  setParentItemInfo(groupNo,parentIitemNo);
	// $('#parent_list').val(parentIitemNo).trigger('change.select2'); 
	 $("#parentItemNo").val(parentIitemNo);
	 $("#parentItemName").val(parentIitemName);
	 $('#identifyType').val(identifyType).trigger('change.select2');
	 $('#uom_list').val(uomNo).trigger('change.select2'); 
	 $("#uomNo").val(uomNo);
	 $("#uomName").val(uomName);
	 $('#method_list').val(methodNo).trigger('change.select2'); 
	 $("#methodNo").val(methodNo);
	 $("#methodName").val(methodName);
	 $("#unitPrice").val(unitPrice);
	 $('#country_list').val(countryNo).trigger('change.select2'); 
	 $("#countryNo").val(countryNo);
	 $("#countryName").val(countryName);
	 $("#brandName").val(brandName);
	 $("#modelName").val(modelName);
	 $('#supplier_list').val(supplierNo).trigger('change.select2'); 
	 $("#supplierNo").val(supplierNo);
	 $("#supplierName").val(s_supplier_nm);
	 $('#batchRequired').val(batchRequired).trigger('change.select2'); 
	 $('#expireable').val(expireable).trigger('change.select2'); 
	 $('#replaceable').val(replaceable).trigger('change.select2'); 
	 $('#repairable').val(repairable).trigger('change.select2'); 
	 $("#remarks").val(remarks);
	
	if(status == 'Active'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Active');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('Inactive');
		$('.check-status').text('Inactive');
	}
	
	$("#itemInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#itemInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#itemInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/itemInfo/save-item-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#itemInfoModal").modal('hide');
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
