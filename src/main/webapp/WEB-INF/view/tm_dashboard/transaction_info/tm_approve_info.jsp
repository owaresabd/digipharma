<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<spring:message code=""/>

<style>
/* .table {
  margin-bottom: 5px !important;
} */
</style>
<!------------------------ Start: Create Product Color Types Modal -------------------->

<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
<div class="modal-header bg-cyan">
    <table class="table table-bordered" style="border: 2px solid #ddd;">
    	<tbody style="border: 2px solid #ddd;"> 
    	<tr>
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Test Result Information</td>
    	<td style="width:130px">Document No.</td>
    	<td colspan="2">FM-DIL-GN-085</td>
    	</tr>
    	<tr style="border: 2px solid #ddd;">
    	<td style="width:130px">Revision No.</td>
    	<td style="text-align:center; width:35px">00</td>
    	<td>Page 1 of 1</td>
    	</tr>
    	<tr style="border: 2px solid #ddd;">                 	
    	<td style="width:130px">Effective Date</td>
    	<td colspan="2">15 Jan 2020</td>
    	</tr>
    	</tbody>
    </table>
</div>

                <form method="post" id="qcResultVerifyByTmInfoForm" modelAttribute="resultVerifyByTmInfo">
                 	<div class="modal-body">
					<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="sampleRcvId" name="sampleRcvId" value="${info.id}"/>
                 		<input type="hidden" id="sampleId" name="sampleId" value="${info.sampleId}"/>
                 		<div class="row">
                 			<div class="col-md-4">
                            	<span><b>ARN No :</b></span>
                            	<div class="form-group">${info.arnNo}</div>
                            </div>
                 			<div class="col-md-4">
                            	<span><b>Name of Sample :</b></span>
                            	<div class="form-group">                            	
	                            	${info.materialName}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Sample ID :</b></span>
                            	<div class="form-group">
	                            	${info.udSampleNo}
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>Type of Sample :</b></span>
                            	<div class="form-group">
	                            	${info.materialTypeName}
                            	</div>
                            </div>
                 		<div class="col-md-4">
                            	<span><b>Test Analysis :</b></span>
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
                            
                            <div class="col-md-4">
                            	<span><b>Type of Request :</b></span>
                            	<div class="form-group">
	                            	${info.reqTypeName}
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 			<div class="col-md-4">
                            	<span><b>Sample Received Time :</b></span>
                            	<div class="form-group">
	                            	<fmt:formatDate var="receiveDate" value="${info.receiveDate}" pattern="dd-MMM-yyyy hh:mm:ss aa"/>
									${receiveDate}
                            	</div>
                            </div>
                 			<div class="col-md-4">
                            	<span><b>Distribution Time (Chemi) :</b></span>
                            	<div class="form-group">
	                            	<fmt:formatDate var="chemiDate" value="${info.chemiDistDate}" pattern="dd-MMM-yyyy hh:mm:ss aa"/>
									${chemiDate}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Distribution Time (Micro) :</b></span>
                            	<div class="form-group">
	                            	<fmt:formatDate var="microDate" value="${info.microDistDate}" pattern="dd-MMM-yyyy hh:mm:ss aa"/>
									${microDate}
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-4">
                            	<span><b>Sample Description :</b></span>
                            	<div class="form-group">
	                            	${info.sampleRcvDesc}
                            	</div>
                            </div>
	                 		</div>
						
						<div class="panel panel-primary">
						<div class="panel-body">
							
							<div class="row">
							<div class="col-md-12">
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th class="align-center" style="width: 100px;">TEST ITEM</th>
							<th class="align-center" style="width: 100px;">SPECIFICATION</th>
							<!-- <th class="align-center" style="width: 100px;">DECLARED CLAIM</th> -->
							<th class="align-center" style="width: 100px;">TEST RESULT</th>
							<th class="align-center" style="width: 100px;">UNIT</th>
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
							<td class="align-center">${infos.testDate}
								<%-- <fmt:formatDate var="testDate" value="${infos.testDate}" pattern="dd-MMM-yyyy hh:mm:ss aa"/>
									${testDate} --%>
							</td>
							<td class="align-center">${infos.referenceName}</td>
							<%-- <td class="align-center" id="resultAction${infos.id}">&nbsp;</td> --%>
						</tr>
			</c:forEach>
					</tbody>


				</table>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-6">
					<span><b>Note :</b></span>
					<div class="form-group">
						<c:out value="${info.note}"/>
					</div>
				</div>
				<div class="col-md-6">
					<span><b>Remarks :</b></span>
					<div class="form-group">
						<c:out value="${info.remarks}"/>
					</div>
				</div>
			</div>
			</div>				
</div>
						
	
		</div>
	                 <div class="modal-footer save-footer align-center" style="background-color: #cff0f5;">
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

<script>

$(document).on('change', '.btn-file :file', function() {
	var input = $(this),
		label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [label]);
});

$('.btn-file :file').on('fileselect', function(event, label) {
	    
    var input = $(this).parents('.form-group').find(':text'),
        log = label;
    
    if( input.length ) {
        input.val(log);
    } else {
        if( log ) alert(log);
    }
    
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            $('#img-upload').attr('src', e.target.result);
        }
        
        reader.readAsDataURL(input.files[0]);
    }
}




</script>
