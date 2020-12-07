<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
$(document).ready(function () {
    // Add slimscroll to element
    $('.slimscroll').slimscroll({
        height: '450px'
    })
});
</script>
<!------------------------ Start: Create Product Color Types Modal -------------------->

 <div class="modal-header bg-blue-grey">
	<button type="button" class="mod-cl close load" data-dismiss="modal" aria-hidden="true">&times;</button>
   	<h4 class="modal-title align-center" id="defaultModalLabel">POSITION SUMMARY INFO</h4>
</div>
                <form method="post" id="positionInfoForm"  onkeypress="if (event.keyCode == 13) { return false; }">
                 	<div class="modal-body">
                 		<div class="alert-code alert-block alert-danger hidden"></div><br>
                 		<input type="hidden" id="id" name="id" value=""/>
                 		<input type="hidden" id="desig_id" name="desigId" value=""/>
                 		
                 		<div class="row">
                        <div class="col-md-12">
                        	<div class="form-group">
                            	<table id="positionTable" class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th class="align-center">POSITION NO</th>
										<th class="align-center" style="width: 150px;">DESCRIPTION</th>
										<th class="align-center">####</th>
									</tr>
								</thead>
								<tbody id="tbl_posts_body">
								<c:choose>
								    <c:when test="${positionInfoList.size() > 0}">
										<c:forEach var="info" items="${positionInfoList}" varStatus="counter">
										<tr>
											<td class="align-center">
												<span class="sn">${counter.count}</span>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="position_desc" name='positionDesc[]' value="${info.positionDesc}" required="required"/>
											</td>
											<td class="align-center">
												<a class="btn-edit btn btn-xs" onclick="addRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
												<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>
											</td>
										</tr>
										</c:forEach>
								    </c:when>    
								    <c:otherwise>
								        <tr>
											<td class="align-center">
												<span class="sn">1</span>
											</td>
											<td class="align-center">
												<input style="width: 100%;" type="text" id="position_desc" name='positionDesc[]' required="required"/>
											</td>
											<td class="align-center">
												<a class="btn-edit btn btn-xs" onclick="addRow(this)"><i class="glyphicon glyphicon-plus"></i></a>
												<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>
											</td>
										</tr>
								    </c:otherwise>
								</c:choose>

								</tbody>
								</table>
							</div>
		                </div>   
		                </div>
	                 </div>
	                 <div class="modal-footer" style="background-color: #ced9dc;">
						<button type="submit" id="saveBtn" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">save</i>
							<span><spring:message code="btn.save"/></span>
						</button>
						<button type="button" class="btn bg-red btn-sm waves-effect pull-right m-r-10" data-dismiss="modal">
							<i class="material-icons">close</i><span>CLOSE</span>
						</button>
	                 </div>
                </form>

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

[type="checkbox"] + label {
    height: 10px;
    font-size: 12px;
}
</style>
<script>
$('#activity_statusP').change(function() {
	if (this.checked) {
		$('#statusP').val('Y');
		$('.check-statusP').text('Active ?');
	}else{
		$('#statusP').val('N');
		$('.check-statusP').text('Inactive ?');
	}
});

var aTable = $('#positionTable').DataTable({
	"aaSorting" : [],
	 "lengthMenu": [[10000], ["All"]],
	 ordering : false,
	 "oLanguage" : {
            "sLengthMenu" : "Show _MENU_ Rows",
            "sSearch" : "",
            "sSearchWidth" : "300px",
            "sSearchPlaceholder": "Search records ....",
            "sEmptyTable": "No data available here",
            "oPaginate" : {
                "sPrevious" : "<span class='fa fa-chevron-left'></span>",
                "sNext" : "<span class='fa fa-chevron-right'></span>"
            }
        },
});

$('#positionTable_length, #positionTable_info, #positionTable_paginate, #positionTable_filter').hide();

function addRow(el){
	
	var positionNo = $('#positionTable >tbody >tr').length+1;
	var td1="<span class='sn'>"+positionNo+"</span>";
	var td2="<input style='width: 100%;' type='text' id='r_position_desc' name='positionDesc[]' required='required'/>";
	var html = '' 
			+ '<a class="btn-edit btn btn-xs" onclick="addRow(this)"><i class="glyphicon glyphicon-plus"></i></a>'
			+ '<a class="btn btn-xs waves-effect" onclick="remove(this)"><i class="material-icons">delete_forever</i></a>';
	var rowNode = aTable.row.add([td1, td2, html]).draw( false ).node();
	aTable.draw();
	aTable.page('last').draw(false);
	$(rowNode).find("td:eq(0)").css({"text-align": "center"});
	$(rowNode).find("td:eq(2)").css({"text-align": "center"});
		
	}
	
function remove(el){
	var rowCount = $('#tbl_posts_body tr').length;
	if(rowCount>1){
	 var tbl = $(el).closest('table').DataTable();
	 var tr  = $(el).closest('tr');
	   
	    tbl.row(tr).remove().draw(false);
	$('#tbl_posts_body tr').each(function(index) {
	      //alert(index);
	      $(this).find('span.sn').html(index+1);
	    });	
	
	}else{
		sweetAlert("Alert!", "You can not delete all rows", "warning");
		}
}

$("#positionInfoForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#positionInfoForm").serialize();
    
    if($(".alert-code").hasClass('hidden')){
    	$.ajax({	
	    	url : "${pageContext.request.contextPath}/designation/save-position-infos",
	        type: 'POST',
	        data: formData,
	        async: false,
	        success: function (data) {				 
	        	$("#otherInfoModal").modal('hide');
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

</script>
