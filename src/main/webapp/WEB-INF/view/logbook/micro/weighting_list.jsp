<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">WEIGHTING LIST</span>
		
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
								<th class="align-left" >MEDIUM &amp; REAGENT NAME</th>
								<th class="align-left" >LAB BATCH NO.</th>
								<th class="align-center" >BATCH SIZE</th>
								<th class="align-center" >PREPARATION DATE</th>
								<th class="align-center" >EXP. DATE</th>
								<th class="align-center" >ACTION</th>
							</tr>
						</thead>
						 <tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="align-left">
								${info.reagentName}
								</td>
								<td class="align-left">${info.batchNo}</td>
								<td class="align-center">${info.batchSize}</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd.MMM.yyyy" value="${info.prepDate}" var="dateOfPrep" />
								${dateOfPrep}
								</td>
								<td class="align-center">
								<fmt:formatDate pattern="dd.MMM.yyyy" value="${info.expDate}" var="expDate" />
								${expDate}
								</td>
								<td class="align-center">
								<c:choose>
									    <c:when test="${info.isWeight =='Y'}">
											<a class="btn-edit btn btn-xs" onclick="edit(this)">
												<i class="material-icons">mode_edit</i>
											</a>
									    </c:when>
									    <c:when test="${info.isWeight =='N'}"> 
											<a class="btn-edit btn btn-xs" onclick="weightingInfo(this)"><i class="material-icons">forward</i></a>
									    </c:when>     
									    
									</c:choose>
									
									
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_reagent_id" value="${info.reagentId}">
									<input type="hidden" id="r_batch_no" value="${info.batchNo}">
									<input type="hidden" id="r_batch_size" value="${info.batchSize}">
									<input type="hidden" id="r_prep_date" value="${info.prepDate}">
									<input type="hidden" id="r_exp_date" value="${info.expDate}">
									
									
								</td>
							</tr>
							</c:forEach>
						</tbody> 
					</table>
				</div>
			</div>
		</div>
	</div>

<div class="modal fade" id="infoModal" style="overflow: auto;" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
             <div class="modal-content">
                  <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:100px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Medium &amp; Reagent Preparation </td>
                 	<td style="width:90px">Document No.</td>
                 	<td colspan="2" style="width:120px">LB-DIL-MC-030</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 OCT 2019</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="infoForm">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="equipment_id">MEDIUM &amp; REAGENT NAME :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
	                            	<select id="reagentId" name="reagentId" class="js-example-theme-single form-control" onchange="getBatchInfo(this.id);" style="width: 100%;" required="required">
				                        	<option value="">-Select-</option>
				                        <c:forEach var="info" items="${mediumInfos}">
				                           	<option value="${info.id }">${info.reagentName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
                 			<div class="col-md-3 align-right top">
                            	<label for="qc_reference">LAB BATCH NO. :</label>
                            </div>
                            <div class="col-md-3">
                           	<div class="form-group">
                           		<input type="hidden" id="batchSeqNo" name="batchSeqNo" value=""/>
                                <input type="text" id="batchNo" name="batchNo" value="" class="form-control" style="width: 100%;" autocomplete="off" readonly="readonly">
                           	</div>
                            </div>
                 		</div>
						<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="lot_no">BATCH SIZE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="batchSize" name="batchSize" value="" class="form-control" style="width: 100%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 			<div class="col-md-3 align-right top">
                            	<label for="temperature">PREPARATION DATE :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="prepDate" name="prepDate" value="" class="form-control dates" style="width: 60%;" autocomplete="off" required="required">
                           	</div>
                            </div>
                 		</div>
                 	 
						<div class="row">
	                 		<div class="col-md-3 align-right top">
                            	<label for="ph_result">Exp. Date :</label>
                            </div>
                            <div class="col-md-3">
                            <div class="form-group">
                                <input type="text" id="expDate" name="expDate" value="" class="form-control dates" style="width: 60%;" autocomplete="off" required="required">
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
	<div class="modal fade" id="weightingInfoModal" style="overflow: auto !important;"  role="dialog" data-backdrop="static" data-keyboard="false"  >
         <div id="modalId" class="modal-dialog modal-lg" role="document">
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








function weightingInfo(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
	    url : "${pageContext.request.contextPath}/logbook_micro/weightingInfo",
		success : function(data) {
			$('#weightingInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			$("#preparationId").val(id);
			
			}
		});
}

</script>
