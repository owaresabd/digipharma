<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="commanage.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/company/manage?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/company/manage?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="companyList">
						<thead>
							<tr>
								<th class="hidden">ID</th>
								<th class="align-left" style="width: 120px;">COMPANY CODE</th>
								<th class="align-left">COMPANY NAME</th>
								<th class="align-left">COMPANY GROUP</th>
								<th class="align-left">BUSINESS NATURE</th>
								<th class="align-left">FISCAL YEAR</th>
								<th class="align-left">CURRENCY</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="com" items="${companies}">
							<tr>
								<td class="field_id hidden">${com.id}</td>
								<td class="field_company_code align-center">${com.companyCode}</td>
								<td class="field_company_name">${com.companyName}</td>
								<td class="field_company_group">${com.companyGroup}</td>
								<td class="field_business_nature">${com.businessnature}</td>
								<td class="field_fisyear align-center">${com.fisyear}</td>
								<td class="field_currency align-center">${com.currencyName}</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${com.id}">
									<input type="hidden" id="com_code" value="${com.companyCode}">
									<input type="hidden" id="com_name" value="${com.companyName}">
									<input type="hidden" id="com_group" value="${com.companyGroup}">
									<input type="hidden" id="biz_nature" value="${com.businessnature}">
									<input type="hidden" id="curr_code" value="${com.currencyCode}">
									<input type="hidden" id="curr_name" value="${com.currencyName}">
									<input type="hidden" id="fis_month" value="${com.fismonths}">
									<input type="hidden" id="fis_year" value="${com.fisyear}">
									<input type="hidden" id="t_tin" value="${com.tin}">
									<input type="hidden" id="v_vat" value="${com.vat}">
									<input type="hidden" id="r_regno" value="${com.regno}">
									<input type="hidden" id="c_country" value="${com.country}">
									<input type="hidden" id="c_city" value="${com.city}">
									<input type="hidden" id="a_address" value="${com.address}">
									<input type="hidden" id="p_phone" value="${com.phone}">
									<input type="hidden" id="m_mobile" value="${com.mobile}">
									<input type="hidden" id="e_email" value="${com.email}">
									<input type="hidden" id="f_fax" value="${com.fax}">
									<input type="hidden" id="w_website" value="${com.website}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									<a class="btn-delete btn btn-xs" onclick="del(this)"><i class="material-icons">delete_forever</i></a>
								</td>
							</tr>
							</c:forEach>
						 </tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="companyModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header bg-blue-grey">
                	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="defaultModalLabel"><spring:message code="commanage.page.title"/></h4>
                </div>
                <form method="post" id="companyForm" modelAttribute="company">
                	<div class="modal-body">
                 	<div class="row clearfix">
                         <div class="col-md-6">
							<fieldset>
								<legend><spring:message code="com.header1"/></legend>
								<div class="col-md-4">
									<input type="hidden" id="id" name="id" value="">
									<span><b><spring:message code="com.code"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="company_code" name="companyCode" value="" class="form-control" required>
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-8">
									<span><b><spring:message code="com.name"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="company_name" name="companyName" value="" class="form-control" required>
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-6">
	                               <span>
	                                   <b><spring:message code="com.nature"/></b>
	                               </span>
	                               <select id="business_nature" class="form-control show-tick">
	                               		<option value="0">---Select Business Nature---</option>
	                                  	<option value="Banking and Finance">Banking & Finance</option>
	                                  	<option value="Insurance industry">Insurance Industry</option>
	                                  	<option value="Software industry">Software Industry</option>
	                                  	<option value="Telecommunications">Telecommunications</option>
	                                  	<option value="Electronics industry">Electronics Industry</option>
	                                  	<option value="Electrical power industry">Electrical Power Industry</option>
	                                  	<option value="Pharmaceutical industry">Pharmaceutical Industry</option>
	                                  	<option value="Transport">Transport</option>
	                                  	<option value="Steel industry">Steel Industry</option>
	                                  	<option value="Food & Consumer Industry">Food & Consumer Industry</option>
	                               </select>
	                               <input type="hidden" id="businessnature" name="businessnature" value="">
	                            </div>
	                            <div class="col-md-6">
	                               <span>
	                                   <b><spring:message code="com.country"/></b>
	                               </span>
	                               <select id="countryList" class="form-control show-tick">
	                               		<option value="0">---Select Country---</option>
	                                  	<option value="Bangladesh">Bangladesh</option>
	                                  	<option value="India">India</option>
	                                  	<option value="United States of America">United States of America (USA)</option>
	                                  	<option value="England">England (UK)</option>
	                               </select>
	                               <input type="hidden" id="country" name="country" value="">
	                            </div>
	                            <div class="col-md-6 m-t-10">
	                               <span>
	                                   <b><spring:message code="com.city"/></b>
	                               </span>
	                               <select id="cityList" class="form-control show-tick">
	                               		<option value="0">---Select City---</option>
	                                  	<option value="Dhaka">Dhaka</option>
	                                  	<option value="Chittagong">Chittagong</option>
	                                  	<option value="Shylet">Sylhet</option>
	                                  	<option value="Mumbai">Mumbai</option>
	                                  	<option value="Delhi">Delhi</option>
	                                  	<option value="New York">New York</option>
	                                  	<option value="Washington">Washington</option>
	                                  	<option value="London">London</option>
	                                  	<option value="Manchester">Manchester</option>
	                               </select>
	                               <input type="hidden" id="city" name="city" value="">
	                            </div>
	                            <div class="col-md-6 m-t-10">
									<span><b><spring:message code="com.compgroup"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="company_group" name="companyGroup" value="" class="form-control" required>
	                                    </div>
	                                </div>
								</div>
							</fieldset>
						 </div>
						 <div class="col-md-6">
							<fieldset>
								<legend><spring:message code="com.header2"/></legend>
								<div class="col-md-6">
									<span><b><spring:message code="com.phone"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="phone" name="phone" value="" class="form-control" required>
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-6">
									<span><b><spring:message code="com.mobile"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="mobile" name="mobile" value="" class="form-control" required>
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-6">
									<span><b><spring:message code="com.email"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="email" id="email" name="email" value="" class="form-control" required>
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-6">
									<span><b><spring:message code="com.website"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="website" name="website" value="" class="form-control">
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-5">
									<span><b><spring:message code="com.fax"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="fax" name="fax" value="" class="form-control">
	                                    </div>
	                                </div>
								</div>
	                            <div class="col-md-7">
	                                <span><b><spring:message code="com.address"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <textarea rows="3" id="addr" class="form-control" required></textarea>
	                                        <input type="hidden" id="address" name="address" value="">
	                                    </div>
	                                </div>
	                            </div>
							</fieldset>
						 </div>
                         <div class="col-md-12">
							<fieldset>
								<legend><spring:message code="com.header3"/></legend>
								<div class="col-md-4">
									<span><b><spring:message code="com.tin"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="tin" name="tin" value="" class="form-control">
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-4">
									<span><b><spring:message code="com.vat"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="vat" name="vat" value="" class="form-control">
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-4">
									<span><b><spring:message code="com.regno"/></b></span>
	                                <div class="form-group">
	                                    <div class="form-line">
	                                        <input type="text" id="regno" name="regno" value="" class="form-control">
	                                    </div>
	                                </div>
								</div>
								<div class="col-md-4">
	                               <span>
	                                   <b><spring:message code="com.currency"/></b>
	                               </span>
	                               <select id="currency" class="form-control show-tick">
	                               		<option value="0">---Select Currency---</option>
	                                  	<option value="BDT">Taka</option>
	                                  	<option value="Rupee">Rupee</option>
	                                  	<option value="Dollar">Dollar</option>
	                                  	<option value="Pound">Pound</option>
	                                  	<option value="Euro">Euro</option>
	                               </select>
	                               <input type="hidden" id="currency_code" name="currencyCode" value="">
	                               <input type="hidden" id="currency_name" name="currencyName" value="">
	                            </div>
	                            <div class="col-md-4">
	                               <span>
	                                   <b><spring:message code="com.fiscalyear"/></b>
	                               </span>
	                               <select id="fiscalyear" class="form-control show-tick">
	                               		<option value="0">---Select Fiscal-year---</option>
	                               </select>
	                               <input type="hidden" id="fisyear" name="fisyear" value="">
	                            </div>
	                            <div class="col-md-4">
	                               <span>
	                                   <b><spring:message code="com.fiscalmonth"/></b>
	                               </span>
	                               <select id="month" class="form-control show-tick">
	                               		<option value="0">---Select Fiscal-month---</option>
	                                  	<option value="January">January</option>
	                                  	<option value="February">February</option>
	                                  	<option value="March">March</option>
	                                  	<option value="April">April</option>
	                                  	<option value="May">May</option>
	                                  	<option value="June">June</option>
	                                  	<option value="July">July</option>
	                                  	<option value="August">August</option>
	                                  	<option value="September">September</option>
	                                  	<option value="October">October</option>
	                                  	<option value="November">November</option>
	                                  	<option value="December">December</option>
	                               </select>
	                               <input type="hidden" id="fismonths" name="fismonths" value="">
	                            </div>
							</fieldset>
						</div>
                     </div>
                 </div>
                 <div class="modal-footer">
					<button type="button" class="btn bg-red btn-sm waves-effect  pull-right  m-r-10" data-dismiss="modal">
						<i class="material-icons">close</i>
						<span><spring:message code="btn.close"/></span>
					</button>
					<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right  m-r-10">
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
</style>

<script type="text/javascript">

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

	$(function() {
		$('#company_code').focus();
	});
	
	$(function() {
	    $('#company_code').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	});
	
	$(function() {
		var start_year = new Date().getFullYear();
		for (var i = start_year + 2; i > start_year - 10; i--) {
			$('#fiscalyear').append('<option value="' + i + '">' + i + '</option>');
		}
	});
	
	$('#addr').keyup(function() {
		$("#address").val(this.value);
	});
	
	$("#business_nature").on('change', function() {
		$("#businessnature").val($("#business_nature option:selected").val());
	});
	
	$("#countryList").on('change', function() {
		$("#country").val($("#countryList option:selected").val());
	});
	
	$("#cityList").on('change', function() {
		$("#city").val($("#cityList option:selected").val());
	});
	
	$("#currency").on('change', function() {
		$("#currency_code").val($("#currency option:selected").val());
		$("#currency_name").val($("#currency option:selected").text());
	});
	
	$("#fiscalyear").on('change', function() {
		$("#fisyear").val($("#fiscalyear option:selected").val());
	});
	
	$("#month").on('change', function() {
		$("#fismonths").val($("#month option:selected").val());
	});
	
	function add(el) {
		$("#id").val("");
		$("#company_code").val("");
		$("#company_name").val("");
		$("#company_group").val("");
		$("#businessnature").val("");
		$("#country").val("");
		$("#city").val("");
		$("#phone").val("");
		$("#mobile").val("");
		$("#email").val("");
		$("#website").val("");
		$("#fax").val("");
		$("#addr").val("");
		$("#address").val("");
		$("#tin").val("");
		$("#vat").val("");
		$("#regno").val("");
		$("#currency_code").val("");
		$("#currency_name").val("");
		$("#fisyear").val("");
		$("#fismonths").val("");
		$("#business_nature").val(0);
		$("#countryList").val(0);
		$("#cityList").val(0);
		$("#currency").val(0);
		$("#fiscalyear").val(0);
		$("#month").val(0);
		
		$("#companyModal").modal('show');
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
		$("#company_code").focus();
	};
	
	function edit(el) {
		var id = $(el).closest('tr').find("#row_id").val();
		var companyCode = $(el).closest('tr').find("#com_code").val();
		var companyName = $(el).closest('tr').find("#com_name").val();
		var companyGroup = $(el).closest('tr').find("#com_group").val();
		var bizNature = $(el).closest('tr').find("#biz_nature").val();
		var currCode = $(el).closest('tr').find("#curr_code").val();
		var currName = $(el).closest('tr').find("#curr_name").val();
		var fisMonth = $(el).closest('tr').find("#fis_month").val();
		var fisYear = $(el).closest('tr').find("#fis_year").val();
		var tin = $(el).closest('tr').find("#t_tin").val();
		var vat = $(el).closest('tr').find("#v_vat").val();
		var regno = $(el).closest('tr').find("#r_regno").val();
		var country = $(el).closest('tr').find("#c_country").val();
		var city = $(el).closest('tr').find("#c_city").val();
		var address = $(el).closest('tr').find("#a_address").val();
		var phone = $(el).closest('tr').find("#p_phone").val();
		var mobile = $(el).closest('tr').find("#m_mobile").val();
		var email = $(el).closest('tr').find("#e_email").val();
		var fax = $(el).closest('tr').find("#f_fax").val();
		var website = $(el).closest('tr').find("#w_website").val();
		
		$("#id").val(id);
		$("#company_code").val(companyCode);
		$("#company_name").val(companyName);
		$("#company_group").val(companyGroup);
		$("#businessnature").val(bizNature);
		$("#country").val(country);
		$("#city").val(city);
		$("#phone").val(phone);
		$("#mobile").val(mobile);
		$("#email").val(email);
		$("#website").val(website);
		$("#fax").val(fax);
		$("#addr").val(address);
		$("#address").val(address);
		$("#tin").val(tin);
		$("#vat").val(vat);
		$("#regno").val(regno);
		$("#currency_code").val(currCode);
		$("#currency_name").val(currName);
		$("#fisyear").val(fisYear);
		$("#fismonths").val(fisMonth);
		$("#business_nature").val(bizNature);
		$("#countryList").val(country);
		$("#cityList").val(city);
		$("#currency").val(currCode);
		$("#fiscalyear").val(fisYear);
		$("#month").val(fisMonth);
		
		$("#companyModal").modal('show');
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
	};
	
	$("#companyForm").submit(function(event){
		event.preventDefault();				
	    var formData = $("#companyForm").serialize();
	    $.ajax({	
	    	url : "${pageContext.request.contextPath}/company/manage-company",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$("#companyModal").modal('hide');
				$('.modal-backdrop').remove();
				$("#view_page").html(data);
				sweetAlert("Saved!", "Your data has been Saved.", "success", 1000, false);
	        },
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "fail", 1000, false);
		  	}
	    });	
	});

	function del(el) {
		var id = $(el).closest('tr').find("#row_id").val();
		swal({
	        title: "Are you sure?",
	        text: "You will not be able to recover this data!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes, delete it!",
	        cancelButtonText: "No, cancel please!",
	        closeOnConfirm: false,
	        closeOnCancel: false
	    }, function (isConfirm) {
	        if (isConfirm) {
				$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/company/delete-company/" + id ,
					success : function(data) {
						$("#view_page").html(data);
						sweetAlert("Deleted!", "Deleted Successfully", "success", 1000, false);
					},
					error: function(){
						sweetAlert("Failed!", "Something going wrong.", "fail", 1000, false);
				  	}
				});
	        } else {
	        	sweetAlert("Cancelled", "Your data is safe :)", "error", 0, false);
	        }
	    });
	}
	
</script>

<!-- Jquery DataTable Plugin Js -->
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery-datatable/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>