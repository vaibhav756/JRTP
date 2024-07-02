<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div class="col-md-8 text-center">
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
</body>
</html>