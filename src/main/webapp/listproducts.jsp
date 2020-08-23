<%@page import="com.iiht.evaluation.coronokit.model.ProductMaster"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<hr/>

	<br/>
	<%
		// fetch the shared data
		List<ProductMaster> productdetails =  (List<ProductMaster>) request.getAttribute("productdetails");
	%>
	<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Cost</th>
			<th>Description</th>
			<th>Edit</th>
			<th>Delete</th>
		</thead>
		<tbody>
			
			<% for(ProductMaster productdetail : productdetails) { int i=0; %>
			<tr>
				<td><a href="pname"><%=productdetail.getProductName()%></a></td>
				<td><a href="pcost"><%=productdetail.getCost()%></a></td>
				<td><a href="pdesc"><%=productdetail.getProductDescription()%></a></td>
				<td><a href="admin?action=editproduct&id=<%=productdetail.getId()%>"><button>Edit</button></a></td>
				<td><a href="admin?action=deleteproduct&id=<%=productdetail.getId()%>"><button>Delete</button></a></td>
			</tr>
			<% } %>
		</tbody>
	</table>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>