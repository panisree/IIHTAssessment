<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@page import="com.iiht.evaluation.coronokit.model.CoronaKit"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%
		// fetch the shared data
		List<ProductMaster> productdetails =  (List<ProductMaster>) request.getAttribute("productdetails");
		String person =  (String) request.getAttribute("person");
		String[] persondetails=person.split(",");
		
	%>
<title>Corona Kit-All Products<%=persondetails[0]%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<form action="user?action=showkit" method="post">
	
	<h2>Welcome <%=persondetails[0]%></h2>
	
	<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Cost</th>
			<th>Quantity</th>
		</thead>
		<tbody>
			
			<% for(ProductMaster productdetail : productdetails) { int i=0; %>
			<tr>
				<td><input type="hidden" name="pname" value="<%=productdetail.getProductName()%>"><%=productdetail.getProductName()%></td>
				
				<td><input type="hidden" name="pcost" value=<%=productdetail.getCost()%>><%=productdetail.getCost()%></td>
				<td><input type="hidden" name="idvalue" value=<%=productdetail.getId()%>><input type="text" name="Qty" style="width:100%;"></td>	
			</tr>
			<% } %>
		</tbody>
	</table>
	<input type="hidden" name="person" value=<%=person%>>
	<div align=right><input type="submit" value="Show Kit"><div></div>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>