<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message code=""/>
<link href="${pageContext.request.contextPath}/css/select2.min.css" rel="stylesheet" media="screen">
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
	   <span style="text-shadow: 2px 2px 2px #aaa;">QP INFORMATION</span>
		
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
								<th class="align-left">QP NAME</th>
								<th class="align-left">QP ID</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${counter.count}</td>
								<td class="field_type_remarks">${info.qpName}</td>
								<td class="field_qm_name">${info.udQpId}</td>
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
									<input type="hidden" id="r_qm_id" value="${info.qmId}">
									<input type="hidden" id="r_qp_name" value="${info.qpName}">
									<input type="hidden" id="r_ud_qp_id" value="${info.udQpId}">
									<input type="hidden" id="r_revision_no" value="${info.revisionNo}">
									<input type="hidden" id="r_effective_date" value="${info.effectiveDate}">
									<input type="hidden" id="r_doc_name" value="${info.attachmentFile}">
									<input type="hidden" id="r_remarks" value="${info.remarks}">
									<input type="hidden" id="r_status" value="${info.status}">
									<a class="btn-edit btn btn-xs" onclick="edit(this)"><i class="material-icons">mode_edit</i></a>
									<c:if test="${not empty info.attachmentFile}">
									<%-- <a class="btn-edit btn btn-xs" onclick="fileViewer('${pageContext.request.contextPath}/image/qp_doc/${info.attachmentFile}')"><i class="fa fa-eye"></i></a> --%>
									<a id="down_link" class="btn-edit btn btn-xs" href="${pageContext.request.contextPath}/image/qp_doc/${info.attachmentFile}" target="_blank"><i class="fa fa-eye"></i></a>
									</c:if>
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
<div class="modal fade" id="qpInfoModal"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog" role="document">
             <div class="modal-content">
               <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
             <div class="modal-header bg-cyan" >
                 	<table class="table table-bordered" style="border: 2px solid #ddd;">
                 	<tbody>
                 	<tr>
                 	<td rowspan="3" style="width:50px"><img class="p-l-5" alt="Logo" src="${pageContext.request.contextPath}/image/drug-red-logo.png"></td>
                 	<td rowspan="3" style="width:110px">DRUG INTERNATIONAL<br> LIMITED (UNIT-2)</td>
                 	<td rowspan="3" class="text-center font-bold" style="font-size: 16px !important">Form: QP Info </td>
                 	<td>Document No.</td>
                 	<td colspan="2">FM-DIL-GN-076</td>
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
                 <form method="post" id="qpInfoForm" modelAttribute="qpInfo" enctype="multipart/form-data">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="qm_name">QM NAME:</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<select id="qmId" name="qmId" class="js-example-theme-single form-control" required="required" style="width: 100%;">
				                        	<option value="">--Select One--</option>
				                        <c:forEach var="info" items="${qmInfos}">
				                           	<option value="${info.id }">${info.qmName}</option>
				                        </c:forEach>
				                        </select>
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="qm_name">QP NAME:</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<input type="text" id="qpName" name="qpName" class="form-control"   autocomplete="off" >
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right top">
                            	<label for="qm_name">QP ID. :</label>
                            </div>
                            <div class="col-md-8">
	                            <div class="form-group">
	                            	<input type="text" id="udQpId" name="udQpId" class="form-control"   autocomplete="off" >
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right m-t-20 p-l-0">
                            	<label for="remarks">REVISION NO. :</label>
                            </div>
                            <div class="col-md-8 m-t-15">
                            	<div class="form-group">
                                	<input type="text" id="revisionNo" name="revisionNo" class="form-control" placeholder="Revision No"  autocomplete="off" >
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
	                 		<div class="col-md-4 align-right m-t-20 p-l-0">
                            	<label for="remarks">EFFECTIVE DATE. :</label>
                            </div>
                            <div class="col-md-8 m-t-15">
                            	<div class="form-group">
                                	<input type="date" id="effectiveDate" name="effectiveDate" class="form-control" placeholder="Effective Date"  autocomplete="off" >
	                            </div>
                            </div>
                 		</div>
                 		<div class="row">
                 		<div class="col-md-4 align-right top">
                            	<label for="method_name">ATTACHMENT :</label>
                            </div>
                 		<div class="col-md-8">
                 		<div class="form-group">							       
							    	<span class="btn btn-xs btn-file bg-blue-grey waves-effect">
									<input type="file" accept=".pdf" id="img_name" name="img_name" value="" onchange="readURL(this);">
									</span>
									<input type="text" id="image_name" name="imageName" class="form-control hidden" readonly>
									<input type="hidden" id="i_name" name="i_name">
							    </div>
						</div>
                 		</div>                		
                 		
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

	<div class="modal fade" id="pdfInfoModal"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
         <div class="modal-header bg-cyan">
                           <button type="button" class="mod-cl p-r-5 load close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
             <div class="modal-content">
             <div class="modal-body">
             
               <embed src="" width='100%;' height='500px;' type="application/pdf" id="emb_pdf_object_id" /> 
               
               </div>
               <div class="modal-footer bg-cyan">
	                 <button type="button" class="btn bg-red btn-xs waves-effect pull-right m-r-10"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						
	                 </div>
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
<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>
<script>
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
}
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
    $('#qp_name').keyup(function() {
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

function add(el) {
	$("#id").val("");
	$("#qmId").val('').trigger('change.select2');
	$("#qpName").val("");
	$("#udQpID").val("");
	$("#remarks").val("");
	$("#activity_status").prop('checked', true);
	$('#status').val('Y');
    $("#qpInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) { 
	var id 		= $(el).closest("tr").find("#row_id").val();
	var qmId 	= $(el).closest("tr").find("#r_qm_id").val();
	var qpName 	= $(el).closest("tr").find("#r_qp_name").val();
	var udQpId 	= $(el).closest("tr").find("#r_ud_qp_id").val();
	var effectiveDate 	= $(el).closest("tr").find("#r_effective_date").val();
	var revisionNo 	= $(el).closest("tr").find("#r_revision_no").val();
	var attachmentFile = $(el).closest("tr").find("#r_doc_name").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var status 		= $(el).closest('tr').find("#r_status").val();
	
	
	$("#id").val(id);
	$("#qmId").val(qmId).trigger('change.select2');
	$("#qpName").val(qpName);
	$("#udQpId").val(udQpId);
	$("#effectiveDate").val(effectiveDate);
	$("#revisionNo").val(revisionNo);
	$("#remarks").val(remarks);
	$("#image_name").val(attachmentFile);
	$("#i_name").val(attachmentFile);
	if(status == 'Y'){
		$('#activity_status').prop('checked', true);
		$('#status').val('Y');
		$('.check-status').text('Active');
	}else{
		$('#activity_status').prop('checked', false);
		$('#status').val('N');
		$('.check-status').text('Inactive');
	}
	
	$("#qpInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#qpInfoForm").submit(function(event){
	event.preventDefault();				
	  var formData = new FormData($("#qpInfoForm")[0]);
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
	    	url : "${pageContext.request.contextPath}/qp_info/save-qp-info",
	        type: 'POST',
	        data: formData,
	        enctype: 'multipart/form-data',
	        async: false,
	        processData: false,
	        contentType: false,
	        cache: false,
	        success: function (data) {				 
	        	$("#qpInfoModal").modal('hide');
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

function fileViewer(path) {
	
	$("#pdfInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $('#emb_pdf_object_id').css('display','block');
	$("#emb_pdf_object_id").attr("src",path+"#toolbar=0");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}
	
</script>
