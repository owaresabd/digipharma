<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!------------------------ Start: Create Product Color Types Modal -------------------->

<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
<div class="modal-header bg-cyan">
    <table class="table table-bordered" style="border: 2px solid #ddd;">
    	<tbody style="border: 2px solid #ddd;">
    	<tr>
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Sample Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-QA-001</td>
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

<form method="post" id="requestInfoForm" onkeypress="if (event.keyCode == 13) { return false; }">
                 	<div class="modal-body ">
                 	<div class="alert-code alert-block alert-danger hidden"></div><br>
                 	
                 		<input type="hidden" id="cssRequestId" name="cssRequestId" value="${cssReqInfo.id}"/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>SERVICE REQ. NO :</b></span>
                            	
                            	<div class="form-group">${cssReqInfo.udCssNo}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>DATE &amp; TIME :</b></span>
                            	
                            	<div class="form-group">${cssReqInfo.reqDate}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>REQUEST TYPE :</b></span>
                            	
                            	<div class="form-group">${cssReqInfo.reqTypeName}</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>NAME OF MATERIALS :</b></span>
                            	
                            	<div class="form-group">${cssReqInfo.materialName}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MATERIALS CODE NO. :</b></span>
                            	
                            	<div class="form-group">${cssReqInfo.materialCode}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TYPE OF MATERIALS :</b></span>
                            	
                            	<div class="form-group">${cssReqInfo.materialTypeName}</div>
                            </div>
                 		</div>
                 		<c:if test="${not empty cssReqInfo.manufactureName}">
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>MANUFACTURER/ SOURCE :</b></span>
                            	<div class="form-group">${cssReqInfo.manufactureName}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>COUNTRY/ORIGIN :</b></span>
                            	<div class="form-group">${cssReqInfo.countryName}</div>
                            </div>
                        </div>    
                        </c:if>    
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<c:choose>
    <c:when test="${cssReqInfo.typeCode eq 'F'}">
       <table id="reqTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 130px;">Batch No.</th>
													<th class="align-center" style="width: 120px;">Lot No.</th>
													<th class="align-center" style="width: 150px;">Mfg. Date</th>
													<th class="align-center" style="width: 150px;">Exp. Date</th>
													<th class="align-center" style="width: 60px;">Batch Size</th>
													<th class="align-center" style="width: 60px;">Unit</th>
													<th class="align-center" style="width: 70px;">Pack Size</th>
												</tr>
												</thead>
												<tbody>
												<c:forEach var="info" items="${cssReqInfoList}">
												<tr>
													<td class="align-center">${info.batchNo}</td>
													<td class="align-center">${info.batchLot}</td>
													<td class="align-center">
														<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.mfgDate}" var="mfgDate" />
														${mfgDate}
													</td>
													<td class="align-center">
														<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.expDate}" var="expDate" />
														${expDate}
													</td>
													<td class="align-center">${info.quantity}</td>
													<td class="align-center">${info.unitName}</td>
													<td class="align-center">${info.packSize}</td>
												</tr>
												</c:forEach>
												</tbody>
											</table>
    </c:when>    
    <c:otherwise>
       <table id="reqTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th class="align-center" style="width: 130px;">Batch No.</th>
													<th class="align-center" style="width: 120px;">Lot No.</th>
													<th class="align-center" style="width: 150px;">Mfg. Date</th>
													<th class="align-center" style="width: 150px;">Exp. Date</th>
													<th class="align-center" style="width: 60px;">Qty</th>
													<th class="align-center" style="width: 60px;">Unit</th>
													<th class="align-center" style="width: 130px;">No. of Drums</th>
												</tr>
												</thead>
												<tbody>
												<c:forEach var="info" items="${cssReqInfoList}">
												<tr>
													<td class="align-center">${info.batchNo}</td>
													<td class="align-center">${info.batchLot}</td>
													<td class="align-center">
														<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.mfgDate}" var="mfgDate" />
														${mfgDate}
													</td>
													<td class="align-center">
														<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.expDate}" var="expDate" />
														${expDate}
													</td>
													<td class="align-center">${info.quantity}</td>
													<td class="align-center">${info.unitName}</td>
													<td class="align-center">${info.noOfDrums}</td>
												</tr>
												</c:forEach>
												</tbody>
											</table>
       
    </c:otherwise>
</c:choose>
					</div>
				</div>
			</div>
			<div class="row">
	        	<div class="col-md-3">
                	<span><b>Analysis :</b></span>
                	<div class="form-group">
						<c:choose>
							<c:when test="${(cssReqInfo.isChemical eq 'Y') && (cssReqInfo.isMicrobiological eq 'Y')}">
      						Chemical & Microbiological
      						</c:when>
							<c:when test="${(cssReqInfo.isChemical eq 'Y') && (cssReqInfo.isMicrobiological ne 'Y')}">
      							Chemical 
     						 </c:when>
							<c:when test="${(cssReqInfo.isChemical ne 'Y') && (cssReqInfo.isMicrobiological eq 'Y')}">
       						Microbiological 
      						</c:when>

						</c:choose>
					</div>
                </div>
                <div class="col-md-3">
                   	<span><b>Required Test Sample Amount (Not less than) :</b></span>
                   	
                   	<div class="form-group">
                   	<c:if test="${not empty cssReqInfo.totalSamAmt}">
                      <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${cssReqInfo.totalSamAmt}"/>&nbsp;${cssReqInfo.matUnitName} 
                   	</c:if>
                   	</div>
                </div>
                <c:choose>
                <c:when test="${cssReqInfo.typeCode eq 'W'}">
                <div class="col-md-3">
                   	<span><b>Kept Amount :</b></span>
                   	
                   	<div class="form-group">
                   	<c:if test="${not empty cssReqInfo.keptAmount}">
                      <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${cssReqInfo.keptAmount}"/>&nbsp;${cssReqInfo.matUnitName} 
                   	</c:if>
                   	</div>
                </div>
                
                </c:when>
                <c:otherwise>
                <div class="col-md-3">
                   	<span><b>Required Retention Sample Amount (Not less than) :</b></span>
                   	
                   	<div class="form-group">
                   	<c:if test="${not empty cssReqInfo.totalRetAmt}">
                      <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${cssReqInfo.totalRetAmt}"/>&nbsp;${cssReqInfo.matUnitName} 
                   	</c:if>
                   	</div>
                </div>
                </c:otherwise>
                
                </c:choose>
                <div class="col-md-3">
					<span><b>Sample Details :</b></span>
					<div class="form-group">
						<c:out value="${cssReqInfo.sampleDetails}" />
					</div>
				</div>
            </div>
			
			<div class="row">
           		<div class="col-md-4">
                  	<span><b>Client Name :</b></span>
                  	<div class="form-group">
                    	<select onchange="clientInfoById(this.id)" id="clientName" class="js-example-theme-single form-control" style="width: 100%;">
	                        	<option></option>
	                        <c:forEach var="info" items="${clientList}">
	                           	<option value="${info.id }">${info.clientName}</option>
	                        </c:forEach>
	                    </select>
	                    <input type="hidden" id="client_name" name="clientName" value=""/>
                  	</div> 
             	</div>
                  
               <div class="col-md-4">
	               <span><b>Client ID. :</b></span>
	               <div class="form-group">
	               	<input type="text" id="clientId" name="clientId" value="" style="width:100%" class="form-control sentenceCase" autocomplete="on" readonly="readonly">
	               </div>
               </div>
	           <div class="col-md-4">
	           <span><b>Storage Condition :</b></span>
	           	<div class="form-group">
	            	<select  id="storageConId" name="storageConId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
	                       	<option></option>
	                	<c:forEach var="info" items="${conditionList}">
	                    	<option value="${info.id }">${info.storageDesc}</option>
	                    </c:forEach>
	                </select>
	                <input type="hidden" id="storageCondition" name="storageCondition" value=""/>
	           	</div>
	           </div>
          	</div>
			<c:choose>
  <c:when test="${cssReqInfo.typeCode eq 'F'}">
   <div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Sampling Plan :</b></span>
                            	
                            	<div class="form-group">
	                            <select  id=methodId name="methodId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="methodInfo" items="${methodList}">
			                           	<option value="${methodInfo.id }">${methodInfo.methodName}</option>
			                        </c:forEach>
				                </select>
                            	</div>
                            </div>
                            
                            <div class="col-md-3">
                            	<span><b>Sampling Procedure :</b></span>
                            	<div class="form-group">
	                            	<select id="sampleProcedure"   name="sampleProcedure" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        	<option value="N">Normal</option>
			                        	<option value="A">Aseptic</option>
			                        	<option value="0">N/A</option>
				                    </select>
                            	</div>
                            </div>
                            <div class="col-md-3" id="sampleBooth">
                            	<span><b>Sampling Booth No. :</b></span>
                            	<div class="form-group">
	                            	<select   class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                       	<option></option>
				                        <c:forEach var="boothInfo" items="${boothList}">
				                           	<option value="${boothInfo.id }">${boothInfo.boothName}</option>
				                        </c:forEach>
				                    </select>
				                    
				                    <input type="hidden" id="boothId" name="areaBoothId" value=""> 
                            	</div>
                            </div>
                            <div class="col-md-3" id="sampleArea" style="display: none">
                            	<span><b>Sampling Area :</b></span>
                            	<div class="form-group">
	                            	<select   class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        <option></option>
				                        <c:forEach var="areaInfo" items="${areaList}">
				                           	<option value="${areaInfo.id }">${areaInfo.areaName}</option>
				                        </c:forEach>
				                    </select>
				                     <input type="hidden" id="areaId" name="areaBoothId" value=""> 
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Sampling Equipment ID :</b></span>
                            	<div class="form-group">
	                              <select   class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        <option></option>
				                        <c:forEach var="equipInfo" items="${samEquipInfos}">
				                           	<option value="${equipInfo.id }">${equipInfo.equipmentId}</option>
				                        </c:forEach>
				                        </select>
				                        
				                        <input type="hidden" id="equipmentId" name="equipmentId" value=""> 
				                       
                            	</div>
                            </div>
                 		</div>
  </c:when>
  
  <c:otherwise>
   <div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Sampling Plan :</b></span>
                            	
                            	<div class="form-group">
	                            <select  id=methodId name="methodId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        <c:forEach var="methodInfo" items="${methodList}">
			                           	<option value="${methodInfo.id }">${methodInfo.methodName}</option>
			                        </c:forEach>
				                </select>
                            	</div>
                            </div>
                            
                            <div class="col-md-3">
                            	<span><b>Sampling Procedure :</b></span>
                            	<div class="form-group">
	                            	<select  id="sampleProcedure" name="sampleProcedure" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        	<option></option>
			                        	<option value="N">Normal</option>
			                        	<option value="A">Aseptic</option>
			                        	<option value="0">N/A</option>
				                    </select>
                            	</div>
                            </div>
                            <div class="col-md-3" id="sampleBooth">
                            	<span><b>Sampling Booth No. :</b></span>
                            	<div class="form-group">
	                            	<select  id="boothId" name="areaBoothId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                       	<option></option>
				                        <c:forEach var="boothInfo" items="${boothList}">
				                           	<option value="${boothInfo.id }">${boothInfo.boothName}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                            <div class="col-md-3" id="sampleArea" style="display: none">
                            	<span><b>Sampling Area :</b></span>
                            	<div class="form-group">
	                            	<select  id="areaId" name="areaBoothId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        <option></option>
				                        <c:forEach var="areaInfo" items="${areaList}">
				                           	<option value="${areaInfo.id }">${areaInfo.areaName}</option>
				                        </c:forEach>
				                    </select>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Sampling Equipment ID :</b></span>
                            	<div class="form-group">
	                              <select  id="equipmentId" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        <option></option>
				                        <c:forEach var="equipInfo" items="${samEquipInfos}">
				                           	<option value="${equipInfo.id }">${equipInfo.equipmentId}</option>
				                        </c:forEach>
				                        </select>
                            	</div>
                            </div>
                            
                 		</div>
  </c:otherwise>
</c:choose>
			
                 		<div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Work Instruction No :</b></span>
                            	
                            	<div class="form-group">
	                             <select  id="workInsId" name="workInsId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="insInfo" items="${instructionList}">
				                           	<option value="${insInfo.id }">${insInfo.insUdId}</option>
				                        </c:forEach>
				                        </select>
				                       
                            	</div>
                            </div>
                            
                            <div class="col-md-3">
                            	<span><b>Special Precautions Taken :</b></span>
                            	
                            	<div class="form-group">
	                             <input type="text" id="precautionTaken" name="precautionTaken" value="" style="width:100%" class="form-control"   autocomplete="on">
				                       
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Date of Sampling :</b></span>
                            	
                            	<div class="form-group">
	                             <input type="text" id="samplingDate" name="samplingDate" value="" style="width:60%" class="form-control"   autocomplete="off">
				                       
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Sampled By :</b></span>
                            	
                            	<div class="form-group">
	                                <select id="samplingBy" name="samplingBy" class="js-example-theme-single form-control" style="width:100%" required>
				                        	<option></option>
				                         <c:forEach var="byInfo" items="${sampleByList}">
				                           	<option value="${byInfo.empId }">[${byInfo.udEmpNo}] ${byInfo.empName}</option>
				                        </c:forEach>
				                        </select>
				                       
                            	</div>
                            </div>
                            
                            
                            
                 		</div>
                 		<div class="row">
				<div class="col-md-12">
					<span><b>Remarks :</b></span>
					<div class="form-group">
						<input type="text" id="remarks" name="remarks" value="" style="width:100%" class="form-control"   autocomplete="off">
					</div>
				</div>


			</div>



		
	                 
	                 
	</div>                 
	                 
	                 
	                 
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">input</i>
							<span>SAVE</span>
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
<script>
$( function() {
	
		$('#clientName').keyup(function() {
			 this.value = this.value.toLowerCase().replace(/(^|\s)[a-z]/g, function(block) {
		    return block.toUpperCase();
		  });
		});
		$('#clientId').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
    $( "#samplingDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
   
   
});
$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select from list"
});

$("#storageConId").on('change', function() {
	var id 		 = $('option:selected', this).val();
	var condText = $('option:selected', this).text();
	$("#storageCondition").val(condText);
});

$("#sampleProcedure").on('change', function(){
	var procedureVal = $('option:selected', this).val();
	
	if(procedureVal=='N' || procedureVal=='0'){
		$("#sampleArea").css("display", "none");
		$("#areaId" ).attr('name', 'areaId');
		$("#areaId").prop('required',false);
		$("#sampleBooth").css("display", "block");
		$("#boothId" ).attr('name', 'areaBoothId');
		$("#boothId").prop('required',true);
		$("#boothId").val('').trigger('change.select2');
		}
	if(procedureVal=='A'){
		$("#sampleBooth").css("display", "none");
		$("#boothId" ).attr('name', 'boothId');
		$("#boothId").prop('required',false);
		$("#sampleArea").css("display", "block");
		$("#areaId" ).attr('name', 'areaBoothId');
		$("#areaId").prop('required',true);
		$("#areaId").val('').trigger('change.select2');
		}
});

$("#requestInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#requestInfoForm").serialize();
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this data!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, Request To Sample!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/css/save-sample-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#requestInfoModal").modal('hide');
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
    	} else {
        	sweetAlert("Cancelled", "", "error", 0, false);
        }
        });
});

function clientInfoById(id){
	var clientId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/client/getClientInfoById/" + clientId, 
	function( data ) {
		$("#client_name").val(data.clientName);
		$("#clientId").val(data.udClientId);
	});
}

</script>
