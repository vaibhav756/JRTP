<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" />
</head>
<body>
	<form:form method="POST" action="loginuser" modelAttribute="loginform">
	<div class="container">
	<h1 class="text-center bg-primary text-white">Login Page</h1>
		<div class="row">
			<div class="col-md-6">
				<img src="images/logo.jpg" class="w-100" height="150px" alt="logo" />
				<h5>Please login to your account</h5>
				<c:if test="${error!=null}">
					<h5 style="color:red">${error}</h5>
				</c:if>
				<c:if test="${result!=null}">
					<h5 style="color:red">${result}</h5>
				</c:if>
				<div class="form-group mb-4">
				    <form:input type="email" class="form-control" path="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"></form:input>
				</div>
			  	<div class="form-group mb-4">
				    <form:input type="password" class="form-control" path="pwd" id="exampleInputPassword1" placeholder="Password"></form:input>
			  	</div>
			  	<div class="row mb-4">
				  	<form:button type="submit" class="w-100 btn btn-primary">LOG IN</form:button>
			  	</div>
			  	<div class="row">
				  	<a href="forgotpwd" class="w-100 d-block text-center">Forgot Password?</a>
			  	</div>
			  	<div class="row mt-5 d-block">
			  		<h4 class="text-center d-block">Don't have an account?</h4>&nbsp;&nbsp;&nbsp;<a href="signup" class="btn btn-outline-danger d-block">Create New</a>
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