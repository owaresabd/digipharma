<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
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
	    <span style="text-shadow: 2px 2px 2px #aaa;">PRODUCT SETUP</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/product/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/product/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="productList">
						<thead>
							<tr>
							<th class="align-center">SL#</th>
								<th class="align-left" >PRODUCT NAME</th>
								<th class="align-center">SHELF LIFE</th>
								<th class="align-center">BATCH SIZE </th>
								<th class="align-center">PACK SIZE </th>
								<th class="align-center" style="width: 80px;">STATUS</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count} </td>
								<td class="align-left">${info.productName} </td>
								<td class="align-center">${info.shelfLife} &nbsp; Month</td>
								<td class="align-center">${info.batchSize} &nbsp; ${info.unitName} </td>
								<td class="align-center">${info.packSize}</td>
								<td class="align-center" width="100px">
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
									<input type="hidden" id="r_product_id" value="${info.productId}">
									<input type="hidden" id="r_product_nm" value="${info.productName}">
									<input type="hidden" id="r_shelf_life" value="${info.shelfLife}">
									<input type="hidden" id="r_batch_size" value="${info.batchSize}">
									<input type="hidden" id="r_unit_id" value="${info.unitId}">
									<input type="hidden" id="r_pack_size" value="${info.packSize}">
									<input type="hidden" id="r_status" value="${info.status}"> 
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									<a class="btn-edit btn btn-xs" onclick="productInfoPrint('${info.id}','${info.productId}')"><i class="material-icons">print</i></a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="productModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Product Setup </td>
                 	<td>Document No.</td>
                 	<td colspan="2">FM-PR-001</td>
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
                 <!-- <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl load close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">PRODUCT SETUP</h4>
                 </div> -->
                 <form method="post" id="productInfoForm" modelAttribute="productInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">PRODUCT NAME :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <select  id="productId" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option value="">-Select Product-</option>
				                        <c:forEach var="matInfo" items="${materialList}">
				                           	<option value="${matInfo.id }">${matInfo.materialName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="product_id" name="productId" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_type">SHELF LIFE :</label>
                            </div>
                            <div class="col-md-2">
                            <div class="form-group">
                            	<input type="text" id="shelfLife" name="shelfLife" maxlength="2" value="" class="form-control"  autocomplete="off">
                            </div>
                            </div>
                            
                            <div class="col-md-5">
                            <div class="form-group">
                            	<span class="badge bg-pink"><label> Value should be in Month.</label></span>
                            </div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_type">BATCH SIZE :</label>
                            </div>
                            <div class="col-md-2">
                            <div class="form-group">
                            	<input type="text" id="batchSize" name="batchSize" value="" class="form-control" autocomplete="off">
                            </div>
                            </div>
                            <div class="col-md-4">
                            	<div class="form-group">
	                                <select id="unitId" name="unitId"  class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option value="">-Select Unit-</option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 		</select>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_type">PACK SIZE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                            	<input type="text" id="packSize" name="packSize" value="" class="form-control"  autocomplete="off">
                            </div>
                            </div>
                            <div class="col-md-5">
                            <div class="form-group">
                            	<span class="badge bg-pink"><label> Press Alt+0215 for &times; Sign.</label></span>
                            </div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right">
                            	<label for="status">ACTIVITY STATUS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="demo-checkbox">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
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

$(".load").on('click', function(e){
	var link ="${pageContext.request.contextPath}/product/maintain";
	$("#productModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
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


$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$("#productId").on('change', function() {
	var id 	= $('option:selected', this).val();
	$("#product_id").val(id);
});

function add(el) {
	$("#id").val('');
	$("#productId").val("").trigger('change.select2');
	$(".md").prop("disabled", false);
	$("#product_id").val('');
	$("#shelfLife").val('');
	$("#batchSize").val('');
	$("#unitId").val("").trigger('change.select2');
	$("#packSize").val('');
	
    $("#productModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {	
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var productId 	= $(el).closest("tr").find("#r_product_id").val();
	var productNm 	= $(el).closest("tr").find("#r_product_nm").val();
	var shelfLife 	= $(el).closest("tr").find("#r_shelf_life").val();
	var batchSize 	= $(el).closest("tr").find("#r_batch_size").val();
	var unitId 		= $(el).closest("tr").find("#r_unit_id").val();
	var packSize 	= $(el).closest("tr").find("#r_pack_size").val();
	var Status 		= $(el).closest('tr').find("#r_status").val();
	
	$("#id").val(Id);
	//Below code for add new option combobox
	var opt = document.createElement('option');
    opt.value = productId;
    opt.innerHTML = productNm;
    //End//
    $('#productId').append(opt);
	$("#productId").val(productId).trigger('change.select2');
	$(".md").prop("disabled", true);
	$("#product_id").val(productId);
	$("#shelfLife").val(shelfLife);
	$("#batchSize").val(batchSize);
	$("#unitId").val(unitId).trigger('change.select2');
	$("#packSize").val(packSize);
	
	if(Status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#productModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#productInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#productInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/product/save-product-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#productModal").modal('hide');
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
}  else {
	sweetAlert("Cancelled", "", "error", 0, false);
}

});
});


</script>