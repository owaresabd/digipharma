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
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Retention Sample Receive Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-QA-004</td>
    	</tr>
    	<tr style="border: 2px solid #ddd;">                 	
    	<td>Revision No.</td>
    	<td style="text-align:center;">00</td>
    	<td>Page 1 of 1</td>
    	</tr>
    	<tr style="border: 2px solid #ddd;">                 	
    	<td>Effective Date</td>
    	<td colspan="2">15 Jan 2020</td>
    	</tr>             	
    	</tbody>
    </table>
</div>
                 
                <form method="post" id="retSampleRecvInfoForm" modelAttribute="retSampleRecvInfo">
                 	<div class="modal-body">
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<input type="hidden" id="retReqId" name="retReqId" value=""/>
                 		<input type="hidden" id="sampleId" name="sampleId" value="${retenInfo.sampleId}"/>
                 		<div class="row">
                 		<div class="col-md-6">
                            	<span><b>NAME OF SAMPLE :</b></span>
                            	
                            	<div class="form-group">
	                              ${retenInfo.materialName}
				                       
                            	</div>
                            </div>
	                 		<div class="col-md-6">
                            	<span><b>SAMPLE ID :</b></span>
                            	
                            	<div class="form-group">
	                              ${retenInfo.udSampleNo}
				                       
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b> TYPE OF SAMPLE :</b></span>
                            	
                            	<div class="form-group">
	                              <c:out value="${retenInfo.materialTypeName}"/>
				                       
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>TEST ANALYSIS :</b></span>
                            	<div class="form-group">
	                               <c:choose>
								<c:when test="${(retenInfo.isChemical eq 'Y') && (retenInfo.isMicrobiological eq 'Y')}">
	      						Chemical & Microbiological
	      						</c:when>
								<c:when test="${(retenInfo.isChemical eq 'Y') && (retenInfo.isMicrobiological ne 'Y')}">
	      							Chemical 
	     						 </c:when>
								<c:when test="${(retenInfo.isChemical ne 'Y') && (retenInfo.isMicrobiological eq 'Y')}">
	       						Microbiological 
	      						</c:when>
							</c:choose>
				                       
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
                 		<div class="col-md-12">
                            	<span><b>REQUIRED RETENTION SAMPLE AMOUNT :</b></span>
                            	<div class="form-group">
                            	<c:if test="${not empty retenInfo.totalRetAmt}">
	                            	 <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${retenInfo.totalRetAmt}"/>&nbsp;${retenInfo.matUnitName}
                            	</c:if>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                 		<div class="col-md-12">
                            	<span><b>RETENTION SAMPLE DESCRIPTION :</b></span>
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
								<div class="col-md-2">
										<span><b>RCV. QTY :</b></span>
										<div class="form-group">
											<input type="text" id="ret_qty" name="retQty" value="" required="required" class="form-control number" placeholder="Receive qty" autocomplete="off" >
										</div>
									</div>
									<div class="col-md-2">
										<span><b>UNIT :</b></span>
										<div class="form-group">
											<input style="width: 100%;" type="text" id="unit_name" name="" value="${retenInfo.matUnitName}" class="align-center" readonly="readonly"/>
											<input style="width: 100%;" type="hidden" id="ret_unit_id" name="retUnitId" value="${retenInfo.unitId}"/>
											<%-- <select  id="ret_unit_id" name="retUnitId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Unit--</option>
				                        	<c:forEach var="unitInfo" items="${unitList}">
				                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
				                        	</c:forEach>
				                        	</select> --%>
										</div>
									</div>
									
									<div class="col-md-4">
										<span><b>EQUIPMENT ID :</b></span>
										<div class="form-group">
										<select  id="ret_equ_id" name="retEquId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Equipment ID--</option>
				                        	<c:forEach var="equpInfo" items="${storageEquipInfos}">
				                           	<option value="${equpInfo.id }">${equpInfo.equipmentId}</option>
				                        	</c:forEach>
				                        </select>
										</div>
									</div>
									<div class="col-md-2">
										<span><b>ROOM NO :</b></span>
										<div class="form-group">
											<select  id="ret_room_id" name="retRoomId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option>--Select Room No--</option>
				                        	<c:forEach var="roomInfo" items="${roomList}">
				                           	<option value="${roomInfo.id }">${roomInfo.roomName}</option>
				                        	</c:forEach>
				                        	</select>
										</div>
									</div>
									<div class="col-md-2">
										<span><b>RACK NO :</b></span>
										<div class="form-group">
											<select  id="ret_rack_id" name="retRackId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
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

$("#retSampleRecvInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#retSampleRecvInfoForm").serialize();
    
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/retention/saveSampleRcvInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#retInfoModal").modal('hide');
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
