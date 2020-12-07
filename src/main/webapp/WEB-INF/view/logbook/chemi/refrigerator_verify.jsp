<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="" />
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
	<style>
.trackDiv {
  background-image: url("${pageContext.request.contextPath}/image/vehicle_attachment/track.gif");
}
</style>
<div class="container-fluid">
	<div class="block-header">
		<span style="text-shadow: 2px 2px 2px #aaa;">REFRIGERATOR VERIFY INFO</span>
		
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
                           	<select id="equipment_id" class="js-example-theme-single form-control" onchange="getWaterPurifyVerifiedPendingInfos()" style="width: 100%;">
                     	    <option value="">PLEASE SELECT EQUIPMENT</option>                     			
                   			<c:forEach var="info" items="${equipInfos}">
							    <option value="${info.id}">${info.equipmentName}</option>
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
      getWaterPurifyVerifiedPendingInfos();
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

function showHide(){
	var  dateRangeVal= $("#dateRange").val();
	var valSplt=dateRangeVal.split('*');
	if($.trim(valSplt[0])=="11"){
		$("#fromDate").removeAttr('disabled');
		$("#toDate").removeAttr('disabled');
	}else{
		$("#fromDate").attr('disabled','disabled');
		$("#toDate").attr('disabled','disabled');
	}
}

function getdefinedDateVal(val,dt1,dt2){
		var valSplt=val.split('*');	
		$('#'+dt1).datepicker('setDate', $.trim(valSplt[1]));
		$('#'+dt2).datepicker('setDate', $.trim(valSplt[2]));
	}

$( function() {
    $(".dates").datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
    var m_names = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");  
    var d = new Date();  
    var curr_date = d.getDate();  
    var curr_month = d.getMonth();  
    var curr_year = d.getFullYear();  
    var today = curr_date + "-" + m_names[curr_month] + "-" + curr_year;
    var stDate = "1" + "-" + m_names[curr_month] + "-" + curr_year;
    $("#fromDate").val(today);
    $("#toDate").val(today);
    
});

function getWaterPurifyVerifiedPendingInfos(){
    var equipmentId = $('#equipment_id option:selected').val();
	var ajaxURL = "${pageContext.request.contextPath}/logbook_chemi/getRefrigeratorVerifiedPendingInfos?equipmentId="+equipmentId;
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
	
	$("#vehicle_list").on('change', function(){
		var id = $('option:selected', this).val();
		var text = $('option:selected', this).text();
		$("#vehicle_id").val(id);
		$("#vehicle_name").val(text);
		$(".alert").empty().addClass("hidden");
	});
		
</script>