<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
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
	    <span style="text-shadow: 2px 2px 2px #aaa;">BATCH INFORMATION</span>
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
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="batchList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;">SL#</th>
								<th class="align-left">PRODUCT NAME</th>
								<th class="align-center">BATCH NO</th>
								<th class="align-center">BATCH SIZE</th>
								<th class="align-center">SHELF LIFE</th>
								<th class="align-center">MFG. DATE </th>
								<th class="align-center">EXP DATE</th>
								<!-- <th class="align-center" style="width: 80px;">STATUS</th> -->
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.productName}</td>
								<td class="align-center">${info.batchNo}</td>
								<td class="align-center">${info.batchSize} &nbsp; ${info.unitName}</td>
								<td class="align-center">${info.shelfLife} &nbsp; Month </td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.mfgDate}" var="mfgDate" />
	                              <c:out value="${mfgDate}"/>
								
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.expDate}" var="expDate" />
	                              <c:out value="${expDate}"/>
								</td>
								
								<%-- <td class="align-center" width="100px">
									<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
									</c:choose>
								</td> --%>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_product_id" value="${info.productId}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_batch_size" value="${info.batchSize}">
									<input type="hidden" id="r_unit_id" value="${info.unitId}">
									<input type="hidden" id="r_unit_Name" value="${info.unitName}">
									<input type="hidden" id="r_mfg_date" value="${info.mfgDate}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_shelf_life" value="${info.shelfLife}">
									<input type="hidden" id="r_status" value="${info.status}">
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
	
	<div class="modal fade" id="batchInfoModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Batch Setup Info</td>
                 	<td>Document No.</td>
                 	<td colspan="2">FM-PR-002</td>
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
                 <form method="post" id="batchInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>NAME OF PRODUCT :</b></span>
                            	<div class="form-group">
	                                <select  id="productId" onchange="productInfoById(this.id)" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option >-Select Product-</option>
				                        <c:forEach var="matInfo" items="${materialList}">
				                           	<option value="${matInfo.id }">${matInfo.materialName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="product_id" name="productId" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>BATCH NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="batchNo" name="batchNo" value="" class="form-control" readonly="readonly" >
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>BATCH SIZE :</b></span>
                            	<div class="input-group">
                            	<div class="align-left">
	                                <input type="text" id="batchSize" name="batchSize" value="" class="form-control" style="width: 50%;"  readonly="readonly">
                            	<input type="hidden" value="" id="unitId" name="unitId"/>
                            	</div>
                            	<div id="unitName" class="input-group-addon col-md-6 align-left"> </div>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>SHELF LIFE :</b></span>
                            	<div class="input-group">
                            	<div class="align-left">
	                                <input type="text" id="shelfLife" onkeyup="getExpDate();" style="width: 35%;" name="shelfLife" value="" class="form-control"  readonly="readonly">
                            	</div>
                            	<div class="input-group-addon col-md-6 align-left"> &nbsp;Month</div>
                            	</div>
                            </div>
                            
                 		</div>
                 		
                 		<div class="row">
                 			<div class="col-md-4">
                            	<span><b>LOT NO. :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="lotNo" name="lotNo" value="" class="form-control"  maxlength="10" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MFG. DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" onselect="getExpDate();" id="mfgDate" name="mfgDate" value="" class="form-control"  readonly="readonly" style="width: 50%;">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>EXP. DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="expDate" name="expDate" value="" required="required" class="form-control"  style="width: 50%;" readonly="readonly">
                            	</div>
                            </div>
                 		</div>
                 		<!-- <div class="row">
                            
                            <div class="col-md-6">
                            	<span><b>ACTIVITY STATUS :</b></span>
                            	<div class="demo-checkbox m-t-5">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            </div>
                 		</div> -->
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
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
	var link ="${pageContext.request.contextPath}/batch/maintain";
	$("#batchInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});
function getExpDate(){
	
	var duration=$("#shelfLife").val();
	var mfDate=$("#mfgDate").val();
	var date = new Date(mfDate);
	date.setMonth(date.getMonth() + parseInt(duration));
    var months=["Jan","Feb","Mar","Apr","May","Jun","Jul",
		"Aug","Sep","Oct","Nov","Dec"];
		var val=date.getDate()+"-"+months[date.getMonth()]+"-"+date.getFullYear();
	$("#expDate").val(val) 
}
$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
    $( "#mfgDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
});
$('#mfgDate').datepicker().on("input change", function (e) {
	getExpDate();
});
/* $('#activity_status').change(function() {
	if (this.checked) {
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
}); */


$("#productId").on('change', function() {
	var id 	= $('option:selected', this).val();
	$("#product_id").val(id);
});

function productInfoById(id){
	var productId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/product/getProductInfoById/" + productId, 
	function( data ) {
		if(data.status ==0){
			$("#batchNo").val(data.batchNo);
			 //$("#batchDate").val(convertMmDate(data.batchDate));
			 $("#batchSize").val(data.batchSize);
			 $("#unitId").val(data.unitId);
			 $("#unitName").text(data.unitName);
			 $("#shelfLife").val(data.shelfLife);
			 $("#mfgDate").val(convertMmDate(data.mfgDate));
			$("#expDate").val(convertMmDate(data.expDate));
			
		}else{
			sweetAlert("Alert!", "Batch Pending for QC Request.", "warning", 1000, false);
		 
		}
	});
}

$('.number').keypress(function (event) {
    return isNumber(event, this)
});



function add(el) {
	
	$("#id").val("");
	$('#productId').val($('#productId option:first-child').val()).trigger('change');
	//$("#productId").val("");
	$(".md").prop("disabled", false);
	$("#product_id").val('');
	$("#batchNo").val("");
	$("#batchSize").val("");
	$("#unitId").val("");
	$("#unitName").text("");
	$("#lotNo").val("");
	$("#shelfLife").val("");
	$("#mfgDate").val("");
	$("#expDate").val("");
		
    $("#batchInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var productId 	    = $(el).closest("tr").find("#r_product_id").val();
	var batchNo 		= $(el).closest("tr").find("#r_batch_no").val();
	var batchSize 		= $(el).closest("tr").find("#r_batch_size").val();
	var unitId 			= $(el).closest("tr").find("#r_unit_id").val();
	var unitName 		= $(el).closest("tr").find("#r_unit_Name").val();
	var mfgDate 	   = $(el).closest('tr').find("#r_mfg_date").val();
	var expDate 		= $(el).closest("tr").find("#r_exp_date").val();
	var lotNo			= $(el).closest("tr").find("#r_lot_no").val();
	var shelfLife	    = $(el).closest("tr").find("#r_shelf_life").val();
	var Status 			= $(el).closest('tr').find("#r_status").val();
	
	$("#id").val(Id);
	$('select#productId').val(productId).select2();
	//$("#productId").select2().select2('val',productId);
	//$("#productId").val(productId).trigger('change.select2');
	$(".md").prop("disabled", true);
	$("#product_id").val(productId);
	$("#batchNo").val(batchNo);
	$("#batchSize").val(batchSize);
	$("#unitId").val(unitId);
	$("#unitName").text(unitName);
	$("#lotNo").val(lotNo);
	$("#shelfLife").val(shelfLife);
	$("#mfgDate").val(convertMmDate(mfgDate));
	$("#expDate").val(convertMmDate(expDate));
	/* if(Status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	} */
	
	$("#batchInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#batchInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#batchInfoForm").serialize();
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
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/batch/save-batch-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#batchInfoModal").modal('hide');
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