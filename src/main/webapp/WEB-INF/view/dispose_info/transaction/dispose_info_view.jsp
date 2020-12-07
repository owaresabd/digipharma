<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<style>
.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
}
.table > tbody > tr > td, .table > tfoot > tr > td {
    font-size: none !important;
}
.bg-cyan {
  background-color: #30acbb !important;
  color: #fff; 
  }
  
</style>

	

             	<button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             	<div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Dispose Information</td>
                 	<td>Document No.</td>
                 	<td colspan="2">LB-DIL-CM-019</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">
                 	<td>Revision No.</td>
                 	<td>00</td>
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
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
                 		<div class="row">
           <div class="col-md-6">
	           <span><b>DISPOSE ID :</b></span>
	           <div class="form-group">${info.udDisposeNo}</div>
           </div>
           <div class="col-md-6">
	           <span><b>DISPOSE AT :</b></span>
	           <div class="form-group">
				<fmt:formatDate pattern="dd-MMM-yyyy h:mm:ss a" value="${info.disposeTime}" var="disposeTime"/>
	            <c:out value="${disposeTime}"/>

				</div>
           </div>
           
		</div>
                 		<c:if test = "${info.isSample eq 'Y'}">
                 		<div class="row">
	                 		
                            <div class="col-md-12">
                            	<div class="form-group">
	                                <div class="demo-checkbox">
									<label for="sample_status"><b><span>SAMPLES</span></b></label>
									
								</div>
                            	</div>
                            </div>
                 		</div>
                 		
                 		
                 		<div class="row">
                         <div class="col-md-12">
                           <div class="form-group" >
                            	<table id="sampleViewTable" class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th class="align-center" style="width: 150px;">SAMPLE ID</th>
											<th class="align-center" style="width: 100px;">SAMPLE NAME</th>
											<th class="align-center" style="width: 130px;"> AMOUNT</th>
											<th class="align-center" style="width: 130px;">UNIT</th>
											
										</tr>
										<c:forEach var="sampleInfo" items="${disposeSampleInfos}" varStatus="counter">
										<tr>
										<td class="align-center"> 
											${sampleInfo.udSampleNo}
											
										</td>
										<td class="align-center">
										${sampleInfo.materialName}
											
											
										</td>
										<td class="align-center">
											${sampleInfo.quantity}
										</td>
										<td class="align-center">
											${sampleInfo.unitName}
										
										</td>
										
									</tr>
									
									</c:forEach>
									</thead>
									<tbody>
									
								</tbody>
							</table>
					</div>
                            </div>   
                            
                 		</div>
                 		
                 		</c:if>
                 		
                 		<c:if test = "${info.isProduct eq 'Y'}">
                 		<div class="row">
	                 		
                            <div class="col-md-10">
                            	<div class="form-group">
	                                <div class="demo-checkbox">
									
									<label for="product_status"><b><span >QC OTHER ACCESSORIES</span></b></label>
									
								</div>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                        <div class="col-md-12">
                        	<div class="form-group">
                            	<table id="productViewTable" class="table table-bordered table-striped table-hover">
									<thead>
										<tr>
											<th class="align-center" style="width: 150px;">PRODUCT NAME</th>
											<th class="align-center" style="width: 100px;">PRODUCT ID</th>
											<th class="align-center" style="width: 130px;"> Amount</th>
											<th class="align-center" style="width: 130px;">Unit</th>
											
										</tr>
										<c:forEach var="productInfo" items="${disposeProductInfos}" varStatus="counter">
										<tr>
										<td class="align-center"> 
											${productInfo.productName}
											
										</td>
										<td class="align-center">
											${productInfo.productCode}
										</td>
										<td class="align-center">
											${productInfo.quantity}
										</td>
										<td class="align-center">
										${productInfo.unitName}
										
										</td>
										
									</tr>
									
									</c:forEach>
									</thead>
									<tbody>
									
								</tbody>
							</table>
					</div>
                  </div>   
              </div>
                 </c:if>		
				<div class="row">
                            <div class="col-md-4">
                            	<span><b>RESPONSIBLE PERSON :</b></span>
                            	<div class="form-group">
	                                ${info.employeeName}
				                   
                            	</div>
                            </div>
                            <div class="col-md-8">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
	                                ${info.remarks}
                            	</div>
                            </div>
                            
                 		</div>		
						
                 		
                 		
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
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



body {
    padding-right:0px !important;
    margin-right:0px !important;
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

input {
    height: 28px !important;
}

</style>




<script>
function chngLang(el){
	var link = $(el).attr('id');
	$.ajax({
		async : false,
		url : link ,
		success : function(result) {
			$("#view_page").html(result);
		},
		error: function(){
			console.log("this employee has no user id.");
	  	}
	});
}
function pageReload(link){
	$.ajax({
		async : false,
		url : link ,
		success : function(result) {
			$("#view_page").html(result);
		},
		error: function(){
			console.log("this employee has no user id.");
	  	}
	});
}
$(".load").on('click', function(e){
	var link =  "${pageContext.request.contextPath}/dispose/maintain";
	$("#disposeInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});











</script>