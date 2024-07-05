<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
<title>VKY IT</title>
</head>
<body>
	<div>
	<nav class="navbar navbar-dark bg-primary justify-content-between">
		<img src="images/logo.jpg" width="200px" height="80px" alt="logo" />
		<form class="form-inline">
	   		<a href="loginpage" class="btn btn-light" >Login</a>&nbsp;&nbsp;&nbsp;
			<a href="signup" class="btn btn-dark" >Sign Up</a>
		</form>
	</nav>
	<div class="container">
		<div class="row">
		<c:if test="${error!=null}">
			<div class="text-center">
				<h1>${error}</h1>
			</div>
		</c:if>
		<div class="col-md-8 text-center" id="filterddiv"></div>		
		<div class="col-md-8 text-center" id="blogdiv">
			<c:if test="${blogs!=null}">
				<c:forEach items="${blogs}" var="blog">
					<div class="row">
						<h3><a href="viewblog?blogid=${blog.blogId}">${blog.blogTitle}</a></h3>						
					</div>
					<div class="row">
						<h6>${blog.crtnTime}</h6>						
					</div>
					<div class="row">
						<p>${blog.blogShortDesc}&nbsp;&nbsp;<a href="viewblog?blogid=${blog.blogId}">readmore</a></p>						
					</div>
					<hr>
				</c:forEach>
.			</c:if>
			<c:if test="${blogs==null}">
				<h3>No blogs available</h3>
			</c:if>
		</div>
		<div class="col-md-4">
			<table>
				<thead class="text-center">
					<tr>
						<th><h3>Blog Search</h3></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="form-inline">
						<input class="form-control mr-sm-2" type="search" id="blogsearch" placeholder="Search" aria-label="Search">
    					<button class="btn btn-outline-success my-2 my-sm-0" id="searchbtn" type="submit">Search</button>
    					</td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>
	</div>
	<h6 class="text-center">@CopyRight VKY Institute</h6>
	</div>
</body>
<script>

	$("#searchbtn").on("click",function(){
		
		$.ajax({
			url : "getBlogByName",
			type : "GET",
			data : {
				blogname : $("#blogsearch").val()
			},success : function(data)
			{
				$("#blogdiv").hide();
				$("#filterddiv").html(data);
			},error : function(data)
			{
				alert(data);
			}
		})
		
	});

</script>
</html>