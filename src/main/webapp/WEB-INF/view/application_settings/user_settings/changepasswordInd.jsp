<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">CHANGE PASSWORD</span>
    	<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
            	<li><a class="dropdown-item" id="${pageContext.request.contextPath}/user/changepasswordInd?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/user/changepasswordInd?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<form method="post" id="passwordForm" autocomplete="off">
					<div class="alert alert-block alert-danger hidden"></div>
					<div class="row clearfix wrapper">
						<table align="center">
							<tr>
								<td class="align-right">User Name : &nbsp;</td>
								<td width="300px"><input type="text" id="username" name="username" value="" class="" placeholder="User Name" autocomplete="off" required></td>
							</tr>
							<tr>
								<td class="align-right">Email : &nbsp;</td>
								<td><input type="email" id="email" name="email" value="" class="" placeholder="Email" autocomplete="off" required></td>
							</tr>
							<tr>
								<td class="align-right">Old Password : &nbsp;</td>
								<td><input type="password" id="oPassword" name="oPassword" value="" class="" placeholder="Old Password" autocomplete="off" required></td>
							</tr>
							<tr>
								<td class="align-right">New Password : &nbsp;</td>
								<td><input type="password" id="nPassword" name="nPassword" value="" class="" placeholder="New Password" autocomplete="off" required></td>
							</tr>
							<tr>
								<td class="align-right">Re-type New Password : &nbsp;</td>
								<td><input type="password" id="rPassword" name="rPassword" value="" class="m-b-10" placeholder="Re-type New Password" autocomplete="off" required></td>
							</tr>
							<tr class="m-t-5">
								<td></td>
								<td class="align-right">
									<button type="button" id="clrBtn" class="btn bg-blue btn-sm waves-effect">
										<i class="material-icons">close</i>
										<span>CLEAR</span>
									</button>&nbsp;&nbsp;
									<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right">
										<i class="material-icons">save</i>
										<span>SUBMIT</span>
									</button>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<style>

.wrapper {
  	padding: 25px;
}

label {
    margin-top: 5px !important;
    font-style: italic !important;
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

form-group, input, textarea {
   	border: 1px solid #c8c7cc;
   	border-radius: 4px !important;
   	margin-top: 5px;
   	width: 100%;
}

</style>
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

$(function() {
	$('#username').focus();
});

$("#clrBtn").on('click', function(){
	$("#username").val("");
	$("#email").val("");
	$("#oPassword").val("");
	$("#nPassword").val("");
	$("#rPassword").val("");
	$('#username').focus();
});

$("#username").on('input', function() {
	var username = $("#username").val();
    
	$.get( "${pageContext.request.contextPath}/user/validate-code/" + username, function( data ) {
		if (data.outcome === 'false') {
			$(".alert").empty().removeClass("hidden");
	    	$(".alert").html("This is not a valid username!");
	    	$("#username").focus();
		} else {
			$(".alert").empty().addClass("hidden");
			console.log("username available");
		}
	});
});

$("#email").on('input', function() {
	var username = $("#username").val();
    var email = $("#email").val();
    
    $.get( "${pageContext.request.contextPath}/user/validate-employee?username=" + username + "&email=" + email, function( data ) {
		if (data.outcome === 'false') {
			$(".alert").empty().removeClass("hidden");
	    	$(".alert").html("This is not a valid username & email!");
	    	$("#email").focus();
		} else {
			$(".alert").empty().addClass("hidden");
			console.log("credentials available");
		}
	});
});

$("#oPassword").on('input', function() {
	var username = $("#username").val();
	var password = $("#oPassword").val();
    
    $.get( "${pageContext.request.contextPath}/user/validate-password?password=" + password + "&username=" + username, function( data ) {
		if (data.outcome === 'false') {
			$(".alert").empty().removeClass("hidden");
	    	$(".alert").html("You entered wrong Old password!");
	    	$("#oPassword").focus();
		} else {
			$(".alert").empty().addClass("hidden");
			console.log("password ok");
		}
	});
});

$("#rPassword").focusout(function() {
	var password1 = $("#nPassword").val();
    var password2 = $("#rPassword").val();

    if (password1 != password2){
    	$(".alert").empty().removeClass("hidden");
    	$(".alert").html("New Passwords do not match!");
    }else{
    	$(".alert").empty().addClass("hidden");
    }
});

$("#passwordForm").submit(function(event){
	event.preventDefault();	
    var formData = $("#passwordForm").serialize();
    var username = $("#username").val();
	var password = $("#nPassword").val();
    
	if($(".alert").hasClass('hidden')){
		$.get( "${pageContext.request.contextPath}/user/save-new-password?password=" + password + "&username=" + username, function( data ) {
			console.log(data.outcome);
			if (data.outcome === 'true') {
				window.location.href = "${pageContext.request.contextPath}/logout";
			} else {
				sweetAlert("Failed!", "You entered wrong credentials, please enter right credentials!", "warning", 3000, false);
			}
		});
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 3000, false);
    }
});
</script>