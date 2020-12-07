<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<!------------------------ Start: Create Product Color Types Modal -------------------->

<button type="button" class="mod-cl p-r-10 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
<div class="modal-header bg-cyan">
    <table class="table table-bordered" style="border: 2px solid #ddd;">
    	<tbody style="border: 2px solid #ddd;">
    	<tr>
    	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
    	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
    	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form : Weighting Information</td>
    	<td>Document No.</td>
    	<td colspan="2">FM-WH-001</td>
    	</tr>
    	<tr style="border: 2px solid #ddd;">
    	<td>Revision No.</td>
    	<td style="text-align:center; width:35px">00</td>
    	<td>Page 1 of 1</td>
    	</tr>
    	<tr style="border: 2px solid #ddd;">                 	
    	<td>Effective Date</td>
    	<td colspan="2">15 Jan 2020</td>
    	</tr>
    	</tbody>
    </table>
</div>

                <form method="post" id="weightingInfoForm" name="weightingInfoForm"  onkeypress="if (event.keyCode == 13) { return false; }">
               <div class="modal-body ">
                <div class="row">
	                 		<div class="col-md-2 align-left">
	                 		<input style="width: 100%;" type="hidden" id="preparationId" name="preparationId" value="" />
                            	<label for="equipment_id">BALANCE ID  :</label>
                            </div>
                            <div class="col-md-3">
	                            <div class="form-group">
	                            	<select id="balanceId" name="balanceId" class="js-example-theme-single form-control" style="width: 100%;" required="required">
				                       	<option value="">Select Equipment</option>
				                        <c:forEach var="info" items="${equipInfos}">
				                           	<option value="${info.id }">${info.equipmentName}</option>
				                        </c:forEach>
				                    </select>
	                            </div>
                            </div>
                 		</div>
                 	<div class="row">
                        	<div class="col-md-12">
                           	<div class="form-group">
                            	<table id="weightingTable" class="table table-bordered table-striped table-hover"> 
									<thead>
										<tr>
											<th class="align-center" style="width: 130px;">Media &amp; Reagent Code No. </th>
											<th class="align-center" style="width: 150px;">Raw Material </th>
											<th class="align-center" style="width: 100px;">Batch/ Lot No.</th>
											<th class="align-center" style="width: 80px;">Exp. Date</th>
											<th class="align-center" style="width: 60px;">Net Weight</th>
											<th class="align-center" style="width: 70px;">Unit</th>
											<th class="align-center" style="width: 60px;">####</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="align-center">
											<!-- <input type="hidden" id="childId" value=""/> -->
											<select id="codeNo" class="js-example-theme-single form-control"  onchange="setInformation(this.id);"   style="width: 100%;">
				                        	<option value="">Select Code No.</option>
				                        <c:forEach var="info" items="${infos}">
				                           	<option value="${info.id }@${info.materialsId }@${info.materialsName}@${info.batchNo}@${info.expDate }">${info.codeNo}</option>
				                        </c:forEach>
				                    </select>
											</td>
											<td class="align-center">
											<input style="width: 100%;" type="hidden" id="materialId"  />
												<input style="width: 100%;" type="text" id="materialName" readonly="readonly"  />
											</td>
											<td class="align-center">
											
												<input style="width: 100%;" type="text" id="batchNo1"  readonly="readonly"/>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="expDate1" readonly="readonly" />
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="qty" class="number"/>
											</td>
											<td class="align-center">
											<select id="unitId"  class="js-example-theme-single form-control" style="width: 100%;">
				                        	<option value="">Select Unit</option>
						                        <c:forEach var="unitInfo" items="${unitList}">
						                           	<option value="${unitInfo.id }">${unitInfo.unitCode}</option>
						                        </c:forEach>
				                 			</select>
												
											</td>
											
											<td class="align-center">
												<button type="button" id="addBtn" class="btn bg-blue btn-xs waves-effect" onclick="addRow(this)"> 
													<i class="material-icons">add</i>
												</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
                           	</div>
                           	
                		</div>
	                 <div class="modal-footer save-footer" style="background-color: #cff0f5;">
	                 	<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10 md">
							<i class="material-icons">input</i>
							<span>SAVE</span>
						</button>						
	                 </div>
	                 </div>
                 </form>

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
.sweet-alert button.cancel {
    background-color: #E5342C;
}
</style>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script>
$(".js-example-theme-single").select2({
    theme: "classic"
});
function setInformation(id){
	var idVal=$("#"+id).val();
    var valSplt=idVal.split('@');
     $('#materialId').val(valSplt[1]);
	 $('#materialName').val(valSplt[2]);
	 $('#batchNo1').val(valSplt[3]);
	 $('#expDate1').val((valSplt[4]!=''?convertMmDate(valSplt[4]):''));
}
var aTable = $('#weightingTable').DataTable({
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

$('#weightingTable_length, #weightingTable_info, #weightingTable_paginate, #weightingTable_filter').hide();
function addRow(el){
	//var childId=$("#childId").val();
	var codeNo=$('#codeNo option:selected').text();
	var materialId= $("#materialId").val();
	var materialName= $("#materialName").val();
	var batchNo  = $("#batchNo1").val();
	var expDate  = $("#expDate1").val();
	var qty 	 = $("#qty").val();
	var unitId 	 = $("#unitId").val();
	var unitNm 	 = $('#unitId option:selected').text();
	
	var appendRow=false;
	var html = '' 
			+ '<input type="hidden"  name="codeNo[]" value="'+codeNo+'"/>'
			+ '<input type="hidden" id="i_material_id"  name="materialId[]" value="'+materialId+'"/>'
			+ '<input type="hidden" id="i_material_nm"   value="'+materialName+'"/>'
			+ '<input type="hidden" id="i_batch_no"  name="batchNo[]" value="'+batchNo+'"/>'
			+ '<input type="hidden" id="i_exp_date"  name="expDate[]" value="'+expDate+'"/>'
			+ '<input type="hidden" id="i_qty" name="quantity[]" value="'+qty+'"/>'
			+ '<input type="hidden" id="i_unitId" name="unitId[]" value="'+unitId+'"/>'
			+ '<input type="hidden" id="i_unitNm" value="'+unitNm+'"/>'
			+ '<a class="btn-edit btn btn-xs" onclick="editRow(this)"><i class="material-icons">mode_edit</i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
		
		if( codeNo != '' && materialName != '' && batchNo != '' && expDate !='' && qty != '' && unitId!=''){
		 appendRow=true;
		 if(appendRow=true){
				var rowNode = aTable.row.add([codeNo, materialName, batchNo, expDate, qty, unitNm,  html]).draw( false ).node();
				aTable.draw();
				aTable.page('last').draw(false);
				$(rowNode).find("td:eq(0)").css({"text-align": "left"});
				$(rowNode).find("td:eq(1)").css({"text-align": "left"});
				$(rowNode).find("td:eq(2)").css({"text-align": "left"});
				$(rowNode).find("td:eq(3)").css({"text-align": "center"});
				$(rowNode).find("td:eq(4)").css({"text-align": "center"});
				$(rowNode).find("td:eq(5)").css({"text-align": "center"});
				
				$("#codeNo").select2("val", '');
				$("#materialId").val('');
			    $("#materialName").val('');
				$("#batchNo1").val('');
				$("#expDate1").val('');
				$("#qty").val('');
				$("#unitId").select2("val", '');
				
				}
				
		
		}else{
			sweetAlert("Failed!", "Input all Fields.!", "warning", 5000, false);
		}
}
$("#weightingInfoForm").submit(function(event){
	event.preventDefault();				
	var formData = $("#weightingInfoForm").serialize();
	swal({
        title: "Are you sure?",
        text: "Your request will be sent to CSD!!!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#00973E",
        confirmButtonText: "Yes",
        cancelButtonText: "No",
        closeOnConfirm: true,
        closeOnCancel: true
    }, function (isConfirm) {
    	if (isConfirm) {
    	if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/logbook_micro/saveWeightingInfo",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#weightingInfoModal").modal('hide');
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
    } else {
    	sweetAlert("Cancelled", "", "", 0, false);
    }
    });
});

</script>
