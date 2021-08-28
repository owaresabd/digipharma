<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">PRODUCT LIST</span>
		
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
								<th class="align-left" style="width: 100px;">PRODUCT NAME</th>
								<th class="align-left">PRODUCT CODE</th>
								<th class="align-left">PRODUCT TYPE</th>
								<th class="align-left">GENERIC NAME</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 75px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							 <c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_type_code">${info.itemName}</td>
								<td class="field_type_name">${info.itemCode}</td>
								<td class="field_type_name">${info.itemTypeInfo.typeName}</td>
								<td class="field_type_name">${info.genericInfo.genericName}</td>
								<td class="field_type_status align-center" width="100px">
									<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									
									<input type="hidden" id="s_item_name" value="${info.itemName}">
									<input type="hidden" id="s_item_code" value="${info.itemCode}">
									<input type="hidden" id="s_type_id" value="${info.typeId}">
									<input type="hidden" id="s_generic_id" value="${info.genericId}">
									<input type="hidden" id="s_manufacturer_id" value="${info.manufacturerId}">
									<input type="hidden" id="s_country_id" value="${info.countryId}">
									<input type="hidden" id="s_unit_id" value="${info.unitId}">
									<input type="hidden" id="s_purchase_price" value="${info.purchasePrice}">
									<input type="hidden" id="s_sales_price" value="${info.salesPrice}">
									<input type="hidden" id="s_rack_no" value="${info.rackNo}">
									<input type="hidden" id="s_row_no" value="${info.rowNo}">
									<input type="hidden" id="s_remarks" value="${info.remarks}">
									<input type="hidden" id="s_status" value="${info.status}">
									
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a> || <a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="fa fa-barcode"></i></a> 
									
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
                    <h4 class="modal-title align-center" id="defaultModalLabel">PRODUCT INFORMATION</h4>
                 </div>
                 <form method="post" id="itemInfoForm" modelAttribute="itemInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>PRODUCT NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="itemName" maxlength="10" name="itemName" value="" class="form-control"   autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>PRODUCT CODE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="itemCode" name="itemCode" value="" class="form-control"  autocomplete="off" required>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>CATEGORY NAME :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="typeId" name="typeId" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="types" items="${typeInfos}">
				                           	<option value="${types.id }">${types.typeName }</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>GENERIC NAME :</b></span>
                            	<div class="form-group">
	                                <select id="genericId" name="genericId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="types" items="${genericInfos}">
				                           	<option value="${types.id }">${types.genericName }</option>
				                        </c:forEach>
				                        </select>
				                       
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MANUFACTURER NAME :</b></span>
                            	<div class="form-group">
	                                <select id="manufacturerId" name="manufacturerId" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="manInfos" items="${manufacturerInfos}">
				                           	<option value="${manInfos.id}">${manInfos.manufacturerName}</option>
				                        </c:forEach>
				                        </select>
				                        
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>ORIGIN/ COUNTRY. :</b></span>
                            	<div class="form-group">
	                                <select id="countryId" name="countryId" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="countryInfo" items="${countryInfos}">
				                           	<option value="${countryInfo.id}">${countryInfo.countryName}</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>UNIT NAME :</b></span>
                            	<div class="form-group">
	                                <select id="unitId" name="unitId" class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option></option>
				                        <c:forEach var="infos" items="${unitInfos}">
				                           	<option value="${infos.id}">${infos.unitName }</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
	                 		<div class="col-md-4">
                            	<span><b>PURCHASE PRICE:</b></span>
                            	<div class="form-group">
	                               
				                       <input type="text" id="purchasePrice" name="purchasePrice" value="" class="form-control number"  style="width: 50%;">
				                        
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>SALES PRICE :</b></span>
                            	<div class="form-group">
	                                
				                         <input type="text" id="salesPrice" name="salesPrice" value="" class="form-control number"  style="width: 50%;">
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>RACK NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="rackNo" name="rackNo" value="" class="form-control number"  style="width: 50%;">
                            	</div>
                            </div>
	                 		<div class="col-md-4">
                            	<span><b>ROW NO:</b></span>
                            	<div class="form-group">
	                               
				                       <input type="text" id="rowNo" name="rowNo" value="" class="form-control number"  style="width: 50%;">
				                        
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>ACTIVITY STATUS :</b></span>
                            	<div class="form-group">
	                                
				                         <div class="demo-checkbox">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="Inactive">
								</div>
                            	</div>
                            </div>
                            
                 		</div>
                 		
                 		<div class="row">
	                 		
                            <div class="col-md-12">
                            	<span><b>DESCRIPTION :</b></span>
                            	<div class="form-group">
	                                <textarea rows="5" id="remarks" name="remarks" class="form-control"  ></textarea>
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



$('#activity_status').change(function() {
	if (this.checked) {
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
});



function add(el) {
	$("#id").val("");
	 $("#itemName").val("");
	 $("#itemCode").val("");
	 $("#typeId").select2("val", "");
	 $("#genericId").select2("val", "");
	 $("#manufacturerId").select2("val", "");
	 $("#countryId").select2("val", "");
	 $("#unitId").select2("val", "");
	 $("#purchasePrice").val("");
	 $("#salesPrice").val("");
	 $("#rackNo").val("");
	 $("#rowNo").val("");
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
	var id = $(el).closest("tr").find("#row_id").val();
	var itemName = $(el).closest("tr").find("#s_item_name").val();
	var itemCode = $(el).closest("tr").find("#s_item_code").val();
	var typeId = $(el).closest("tr").find("#s_type_id").val();
	var genericId = $(el).closest("tr").find("#s_generic_id").val();
	var manufacturerId = $(el).closest("tr").find("#s_manufacturer_id").val();
	var countryId = $(el).closest("tr").find("#s_country_id").val();
	var unitId = $(el).closest("tr").find("#s_unit_id").val();
	var purchasePrice=$(el).closest("tr").find("#s_purchase_price").val();
	var salesPrice=$(el).closest("tr").find("#s_sales_price").val();
	var rackNo=$(el).closest("tr").find("#s_rack_no").val();
	var rowNo=$(el).closest("tr").find("#s_row_no").val();
	var remarks = $(el).closest("tr").find("#s_remarks").val();
	var status = $(el).closest('tr').find("#s_status").val();
	
	
	 $("#id").val(id);
	 $("#itemName").val(itemName);
	 $("#itemCode").val(itemCode);
	 $("#typeId").select2("val", typeId);
	 $("#genericId").select2("val", genericId);
	 $("#manufacturerId").select2("val", manufacturerId);
	 $("#countryId").select2("val", countryId);
	 $("#unitId").select2("val", unitId);
	 $("#purchasePrice").val(purchasePrice);
	 $("#salesPrice").val(salesPrice);
	 $("#rackNo").val(rackNo);
	 $("#rowNo").val(rowNo);
	 $("#remarks").val(remarks);
	
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
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
	    	url : "${pageContext.request.contextPath}/item/save-item-info",
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
