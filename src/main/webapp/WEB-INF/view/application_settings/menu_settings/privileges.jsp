<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="privilege.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/menu/privilege?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/menu/privilege?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
            </ul>
        </div>
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<div class="alert alert-block alert-danger hidden"></div>
					<div class="row">
						<div class="col-md-5">
							<span><b><spring:message code="sys.suite"/> :</b></span>
							<select id="suite_list" class="form-control show-tick">
	                        	<option value="none">---Select Suite---</option>
	                        <c:forEach var="suite" items="${suites}">
	                           	<option value="${suite.id }">${suite.suiteName }</option>
	                        </c:forEach>
	                        </select>
						</div>
						<div class="col-md-5">
							<span><b><spring:message code="sys.module"/> :</b></span>
							<select id="module_list" class="form-control show-tick">
								<option value="none">---Select Module---</option>
							</select>
						</div>
						<div class="col-md-2 m-t-10">
							<button type="button" class="btn btn-sm bg-blue waves-effect pull-right" id="btnAdd" onClick="add(this)" data-toggle="tooltip" title="Add New">
								<i class="material-icons">games</i>
							</button>
						</div>
					 </div>
					<div class="row">
						<div class="col-xs-12 privilege-table">
							<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="privList">
								<thead>
									<tr>
										<th class="align-center" style="width: 120px;">PRIVILEGE CODE</th>
										<th class="align-left">PRIVILEGE NAME</th>
										<th class="align-left" style="width: 150px;">MODULE NAME</th>
										<th class="align-left">SUITE NAME</th>
										<th class="align-left">URL</th>
										<th class="align-center" style="width: 100px;">ACTION</th>
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

<!------------------------ Start: Create Product Color Types Modal -------------------->

	 <div class="modal fade" id="privilegeModal" tabindex="-1" role="dialog">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="defaultModalLabel"><spring:message code="sys.privilege"/></h4>
                 </div>
                 <form method="post" id="privilegeForm" modelAttribute="privilege">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<div class="row clearfix">
                         	<div class="col-md-4">
                             	<div class="form-group">
                                 	<div class="form-line">
                                 		<input type="hidden" id="suite_id" name="suiteId" value=""/>
										<input type="hidden" id="module_id" name="moduleId" value=""/>
                                 		<input type="hidden" id="id" name="id" value=""/>
                                    	<input type="number" id="privilege_code" name="privilegeCode" value="" class="form-control" placeholder="Privilege Code" maxlength="8" required>
                                 	</div>
                             	</div>
                         	</div>
	             			<div class="col-md-8">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="text" id="privilege_name" name="privilegeName" value="" class="form-control" placeholder="Privielge Name" maxlength="255" required>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-md-12">
	                            <div class="form-group">
	                                <div class="form-line">
	                                     <input type="text" id="menu_url" name="menuUrl" value="" class="form-control" placeholder="Privilege Url" maxlength="255" required>
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
	
	var aTable = $('#privList').DataTable({
		"aaSorting" : [[1, 'asc']],
		 "lengthMenu": [[10000], ["All"]],
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
	
	$('#privilege_code').keyup(function() {
		var suiteId = $("#suite_id").val();
		var moduleId = $("#module_id").val();
		var privilegeCode = $("#privilege_code").val();
		$.get( "${pageContext.request.contextPath}/menu/validate-privilegeCode/" + suiteId + "/" + moduleId + "/" + privilegeCode, 
		function( data ) {
			if (data.outcome === 'true') {
				$(".alert-code").empty().removeClass("hidden");
		    	$(".alert-code").html("Duplicate privilege code available!");
			} else {
				$(".alert-code").empty().addClass("hidden");
				console.log("no duplicate code");
			}
		});
	});
	
	$("#suite_list").on('change', function() {
		var suiteId = $("#suite_list option:selected").val();
		$("#suite_id").val(suiteId);
		$(".alert").empty().addClass("hidden");
		
		var rows = aTable.rows().remove().draw();
		if(suiteId == 'none'){
			$("#module_id").val("");
			$("#module_list").empty(); 
			$("<option />", {
		        val: 'none',
		        text: '---Select Module---'
		    }).appendTo("#module_list");
		}else{
			loadModuleList(suiteId);
		}
	});
	
	function loadModuleList(suiteId) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/menu/moduleList/" + suiteId ,
			dataType : 'json',
			success : function(data) {
				$("#module_list").empty(); 
				var moduleList = data.length;
				if (moduleList > 0) {
					$("<option />", {
				        val: 'none',
				        text: '---Select Module---'
				    }).appendTo("#module_list");
					showModuleList(data);
				} else {
					$("#module_id").val("");
					$("<option />", {
				        val: 'none',
				        text: '---Select Module---'
				    }).appendTo("#module_list");
				}

			}
		});
	}
	
	function showModuleList(data) {
		for (var i = 0; i < data.length; i++) {
			var mod_id = data[i].id || "";
			var mod_code = data[i].moduleCode || "";
			var mod_name = data[i].moduleName || "";
			
			$('#module_list').append('<option value="' + mod_id + '">' + mod_name + '</option>');
		}
	}
	
	$("#module_list").on('change', function() {
		var moduleId = $("#module_list option:selected").val();
		$("#module_id").val(moduleId);
		$(".alert").empty().addClass("hidden");
		
		if(moduleId == 'none'){
			$("#privList tbody").empty();
		}else{
			loadPrivilegeList(moduleId);
		}
	});
	
	function loadPrivilegeList(moduleId){
		var suiteId = $("#suite_id").val();
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/menu/privilegeList/" + suiteId + "/" + moduleId,
			dataType : 'json',
			success : function(data) {
				var rows = aTable.rows().remove().draw();
				var privileges = data.length;
				if (privileges > 0) {
					showPrivileges(data);
				} else {
					$("#privList tbody").empty();
				}

			}
		});
	}
	
	function showPrivileges(data){
		for (var i = 0; i < data.length; i++) {
			var id = data[i].id || "";
			var privCode = data[i].privilegeCode || "";
			var privName = data[i].privilegeName || "";
			var modName = data[i].moduleName || "";
			var sutName = data[i].suiteName || "";
			var menuUrl = data[i].menuUrl || "";
			
			var html = '' 
					+ '<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>'
					+ '<a class="btn-delete btn btn-xs" onclick="del(this)"><i class="material-icons">delete_forever</i></a>'
					+ '<input type="hidden" id="row_id" value="' + id  + '">';
			var rowNode = aTable.row.add( [privCode,privName,modName,sutName,menuUrl,html] ).draw( false ).node();
			$(rowNode).find(":eq(0)").css({"text-align": "center"});
			$(rowNode).find(":eq(5)").css({"text-align": "center"});
		}
	}
	
	function add(el) {
		if ($("#suite_list option:selected").val() == 'none') {
			$(".alert").empty().removeClass("hidden");
			$(".alert").html("Please select suite!");
		}else if($("#module_list option:selected").val() == 'none'){
			$(".alert").empty().removeClass("hidden");
			$(".alert").html("Please select module!");
		}else{
			$("#id").val("");
			$("#privilege_code").val("");
			$("#privilege_name").val("");
			$("#menu_url").val("");
		    $("#privilegeModal").modal();
		    $(".modal-backdrop.fade.in").off("click");
		    $(".modal").off("keydown");
		    $(".alert").empty().addClass("hidden");
		    $(".alert-code").empty().addClass("hidden");
		}
	}
	
	/**************************Start of Row Edit Function******************************/

	function edit(el) {
		var Id = $(el).closest("tr").find("#row_id").val();
		var pCode = $(el).closest("tr").find("td:eq(0)").text();
		var pName = $(el).closest("tr").find("td:eq(1)").text();
		var pUrl = $(el).closest("tr").find("td:eq(4)").text();
		
		$("#id").val(Id);
		$("#privilege_code").val(pCode);
		$("#privilege_name").val(pName);
		$("#menu_url").val(pUrl);
		
		$("#privilegeModal").modal('show');
		$(".alert-code").empty().addClass("hidden");
		$(".modal-backdrop.fade.in").off("click");
		$(".modal").off("keydown");
	};
	/***************************End of Row Edit Function**********************************/
	$("#privilegeForm").submit(function(event){
		event.preventDefault();				
	    var formData = $("#privilegeForm").serialize();
	    if($(".alert-code").hasClass('hidden')){
	    	$.ajax({	
		    	url : "${pageContext.request.contextPath}/menu/save-privileges",
		        type: 'POST',
		        data: formData,
		        async: false,
		        success: function (data) {				 
		        	$("#privilegeModal").modal('hide');
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
					url : "${pageContext.request.contextPath}/menu/delete-privileges/" + id ,
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
		    
			