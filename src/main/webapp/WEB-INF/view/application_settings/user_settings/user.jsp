<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="user.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/user/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/user/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<div class="alert alert-block alert-danger hidden"></div>
					<div class="row">
						<div class="col-md-6">
							<span><b><spring:message code="lbl.company"/> :</b></span>
							<select id="company_list" class="form-control show-tick">
	                        	<option value="none">---Company Select---</option>
	                        <c:forEach var="com" items="${company}">
	                           	<option value="${com.id }">${com.companyName }</option>
	                        </c:forEach>
	                        </select>
						</div>
						<div class="col-md-6 m-t-15">
							<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
								<i class="material-icons">games</i>
							</button>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 role-table">
							<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="userList">
								<thead>
									<tr>
										<th class="align-left" style="width: 100px;">USER ID</th>
										<th class="align-left">USER NAME</th>
										<th class="align-left">DESIGNATION</th>
										<th class="align-left">ROLE</th>
										<th class="align-left">EMAIL</th>
										<th class="align-left">MOBILE</th>
										<th class="align-center" style="width: 100px;">ACTION</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${users}" varStatus="counter">
									<tr>
										<td class="field_username align-left">${user.userName}</td>
										<td class="field_fullname">${user.fullName}</td>
										<td class="field_designation">${user.designation}</td>
										<td class="field_usertype align-left">${user.userType}</td>
										<td class="field_email">${user.email}</td>
										<td class="field_mobile">${user.mobile}</td>
										<td class="align-center">
											<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
											<a class="btn-delete btn btn-xs" onclick="del(this)"><i class="material-icons">delete_forever</i></a>
											<input type="hidden" id="row_id" class="row-id" value="${user.id}"/>
											<input type="hidden" id="f_name" class="f-name" value="${user.fullName}"/>
											<input type="hidden" id="u_name" class="u-name" value="${user.userName}"/>
											<input type="hidden" id="e_email" class="e-email" value="${user.email}"/>
											<input type="hidden" id="p_password" class="p-password" value="${user.password}"/>
											<input type="hidden" id="a_active" class="a-active" value="${user.active}"/>
											<input type="hidden" id="r_id" class="r-id" value="${user.roleId}"/>
											<input type="hidden" id="u_type" class="u-type" value="${user.userType}"/>
											<input type="hidden" id="c_code" class="c-code" value="${user.companyCode}"/>
											<input type="hidden" id="c_name" class="c-name" value="${user.companyName}"/>
											<input type="hidden" id="t_id" class="t-id" value="${user.typeId}"/>
											<input type="hidden" id="desig" class="desig" value="${user.designation}"/>
											<input type="hidden" id="a_address" class="a-address" value="${user.address}"/>
											<input type="hidden" id="m_mobile" class="m-mobile" value="${user.mobile}"/>
											<input type="hidden" id="c_id" class="c-id" value="${user.companyId}"/>
											<input type="hidden" id="sup_user" class="sup-user" value="${user.superUser}"/>
											<input type="hidden" id="c_employeeId" value="${user.employeeId}"/>
											<input type="hidden" id="c_isEmployee" value="${user.isEmployee}"/>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!------------------------ Start: Create Product Color Types Modal -------------------->

	 <div class="modal fade" id="userModal" tabindex="-1" role="dialog">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="defaultModalLabel"><spring:message code="lbl.user"/> :</h4>
                 </div>
                 <form method="post" id="userForm" modelAttribute="user">
                 	<div class="modal-body">
                 		<div class="alert alert-block alert-danger"></div>
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
	                 	<div class="row clearfix">
	                 		<div class="col-md-12">
	                 		<div class="col-md-6">
                            	<div class="form-group">
									<input type="checkbox" id="employee_status" class="filled-in chk-col-green">
									<label for="employee_status"><b><span class="employee-status">Is Employee ?</span></b></label>
									<input type="hidden" id="employeeStatus" name="isEmployee" value="N">
								</div>
                            </div>
	                 		<div class="col-md-6">
	                 			<div class="form-group">
								<select id="employee_id" name="employeeId" class="js-example-theme-single form-control" disabled onchange="employeeInfoById(this.id);" style="width: 100%;">
									<option value="">-Select Employee-</option>
								<c:forEach var="employee" items="${employeeInfos}">
	                           		<option value="${employee.id }">${employee.empName }</option>
	                        	</c:forEach>
								</select>
								
								</div>
	                        </div>
	                 		</div>
	                 		<div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="material-icons">person</i>
                                    </span>
                                    <div class="form-line">
                                    	<input type="hidden" id="id" name="id" value=""/>
                                        <input type="text" id="user_name" name="userName" value="" class="form-control date" placeholder="application's user-id" required>
                                    </div>
                                    <span class="input-group-addon">
                                        <i class="material-icons">send</i>
                                    </span>
                                </div>
                            </div>
                            <div class="col-md-6">
								<select id="role_list" class="form-control show-tick">
									<option value="0">---Role Select---</option>
								<c:forEach var="role" items="${roles}">
	                           		<option value="${role.id }">${role.roleName }</option>
	                        	</c:forEach>
								</select>
								<input type="hidden" id="company_id" name="companyId" value=""/>
								<input type="hidden" id="type_user" name="userType" value=""/>
								<input type="hidden" id="type_id" name="typeId" value=""/>
	                        </div>
	                        <div class="col-md-7">
                                 <div class="input-group">
                                     <span class="input-group-addon">
                                         <i class="material-icons">person</i>
                                     </span>
                                     <div class="form-line">
                                         <input type="text" id="full_name" name="fullName" value="" class="form-control date" placeholder="user fullname" required>
                                     </div>
                                 </div>
                             </div>
                             <div class="col-md-5">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="text" id="designation" name="designation" value="" class="form-control" placeholder="designation" required>
	                                </div>
	                            </div>
	                          </div>
	                         <div class="col-md-6">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="email" id="email" name="email" value="" class="form-control" placeholder="email" required>
	                                </div>
	                            </div>
	                          </div>
	                          <div class="col-md-6">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="number" id="mobile" name="mobile" value="" class="form-control" placeholder="contact number" required>
	                                </div>
	                            </div>
	                          </div>
	                          <div class="col-md-6">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="password" id="password1" name="password" value="" class="form-control" placeholder="password" maxlength="8">
	                                </div>
	                            </div>
	                          </div>
	                          <div class="col-md-6">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="password" id="password2" value="" class="form-control" placeholder="confirm password" maxlength="8">
	                                </div>
	                            </div>
	                         </div>
	                         <div class="col-md-12">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <textarea rows="3" id="address_detail" class="form-control" placeholder="please enter your detailed address" required></textarea>
	                                     <input type="hidden" id="address" name="address" value="">
	                                </div>
	                            </div>
	                         </div>
	                         <div class="col-md-6 super-user hidden">
								<div class="demo-checkbox">
									<input type="checkbox" id="super_user" class="filled-in chk-col-green">
									<label for="super_user"><b>Active User ?</b></label>
									<input type="hidden" id="superUser" name="superUser" value="false">
								</div>
							</div>
	                     </div>
	                 </div>
	                 <div class="modal-footer">
	                    <button type="button" class="btn bg-red btn-sm waves-effect pull-right  m-r-10" data-dismiss="modal">
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
</style>
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>	
<script>
	/********************Modal open**********************************/
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
	
	function employeeInfoById(id){
		var employeeId=$("#"+id+"").val();
		$.get( "${pageContext.request.contextPath}/user/getEmployeeInfoById/" + employeeId, 
		function( data ) {
			$("#full_name").val(data.empName);
			$("#designation").val(data.desigName);
			$("#email").val(data.email);
			$("#mobile").val(data.mobileNo);
			$("#address_detail").val(data.address);
			$("#address").val(data.address);
			
			
		});
	}
	$(document).ready(function () {
		var companyId = '${companyId}';
		$("#company_list").val(companyId);
		$("#company_id").val(companyId);
		/* if (companyId == '9b4169c4-84c8-4f15-adf8-976ab433c343'){
			$(".super-user").removeClass('hidden');
		}else {
			$(".super-user").addClass('hidden');
		} */
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
	
	$('#employee_status').change(function() {
		if (this.checked) {
			$('#employeeStatus').val('Y');
			$('.employee-status').text('Employee ?');
			$("#employee_id").prop("disabled", false);
		}else{
			$('#employeeStatus').val('N');
			$('.employee-status').text('Is Employee ?');
			$("#employee_id").prop("disabled", true);
		}
	});
	$(".js-example-theme-single").select2({
	    theme: "classic"
	});
	 
	var aTable = $('#userList').DataTable({
		"aaSorting" : [[1, 'asc']],
		 "lengthMenu": [[10000], ["All"]],
		 ordering : false,
		 "oLanguage" : {
	            "sLengthMenu" : "Show _MENU_ Rows",
	            "sSearch" : "",
	            "sSearchWidth" : "300px",
	            "sSearchPlaceholder": "Search records ....",
	            "sEmptyTable": "No employee available",
	            "oPaginate" : {
	                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
	                "sNext" : "<span class='fa fa-chevron-right'></span>"
	            }
	        },
	});
	
	$('#userList_length').hide();
	
	$('#user_name').keyup(function() {
		var username = $("#user_name").val();
		var companyId = $("#company_id").val();
		$.get( "${pageContext.request.contextPath}/user/validate-code/" + username , 
		function( data ) {
			if (data.outcome === 'true') {
				$(".alert-code").empty().removeClass("hidden");
		    	$(".alert-code").html("Duplicate username available!");
			} else {
				$(".alert-code").empty().addClass("hidden");
				console.log("no duplicate code");
			}
		});
	});
	
	$("#company_list").on('change', function() {
		var companyId = $("#company_list option:selected").val();
		$("#company_id").val(companyId);
		$(".alert").empty().addClass("hidden");
		
		var rows = aTable.rows().remove().draw();
		if(companyId == 'none'){
			$("#type_id").val("");
			$("#role_list").empty(); 
			$("<option />", {
		        val: 0,
		        text: '---Role Select---'
		    }).appendTo("#role_list");
		}else{
			loadCompanyRoles(companyId);
			loadCompanyUsers(companyId);
		}
	});
	
	function loadCompanyRoles(companyId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/role/roleByCompany/" + companyId ,
			dataType : 'json',
			success : function(data) {
				$("#role_list").empty(); 
				var roleList = data.length;
				if (roleList > 0) {
					$("<option />", {
				        val: 0,
				        text: '---Role Select---'
				    }).appendTo("#role_list");
					showRoleList(data);
				} else {
					$("#type_id").val("");
					$("<option />", {
				        val: 0,
				        text: '---Role Select---'
				    }).appendTo("#role_list");
				}

			}
		});
	}
	
	function showRoleList(data) {
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var company_id = data[i].companyId || "";
			var role_code = data[i].roleCode || "";
			var role_name = data[i].roleName || "";
			
			$('#role_list').append('<option value="' + id + '">' + role_code + '</option>');
		}
	}
	
	function loadCompanyUsers(companyId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/user/userByCompany/" + companyId ,
			dataType : 'json',
			success : function(data) {
				var rows = aTable.rows().remove().draw();
				var companyUser = data.length;
				if (companyUser > 0) {
					showCompanyUsers(data);
				} else {
					$("#userList tbody").empty();
				}

			}
		});
	}
	
	function showCompanyUsers(data) {
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var userName = data[i].userName || "";
			var email = data[i].email || "";
			var password = data[i].password || "";
			var fullName = data[i].fullName || "";
			var active = data[i].active || "";
			var userType = data[i].userType || "";
			var companyCode = data[i].companyCode || "";
			var companyName = data[i].companyName || "";
			var roleId = data[i].roleId || "";
			var typeId = data[i].typeId || "";
			var designation = data[i].designation || "";
			var address = data[i].address || "";
			var mobile = data[i].mobile || "";
			var companyId = data[i].companyId || "";
			var supUser = data[i].superUser || "";
			
			var html = ''
					+ '<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>'
					+ '<a class="btn-delete btn btn-xs" onclick="del(this)"><i class="material-icons">delete_forever</i></a>'
					+ '<input type="hidden" id="row_id" class="row-id" value="' + id  + '"/>'
					+ '<input type="hidden" id="f_name" class="f-name" value="' + fullName  + '"/>'
					+ '<input type="hidden" id="u_name" class="u-name" value="' + userName  + '"/>'
					+ '<input type="hidden" id="e_email" class="e-email" value="' + email  + '"/>'
					+ '<input type="hidden" id="p_password" class="p-password" value="' + password  + '"/>'
					+ '<input type="hidden" id="a_active" class="a-active" value="' + active  + '"/>'
					+ '<input type="hidden" id="r_id" class="r-id" value="' + roleId  + '"/>'
					+ '<input type="hidden" id="u_type" class="u-type" value="' + userType  + '"/>'
					+ '<input type="hidden" id="c_code" class="c-code" value="' + companyCode  + '"/>'
					+ '<input type="hidden" id="c_name" class="c-name" value="' + companyName  + '"/>'
					+ '<input type="hidden" id="t_id" class="t-id" value="' + typeId  + '"/>'
					+ '<input type="hidden" id="desig" class="desig" value="' + designation  + '"/>'
					+ '<input type="hidden" id="a_address" class="a-address" value="' + address  + '"/>'
					+ '<input type="hidden" id="m_mobile" class="m-mobile" value="' + mobile  + '"/>'
					+ '<input type="hidden" id="c_id" class="c-id" value="' + companyId  + '"/>'
					+ '<input type="hidden" id="sup_user" class="sup-user" value="' + supUser  + '"/>';
			var rowNode = aTable.row.add( [userName,fullName,designation,userType,email,mobile,html] ).draw( false ).node();
			aTable.order([0, 'asc']).draw();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
			$(rowNode).find(":eq(3)").css({"text-align": "center"});
			$(rowNode).find(":eq(6)").css({"text-align": "center"});
		}
	}
	
	$("#password2").focusout(function() {
		var password1 = $("#password1").val();
	    var password2 = $("#password2").val();

	    if (password1 != password2){
	    	$(".alert").empty().removeClass("hidden");
	    	$(".alert").html("Passwords do not match!");
	    }else{
	    	$(".alert").empty().addClass("hidden");
	    }
    });
	
	$('#address_detail').keyup(function() {
    	$("#address").val(this.value);
    });
	
	$("#role_list").on('change', function() {
		$("#type_id").val($("#role_list option:selected").val());
		$("#type_user").val($("#role_list option:selected").text());
	});
	
	$('#super_user').change(function() {
		if (this.checked) {
			$('#superUser').val(true);
		}else{
			$('#superUser').val(false);
		}
	});
	
	function add(el) {
		if ($("#company_list option:selected").val() == 'none') {
			$(".alert").empty().removeClass("hidden");
			$(".alert").html("Please select company!");
		}else{
			
			var companyId = $("#company_id").val();
			/* if (companyId == '9b4169c4-84c8-4f15-adf8-976ab433c343'){
				$(".super-user").removeClass('hidden');
			}else {
				$(".super-user").addClass('hidden');
			} */
			
			$("#id").val("");
			$("#user_name").val("");
			$("#full_name").val("");
			$("#email").val("");
			$("#mobile").val("");
			$("#super_user").prop('checked', false);
			$('#superUser').val('false');
			$("#designation").val("");
			$("#password1").val("");
			$("#password2").val("");
			$("#password1").removeClass('hidden');
			$("#password2").removeClass('hidden');
			$("#address_detail").val("");
			$("#address").val("");
			$("#role_list").val(0);
			$("#employee_status").prop('checked', false);
			$('#employeeStatus').val('N');
			$('.employee-status').text('Is Employee ?');
			$("#employee_id").prop("disabled", true);
			$('#employee_id').val('').trigger('change.select2');
			
		    $("#userModal").modal();
		    $(".modal-backdrop.fade.in").off("click");
		    $(".modal").off("keydown");
		    $(".alert").empty().addClass("hidden");
		    $(".alert-code").empty().addClass("hidden");
		}
	}
	
	/**************************Start of Row Edit Function******************************/

	function edit(el) {
		var Id = $(el).closest("tr").find("#row_id").val();
		var userName = $(el).closest("tr").find("td:eq(0)").text();
		var fullName = $(el).closest("tr").find("td:eq(1)").text();
		var desig 	 = $(el).closest("tr").find("td:eq(2)").text();
		var userType = $(el).closest('tr').find("td:eq(3)").text();
		var email 	 = $(el).closest("tr").find("td:eq(4)").text();
		var mobile 	 = $(el).closest("tr").find("td:eq(5)").text();
		var typeId 	 = $(el).closest('tr').find("#t_id").val();
		var address  = $(el).closest('tr').find("#a_address").val();
		var supUser  = $(el).closest('tr').find("#sup_user").val();
		var employeeId  = $(el).closest('tr').find("#c_employeeId").val();
		var isEmployee  = $(el).closest('tr').find("#c_isEmployee").val();
		
		var companyId = $("#company_id").val();
		/* if (companyId == '9b4169c4-84c8-4f15-adf8-976ab433c343'){
			$(".super-user").removeClass('hidden');
		}else {
			$(".super-user").addClass('hidden');
		} */
		
		$("#id").val(Id);
		$("#user_name").val(userName);
		$("#full_name").val(fullName);
		$("#email").val(email);
		$("#mobile").val(mobile);
		$("#designation").val(desig);
		$("#password1").addClass('hidden');
		$("#password2").addClass('hidden');
		$("#address_detail").val(address);
		$("#address").val(address);
		$("#role_list").val(typeId);
		$("#type_id").val(typeId);
		$("#type_user").val(userType);
		if(isEmployee == 'Y'){
			$("#employee_status").prop('checked', true);
			$('#employeeStatus').val('Y');
			$('.employee-status').text('Employee ?');
			$("#employee_id").prop("disabled", false);
			$('#employee_id').val(employeeId).trigger('change.select2');
		}else{
			$("#employee_status").prop('checked', false);
			$('#employeeStatus').val('N');
			$('.employee-status').text('Is Employee ?');
			$("#employee_id").prop("disabled", true);
			$('#employee_id').val('').trigger('change.select2');
		}
		if(supUser == 'true'){
			$("#super_user").prop('checked', true);
			$('#superUser').val('true');
		}else{
			$("#super_user").prop('checked', false);
			$('#superUser').val('false');
		}
		
		$("#userModal").modal('show');
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
		$(".alert").empty().addClass("hidden");
		$(".alert-code").empty().addClass("hidden");
	};
	/***************************End of Row Edit Function**********************************/
	$("#userForm").submit(function(event){
		event.preventDefault();				
	    var formData = $("#userForm").serialize();
	    
	    if($(".alert-code").hasClass('hidden')){
	    	$.ajax({	
		    	url : "${pageContext.request.contextPath}/user/save-users",
		        type: 'POST',
		        data: formData,
		        async: false,
		        success: function (data) {				 
		        	$("#userModal").modal('hide');
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
	/***************************Start of "OK" Button Function******************************/
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
					url : "${pageContext.request.contextPath}/user/delete-users/" + id ,
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
	
	/********************End Modal open**********************************/
	
</script>
		    
			