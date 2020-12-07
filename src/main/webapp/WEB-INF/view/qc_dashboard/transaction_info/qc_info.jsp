<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!------------------------ Start: Create Product Color Types Modal -------------------->


<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
<div class="modal-header bg-cyan">
    <table class="table table-bordered" style="border: 2px solid #ddd;">
    	<tbody style="border: 2px solid #ddd;">
    	<tr style="border: 2px solid #ddd;">                 	
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Sample Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-QA-002</td>
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

                <form method="post">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="whRequestId" name="whRequestId" value="${qcInfo.id}"/>
                 		<div class="row">
                 		<div class="col-md-6">
                            	<span><b>NAME OF SAMPLE :</b></span>
                            	<div class="form-group">
	                              ${qcInfo.materialName}
                            	</div>
                            </div>
	                 		<div class="col-md-6">
                            	<span><b>SAMPLE ID :</b></span>
                            	<div class="form-group">
	                              ${qcInfo.udSampleNo}
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b> TYPE OF SAMPLE :</b></span>
                            	<div class="form-group">
	                              <c:out value="${qcInfo.materialTypeName}"/>
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>TEST ANALYSIS :</b></span>
                            	<div class="form-group">
	                               <c:choose>
								<c:when test="${(qcInfo.isChemical eq 'Y') && (qcInfo.isMicrobiological eq 'Y')}">
	      						Chemical & Microbiological
	      						</c:when>
								<c:when test="${(qcInfo.isChemical eq 'Y') && (qcInfo.isMicrobiological ne 'Y')}">
	      							Chemical 
	     						 </c:when>
								<c:when test="${(qcInfo.isChemical ne 'Y') && (qcInfo.isMicrobiological eq 'Y')}">
	       						Microbiological 
	      						</c:when>
							</c:choose>
                            	</div>
                            </div>
                            
                 		</div>
                 		
                		<div class="row">
                 			<div class="col-md-6">
                            	<span><b>SAMPLE SENT BY :</b></span>
	                            	<div class="form-group">CSD</div>
                            </div>
	                 		<div class="col-md-6">
                            	<span><b>TYPE OF REQUEST :</b></span>
                            	<div class="form-group">
	                              ${qcInfo.reqTypeName}
                            	</div>
                            </div>                            
                 		</div>
                 		
                 		<div class="row">
                 			<div class="col-md-6">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">${qcInfo.storageCondition}</div>
                            </div>
	                 		<div class="col-md-6">
                            	<span><b>SAMPLE DESCRIPTION :</b></span>
                            	<div class="form-group">${qcInfo.remarks}</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-6">
                            	<span><b>REQUIRED TOTAL TEST SAMPLE :</b></span>
                            	<div class="form-group">
                            	<c:if test="${not empty qcInfo.totalSamAmt}">
	                            	 <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${qcInfo.totalSamAmt}"/>&nbsp;${qcInfo.matUnitName}
                            	</c:if>
                            	</div>
                            </div>
                            <c:if test="${not empty qcInfo.chemiSamAmt}">
                            <div class="col-md-6">
                            	<span><b>REQUIRED TEST SAMPLE (CHEMICAL) :</b></span>
                            	<div class="form-group">
                            	<c:if test="${not empty qcInfo.chemiSamAmt}">
	                            	 ${qcInfo.chemiSamAmt}&nbsp;${qcInfo.matUnitName}
                            	</c:if>
                            	</div>
                            </div>
                            </c:if>
                 		</div>
                 		<div class="row">
                            <c:if test="${not empty qcInfo.microSamAmt}">
                            <div class="col-md-6">
                            	<span><b>REQUIRED TEST SAMPLE (MICROBIOLOGICAL) :</b></span>
                            	<div class="form-group">
                            	<c:if test="${not empty qcInfo.microSamAmt}">
	                            	 ${qcInfo.microSamAmt}&nbsp;${qcInfo.matUnitName}
                            	</c:if>
                            	</div>
                            </div>
                            </c:if>
                 		</div>
	                 </div>
	                 
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
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

