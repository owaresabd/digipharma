<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="" />
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
		<span style="text-shadow: 2px 2px 2px #aaa;">COLONY COUNTER CHECK INFO</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card ">
				<div class="body">
				
					<div class="row">
						<div class="col-md-4 ">
							<span class="month">EQUIPMENT ID : </span>
							<select id="id" name="id" class="js-example-theme-single form-control" style="width: 100%;">
	                    		<option></option>
	                  		<c:forEach var="info" items="${equipInfos}">
							    <option value="${info.id}">${info.equipmentId}[${info.equipmentName}]</option>
						    </c:forEach>
	                    	</select>
	                    	
						</div>
						<br>
						<button type="button" class="btn bg-purple btn-sm waves-effect" onclick="getScheduleDetails()"  data-toggle="tooltip" title="Search By Date-Range">
							<i class="material-icons">report</i><span>REPORT</span>
						</button>
						
					</div>
					
			</div>
			<div class="row" id="detailsDiv">
						
			</div>
		</div>
	</div>
</div>

</div>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>	
<script>
$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search equipment name from list..",
	escapeMarkup: function (text) { return text; }
});
var aTable = $('#verifyTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
	 "oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#verifyTable_length, #verifyTablee_info, #verifyTable_paginate, #verifyTable_filter').hide();

function getScheduleDetails(){
    var id = $('#id option:selected').val();
	var ajaxURL = "${pageContext.request.contextPath}/equipment/getScheduleDetails?id=" +id;
		$.ajax({
			async : false,
			url : ajaxURL ,
			success : function(result) {
				$("#detailsDiv").html(result);
				
			}
		});
	}

		
</script>