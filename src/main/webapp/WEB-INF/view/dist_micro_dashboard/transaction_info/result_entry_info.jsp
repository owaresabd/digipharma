<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<!------------------------ Start: Create Product Color Types Modal -------------------->

 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">RESULT ENTRY</h4>
                 </div>
                <form method="post" id="qcResultInfoForm" modelAttribute="qcResultInfo" enctype="multipart/form-data">
                 	<div class="modal-body">
                 	<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>Name of Sample :</b></span>
                            	<div class="form-group">
	                            	${info.materialName}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Analytical Reference No :</b></span>
                            	<div class="form-group">
	                            	${info.arnNo}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Type of Sample :</b></span>
                            	<div class="form-group">
	                            	${info.materialTypeName}
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
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
                            <%-- <div class="col-md-4">
                            	<span><b>Sample ID :</b></span>
                            	<div class="form-group">
	                            	${info.udSampleNo}
                            	</div>
                            </div> --%>
                            <div class="col-md-4">
                            	<span><b>Type of Request :</b></span>
                            	<div class="form-group">
	                            	${info.reqTypeName}
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>Sample Quantity Received :</b></span>
                            	<div class="form-group">
	                            	${info.stqty} &nbsp;${info.stUnitName}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Storage Condition :</b></span>
                            	<div class="form-group">
	                            	${info.storageCondition}
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>Sample Description :</b></span>
                            	<div class="form-group">
	                            	${info.sampleRcvDesc}
                            	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
									<div class="col-md-4">
										<span><b>Distribution Time :</b></span>
										<div class="form-group">
										<fmt:formatDate  value="${info.distributionAt}" var="distributionAt"  pattern="dd-MMM-yyyy hh:mm:ss aa"/>
											${distributionAt}
											
										</div>
									</div>
									<div class="col-md-8">
										<span><b>Distributed By :</b></span>
										<div class="form-group">
										${info.distributionBy}
										</div>
									</div>
								</div>
								<div class="row">
                 		<div class="col-md-4">
                            	<span><b>Analyst :</b></span>
                            	<div class="form-group">
	                            	${info.analystName}
                            	</div>
                            </div>
                           
                            
                 		</div>
                 		<div class="row">
                 		<table id="reqTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
												  	<th class="align-center" style="width: 50px;">SL #</th>
													<th class="align-center" style="width: 100px;">Test parameter No</th>
													<th class="align-center" style="width: 100px;">Test Parameter</th>
													<th class="align-center" style="width: 100px;">Unit</th>
													<th class="align-center" style="width: 200px;">Test Result</th>
													<th class="align-center" style="width: 100px;">Specification</th>
													<th class="align-center" style="width: 100px;">Reference</th>
													<th class="align-center" style="width: 60px;">Test Method</th>
													<th class="align-center" style="width: 150px;">Equipment ID</th>
													<th class="align-center" style="width: 100px;">Attached File</th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td class="align-center">1</td>
													<td class="align-center">${info.testParameterNo}</td>
													<td class="align-center">${info.testParameterName}</td>
													<td class="align-center">
														<input type="hidden" value="${info.testUnitId}" name="unitId">
													 	${info.testUnitName}
													</td>
													<td class="align-center"><textarea name="testResult" rows="2" cols="25"></textarea></td>
													<td class="align-center">${info.specification}</td>
													<td class="align-center">${info.referenceName}</td>
													<td class="align-center">${info.testMethodName}</td>
													<td class="align-center">${info.equipmentName}</td>
													<td class="align-center">
														<div class="form-group">							       
												    	<span class="btn btn-xs btn-file bg-blue-grey waves-effect">
														<input type="file" accept="*" id="img_name" name="img_name" value="" onchange="readURL(this);">
														</span>
														<input type="text" id="image_name" name="imageName" class="form-control hidden" readonly>
														<input type="hidden" id="i_name" name="i_name">
							    						</div>
													</td>
												</tr>			
											</tbody>
										</table>
											<span class="col-md-1"><b>Remarks :</b></span><span class="col-md-4"><textarea name="remarks" rows="3" cols="60"></textarea></span>
									</div>
	                 	</div>
	                 
	                 <div class="modal-footer save-footer" style="background-color: #ced9dc;">
	                 	<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span>SUBMIT</span>
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
    theme: "classic",
	placeholder: "Select or search from list.."
});
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

$("#qcResultInfoForm").submit(function(event){
	event.preventDefault();					
    var formData = new FormData($("#qcResultInfoForm")[0]);
    swal({
        title: "Are you sure?",
        text: "You will submit this form!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, submit this form!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/result/saveResultInfo",
	    	 type: 'POST',
		        data: formData,
		        enctype: 'multipart/form-data',
		        async: false,
		        processData: false,
		        contentType: false,
		        cache: false,
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
    	   }else{
    	    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    	    }
    	    	} else {
    	    		sweetAlert("Cancelled", "", "error", 0, false);
    	    	}
    	    	});    
    
});

</script>
