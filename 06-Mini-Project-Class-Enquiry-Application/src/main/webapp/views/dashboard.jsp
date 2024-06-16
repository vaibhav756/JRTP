<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary justify-content-between">
		<img src="images/logo.png" width="200px" height="80px" alt="logo" />
		<form class="form-inline">
	   		<a href="addenquiry" class="btn btn-light" >Add Enquiry</a>&nbsp;&nbsp;&nbsp;
			<a href="viewenquiry" class="btn btn-dark" >View Enquiry</a>
		</form>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-06">
				<h1>Total Enquiry : ${total}</h1>
				<h1>Total Success Enquiry : ${enrolled}</h1>
				<h1>Total Failed Enquiry : ${lost}</h1>
			</div>
			<div class="col-md-06">
				<img alt="ladyImage" src="images/lady.png">
			</div>
		</div>
	</div>
</body>
</html>