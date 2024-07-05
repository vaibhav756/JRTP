<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Comments Page</title>
</head>
<body>
<c:forEach items="${comments}" var="comment">
	<div>
				<h1>${comment.name}</h1>
				<h3>${comment.crtnTime}</h3>
				<p style="color:red">${comment.comment}</p>
	</div><hr>
</c:forEach>
</body>
</html>