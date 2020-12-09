<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
    <title>DMS :: Diagnostic Management System</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginForm.css" media="screen"></link>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/has-float-label.css" media="screen"></link>
    <link rel='stylesheet' type="text/css" href='${pageContext.request.contextPath}/css/bootstrap.min.css'>
    <link rel="icon" href="${pageContext.request.contextPath}/image/ati.jpg" type="image/x-icon" >
    <style>
    .bold{
    font-weight: bold;
    color:#red;
    }
     .text-red{
    font-weight: bold;
    color:#FFF;
    }
    .h3,h3 {
    margin-top: 2px;
    font-size: 30px;
    }
    /*bUBLE CSS*/
    
    form {
  padding: 20px 0;
  position: relative;
  z-index: 2;
}
    .bg-bubbles {
  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}
.bg-bubbles li {
  position: fixed;
  list-style: none;
  display: block;
  width: 40px;
  height: 40px;
  background-color: rgba(255, 255, 255, 0.15);
  bottom: -160px;
  border-radius: 150px;
  -webkit-animation: square 50s infinite;
  animation: square 50s infinite;
  -webkit-transition-timing-function: linear;
  transition-timing-function: linear;
}
.bg-bubbles li:nth-child(1) {
  left: 15%;
  animation-delay: 0s;
  animation-duration: 3s;
}
.bg-bubbles li:nth-child(2) {
  left: 20%;
  width: 80px;
  height: 80px;
  animation-delay: 2s;
  animation-duration: 5s;
}
.bg-bubbles li:nth-child(3) {
  left: 25%;
  animation-delay: 4s;
  animation-duration: 7s;
}
.bg-bubbles li:nth-child(4) {
  left: 40%;
  width: 60px;
  height: 60px;
   animation-delay: 8s;
  animation-duration: 32s;
  background-color: rgba(255, 255, 255, 0.25);
}
.bg-bubbles li:nth-child(5) {
  left: 70%;
  animation-delay: 15s;
  animation-duration: 8s;
}
.bg-bubbles li:nth-child(6) {
  left: 80%;
  width: 12px;
  height: 12px;
  animation-delay: 30s;
  animation-duration: 9s;
  background-color: rgba(255, 255, 255, 0.3);
}
.bg-bubbles li:nth-child(7) {
  left: 32%;
  width: 160px;
  height: 160px;
  animation-delay: 7s;
  animation-duration: 5s;
}
.bg-bubbles li:nth-child(8) {
  left: 55%;
  width: 20px;
  height: 20px;
  animation-delay: 11s;
  animation-duration: 4s;
}
.bg-bubbles li:nth-child(9) {
  left: 75%;
  width: 20px;
  height: 20px;
  animation-delay: 2s;
  animation-duration: 3s;
  background-color: rgba(255, 255, 255, 0.3);
}
.bg-bubbles li:nth-child(10) {
  left: 90%;
  width: 160px;
  height: 160px;
  animation-delay: 12s;
  animation-duration: 9s;
}
.bg-bubbles li:nth-child(11) {
  left: 25%;
  width: 60px;
  height: 60px;
  animation-delay: 13s;
  animation-duration: 3s;
}
@-webkit-keyframes square {
  0% {
    transform: translateY(0);
    background-color: rgba(89, 199, 212, 0);
  }
  100% {
    transform: translateY(-100px);
    background-color: #fff;
  }
}
@keyframes square {
  0% {
    transform: translateY(0);
    background-color: #59c7d4;
  }
  100% {
    transform: translateY(-500px);
    background-color: rgba(89, 199, 212, 0);
  }
}
    </style>
</head>

<body>
	<div class="wrapper">
	<div class="container">
	<%-- <div class="bg-info" align="center"><img src="${pageContext.request.contextPath}/image/header-logo.png"></div>--%>
	<h3 class="text-center bold">Diagnostic Management System</h3> 
	    <form action="${pageContext.request.contextPath}/login" method="POST" class="form form-signin">
	    	<div class="logo" align="center"><img src="${pageContext.request.contextPath}/image/user-login.png"></div>
	        <div class="form-group">
		        <span class="has-float-label">
	                <input required="required" class="form-control" name="username" id="username" type="text" placeholder="Enter your user name" autofocus>
	                <label for="username" style="padding: 0px">User Name</label>
	            </span>
	        </div>
	        <div class="form-group">
	        	<span class="has-float-label">
	                <input required="required" class="form-control" name="password" id="password" type="password" placeholder="Enter your password">
	                <label for="password">Password</label>
	            </span>
	        </div>
	        
	        <button class="btn btn-md btn-info btn-block btn-st" type="submit" >Login</button>
	        <c:if test="${param.error == true}">
			    <div id="error">
			    	<span><spring:message code="message.badCredentials"/></span>
			    </div>
			</c:if>
			<c:if test="${param.logSucc == true}">
			    <div id="success">
			    	<span><spring:message code="message.logoutSucc"/></span>
			    </div>
			</c:if>
			
			
			
	
	    </form>
	    <!-- <ul class="bg-bubbles">
		<li></li>
    <li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul> -->
</div>

	</div>
</body>
</html>