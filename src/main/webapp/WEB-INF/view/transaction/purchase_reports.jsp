<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">PRODUCT PURCHASE REPORT</span>
    	
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<div class="row">
						<div class="alert alert-block alert-danger hidden"></div>
						<div class="col-md-4 company-list">
							<span class="month">SUPPLIER NAME : </span>
							<select id="supplierId" class="js-example-theme-single form-control" style="width: 100%;">
	                    		<option></option>
	                  		<c:forEach var="suppInfos" items="${suppInfos}">
							    <option value="${suppInfos.id}">${suppInfos.supplierName}</option>
						    </c:forEach>
	                    	</select>
	                    	
						</div>
						<div class="col-md-2">
							<span class="month"><b><spring:message code="rpt.from.date"/></b></span>
							<div class="form-group">
			                    <div class="input-group input-daterange">
									<input class="form-control" id="date1" name="date1" value="" autocomplete="off"/>
								</div>
							</div>
						</div>
						<div class="col-md-2">
							<span class="month"><b><spring:message code="rpt.to.date"/></b></span>
							<div class="form-group">
			                    <div class="input-group input-daterange">
									<input class="form-control" id="date2" name="date2" value="" autocomplete="off"/>
								</div>
							</div>
						</div><br>
						<button type="button" class="btn bg-purple btn-sm waves-effect m-t-5" onclick="searchDate()"  data-toggle="tooltip" title="Search By Date-Range">
							<i class="material-icons">search</i><span>GENERATE REPORT</span>
						</button>
						<button type="button" id="btnPrint" class="btn bg-red btn-sm waves-effect pull-right m-t-5 hidden" onclick="printDiv(this)">
							<i class="material-icons">print</i><span><spring:message code="btn.print"/></span>
						</button>
					</div>
					<div class="row hidden" id="printDiv">
						<div class="col-md-12 m-t--10" align="center">
							<h3><b>${company}</b></h3>
							<h5 style="margin-top: -10px;">${address}</h5>
							<h5><b><u>Report Date: <span class="bet-date"></span></u></b></h5>
						</div>
						<div class="col-md-12" id="icDiv">
							<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="infoList">
								<thead>
									<tr>
										<th class="align-center" width="80px">SI No.</th>
										<th class="align-center" width="250px">DEPARTMENT NAME</th>
										<th class="align-center" width="250px">ITEM NAME</th>
										<th class="align-center" width="100px">RECEIVE DATE</th>
										<th class="align-center" width="100px">QUANTITY</th>
										<th class="align-center" width="100px">UNIT PRICE</th>
										<th class="align-center" width="100px">TOTAL PRICE</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<style>

.search {
	margin-top: 3px;
}

.alert, .alert-code {
	font-size : 15px;
	color: white;
}

.month {
	font-size: 15px;
    font-weight: bold;
}

.uus {
	font-style: italic;
   	font-weight: bold;
}

#date1, #date2 {
    background-color: mintcream;
   	border: 1px solid #c8c7cc;
   	border-radius: 5px !important;
}

table.dataTable {
    clear: both;
    margin-top: -20px !important;
    margin-bottom: 6px !important;
    max-width: none !important;
}

#btnPrint {
	margin-right: 15px;
}

input, textarea {
   	border: 1px solid #c8c7cc;
   	border-radius: 4px !important;
}

html {
    overflow-y: scroll;
}

body {
    padding-right:0px !important;
    margin-right:0px !important;
    font-family: 'Tangerine', serif;
}

body.modal-open {
    overflow: auto;
}

.datepicker {
	top: 135px !important;
	margin-left: 110px !important;
}

.table-bordered tbody tr td, .table-bordered tbody tr th {
    padding: 10px;
    border: 1px solid #fff !important;
}

#infoList.table-bordered th, #infoList.table-bordered td {
	border: .25px solid #000 !important;
	font-size: 12px;
	font-style: italic;
	text-overflow: ellipsis;
}

.dataTables_scrollBody thead tr[role="row"]{
    visibility: collapse !important;
}
</style>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/printThis.js"></script>
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

var aTable = $('#infoList').DataTable({
	"aaSorting" : [[0, 'asc']],
	 "lengthMenu": [[100000], ["All"]],
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

$('#infoList_length, #infoList_info, #infoList_paginate, #infoList_filter').hide();

$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search supplier name from list..",
	escapeMarkup: function (text) { return text; }
});

$(function (){
	var m_names = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");  
    var d = new Date();  
    var curr_date = d.getDate();  
    var curr_month = d.getMonth();  
    var curr_year = d.getFullYear();  
    var today = curr_date + "-" + m_names[curr_month] + "-" + curr_year;
    var stDate = "1" + "-" + m_names[curr_month] + "-" + curr_year;
    $("#date1").val(stDate);
    $("#date2").val(today);
});


$( function() {
    $( "#date1" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        endDate: '+0d',
        autoclose: true
    });
    
    $( "#date2" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        endDate: '+0d',
        autoclose: true
    });
});

/* $("#supplier_list").on('change', function() {
	var id = $('option:selected', this).val();
	var name = $('option:selected', this).text();
	$("#supplier_id").val(id);
	$("#supplier_name").val(name);
	$(".alert").empty().addClass("hidden");
	aTable.rows().remove().draw();
	$("#btnPrint").addClass('hidden');
	$(".bet-date").text("");
	$("#printDiv").addClass('hidden');
});
 */
function searchDate(){
	var id = $("#supplier_id").val();
	var date1 = $("#date1").val();
	var date2 = $("#date2").val();
	
	var strDate = new Date(date1);
	var endDate = new Date(date2);
	
	if(id != ""){
		if(strDate > endDate){
			sweetAlert("Alert!", "From date must be smaller than To date!", "error", 3000, false);
			aTable.rows().remove().draw();
			$("#btnPrint").addClass('hidden');
			$("#printDiv").addClass('hidden');
		}else{
			$.ajax({
				async : false,
				url : "${pageContext.request.contextPath}/report/get-supplier-direct-receive-details?id=" + id + "&date1=" + date1 + "&date2=" + date2 ,
				success : function(data) {
					aTable.rows().remove().draw();
					var dataList = data.length;
					var n = 0;
					if (dataList > 0) {
						for(var i = 0; i < data.length; i++){
							var product = data[i].itemName;
							var recDate = data[i].udItemNo;
							var deptName = data[i].uomName;
							var price = data[i].price || 0;
							var total = data[i].total || 0;
							var qty = data[i].opbalStock || 0;
							n += 1;
							
							var rowNode = aTable.row.add( [n,deptName,product,convertDate(recDate),qty,price,total] ).draw( false ).node();
							$(rowNode).find(":eq(0)").css({"text-align": "center"});
							$(rowNode).find(":eq(3)").css({"text-align": "center"});
							$(rowNode).find(":eq(4)").css({"text-align": "center"});
							$(rowNode).find(":eq(5)").css({"text-align": "center"});
							$(rowNode).find(":eq(6)").css({"text-align": "right"});
						}
						$(".bet-date").text(date1 + " to " + date2);
						$("#printDiv").removeClass('hidden');
						$("#btnPrint").removeClass('hidden');
					} else {
						sweetAlert("Alert!", "There is not any direct receive info for this supplier or in this date-range!", "error", 3000, false);
						$("#btnPrint").addClass('hidden');
						$("#printDiv").addClass('hidden');
						aTable.rows().remove().draw();
					}
				}
			});
		}
	}else {
		sweetAlert("Alert!", "Please select supplier name from list!", "error", 3000, false);
		$("#btnPrint").addClass('hidden');
		$("#printDiv").addClass('hidden');
		aTable.rows().remove().draw();
	}
}

function printDiv(el){
	$('#printDiv').printThis({
		importCSS : true,
		loadCSS : [ "${pageContext.request.contextPath}/css/registersummary.css"],
	});
}
</script>