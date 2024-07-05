<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<!-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
<head>
<meta charset="ISO-8859-1">
<title>View Blog Page</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary justify-content-between">
		<img src="images/logo.jpg" width="200px" height="80px" alt="logo" />
		<form class="form-inline">
	   		<a href="loginpage" class="btn btn-light" >Login</a>&nbsp;&nbsp;&nbsp;
			<a href="signup" class="btn btn-dark" >Sign Up</a>
		</form>
	</nav>
	<div class="container">
		<div class="row"><h1 style="color:blue">${blogdto.blogTitle}</h1></div><hr>
		<div class="row"><h4>${blogdto.blogShortDesc}</h4></div><hr>
		<div class="row"><p>${blogdto.blogContent}</p></div><hr>
		<h1>Comments :</h1><hr>
		<div class="row" id="newcomments"></div>
		<div class="row" id="commentsdiv">
			<c:forEach items="${comments}" var="comment">
				<div>
					<h1>${comment.name}</h1>
					<h4>${comment.crtnTime}</h4>
					<p style="color:red">${comment.comment}</p>
				</div><hr>
			</c:forEach>
		</div><hr>
		<div>
			<h1 class="text-center">Add Comment</h1>
			<form:form method="POST" action="submitcomments" modelAttribute="commentform">
				<div class="form-group">
				    <label for="username">Enter User Name</label>
				    <form:input type="text" class="form-control" id="username" path="name" placeholder="Enter Username"></form:input>
				  </div>
				  <div class="form-group">
				    <label for="useremail">Enter Email</label>
				    <form:input type="text" class="form-control" id="useremail" path="email" placeholder="Enter Email"></form:input>
				  </div>
	  			  <div class="form-group">
				    <label for="comment">Enter Comment</label>
				    <form:input type="text" class="form-control" id="comment" path="comment" placeholder="Enter Comment"></form:input>
				  </div>
				  <div class="jusify-content-center text-center">
					  	<a id="commentsubmit" href="#" class="btn btn-primary">Submit</a>&nbsp;&nbsp;&nbsp;
			    		<a href="/vkyit/" class="btn btn-danger">Back</a>
				  </div>
				  <br>
			</form:form>
		</div>
	</div>
</body>
<script>

	$("#commentsubmit").on("click",function()
	{
		var comment={};
		comment["blogid"]=${blogdto.blogId};
		comment["name"]=$("#username").val();
		comment["email"]=$("#useremail").val();
		comment["comment"]=$("#comment").val();
		$.ajax({
			type : 'POST',
			url : 'submitcomment',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(comment),
			success : function(data)
			{
				if(data!=null)
				{
					$("#commentsdiv").hide();
					$("#newcomments").html(data);
					$("#username").val("");
					$("#useremail").val("");
					$("#comment").val("");
				}
			},error : function(data)
			{
				alert("Error occured");
			}
		})		
	})


</script>
</html>