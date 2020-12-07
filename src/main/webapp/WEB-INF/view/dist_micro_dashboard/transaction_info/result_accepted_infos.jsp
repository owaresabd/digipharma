<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">ACCEPTED RESULT LIST</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/result/resultSubmittedList?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/result/resultSubmittedList?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
								<th class="align-center" style="width: 70px;">SL #</th>
								<th class="align-left" style="width: 200px;">SAMPLE ID</th>
								<th class="align-left" style="width: 200px;">NAME OF SAMPLE</th>
								<!-- <th class="align-center" style="width: 150px;">DATE &amp; TIME</th> -->
								<th class="align-center">STATUS</th>
								<th class="align-center" style="width: 150px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${sampleAcceptedInfos}" varStatus="counter">
							<tr>
								<td class="field_type_code align-center">${counter.count}</td>
								<%-- <td class="field_type_code align-center">${info.udCssNo}</td> --%>
								<td class="field_type_code align-left">${info.udSampleNo}</td>
								<td class="field_type_code">${info.materialName}</td>
								<%-- <td class="field_type_name">${info.receiveDate}</td> --%>
								<td class="field_type_status align-center" width="100px">
									<span class="badge bg-green">Accepted</span>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.sampleRcvId}">
									<a class="btn-edit btn btn-xs" onclick="view(this)"><i class="material-icons">visibility</i></a>
									<%-- <c:if test="${info.resultStatus eq 'R'}">
									 <a class="btn-edit btn btn-xs" onclick="add(this)"><i class="material-icons">near_me</i></a>
									 </c:if> --%>
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
		url : "${pageContext.request.contextPath}/result/getMicroResultAcceptedInfo/" + id ,
		success : function(data) {
			$('#qcInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			}
		});
}
function add(el) {
	
	var id = $(el).closest("tr").find("#row_id").val();
	
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/result/resultEntryForm/" + id ,
		
		success : function(data) {
			$('#qcInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$('#id').val(id);
			
			}
		});
}






	
</script>
