<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">TEST REPORTS LIST</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL#</th>
								<th class="align-left" style="width: 200px;">SAMPLE ID</th>
								<th class="align-left" style="width: 200px;">NAME OF SAMPLE</th>
								<th class="align-center" style="width: 100px;">REPORT DATE</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="field_type_code align-center">${counter.count}</td>
								<td class="field_type_code align-left">${info.udCssNo}</td>
								<td class="field_type_code align-left">${info.materialName}</td>
								<td class="field_type_code align-center">
								<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.reportDate}" var="dateVal" />
								<input style="width: 100%;" type="text" id="reportDate${info.udCssNo}" class="dates align-center" value="<c:out value="${dateVal}"/>" />
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.sampleId}">
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
									
									 <a class="btn-edit btn btn-xs" onclick="testReportPrint('${info.sampleId}','${info.udCssNo}','${info.udCssNo}')"><i class="material-icons">print</i></a>
									
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
			
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>		
<script>
$( function() {
	
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	$('#typeList').on('draw.dt', function () {
        $('#typeList').ready(function () {
            $(function () {
               $( ".dates" ).datepicker({
            		format: "d-M-yyyy",
                    todayHighlight: true,
                    autoclose: true
                });
            })
        });
    });
    
	$( ".dates" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
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
	var link =  "${pageContext.request.contextPath}/lm/maintain";
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
function testReportPrint(id,udId,rptDate) {
	var datVal=$('#reportDate'+rptDate).val();
	var ajaxURL = "${pageContext.request.contextPath}/css/test-report-info-print?sampleId="+id+'&udSampleId='+udId+'&reportDate='+datVal;
	
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
function view(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	var id2 = $(el).attr('id');
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/css/getTestResultDetailsInfo/"+ id ,
		success : function(data) {
			$('#qcInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			if(id2=='btnView'){
				$(".md").css("display", "none");
				
				}
			
			
			
			}
		});
}




	
</script>
