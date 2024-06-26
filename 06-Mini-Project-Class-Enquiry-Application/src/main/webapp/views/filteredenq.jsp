<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title></title>
</head>
<body>
		<table class="table table-striped table-bordered text-center">
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
</body>
</html>