<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">SENT REQUEST INFO</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;"> SL #</th>
							    <th class="align-left"> REQUEST NO.</th>
								<th class="align-left">NAME OF MATERIALS </th>
								<th class="align-left">REQUEST TYPE </th>
								<th class="align-left">GR. NO.</th>
								<th class="align-center">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${whRequestList}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_type_name">${info.udWhReqNo}</td>
								<td class="field_type_name">${info.materialName}</td>
								<td class="field_type_status align-left" >${info.reqTypeName}</td>
								<td class="field_type_name">${info.grNo}</td>
								
								<td class="align-center">
								<input type="hidden" id="row_id" value="${info.id}">
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
									<a class="btn-edit btn btn-xs" onclick="whRequestInfoPrint('${info.id}','${info.udWhReqNo}')"><i class="material-icons">print</i></a>
									
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<!------------------------ Start: Create Product Color Types Modal -------------------->

     
     <div class="modal fade" id="whRequestViewInfoModal" style="overflow: auto;"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                 
             </div>
         </div>
     </div>

	 
</div>  

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
</style>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>			
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>		
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
	var link =  "${pageContext.request.contextPath}/warehouse/sentRequestList";
	$("#whRequestInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});
$(".modal-header").on("mousedown", function(mousedownEvt) {
    var $draggable = $(this);
    var x = mousedownEvt.pageX - $draggable.offset().left,
        y = mousedownEvt.pageY - $draggable.offset().top;
    $("body").on("mousemove.draggable", function(mousemoveEvt) {
        $draggable.closest(".modal-dialog").offset({
            "left": mousemoveEvt.pageX - x,
            "top": mousemoveEvt.pageY - y
        });
    });
    $("body").one("mouseup", function() {
        $("body").off("mousemove.draggable");
    });
    $draggable.closest(".modal").one("bs.modal.hide", function() {
        $("body").off("mousemove.draggable");
    });
});


$(function() {
    $('.uppercase').keyup(function() {
        this.value = this.value.toUpperCase();
    });
    
});

$('.number').keypress(function (event) {
    return isNumber(event, this)
});



$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select from list"
	 
});
var aTable = $('#reqTable').DataTable({
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

$('#reqTable_length, #reqTable_info, #reqTable_paginate, #reqTable_filter').hide();

function remove(el){
	var qty 		= $(el).closest("tr").find("#i_qty").val();
	var rcvQty		= $("#rcvQty").val();
	var totalQty	= parseFloat(rcvQty) - parseFloat(qty);
	$("#rcvQty").val(totalQty);
	
    var tbl = $(el).closest('table').DataTable();
    var tr 	= $(el).closest('tr');
    tbl.row(tr).remove().draw(false);
}


function whRequestDetailsById(id){
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/warehouse/whRequestDetailsById?whRequestId=" + id,
		dataType : 'json',
		success : function(data) {
			
			$("#reqTable").find("tr:gt(1)").remove();
			var requestList = data.length;
			 if (requestList > 0) {
				 showRequestList(data);
			} 
		}
	});
}
function materialInfoById(id){
	var materialId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/material/getMaterialInfoById/" + materialId, 
	function( data ) {
		
		$("#materialCode").val(data.materialCode);
		$('#materialTypeId').val(data.materialTypeId).trigger('change.select2');
		 $("#materialType_id").val(data.materialTypeId);
	});
}

function showRequestList(data) {
	for (var i = 0; i < data.length; i++) {
		var id = data[i].whRequestId || "";
		var chdId = data[i].childId || "";
		var mfgDate = data[i].mfgDate || "";
		var expDate = data[i].expDate || "";
		var batchLot = data[i].batchLot || "";
		var batchNo = data[i].batchNo || "";
		var qty =data[i].quantity || "";
		var unitId =data[i].unitId || "";
		var unitName =data[i].unitName || "";
		var noOfDrums = data[i].noOfDrums || "";
		
		var dates;
		if(mfgDate === undefined){
			dates = "";	
		}else{
			dates = convertMmDate(mfgDate);	
		}
		var expireDate;
		if(expDate === undefined){
			expireDate = "";	
		}else{
			expireDate = convertMmDate(expDate);	
		}
		var html = '' 
			+ '<input type="hidden"  name="id[]" value="'+id+'"/>'
			+ '<input type="hidden" id="i_child_id"  name="childId[]" value="'+chdId+'"/>'
			+ '<input type="hidden" id="i_batch_no"  name="batchNo[]" value="'+batchNo+'"/>'
			+ '<input type="hidden" id="i_batch_lot" name="batchLot[]" value="'+batchLot+'"/>'
			+ '<input type="hidden" id="i_mfg_date" name="mfgDate[]"  value="'+dates+'"/>'
			+ '<input type="hidden" id="i_exp_date"  name="expDate[]" value="'+expireDate+'"/>'
			+ '<input type="hidden" id="i_qty" name="quantity[]" value="'+qty+'"/>'
			+ '<input type="hidden" id="i_unitId" name="unitId[]" value="'+unitId+'"/>'
			+ '<input type="hidden" id="i_unitNm" value="'+unitName+'"/>'
			+ '<input type="hidden" id="i_no_of_drums" name="noOfDrums[]" value="'+noOfDrums+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRow(this)"><i class="material-icons">mode_edit</i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		
		var rowNode = aTable.row.add([batchNo, batchLot, dates, expireDate, qty, unitName, noOfDrums, html]).draw( false ).node();
		aTable.draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(0)").css({"text-align": "center"});
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		$(rowNode).find("td:eq(3)").css({"text-align": "center"});
		$(rowNode).find("td:eq(4)").css({"text-align": "center"});
		$(rowNode).find("td:eq(5)").css({"text-align": "center"});
		$(rowNode).find("td:eq(6)").css({"text-align": "center"});
		$(rowNode).find("td:eq(7)").css({"text-align": "center"});
	}
}


function view(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/warehouse/get-whRequest-info/" + id ,
		success : function(data) {			
			$('#whRequestViewInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$(".md").css("display", "none");
			
			}
		});
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
