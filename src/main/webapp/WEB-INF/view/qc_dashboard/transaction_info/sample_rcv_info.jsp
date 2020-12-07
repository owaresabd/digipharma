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
    	<tr style="border: 2px solid #ddd;">                 	
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Sample Receive Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-DIL-GN-081</td>
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
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<input type="hidden" id="qcReqId" name="qcReqId" value=""/>
                 		<input type="hidden" id="sampleId" name="sampleId" value="${qcInfo.sampleId}"/>
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
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
                            	${qcInfo.storageCondition}
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>REQUIRED TOTAL TEST SAMPLE :</b></span>
                            	<div class="form-group">
                            	<c:choose>
								  <c:when test="${qcInfo.typeCode =='PT' || qcInfo.typeCode =='AV'}">
								   N/A
								  </c:when>
  
								  <c:otherwise>
								   <c:if test="${not empty qcInfo.totalSamAmt}">
									<fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${qcInfo.totalSamAmt}"/>&nbsp;${qcInfo.matUnitName}
								    </c:if>
								  </c:otherwise>
								</c:choose>
                            	
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<c:if test="${not empty qcInfo.chemiSamAmt}">
                            <div class="col-md-6">
                            	<span><b>REQUIRED TEST SAMPLE (CHEMICAL) :</b></span>
                            	<div class="form-group">
                            	<c:choose>
								  <c:when test="${qcInfo.typeCode =='PT' || qcInfo.typeCode =='AV'}">
								   N/A
								  </c:when>
  
								  <c:otherwise>
								 <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${qcInfo.chemiSamAmt}"/>&nbsp;${qcInfo.matUnitName}
								   
								  </c:otherwise>
								</c:choose>
                            	
	                            	 
                            	
                            	</div>
                            </div>
                            </c:if>
                            <c:if test="${not empty qcInfo.microSamAmt}">
                            <div class="col-md-6">
                            	<span><b>REQUIRED TEST SAMPLE (MICROBIOLOGICAL) :</b></span>
                            	<div class="form-group">
                            	<c:choose>
								  <c:when test="${qcInfo.typeCode =='PT' || qcInfo.typeCode =='AV'}">
								   N/A
								  </c:when>
  
								  <c:otherwise>
								 <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${qcInfo.microSamAmt}"/>&nbsp;${qcInfo.matUnitName}
								   
								  </c:otherwise>
								</c:choose>
                            	
                            	</div>
                            </div>
                            </c:if>
                 		</div>
                 		<c:if test="${not empty qcInfo.keptAmount}">
                 		<div class="row">
                 			
                            <div class="col-md-6">
                            	<span><b>WORKING STANDARD KEPT AMOUNT :</b></span>
                            	<div class="form-group">
                            	<fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${qcInfo.keptAmount}"/>&nbsp;${qcInfo.matUnitName}
								
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		</c:if>
                 		<div class="row">
                 		<div class="col-md-12">
                            	<span><b>SAMPLE DESCRIPTION :</b></span>
                            	<div class="form-group">
	                            	<textarea rows="2" id="sample_desc" name="sampleDesc" class="form-control" placeholder="Description goes here......." ></textarea>
                            	</div>
                            </div>
                 		</div>
                 		<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">STORAGE LOCATION :</h1>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-3">
										<span><b>CHEMICAL QTY :</b></span>
										<div class="form-group">
										<c:choose>
											<c:when test="${(qcInfo.isChemical eq 'Y')}">
				      						<input type="text" id="st_chemi_qty" name="stChemiQty" onkeyup="totalAmount()" value="" class="form-control number" required="required" placeholder="Chemi qty" autocomplete="off" >
				      						</c:when>
											<c:when test="${(qcInfo.isChemical eq 'N')}">
				      						<input type="text" id="st_chemi_qty" name="stChemiQty" onkeyup="totalAmount()" value="" class="form-control number" readonly="readonly" placeholder="Chemi qty" autocomplete="off" >
				     						</c:when>
										</c:choose>
										</div>
									</div>
									<div class="col-md-3">
										<span><b>MICROBIOLOGICAL QTY :</b></span>
										<div class="form-group">
										<c:choose>
											<c:when test="${(qcInfo.isMicrobiological eq 'Y')}">
				      						<input type="text" id="st_micro_qty" name="stMicroQty" onkeyup="totalAmount()" value="" class="form-control number" required="required"  placeholder="Micro qty" autocomplete="off" >
				      						</c:when>
											<c:when test="${(qcInfo.isMicrobiological eq 'N')}">
				      							<input type="text" id="st_micro_qty" name="stMicroQty" onkeyup="totalAmount()" value="" class="form-control number" readonly="readonly" placeholder="Micro qty" autocomplete="off" >
				     						 </c:when>
										</c:choose>
										</div>
									</div>
									<div class="col-md-3">
										<span><b>TOTAL RECEIVE QTY :</b></span>
										<div class="form-group">
											<input type="text" id="st_qty" name="stQty" value="" class="form-control number" readonly="readonly" placeholder="Total Receive qty" autocomplete="off" >
										</div>
									</div>
									<div class="col-md-2">
										<span><b>UNIT :</b></span>
										<div class="form-group">
											<input style="width: 100%;" type="text" id="unit_name" name="" value="${qcInfo.matUnitName}" class="align-center" readonly="readonly"/>
											<input style="width: 100%;" type="hidden" id="st_unit_id" name="stUnitId" value="${qcInfo.unitId}"/>
											
										</div>
									</div>
								</div>	
								<div class="row">
									<div class="col-md-6">
										<span><b>EQUIPMENT ID :</b></span>
										<div class="form-group">
										<select  id="st_equ_id" name="stEquId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Equipment ID--</option>
				                        	<c:forEach var="equpInfo" items="${storageEquipInfos}">
				                           	<option value="${equpInfo.id }">${equpInfo.equipmentId}</option>
				                        	</c:forEach>
				                        </select>
										</div>
									</div>
									<div class="col-md-3">
										<span><b>ROOM NO :</b></span>
										<div class="form-group">
											<select  id="st_room_id" name="stRoomId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Room No--</option>
				                        	<c:forEach var="roomInfo" items="${roomList}">
				                           	<option value="${roomInfo.id }">${roomInfo.roomName}</option>
				                        	</c:forEach>
				                        	</select>
										</div>
									</div>
									<div class="col-md-3">
										<span><b>RACK NO :</b></span>
										<div class="form-group">
											<select  id="st_rack_id" name="stRackId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Rack No.--</option>
				                        	<c:forEach var="rackInfo" items="${rackList}">
				                           	<option value="${rackInfo.id }">${rackInfo.rackName}</option>
				                        	</c:forEach>
				                        	</select>
										</div>
									</div>
								</div>
							</div>
						</div>
						
	                 </div>
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span>RECEIVE</span>
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
$('.number').keypress(function (event) {
    return isNumber(event, this)
});
$(".js-example-theme-single").select2({
    theme: "classic"
});

function totalAmount(){
	var chemil=parseFloat($('#st_chemi_qty').val()).toFixed(2); 
    var micro= parseFloat($('#st_micro_qty').val()).toFixed(2); 
	
	 if(isNaN(chemil)){
		 chemil=0;
	   }
      if(isNaN(micro)){
    	  micro=0;
        }
		
	  var totalAmt=parseFloat(chemil)+parseFloat(micro);
	  $('#st_qty').val(parseFloat(totalAmt).toFixed(2));
}

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
