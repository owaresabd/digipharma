<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
<div class="modal-header bg-cyan">
    <table class="table table-bordered" style="border: 2px solid #ddd;">
    	<tbody style="border: 2px solid #ddd;">
    	<tr>
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Material Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-DIL-GN-070</td>
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

<form method="post" id="materialInfoForm">
	<div class="modal-body">
		
		<div class="row">
           <div class="col-md-6">
	           <span><b>MATERIAL NAME :</b></span>
	           <div class="form-group">${info.materialName}</div>
           </div>
           <div class="col-md-3">
	           <span><b>MATERIAL CODE :</b></span>
	           <div class="form-group">${info.materialCode}</div>
           </div>
           <div class="col-md-3">
	           <span><b>MATERIAL TYPE  :</b></span>
	           <div class="form-group">${info.materialTypeName}</div>
           </div>
		</div>
		<div class="row">
           <div class="col-md-6">
	           <span><b>STORAGE CONDITION :</b></span>
	           <div class="form-group">${info.storageCondition}</div>
           </div>
           <div class="col-md-3"> 
	           <span><b>SAMPLING PLAN :</b></span>
	           <div class="form-group">${info.samplePlanName}</div>
           </div>
           <div class="col-md-3">
	           <span><b>SAMPLING PROCEDURE  :</b></span>
	           <div class="form-group">
		           <c:choose>
				   <c:when test="${info.matSamProcedure eq 'N'}">Normal</c:when>
				   <c:when test="${info.matSamProcedure eq 'A'}">Aseptic</c:when>
				   <c:when test="${info.matSamProcedure eq '0'}">N/A</c:when>     
				   </c:choose>
				</div>
           </div>
        </div>
		<div class="row">
			<div class="col-md-2">
			<label for="service_item">ANALYSIS :</label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
			<div class="form-group">
				<%-- <c:choose>
			    <c:when test="${info.isChemical =='Y'}">
					<input type="checkbox" checked="checked" class="filled-in chk-col-green">
			    </c:when>    
			    <c:otherwise>
			        <input type="checkbox" class="filled-in chk-col-green">
			    </c:otherwise>
				</c:choose> --%>
			<label for="chemical_status"><b><span>CHEMICAL</span></b></label>
			</div>
			</div>
		</div>
		
<div class="row">
<div class="col-md-12">
	<div class="form-group" style="overflow: auto;">
		<table id="chemicTable" class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th class="align-center" style="width: 100px;">Test Parameter No</th>
				<th class="align-center" style="width: 150px;">Test Parameter Name</th>
				<th class="align-center" style="width: 120px;">Specification</th>
				<th class="align-center" style="width: 100px;">Reference</th>
				<th class="align-center" style="width: 130px;">Test Method</th>
				<th class="align-center" style="width: 130px;">Test Unit</th>
				<th class="align-center" style="width: 130px;">LOD</th>
				<th class="align-center" style="width: 130px;">Sample Amount</th>
				<th class="align-center" style="width: 130px;">Unit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="info" items="${chemiInfos}" varStatus="counter">
			<tr>
				<td class="align-center" style="width: 100px;">${info.chemiParameterNo}</td>
				<td class="align-center" style="width: 150px;">${info.chemiParameterName}</td>
				<td class="align-center" style="width: 120px;">${info.chemiSpecification}</td>
				<td class="align-center" style="width: 100px;">${info.chemiReferenceName}</td>
				<td class="align-center" style="width: 130px;">${info.chemiMethodName}</td>
				<td class="align-center" style="width: 130px;">${info.testUnitName}</td>
				<td class="align-center" style="width: 130px;">${info.chemiLod}</td>
				<td class="align-center" style="width: 130px;">${info.chemiSampleAmount}</td>
				<td class="align-center" style="width: 130px;">${info.unitName}</td>
			</tr>
			</c:forEach>		
		</tbody>
		</table>
	</div>
</div>   
</div>
            		
<div class="row"> 		
<div class="col-md-10">
	<div class="form-group">
	<div class="form-group">
		<%-- <c:choose>
	    <c:when test="${info.isMicrobiological =='Y'}">
			<input type="checkbox" checked="checked" class="filled-in chk-col-green">
	    </c:when>    
	    <c:otherwise>
	        <input type="checkbox" class="filled-in chk-col-green">
	    </c:otherwise>
		</c:choose> --%>
	<label for="micro_status"><b><span >MICROBIOLOGICAL</span></b></label>
	</div>
	</div>
</div>
</div>

<div class="row">
<div class="col-md-12">
	<div class="form-group" style="overflow: auto;">
		<table id="microTable" class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th class="align-center" style="width: 100px;">Test Parameter No</th>
				<th class="align-center" style="width: 150px;">Test Parameter Name</th>
				<th class="align-center" style="width: 120px;">Specification</th>
				<th class="align-center" style="width: 100px;">Reference</th>
				<th class="align-center" style="width: 130px;">Test Method</th>
				<th class="align-center" style="width: 130px;">Test Unit</th>
				<th class="align-center" style="width: 130px;">LOD</th>
				<th class="align-center" style="width: 130px;">Sample Amount</th>
				<th class="align-center" style="width: 130px;">Unit</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="info" items="${microInfos}" varStatus="counter">
			<tr>
				<td class="align-center" style="width: 100px;">${info.microParameterNo}</td>
				<td class="align-center" style="width: 150px;">${info.microParameterName}</td>
				<td class="align-center" style="width: 120px;">${info.microSpecification}</td>
				<td class="align-center" style="width: 100px;">${info.microReferenceName}</td>
				<td class="align-center" style="width: 130px;">${info.microMethodName}</td>
				<td class="align-center" style="width: 130px;">${info.testUnitName}</td>
				<td class="align-center" style="width: 130px;">${info.microLod}</td>
				<td class="align-center" style="width: 130px;">${info.microSampleAmount}</td>
				<td class="align-center" style="width: 130px;">${info.unitName}</td>
			</tr>
			</c:forEach>		
		</tbody>
		</table>
	</div>
</div>   
</div>
<div class="row">
	<div class=" col-md-6">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h1 class="panel-title"><b>Sample Amount (Chemical)</b></h1>
		</div>
	
		<div class="panel-body">
		<div class="row">
			<div class="col-md-4">
				<span><b>Test Sample :</b></span>
	               <div class="form-group">${info.chemicalSampleAmt}</div>
			</div>
	
			<div class="col-md-4">
				<span><b>Retention Sample :</b></span>
	               <div class="form-group">${info.chemicalRetentionAmt}</div>
			</div>
			<div class="col-md-4">
				<span><b>Total :</b></span>
	               <div class="form-group">${info.chemicalTotal}</div>
			</div>
		</div>
		</div>
	</div>
	</div>
	<div class="col-md-6">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h1 class="panel-title"><b>Sample Amount ( Microbiological )</b></h1>
		</div>
	
		<div class="panel-body">
		<div class="row">
			<div class="col-md-4">
				<span><b>Test Sample :</b></span>
	               <div class="form-group">${info.microSampleAmt}</div>
			</div>
	
			<div class="col-md-4">
				<span><b>Retention Sample :</b></span>
	               <div class="form-group">${info.microRetentionAmt}</div>
			</div>
			<div class="col-md-4">
				<span><b>Total :</b></span>
	               <div class="form-group">${info.microTotal}</div>
			</div>
		</div>
		</div>
	</div>
	</div>
</div>
    <div class="row">
   		<div class="col-md-6">
           	<span><b>TOTAL SAMPLE AMOUNT :</b></span>
           	<div class="form-group">
            	${info.totalAmt}&nbsp;${info.unitName}
            </div>
        </div>
                    
        <div class="col-md-6">
            <span><b>STATUS :</b></span> 
            <div class="form-group">
			<c:choose>
		    <c:when test="${info.status =='Y'}">
				<span class="badge bg-green">Active</span>
		    </c:when>    
		    <c:otherwise>
		        <span class="badge bg-red">Inactive</span>
		    </c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>     		
    </div>
    <div class="modal-footer" style="background-color: #cff0f5;">
	    <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10 load"  data-dismiss="modal">
		<i class="material-icons">close</i><span>CLOSE</span>
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

input {
    height: 28px !important;
}

</style>

