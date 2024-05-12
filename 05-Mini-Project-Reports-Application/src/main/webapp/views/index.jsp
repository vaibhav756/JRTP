<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Report Application</title>
</head>
<body>
	<div class="container">	<h1 class="pb-3 pt-3 text-center">Report Application</h1>
	<form:form action="getcitizendata" modelAttribute="dto" method="POST">
		<table class="text-center">
			<tr class="pb-3 pt-3">
				<td class="h5">Plan Name : </td>
				<td>
					<form:select path="planName">
						<form:option value="" >--select--</form:option>
						<form:options items="${plans}"/>
					</form:select>
				</td>
				<td class="h5">Plan Status : </td>
				<td>
					<form:select path="planStatus">
						<form:option value="" >--select--</form:option>
						<form:options items="${status}"/>
					</form:select>
				</td>
				<td class="h5">Gender : </td>
				<td>
					<form:radiobutton path="gender" value="Male" />Male
					<form:radiobutton path="gender" value="Female" />Female
				</td>
			</tr>
			<tr>
				<td class="h5">Start Date : </td>
				<td>
					<form:input class="form-control" type="date" path="planStartDate" />
				</td>
				<td class="h5">End Date : </td>
				<td>
					<form:input class="form-control" type="date" path="planEndDate" />
				</td>
			</tr>
			<tr>
				<td>
					<a class="btn btn-danger" href="index">Clear</a>
				</td>
				<td>
					<button class="btn btn-primary" type="submit">Search</button>
				</td>
			</tr>
		</table>
	</form:form>
	
	
	<hr/>
	
<%-- 	<c:if test="${citizendtolist==null || empty citizendtolist}">
		<h1 class="text-center">No Record Found</h1>
	</c:if>
 --%>	<%-- <c:if test="${citizendtolist!=null && !empty citizendtolist}"> --%>
		<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Citizen Id</th>
				<th>Citizen Name</th>
				<th>Plan Name</th>
				<th>Gender</th>
				<th>Plan Status</th>
				<th>Plan StartDate</th>
				<th>Plan EndDate</th>
				<th>BenefitAmt</th>
				<th>Denied Reason</th>
				<th>Termination Date</th>
				<th>Termination Reason</th>
			</tr>
		</thead>
		<c:forEach items="${citizendtolist}" var="item">
		<tbody>
			<tr>
				<td>${item.citizenId}</td>
				<td>${item.citizenName}</td>
				<td>${item.planName}</td>
				<td>${item.gender}</td>
				<td>${item.planStatus}</td>
				<td>${item.planStartDate}</td>
				<td>${item.planEndDate}</td>
				<td>${item.benefitAmt}</td>
				<td>${item.denialReason}</td>
				<td>${item.terminationDate}</td>
				<td>${item.terminationReason}</td>
			</tr>
		</tbody>
		</c:forEach>
		<c:if test="${citizendtolist==null || empty citizendtolist}">
			<tr>
				<td colspan="12" class="text-center">No Record Found</h1>
			</tr>
		</c:if>
		</table>
<%-- 	</c:if> --%>
	
	<hr style=""/>

	<h5 class="text-center">Export : <a href="/exportExcel" class="btn btn-primary">EXCEL</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/exportpdf" class="btn btn-secondary">PDF</a></h5>
	
	</div>
</body>
</html>