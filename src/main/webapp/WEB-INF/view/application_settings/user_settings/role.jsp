<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="role.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/role/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/role/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
							<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="roleList">
								<thead>
									<tr>
										<th class="align-left" style="width: 200px;">ROLE CODE</th>
										<th class="align-left">ROLE NAME</th>
										<th class="align-center" style="width: 100px;">ACTION</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="role" items="${roles}">
									<tr>
										<td class="field_role_code">${role.roleCode}</td>
										<td class="field_role_name">${role.roleName}</td>
										<td class="align-center">
											<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
											<a class="btn-delete btn btn-xs" onclick="del(this)"><i class="material-icons">delete_forever</i></a>
											<input type="hidden" id="row_id" name="id" class="row-id" value="${role.id}"/>
											<input type="hidden" id="comp_id" name="compId" class="comp-id" value="${role.companyId}"/>
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

	 <div class="modal fade" id="roleModal" tabindex="-1" role="dialog">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="defaultModalLabel"><spring:message code="user.role"/></h4>
                 </div>
                 <form method="post" id="userTypeForm" modelAttribute="userTypes">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
	                 	<div class="row clearfix">
	                         <div class="col-md-5">
	                             <div class="form-group">
	                                 <div class="form-line">
	                                 	<input type="hidden" id="id" name="id" value=""/>
	                                 	<input type="hidden" id="company_id" name="companyId" value=""/>
	                                    <input type="text" id="role_code" name="roleCode" value="" class="form-control" placeholder="Role Code" maxlength="12" required>
	                                 </div>
	                             </div>
	                         </div>
	                         <div class="col-md-7">
	                             <div class="form-group">
	                                 <div class="form-line">
	                                     <input type="text" id="role_name" name="roleName" value="" class="form-control" placeholder="Role Name" maxlength="255" required>
	                                 </div>
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

	.dataTables_filter input {
	    width: 150px !important;
	}
	
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
	
	$(document).ready(function () {
		var companyId = '${companyId}';
		$("#company_list").val(companyId);
		$("#company_id").val(companyId);
	});
	
	$('#role_code').focusout(function() {
		var roleCode = $("#role_code").val();
		var companyId = $("#company_id").val();
		$.get( "${pageContext.request.contextPath}/role/validate-code/" + companyId + "/" + roleCode , 
		function( data ) {
			if (data.outcome === 'true') {
				$(".alert-code").empty().removeClass("hidden");
		    	$(".alert-code").html("Duplicate role code available!");
			} else {
				$(".alert-code").empty().addClass("hidden");
				console.log("no duplicate code");
			}
		});
	});
	
	$(function() {
	    $('#role_code').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });
	});
	
	var aTable = $('#roleList').DataTable({
		"aaSorting" : [[1, 'asc']],
		 "lengthMenu": [[10], ["All"]],
		 ordering : false,
		 "oLanguage" : {
	            "sLengthMenu" : "Show _MENU_ Rows",
	            "sSearch" : "",
	            "sSearchWidth" : "300px",
	            "sSearchPlaceholder": "Search records ....",
	            "sEmptyTable": "No user role available",
	            "oPaginate" : {
	                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
	                "sNext" : "<span class='fa fa-chevron-right'></span>"
	            }
	        },
	});
	
	$('#roleList_length').hide();
	
	$("#company_list").on('change', function() {
		var companyId = $("#company_list option:selected").val();
		$("#company_id").val(companyId);
		$(".alert").empty().addClass("hidden");
		
		if(companyId == 'none'){
			$("#roleList tbody").empty();
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
				var rows = aTable.rows().remove().draw();
				var roleList = data.length;
				if (roleList > 0) {
					showRoleList(data);
				} else {
					$("#roleList tbody").empty();
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
			
			var html = '' 
					+ '<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>'
					+ '<a class="btn-delete btn btn-xs" onclick="del(this)"><i class="material-icons">delete_forever</i></a>'
					+ '<input type="hidden" id="row_id" name="id" class="row-id" value="' + id  + '"/>'
					+ '<input type="hidden" id="comp_id" name="compId" class="comp-id" value="' + company_id  + '"/>';
			var rowNode = aTable.row.add( [role_code,role_name,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
			$(rowNode).find(":eq(2)").css({"text-align": "center"});
		}
	}
	
	function add(el) {
		if ($("#company_list option:selected").val() == 'none') {
			$(".alert").empty().removeClass("hidden");
			$(".alert").html("Please select company!");
		}else{
			$("#id").val("");
			$("#role_code").val("");
			$("#role_name").val("");
		    $("#roleModal").modal();
		    $(".modal-backdrop.fade.in").off("click");
		    $(".modal").off("keydown");
		    $(".alert").empty().addClass("hidden");
		    $(".alert-code").empty().addClass("hidden");
		}
	}
	
	/**************************Start of Row Edit Function******************************/

	function edit(el) {
		var Id = $(el).closest('tr').find("#row_id").val();
		var Code = $(el).closest("tr").find("td:eq(0)").text();
		var Name = $(el).closest("tr").find("td:eq(1)").text();
		
		$("#id").val(Id);
		$("#role_code").val(Code);
		$("#role_name").val(Name);
		
		$("#roleModal").modal('show');
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
		$(".alert-code").empty().addClass("hidden");
	};
	/***************************End of Row Edit Function**********************************/
	$("#userTypeForm").submit(function(event){
		event.preventDefault();				
	    var formData = $("#userTypeForm").serialize();
	    
	    if($(".alert-code").hasClass('hidden')){
	    	$.ajax({	
		    	url : "${pageContext.request.contextPath}/role/save-roles",
		        type: 'POST',
		        data: formData,
		        async: false,
		        success: function (data) {				 
		        	$("#roleModal").modal('hide');
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
					url : "${pageContext.request.contextPath}/role/delete-roles/" + id ,
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
			