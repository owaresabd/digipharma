<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code=""/>
<div class="container-fluid">
	<div class="block-header">
	   <span style="text-shadow: 2px 2px 2px #aaa;">ANALYST VALIDATION VERIFY INFO</span>
		
	</div>
		   <form method="post" id="analystValidationUpdateForm" onkeypress="if (event.keyCode == 13) { return false; }">
              <div class="alert-code alert-block alert-danger hidden"></div><br>
	<div class="row clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="card">
				<div class="body table-responsive table-bordered">
					<table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="verifyTable">
						<thead>
							<tr>
								<th class="align-center" style="width: 110px;">ANALYST NAME</th>
								<th class="align-center" style="width: 110px;">ANALYST ID</th>
								<th class="align-center" style="width: 100px;">AREA OF COMPETENCY</th>
								<th class="align-center" style="width: 100px;">DATE OF COMPETENCY</th>
								<th class="align-center" style="width: 100px;">NEXT DATE OF COMPETENCY</th>
								<th class="align-center" style="width: 100px;">STATUS</th>
								<th class="align-center" style="width: 100px;">APPROVED BY</th>
								<th class="align-center">REMARKS</th>
								<th class="align-center" style="width: 100px;"> 
								<span class="input-group-addon">
                                           <!-- <input type="checkbox" class="filled-in" name="" id="checkedAll" />-->
										    ALL<input type="checkbox" class="filled-in"  id="checkedAll" />
                                            <label for="checkedAll"></label>
											
											<input type="hidden" id="equipmentId" value="${info.equipmentId}">
									
                                </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="info" items="${infos}" varStatus="counter">
							<tr>
								<td class="align-center">${info.analystName}</td>
								<td class="align-center">${info.udAnalystId}</td>
								<td class="align-center">${info.areaOfCompetency}</td>
								<td class="align-center"><fmt:formatDate var="dateOfCompetency" value="${info.dateOfCompetency}" pattern="dd-MMM-yyyy"/>
								${dateOfCompetency}</td>
								<td class="align-center"><fmt:formatDate var="nextDateOfCompetency" value="${info.nextDateOfCompetency}" pattern="dd-MMM-yyyy"/>
								${nextDateOfCompetency}</td>
								<td class="align-center">${info.status}</td>
								<td class="align-center">${info.dispensedByName}</td>
								<td class="align-left">
								 <input type="text" name="remarks[]" id="" value="${info.remarks}">
								</td>
								<td class="align-center">
							   	<input type="hidden" name="id[]" id="row_id" value="${info.id}">
									<input value="N" id="hid_b${counter.count}" type="hidden" class="hiddenValCkbx"  name="hiddenValCkbx[]"/>
		                                    <input type="checkbox" id="b${counter.count}"  class="filled-in ads_Checkbox" name="ckBoxgrp"/>
											<label for="b${counter.count}"></label>
                               
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div>
					<!--<a class="btn-edit btn btn-xs" onclick="verify(this)"><i class="material-icons">forward</i></a>-->
					<button type="submit" id="saveBtn" title="Click to Verify" class="btn bg-green btn-sm waves-effect pull-right m-r-10">
							<i class="material-icons">forward</i></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

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

[type="checkbox"] + label {
    height: 10px;
    font-size: 12px;
}
</style>
	
<script>


	

$(document).ready(function() {

 $("#checkedAll").change(function() {
        if (this.checked) {
            $(".ads_Checkbox").each(function() {
                this.checked=true;
                $(".hiddenValCkbx").val("Y");
		      });

        } else {
            $(".ads_Checkbox").each(function() {
                this.checked=false;
                $(".hiddenValCkbx").val("N");
		    });
        }
        
    });

    
    $(".ads_Checkbox").click(function () {
        if ($(this).is(":checked")) {
            var isAllChecked = 0;

            $(".ads_Checkbox").each(function() {
                if (!this.checked)
                    isAllChecked = 1;
					var id = $(this).attr('id');
		    });

            if (isAllChecked == 0) {
                $("#checkedAll").prop("checked", true);
	        }     
        }
        else {
            $("#checkedAll").prop("checked", false);
	    }
    });
		$( ".ads_Checkbox" ).on( "click", function() {
		var id = $(this).attr('id');
		if ($(this).is(':checked')) {
			     var rnsId = id.substring(1, id.length);	
				 $("#hid_b"+rnsId).val("Y");	
		}else{
			 var rnsId = id.substring(1, id.length);	
			$("#hid_b"+rnsId).val("N");
		}
	});
		
});

$("#analystValidationUpdateForm").submit(function(event){
	event.preventDefault();				
    var formData = $("#analystValidationUpdateForm").serialize();
	
	
    swal({
        title: "Are you sure?",
        text: "You will verify selected data!",
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
	    	url : "${pageContext.request.contextPath}/logbook_chemi/verifyAnalystValidation",
	        type: 'POST',
	        data: formData,
	        async: false,
			success : function(data) {
				$("#view_page").html(data);
				sweetAlert("Updated!", "Verified Successfully", "success", 1000, false);
			},
	        error: function(){
	        	sweetAlert("Failed!", "Something went wrong.", "fail", 1000, false);
		  	}
	    });
    }else{
    	sweetAlert("Failed!", "Please remove all error, then submit again.", "warning", 1000, false);
    }
    	} else {
        	sweetAlert("Cancelled", "", "error", 1000, false);
        }
        });
});

</script>
