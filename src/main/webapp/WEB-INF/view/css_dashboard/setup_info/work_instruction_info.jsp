<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
<style>
.modal-header {
    padding: 5px !important;
    }

.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    padding: 2px !important;
}
</style>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">WORK INSTRUCTION INFORMATION</span>
		<div class="btn-group language pull-right">
            <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">g_translate</i>
                <span><spring:message code="btn.lang.title"/></span><span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=en" onclick="chngLang(this)"><spring:message code="app.lang.english"/></a></li>
                <li><a class="dropdown-item" id="${pageContext.request.contextPath}/braketype/maintain?lang=bn" onclick="chngLang(this)"><spring:message code="app.lang.bangla"/></a></li>
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
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="insTable">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-left">WORK INSTRUCTION NO</th>
								<th class="align-left">WORK INSTRUCTION NAME</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${instructionList}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_instruction_name">${info.insUdId}</td>
								<td class="field_type_remarks">${info.instructionName}</td>
								<td class="field_type_status align-center" width="100px">
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
									<input type="hidden" id="r_instruction_name" value="${info.instructionName}">
									<input type="hidden" id="r_ins_ud_id" value="${info.insUdId}">
									<input type="hidden" id="r_doc_name" value="${info.workInsDocName}">
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

<!------------------------ Start: Create Product Color Types Modal -------------------->
<div class="modal fade" id="workInstructionModal" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
                <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: Work Instruction Info </td>
                 	<td>Document No.</td>
                 	<td colspan="2">FM-DIL-GN-075</td>
                 	</tr>
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Revision No.</td>
                 	<td>00</td>
                 	<td>Page 1 of 1</td>
                 	</tr> 
                 	<tr style="border: 2px solid #ddd;">                 	
                 	<td>Effective Date</td>
                 	<td colspan="2">15 Jan 2020</td>
                 	</tr>             	
                 	</tbody>
                 	</table>
                   
                 </div>
                 <form method="post" id="workInstructionForm" modelAttribute="instructionInfo" enctype="multipart/form-data">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="instruction_name">WORK INSTRUCTION NO. :</label>
                            </div>
                            <div class="col-md-4">
	                            <div class="form-group">
	                            	<input type="text" id="ud_ins_id" name="insUdId" class="form-control" placeholder="Instruction No"  autocomplete="off">
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="instruction_name">WORK INSTRUCTION NAME :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<input type="text" id="instruction_name" name="instructionName" class="form-control" placeholder="Work Instruction Name" required="required" autocomplete="off">
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right m-t-20">
                            	<label for="remarks">REMARKS :</label>
                            </div>
                            <div class="col-md-8 m-t-15">
                            	<div class="form-group">
                                	<textarea rows="3" id="remarks" name="remarks" class="form-control" placeholder=" Remarks goes here......."></textarea>
	                            </div>
                            </div>
                 		</div>
                 		<!-- <div class="row">
                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">ATTACHMENT :</label>
                            </div>
                 		<div class="col-md-8">
                 		<div class="form-group">							       
							    	<span class="btn btn-xs btn-file bg-blue-grey waves-effect">
									<input type="file" accept="image/*" id="img_name" name="img_name" value="" onchange="readURL(this);">
									</span>
									<input type="text" id="image_name" name="imageName" class="form-control hidden" readonly>
									<input type="hidden" id="i_name" name="i_name">
							    </div>
						</div>
                 		</div>  -->               		
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right">
                            	<label for="status">ACTIVITY STATUS :</label>
                            </div>
                            <div class="col-md-8">
                            	<div class="demo-checkbox">
									<input type="checkbox" id="activity_status" checked="checked" class="filled-in chk-col-green">
									<label for="activity_status"><b><span class="check-status">Active ?</span></b></label>
									<input type="hidden" id="status" name="status" value="Y">
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
textarea {
    resize: none;
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

.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
    vertical-align: middle !important;
}

[type="checkbox"] + label {
    height: 10px;
    font-size: 12px;
}

</style>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>
<script>
/* $(document).on('change', '.btn-file :file', function() {
	var input = $(this),
		label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [label]);
});

$('.btn-file :file').on('fileselect', function(event, label) {
	    
    var input = $(this).parents('.form-group').find(':text'),
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
} */
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

$(function() {
    $('#instruction_name').keyup(function() {
        this.value = this.value.toUpperCase();
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

$(function() {
    $('#ud_ins_id').keyup(function() {
        this.value = this.value.toUpperCase();
    });
});

$('#ud_ins_id').keyup(function() {
	var instNo = $("#ud_ins_id").val();
	$.get( "${pageContext.request.contextPath}/workInstruction/validate-instNo/" + instNo, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate Instruction No. available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
	});
});

function getFileSizeandName(input){
	//saveAttachment();

	for(var i =0; i<input.files.length; i++){           
		var filesizeInBytes = input.files[i].size + " Bytes";
		var filesizeInMB = (filesizeInBytes / (1024*1024)).toFixed(2);
		var filename = input.files[i].name;

		var html = '' 
			+ '<a class="btn btn-xs btn-danger waves-effect" onclick="del(this)"><i class="material-icons">delete_forever</i></a>'
			+ '<input type="hidden" id="file_name" name="fileName" class="file-name" value="'+ filename +'"/>'
			+ '<input type="hidden" id="file_size" name="fileSize" class="file-size" value="'+ filesizeInBytes +'"/>';
		var rowNode = aTable.row.add( [filename,filesizeInBytes,html] ).draw( false ).node();
		aTable.order([0, 'asc']).draw();
		aTable.page('last').draw(false);
		$(rowNode).find("td:eq(1)").css({"text-align": "center"});
		$(rowNode).find("td:eq(2)").css({"text-align": "center"});
	} 
}

$('#instruction_name').keyup(function() {
	var instructionName = $("#instruction_name").val();
	$.get( "${pageContext.request.contextPath}/workInstruction/validate-instructionName/" + instructionName, 
	function( data ) {
		if (data.outcome === 'true') {
			$(".alert-code").empty().removeClass("hidden");
	    	$(".alert-code").html("Duplicate Method Name available!");
		} else {
			$(".alert-code").empty().addClass("hidden");
			console.log("no duplicate code");
		}
	});
});

function add(el) {
	$("#id").val("");
	$("#ud_ins_id").val("");
	$("#ud_ins_id").removeAttr('readonly');
	$("#instruction_name").val("");
	$("#remarks").val("");
	$("#activity_status").prop('checked', true);
	$('.check-status').text('Active ?');
	$('#status').val('Y');
    $("#workInstructionModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) { 
	var Id 			= $(el).closest("tr").find("#row_id").val();
	var instName 	= $(el).closest("tr").find("#r_instruction_name").val();
	var instUdId 	= $(el).closest("tr").find("#r_ins_ud_id").val();
	var workInsDocName = $(el).closest("tr").find("#r_doc_name").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var status 		= $(el).closest('tr').find("#r_status").val();
	$("#id").val(Id);
	$("#instruction_name").val(instName);
	$("#ud_ins_id").val(instUdId);
	$("#ud_ins_id").attr('readonly', 'readonly');
	$("#remarks").val(remarks);
	$("#image_name").val(workInsDocName);
	$("#i_name").val(workInsDocName);
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active ?');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive ?');
	}
	
	$("#workInstructionModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#workInstructionForm").submit(function(event){
	event.preventDefault();				
	  var formData = new FormData($("#workInstructionForm")[0]);
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
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/workInstruction/save-workInstruction-info",
	        type: 'POST',
	        data: formData,
	        enctype: 'multipart/form-data',
	        async: false,
	        processData: false,
	        contentType: false,
	        cache: false,
	        success: function (data) {				 
	        	$("#workInstructionModal").modal('hide');
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
    	} else {
    		sweetAlert("Cancelled", "", "error", 0, false);
    	}
    	});
});


	
</script>
