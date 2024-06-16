<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>VKY IT</title>
</head>
<body>
	<div >
	<nav class="navbar navbar-dark bg-primary justify-content-between">
		<img src="images/logo.png" width="200px" height="80px" alt="logo" />
		<form class="form-inline">
	   		<a href="loginpage" class="btn btn-light" >Login</a>&nbsp;&nbsp;&nbsp;
			<a href="signup" class="btn btn-dark" >Sign Up</a>
		</form>
	</nav>
	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img class="d-block w-100" height="400px" src="images/banner_1.jpg" alt="First slide">
	    </div>
	    <div class="carousel-item">
	      <img class="d-block w-100" height="400px" src="images/banner_2.png" alt="Second slide">
	    </div>
	    <div class="carousel-item">
	      <img class="d-block w-100" height="400px" src="images/banner_3.jpg" alt="Third slide">
	    </div>
	  </div>
	  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	</div>
	<h1 class="text-center">Explore Our Courses</h1>
	<div class="row">
		<div class="col-md-4">
			<img src="images/devops.jpeg" width="100%" height="150px" alt="Dev-Ops" />
		</div>
		<div class="col-md-4">
			<img src="images/python.jpeg" width="100%" height="150px" alt="Python" />
		</div>
		<div class="col-md-4">
			<img src="images/springboot.jpeg" width="100%" height="150px"  alt="SpringBoot" />
		</div>
	</div>
	<h6 class="text-center">@CopyRight VKY Institute</h6>
	</div>
</body>
</html>