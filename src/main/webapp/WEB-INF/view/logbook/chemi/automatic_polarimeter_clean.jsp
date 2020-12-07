<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="" />
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
		<span style="text-shadow: 2px 2px 2px #aaa;">AUTOMATIC POLARIMETER CLEAN INFO</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card ">
				<div class="body">
				
					<div class="row">
						<div class="col-md-2">
                           <label for="equipment_id">EQUIPMENT ID :</label>
                        </div>
						<div class="col-md-3">
                           	<select id="equipment_id" class="js-example-theme-single form-control" onchange="getAutomaticPolarimeterCleanPendingInfos()" style="width: 100%;">
                     	    <option value="">PLEASE SELECT EQUIPMENT</option>                     			
                   			<c:forEach var="info" items="${equipInfos}">
							    <option value="${info.id }">${info.equipmentName}</option>
						    </c:forEach>
                     		</select>                     		
                        </div>	
					</div>
					<div class="row">
					<div class="col-md-4">
					</div>
					
					<div class="col-md-4">
					</div>
                    </div>
					
					
				</div>
			</div>
			<div class="row" id="serviceDiv">
						
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>	
<script>
 $(document).ready(function() {
setTimeout(function() {
      getAutomaticPolarimeterCleanPendingInfos();
    }, 2);
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

function getAutomaticPolarimeterCleanPendingInfos(){
    var equipmentId = $('#equipment_id option:selected').val();
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/getAutomaticPolarimeterCleanPendingInfos?equipmentId="+equipmentId;
		$.ajax({
			async : false,
			url : ajaxURL ,
			success : function(result) {
				var dataList = data.length;
				$("#serviceDiv").html(result);
			}
		});
	}
	

	function chngLang(el) {
		var link = $(el).attr('id');
		$.ajax({
			async : false,
			url : link,
			success : function(result) {
				$("#view_page").html(result);
			},
			error : function() {
				console.log("this employee has no user id.");
			}
		});
	}
	
	$(".js-example-theme-single").select2({
	    theme: "classic",
		placeholder: "Select or search from list.."
	});
	
			
</script>