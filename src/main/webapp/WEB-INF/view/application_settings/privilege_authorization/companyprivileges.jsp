<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="comprivilege.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/companyprivilege/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/companyprivilege/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
	                        <input type="hidden" id="company_id" name="companyId" value=""/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-5 role-table" align="left">
							<div class="font-underline" style="color:#007AFF; font-size: 18px;">
								<spring:message code="assign.privilege"/>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-bordered table-striped table-hover" id="companyPrivList">
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

	var aTable = $('#companyPrivList').DataTable({
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
	
	$('#companyPrivList_length').hide();
	
	$("#company_list").on('change', function() {
		var companyId = $("#company_list option:selected").val();
		$("#company_id").val(companyId);
		if(companyId == 'none'){
			$("#companyPrivList tbody").empty();
			$("#privList tbody").empty();
		}else{
			loadAssignedPrivilege(companyId);
			loadUnassignedPrivilege(companyId);
		}
	});
	
	function loadAssignedPrivilege(companyId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/companyprivilege/privilge/" + companyId ,
			dataType : 'json',
			success : function(data) {
				var rows = aTable.rows().remove().draw();
				var companyPrivileges = data.length;
				if (companyPrivileges > 0) {
					showCompanyPrivileges(data);
				} else {
					$("#companyPrivList tbody").empty();
				}

			}
		});
	}
	
	function loadUnassignedPrivilege(companyId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/companyprivilege/privilegeList/" + companyId,
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
	
	function showCompanyPrivileges(data) {
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var priv_id = data[i].privilegeId || "";
			var priv_code = data[i].privilegeCode || "";
			var priv_name = data[i].privilegeName || "";

			var html = '' + priv_name
					+ '<input type="hidden" id="id" name="id" class="id" value="' + id  + '"/>'
					+ '<input type="hidden" id="priv_id" name="privilegeId" class="priv-id" value="' + priv_id  + '"/>';
			var rowNode = aTable.row.add( [priv_code,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
		}
	}
	
	function showPrivileges(data) {
		for (var i = 0; i < data.length; i++) {
			var priv_id = data[i].id || "";
			var priv_code = data[i].privilegeCode || "";
			var priv_name = data[i].privilegeName || "";

			var html= '' + priv_name
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
	
	$("#companyPrivList").on('click', 'tr', function () {
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
				
				addCompanyPrivilgee(privId);
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
			
			removeCompanyPrivilgee(privId);
	    });
	}
	
	function addCompanyPrivilgee(privId){
		var companyId = $("#company_list option:selected").val();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/companyprivilege/save-company-privilge/" + privId + "/" + companyId,
			success : function() {
				console.log("save success");
			},
			error: function(){
				console.log("this employee has no user id.");
		  	}
		});
	}
	
	function removeCompanyPrivilgee(privId){
		var companyId = $("#company_list option:selected").val();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/companyprivilege/remove-company-privilge/" + privId + "/" + companyId,
			success : function() {
				console.log("save success");
			},
			error: function(){
				console.log("this employee has no user id.");
		  	}
		});
	}
	
	
</script>

