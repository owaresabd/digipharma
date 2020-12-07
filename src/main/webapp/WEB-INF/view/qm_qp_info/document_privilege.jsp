<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;">DOCUMENT PRIVILEGE</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body">
					<div class="row">
						<div class="col-md-6">
							<span><b>USER NAME:</b></span>
							<select id="user_list" class="js-example-theme-single form-control">
	                        		<option value="none">---SELECT USER---</option>
	                        <c:forEach var="info" items="${users}">
	                           	<option value="${info.id }">${info.userName }[${info.fullName }]</option>
	                        </c:forEach>
	                        </select>
	                        <input type="hidden" id="user_id" name="userId" value=""/>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-5 role-table" align="left">
							<div class="font-underline" style="color:#007AFF; font-size: 18px;">
								Assigned Document
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-bordered table-striped table-hover" id="assignedList">
										<thead>
											<tr>
												<th class="align-left" style="width: 100px;">DOCUMENT ID</th>
												<th class="align-left">DOCUMENT NAME</th>
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
									<button type="button" onclick="addDocument()" class="btn btn-sm arrow bg-green waves-effect">
										<i class="material-icons">subdirectory_arrow_left</i>
									</button>
									<button type="button" onclick="removeDocument()" class="btn btn-sm arrow bg-red waves-effect">
										<i class="material-icons">subdirectory_arrow_right</i>
									</button>
								</div>
							</div>
						</div>
						<div class="col-xs-5 priv-table" align="right">
							<div class="font-underline" style="color:#007AFF; font-size: 18px;" align="left">
								Unassigned Document
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table class="table table-bordered table-striped table-hover" id="unassignedList">
										<thead>
											<tr>
												<th class="align-left" style="width: 100px;">DOCUMENT ID</th>
												<th class="align-left">DOCUMENT NAME</th>
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
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script type="text/javascript">
$(".js-example-theme-single").select2({
    theme: "classic"
	 
});
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

	var aTable = $('#assignedList').DataTable({
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
	
	var eTable = $('#unassignedList').DataTable({
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
	
	$('#unassignedList_length').hide();
	
	$('#assignedList_length').hide();
	
	$("#user_list").on('change', function() {
		var userId = $("#user_list option:selected").val();
		
		$("#user_id").val(userId);
		if(userId == 'none'){
			$("#assignedList tbody").empty();
			$("#unassignedList tbody").empty();
		}else{
			loadAssignedDocument(userId);
			loadUnassignedDocument(userId);
		}
	});
	
	function loadAssignedDocument(userId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/qp_info/assignedDocumentList/" + userId ,
			dataType : 'json',
			success : function(data) {
				var rows = aTable.rows().remove().draw();
				var assignedInfos = data.length;
				if (assignedInfos > 0) {
					showAssignedList(data);
				} else {
					$("#assignedList tbody").empty();
				}

			}
		});
	}
	
	function loadUnassignedDocument(userId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/qp_info/unassignedDocumentList/" + userId,
			dataType : 'json',
			success : function(data) {
				var rows = eTable.rows().remove().draw();
				var unassignedList = data.length;
				if (unassignedList > 0) {
					showUnassignedList(data);
				} else {
					$("#unassignedList tbody").empty();
				}

			}
		});
	}
	
	function showAssignedList(data) {
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var udDocId = data[i].udDocumentId || "";
			var docName = data[i].documentName || "";

			var html = '' + docName
					+ '<input type="hidden" id="id" name="id" class="priv-id" value="' + id  + '"/>';
			var rowNode = aTable.row.add( [udDocId,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
		}
	}
	
	function showUnassignedList(data) {
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var udDocId = data[i].udDocumentId || "";
			var docName = data[i].documentName || "";

			var html= '' + docName
					+ '<input type="hidden" id="id" name="id" class="priv-id" value="' + id  + '"/>';
			var rowNode = eTable.row.add( [udDocId,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
		}
	}
	
	$("#unassignedList").on('click', 'tr', function () {
		if($(this).closest('tr').find("td").hasClass("sel-priv")){
			$(this).closest('tr').removeClass("tr-priv");
			$(this).closest('tr').find("td").removeClass("sel-priv");
		}else{
			$(this).closest('tr').addClass("tr-priv");
			$(this).closest('tr').find("td").addClass("sel-priv");
		}
    });
	
	$("#assignedList").on('click', 'tr', function () {
		if($(this).closest('tr').find("td").hasClass("sel-rpriv")){
			$(this).closest('tr').removeClass("tr-rpriv");
			$(this).closest('tr').find("td").removeClass("sel-rpriv");
		}else{
			$(this).closest('tr').addClass("tr-rpriv");
			$(this).closest('tr').find("td").addClass("sel-rpriv");
		}
    });
	
	function addDocument() {
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
				
				addDocumentInfo(privId);
		    });
		
	}
	
	function removeDocument() {
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
			removeDocumentInfo(privId);
	    });
	}
	
	function addDocumentInfo(docId){
		var userId = $("#user_list option:selected").val();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/qp_info/save-document-mapping/" + docId + "/" + userId,
			success : function() {
				console.log("save success");
			},
			error: function(){
				console.log("this employee has no user id.");
		  	}
		});
	}
	
	function removeDocumentInfo(docId){
		var userId = $("#user_list option:selected").val();
		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/qp_info/remove-document-mapping/" + docId + "/" + userId,
			success : function() {
				console.log("save success");
			},
			error: function(){
				console.log("this employee has no user id.");
		  	}
		});
	}
	
	
</script>

