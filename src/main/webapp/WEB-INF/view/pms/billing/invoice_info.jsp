<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code="" />
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="row clearfix">
		<div class="col-md-12">
			<div class="card">

				<div class="body">
					<div class="row clearfix">
						<div class="col-xs-12 ol-sm-12 col-md-12 col-lg-12">
							<div class="panel-group" id="accordion_9" role="tablist"
								aria-multiselectable="true">
								<div class="panel panel-col-teal">

									<div id="collapseOne_9" class="panel-collapse collapse in"
										role="tabpanel" aria-labelledby="headingOne_9">
										<div class="panel-body">

											<div class="row">
												<div class="col-md-10">
													<div class="panel panel-col-teal">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a role="button" data-toggle="collapse"
																	aria-expanded="true"> INVOICE INFORMATION </a>
															</h4>
														</div>
														<div id="collapseOne_9" class="panel-collapse collapse in"
															role="tabpanel" aria-labelledby="headingOne_9">
															<div class="panel-body">
																<div class="row">
                 		
                 			<div class="col-md-2">
                 				<span><b>INVOICE NO</b></span>
                               	<div class="form-group">
	                                 <div class="form-line">
	                                     <input type="text" id="invoiceNo" name="invoiceNo" value="${invoiceNo}" class="form-control" readonly="readonly">
	                                 </div>
	                             </div>
                 			</div>
                 			<div class="col-md-2">
                            	<span><b>INVOICE DATE</b></span>
                               	<div class="form-group">
	                                 <div class="form-line">
	                                     <input type="text" id="invoiceDate" name="invoiceDate" value="" class="form-control" readonly="readonly">
	                                 </div>
	                             </div>
                            </div>
                            <div class="col-md-4">
                            	<span><b>PATIENT NAME</b></span>
                               	<div class="form-group">
	                                 <div class="form-line">
	                                     <input type="text" id="invoiceDate" name="invoiceDate" value="" class="form-control">
	                                 </div>
	                             </div>
                            </div>
                            
                            <div class="col-md-2">
                            	<span><b>&nbsp;</b></span>
                               	<div class="form-group">
                               		<button type="button" id="addBtn" class="btn bg-indigo btn-sm waves-effect pull-left" onclick="addRow(this)">
							<i class="material-icons aa">add</i>
						</button>
								</div>
                            </div>
                            
							
                 		</div>
                 		
                 		<div class="row">
                 		<div class="col-md-4">
                            	<span><b>TEST NAME</b></span>
                               	<div class="form-group">
	                                 <select  id=testId name="testId" class="js-example-theme-single form-control" >
			                        	<option></option>
			                        <c:forEach var="info" items="${testInfos}">
			                           	<option value="${info.id }">${info.testName}</option>
			                        </c:forEach>
			                        </select>
	                             </div>
                            </div>
                 		</div>
                 		
                 		<div class="row">
                 		
                 			<div class="panel panel-col-teal">
														
														
															
																
						<table class="table table-bordered table-striped table-hover" id="repairInfo">
							<thead>
								<tr>
									<th class="align-center">TEST NAME</th>
									<th class="align-center">RATE</th>
									<th class="align-center">QUANTITY</th>
									<th class="align-center">AMOUNT</th>
									<th class="align-center" style="width: 50px;">ACTION</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th class="align-center">
										 <div class="form-group">
	                                 <input class="form-control dates" id="po_date" name="poDate" value="" autocomplete="off"/>
                            	</div>
									</th>
									<th class="align-center">
									 <input class="form-control dates" id="po_date" name="poDate" value="" autocomplete="off"/>
									</th>
									<th class="align-center">
									  <input class="form-control dates" id="po_date" name="poDate" value="" autocomplete="off"/>
									 </th>
									<th class="align-center">
										<input class="form-control dates" id="po_date" name="poDate" value="" autocomplete="off"/>
									</th>
									<th class="align-center" style="width: 50px;">
										<button type="button" id="addBtn" class="btn bg-indigo btn-sm waves-effect pull-left" onclick="addRow(this)">
							<i class="material-icons aa">add</i>
						</button>
									</th>
							</tbody>
						</table>
           			

															
														
													</div>
                 		</div>
															
															</div>
														</div>
													</div>
												</div>



												<div class="col-md-2">
													<div class="panel panel-col-teal">
														<div class="panel-heading">
															<h4 class="panel-title">
																<a role="button" data-toggle="collapse"
																	aria-expanded="true"> AMOUNT DETAILS </a>
															</h4>
														</div>
														<div id="collapseOne_9" class="panel-collapse collapse in"
															role="tabpanel" aria-labelledby="headingOne_9">
															<div class="panel-body">
																<div class="row">
																	<div class="col-md-12">
																		<span><b>SUB TOTAL</b></span>
																		<div class="form-group">

																			<input type="text" id="po_no" name="poNo" value=""
																				class="form-control" readonly="readonly">

																		</div>
																	</div>
																	<div class="col-md-12">
																		<span><b>VAT</b></span>
																		<div class="form-group">

																			<input type="text" style="width: 49%;" id="po_no"
																				name="poNo" value="" class="form-control"
																				maxlength="3" autocomplete="off"><span>%</span><input
																				type="text" style="width: 49%;" id="po_no"
																				name="poNo" value="" class="form-control"
																				maxlength="3" autocomplete="off">

																		</div>
																	</div>
																	<div class="col-md-12">
																		<span><b>DISCOUNT</b></span>
																		<div class="form-group">

																			<input type="text" id="po_no" name="poNo" value=""
																				class="form-control"
																				 maxlength="6"
																				autocomplete="off" required>

																		</div>
																	</div>
																	
																	<div class="col-md-12">
																		<span><b>TOTAL</b></span>
																		<div class="form-group">

																			<input type="text" id="po_no" name="poNo" value=""
																				class="form-control"
																				  maxlength="6"
																				autocomplete="off" required>

																		</div>
																	</div>
																	<div class="col-md-12">
																		<span><b>PAID</b></span>
																		<div class="form-group">

																			<input type="text" id="po_no" name="poNo" value=""
																				class="form-control"
																				  maxlength="6"
																				autocomplete="off" required>

																		</div>
																	</div>
																	<div class="col-md-12">
																		<span><b>DUE</b></span>
																		<div class="form-group">

																			<input type="text" id="po_no" name="poNo" value=""
																				class="form-control"
																				  maxlength="6"
																				autocomplete="off" required>

																		</div>
																	</div>



																</div>

															</div>
														</div>
													</div>
												</div>
											</div>





										</div>
									</div>
								</div>


							</div>
						</div>






					</div>
				</div>
			</div>

		</div>





	</div>
</div>
<style>

.select2-container {
    width: 100% !important;
}
.mod-cl {
	color: transparent;
	opacity: 1;
}

.alert-code {
	color: white;
}

.top {
	margin-top: 5px !important;
}

html {
	overflow-y: scroll;
}

body {
	padding-right: 0px !important;
	margin-right: 0px !important;
}

body.modal-open {
	overflow: auto;
}

input, textarea {
	border: 1px solid #c8c7cc;
	border-radius: 4px !important;
}

input {
	height: 28px !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	vertical-align: middle !important;
}

[type="checkbox"]+label {
	height: 10px;
	font-size: 12px;
}

.dates {
	background-color: mintcream;
	border: 1px solid #c8c7cc;
	border-radius: 5px !important;
}
</style>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>
<script>
	function chngLang(el) {
		var link = $(el).attr('id');
		$.ajax({
			async : false,
			url : link,
			success : function(result) {
				$("#view_page").html(result);
			},
			error : function() {
				console.log("this employee has no user id.");
			}
		});
	}
	$(".js-example-theme-single").select2({

		theme : "classic"
	});

	$(".modal-header")
			.on(
					"mousedown",
					function(mousedownEvt) {
						var $draggable = $(this);
						var x = mousedownEvt.pageX - $draggable.offset().left, y = mousedownEvt.pageY
								- $draggable.offset().top;
						$("body")
								.on(
										"mousemove.draggable",
										function(mousemoveEvt) {
											$draggable
													.closest(".modal-dialog")
													.offset(
															{
																"left" : mousemoveEvt.pageX
																		- x,
																"top" : mousemoveEvt.pageY
																		- y
															});
										});
						$("body").one("mouseup", function() {
							$("body").off("mousemove.draggable");
						});
						$draggable.closest(".modal").one("bs.modal.hide",
								function() {
									$("body").off("mousemove.draggable");
								});
					});

	$("#testId").on('change', function() {
		var id = $('option:selected', this).val();
		$.get( "${pageContext.request.contextPath}/test/" + id, 
		function( data ) {
			console.log(data[0].testCode);
			
			
		});
	});

	

	$('#activity_status').change(function() {
		if (this.checked) {
			$('#status').val('Y');
			$('.check-status').text('Active ?');
		} else {
			$('#status').val('N');
			$('.check-status').text('Inactive ?');
		}
	});
	function getTestById(id){
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/itemIssue/itemInfoById?itemId=" + id,
			dataType : 'json',
			success : function(data) {
				for(var i=0; i<data.length; i++){
					var price = data[i].unitPrice;
					var uomId = data[i].uomNo;
					var uomName = data[i].uomName;
					var batchRequired = data[i].batchRequired;
					var expireable = data[i].expireable;
					
					$("#uom_id").val(uomId);
					$("#uom_name").val(uomName);
					$('#uom_list').val(uomId).trigger('change.select2');
					
				}
			}
		});
	}
	function add(el) {

		$("#id").val("");
		$("#supplierCode").val("");
		$("#supplierName").val("");
		$("#supplierTypeId").select2("val", "");
		$("#address").val("");
		$("#contactPerson").val("");
		$("#designation").val("");
		$("#phoneNumber").val("");
		$("#mobile").val("");
		$("#email").val("");
		$("#website").val("");
		$("#activity_status").prop('checked', false);
		$('#status').val('N');
		$("#supplierInfoModal").modal();
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
		$(".alert").empty().addClass("hidden");
		$(".alert-code").empty().addClass("hidden");
	}

	function edit(el) {

		var Id = $(el).closest("tr").find("#row_id").val();
		var supplierCode = $(el).closest("tr").find("#f_supp_code").val();
		var supplierName = $(el).closest("tr").find("#f_supp_name").val();
		var supplierTypeId = $(el).closest("tr").find("#f_type_id").val();
		var address = $(el).closest("tr").find("#f_address").val();
		var contactPerson = $(el).closest("tr").find("#f_person").val();
		var designation = $(el).closest("tr").find("#f_desig").val();
		var phoneNumber = $(el).closest("tr").find("#f_phone").val();
		var mobile = $(el).closest("tr").find("#f_mobile").val();
		var email = $(el).closest("tr").find("#f_email").val();
		var website = $(el).closest("tr").find("#f_website").val();
		var status = $(el).closest('tr').find("#f_status").val();

		$("#id").val(Id);
		$("#supplierCode").val(supplierCode);
		$("#supplierName").val(supplierName);
		$("#supplierTypeId").select2("val", supplierTypeId);
		//$("textarea#ExampleMessage").val(result.exampleMessage);
		$("#address").val(address);
		$("#contactPerson").val(contactPerson);
		$("#designation").val(designation);
		$("#phoneNumber").val(phoneNumber);
		$("#mobile").val(mobile);
		$("#email").val(email);
		$("#website").val(website);
		if (status == 'Y') {
			$('#activity_status').prop('checked', true);
			$('#status').val('Y');
			$('.check-status').text('Active');
		} else {
			$('#activity_status').prop('checked', false);
			$('#status').val('N');
			$('.check-status').text('Inactive');
		}

		$("#supplierInfoModal").modal();
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
		$(".alert").empty().addClass("hidden");
		$(".alert-code").empty().addClass("hidden");
	};

	$("#supplierInfoForm")
			.submit(
					function(event) {
						event.preventDefault();
						var formData = $("#supplierInfoForm").serialize();

						if ($(".alert-code").hasClass('hidden')) {
							$
									.ajax({
										url : "${pageContext.request.contextPath}/supplier/save-suppliers",
										type : 'POST',
										data : formData,
										async : false,
										success : function(data) {
											$("#supplierInfoModal").modal(
													'hide');
											$('.modal-backdrop').remove();
											$("#view_page").html(data);
											sweetAlert(
													"Saved!",
													"Your data has been Saved.",
													"success", 1000, false);
										},
										error : function() {
											sweetAlert("Failed!",
													"Something went wrong.",
													"fail", 1000, false);
										}
									});
						} else {
							sweetAlert(
									"Failed!",
									"Please remove all error, then submit again.",
									"warning", 1000, false);
						}
					});
</script>