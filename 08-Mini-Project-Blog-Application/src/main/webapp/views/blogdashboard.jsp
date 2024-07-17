<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
<title>Blog Dashboard Page</title>
<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">VKY IT Blog Application</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <!-- <li class="nav-item active">
        <a class="nav-link" href="#">Blogs <span class="sr-only">(current)</span></a>
      </li> -->
      <li class="nav-item active">
        <a class="nav-link" href="createblog">Create Blog<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="viewcomments">Comments<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active" style="float:right">
        <a class="nav-link" href="logoutuser">Logout<span class="sr-only">(current)</span></a>
      </li>
  </div>
</nav>
<div class="container">
	<c:if test="${success!=null}">
		<h3 class="text-success">${success}</h3>
	</c:if>
	<c:if test="${error!=null}">
		<h3 class="text-danger">${error}</h3>
	</c:if>
	<div class="justify-content-center text-center">
		<h1>Blog Dashboard</h1>
	</div>
	
	<div id="filteredblogdiv"></div>
	<div id="blogdiv">
		<table id="blogtable" class="table table-striped table-bordered text-center">
					<thead>
						<tr>
							<th>Blog Id</th>
							<th>Blog Title</th>
							<th>Short Description</th>
							<th>Created At</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<c:forEach items="${allblogs}" var="blog">
						 <tbody>
							<tr>
								<td>${blog.blogId}</td>
								<td>${blog.blogTitle}</td>
								<td>${blog.blogShortDesc}</td>
								<td>${blog.crtnTime}</td>
								<td><a class="btn btn-primary" onclick="editblog(${blog.blogId})">Edit</a></td>
								<td><a class="btn btn-danger" onclick="deleteblog(${blog.blogId})">Delete</a></td>
							</tr>
						</tbody> 
					</c:forEach>
					 <c:if test="${allblogs==null || empty allblogs}">
						<tr>
							<td colspan="12" class="text-center">No Record Found</h1>
						</tr>
					</c:if>
			</table>
		</div>
</div>
<script>
	
	function deleteblog(blogid)
	{
		$.ajax({
			url : "deleteblog",
			type : "GET",
			data : {
				blogid : blogid
			},success : function(data)
			{
				$("#blogdiv").hide();
				$("#filteredblogdiv").html(data);
			},error : function(data)
			{
				alert("Error occured while deleting blog")				
			}
		})
	}
	
	function editblog(blogid)
	{
		window.location.href="editblog?blogid="+blogid;
	}
	
</script>
</body>
</html>