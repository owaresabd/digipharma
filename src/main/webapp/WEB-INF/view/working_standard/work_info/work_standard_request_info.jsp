<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<style>
/* .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
} */
.table > tbody > tr > td, .table > tfoot > tr > td {
    font-size: none !important;
}
.bg-cyan {
  background-color: #30acbb !important;
  color: #fff; 
  }
  
</style>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">WORKING STANDARD INFORMATION</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/batch/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/batch/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button><br><br>
					
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="batchList">
						<thead>
							<tr>
								<th class="align-center" style="width: 60px;">SL#</th>
								<th class="align-left">MATERIAL NAME</th>
								<th class="align-left" >MATERIAL CODE</th>
								<th class="align-left" >MATERIAL TYPE</th>
								<th class="align-left" >KEPT AMOUNT</th>
								<th class="align-center" >STATUS</th>
								<th class="align-center" >ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">${info.materialName}</td>
								<td class="align-left">
									${info.materialCode}
								</td>
								<td class="align-left">
								${info.materialTypeName}
								</td>
								<td class="align-left">
								<c:if test="${info.keptAmount !=null}">
									${info.keptAmount} &nbsp;${info.unitName}
								</c:if>
								</td>
								<td class="align-center">
								<c:choose>
									    <c:when test="${info.status =='P'}">
											<span class="badge bg-orange">Pending</span>
									    </c:when>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Request Created</span>
									    </c:when>     
									    
									</c:choose>
								
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="row_material_id" value="${info.materialId}">
									<input type="hidden" id="row_material_code" value="${info.materialCode}">
									<input type="hidden" id="row_type_id" value="${info.materialTypeId}">
									<input type="hidden" id="row_type_id" value="${info.materialTypeId}">
									<input type="hidden" id="row_amount" value="${info.keptAmount}">
									<input type="hidden" id="row_remarks" value="${info.remarks}">
									<c:choose>
									    <c:when test="${info.status =='P'}">
											<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									    </c:when>
									    <c:when test="${info.status =='Y'}">
											<i class="material-icons">verified_user</i>
									    </c:when>     
									    
									</c:choose>
									
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="standardInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false" >
         <div class="modal-dialog" role="document">
             <div class="modal-content">
             <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody style="border: 2px solid #ddd;">
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Working Standard Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:110px">LB-DIL-CM-020</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 Jan 2020</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                 </div>
                 
                 <form method="post" id="standardInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">NAME OF MATERIALS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
                            		<select onchange="materialInfoById(this.id)" id="materialId" name="materialId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                        	<option></option>
				                        <c:forEach var="matInfo" items="${materialList}">
				                           	<option value="${matInfo.id }">${matInfo.materialName}</option>
				                        </c:forEach>
				                    </select>
	                                
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">MATERIAL CODE:</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <input type="text" id="materialCode" name="materialCode" value="" class="form-control" placeholder="material Code" readonly="readonly">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">MATERIAL TYPE :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <select id="materialTypeId" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        	<option></option>
				                        <c:forEach var="typeInfo" items="${typeList}">
				                           	<option value="${typeInfo.id }">${typeInfo.typeName}</option>
				                        </c:forEach>
				                	</select>
				                	<input type="hidden" id="materialType_id" name="materialTypeId" value=""/>
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">KEPT AMOUNT :</label>
                            </div>
                            <div class="col-md-2">
                            	<div class="form-group">
	                                <input type="text" id="keptAmount" name="keptAmount" style="width: 80%;" value="" class="form-control" >
                            	</div>
                            </div>
                            <div class="col-md-4">
                            	<div class="form-group">
                            	 <input type="text" id="unitName" readonly="readonly" name="unitName" style="width: 50%;" value="" class="form-control" required="required" >
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="service_item">REMARKS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="form-group">
	                                <textarea rows="2" id="remarks" name="remarks" class="form-control" placeholder="remarks here......."></textarea>
                            	</div>
                            </div>
                 		</div>
                 		
               		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect load pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i><span><spring:message code="btn.save"/></span>
						</button>
	                 </div>
                 </form>
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

input {
    height: 28px !important;
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

$(".load").on('click', function(e){
	var link ="${pageContext.request.contextPath}/working_standard/request";
	$("#standardInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});

$(".js-example-theme-single").select2({
    theme: "classic"
});


function materialInfoById(id){
	var materialId=$("#"+id+"").val();
	$.get( "${pageContext.request.contextPath}/material/getMaterialInfoById/" + materialId, 
	function( data ) {
		
		$("#materialCode").val(data.materialCode);
		$('#materialTypeId').val(data.materialTypeId).trigger('change.select2');
		 $("#materialType_id").val(data.materialTypeId);
		 $("#unitName").val(data.unitName);
	});
}




function add(el) {
	$("#id").val("");
	$("#materialId").val("").trigger('change.select2');
	$("#materialTypeId").val("").trigger('change.select2');
	$("#materialType_id").val('');
	$("#materialCode").val('');
	$("#keptAmount").val('');
	$("#unitName").val('');
	$("#remarks").val('');
	
	
	$("#standardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var materialId	= $(el).closest("tr").find("#row_material_id").val();
	var materialCode = $(el).closest("tr").find("#row_material_code").val();
	var materialTypeId		= $(el).closest('tr').find("#row_type_id").val();
	var amount		= $(el).closest('tr').find("#row_amount").val();
	var remarks		= $(el).closest('tr').find("#row_remarks").val();
	
	
	$("#id").val(Id);
	$("#materialId").val(materialId).trigger('change.select2');
	$("#materialTypeId").val(materialTypeId).trigger('change.select2');
	$("#materialType_id").val(materialTypeId);
	$("#materialCode").val(materialCode);
	$("#remarks").val(remarks);
	
	$("#standardInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#standardInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#standardInfoForm").serialize();
    	
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/working_standard/saveRequestInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#standardInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "fail", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    }

});


</script>