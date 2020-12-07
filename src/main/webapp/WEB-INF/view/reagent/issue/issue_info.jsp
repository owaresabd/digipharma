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
	    <span style="text-shadow: 2px 2px 2px #aaa;">REAGENT ISSUE INFORMATION</span>
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
								<th class="align-center">STOCK QTY</th>
								<th class="align-center">ISSUE QTY</th>
								<th class="align-center">LAB TYPE</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.reagentName}</td>
								<td class="align-center">${info.stockQty}&nbsp; ${info.issueUnitName}</td>
								<td class="align-center">${info.issueQty}&nbsp; ${info.issueUnitName}</td>
								<td class="align-center">
									<c:choose>
									    <c:when test="${info.labType =='C'}">
											<c:out value="Chemical"/>
									    </c:when>
									    <c:when test="${info.labType =='M'}">
											<c:out value="Microbiological"/>
									    </c:when>
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_reagent_id" value="${info.reagentId}">
									<input type="hidden" id="r_stock_qty" value="${info.stockQty}">
									<input type="hidden" id="r_issue_qty" value="${info.issueQty}">
									<input type="hidden" id="r_issue_unit_id" value="${info.issueUnitId}">
									<input type="hidden" id="r_unit_name" value="${info.issueUnitName}">
									<input type="hidden" id="r_lab_type" value="${info.labType}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
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
	
	<div class="modal fade" id="reagentIssueInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Reagent Issue Info</td>
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
                 
                 <form method="post" id="reagentIssueInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
                            <div class="col-md-5">
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
                            <div class="col-md-3">
                            	<span><b>STOCK QTY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="stock_qty" name="stockQty" readonly="readonly" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>UNIT NAME :</b></span>
                            	<div class="form-group">
	                                
				                    <input type="text" id="stock_unit_name" value="" style="width: 70%;" readonly="readonly"/>
				                    
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-5">
                            	<span><b>LAB TYPE :</b></span>
                            	<div class="form-group">
	                                <select  id="lab_list" class="js-example-theme-single form-control md" style="width: 100%;" required="required">
				                        <option>-Select Lab-</option>
				                        <option value="C">Chemical</option>
				                        <option value="M">Microbiological</option>
				                    </select>
				                    <input type="hidden" id="lab_type" name="labType" value=""/>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>ISSUED QTY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="issue_qty" name="issueQty" value="" class="form-control" style="width: 70%;" autocomplete="off" required="required">
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>UNIT NAME :</b></span>
                            	<div class="form-group">
	                                
				                    <input type="text" id="issue_unit_name" value="" style="width: 70%;" readonly="readonly"/>
				                    <input type="hidden" id="issue_unit_id" name="issueUnitId" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                            <div class="col-md-12">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
	                            	<textarea rows="2" id="remarks" name="remarks" maxlength="250" class="form-control" placeholder="Description goes here......." ></textarea>
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
	var link ="${pageContext.request.contextPath}/reagent/issue";
	$("#reagentIssueInfoModal").modal('hide');
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

$('.number').keypress(function (event) {
    return isNumber(event, this)
});

$("#reagent_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#reagent_id").val(value);
});
 
$("#lab_list").on('change', function() {
	var value = $('option:selected', this).val();
	$("#lab_type").val(value);
});

function reagentInfoById(id){
	var reagentId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/reagent/getReagentInfoById/" + reagentId, 
	function( data ) {

		$("#stock_unit_name").val(data.unitName);
		$("#issue_unit_name").val(data.unitName);
		$("#issue_unit_id").val(data.unitId);
		$("#stock_qty").val(data.stockQty);
		
		
		//$("#unit_list").val(data.unitId).trigger('change.select2');
	});
}

function add(el) {
	$("#id").val("");
	$("#reagent_id").val('');
	$("#reagent_list").val("").trigger('change.select2');
	$("#stock_qty").val("");
	$("#issue_qty").val("");
	$("#lab_type").val('');
	$("#lab_list").val("").trigger('change.select2');
	$("#stock_unit_name").val('');
	$("#issue_unit_name").val('');
	$("#issue_unit_id").val('');
	$("#remarks").val('');
		
    $("#reagentIssueInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var reagentId 	    = $(el).closest("tr").find("#r_reagent_id").val();
	var stockQty		= $(el).closest('tr').find("#r_stock_qty").val();
	var issueQty 		= $(el).closest('tr').find("#r_issue_qty").val();
	var unitId 			= $(el).closest("tr").find("#r_issue_unit_id").val();
	var unitName 		= $(el).closest("tr").find("#r_unit_name").val();
	var labType 		= $(el).closest("tr").find("#r_lab_type").val();
	var remarks			= $(el).closest("tr").find("#r_remarks").val();
	
	$("#id").val(Id);
	$("#reagent_id").val(reagentId);
	$("#reagent_list").val(reagentId).trigger('change.select2');
	$("#stock_qty").val(stockQty);
	$("#issue_qty").val(issueQty);
	$("#lab_type").val(labType);
	$("#lab_list").val(labType).trigger('change.select2');
	$("#stock_unit_name").val(unitName);
	$("#issue_unit_name").val(unitName);
	$("#issue_unit_id").val(unitId);
	$("#remarks").val(remarks);
		
	$("#reagentIssueInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#reagentIssueInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#reagentIssueInfoForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/reagent/save-issue-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#reagentIssueInfoModal").modal('hide');
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