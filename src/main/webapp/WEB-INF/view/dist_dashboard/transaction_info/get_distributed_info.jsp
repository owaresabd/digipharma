<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>

<style>
/* .table {
  margin-bottom: 0px !important;
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
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Distributed Work Information</td>
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

                <form method="post" id="qcResultAcceptInfoForm">
                 	<div class="modal-body">
                 	<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<input type="hidden" id="isDecision" name="isDecision" value="${info.isDecision}"/>
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>ARN No :</b></span>
                            	<div class="form-group">
	                            	${info.arnNo}
                            	</div>
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
						    <th class="align-center" style="width: 50px;">SL #</th>
							<th class="align-center" style="width: 100px;">Test parameter No</th>
							<th class="align-center" style="width: 100px;">Test Parameter</th>
							<th class="align-center" style="width: 100px;">Specification</th>
							<th class="align-center" style="width: 100px;">Reference</th>
							<th class="align-center" style="width: 60px;">Test Method</th>
							<th class="align-center" style="width: 150px;">Equipment ID</th>
							<th class="align-center" style="width: 150px;">Analyst</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="infos" items="${infos}" varStatus="counter">
						<tr>
							<td class="align-center">${counter.count}</td>
							<td class="align-center">${infos.testParameterNo}</td>
							<td class="align-center">${infos.testParameterName}</td>
							<td class="align-center">${infos.specification}</td>
							<td class="align-center">${infos.referenceName}</td>
							<td class="align-center">${infos.testMethodName}</td>
							<td class="align-center">${infos.equipmentName}</td>
							<td class="align-center">
								<c:if test="${not empty (infos.analystName)}">
									[${infos.analystEmpId}] ${infos.analystName}
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				
			</div>
			</div>				
		</div>
						
	
		</div>
            <div class="modal-footer save-footer align-center" style="background-color: #cff0f5;">
            	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10 load"  data-dismiss="modal">
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
function add(el) {

}
function resultAccept(el) {
	var id = $(el).closest('tr').find("#row_id").val();
	swal({
        title: "Are you sure?",
        text: "You will accept this result!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, accept this result!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
        if (isConfirm) {
			$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/result/getResultAcceptInfo/" + id ,
				success : function(data) {
					sweetAlert("Accept!", "Result Accept Successfully", "success", 1000, false);
					$("#resultAction"+data).text("Result Accepted");
				},
				error: function(){
					sweetAlert("Failed!", "Something going wrong.", "fail", 1000, false);
			  	}
			});
        } else {
        	sweetAlert("Cancelled", "Your data is safe :)", "error", 0, false);
        }
    });
}


</script>
