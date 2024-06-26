<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Enquiries</title>
<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-dark bg-primary justify-content-between">
		<img src="images/logo.png" width="200px" height="80px" alt="logo" />
		<form class="form-inline">
			<a href="dashboard" class="btn btn-light" >View Dashboard</a>&nbsp;&nbsp;&nbsp;
	   		<a href="addenquiry" class="btn btn-dark" >Add Enquiry</a>&nbsp;&nbsp;&nbsp;
			<a href="logoutuser" class="btn btn-danger" >Logout</a>
		</form>
	</nav>
	<div class="container">
		<div class="row text-center">
			<c:if test="${error!=null}">
				<h3 class="text-danger">${error}</h3>
			</c:if>
			<c:if test="${success!=null}">
				<h3 class="text-success">${success}</h3>
			</c:if>
		</div>
		<div class="row text-center">
			<div class="col-md-4"><h6>Course Name</h6>
			<select class="enqfilter" id="course">
				<option value="">--select--</option>
				<c:forEach items="${courses}" var="course">
					<option>${course}</option>
				</c:forEach>
			</select>
			</div>
			<div class="col-md-4"><h6 >Class Mode</h6>
			<select class="enqfilter" id="mode">
				<option value="">--select--</option>
				<option>Online</option>
				<option>Offline</option>
			</select>
			</div>
			<div class="col-md-4"><h6>Enquiry Status</h6>
				<select class="enqfilter" id="status">
					<option value="">--select--</option>
					<c:forEach items="${status}" var="stat">
						<option>${stat}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div id="filtereddiv"></div>
		<table id="enqtable" class="table table-striped table-bordered text-center">
				<thead>
					<tr>
						<th>Enquiry Id</th>
						<th>Student Name</th>
						<th>Student Ph No</th>
						<th>Class Mode</th>
						<th>Course Name</th>
						<th>Edit</th>
					</tr>
				</thead>
				<c:forEach items="${enquiries}" var="item">
				<tbody>
					<tr>
						<td>${item.enqId}</td>
						<td>${item.studName}</td>
						<td>${item.studPhno}</td>
						<td>${item.classMode}</td>
						<td>${item.courseName}</td>
						<td><a class="btn btn-primary" href="editenq?enqid=${item.enqId}">Edit</a></td>
					</tr>
				</tbody>
				</c:forEach>
				<c:if test="${enquiries==null || empty enquiries}">
					<tr>
						<td colspan="12" class="text-center">No Record Found</h1>
					</tr>
				</c:if>
		</table>
	</div>
	
	<script>
	
		$(".enqfilter").on("change",function(){
			
			$.ajax({
				type : "GET",
				url : "getfilteredenq",
				data : {
					name : $("#course").val(),
					mode : $("#mode").val(),
					status : $("#status").val()
				},
				success : function(data)
				{
					$("#enqtable").hide();
					$("#filtereddiv").html(data);
				},
				error : function(data)
				{
					alert("Error");
				}
			})
			
		});
		
	</script>
</body>
</html>