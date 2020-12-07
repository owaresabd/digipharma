<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">

<style type="text/css">

        
textarea {
  resize: none;
}
</style>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">EMPLOYEE PROFILE</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/employee/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/employee/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<div class="modal-body">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h1 class="panel-title">General Information</h1>
							</div>

							<div class="panel-body">
								<div class="col-md-9">
									<div class="col-md-4">
										<span><b>EMPLOYEE ID :</b></span>
										<div class="form-group">${info.udEmpNo}</div>
									</div>
									
									<div class="col-md-4">
										<span><b>EMPLOYEE NAME :</b></span>
										<div class="form-group">${info.empName}</div>
									</div>
									<div class="col-md-4">
										<span><b>DESIGNATION :</b></span>
										<div class="form-group">${info.desigName}</div>
									</div>
								
									<div class="col-md-4">
										<span><b> FATHER'S NAME :</b></span>
										<div class="form-group">${info.fatherName}</div>
									</div>
									<div class="col-md-4">
										<span><b>MOTHER'S NAME :</b></span>
										<div class="form-group">${info.motherName}</div>
									</div>
									<div class="col-md-4">
										<span><b>DATE OF BIRTH :</b></span>
										<div class="form-group">
										<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.dob}" var="dob"/>
	                              			${dob}
										</div>
									</div>
								
									<div class="col-md-4">
										<span><b> GENDER :</b></span>
										<div class="form-group">
										<c:choose>
										    <c:when test="${info.genderId eq 'M'}">Male</c:when>
										    <c:when test="${info.genderId eq 'F'}">Female</c:when>     
									    </c:choose>
										</div>
									</div>
									<div class="col-md-4">
										<span><b>BLOOD GROUP :</b></span>
										<div class="form-group">${info.bloodGroup}</div>
									</div>
									<div class="col-md-4">
										<span><b>MARITAL STATUS :</b></span>
										<div class="form-group">
										<c:choose>
										    <c:when test="${info.maritalStatus eq '1'}">Unmarried</c:when>
										    <c:when test="${info.maritalStatus eq '2'}">Married</c:when>     
									    </c:choose>
	                              		</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
									<label>Employee's Picture :</label>
									<img src="${pageContext.request.contextPath}/image/employee_info_doc/${info.id}-${info.employeeImage}" id='view-img-upload' height="120" alt="No image" 
                  							 onerror="this.src='${pageContext.request.contextPath}/image/employee_info_doc/default-image.png';"/>
									
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> ADDRESS :</b></span>
										<div class="form-group">${info.address}</div>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> NID :</b></span>
										<div class="form-group">${info.nid}</div>
									</div>
									<div class="col-md-4">
										<span><b>MOBILE NO :</b></span>
										<div class="form-group">${info.mobileNo}</div>
									</div>
									<div class="col-md-4">
										<span><b>EMAIL :</b></span>
										<div class="form-group">${info.email}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> QUALIFICATION :</b></span>
										<div class="form-group">${info.qualification}</div>
									</div>
									<div class="col-md-4">
										<span><b>JOINING DATE :</b></span>
										<div class="form-group">
										<fmt:formatDate pattern="dd-MMM-yyyy" value="${info.joiningDate}" var="joiningDate" />
	                              			${joiningDate}
										</div>
									</div>
									<div class="col-md-4">
										<span><b>EXPERIENCE :</b></span>
										<div class="form-group">${info.experience}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> REGISTRATION NO :</b></span>
										<div class="form-group">${info.regNo}</div>
									</div>
									<div class="col-md-4">
										<span><b>GRADE :</b></span>
										<div class="form-group">${info.gradeName}</div>
									</div>
									<div class="col-md-4">
										<span><b>REPORTING TO :</b></span>
										<div class="form-group">${info.reportingName}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<span><b> SUPERVISOR'S SUPERIOR : :</b></span>
										<div class="form-group">${info.superiorName}</div>
									</div>
									<div class="col-md-4">
										<span><b>SUPERVISOR: :</b></span>
										<div class="form-group">${info.supervisorName}</div>
									</div>
									<div class="col-md-4">
										<span><b>SUBORDINATE :</b></span>
										<div class="form-group">${info.subordinateName}</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-8">
										<span><b> RELATIONSHIP :</b></span>
										<div class="form-group">
										${info.relationship}
											
										</div>
									</div>
									<div class="col-md-4">
										<span><b> ACTIVITY STATUS :</b></span>
										<div class="form-group">
										<c:choose>
									    <c:when test="${info.status =='Y'}">
											<span class="badge bg-green">Active</span>
									    </c:when>    
									    <c:otherwise>
									        <span class="badge bg-red">Inactive</span>
									    </c:otherwise>
									</c:choose>
											
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

.div-content {
	min-height: 470px !important;
}

.btn-file, .multi {
    position: relative;
    overflow: hidden;
}
.btn-file input[type=file], .multi input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
}

#img-upload, #img-upload1, #img-upload2, #img-upload3 {
    width: 100%;
}

.btn:not(.btn-link):not(.btn-circle) i {
    top: 2px;
}

.dates {
    background-color: mintcream;
   	border: 1px solid #c8c7cc;
   	border-radius: 5px !important;
}
</style>
		
 <script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
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

 $(".js-example-theme-single").select2({
    theme: "classic",
	placeholder: "Select or search from list.."
});
 
 
 $(document).on('change', '.btn-file :file', function() {
		var input = $(this),
			label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		input.trigger('fileselect', [label]);
	});

	$('.btn-file :file').on('fileselect', function(event, label) {
		    
		    var input = $(this).parents('.input-group').find(':text'),
		        log = label;
		    
		    if( input.length ) {
		        input.val(log);
		    } else {
		        if( log ) alert(log);
		    }
	    
	});
 
 function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        
	        reader.onload = function (e) {
	            $('#img-upload').attr('src', e.target.result);
	        }
	        
	        reader.readAsDataURL(input.files[0]);
	    }
	}

$( function() {
	 $('#udEmpNo').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	 $('#empName').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
    $("#dob").datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
    
    $( "#joiningDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
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

$('#udEmpNo').keyup(function() {
	var udEmpNo = $("#udEmpNo").val();
	$.get( "${pageContext.request.contextPath}/employee/validate-udEmpNo/" + udEmpNo, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate employee id  available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate employee id");
		}
	});
});

$( function() {
    $( ".stDate" ).datepicker({
		format: "d-M-yyyy",
        todayHighlight: true,
        autoclose: true
    });
});


function view(el) {
	var id = $(el).closest("tr").find("#row_id").val();
	$.ajax({
		async : false,
		url : "${pageContext.request.contextPath}/employee/getEmployeeInfoById/" + id ,
		success : function(data) {
			$('#employeeViewInfoModal').modal('show').find('.modal-content').html(data);
			$(".modal-backdrop.fade.in").off("click");
			$(".modal").off("keydown");
			
			}
		});
}


</script>