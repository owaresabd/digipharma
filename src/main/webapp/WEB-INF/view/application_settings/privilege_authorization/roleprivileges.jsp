<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="roleprivilege.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/roleprivilege/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/roleprivilege/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body">
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
						<div class="col-md-6">
							<span><b><spring:message code="user.role"/> : </b></span>
							<select id="role_list" class="form-control show-tick">
								<option value="none">---Role Select---</option>
	                        </select>
	                        <input type="hidden" id="company_id" name="companyId" value=""/>
	                        <input type="hidden" id="type_id" name="typeId" value=""/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-5 role-table" align="left">
							<div class="font-underline" style="color:#007AFF; font-size: 18px;">
								<spring:message code="assign.privilege"/>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-bordered table-striped table-hover" id="rolePrivList">
										<thead>
											<tr>
												<th class="align-center" style="width: 100px;">Privilege Code</th>
												<th class="align-left">Privilege Name</th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-xs-2">
							<div class="row display-flex ">
								<div style="width: 50px; margin: auto;" class="button-group">
									<button type="button" onclick="addPrivilege()" class="btn btn-sm arrow bg-green waves-effect">
										<i class="material-icons">subdirectory_arrow_left</i>
									</button>
									<button type="button" onclick="removePrivilege()" class="btn btn-sm arrow bg-red waves-effect">
										<i class="material-icons">subdirectory_arrow_right</i>
									</button>
								</div>
							</div>
						</div>
						<div class="col-xs-5 priv-table" align="right">
							<div class="font-underline" style="color:#007AFF; font-size: 18px;" align="left">
								<spring:message code="unassign.privilege"/>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-bordered table-striped table-hover" id="privList">
										<thead>
											<tr>
												<th class="align-center" style="width: 100px;">Privilege Code</th>
												<th class="align-left">Privilege Name</th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
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
	.arrow {
	    background: #fff;
	    color: #007AFF;
	    border-color: #008;
	    border-radius: 5px;
	    /* width: 45px;
	    height: 35px; */
	    margin-top: 10px;
	}
	
	.duo {
		background: #fff;
	    border-color: #DC143C;
	    border-radius: 5px;
		color: #DC143C;
	}
	
	.display-flex {
	    display: flex;
	    /* border: 1px solid #ddd; */
	    padding: 10px 0px 12px 0px;
	    border-radius: 5px;
	    margin-left: 10px;
	    margin-right: 10px;
	    margin-top: 117px;
	}
	
	.dataTables_filter input {
	    width: 150px !important;
	}
	
	.sel-rpriv{
		border: 1px solid #fffefe !important;
    	background-color: lightpink !important;
	}
	
	.sel-priv{
		border: 1px solid #fffefe !important;
    	background-color: darkseagreen !important;
	}
	
	select.required-company {
	    border-right-color: #f00 !important;
	    border-right-width: 2px !important;
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

	var aTable = $('#rolePrivList').DataTable({
		"aaSorting" : [[1, 'asc']],
		 "lengthMenu": [[10], [25], [50], ["All"]],
		 ordering : false,
		 "oLanguage" : {
	            "sLengthMenu" : "Show _MENU_ Rows",
	            "sSearch" : "",
	            "sSearchWidth" : "300px",
	            "sSearchPlaceholder": "Search records ....",
	            "sEmptyTable": "No assigned privilege available",
	            "oPaginate" : {
	                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
	                "sNext" : "<span class='fa fa-chevron-right'></span>"
	            }
	        },
	});
	
	var eTable = $('#privList').DataTable({
		"aaSorting" : [[1, 'asc']],
		 "lengthMenu": [[10], [25], [50], ["All"]],
		 ordering : false,
		 "oLanguage" : {
	            "sLengthMenu" : "Show _MENU_ Rows",
	            "sSearch" : "",
	            "sSearchWidth" : "300px",
	            "sSearchPlaceholder": "Search records ....",
	            "sEmptyTable": "No privilege available",
	            "oPaginate" : {
	                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
	                "sNext" : "<span class='fa fa-chevron-right'></span>"
	            }
	        },
	});
	
	$('#privList_length').hide();
	
	$('#rolePrivList_length').hide();
	
	$("#company_list").on('change', function() {
		var companyId = $("#company_list option:selected").val();
		$("#company_id").val(companyId);
		var rows = aTable.rows().remove().draw();
		var rows = eTable.rows().remove().draw();
		if(companyId == 'none'){
			$("#type_id").val("");
			$("#role_list").empty(); 
			$("<option />", {
		        val: 'none',
		        text: '---Role Select---'
		    }).appendTo("#role_list");
		}else{
			loadCompanyRoles(companyId);
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
				        val: 'none',
				        text: '---Role Select---'
				    }).appendTo("#role_list");
					showRoleList(data);
				} else {
					$("#type_id").val("");
					$("<option />", {
				        val: 'none',
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

	$("#role_list").on('change', function() {
		$("#type_id").val($("#role_list option:selected").val());
		var typeId = $("#role_list option:selected").val();
		if(typeId == 'none'){
			$("#rolePrivList tbody").empty();
			$("#privList tbody").empty();
		}else{
			loadAssignedPrivilege(typeId);
			loadUnassignedPrivilege(typeId);
		}
	});
	
	function loadAssignedPrivilege(typeId) {
		var companyId = $("#company_list option:selected").val();
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/roleprivilege/privilge/" + typeId + "/" + companyId,
			dataType : 'json',
			success : function(data) {
				var rows = aTable.rows().remove().draw();
				var rolePrivileges = data.length;
				if (rolePrivileges > 0) {
					showRolePrivileges(data);
				} else {
					$("#rolePrivList tbody").empty();
				}

			}
		});
	}
	
	function loadUnassignedPrivilege(typeId) {
		var companyId = $("#company_list option:selected").val();
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/roleprivilege/privilegeList/" + typeId + "/" + companyId,
			dataType : 'json',
			success : function(data) {
				var rows = eTable.rows().remove().draw();
				var privList = data.length;
				if (privList > 0) {
					showPrivileges(data);
				} else {
					$("#privList tbody").empty();
				}

			}
		});
	}
	
	function showRolePrivileges(data) {
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var priv_id = data[i].privilegeId || "";
			var priv_code = data[i].privilegeCode || "";
			var suite_name = data[i].suiteName || "";
			var module_name = data[i].moduleName || "";
			var priv_name = data[i].privilegeName || "";
			var tRowVal= suite_name+" &#8608; "+module_name+" &#8608; "+priv_name
			var html = '' + tRowVal
					+ '<input type="hidden" id="id" name="id" class="id" value="' + id  + '"/>'
					+ '<input type="hidden" id="priv_id" name="privilegeId" class="priv-id" value="' + priv_id  + '"/>';
			var rowNode = aTable.row.add( [priv_code,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
		}
	}
	
	function showPrivileges(data) {
		for (var i = 0; i < data.length; i++) {
			var priv_id = data[i].privilegeId || "";
			var priv_code = data[i].privilegeCode || "";
			var suite_name = data[i].suiteName || "";
			var module_name = data[i].moduleName || "";
			var priv_name = data[i].privilegeName || "";
			var tRowVal= suite_name+" &#8608; "+module_name+" &#8608; "+priv_name
			var html= '' + tRowVal
					+ '<input type="hidden" id="priv_id" name="privilegeId" class="priv-id" value="' + priv_id  + '"/>';
			var rowNode = eTable.row.add( [priv_code,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
		}
	}
	
	$("#privList").on('click', 'tr', function () {
		if($(this).closest('tr').find("td").hasClass("sel-priv")){
			$(this).closest('tr').removeClass("tr-priv");
			$(this).closest('tr').find("td").removeClass("sel-priv");
		}else{
			$(this).closest('tr').addClass("tr-priv");
			$(this).closest('tr').find("td").addClass("sel-priv");
		}
    });
	
	$("#rolePrivList").on('click', 'tr', function () {
		if($(this).closest('tr').find("td").hasClass("sel-rpriv")){
			$(this).closest('tr').removeClass("tr-rpriv");
			$(this).closest('tr').find("td").removeClass("sel-rpriv");
		}else{
			$(this).closest('tr').addClass("tr-rpriv");
			$(this).closest('tr').find("td").addClass("sel-rpriv");
		}
    });
	
	function addPrivilege() {
		 var addItem = $(".tr-priv");
		 var id = "";
		    $.each(addItem, function(i, item){
		       	var row = $(item);
		       	var privId = row.find("td:eq(1) .priv-id").val() || "";
				var privCode = row.find("td:eq(0)").text() || "";
				var privName = row.find("td:eq(1)").text() || "";
				
				var html = '' + privName
						+ '<input type="hidden" id="id" name="id" class="id" value="' + id  + '"/>'
						+ '<input type="hidden" id="priv_id" name="privilegeId" class="priv-id" value="' + privId  + '"/>';
				var rowNode = aTable.row.add( [privCode,html] ).draw( false ).node();
				aTable.order([0, 'asc']).draw();
				aTable.page('first').draw(false);
				$(rowNode).find(":eq(0)").css({"text-align": "center"});
				
				eTable
				    .row( item )
				    .remove()
				    .draw();
				
				addRolePrivilgee(privId);
		    });
		
	}
	
	function removePrivilege() {
		var addItem = $(".tr-rpriv");
		var id;
	    $.each(addItem, function(i, item){
	       	var row = $(item);
			
			var privId = row.find("td:eq(1) .priv-id").val() || "";
			var privCode = row.find("td:eq(0)").text() || "";
			var privName = row.find("td:eq(1)").text() || "";
			
			var html= '' + privName
					+ '<input type="hidden" id="priv_id" name="privilegeId" class="priv-id" value="' + privId  + '"/>';
			var rowNode = eTable.row.add( [privCode,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
			
			aTable
			    .row( item )
			    .remove()
			    .draw();
			
			removeRolePrivilgee(privId);
	    });
	}
	
	function addRolePrivilgee(privId){
		var typeId = $("#role_list option:selected").val();
		var companyId = $("#company_list option:selected").val();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/roleprivilege/save-role-privilge/" + privId + "/" + typeId + "/" + companyId,
			success : function() {
				console.log("save success");
			},
			error: function(){
				console.log("this employee has no user id.");
		  	}
		});
	}
	
	function removeRolePrivilgee(privId){
		var typeId = $("#role_list option:selected").val();
		var companyId = $("#company_list option:selected").val();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/roleprivilege/remove-role-privilge/" + privId + "/" + typeId + "/" + companyId,
			success : function() {
				console.log("save success");
			},
			error: function(){
				console.log("this employee has no user id.");
		  	}
		});
	}
	
	
</script>

