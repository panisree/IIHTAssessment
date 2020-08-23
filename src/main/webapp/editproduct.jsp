<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<hr/>
<br/>
	<%
		// fetch the shared data
		ProductMaster productdetail =  (ProductMaster) request.getAttribute("productdetail");
	%>
	<form action="admin?action=updateproduct&id=<%=productdetail.getId()%>" method="post">
	<table border="0" width="100%">
		
		<tbody>
			
			
			<tr>
				<td>Product Name</td>
				<td><input type="text" id="pname" name="pname" value=<%=productdetail.getProductName()%>></td>
			</tr>	
			<tr>
				<td>Product Cost</td>
				<td><input type="text" id="pcost" name="pcost" value=<%=productdetail.getCost()%>></td>
			</tr>	
			<tr>
				<td>Product Description</td>	
				<td><input type="text" id="pdesc" name="pdesc" value=<%=productdetail.getProductDescription()%>></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></a></td>
			</tr>
		</tbody>
	</table>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>