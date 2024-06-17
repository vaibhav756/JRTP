<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Application</title>
<script src="/webjars/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<form action="welcomeuser" name="welcomeform" method="GET">
			Enter Name<input id="username" type="text" name="username"></input>
			<!-- Use for form submission -->
			<!-- <input type="submit" value="Submit" /> -->
			<input type="button" onClick="submitajax()" value="Submit" />
			<hr><br>
				<div id="result">${result}</div>
			<hr><br>	
			<select id="country" name="countries">
				<option>USA</option>
				<option>INDIA</option>
				<option>CANADA</option>
				<option>DUBAI</option>
			</select><hr><br>
				<div id="countryresult">${result}</div>
		</form>
		
	</div>
</div>
<script>

	function submitajax()
	{
		var username=$("#username").val();
		$.ajax({
			type :  "GET",
			url : "welcomeuser",
			data : {
				username : username //This is use to send QueryParam.If we want to send object it will go as RequestBody
			},
			success : function(data)
			{
				$("#result").text(data);	
			},
			error : function(data)
			{
				console.log(data);
			}
		})
	}
	
	$("#country").on("change",function()
	{
		var countryname=$("#country").val();
		$.ajax({
			type : "GET",
			url : "welcomecountry",
			data : {
				country : countryname
			},
			success : function(data)
			{
				$("#countryresult").text(data);
			},
			error : function(data)
			{
				alert(data);				
			}
		})
	})

</script>
</body>
</html>