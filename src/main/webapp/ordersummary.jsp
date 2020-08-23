<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
		// fetch the shared data
		
		String[] pname=(String[]) request.getAttribute("pname");
		String[] pcost=(String[]) request.getAttribute("pcost");
		String[] pqty=(String[]) request.getAttribute("Qty");
		String[] id=(String[]) request.getAttribute("idvalue");
		String person = (String) request.getAttribute("person");
		String[] persondetails=person.split(",");
		String totalCost= (String) request.getAttribute("totalCost");
		String address=(String) request.getAttribute("address");
	%>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary<%=persondetails[0]%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h2 align="left" color=blue>Please review your order</h2>
<br></br>
<br>
<span>Your Contact details:</span>
</br>
<form action="user?action=saveorder" method="post">
<table border="0" width="100%">
		
		<tbody>
			<tr>
				<td>Person Name</td>
				<td><%=persondetails[0]%></td>
			</tr>
			<tr>
				<td>Person Contact</td>
				<td><%=persondetails[1]%></td>
			</tr>
			<tr>
				<td>Person Email</td>
				<td><%=persondetails[2]%></td>
			</tr>
			<tr>
				<td>Adress to deliver</td>	
				<td><input type="hidden" name="address" value=<%=address%>><%=address%></td>
			</tr>
			
		</tbody>
	</table>
<br></br>
<h2 align="left" color=blue>Order Summary</h2>
	
	<table border="1" width="100%">
		<tbody>
			
			<% for(int i=0; i<id.length;i++) {  %>
 			<tr>
				<td><input type="hidden" name="pname" value=<%=pname[i]%>><%=pname[i]%></td>
				
				<td><input type="hidden" name="pcost" value=<%=pcost[i]%>><%=pcost[i]%></td>
				
				<td><input type="hidden" name="idvalue" value=<%=id[i]%>><input type="hidden" name="Qty" value=<%=pqty[i]%>><%=pqty[i]%></td>
				
 			</tr>
			<% } %>
		</tbody>
	</table>
	<input type="hidden" name="person" value=<%=person%>>
	<div align=right><input type="hidden" name="totalCost" value=<%=totalCost%>><span>Total Cost : <%=totalCost%></span><div></div>
	<div align=right><input type="submit" value="Confirm Order"><div></div>
	</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>