<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<spring:message code=""/>
<!DOCTYPE html>
<html>

<head>
    <!-- <meta charset="UTF-8"> -->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>PMS :: Pharmacy Management System</title>
    <!-- Favicon-->
    <link rel="icon" href="${pageContext.request.contextPath}/image/ati.jpg" type="image/x-icon" >
	
	<!--START:::********************** Project CSS Library *****************************-->
	
    <!-- Google Fonts -->
    <link href="${pageContext.request.contextPath}/css/google-font.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/material-icon.css" rel="stylesheet" type="text/css">

    <!-- Waves Effect Css -->
    <link href="${pageContext.request.contextPath}/vendor/node-waves/waves.css" rel="stylesheet" />

    <!-- Animation Css -->
    <link href="${pageContext.request.contextPath}/vendor/animate-css/animate.css" rel="stylesheet" />
    
    <!-- Dropzone Css -->
    <link href="${pageContext.request.contextPath}/vendor/dropzone/dropzone.css" rel="stylesheet">
    

    <!-- Morris Chart Css-->
    <link href="${pageContext.request.contextPath}/vendor/morrisjs/morris.css" rel="stylesheet" />
    
    <!-- Sweetalert Css -->
	<link href="${pageContext.request.contextPath}/vendor/sweetalert/sweetalert.css" rel="stylesheet" />
	
	<!-- Multi Select Css -->
    <link  href="${pageContext.request.contextPath}/vendor/multi-select/css/multi-select.css" rel="stylesheet">

    <!-- Bootstrap Spinner Css -->
    <link  href="${pageContext.request.contextPath}/vendor/jquery-spinner/css/bootstrap-spinner.css" rel="stylesheet">

    <!-- Bootstrap Tagsinput Css -->
    <link  href="${pageContext.request.contextPath}/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">

    <!-- Bootstrap Select Css -->
    <link  href="${pageContext.request.contextPath}/vendor/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />

    <!-- noUISlider Css -->
    <link  href="${pageContext.request.contextPath}/vendor/nouislider/nouislider.min.css" rel="stylesheet" />

    <!-- Custom Css -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <!-- Fontawesome Css -->
    <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    
    <!-- Bootstrap Core Css -->
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
    
    <!-- JQuery DataTable Css -->
    <link href="${pageContext.request.contextPath}/vendor/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.css" rel="stylesheet">

    <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
    <link href="${pageContext.request.contextPath}/css/themes/all-themes.css" rel="stylesheet" />
    
    <link href="${pageContext.request.contextPath}/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">


	<!--END:::********************** Project CSS Library *****************************-->
	
	<!--START:::********************** Project JS Library *****************************-->
    
    <!-- Jquery Core Js -->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core Js -->
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.js"></script>

    <!-- Select Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/bootstrap-select/js/bootstrap-select.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/multi-select/js/jquery.multi-select.js"></script>

    <!-- Slimscroll Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/jquery-slimscroll/jquery.slimscroll.js"></script>

    <!-- Waves Effect Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/node-waves/waves.js"></script>

    <!-- Jquery CountTo Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/jquery-countto/jquery.countTo.js"></script>

    <!-- Morris Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/raphael/raphael.min.js"></script>
   <script src="${pageContext.request.contextPath}/vendor/morrisjs/morris.js"></script>

    <!-- ChartJs -->
    <script src="${pageContext.request.contextPath}/vendor/chartjs/Chart.bundle.js"></script>
  <!--  <script src="${pageContext.request.contextPath}/js/chartjs.js"></script> -->

    <!-- Flot Charts Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/flot-charts/jquery.flot.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/flot-charts/jquery.flot.resize.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/flot-charts/jquery.flot.pie.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/flot-charts/jquery.flot.categories.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/flot-charts/jquery.flot.time.js"></script>

    <!-- Sparkline Chart Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/jquery-sparkline/jquery.sparkline.js"></script>
    
    <!-- Jquery DataTable Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/jquery-datatable/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery-datatable/extensions/export/dataTables.buttons.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/pages/tables/jquery-datatable.js"></script>
    
    <!-- SweetAlert Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/sweetalert/sweetalert.min.js"></script>

    <!-- Custom Js -->
    <script src="${pageContext.request.contextPath}/js/admin.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/pages/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/pages/ui/modals.js"></script>
    <script src="${pageContext.request.contextPath}/js/pages/ui/dialogs.js"></script>
    
    <!-- Bootstrap Notify Plugin Js -->
    <script src="${pageContext.request.contextPath}/vendor/bootstrap-notify/bootstrap-notify.js"></script>

    <!-- Demo Js -->
    <script src="${pageContext.request.contextPath}/js/demo.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jstree/3.3.5/jstree.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/momentjs/moment.js"></script>
     
	<script src="${pageContext.request.contextPath}/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	
	<!--END:::********************** Project JS Library *****************************-->
    
	<style type="text/css">
	.info-box {
	cursor:pointer !important;
	}
		body {
			font-family: 'Tangerine', serif;
			/* text-shadow: 2px 2px 2px #aaa; */
		}
	
		.cont-height {
			height: 60px;
			position: fixed;
		}

		 section.content {
			margin: 65px 0 0 270px;
			-moz-transition: 0.5s;
			-o-transition: 0.5s;
			-webkit-transition: 0.5s;
			transition: 0.5s;
			/* height: 590px;
			overflow: auto;
			overflow-y: auto; */
		} 
		
		.navbar {
			background-image: url("${pageContext.request.contextPath}/image/header-top1.jpg");
		}
		
		.search {
			margin-top: 12px;
		}
		
		.uus {
			font-style: italic;
		   	font-weight: bold;
		   	font-size: larger;
		   	text-decoration: underline;
		}
		
		.vl {
			border-left: 6px solid white;
			height: 80px;
			position: absolute;
			left: 85%;
			margin-left: 10px;
			top: 0;
		}
		
		.vlb {
			border-left: 6px solid white;
			height: 80px;
			position: absolute;
			left: 85%;
			margin-left: 20px;
			top: 0;
		}
		
		.pcl {
			color: cadetblue;
			font-style: italic;
			font-family: a;
		}
		
		#transaction_date, #date1, #date2 {
			background-color: mintcream;
			border: 1px solid #c8c7cc;
			border-radius: 5px !important;
		}

		.mod-cl {
			color: transparent;
			opacity: 1;
		}
		
		.uus {
			text-decoration: underline;
			font-style: italic;
			font-size: larger;
		}
		
		#date {
			color: white; 
			font-size: 13px; 
			font-style: italic; 
			font-weight: bold;
		}
		
		#clock{
			background: white; 
			font-size: 13px; 
			font-style: italic; 
			font-weight: bold;
			margin-top: -10px;
		}
			.modal-body{
			    overflow: hidden;
    			height: 30;
    			
			}
			.modal-open .modal{
			overflow-x: hidden;
 			overflow-y: hidden; 
			}
	</style>
	
</head>

<body class="theme-red">
    <!-- Page Loader -->
    <div class="page-loader-wrapper">
        <div class="loader">
            <div class="preloader">
                <div class="spinner-layer pl-red">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
            <p>Please wait...</p>
        </div>
    </div>
    <!-- #END# Page Loader -->
    <!-- Overlay For Sidebars -->
    <div class="overlay"></div>
    <!-- #END# Overlay For Sidebars -->
    <!-- Search Bar -->
    <div class="search-bar">
        <div class="search-icon">
            <i class="material-icons">search</i>
        </div>
        <input type="text" placeholder="START TYPING...">
        <div class="close-search">
            <i class="material-icons">close</i>
        </div>
    </div>
    <!-- #END# Search Bar -->
    <!-- Top Bar -->
    <nav class="navbar cont-height">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                <a href="javascript:void(0);" class="bars"></a>
                <a class="navbar-brand" style="font-size: 20px;" href="${pageContext.request.contextPath}/index">
                	<span style="text-shadow: 2px 2px 2px #aaa;"><i><b>&nbsp;&nbsp;&nbsp;&nbsp;</b><b><%-- ${user.companyName} --%>Pharmacy Management System</b></i></span>
                </a>
            </div>
            <div class="collapse navbar-collapse m-t-10" id="navbar-collapse">
            
            	<div class="nav navbar-nav navbar-right">
            		
	            	<p id="date"></p>
	            	
					<div class="align-center" id="clock">
					  <span class="unit" id="hours"></span>&nbsp;:
					  <span class="unit" id="minutes"></span>&nbsp;:
					  <span class="unit" id="seconds"></span>&nbsp;
					  <span class="unit" id="ampm"></span>
					</div>
            	</div>
            </div>
        </div>
    </nav>
    <!-- #Top Bar -->
    <section>
        <!-- Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
            <!-- User Info -->
            <div class="user-info">
                <div class="image">
                    <img src="${pageContext.request.contextPath}/image/${user.userName }.jpg" width="48" height="48" alt="No user image" 
                    onerror="this.src='${pageContext.request.contextPath}/image/default.jpg';"/>
                </div>
                <div class="info-container">
                    <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    	<span><b>${user.fullName}</b></span>
                    </div>
                    <div class="email"><span>${user.email}</span></div>
                    <div class="btn-group user-helper-dropdown">
                        <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                        <input type="hidden" id="empId" name="empId" value="${user.employeeId}"/>
                        <ul class="dropdown-menu pull-right">
                            <li><a href="${pageContext.request.contextPath}/user/profile" data-ajax="true"><i class="material-icons">person</i>Profile</a></li>
                            <!-- <li><a href="javascript:void(0);"><i class="material-icons">person</i>Profile</a></li> -->
                            <li role="separator" class="divider"></li>
                           
                            <li><a href="javascript:void(0);"><i class="material-icons">shopping_cart</i>Sales</a></li>
                            <li><a href="javascript:void(0);"><i class="material-icons">favorite</i>Likes</a></li>
                            <li role="separator" class="divider"></li>
                            <li>
                            <form action="${pageContext.request.contextPath}/logout" method="get">
								<button class="btn btn-md btn-danger btn-block" name="logout"
									type="Submit"><i class="material-icons">input</i>&nbsp; Logout</button>
							</form>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- #User Info -->
            <!-- Menu -->
            <div class="menu" style="text-shadow: 2px 2px 2px #aaa; font-family: 'Tangerine', serif;">
                <ul class="list">
                    <c:choose>
	               		<c:when test="${count != 0}">
		               		<li class="header">
			                    <a href="${pageContext.request.contextPath}/index">
									<i class="material-icons">home</i>
									<span>DASHBOARD</span> 
								</a>
		                    </li>
		                    <c:set var="prevPMenu" value=""/>
							<c:set var="prevSMenu" value=""/>
							<c:set var="currPMenu" value=""/>
							<c:set var="currSMenu" value=""/>
							<c:set var="nextPMenu" value=""/>
							<c:set var="nextSMenu" value=""/>
							
							<c:forEach var="i" begin="0" end="${count-1 }">
								<c:set var="currPMenu" value="${menuList[i].suiteCode}"/>
								<c:set var="currSMenu" value="${currPMenu}${menuList[i].moduleCode}"/>
								
								<c:if test="${!currPMenu.equals(prevPMenu)}">
									<li>
									<a href="javascript:void(0);" class="menu-toggle">
										<i class="material-icons">${menuList[i].menuIcon}</i>
										<span><c:out value="${menuList[i].suiteName}"/></span> 
									</a>
									<ul class="ml-menu anchor">
								</c:if>
								
								<c:if test="${!currSMenu.equals(prevSMenu)}">
									<li>
									<a href="javascript:void(0);" class="menu-toggle">  
										<span><c:out value="${menuList[i].moduleName}"/></span>
									</a>
									<ul class="ml-menu">
								</c:if>
		
								<li>
									<a href="${pageContext.request.contextPath}/${menuList[i].menuUrl}" data-ajax="true">
										<span><i class="material-icons"></i><c:out value="${menuList[i].privilegeName}"/></span>
									</a>
								</li>
								
								<c:choose>
									<c:when test="${ i<  count -1}">
										<c:set var="nextPMenu" value="${menuList[i+1].suiteCode}"/>
										<c:set var="nextSMenu" value="${nextPMenu}${menuList[i+1].moduleCode}"/>
									</c:when>
									<c:otherwise>
										<c:set var="nextPMenu" value=""/>
										<c:set var="nextSMenu" value=""/>
									</c:otherwise>
								</c:choose>
								
								<c:if test="${!currSMenu.equals(nextSMenu)}">
									</ul></li><!-- END SUB CAT -->
								</c:if>
								
								<c:if test="${!currPMenu.equals(nextPMenu)}">
									</ul></li><!-- END CAT -->
								</c:if>
								
								<c:set var="prevPMenu" value="${currPMenu}"/>
								<c:set var="prevSMenu" value="${currSMenu}"/>
							</c:forEach>
	               		</c:when>
	               		<c:otherwise>
					        <li class="header">
			                    <a id="${pageContext.request.contextPath}/dashboard/home" onClick="viewPage(this)">
									<i class="material-icons">home</i>
									<span>DASHBOARD</span> 
								</a>
		                    </li>
					    </c:otherwise>
	               	</c:choose>
                </ul>
            </div>
            <!-- #Menu -->
            <!-- Footer -->
            <div class="legal">
                <div class="copyright">
                    &reg; &copy; <a href="javascript:void(0);">ATI :: LIMS</a>
                </div>
            </div>
            <!-- #Footer -->
        </aside>
        <!-- #END# Left Sidebar -->
        <!-- Right Sidebar -->
        
        <!-- #END# Right Sidebar -->
    </section>
	<section class="content">
    	<div id="view_page">
    		
    		<div class="container-fluid">
				<div class="block-header">
					<h2 style="text-shadow: 2px 2px 2px #aaa;">DASHBOARD</h2>
				</div>
				
				<!-- Widgets -->
				 <div class="row clearfix">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="info-box bg-indigo hover-expand-effect">
							<div class="icon">
								<i class="material-icons">assignment</i>
							</div>
							<div class="content">
								<div class="text">PENDING CSS REQUEST</div>
								<div class="number count-to"  data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="info-box bg-blue hover-expand-effect">
							<div class="icon">
								<i class="material-icons">assignment</i>
							</div>
							<div class="content">
								<div class="text">PENDING SAMPLE</div>
								<div class="number count-to"  data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="info-box bg-light-blue hover-expand-effect">
							<div class="icon">
								<i class="material-icons">assignment</i>
							</div>
							<div class="content">
								<div class="text"> PENDING RECEIVE </div>
								<div class="number count-to"  data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
						<div class="info-box bg-cyan hover-expand-effect">
							<div class="icon">
								<i class="material-icons">assignment</i>
							</div>
							<div class="content">
								<div class="text">PENDING DISTRIBUTION</div>
								<div class="number count-to" id="serPenSize" data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
						<div class="info-box bg-teal hover-expand-effect">
							<div class="icon">
								<i class="material-icons">assignment</i>
							</div>
							<div class="content">
								<div class="text">PENDING RESULT</div>
								<div class="number count-to"  id="rpExpSize" data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
						<div class="info-box  bg-lime hover-expand-effect">
							<div class="icon">
							<i class="material-icons">assignment</i>								
							</div>
							<div class="content">
								<div class="text">PENDING VERIFIED</div>
								<div class="number count-to"  id="insExpSize" data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
						<div class="info-box bg-green hover-expand-effect">
							<div class="icon">
							<i class="material-icons">assignment</i>
							</div>
							<div class="content">
								<div class="text">TM PENDING</div>
								<div class="number count-to"  id="licExpSize" data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
						<div class="info-box bg-light-green hover-expand-effect">
							<div class="icon">
							<i class="material-icons">assignment</i>								
							</div>
							<div class="content">
								<div class="text">LM PENDING</div>
								<div class="number count-to"  id="fitExpSize" data-to="0" data-speed="1000" data-fresh-interval="20"></div>
							</div>
						</div>
					</div>
					
					
					
				</div>
				<%-- <div class="row clearfix">
                <!-- Line Chart -->
                
                <!-- #END# Line Chart -->
                <!-- Bar Chart -->
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2 class="font-bold col-teal">NUMBER OF TEST :: MONTH WISE (CURRENT YEAR)</h2>
                            
                        </div>
                        <div class="body">
                            <canvas id="bar_chart" height="218" width="437" style="display: block; width: 437px; height: 218px;"></canvas>
                        </div>
                    </div>
                </div>
                <!-- #END# Bar Chart -->
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="header">
                            <h2 class="font-bolder col-orange">NUMBER OF TEST :: MATERIAL TYPE WISE(CURRENT YEAR)</h2>
                            
                        </div>
                        <div class="body">
                            <canvas id="pie_chart" height="218" width="437" style="display: block; width: 437px; height: 218px;"></canvas>
                        </div>
                    </div>
                </div>
            </div> --%>
 		
 		
    	</div>
    	
    </section>
    

	<script>
	
	
	function getRandomRgb() {
		  var num = Math.round(0xffffff * Math.random());
		  var r = num >> 16;
		  var g = num >> 8 & 255;
		  var b = num & 255;
		  return 'rgb(' + r + ', ' + g + ', ' + b + ')';
		}
		$(function(){
			$("a[data-ajax=true]:not(.linked), button[data-ajax=true]:not(.linked)").on('click', function (event) {
				event.preventDefault();
				if ($(this)) {
					viewPage($(this));
				}
				else{
					viewPage($(this));
				}
	
				$('.search-modal').modal("hide");
				return false;
	
			});
		}).addClass("linked");
		
		function viewPage(el){
			var link = $(el).attr('href');
			$('a').removeClass('check-mark');
			$('a span i').removeClass('triangle');
			$.ajax({
				async : false,
				url : link ,
				success : function(result) {
					$("#view_page").html(result);
					$(el).closest('li').find('a').addClass('check-mark');
					$(el).closest('li').find('a span i').addClass('triangle');
				},
				error: function(){
					console.log("this page has some error");
				}
			});
		}
		
		
		 /*var chartData = ${chartInfo};
		var monthName = [];
		var totalTest = [];
		
		var typeChartData = ${pieChartInfo};
		var typeName = [];
		var totalTypeTest = [];
		var pieChartColor = [];*/
		$(function () {
		   /* for(var i=0; i<chartData.length; i++){
		    	monthName.push(chartData[i].monthName);
		    	totalTest.push(chartData[i].noOfTest);
		    	
		    }
		    
		     for(var i=0; i<typeChartData.length; i++){
		    	 typeName.push(typeChartData[i].typeName);
		    	 totalTypeTest.push(typeChartData[i].noOfTest);
		    	 pieChartColor.push(getRandomRgb());
		    	
		    }
		     
		    for(var i=0; i<recData.length; i++){
		    	receiveGroup.push(recData[i].accountParentName);
		    	receiveTotal.push(recData[i].drAmount);
		    	receiveColor.push(getRandomRgb());
		    }
		     */
		    clockJs();
		    //chartStart();
		});
		
		function chartStart(){
			new Chart(document.getElementById("bar_chart").getContext("2d"), getChartJs('bar'));
		    new Chart(document.getElementById("pie_chart").getContext("2d"), getChartJs('pie'));
		}

		function getChartJs(type) {
		    var config = null;

		    if (type === 'bar') {
		        config = {
		            type: 'bar',
		            data: {
		                labels: monthName,
		                datasets: [{
		                    label: "Total",
		                    data: totalTest,
		                    backgroundColor: 'teal'
		                }]
		            },
		            options: {
		                responsive: true,
		                legend: false
		            }
		        }
		    }else if (type === 'pie') {
		        config = {
		            type: 'pie',
		            data: {
		                datasets: [{
		                    data: totalTypeTest,
		                    backgroundColor: pieChartColor,
		                }],
		                labels: typeName
		            },options: {
		            	responsive: true,
		        legend: {
		            display: true,
		            labels: {
		                fontColor: 'rgb(0, 0, 0)'
		            }
		        }
		    }
		        }
		    } 
		    
		    return config;
		} 
		function clockJs(){
			var $dOut = $('#date'),
			    $hOut = $('#hours'),
			    $mOut = $('#minutes'),
			    $sOut = $('#seconds'),
			    $ampmOut = $('#ampm');
			
			var months = [
			  'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'
			];
		
			var days = [
			  'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'
			];
			
			function update(){
			  var date = new Date();
			  
			  var ampm = date.getHours() < 12
			             ? 'AM'
			             : 'PM';
			  
			  var hours = date.getHours() == 0
			              ? 12
			              : date.getHours() > 12
			                ? date.getHours() - 12
			                : date.getHours();
			  
			  var minutes = date.getMinutes() < 10 
			                ? '0' + date.getMinutes() 
			                : date.getMinutes();
			  
			  var seconds = date.getSeconds() < 10 
			                ? '0' + date.getSeconds() 
			                : date.getSeconds();
			  
			  var dayOfWeek = days[date.getDay()];
			  var month = months[date.getMonth()];
			  var day = date.getDate();
			  var year = date.getFullYear();
			  
			  var dateString = dayOfWeek + ', ' + month + ' ' + day + ', ' + year;
			  
			  $dOut.text(dateString);
			  $hOut.text(hours);
			  $mOut.text(minutes);
			  $sOut.text(seconds);
			  $ampmOut.text(ampm);
			} 

			update();
			window.setInterval(update, 1000);
		}
		
	</script>

</body>

</html>
