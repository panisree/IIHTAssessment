<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<hr/>

<form action="admin?action=insertproduct" method="post">
	<table border="0" width="100%">
		
		<tbody>
			
			
			<tr>
				<td>Product Name</td>
				<td><input type="text" id="pname" name="pname"></td>
			</tr>	
			<tr>
				<td>Product Cost</td>
				<td><input type="text" id="pcost" name="pcost"></td>
			</tr>	
			<tr>
				<td>Product Description</td>	
				<td><input type="text" id="pdesc" name="pdesc"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</tbody>
	</table>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>