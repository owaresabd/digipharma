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
	    <span style="text-shadow: 2px 2px 2px #aaa;">REAGENT RECEIVE INFORMATION</span>
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
								<th class="align-left">REAGENT NAME</th>
								<th class="align-center">QUANTITY</th>
								<th class="align-center">BATCH NO</th>
								<th class="align-center">LOT NO</th>
								<th class="align-center">PACK SIZE</th>
								<th class="align-center">MFG. DATE </th>
								<th class="align-center">EXP. DATE</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.reagentName}</td>
								<td class="align-center">${info.receiveQty}&nbsp; ${info.receiveUnitName}</td>
								<td class="align-center">${info.batchNo}</td>
								<td class="align-center">${info.lotNo}</td>
								<td class="align-center">${info.packSize}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.mfgDate}" var="mfgDate" />
	                              <c:out value="${mfgDate}"/>
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.expDate}" var="expDate" />
	                              <c:out value="${expDate}"/>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_reagent_id" value="${info.reagentId}">
									<input type="hidden" id="r_receive_date" value="${info.receiveDate}">
									<input type="hidden" id="r_receive_qty" value="${info.receiveQty}">
									<input type="hidden" id="r_receive_unit_id" value="${info.receiveUnitId}">
									<input type="hidden" id="r_unit_name" value="${info.receiveUnitName}">
									<%-- <input type="hidden" id="r_receive_by" value="${info.receiveBy}"> --%>
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_lot_no" value="${info.lotNo}">
									<input type="hidden" id="r_pack_size" value="${info.packSize}">
									<input type="hidden" id="r_mfg_date" value="${info.mfgDate}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									<input type="hidden" id="r_purity" value="${info.purity}">
									<input type="hidden" id="r_manufacturer_id" value="${info.manufacturerId}">
									<input type="hidden" id="r_country_id" value="${info.countryId}">
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
	
	<div class="modal fade" id="reagentInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Reagent Receive Info</td>
                 	<td>Document No.</td>
                 	<td colspan="2">LB-DIL-CM-027</td>
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
                 
                 <form method="post" id="reagentReceiveInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<%-- <div class="row">
                            <div class="col-md-4">
                            	<span><b>RECEIVED BY :</b></span>
                            	<div class="form-group">
	                                <select  id="receive_list" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option >-Select One-</option>
				                        <c:forEach var="info" items="${employeeInfos}">
				                           	<option value="${info.id }">${info.empName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="receive_by" name="receiveBy" value=""/>
                            	</div>
                            </div>
                 		</div> --%>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>NAME OF THE REAGENT :</b></span>
                            	<div class="form-group">
	                                <select  id="reagent_list" onchange="reagentInfoById(this.id)" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option >-Select Product-</option>
				                        <c:forEach var="reagent" items="${reagentInfos}">
				                           	<option value="${reagent.id }">${reagent.productName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="reagent_id" name="reagentId" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>RECEIVED QUANTITY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="receive_qty" name="receiveQty" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>UNIT NAME :</b></span>
                            	<div class="form-group">
	                                <%-- <select  id="unit_list" class="js-example-theme-single form-control" style="width: 70%;" required="required">
				                        	<option >-Select Product-</option>
				                        <c:forEach var="info" items="${unitList}">
				                           	<option value="${info.id }">${info.unitCode}</option>
				                        </c:forEach>
				                    </select> --%>
				                    <input type="text" id="receive_unit_name" value="" style="width: 70%;" readonly="readonly"/>
				                    <input type="hidden" id="receive_unit_id" name="receiveUnitId" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>BATCH NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="batch_no" name="batchNo" value="" class="form-control" style="width: 70%;" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>LOT NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="lot_no" name="lotNo" value="" class="form-control" style="width: 70%;" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>PACK SIZE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="pack_size" name="packSize" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>RECEIVING DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="receive_date" name="receiveDate" value="" class="form-control" style="width: 70%;" autocomplete="off" onfocus="this.blur();" readonly="readonly" required="required">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MFG. DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="mfg_date" name="mfgDate" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
	                                <!-- <input type="text" onselect="getExpDate();" id="mfgDate" name="mfgDate" value="" class="form-control" style="width: 50%;"> -->
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>EXP. DATE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="exp_date" name="expDate" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>PURITY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="purity" name="purity" value="" class="form-control" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MANUFACTURER NAME :</b></span>
                            	<div class="form-group">
	                                <select  id="manufacturer_list" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option >-Select Manufacture-</option>
				                        <c:forEach var="info" items="${manufactureList}">
				                           	<option value="${info.id }">${info.sourceName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="manufacturer_id" name="manufacturerId" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MANUFACTURER COUNTRY :</b></span>
                            	<div class="form-group">
	                                <select  id="country_list" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        	<option >-Select Product-</option>
				                        <c:forEach var="info" items="${countryList}">
				                           	<option value="${info.id }">${info.countryName}</option>
				                        </c:forEach>
				                    </select>
				                    <input type="hidden" id="country_id" name="countryId" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span><spring:message code="btn.save"/></span>
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
	var link ="${pageContext.request.contextPath}/reagent/receive";
	$("#reagentInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});


$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#receive_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
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

$(document).ready(function () {
	   $('#receive_date').datepicker(
	     {
	        beforeShow: function (input, inst) 
	        { inst.dpDiv = $('<div style="display: none;"></div>'); }
	     });
	});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$("#reagent_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#reagent_id").val(value);
});

$("#receive_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#receive_by").val(value);
});

$("#unit_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#receive_unit_id").val(value);
});

$("#manufacturer_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#manufacturer_id").val(value);
});

$("#country_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#country_id").val(value);
});

function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

		$("#receive_unit_name").val(data.unitName);	
		$("#receive_unit_id").val(data.unitId);
		//$("#unit_list").val(data.unitId).trigger('change.select2');
	});
}

function add(el) {
	$("#id").val("");
	$("#reagent_id").val('');
	$("#reagent_list").val("").trigger('change.select2');
	$('#receive_date').datepicker('setDate', new Date());
	//$('#receive_date').attr('readonly', true);
	$("#receive_qty").val("");
	$("#receive_unit_id").val('');
	$("#receive_unit_name").val('');
	//$("#unit_list").val("").trigger('change.select2');
	//$("#receive_by").val('');
	//$("#receive_list").val("").trigger('change.select2');
	$("#batch_no").val("");
	$("#lot_no").val("");
	$("#pack_size").val("");
	$("#purity").val("");
	$("#mfg_date").datepicker('setDate', '');
	$("#exp_date").datepicker('setDate', '');
	$("#manufacturer_id").val('');
	$("#manufacturer_list").val("").trigger('change.select2');
	$("#country_id").val('');
	$("#country_list").val("").trigger('change.select2');
		
    $("#reagentInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var reagentId 	    = $(el).closest("tr").find("#r_reagent_id").val();
	var receiveDate		= $(el).closest('tr').find("#r_receive_date").val();
	var receiveQty 		= $(el).closest('tr').find("#r_receive_qty").val();
	var unitId 			= $(el).closest("tr").find("#r_receive_unit_id").val();
	var unitName 		= $(el).closest("tr").find("#r_unit_name").val();
	//var receiveBy 	= $(el).closest('tr').find("#r_receive_by").val();
	var batchNo 		= $(el).closest("tr").find("#r_batch_no").val();
	var lotNo 			= $(el).closest("tr").find("#r_lot_no").val();
	var packSize 		= $(el).closest('tr').find("#r_pack_size").val();
	var purity 			= $(el).closest('tr').find("#r_purity").val();
	var mfgDate 		= $(el).closest('tr').find("#r_mfg_date").val();
	var expDate 		= $(el).closest('tr').find("#r_exp_date").val();
	var manufacturerId 	= $(el).closest("tr").find("#r_manufacturer_id").val();
	var countryId 		= $(el).closest('tr').find("#r_country_id").val();
	
	$("#id").val(Id);
	$("#reagent_id").val(reagentId);
	$("#reagent_list").val(reagentId).trigger('change.select2');
	$("#receive_qty").val(receiveQty);
	$("#receive_unit_id").val(unitId);
	$("#receive_unit_name").val(unitName);
	//$("#unit_list").val(unitId).trigger('change.select2');
	//$("#receive_by").val(receiveBy);
	//$("#receive_list").val(receiveBy).trigger('change.select2');
	$("#batch_no").val(batchNo);
	$("#lot_no").val(lotNo);
	$("#pack_size").val(packSize);
	$("#purity").val(purity);
	$('#receive_date').datepicker('setDate', convertMmDate(receiveDate));
	$('#mfg_date').datepicker('setDate', convertMmDate(mfgDate));
	$('#exp_date').datepicker('setDate', convertMmDate(expDate));
	$("#manufacturer_id").val(manufacturerId);
	$("#manufacturer_list").val(manufacturerId).trigger('change.select2');
	$("#country_id").val(countryId);
	$("#country_list").val(countryId).trigger('change.select2');
		
	$("#reagentInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#reagentReceiveInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#reagentReceiveInfoForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/reagent/save-receive-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#reagentInfoModal").modal('hide');
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