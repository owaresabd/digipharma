<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container-fluid">
	 
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
			
				<div class="body table-responsive table-bordered">
				<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Equipment Maintenance Schedule for Quality Control(Laboratory)</td>
                 	<td>Document No.</td>
                 	<td colspan="2">FM-DIL-GN-013</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td style="text-align:center; width:35px">00</td>
				    <td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">08 Oct 2019</td>
                 	</tr>             	
                 	</tbody>
                 </table>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" style="width:100%; overflow:auto; " id="verifyTable">
						<thead>
							<tr>
								<th class="align-center" style="width: 50px;">SL NO</th>
								<th class="align-left" >Name of Instrument</th>
								<th class="align-left" >Instrument Id</th>
								<th class="align-left" >Location</th>
								<th class="align-left" >Type of Maintenance</th>
								<th class="align-center" >Frequency</th>
								<c:forEach var="i" begin="1" end="${maxValue}" varStatus="loopMax">
    							  <th class="align-center" style="width: 100px;">
    							  <c:choose>
								  <c:when test="${((i % 10) == 1) && ((i % 10) != 11)}">
								    ${i}st
								  </c:when>
								  <c:when test="${((i % 10) == 2) && ((i % 10) != 12)}">
								    ${i}nd
								  </c:when>
								  <c:when test="${((i % 10) == 3) && ((i % 10) != 13)}">
								    ${i}rd
								  </c:when>
								  <c:otherwise>
								   ${i}th
								  </c:otherwise>
								</c:choose>
    							  
    							  Schedule 
    							  
    							  </th>
								</c:forEach>
								
								<th class="align-left" style="width: 100px;">Remarks</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
							  <td class="align-center">${counter.count}</td>
								<td class="align-left" style="width: auto;">${info.equipFullName}</td>
								<td class="align-left">${info.equipmentName}</td>
								<td class="align-left">${info.currLocation}</td>
								<td class="align-left">${info.typeName}</td>
								<td class="align-center">
								
								<c:choose>
								  <c:when test="${info.freqType == 'D'}">
								    Daily
								  </c:when>
								<c:when test="${info.freqType == 'M'}">
								    ${info.freqDuration} Months
								  </c:when>
								 <c:when test="${info.freqType == 'Y'}">
								    ${info.freqDuration} Years
								  </c:when>
								  <c:when test="${info.freqType == 'C'}">
								    ${info.freqDuration} Days
								  </c:when>
								  
								</c:choose>
								</td>
								
								<c:set var="dateParts" value="${fn:split(info.dateArray, ',')}" />
								<c:forEach var="j" begin="0" end="${maxValue-1}" varStatus="loopMax2">
								 <td class="align-center">
								 <c:if test="${not empty dateParts[j]}">
								
	                              	<c:out  value="${dateParts[j]}"/>
							    	
								</c:if>
								<c:if test="${ empty dateParts[j]}">
							    	N/A
								</c:if>
								 
								
								
								</td>
    							 
								</c:forEach>
								
								<td class="align-left">${info.remarks}</td>
								
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div>
					
					</div>
				</div>
			</div>
		</div>
	</div>


</div>  


	

