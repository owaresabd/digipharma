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
                 <form method="post"   id="qcInfoForm"  onkeypress="if (event.keyCode == 13) { return false; }">
                 	<div class="modal-body ">
                 	<div class="alert-code alert-block alert-danger hidden"></div><br>
                 	
                 		<input type="hidden" id="sampleId" name="sampleId" value="${sampleInfo.id}"/>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>SERVICE REQ. NO :</b></span>
                            	<div class="form-group">${sampleInfo.udCssNo}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>DATE &amp; TIME :</b></span>
                            	<div class="form-group">${sampleInfo.reqDate}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>REQUEST TYPE :</b></span>
                            	<div class="form-group">${sampleInfo.reqTypeName}</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>NAME OF MATERIALS :</b></span>
                            	<div class="form-group">${sampleInfo.materialName}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>MATERIALS CODE NO. :</b></span>
                            	<div class="form-group">${sampleInfo.materialCode}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TYPE OF MATERIALS :</b></span>
                            	<div class="form-group">${sampleInfo.materialTypeName}</div>
                            </div>
                            
                 		</div>
                 		<c:if test="${not empty sampleInfo.manufactureName}">
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>MANUFACTURER/ SOURCE :</b></span>
                            	<div class="form-group">${sampleInfo.manufactureName}</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>COUNTRY/ORIGIN :</b></span>
                            	<div class="form-group">${sampleInfo.countryName}</div>
                            </div>
                        </div>
                        </c:if>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
					<c:choose>
    <c:when test="${sampleInfo.typeCode eq 'F'}">
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
								<c:forEach var="info" items="${whReqDetailsList}">
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
								<c:forEach var="info" items="${whReqDetailsList}">
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
								<c:when test="${(sampleInfo.isChemical eq 'Y') && (sampleInfo.isMicrobiological eq 'Y')}">
	      						Chemical & Microbiological
	      						</c:when>
								<c:when test="${(sampleInfo.isChemical eq 'Y') && (sampleInfo.isMicrobiological ne 'Y')}">
	      							Chemical 
	     						 </c:when>
								<c:when test="${(sampleInfo.isChemical ne 'Y') && (sampleInfo.isMicrobiological eq 'Y')}">
	       						Microbiological 
	      						</c:when>
							</c:choose>
							</div>
                        </div>
                           
                        <div class="col-md-3">
                        	<span><b>Required Test Sample Amount (Not less than) :</b></span>
                        	<div class="form-group">
                           <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${sampleInfo.totalSamAmt}"/>&nbsp; ${sampleInfo.matUnitName}
                        	</div>
                        </div>
                        <div class="col-md-3">
                        	<span><b>Required Retention Sample Amount (Not less than) :</b></span>
                        	<div class="form-group">
                           <fmt:formatNumber type ="number" maxFractionDigits ="3" value ="${sampleInfo.totalRetAmt}"/>&nbsp; ${sampleInfo.matUnitName}
                        	</div>
                        </div>
                        <div class="col-md-3">
							<span><b>Sample Details :</b></span>
							<div class="form-group">
								<c:out value="${sampleInfo.sampleDetails}" />
							</div>
						</div>
            </div>
			
			<div class="row">
           		<div class="col-md-3">
                   	<span><b>Client Name :</b></span>
                   	<div class="form-group">
                     <c:out value="${sampleInfo.clientName}" />
                   	</div>
                </div>
                <div class="col-md-3">
                	<span><b>Client ID :</b></span>
                	<div class="form-group">
                  		<c:out value="${sampleInfo.clientId}"/>
                	</div>
                </div>
                <div class="col-md-6">
                	<span><b>Storage Condition :</b></span>
                	<div class="form-group">
                  		<c:out value="${sampleInfo.storageCondition}"/>
                	</div>
                </div>
          	</div>
			<div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Sampling Method :</b></span>
                            	<div class="form-group">
	                              <c:out value="${sampleInfo.methodName}"/>
                            	</div>
                            </div>
                            
                            <div class="col-md-3">
                            	<span><b>Sampling Procedure :</b></span>
                            	<div class="form-group">
                            	<c:choose>
									    <c:when test="${sampleInfo.sampleProcedure =='N'}">
											<span>Normal</span>
									    </c:when>
									    <c:when test="${sampleInfo.sampleProcedure =='A'}">
											<span>Aseptic</span>
									    </c:when>     
									    <c:otherwise>
									        <span >N/A</span>
									    </c:otherwise>
									</c:choose>
	                            	
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>
                            		<c:choose>
									    <c:when test="${sampleInfo.sampleProcedure =='N' || sampleInfo.sampleProcedure =='0'}">
											<span>Sampling Booth No.</span>
									    </c:when>    
									    <c:otherwise>
									        <span >Sampling Area No.</span>
									    </c:otherwise>
									</c:choose>
                            	 :</b></span>
                            	<div class="form-group">
	                            	<c:out value="${sampleInfo.boothName}"/>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Sampling Equipment ID :</b></span>
                            	<div class="form-group">
	                            	<c:out value="${sampleInfo.equipmentName}"/>
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Work Instruction No :</b></span>
                            	<div class="form-group">
                            		<c:out value="${sampleInfo.workInstName}"/>
                            	</div>
                            </div>
                            
                            <div class="col-md-3">
                            	<span><b>Special Precautions Taken :</b></span>
                            	<div class="form-group">
	                            	<c:out value="${sampleInfo.precautionTaken}"/>
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Date of Sampling :</b></span>
                            	<div class="form-group">
                            		<fmt:formatDate pattern="dd-MMM-yyyy" value="${sampleInfo.samplingDate}" var="sampleDate" />
                            		 ${sampleDate}
	                            	
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Sampled By :</b></span>
                            	<div class="form-group">
	                                <c:out value="${sampleInfo.samplingByName}"/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
						<div class="col-md-12">
							<span><b>Remarks :</b></span>
							<div class="form-group">
								<c:out value="${sampleInfo.remarks}"/>
							</div>
						</div>
						</div>
						<div class="row md">
						<div class="col-md-4 ">
	                      	<span><b>Condition as Received :</b></span>
	                      	<div class="form-group">
	                        <div class="demo-radio-button">
                                <input name="conditionType" value="N" type="radio" id="conditionType_1" checked="checked">
                                <label for="conditionType_1">Normal</label>
                                <input name="conditionType" value="A" type="radio" id="conditionType_2">
                                <label for="conditionType_2">Abnormal</label>
                                
                            </div>
	                      </div>
                        </div>
                        <div class="col-md-8 " id="abnormal_type_div" style="display: none;">
	                      	<span><b> Specify Abnormality:</b></span>
	                      	<div class="form-group">
	                        <div class="demo-radio-button">
                                <input name="abnormalType" type="radio" value="L" id="abnormalType_1">
                                <label for="abnormalType_1">Leak</label>
                                <input name="abnormalType" type="radio" value="B" id="abnormalType_2">
                                <label for="abnormalType_2">Broken</label>
                                <input name="abnormalType" type="radio" value="S"  id="abnormalType_3">
                                <label for="abnormalType_3">Size</label>
                                <input name="abnormalType" type="radio" value="W" id="abnormalType_4">
                                <label for="abnormalType_4">Weight</label>
                                <input name="abnormalType" type="radio" value="O" id="abnormalType_5">
                                <label for="abnormalType_5">Other</label>
                                
                            </div>
	                      	</div>
                        </div>
                        <div class="col-md-12" id="abnormal_desc_div" style="display: none;">
	                      	<span><b>Details :</b></span>
	                      	<div class="form-group">
	                        <input type="text" id="abnormalDesc" name="abnormalDesc" value="" style="width:100%" class="form-control"   autocomplete="off">
	                      	</div>
                        </div>
						</div>
						<div class="row md">
						<div class="col-md-12 ">
	                      	<span><b>Customer's Additional information :</b></span>
	                      	<div class="form-group">
	                        <input type="text" id="customerDesc" name="customerDesc" value="" style="width:100%" class="form-control"   autocomplete="off">
	                      	</div>
                        </div>
                        <!-- <div class="col-md-6 ">
	                      	<span><b>Uncertainity :</b></span>
	                      	<div class="form-group">
	                        <input type="text" id="uncertinity" name="uncertinity" value="" style="width:100%" class="form-control"   autocomplete="off">
	                      	</div>
                        </div> -->
						</div>
						<div class="row md">
						<div class="col-md-6 ">
	                      	<span><b>Sample Description :</b></span>
	                      	<div class="form-group">
	                        <input type="text" id="sampleDesc" name="sampleDesc" value="" style="width:100%" class="form-control"   autocomplete="off">
	                      	</div>
                        </div>
                        <div class="col-md-6 ">
	                      	<span><b>Uncertainity :</b></span>
	                      	<div class="form-group">
	                        <input type="text" id="uncertinity" name="uncertinity" value="" style="width:100%" class="form-control"   autocomplete="off">
	                      	</div>
                        </div>
						</div>
						<div class="row md">
	                 		
                            <div class="col-md-8 align-left">
                            	<div class="demo-checkbox">
									<input type="checkbox" id="decision_status"  class="filled-in chk-col-green">
									<label for="decision_status"><b><span class="check-status">DECISION RULE</span></b></label>
									<input type="hidden" id="isDecision" name="isDecision" value="N">
								</div>
                            </div>
                 		</div>
		
	</div>                 
	                 
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10 md">
							<i class="material-icons">input</i>
							<span>SAVE</span>
						</button>
	                 </div>
	                 
                 </form>

<style>
.demo-radio-button label {
  min-width: 100px;
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
<script>
$('input[name="conditionType"]:radio').change(function() {
	var typeval=$("input[name='conditionType']:checked").val();
	if(typeval=='N'){
		$('input[name="abnormalType"]:radio').attr('checked', false);
		$("#abnormal_type_div").css("display", "none");
		$("#abnormal_desc_div").css("display", "none");
		$("#abnormalDesc").val("");
		
	}else{
		$(this).attr('checked', true);
		$("#abnormal_type_div").css("display", "block");
	}
		
	
});
$('input[name="abnormalType"]:radio').change(function() {
	var typeval=$("input[name='abnormalType']:checked").val();
	if(typeval=='O'){
		$("#abnormal_desc_div").css("display", "block");
		
	}else{
		$("#abnormal_desc_div").css("display", "none");
		$("#abnormalDesc").val("");
	}
		
	
});
$('#decision_status').change(function() {
	if (this.checked) {
		$('#isDecision').val('Y');
	}else{
		$('#isDecision').val('N');
		
	}
});

$("#qcInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#qcInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/sampling/save-qc-req-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#sampleInfoModal").modal('hide');
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
</script>
