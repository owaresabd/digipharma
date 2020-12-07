<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<spring:message code=""/>
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
	   <span style="text-shadow: 2px 2px 2px #aaa;">QM &amp; QP DOCUMENT INFORMATION</span>
		
	</div>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
			
                        
                        <div class="body">
                     	
                       
                            <div class="row clearfix">
                            
                                <div class="col-xs-12 ol-sm-12 col-md-12 col-lg-12">
                               <c:forEach var="qm" items="${infos}" varStatus="loop1">    
                               <div class="panel-group" id="accordion_1" role="tablist" aria-multiselectable="false">
								<div class="panel panel-col-cyan">
								<div class="panel-body">

											<div class="info-box-3 bg-teal">
											<div class="col-md-10 content">
									
										<div class="text"><h4>QM Name: ${qm.qmName} </h4></div>
										<div class="text"><h4>QM ID : ${qm.udQmId}&nbsp;|| &nbsp; Effective Date : <fmt:formatDate pattern="dd.MM.yyyy" value="${qm.qmEffectiveDate}" var="qmEffectiveDate"/>
	                            	<c:out value="${qmEffectiveDate}"/>&nbsp;|| &nbsp;Revision No.:${qm.qmRevisionNo} </h4> </div>
										
									</div>
									
									
											<div class="col-md-2 align-right">
											<a class="btn-edit btn btn-xl m-t-15  pull-right" onclick="fileViewer('${pageContext.request.contextPath}/image/qm_doc/${qm.qmAttachmentFile}')"><i class="material-icons">visibility</i></i></a>
									
											<%-- <a id="down_link" class="btn-edit btn btn-xl m-t-15  pull-right" href="${pageContext.request.contextPath}/image/qm_doc/${qm.qmAttachmentFile}" target="_blank"><i class="material-icons">visibility</i></a> --%>
									
									
									</div>
									
											</div>
											
								<c:forEach var="qp" items="${qplist}" varStatus="loop2">
								<c:if test="${qm.qmId == qp.qmId }">
								<div class="panel panel-col-cyan">
                                            <div class="panel-heading" role="tab" id="headingOne_1">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion_1" href="#${qp.udQpId}" aria-expanded="true" aria-controls="collapseOne_1" class="">
                                                        
                                                     QP Name: ${qp.qpName}<br> QP ID : ${qp.udQpId}&nbsp;|| &nbsp;   Effective Date:<fmt:formatDate pattern="dd.MM.yyyy" value="${qp.qpEffectiveDate}" var="qpEffectiveDate"/>
	                            	<c:out value="${qpEffectiveDate}"/>&nbsp;|| &nbsp;   Revision No.${qp.qpRevisionNo}
                                                    </a>
                                                    
                                                </h4>
                                            </div>
                                            <div id="${qp.udQpId}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne_1" aria-expanded="false" style="">
                                                <div class="panel-body">
                                                <a class="btn-edit btn btn-xl m-t-15  pull-right" onclick="fileViewer('${pageContext.request.contextPath}/image/qp_doc/${qp.qpAttachmentFile}')"><i class="fa fa-eye"></i></a>
                                                <%-- <a id="down_link" class="btn-edit btn btn-xs bg-blue waves-effect pull-right" href="${pageContext.request.contextPath}/image/qp_doc/${qp.qpAttachmentFile}" target="_blank"><i class="material-icons">visibility</i></a> --%>
                                                <br><br>
                                                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="insTable">
						<thead>
							<tr>
								<th class="align-center" style="width: 70px;">SL NO</th>
								<th class="align-left">DOCUMENT ID</th>
								<th class="align-left">DOCUMENT NAME</th>
								<th class="align-left">DOCUMENT TYPE</th>
								<th class="align-left">EFFECTIVE DATE</th>
								<th class="align-center" style="width: 100px;">ACTION</th>
							</tr>
						</thead>
						<tbody>
						<% int rowNo=1; %>
							<c:forEach var="doc" items="${docList}" varStatus="loop3">
							<c:if test="${qp.qpId == doc.qpId }">
							<tr>
								<td class="align-center"><%=rowNo++%></td>
								<td class="field_type_remarks">${doc.udDocumentId}</td>
								<td class="field_qm_name">${doc.documentName}</td>
								<td class="field_qm_name">${doc.docTypeName}</td>
								<td class="field_qm_name">
								<fmt:formatDate pattern="dd.MM.yyyy" value="${doc.docEffectiveDate}" var="docEffectiveDate"/>
	                            	<c:out value="${docEffectiveDate}"/>
								</td>
								
								<td class="align-center">
								<a class="btn-edit btn btn-xs" onclick="fileViewer('${pageContext.request.contextPath}/image/qp_doc/${doc.docAttachmentFile}')"><i class="fa fa-eye"></i></a>
									<%-- <a id="down_link" class="btn-edit btn btn-xs" href="${pageContext.request.contextPath}/image/qp_doc/${doc.docAttachmentFile}" target="_blank"><i class="material-icons">visibility</i></a> --%>
									
								</td>
							</tr>
							
							</c:if>
							</c:forEach>
						</tbody>
					</table>
                                                </div>
                                            </div>
                                        </div>
                                        </c:if>
                                      </c:forEach>  
                                    </div>
                                    </div>
                                    </div>
                                     </c:forEach>   
                                </div>
                          </div>
                        </div>
                    </div>
		</div>
	</div>

<!------------------------ Start: Create Product Color Types Modal -------------------->
<div class="modal fade" id="pdfInfoModal"  role="dialog" data-backdrop="static" data-keyboard="false">
         <div class="modal-dialog modal-lg" role="document">
         <div class="modal-header bg-cyan">
                           <button type="button" class="mod-cl p-r-5 load-file close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
             <div class="modal-content">
             <div class="modal-body">
             
               <embed src="" width='100%;' height='500px;' type="application/pdf" id="emb_pdf_object_id" /> 
               
               </div>
               <div class="modal-footer bg-cyan">
	                 <button type="button" class="btn bg-red btn-xs waves-effect pull-right m-r-10 load-file"  data-dismiss="modal">
							<i class="material-icons">close</i>
							<span>CLOSE</span>
						</button>
						
	                 </div>
             </div>
             
         </div>
     </div> 

	 
</div>  

<style>
.info-box-2{
margin-bottom:10px;
}

.info-box-3{
margin-bottom:10px;
}
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
function pageReload(link){
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
$(".close").on('click', function(e){
	var link =  "${pageContext.request.contextPath}/qp_info/document_infos";
	$("#pdfInfoModal").modal('hide');
	$('.modal-backdrop').remove();
	pageReload(link);
});
$(".load-file").on('click', function(e){
	
	$("#pdfInfoModal").modal('hide');
	$('#emb_pdf_object_id').css('display','none');
	$("#emb_pdf_object_id").attr("src","");
	
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
/*
$('#instruction_no').change(function() {
	var instructionNo = $("#instruction_no").val();
			$.ajax({
				async : false,
				url : "${pageContext.request.contextPath}/workInstruction/collect-insUUId/" + instructionNo,
				success : function(data) {			
					var udInsNos = data.length;
					for (var i = 0; i < data.length; i++) {
						var udid = data[i].insUdId;
						$("#ud_instruction_no").val(udid);			
					}
					}
			});
});
*/
function add(el) {
	$("#id").val("");
	$("#qpId").val('').trigger('change.select2');
	$("#udDocumentId").val("");
	$("#documentName").val("");
	$("#remarks").val("");
	$("#activity_status").prop('checked', true);
	$('#status').val('Y');
    $("#docInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}

function edit(el) { 
	var id 		= $(el).closest("tr").find("#row_id").val();
	var qpId 	= $(el).closest("tr").find("#row_qp_id").val();
	var typeId 	= $(el).closest("tr").find("#row_type_id").val();
	var docName = $(el).closest("tr").find("#r_doc_name").val();
	var udDocId = $(el).closest("tr").find("#r_ud_doc_id").val();
	var effectiveDate = $(el).closest("tr").find("#r_effective_date").val();
	var revisionNo = $(el).closest("tr").find("#r_revision_no").val();
	var attachmentFile = $(el).closest("tr").find("#r_doc_name").val();
	var remarks 	= $(el).closest("tr").find("#r_remarks").val();
	var status 		= $(el).closest('tr').find("#r_status").val();
	
	
	$("#id").val(id);
	$("#qpId").val(qpId).trigger('change.select2');
	$("#docTypeId").val(typeId).trigger('change.select2');
	$("#documentName").val(docName);
	$("#udDocumentId").val(udDocId);
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
	
	$("#docInfoModal").modal();
    $(".modal-backdrop.fade.in").off("click");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
};

$("#docInfoForm").submit(function(event){
	event.preventDefault();				
	  var formData = new FormData($("#docInfoForm")[0]);
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
	    	url : "${pageContext.request.contextPath}/qp_info/save-document-info",
	        type: 'POST',
	        data: formData,
	        enctype: 'multipart/form-data',
	        async: false,
	        processData: false,
	        contentType: false,
	        cache: false,
	        success: function (data) {				 
	        	$("#docInfoModal").modal('hide');
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
	$("#emb_pdf_object_id").attr("src",path+"#toolbar=0&navpanes=0");
    $(".modal").off("keydown");
    $(".alert").empty().addClass("hidden");
    $(".alert-code").empty().addClass("hidden");
}
	
</script>
