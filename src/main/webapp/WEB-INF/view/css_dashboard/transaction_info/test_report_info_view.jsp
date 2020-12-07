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
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Test Report Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-GN-005</td>
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
                 	<div class="modal-body">
                 	<div class="alert-code alert-block alert-danger hidden"></div><br>
                 	
                 		<input type="hidden" id="sampleId" name="sampleId" value="${testResultInfo.id}"/>
                 		<div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Test Report No. :</b></span>
                            	
                            	<div class="form-group">
	                              ${testResultInfo.testReportNo}
				                       
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Date of Sample Received :</b></span>
                            	
                            	<div class="form-group">
	                              ${testResultInfo.receiveDate}
				                       
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Sample ID :</b></span>
                            	<div class="form-group">
	                              ${testResultInfo.udSampleNo}
                            	</div>
                            </div>
                            <%-- Re-write after  --%>
                            <%-- <div class="col-md-3">
                            	<span><b>Name &amp; Contact Information of Sample Sender :</b></span>
                            	<div class="form-group">
	                              ${testResultInfo.fromDeptName}
                            	</div>
                            </div> --%>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-6">
                            	<span><b>Name of Sample :</b></span>
                            	
                            	<div class="form-group">
	                              ${testResultInfo.materialName}
				                       
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Batch No. :</b></span>
                            	
                            	<div class="form-group">
	                             
				                        ${testResultInfo.batchNo}
                            	</div>
                            </div>
                            <%-- Re-write after  --%>
                            <%-- <div class="col-md-3">
                            	<span><b>Manufacturer / Source :</b></span>
                            	<div class="form-group">
	                              ${testResultInfo.manufactureName}
                            	</div>
                            </div> --%>
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-3">
                            	<span><b>Manufacturer Date :</b></span>
                            	
                            	<div class="form-group">
	                              ${testResultInfo.mfgResultDate}
				                       
                            	</div>
                            </div>
                            <div class="col-md-3">
                            	<span><b>Exp. Date :</b></span>
                            	
                            	<div class="form-group">
	                               ${testResultInfo.expResultDate}
				                       
                            	</div>
                            </div>
			<div class="row">

					
				<div class="col-md-12">
					<div class="form-group">
						<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>

							<th class="align-center" style="width: 100px;">TEST ITEM</th>
							<th class="align-center" style="width: 100px;">SPECIFICATION</th>
							<!-- <th class="align-center" style="width: 100px;">DECLARED CLAIM</th> -->
							<th class="align-center" style="width: 70px;">TEST RESULT</th>
							<th class="align-center" style="width: 70px;">UNIT</th>
							<th class="align-center" style="width: 70px;">ATTACHMENT</th>
							<th class="align-center" style="width: 100px;">TEST DATE</th>
							<th class="align-center" style="width: 60px;">REFERENCE METHOD</th>
							<!-- <th class="align-center" style="width: 60px;">UNCERTAINITY</th> -->


						</tr>
					</thead>
					<tbody>
					<c:forEach var="infos" items="${infos}" varStatus="counter">
						<tr>
							<td class="align-center">${infos.testParameterName}</td>
							<td class="align-center">${infos.specification}</td>
							<!-- <td class="align-center">&nbsp;</td> -->
							<td class="align-center">${infos.testResult}</td>
							<td class="align-center">${infos.unitName}</td>
							<td class="align-center">
								<c:choose>
								    <c:when test="${not empty (infos.docLocation)}">
										<a href="${pageContext.request.contextPath}/image/distribution_doc/${infos.docLocation}" download="${infos.docLocation}" 
													class="btn btn-xs btn-success waves-effect"><i class="fa fa-download"></i></a>
								    </c:when>
								    <c:otherwise>N/A</c:otherwise>
								</c:choose>
							</td>
							<td class="align-center">${infos.testDate}</td>
							<td class="align-center">${infos.referenceName}</td>
							<%-- <td class="align-center" id="resultAction${infos.id}">&nbsp;</td> --%>
						</tr>
			</c:forEach>
					</tbody>


				</table>
					</div>
				</div>
			</div>
			
                 		
                 		
                 		<div class="row">
						<div class="col-md-12">
							<span><b>Remarks :</b></span>
							<div class="form-group">
								<c:out value="${testResultInfo.remarks}"/>
							</div>
						</div>
						</div>
						
						
		</div>
	</div>                 
	<div class="modal-footer save-footer" style="background-color: #cff0f5;">
    	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
			<i class="material-icons">close</i><span>CLOSE</span>
		</button>
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
<script>

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
