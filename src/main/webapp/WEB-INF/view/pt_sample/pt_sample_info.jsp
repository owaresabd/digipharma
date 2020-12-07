<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">PT SAMPLE INFORMATION</span>
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
							    <th class="align-left"> SAMPLE NO.</th>
								<th class="align-left">NAME OF SAMPLE </th>
								<th class="align-left">REQUEST TYPE </th>
								<th class="align-center">TEST SCHEDULE </th>
								<th class="align-center">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${ptSampleList}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_type_name">${info.udSampleNo}</td>
								<td class="field_type_name">${info.materialName}</td>
								<td class="field_type_status align-left" >${info.reqTypeName}</td>
								<td class="field_type_status align-center" >
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.schFromDate}" var="schFromDate" />
                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.schToDate}" var="schToDate" />
													${schFromDate}&nbsp;To&nbsp;${schToDate} 
								
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									
									<c:if test="${info.status eq 'PT' || info.status eq 'AV'}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:if>
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
									<c:if test="${info.status eq 'PT' || info.status eq 'AV'}">
									<a class="btn-edit btn btn-xs" onclick="sendRequest(this)"><i class="material-icons">input</i></a>
									</c:if>
									<a class="btn-edit btn btn-xs" onclick="ptSampleInfoPrint('${info.id}','${info.udCssNo}')"><i class="material-icons">print</i></a>
									
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
<div class="modal fade" id="ptSampleInfoModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: PT Sample Info</td>
                 	<td>Document No.</td>
				    <td colspan="2">FM-PR-003</td>
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

                <form method="post" id="ptSampleInfoForm">
                 	<div class="modal-body ">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>REQUEST TYPE :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="reqTypeId" name="reqTypeId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="reqTypeInfo" items="${reqTypeList}">
				                           	<option value="${reqTypeInfo.id }">${reqTypeInfo.typeName}</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>SUPPLIER NAME :</b></span>
                            	
                            	<div class="form-group">
	                                <input style="width: 100%;" type="text" id="supplierName" name="supplierName" required="required" autocomplete="off" />
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TEST SCHEDULE :</b></span>
                            	
                            	<div class="form-group">
	                                <input style="width: 45%;" type="text" class="dates" id="fromDate" name="fromDate" required="required" placeholder="from Date" autocomplete="off"/> 
	                                <input style="width: 45%;" type="text" class="dates" id="toDate"  placeholder="to Date" name="toDate" required="required" autocomplete="off"/>
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
	                                <select id="materialType" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        	<option></option>
				                        <c:forEach var="typeInfo" items="${typeList}">
				                           	<option value="${typeInfo.id }">${typeInfo.typeName}</option>
				                        </c:forEach>
				                	</select>
				                	<input type="hidden" id="materialTypeId" name="materialTypeId" value=""/>
				                	<input type="hidden" id="typeCode" name="typeCode" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                        	<div class="col-md-12">
                           	<div class="form-group">
                            	<table id="reqTable" class="table table-bordered table-striped table-hover"> 
									<thead>
										<tr>
											<th class="align-center" style="width: 130px;">Item No.</th>
											<th class="align-center" style="width: 120px;">Lot No.</th>
											<th class="align-center" style="width: 100px;">Mfg. Date</th>
											<th class="align-center" style="width: 100px;">Exp. Date</th>
											<th class="align-center" style="width: 70px;">Quantity</th>
											<th class="align-center" style="width: 60px;">Unit</th>
											<th class="align-center" style="width: 70px;">Pack Size</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="batchNo" name="batchNo" class="align-center" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="batchLot" name="batchLot" class="align-center" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="mfgDate" name="mfgDate" class="align-center dates" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="expDate" name="expDate" class="align-center dates" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="quantity" name="quantity" class="align-center" required="required" />
											</td>
											<td class="align-center">
												<select id="unitId" name="unitId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="info" items="${unitList}">
				                           	<option value="${info.id }">${info.unitCode}</option>
				                        </c:forEach>
				                	</select>
											
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="packSize" name="packSize" class="align-center" placeholder="Pack Size"/>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
                           	</div>
                		</div>
                		<div class="row">
                		<div class="col-md-4">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
	                                <select  id="storageConId" name="storageConId" class="js-example-theme-single form-control" style="width: 100%;">
	                       	<option></option>
	                	<c:forEach var="info" items="${conditionList}">
	                    	<option value="${info.id }">${info.storageDesc}</option>
	                    </c:forEach>
	                </select>
                            	</div>
                            </div>
                            
                            </div>
                		<div class="row">
	                 		<div class="col-md-12">
                            	<span><b>SAMPLE DETAILS :</b></span>
                            	<div class="form-group">
	                                <textarea rows="2" id="sampleDetails" name="sampleDetails" class="form-control" ></textarea>
                            	</div>
                            </div>
                		</div>
                		<div class="row">
	                 		<div class="col-md-12">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
	                                <textarea rows="2" id="remarks" name="remarks" class="form-control" ></textarea>
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
     
     <div class="modal fade" id="ptInfoModal" style="overflow: auto;"  role="dialog" data-backdrop="static" data-keyboard="false">
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
	var link =  "${pageContext.request.contextPath}/pt_sample/maintain";
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
  $( ".dates" ).datepicker({
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

function materialInfoById(id){
	var materialId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/material/getMaterialInfoById/" + materialId, 
	function( data ) {
		
		$("#materialCode").val(data.materialCode);
		$('#materialType').val(data.materialTypeId).trigger('change.select2');
		 $("#materialTypeId").val(data.materialTypeId);
		 $("#typeCode").val(data.typeCode);
	});
}





function add(el) {
		
	 $("#id").val("");
	 $('#reqTypeId').val('').trigger('change.select2'); 
	 $("#supplierName").val("");
	 $("#fromDate").val("");
	 $("#toDate").val("");
	 $('#materialId').val('').trigger('change.select2');
	 $("#materialCode").val("");
	 $('#materialType').val('').trigger('change.select2');
	 $("#materialTypeId").val("");
	 $("#typeCode").val("");
	 $("#batchNo").val('');
	 $("#batchLot").val('');
	 $("#mfgDate").val('');
	 $("#expDate").val('');
	 $("#quantity").val('');
	 $('#unitId').val('');
	 $('#unitName').val('');
	 $('#packSize').val('');
	 $('#storageConId').val('').trigger('change.select2');
	 $('#sampleDetails').val('');
	 $('#remarks').val('');
	 
	$("#ptSampleInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}






$("#ptSampleInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#ptSampleInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/pt_sample/save-ptSample-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#ptSampleInfoModal").modal('hide');
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
		url : "${pageContext.request.contextPath}/qc/getPtQcInfo/" + id ,
		success : function(data) {
			$('#ptInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$(".md").css("display", "none");
			
			}
		});
}
function sendRequest(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/qc/getPtQcInfo/" + id ,
		success : function(data) {
			$('#ptInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			
			}
		});
}

function edit(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/pt_sample/getPtSampleInfo/" + id ,
		success : function(data) {
			$('#ptInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			}
		});
}

function ptSampleInfoPrint(id,udId) {
	var ajaxURL = "${pageContext.request.contextPath}/pt_sample/request-info-print?requestId="+id+'&udRequestId='+udId;
	
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
