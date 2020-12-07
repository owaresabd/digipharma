<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">CONDUCTIVITY METER LOGBOOK INFO</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/reference/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/reference/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
						<i class="material-icons">games</i>
					</button>
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 110px;">DATE</th>
								<th class="align-center" style="width: 110px;">TIME</th>
								<th class="align-center" style="width: 100px;">SAMPLE NAME</th>
								<th class="align-center" style="width: 180px;">ARN NO </th>
								<th class="align-center" style="width: 180px;">SAMPLE TYPE </th>
								<th class="align-center" style="width: 100px;">CONDUCTIVITY</th>
								<th class="align-center" style="width: 100px;">TDS</th>
								<th class="align-center" style="width: 100px;">DONE BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${multiParamInfos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center"><fmt:formatDate var="operateDate" value="${info.operateDate}" pattern="dd-MMM-yyyy"/>
								${operateDate}</td>
								<td class="align-center">${info.recordTime}</td>
								<td class="align-center">
								<c:choose>
							    	<c:when test="${(info.materialName !=null) && (not empty info.materialName)}">
									${info.materialName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
								<c:choose>
							    	<c:when test="${(info.qcRefName !=null) && (not empty info.qcRefName)}">
									${info.qcRefName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
								<c:choose>
							    	<c:when test="${(info.sampleTypeName !=null) && (not empty info.sampleTypeName)}">
									${info.sampleTypeName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">${info.conductivity}</td>
								<td class="align-center">${info.tds}</td>
								<td class="align-left">${info.operateByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
								<c:choose>
									<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">Verified</span>
									</c:when>
									<c:when test="${info.cleanStatus =='Y'}">
									<span class="badge bg-green">Cleaned</span>
									</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
								 
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_material_id" value="${info.materialId}">
									<input type="hidden" id="r_conductivity" value="${info.conductivity}">
									<input type="hidden" id="r_tds" value="${info.tds}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_equipment_id" value="${info.equipmentId}">
									<input type="hidden" id="r_sample_type_id" value="${info.sampleTypeId}">
									<input type="hidden" id="r_qc_ref_id" value="${info.qcRefId}">
									
									
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
<div class="modal fade" id="conductivityMeterInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Conductivity Meter Logbook Info </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2">LB-DIL-CM-009</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">22 JAN 2020</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="conductivityMeterInfoForm" modelAttribute="multiParameterInfo">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
						           	<div class="col-md-2 align-right top">
                            	<label for="done_by">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
     							<select id="equipment_id" name="equipmentId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                       	<option value="">-Select Equipment-</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>
								
                           	</div>
                            </div>
	            
	              		<div class="col-md-2 align-right top">
                            	<label for="qc_ref_id">AR NO. :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                           	  <select onchange="materialInfoByArn(this.id)" id="qc_ref_id" name="qcRefId" class="js-example-theme-single form-control" style="width: 100%;">
                                   <option value="">-N/A-</option>
                                <c:forEach var="info" items="${arnInfos}">
                                  <option value="${info.id }">${info.arnNo}</option>
                                </c:forEach>
                              </select>
                           	</div>
                            </div>
                            
						</div>
					      		<div class="row">
   		                    
                 			<div class="col-md-2 align-right top">
                            	<label for="product_id">SAMPLE NAME :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<select id="product_id" name="" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
				                        <option value="">-N/A-</option>
				                        <c:forEach var="info" items="${materialList}">
				                           	<option value="${info.id }">${info.materialName}</option>
				                        </c:forEach>
				                    </select>
									
									 <input type="hidden" id="product_id_hidden" name="productId" value=""/>
	                            </div>
                            </div>
											
	                 
                           <div class="col-md-2 align-right top">
                            	<label for="sample_type_list">SAMPLE TYPE :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="sample_type_list" class="js-example-theme-single form-control" style="width: 100%;" disabled="disabled">
			                         <option value="">-N/A-</option>
			                        <c:forEach var="info" items="${typeList}">
			                           	<option value="${info.id }">${info.typeName}</option>
			                        </c:forEach>
			                    </select>
			                    <input type="hidden" id="sample_type_id" name="sampleTypeId" value=""/>
                           	</div>
                            </div>
                 		</div>
                		<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="conductivity">CONDUCTIVITY :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                              <input type="text" id="conductivity" name="conductivity" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required" />
                            </div>
                            </div>
                            
                            <div class="col-md-2 align-right top">
                            	<label for="tds">TDS :</label>
                            </div>
                            <div class="col-md-4">
                            <div class="form-group">
                                <input type="text" id="tds" name="tds" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
	                 		<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">Remarks :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" placeholder="Description goes here......." ></textarea>
	                            </div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #cff0f5;">
	                 <button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i>
							<span><spring:message code="btn.save"/></span>
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
</style>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>	
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>		
<script>
$(".js-example-theme-single").select2({
    theme: "classic"
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

$( function() {
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
	$( "#operate_date" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
	
});


function materialInfoByArn(id){
	var arnNo = $("#"+id+" option:selected").val();
	if(arnNo !=""){
		$.get( "${pageContext.request.contextPath}/logbook_chemi/getMaterialInfoByArn/" + arnNo, 
				function( data ) {
					$("#sample_type_id").val(data.materialTypeId);
					$("#sample_type_list").select2("val", data.materialTypeId);
					$("#product_id_hidden").val(data.materialId);
					$("#product_id").select2("val", data.materialId);
					
				});	
		
	}else{
		$("#sample_type_id").val("");
		$("#sample_type_list").select2("val", "");
		$("#product_id_hidden").val("");
		$("#product_id").select2("val", "");
		
	}
	
}

function add(el) {
	$("#id").val("");
	$("#product_id").select2("val", "");
	$("#equipment_id").select2("val", "");
	$("#qc_ref_id").select2("val", "");
	$("#sample_type_list").select2("val", "");
	$("#sample_type_id").val("");
	$("#conductivity").val("");
	$("#tds").val("");
	$("#remarks").val("");
    $("#conductivityMeterInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) {
	var Id 			 = $(el).closest("tr").find("#row_id").val();
	var qcRefId 	 = $(el).closest("tr").find("#r_qc_ref_id").val();
	var materialID 		 = $(el).closest("tr").find("#r_material_id").val();
	var equipmentId  = $(el).closest("tr").find("#r_equipment_id").val();
	var conductivity = $(el).closest("tr").find("#r_conductivity").val();
	var tds 		 = $(el).closest("tr").find("#r_tds").val();
	var remarks 	 = $(el).closest("tr").find("#r_remarks").val();
	var sampleTypeId = $(el).closest("tr").find("#r_sample_type_id").val();
	$("#id").val(Id);
	
	if(qcRefId==""){
		$("#sample_type_id").val("");
		$("#sample_type_list").select2("val", "");
		$("#product_id_hidden").val("");
		$("#product_id").select2("val", "");
		
	}else{
	$("#qc_ref_id").val(qcRefId).trigger('change.select2');
		
	}
	$("#equipment_id").val(equipmentId).trigger('change.select2');	
	$("#conductivity").val(conductivity);
	$("#tds").val(tds);
	$("#remarks").val(remarks);
		
	$("#conductivityMeterInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#conductivityMeterInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#conductivityMeterInfoForm").serialize();
    swal({
        title: "Are you sure?",
        text: "You will submit this form!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes, submit this form!",
        cancelButtonText: "No, cancel please!",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/logbook_chemi/saveConductivityMeterInfos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#conductivityMeterInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "error", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 3000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});


</script>
