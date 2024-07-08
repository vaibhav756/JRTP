<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Blog</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">VKY IT Blog Application</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="blogdashboard">Blogs <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="createblog">Create Blog<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="comments">Comments<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active" style="float:right">
        <a class="nav-link" href="logout">Logout<span class="sr-only">(current)</span></a>
      </li>
  </div>
</nav>
<div class="container">
	<div class="row justify-content-center">
		<h1>Edit Blog</h1>
	</div>
	<div class="row">
			<c:if test="${error!=null}">
				<h3 class="text-danger">${error}</h3>
			</c:if>
			<form:form method="POST" id="blogform" action="updateblog" modelAttribute="blog" class="w-100">
				<form:hidden path="blogId" id="blogid" />
				<form:hidden path="blogContent" id="blogcontent" />
				  <div class="form-group">
				    <label for="blogtitle">Enter Blog Title</label>
				    <form:input type="text" class="form-control" id="blogtitle" path="blogTitle" placeholder="Enter Blog Title"></form:input>
				  </div>
				  <div class="form-group">
				    <label for="shortdesc">Short Description</label>
				    <form:input type="text" class="form-control" id="shortdesc" path="blogShortDesc" placeholder="Enter Short Description"></form:input>
				  </div>
	  			  <div class="form-group">
				    <label>Content</label>
					<div id="summernote"></div>
				  </div>
				  <div class="row justify-content-center">
					  <button type="button" onclick="finalsubmit()" class="btn btn-primary">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
		  			  <a class="btn btn-danger" href="blogdashboard">Back</a>
	  			  </div>
			
			</form:form>
	</div>
</div>


</body>
<script>

$(document).ready(function() {
	
	  $('#summernote').summernote({
		  placeholder: 'Enter Blog Content',
	      tabsize: 2,
	      height: 200
	  });
	  
	  $('#summernote').summernote('code', '${blog.blogContent}');
	});
	
	function finalsubmit()
	{
		var content=$('#summernote').summernote('code');
		$("#blogcontent").val(content);
		$("#blogform").submit();
	}
	
</script>
</html>