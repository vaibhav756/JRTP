<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Unlock Account</title>
</head>
<body>
	<form:form action="unlockaccount" method="POST" modelAttribute="unlockform">
	<form:hidden path="email" />
	<div class="container">
	<h1 class="text-center bg-primary text-white">Unlock Account</h1>
		<div class="row">
			<div class="col-md-6">
				<img src="images/logo.jpg" class="w-100" height="150px" alt="logo" />
				<h5>Your Email: ${unlockform.email}</h5>
				<c:if test="${result!=null}">
					<h5 style="color:red">${result}</h5>
				</c:if>
				<c:if test="${result==''}">
					<h5>${result}</h5>
				</c:if>
				<div class="form-group mb-4">
				    <form:input type="text" class="form-control" id="temppwd" path="tempPwd" aria-describedby="name" placeholder="Enter Temporary Password"></form:input>
				</div>
				<div class="form-group mb-4">
				    <form:input type="text" class="form-control" id="newpwd" path="newPwd" aria-describedby="emailHelp" placeholder="Enter New Password"></form:input>
				</div>
			  	<div class="form-group mb-4">
				    <form:input type="text" class="form-control" id="cnfpwd" path="confirmPwd" placeholder="Confirm Password"></form:input>
			  	</div>
			  	<div class="row mb-4">
				  	<button type="submit" class="w-100 btn btn-primary">Unlock</button>
			  	</div>
			  	<div class="row mt-5 d-block">
			  		<h4 class="text-center d-block">Already Unlock?</h4>&nbsp;&nbsp;&nbsp;<a href="loginpage" class="btn btn-outline-danger d-block">Login Here</a>
			  	</div>
			  	
			</div>
			<div class="col-md-6">
				<img src="images/blogimg.jpg" height="70%" width="100%" alt="LoginPage" />
			</div>
		</div>
	</div>
	</form:form>
</body>
</html>