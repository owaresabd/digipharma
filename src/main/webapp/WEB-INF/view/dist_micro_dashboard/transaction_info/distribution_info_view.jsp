<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<!------------------------ Start: Create Product Color Types Modal -------------------->

 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">WORK ORDER DEATILS</h4>
                 </div>
                <form method="post" >
                 	<div class="modal-body">
                 		<div class="row">
                 		<div class="col-md-12">
                 		<div class="col-md-4">
                            	<span><b>NAME OF SAMPLE :</b></span>
                            	<div class="form-group">
	                            	${info.materialName}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>ANALYTICAL REFERENCE NO :</b></span>
                            	<div class="form-group">
	                            	${info.arnNo}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TYPE OF SAMPLE :</b></span>
                            	<div class="form-group">
	                            	${info.materialTypeName}
                            	</div>
                            </div>
                 		</div>
                 		<div class="col-md-12">
                 		<div class="col-md-4">
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
                            <%-- <div class="col-md-4">
                            	<span><b>SAMPLE ID :</b></span>
                            	<div class="form-group">
	                            	${info.udSampleNo}
                            	</div>
                            </div> --%>
                            <div class="col-md-4">
                            	<span><b>TYPE OF REQUEST :</b></span>
                            	<div class="form-group">
	                            	${info.reqTypeName}
                            	</div>
                            </div>
                 		</div>
                 		<div class="col-md-12">
                 		<div class="col-md-4">
                            	<span><b>SAMPLE QUANTITY RECEIVED :</b></span>
                            	<div class="form-group">
	                            	${info.stqty} &nbsp;${info.stUnitName}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>STORAGE CONDITION :</b></span>
                            	<div class="form-group">
	                            	${info.storageCondition}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>SAMPLE DESCRIPTION :</b></span>
                            	<div class="form-group">
	                            	${info.sampleRcvDesc}
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="col-md-12">
								
									<h6><b><u>SAMPLE STORAGE LOCATION :</u></b></h6>
									<div class="col-md-4">
										<span><b>ROOM NO :</b></span>
										<div class="form-group">
											${info.stRoomName}
										</div>
									</div>
									<div class="col-md-4">
										<span><b>INSTRUMENT ID :</b></span>
										<div class="form-group">
										${info.stEquipmentName}
										</div>
									</div>
									
									<div class="col-md-4">
										<span><b>RACK NO :</b></span>
										<div class="form-group">
											${info.stRackName}
										</div>
									</div>
								</div>
								<div class="col-md-12">
                 		<div class="col-md-4">
                            	<span><b>ANALYST :</b></span>
                            	<div class="form-group">
	                            	${info.analystName}
                            	</div>
                            </div>
                           
                            
                 		</div>
                 		<div class="col-md-12">
                 		<table id="reqTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
												  <th class="align-center" style="width: 50px;">SL #</th>
													<th class="align-center" style="width: 100px;">Test parameter No</th>
													<th class="align-center" style="width: 100px;">Test Parameter</th>
													<th class="align-center" style="width: 100px;">Specification</th>
													<th class="align-center" style="width: 100px;">Reference</th>
													<th class="align-center" style="width: 60px;">Test Method</th>
													<th class="align-center" style="width: 150px;">Equipment ID</th>
												</tr>
												</thead>
												<tbody>
												
												<tr>
												<td class="align-center">1</td>
													<td class="align-center">${info.testParameterNo}</td>
													<td class="align-center">${info.testParameterName}</td>
													<td class="align-center">${info.specification}</td>
													<td class="align-center">${info.referenceName}</td>
													<td class="align-center">
														${info.testMethodName}
														<%-- <c:if test="${not empty info.testMethodName}">
														<a id="down_link" class="btn-edit btn btn-xs" href="${pageContext.request.contextPath}/image/test_method_doc/">${info.testMethodName}</a>
														</c:if> --%>
													</td>
													<td class="align-center">${info.equipmentName}</td>	
											</tr>
										</tbody>
									</table>
								</div>
						
	                 </div>
	                 </div>
	                 <div class="modal-footer save-footer" style="background-color: #ced9dc;">
	                 	
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
