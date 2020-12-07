<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="" />
<link href="${pageContext.request.contextPath}/css/select2.min.css"
	rel="stylesheet" media="screen">
	<style>

</style>
<div class="container-fluid">
	<div class="block-header">
		<span style="text-shadow: 2px 2px 2px #aaa;"> REFERENCE STANDARD REPORT</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card ">
				<div class="body">
					<div class="row">
						<div class="col-md-3">
                           	<span ><b>NAME OF REFERENCE STANDARD :</b></span>
                           	<select id="reference_list" class="js-example-theme-single form-control" style="width: 100%;">
                     			<option value=""></option>
	                   			<c:forEach var="info" items="${infos}">
								    <option value="${info.id }">${info.refStandardName}</option>
							    </c:forEach>
                     		</select>
                     		
                        </div>
                        
                        <div class="col-md-3 p-t-15">   
                        
						<button type="button" onclick="getrefStandardReport()" id="" class="btn bg-pink btn-md waves-effect">
							<span>GET REPORT</span>
						</button>                   
	                 	<button type="button" onclick="refreshPage()" class="btn bg-orange btn-md waves-effect pull-right" >
							<i class="material-icons">cached</i><span>REFRESH</span>
						</button>
						
	                 	</div>	                
					</div>
					<div class="row" id="mapDiv">
						
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script>
$( function() {
    $("#fromDate").datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    $("#toDate").datepicker({
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
    $(".dates").val(today);
});
 function refreshPage2(){
		 var ajaxURL = "${pageContext.request.contextPath}/user/operationLog/";
			$.ajax({
				async : false,
				url : ajaxURL ,
				success : function(result) {
					$("#view_page").html(result);
					
				}
			});
		}


	
$(".js-example-theme-single").select2({
	    theme: "classic",
		placeholder: "Select or search from list.."
});

function refreshPage() {
	
	$("#reference_list").val("").trigger('change.select2');
	
    //$("#refStandardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}
	
	
function getEventLogReport() {
		var userId = $("#userId").val();
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		var ajaxURL = "${pageContext.request.contextPath}/user/getOperationReport?userId="+userId+'&fromDate='+fromDate+'&toDate='+toDate;
		$.ajax({
			type : "GET",
			url : ajaxURL,
			dataType : 'json',
			success : function(data) {
				
					var path = "${pageContext.request.contextPath}/report/" + data;
					window.open(path, '_blank');
					
			}
		});

}

function getrefStandardReport() {
	var id = $("#reference_list").val();
	var name = $("#reference_list option:selected").text();
	var ajaxURL = "${pageContext.request.contextPath}/ref_standard/ref-standard-print?referId="+id+'&referName='+name;
	
	 if(id !=""){
	 $.ajax({
		type : "GET",
		url : ajaxURL,
		dataType : 'json',
		success : function(data) {
			var path = "${pageContext.request.contextPath}/report/" + data;
			window.open(path, '_blank');
			
		}
	});
 	}else{
    	sweetAlert("Failed!", "Please select Reference Standard, then submit again.", "warning", 3000, false);
    }
	 
}

function whRequestInfoPrint(id,udId) {
	var ajaxURL = "${pageContext.request.contextPath}/warehouse/request-info-print?requestId="+id+'&udRequestId='+udId;
	
	 $.ajax({
		type : "GET",
		url : ajaxURL,
		dataType : 'json',
		success : function(data) {
			var path = "${pageContext.request.contextPath}/report/" + data;
			window.open(path, '_blank');
			
		}
	}); 
}
	
	
	
</script>