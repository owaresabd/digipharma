mainPathUrl = "";

function LoadMainContent(url){debugger;
 isDirty = false;	
var navigateAway = function(){
		mainContentUrl = url;
		$.ajax({
	        url: url,
	        success:function( response, status, xhr ) { debugger;
	        	if(response.indexOf("<!DOCTYPE html>") != -1){
	        		window.location = url;
	        	}
	        	else{	        		
	   /*
	 	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	        						AUTO SCROLL STOP ON PAGE LOAD
	   	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	    */	        				        		
	        		$('#view_page').html(response);
//		    		$('.main-content').hide().html(response).fadeIn(500, function(){
//		    			ScrollToTop();
//		    		});
	        	}
	        	
	    	}
	    });
		isDirty = false;
	};
	if(	isDirty ){
		swal({
			title: "Discard changes?",
			text: "Are you sure to navigate away?",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#007AFF",
			confirmButtonText: "Yes, discard changes!",
			closeOnConfirm: true
		}, navigateAway);
	}
	else{
		navigateAway();
	}
	
}

function GetParameterByName(url, name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
    results = regex.exec(url);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function addZeroes( num ) {
   var num = Number(num);
   if (String(num).split(".").length < 2 || String(num).split(".")[1].length<=2 ){
          num = num.toFixed(2);
      }
   return num;
}

function addCommas(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function sweetAlert(title, message, status, timer = 5000, isReload = false){
    swal({
        title   : title,
        text    : '<strong><i>' + message + '</i></strong>',
        type    : status,
        html    : true,
        timer   : timer,
        allowEscapeKey  : false
    }, function () {
        swal.close();
        if(isReload)
            location.reload(true);
    });
    var e = $(".sweet-alert").find(".swal-timer-count");
    var n = +e.text();
    setInterval(function(){
        n > 1 && e.text (--n);
    }, 1000);
}

//THE SCRIPT THAT CHECKS IF THE KEY PRESSED IS A NUMERIC OR DECIMAL VALUE.
function isNumber(evt, element) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	if ((charCode != 46 || $(element).val().indexOf('.') != -1) && (charCode < 48 || charCode > 57)){
		return false;
	}
	    return true;
}


//Convert date format to 01-01-2000 from any date format.
function convertDate(date){
	var d = new Date(date); 
	var curr_date = d.getDate();  
    var curr_month = d.getMonth();  
    var curr_year = d.getFullYear();
    
    if(curr_date < 10){
    	var date = "0" + curr_date;
    }else {
    	var date = curr_date;
    }
    
    if(curr_month < 9 ){
    	var month = "0" + (curr_month + 1);
    } else {
    	var month = (curr_month + 1);
    }
    
    var formatDate = date + "-" + month + "-" + curr_year;
    
    return formatDate;
}

//Convert date format to 01-Jan-2000 from any date format.
function convertMDate(date){
	var d = new Date(date); 
	var m_names = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	var curr_date = d.getDate();  
    var curr_month = d.getMonth();  
    var curr_year = d.getFullYear();
    
    var formatDate = curr_date + "-" + m_names[parseInt(curr_month)] + "-" + curr_year;
    
    return formatDate;
}

function convertMmDate(date){
	var d = new Date(date); 
	var m_names = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
	var curr_date = d.getDate();  
    var curr_month = d.getMonth();  
    var curr_year = d.getFullYear();
    
    var formatDate = curr_date + "-" + m_names[parseInt(curr_month)] + "-" + curr_year;
    
    return formatDate;
}

//Convert minutes to 00 hour 00 minute.
function timeConvert(n) {
	var num = n;
	var hours = (num / 60);
	var rhours = Math.floor(hours);
	var minutes = (hours - rhours) * 60;
	var rminutes = Math.round(minutes);
	return rhours + " hr " + rminutes + " min";
}