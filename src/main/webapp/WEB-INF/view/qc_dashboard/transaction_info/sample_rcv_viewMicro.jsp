<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<!------------------------ Start: Create Product Color Types Modal -------------------->


<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
<div class="modal-header bg-cyan">
    <table class="table table-bordered" style="border: 2px solid #ddd;">
    	<tbody style="border: 2px solid #ddd;">
    	<tr>
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Sample Receive Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-DIL-GN-081</td> 
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

                <form method="post" >
                 	<div class="modal-body">
                 		
                 		<div class="row">
                 			<div class="col-md-3">
                            	<span><b>NAME OF SAMPLE :</b></span>
                            	<div class="form-group">${info.materialName}</div>
                            </div>
	                 		<div class="col-md-3">
                            	<span><b>AR NO :</b></span>
                            	<div class="form-group">${info.arnNo}</div>
                            </div>
                            <div class="col-md-2">
                            	<span><b>SAMPLE ID :</b></span>
                            	<div class="form-group">${info.udSampleNo}</div>
                            </div>
	                 		<div class="col-md-2">
                            	<span><b> TYPE OF SAMPLE :</b></span>
                            	<div class="form-group"><c:out value="${info.materialTypeName}"/></div>
                            </div>
                            <div class="col-md-2">
                            	<span><b>TEST ANALYSIS :</b></span>
                            	<div class="form-group">
	                               <c:choose>
								<c:when test="${(info.isChemical eq 'Y') && (info.isMicrobiological eq 'Y')}">
	      						Chemical & Microbiological
	      						</c:when>
								<c:when test="${(info.isChemical eq 'Y') && (info.isMicrobiological ne 'Y')}">
	      							Chemical 
	     						 </c:when>
								<c:when test="${(info.isChemical ne 'Y') && (info.isMicrobiological eq 'Y')}">
	       						Microbiological 
	      						</c:when>
							</c:choose>
				                       
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
                 			<div class="col-md-3">
                            	<span><b>TIME OF RECEIVE :</b></span>
                            	<div class="form-group">
                            		<fmt:formatDate var="receiveDate" value="${info.receiveDate}" pattern="dd-MMM-yyyy HH:mm:ss aa"/>
									${receiveDate}
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
                            		${info.storageCondition}
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>SAMPLE DESCRIPTION :</b></span>
                            	<div class="form-group">
                            		${info.sampleRcvDesc}
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			
                 			
                 			<c:choose>
								  <c:when test="${info.providerType =='PT' || info.providerType =='AV'}">
								   <div class="col-md-3">
			                            	<span><b>TEST SCHEDULE :</b></span>
			                            	<div class="form-group">
			                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.schFromDate}" var="schFromDate" />
                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.schToDate}" var="schToDate" />
													${schFromDate} &nbsp; TO&nbsp;  ${schToDate} 
			                            	
			                            	</div>
			                            </div>
								  </c:when>
  
								  <c:otherwise>
									<div class="col-md-3">
                            	<span><b>REQUIRED TEST SAMPLE (MICROBIOLOGICAL) :</b></span>
                            	<div class="form-group">
                            	<c:if test="${not empty info.microSamAmt}">
                            		${info.microSamAmt}&nbsp;${info.matUnitName}
	                            </c:if>
                            	</div>
                            </div>
								  </c:otherwise>
								</c:choose>
                            
                            
                 		</div>
                 		<div class="row">
                 			
                            
                            
                            
                 		</div>
                 		
                 		<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">Storage Location :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-3">
										<span><b>MICROBIOLOGICAL SAMPLE RECEIVED QTY :</b></span>
										<c:if test="${not empty info.stMicroQty}">
										<div class="form-group">
											${info.stMicroQty} &nbsp;${info.stUnitName}
										</div>
										</c:if>
									</div>
									<div class="col-md-3">
										<span><b>ROOM NO :</b></span>
										<div class="form-group">
											${info.stRoomName}
										</div>
									</div>
									<div class="col-md-3">
										<span><b>INSTRUMENT ID :</b></span>
										<div class="form-group">
										${info.stEquipmentName}
										</div>
									</div>
									
									<div class="col-md-3">
										<span><b>RACK NO :</b></span>
										<div class="form-group">
											${info.stRackName}
										</div>
									</div>
								</div>
							</div>
						</div>
	                 </div>
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
	                 </div>
                 </form>

<style>
textarea {
    resize: none;
}
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
<script>
$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search from list.."
});

$("#qcSampleRecvInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#qcSampleRecvInfoForm").serialize();
    
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/qc/saveSampleRcvInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#qcInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "fail", 1000, false);
		  	}
	    });
    
    
});

</script>
