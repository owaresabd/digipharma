<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">VERIFIED RESULT LIST</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/result/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/result/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="tmTable">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL #</th>
								<th class="align-left" style="width: 150px;">SAMPLE ID</th>
								<th class="align-left" style="width: 150px;">ARN NO</th>
								<th class="align-left" style="width: 200px;">NAME OF SAMPLE</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<%-- <c:forEach var="info" items="${verifiedList}" varStatus="counter">
							<tr>
								<td class="field_type_code align-center">${counter.count}</td>
								<td class="field_type_code align-center">${info.udSampleNo}</td>
								<td class="field_type_code align-center">${info.arnNo}</td>
								<td class="field_type_code align-center">${info.materialName}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">forward</i></a>
									<c:if test="${info.resultStatus eq 'P'}">
									<a class="btn-edit btn btn-xs" onclick="add(this)"><i class="material-icons">near_me</i></a>
									</c:if>
								</td>
							</tr>
							</c:forEach> --%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<!------------------------ Start: Create Product Color Types Modal -------------------->
<div class="modal fade" id="qcInfoModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false"  >
         <div id="modalId" class="modal-dialog modal-xxl" role="document">
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
			
<%-- <script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script> --%>		
<script>
 
 $(document).ready(function () {
	 
	 var aTable = $('#tmTable').DataTable({
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

		/*  $('#tmTable_length, #tmTable_info, #tmTable_paginate, #tmTable_filter').hide(); */
	var ajaxURL = "${pageContext.request.contextPath}/tm/tmPendingList";
	$.ajax({
				type : "GET",
				url : ajaxURL,
				dataType : 'json',
				success : function(data) {
					
					for ( var i = 0; i < data.length; i++) {
						var id=data[i].id;
						var udSampleNo=data[i].udSampleNo;
						var arnNo=data[i].arnNo;
						var materialName=data[i].materialName;
						var html = '' 
							+ '<input type="hidden"  id="row_id" value="'+id+'"/>'
							+ '<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>||<a class="btn-edit btn btn-xs" onclick="send(this)"><i class="material-icons">forward</i></a>';
						var rowNode = aTable.row.add( [i+1, udSampleNo, arnNo, materialName, html]).draw( false ).node();
						aTable.draw();
						aTable.page('last').draw(false);
						$(rowNode).find("td:eq(0)").css({"text-align": "center"});
						$(rowNode).find("td:eq(1)").css({"text-align": "center"});
						$(rowNode).find("td:eq(2)").css({"text-align": "center"});
						$(rowNode).find("td:eq(3)").css({"text-align": "center"});
						$(rowNode).find("td:eq(4)").css({"text-align": "center"});
						
					}
					
					
				}
			});
	
	
	
	}); 
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
	var link =  "${pageContext.request.contextPath}/tm/maintain";
	$("#qcInfoModal").modal('hide');
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

function view(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/tm/getVerifiedResultInfo/" + id ,
		success : function(data) {
			$('#qcInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$(".tmInfo").css("display", "none");
			
			}
		});
}
function send(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/tm/getVerifiedResultInfo/" + id ,
		success : function(data) {
			$('#qcInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			
			}
		});
}




	
</script>
