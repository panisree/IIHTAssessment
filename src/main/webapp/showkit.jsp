<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%
		// fetch the shared data
		String[] pname=(String[]) request.getAttribute("pname");
		String[] pcost=(String[]) request.getAttribute("pcost");
		String[] pqty=(String[])request.getAttribute("Qty");
		String[] id=(String[])request.getAttribute("idvalue");
		String person =  (String) request.getAttribute("person");
		String[] persondetails=person.split(",");
		float totalCost=0.00F;
	%>
<title>Corona Kit-My Kit<%=persondetails[0]%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="user?action=placeorder" method="post">
	
	
	<table border="1" width="100%">
		<thead>
			<th>Product Name</th>
			<th>Cost</th>
			<th>Quantity</th>
		</thead>
		<tbody>
			
			<% for(int i=0; i<id.length;i++) {  
				if(pqty[i].isEmpty())
					pqty[i]="0";%>
 			<tr>
				<td><input type="hidden" name="pname" value=<%=pname[i]%>><%=pname[i]%></td>
				
				<td><input type="hidden" name="pcost" value=<%=pcost[i]%>><%=pcost[i]%></td>
				
			
				<td><input type="hidden" name="idvalue" value=<%=id[i]%>><input type="hidden" name="Qty" value=<%=pqty[i]%>><%=pqty[i]%></td>
				<%
					totalCost= totalCost + (Float.parseFloat(pcost[i])*Float.parseFloat(pqty[i]));
				%>
 			</tr>
			<% } %>
		</tbody>
	</table>
	<input type="hidden" name="person" value=<%=person%>>
	<div align=right><input type="hidden" name="totalCost" value=<%=totalCost%>><span>Total Cost : <%=totalCost%></span><div></div>
	<div align=right><input type="submit" value="Place Order"><div></div>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>