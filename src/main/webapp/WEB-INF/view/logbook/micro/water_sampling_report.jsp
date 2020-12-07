<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
		<span style="text-shadow: 2px 2px 2px #aaa;"> WATER SAMPLING REPORT</span>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card ">
				<div class="body">
					<div class="row">
					<div class="col-md-3">
                           	<span ><b>DURATION :</b></span>
							<select id="dateRange" onchange="getdefinedDateVal(this.value,'fromDate','toDate');showHide()" class="js-example-theme-single form-control" style="width: 100%;">
                     	   <c:forEach var="dateRangeID" items="${dateRangeList}">
							    <option value="${dateRangeID.prmId }*${dateRangeID.fd }*${dateRangeID.ld }">${dateRangeID.prmNm }</option>
						    </c:forEach>
                     		</select>
                     		
                        </div>
        		    <div class="col-md-2" id="fromDateDiv" > 
                           	<span ><b>FROM  DATE :</b></span>
                           	<div class="form-group">
				                    <div class="input-group input-daterange">
								<input class="form-control dates" id="fromDate" name="fromDate" value=""  disabled="disabled" autocomplete="off" required="required"/>
									</div>
								</div>
                     		
                        </div>
                    <div class="col-md-2" id="toDateDiv"> 
                           	<span ><b>TO  DATE :</b></span>
                           	<div class="form-group">
				                    <div class="input-group input-daterange">
									<input class="form-control dates" id="toDate" name="toDate" value="" disabled="disabled" autocomplete="off" required="required"/>
									</div>
								</div>
                     		
                        </div>
					<div class="col-md-2 p-t-15">
	                <div class="form-group">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-left m-r-10" id="btnPrint" onClick="printReport(this)" data-toggle="tooltip" title="Print">
						<i class="material-icons">print</i>
					</button>
				
	                            </div>
                            </div>
                 		</div>
               </div>
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script>
$(".js-example-theme-single").select2({
    theme: "classic",
    placeholder: "Select from list.."
});
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

function printReport(el) {

	var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var ajaxURL = "${pageContext.request.contextPath}/logbook_micro/waterSampling-log-print?fromDate=" + fromDate+ "&toDate=" + toDate;
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
</script>