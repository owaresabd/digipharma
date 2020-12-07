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
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Work Distribution Information</td>

    	<td>Document No.</td>
    	<td colspan="2">FM-DIL-GN-082</td>
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
                <form method="post" id="qcSampleRecvInfoForm" modelAttribute="qcSampleRecvInfo">
                 	<div class="modal-body">
                 		<input type="hidden" id="sampleRcvId" name="id" value=""/>
                 		<input type="hidden" id="sampleId" name="sampleId" value="${info.sampleId}"/>
                 		<div class="row">
                            <div class="col-md-3">
                            	<span><b>NAME OF SAMPLE :</b></span>
                            	<div class="form-group">
	                                ${info.materialName}
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>ANALYTICAL REFERENCE NO :</b></span>
                            	<div class="form-group">
	                                ${info.arnNo}
                            	</div>
                            </div>
                            
                 		
                            <div class="col-md-3">
                            	<span><b>SAMPLE ID :</b></span>
                            	<div class="form-group">
	                                ${info.udSampleNo}
                            	</div>
                            </div>
                            <div class="col-md-3">
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
                 		<c:if test="${info.providerType eq 'PT' || info.providerType eq 'AV'}">
                 			<div class="col-md-3">
	                            	<span><b>TEST SCHEDULE :</b></span>
	                            	<div class="form-group">
	                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.schFromDate}" var="schFromDate" />
                            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.schToDate}" var="schToDate" />
													${schFromDate} &nbsp; TO&nbsp;  ${schToDate} 
		                            	
	                            	</div>
	                         </div>
	                         
	                         </c:if>
	                 		<div class="col-md-9">
	                            	<span><b>SAMPLE DESCRIPTION :</b></span>
	                            	<div class="form-group">
	                            	${info.sampleRcvDesc}
		                            	
	                            	</div>
	                         </div>
                 		</div>
                 		<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">DESCRIPTION OF TEST PARAMETERS :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
                         <div class="col-md-12">
                           <div class="form-group">
                            	<table id="reqTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
												  
													<th class="align-center" style="width: 100px;">Test parameter No</th>
													<th class="align-center" style="width: 100px;">Test Parameter</th>
													<th class="align-center" style="width: 100px;">Specification</th>
													<th class="align-center" style="width: 100px;">LOD</th>
													<th class="align-center" style="width: 100px;">Reference</th>
													<th class="align-center" style="width: 60px;">Test Method</th>
													<th class="align-center" style="width: 150px;">Equipment ID</th>
													<th class="align-center" style="width: 130px;">Analyst</th>
													
												</tr>
												</thead>
												<tbody>
												<!-- Below Code for Chemical Parameter -->
												<c:choose>
												<c:when test="${(info.isChemical eq 'Y')}">
	      						
	      						
												<c:forEach var="chemiInfo" items="${chemicalInfos}" varStatus="counter">
												<tr>
												
													<td class="align-center">
													<input type="hidden" value="${chemiInfo.chemiParameterNo}" name="distParameterNo">
													${chemiInfo.chemiParameterNo}
													</td>
													<td class="align-center">
													<input type="hidden" value="${chemiInfo.chemiParameterId}" name="distParameterId">
														${chemiInfo.chemiParameterName}
													</td>
													<td class="align-center">
													<input type="hidden" value="${chemiInfo.chemiSpecification}" name="distSpecification">
														${chemiInfo.chemiSpecification}
													</td>
													<td class="align-center">
													<input type="hidden" value="${chemiInfo.chemiLod}" name="distLod">
														${chemiInfo.chemiLod}
													</td>
													<td class="align-center">
													<input type="hidden" value="${chemiInfo.chemiReferenceId}" name="distReferenceId">
														${chemiInfo.chemiReferenceName}
													</td>
													<td class="align-center">
													<input type="hidden" value="${chemiInfo.chemiTestUnitId}" name="distTestUnitId">
													<input type="hidden" value="${chemiInfo.chemiMethodId}" name="distMethodId">
															${chemiInfo.chemiMethodName}
													</td>
													<td class="align-center">
														<select  id="distEcuipmentId${chemiInfo.chemiParameterId}" onselect="setSelectVal(this.id)" class="js-example-theme-single form-control" style="width: 100%;" required="required" multiple="multiple">
														<option></option>
														<c:forEach var="equipInfo" items="${analyzerEquipInfos}">
															<option value="${equipInfo.id }">${equipInfo.equipmentId}</option>
														</c:forEach>
														</select>
														<input class="distEcuipmentHId" id="distEcuipmentId${chemiInfo.chemiParameterId}H" type="hidden" name="distEcuipmentId" value="">
													</td>
													<td class="align-center">
														<select id="distAnalystId" name="distAnalystId" class="js-example-theme-single form-control" style="width:100%" required>
								                        	<option></option>
								                         <c:forEach var="byInfo" items="${sampleByList}">
								                           	<option value="${byInfo.empId }">[${byInfo.udEmpNo}] ${byInfo.empName}</option>
								                        </c:forEach>
								                        </select>
													</td>
													
												</tr>
												</c:forEach>
												</c:when>
												</c:choose>
												
											
											
											</tbody>


											</table>
					</div>
                            </div>   
                            
                 		</div>
							</div>
						</div>
						
	                 </div>
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span>DISTRIBUTE</span>
						</button>
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
    theme: "classic"
});
//Set option selected onchange
//function setSelectVal(id){
	//alert(" ID :"+id);
$('.js-example-theme-single').change(function(){
  var value = $(this).val();
  var valueId = $(this).attr('id');
  var equipmentVal = value.toString().split(',').join('@');
  $('#'+valueId+'H').val(equipmentVal);
});
//}
$("#qcSampleRecvInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#qcSampleRecvInfoForm").serialize();
    
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/distribution/saveDistributionInfo",
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
