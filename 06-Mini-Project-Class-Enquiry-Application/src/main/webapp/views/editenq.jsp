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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js" />
<meta charset="ISO-8859-1">
<title>Edit Enquiry</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary justify-content-between">
		<img src="images/logo.png" width="200px" height="80px" alt="logo" />
		<form class="form-inline">
	   		<a href="dashboard" class="btn btn-light" >Dashboard</a>&nbsp;&nbsp;&nbsp;
			<a href="viewenquiry" class="btn btn-dark" >View Enquiry</a>&nbsp;&nbsp;&nbsp;
			<a href="logoutuser" class="btn btn-danger" >Logout</a>
		</form>
	</nav>
	
	<div class="container">
		<form:form method="POST" action="updateenq" modelAttribute="enquiry">
				<div class="row">
				<div class="col-md-6">
					<h1 class="text-center bg-dark text-white">Edit Enquiry Page</h1>
						<form:hidden path="enqId" />
						<div class="form-group mb-4">
						    <form:input type="text" class="form-control" path="studName" aria-describedby="name" placeholder="Enter Student Name"></form:input>
						</div>
						<div class="form-group mb-4">
						    <form:input type="number" class="form-control" path="studPhno" aria-describedby="emailHelp" placeholder="Enter Student Phone No"></form:input>
						</div>
					  	<div class="form-group mb-4">
						    <form:select class="form-control" path="classMode">
						    	<form:option value="">--select--</form:option>
						    	<form:option value="online">Online</form:option>
						    	<form:option value="offline">Offline</form:option>
						    </form:select>
					  	</div>
					  	<div class="form-group mb-4">
						    <form:select class="form-control" path="courseName">
				   				<form:option value="">--select--</form:option>	
				    			<form:options items="${courses}"></form:options>
						    </form:select>
					  	</div>
					  	<div class="form-group mb-4">
						    <form:select class="form-control" path="enqStatus">
						    	<form:option value="">--select--</form:option>
				    			<form:options items="${status}"></form:options>
						    </form:select>
					  	</div>
					  	<div class="form-group mb-4 align-items-center justify-content-center d-flex">
					  		<button type="submit" class="btn btn-primary" value="Submit">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-danger" href="dashboard">Cancel</a>
					  	</div>
					</div>
					<div class="col-md-6">
						<img src="images/lady.png" class="w-100" alt="Lady Image"></img>
					</div>
				</div>
		</form:form>
	</div>
</body>
</html>