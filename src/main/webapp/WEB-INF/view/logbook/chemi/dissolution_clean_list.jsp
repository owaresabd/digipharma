<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.css" rel="stylesheet" media="screen">
<script src="${pageContext.request.contextPath}/vendor/mdtimepicker/mdtimepicker.js"></script>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">LOG BOOK:: DISSOLUTION CLEAN INFO</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<br><br>
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="typeList">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-center" style="width: 110px;">DATE </th>
								<th class="align-center" style="width: 110px;">TIME</th>
								<th class="align-center" style="width: 110px;">EQUIPMENT ID</th>
								<th class="align-center" style="width: 180px;">ARN NO.</th>
								<th class="align-center" style="width: 200px;">CLEAN BY</th>
								<th class="align-center" style="width: 200px;">REMARKS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-center">
									<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.recordDate}" var="dateVal" />
	                              	<c:out value="${dateVal}"/>
								</td>
								<td class="align-left">${info.recordTime}</td>
								<td class="align-center">${info.equipmentName}</td>
								<td class="align-center"> 
								<c:choose>
							    	<c:when test="${(info.qcReferenceName !=null) && (not empty info.qcReferenceName)}">
									${info.qcReferenceName}
							    	</c:when>    
								    <c:otherwise>
									 N/A
								    </c:otherwise>
									</c:choose>	
								</td>
								<td class="align-left">${info.cleanByName}</td>
								<td class="align-left">${info.remarks}</td>
								<td class="align-center">
									<c:choose>
									<c:when test="${info.verifyStatus =='Y'}">
									<span class="badge bg-green">Verified</span>
									</c:when>
									<c:otherwise>
									<a class="btn-edit btn btn-xs" onclick="editClean(this)"><i class="material-icons">mode_edit</i></a>
									</c:otherwise>              
								</c:choose>
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_qc_reference" value="${info.qcReferenceName}">
									<input type="hidden" id="r_tested_by" value="${info.testedBy}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_equipment_name" value="${info.equipmentName}">
									<input type="hidden" id="r_clean_by" value="${info.cleanBy}">								
																	
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
<div class="modal fade" id="dissolutionInfoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog  modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Log Book: Dissolution Info</td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-CM-006</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">16 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="dissolutionInfoForm" >
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 				<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="equipment_name">EQUIPMENT ID :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
					            <input type="text" id="equipment_name" value="" class="form-control" style="width: 100%;" readonly="">
                   
	                            </div>
                            </div>
                 			<div class="col-md-2 align-right top">
                            	<label for="qc_reference">ARN NO :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
							 <input type="text" id="qcReferenceId" value="" class="form-control" style="width: 100%;" readonly="">
                           	</div>
                            </div>
                 		</div>
						<div class="row">
	                 		<div class="col-md-2 align-right top">
                            	<label for="clean_by">CLEANED BY :</label>
                            </div>
                            <div class="col-md-4">
                           	<div class="form-group">
                                <select  id="clean_by" name="cleanBy" class="js-example-theme-single form-control" style="width: 100%;" required="required">
			                        <option value="">-Select Employee-</option>
			                        <c:forEach var="info" items="${employeeInfos}">
			                           	<option value="${info.empId }">${info.empName}</option>
			                        </c:forEach>
			                    </select>
                           	</div>
                            </div>
	             		</div>
                 		<div class="row">
	                 		<div class="col-md-2 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-10 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks" name="remarks" class="form-control" maxlength="250" readonly="readonly" placeholder="Description goes here......." ></textarea>
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
    theme: "classic",
    placeholder: "Select from list.."
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

function add(el) {
	$("#id").val("");
	$("#qcReferenceId").val("");
	$("#equipment_name").val("");
	$("#remarks").val("");
    $("#clean_by").val("");
    $("#dissolutionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function editClean(el) {
	var Id 		   = $(el).closest("tr").find("#row_id").val();
	var qcRefId    = $(el).closest("tr").find("#r_qc_reference").val();
	var equipmentId = $(el).closest("tr").find("#r_equipment_name").val();
	var remarks    = $(el).closest("tr").find("#r_remarks").val();
	var cleanBy    = $(el).closest("tr").find("#r_clean_by").val();	

	$("#id").val(Id);
	$("#qcReferenceId").val(qcRefId);
	$("#equipment_name").val(equipmentId);
	$("#remarks").val(remarks);
	$("#clean_by").val(cleanBy).trigger('change.select2');	
			
	$("#dissolutionInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#dissolutionInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#dissolutionInfoForm").serialize();
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/cleanDissolution",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$("#dissolutionInfoModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "error", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

</script>
