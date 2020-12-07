<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">COUNTRY INFO</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/transport/route?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/transport/route?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="countryList">
						<thead>
							<tr>
								<th class="align-center" style="width: 120px;">COUNTRY CODE</th>
								<th class="align-center">COUNTRY NAME</th>
								<th class="align-center">MAIN CURRUNCY</th>
								<th class="align-center">MAIN LANGUAGE</th>
								<th class="align-center">TOTAL PEOPLE</th>
								<th class="align-center">GOVT CODE</th>
								<th class="align-center">NATIONALITY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 80px;">STATUS</th>
								<th class="align-center" style="width: 80px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}">
							<tr>
								<td class="align-center">${info.udCountryCode}</td>
								<td class="align-center">${info.countryName}</td>
								<td class="align-center">${info.mainCurruncy}</td>
								<td class="align-center">${info.mainLangiage}</td>
								<td class="align-center">${info.totalPepole}</td>
								<td class="align-center">${info.govtCode}</td>
								<td class="align-center">${info.nationality}</td>
								<td class="align-center">${info.remarks}</td>
								<td class="align-center" width="100px">
									<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
									</c:choose>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${info.id}">
									<input type="hidden" id="r_ud_country_code" value="${info.udCountryCode}">
									<input type="hidden" id="r_country_name" value="${info.countryName}">
									<input type="hidden" id="r_main_curruncy" value="${info.mainCurruncy}">
									<input type="hidden" id="r_main_langiage" value="${info.mainLangiage}">
									<input type="hidden" id="r_total_pepole" value="${info.totalPepole}">
									<input type="hidden" id="r_govt_code" value="${info.govtCode}">
									<input type="hidden" id="r_nationality" value="${info.nationality}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_status" value="${info.status}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="countryEntryModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title align-center" id="defaultModalLabel">COUNTRY ENTRY INFO</h4>
                 </div>
                 <form method="post" id="countryEntryForm" modelAttribute="countryInfo">
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>COUNTRY CODE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="ud_country_code" name="udCountryCode" value="" class="form-control" placeholder="Country Code" maxlength="16" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>COUNTRY NAME :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="country_name" name="countryName" value="" class="form-control" placeholder="Country Name" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>MAIN CURRUNCY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="main_curruncy" name="mainCurruncy" value="" class="form-control" placeholder="Main Curruncy" maxlength="50" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>MAIN LANGUAGE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="main_langiage" name="mainLangiage" value="" class="form-control" placeholder="Main Language" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>TOTAL PEOPLE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="total_pepole" name="totalPepole" value="" class="form-control" placeholder="Total People" maxlength="10" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>GOVT CODE :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="govt_code" name="govtCode" value="" class="form-control" placeholder="Govt Code" maxlength="10" autocomplete="off">
                            	</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-6">
                            	<span><b>NATIONALITY :</b></span>
                            	<div class="form-group">
	                                <input type="text" id="nationality" name="nationality" value="" class="form-control" placeholder="Nationality" autocomplete="off">
                            	</div>
                            </div>
                            <div class="col-md-6">
                            	<span><b>ACTIVITY STATUS :</b></span>
                            	<div class="demo-checkbox m-t-5">
									<input type="checkbox" id="activity_status" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Inactive ?</span></b></label>
									<input type="hidden" id="status" name="status" value="N">
								</div>
                            </div>
                 		</div>
                 		<div class="row">
                            <div class="col-md-12">
                            	<span><b>REMARKS :</b></span>
                            	<div class="form-group">
                                	<textarea rows="2" id="remarks_detail" class="form-control" placeholder="Country description goes here......."></textarea>
                                	<input type="hidden" id="remarks" name="remarks" value="">
	                            </div>
                            </div>
                 		</div>
                 		
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
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

$(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search from list..",
	dropdownParent: $("#countryEntryModal")
});

$('#remarks_detail').keyup(function() {
	$("#remarks").val(this.value);
});

$('#activity_status').change(function() {
	if (this.checked) {
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
});

$("#area_list").on('change', function(){
	var id = $('option:selected', this).val();
	$("#area_id").val(id);
	$(".alert").empty().addClass("hidden");
	setDistrictName(id);
});

$("#unit_list").on('change', function(){
	var value = $('option:selected', this).val();
	$("#dist_unit").val(value);
	$(".alert").empty().addClass("hidden");
});

function setDistrictName(id){
	$.get( "${pageContext.request.contextPath}/transport/area-info-detail/" + id, 
	function( data ) {
		for (var i = 0; i < data.length; i++) {
			$('#dist_name').val(data[i].distName);
		}
	});
}

$('.number').keypress(function (event) {
    return isNumber(event, this)
});


function add(el) {
	$("#id").val("");
	$("#ud_country_code").val("");
	$("#country_name").val("");
	$("#main_curruncy").val("");
	$("#main_langiage").val("");
	$("#total_pepole").val("");
	$("#govt_code").val("");
	$("#nationality").val("");
	
	$("#remarks_detail").val("");
	$("#remarks").val("");
    $("#countryEntryModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
}

function edit(el) {
	var Id 				= $(el).closest("tr").find("#row_id").val();
	var udCountryCode 	= $(el).closest("tr").find("#r_ud_country_code").val();
	var countryName 	= $(el).closest("tr").find("#r_country_name").val();
	var mainCurruncy 	= $(el).closest("tr").find("#r_main_curruncy").val();
	var mainLangiage 	= $(el).closest("tr").find("#r_main_langiage").val();
	var totalPepole 	= $(el).closest("tr").find("#r_total_pepole").val();
	var govtCode 		= $(el).closest("tr").find("#r_govt_code").val();
	var nationality 	= $(el).closest('tr').find("#r_nationality").val();
	var Remarks 		= $(el).closest("tr").find("#r_remarks").val();
	var Status 			= $(el).closest('tr').find("#r_status").val();
	
	$("#id").val(Id);
	$("#ud_country_code").val(udCountryCode);
	$("#country_name").val(countryName);
	$("#main_curruncy").val(mainCurruncy);
	$("#main_langiage").val(mainLangiage);
	$("#total_pepole").val(totalPepole);
	$("#govt_code").val(govtCode);
	$("#nationality").val(nationality);
	$("#remarks_detail").val(Remarks);
	$("#remarks").val(Remarks);
	
	if(Status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#countryEntryModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
};

$("#countryEntryForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#countryEntryForm").serialize();
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
    if($(".alert").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/country/save-country-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {			 
	        	$("#countryEntryModal").modal('hide');
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
}  else {
	sweetAlert("Cancelled", "", "error", 0, false);
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
				url : "${pageContext.request.contextPath}/transport/delete-route-infos/" + id ,
				success : function(data) {
					$("#view_page").html(data);
					sweetAlert("Deleted!", "Deleted Successfully", "success", 1000, false);
				},
				error: function(){
					sweetAlert("Failed!", "Something going wrong.", "fail", 1000, false);
			  	}
			});
        } else {
        	sweetAlert("Cancelled", "Your data is safe :)", "error", 1000, false);
        }
    });
}
</script>