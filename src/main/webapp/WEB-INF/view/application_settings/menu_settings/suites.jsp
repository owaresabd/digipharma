<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	    <span style="text-shadow: 2px 2px 2px #aaa;"><spring:message code="suite.page.title"/></span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/menu/suite?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/menu/suite?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="roleList">
						<thead>
							<tr>
								<th class="hidden">SUITE ID</th>
								<th class="align-center" style="width: 100px;">SUITE CODE</th>
								<th class="align-left">SUITE NAME</th>
								<th class="align-center" style="width: 100px;">SUITE ICON</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="suite" items="${suites}">
							<tr>
								<td class="field_suite_id hidden">${suite.id}</td>
								<td class="field_suite_code align-center">${suite.suiteCode}</td>
								<td class="field_suite_name">${suite.suiteName}</td>
								<td class="field_suite_icon align-center">
									<i class="material-icons">${suite.suiteIcon}</i>
								</td>
								<td class="align-center">
									<input type="hidden" id="row_id" value="${suite.id}">
									<input type="hidden" id="icon" value="${suite.suiteIcon}">
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

	 <div class="modal fade" id="suiteModal" tabindex="-1" role="dialog">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                 <div class="modal-header bg-blue-grey">
                 	<button type="button" class="mod-cl close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="defaultModalLabel"><spring:message code="sys.suite"/></h4>
                 </div>
                 <form method="post" id="suiteForm" modelAttribute="suite">
                 	<div class="modal-body">
	                 	<div class="row clearfix">
	                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
	                        <div class="col-md-4">
	                             <div class="form-group">
	                                 <div class="form-line">
	                                 	<input type="hidden" id="id" name="id" value=""/>
	                                    <input type="number" id="suite_code" name="suiteCode" value="" class="form-control" placeholder="Suite Code" maxlength="8" required>
	                                 </div>
	                             </div>
	                         </div>
	                         <div class="col-md-8">
	                             <div class="form-group">
	                                 <div class="form-line">
	                                     <input type="text" id="suite_name" name="suiteName" value="" class="form-control" placeholder="Suite Name" maxlength="255" required>
	                                 </div>
	                             </div>
	                         </div>
	                         <div class="col-md-4">
	                             <div class="form-group">
	                                 <div class="form-line">
	                                     <input type="text" id="suite_icon" name="suiteIcon" value="" class="form-control" placeholder="Suite Icon" maxlength="255" required>
	                                 </div>
	                             </div>
	                         </div>
	                         <div class="col-md-4">
	                             <div class="form-group">
	                                 <div>
	                                    <i class="material-icons"><span id="suite_show"></span></i>
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

$('#suite_code').keyup(function() {
	var suiteCode = $("#suite_code").val();
	$.get( "${pageContext.request.contextPath}/menu/validate-suiteCode/" + suiteCode, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate suite code available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
	});
});

$(function() {
    $('#suite_icon').keyup(function() {
    	$("#suite_show").text(this.value);
    });
});

function add(el) {
	$("#id").val("");
	$("#suite_code").val("");
	$("#suite_name").val("");
	$("#suite_icon").val("");
	$("#suite_show").text("");
    $("#suiteModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

/**************************Start of Row Edit Function******************************/

function edit(el) {
	var Id = $(el).closest("tr").find("td:eq(0)").text();
	var Code = $(el).closest("tr").find("td:eq(1)").text();
	var Name = $(el).closest("tr").find("td:eq(2)").text();
	var Icon = $(el).closest('tr').find("#icon").val();
	
	$("#id").val(Id);
	$("#suite_code").val(Code);
	$("#suite_name").val(Name);
	$("#suite_icon").val(Icon);
	$("#suite_show").text(Icon);
	
	$("#suiteModal").modal('show');
	$(".modal-backdrop.fade.in").off("click");
	$(".modal").off("keydown");
	$(".alert-code").empty().addClass("hidden");
};

$("#suiteForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#suiteForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/menu/save-suites",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#suiteModal").modal('hide');
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
				url : "${pageContext.request.contextPath}/menu/delete-suites/" + id ,
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
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>
		    
			