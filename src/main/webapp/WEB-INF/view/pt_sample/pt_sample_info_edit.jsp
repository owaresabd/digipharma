<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<spring:message code=""/>
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

                <form method="post" id="ptUpdateSampleInfoForm">
                 	<div class="modal-body ">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="whRequestId" name="whRequestId" value="${qcInfo.whRequestId}"/>
                 		<input type="hidden" id="cssRequestId" name="cssRequestId" value="${qcInfo.cssRequestId}"/>
                 		<input type="hidden" id="sampleId" name="sampleId" value="${qcInfo.sampleId}"/>
                 		<input type="hidden" id="qcReqId" name="qcReqId" value="${qcInfo.id}"/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>REQUEST TYPE :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="reqTypeIdPt" name="reqTypeId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="reqTypeInfo" items="${reqTypeList}">
				                         <c:choose>
					                        <c:when test="${ reqTypeInfo.id eq qcInfo.reqType}">
					                           	<option selected="${reqTypeInfo.id }" value="${reqTypeInfo.id }">${reqTypeInfo.typeName}</option>
					                           	</c:when>
					                          <c:otherwise>
					                          	<option value="${reqTypeInfo.id }">${reqTypeInfo.typeName}</option>
					                          </c:otherwise>
				                          
				                          </c:choose> 	
				                        </c:forEach>
				                        </select>
				                        
				                        
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>SUPPLIER NAME :</b></span>
                            	
                            	<div class="form-group">
	                                <input style="width: 100%;" type="text" id="supplierName" name="supplierName" value="${qcInfo.clientName }" required="required" autocomplete="off"/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TEST SCHEDULE :</b></span>
                            	
                            	<div class="form-group">
                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${qcInfo.schFromDate}" var="schFromDate" />
                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${qcInfo.schToDate }" var="schToDate" />
	                                <input style="width: 45%;" type="text" class="dates" id="fromDate" name="fromDate" required="required" value="${schFromDate}" autocomplete="off"/> 
	                                <input style="width: 45%;" type="text" class="dates" id="toDate"  value="${schToDate}" name="toDate" required="required" autocomplete="off"/>
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>NAME OF SAMPLE :</b></span>
                            	<div class="form-group">
                            		<select onchange="materialInfoByIdPt(this.id)" id="materialIdPt" name="materialId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="matInfo" items="${materialList}">
				                        
				                        <c:choose>
				                        <c:when test="${ matInfo.id eq qcInfo.materialId}">
				                           	
				                           	<option selected="selected" value="${matInfo.id}">${matInfo.materialName}</option>
				                           	</c:when>
				                          <c:otherwise>
				                          <option value="${matInfo.id}">${matInfo.materialName}</option>
				                          
				                          </c:otherwise>
				                          
				                          </c:choose> 
				                           	
				                        </c:forEach>
				                    </select>
	                                
                            	</div>
                            </div>
                             <div class="col-md-4">
                            	<span><b>SAMPLE CODE NO :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="materialCodePt" name="materialCode"  class="form-control" value="${qcInfo.materialCode}" readonly="readonly">
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>SAMPLE TYPE :</b></span>
                            	<div class="form-group">
	                                <select id="materialTypePt" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        	<option></option>
				                        <c:forEach var="typeInfo" items="${typeList}">
				                        
				                        <c:choose>
				                        <c:when test="${typeInfo.id eq qcInfo.materialTypeId}">
				                           	
				                           <option selected="selected" value="${typeInfo.id }">${typeInfo.typeName}</option>
				                           	</c:when>
				                          <c:otherwise>
				                          <option value="${typeInfo.id }">${typeInfo.typeName}</option>
				                          
				                          </c:otherwise>
				                          
				                          </c:choose> 
				                           	
				                        </c:forEach>
				                	</select>
				                	<input type="hidden" id="materialTypeIdPt" name="materialTypeId" value="${qcInfo.materialTypeId}"/>
				                	<input type="hidden" id="typeCodePt" name="typeCode" value="${qcInfo.typeCode}"/>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                        	<div class="col-md-12">
                           	<div class="form-group">
                            	<table id="detailsTable" class="table table-bordered table-striped table-hover"> 
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
									<c:forEach var="detailInfo" items="${whReqDetailsList}">
										<tr>
											<td class="align-center">
											<input type="hidden" id="whRequestDetailId" name="whRequestDetailId" value="${detailInfo.childId}"/>
												<input style="width: 100%;" type="text" id="batchNo" name="batchNo" value="${detailInfo.batchNo}" class="align-center" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="batchLot" name="batchLot" value="${detailInfo.batchLot}" class="align-center" />
											</td>
											<td class="align-center">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${detailInfo.mfgDate}" var="mfgDate" />
													
												<input style="width: 100%;" type="text" id="mfgDate" name="mfgDate" value="${mfgDate}" class="align-center dates" />
											</td>
											<td class="align-center">
											<fmt:formatDate pattern="dd-MMM-yyyy" value="${detailInfo.expDate}" var="expDate" />
													
												<input style="width: 100%;" type="text" id="expDate" name="expDate" value="${expDate}" class="align-center dates" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="quantity" name="quantity" value="${detailInfo.quantity}" class="align-center" required="required" />
											</td>
											<td class="align-center">
												<select id="unitId" name="unitId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="unitInfo" items="${unitList}">
				                         <c:choose>
				                        <c:when test="${ unitInfo.id eq detailInfo.unitId}">
				                           	
				                           	<option selected="selected" value="${unitInfo.id }">${unitInfo.unitCode}</option>
				                           	</c:when>
				                          <c:otherwise>
				                          <option value="${unitInfo.id }">${unitInfo.unitCode}</option>
				                          
				                          </c:otherwise>
				                          
				                          </c:choose>
				                        </c:forEach>
				                	</select>
											
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="packSize" name="packSize" value="${detailInfo.packSize}" class="align-center" placeholder="Pack Size"/>
											</td>
										</tr>
										
										</c:forEach>
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
	                	<c:forEach var="conInfo" items="${conditionList}">
	                    	
	                    	<c:choose>
		                        <c:when test="${conInfo.id eq qcInfo.storageConId}">
		                           	<option selected="selected" value="${conInfo.id }">${conInfo.storageDesc}</option>
		                           	</c:when>
		                          <c:otherwise>
		                          	<option value="${conInfo.id }">${conInfo.storageDesc}</option>
		                          </c:otherwise>
				                          
				            </c:choose>
	                    </c:forEach>
	                </select>
                            	</div>
                            </div>
                            
                            </div>
                		<div class="row">
	                 		<div class="col-md-12">
                            	<span><b>SAMPLE DETAILS :</b></span>
                            	<div class="form-group">
	                                <textarea rows="2" id="sampleDetails" name="sampleDetails" class="form-control" >${qcInfo.sampleDetails }</textarea>
                            	</div>
                            </div>
                		</div>
                		<div class="row">
	                 		<div class="col-md-12">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
	                                <textarea rows="2" id="remarks" name="remarks" class="form-control" >${qcInfo.remarks }</textarea>
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
<%-- <script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script> --%>		
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


function materialInfoByIdPt(id){
	var materialId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/material/getMaterialInfoById/" + materialId, 
	function( data ) {
		
		$("#materialCodePt").val(data.materialCode);
		$('#materialTypePt').val(data.materialTypeId).trigger('change.select2');
		 $("#materialTypeIdPt").val(data.materialTypeId);
		 $("#typeCodePt").val(data.typeCode);
	});
}












$("#ptUpdateSampleInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#ptUpdateSampleInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/pt_sample/update-ptSample-info",
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





	
</script>
