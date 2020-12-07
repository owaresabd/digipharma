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
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Warehouse Request Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-WH-001</td>
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

                <form method="post" id="whCssInfoForm"  onkeypress="if (event.keyCode == 13) { return false; }">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="whRequestId" name="whRequestId" value="${whReqInfo.id}"/>
                 		
                 		
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>REQUEST TYPE :</b></span>
                            	<div class="form-group">
	                              <c:out value="${whReqInfo.reqTypeName}"/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>FROM DEPARTMENT :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.fromDeptName}"/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>TO DEPARTMENT :</b></span>
                            	<div class="form-group">
	                                 <c:out value="${whReqInfo.toDeptName}"/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b> INVOICE NUMBER :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.invoiceNo}"/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>CHALAN NO. :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.chalanNo}"/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>L/C NO. :</b></span>
                            	<div class="form-group">
	                                 <c:out value="${whReqInfo.lcNo}"/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-8">
                            	<span><b>NAME OF MATERIALS :</b></span>
                            	<div class="form-group">
	                               <c:out value="${whReqInfo.materialName}"/>
                            	</div>
                            </div>
                             <div class="col-md-4">
                            	<span><b>MATERIALS CODE NO :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.materialCode}"/>
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		<div class="row">
	                 		
                           
                            <div class="col-md-4">
                            	<span><b>MATERIAL TYPE :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.materialTypeName}"/>
				                       
                            	</div>
                            </div>
                            
                            <div class="col-md-4">
                            	<span><b>MANUFACTURER / SOURCE :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.manufactureName}"/>
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>COUNTRY/ ORIGIN :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.countryName}"/>
				                       
                            	</div>
                            </div>
                            
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4">
                            	<span><b>GR NO. :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.grNo}"/>
                            	</div>
                            </div>
                            
                            
                 		</div>
                 		<div class="row">
	                 		
                           <div class="col-md-12">
                           <div class="form-group">
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
												<c:forEach var="info" items="${whReqInfoList}">
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
									</div>
                            </div>
                		</div>
                		<div class="row">
                		<div class="col-md-2">
                            	<span><b>RCV. QTY :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.rcvQty}"/> &nbsp;<c:out value="${whReqInfo.rcvUnitName}"/>
                            	</div>
                            </div>
                		</div>
                		<div class="row">
	                 		<div class="col-md-12">
                            	<span><b>SAMPLE DETAILS :</b></span>
                            	<div class="form-group">
	                                <c:out value="${whReqInfo.sampleDetails}"/>
                            	</div>
                            </div> 
                		</div>
	                 </div>
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10 md">
							<i class="material-icons">input</i>
							<span>REQUEST TO CSD</span>
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
.sweet-alert button.cancel {
    background-color: #E5342C;
}
</style>
<script>
$("#whCssInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#whCssInfoForm").serialize();
	swal({
        title: "Are you sure?",
        text: "Your request will be sent to CSD!!!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, Request To CSD!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    	if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/warehouse/save-css-info",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#whRequestViewInfoModal").modal('hide');
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
    	sweetAlert("Cancelled", "", "", 0, false);
    }
    });
});

</script>
