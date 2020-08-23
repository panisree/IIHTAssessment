<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="user?action=insertuser" method="post">
		<div>
			<div><label for="pname">Enter Name:</label> </div>
			<div><input type="text" id="pname" name="pname"> </div>
		</div>
		<div>
			<div><label for="contact">Enter Mobile Number:</label> </div>
			<div><input type="text" id="pcontact" name="pcontact"> </div>
		</div>
		<div>
			<div><label for="email">Enter Email:</label> </div>
			<div><input type="text" id="email" name="email"> </div>
		</div>
		<div>
			<div><input type="submit" value="Submit"> </div>
		</div>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>