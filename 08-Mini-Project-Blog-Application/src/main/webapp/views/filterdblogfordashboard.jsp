<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Filtered Blogs For Dashboard</title>
</head>
<body>
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
</body>
</html>