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
	%>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order<%=persondetails[0]%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<form action="user?action=ordersummary" method="post">
	
	<h2 align="left" color=blue>Please provide your address details</h2>
	
			<% for(int i=0; i<id.length;i++) {  %>
 			<input type="hidden" name="pname" value=<%=pname[i]%>>				
			<input type="hidden" name="pcost" value=<%=pcost[i]%>>				
			<input type="hidden" name="idvalue" value=<%=id[i]%>>
			<input type="hidden" name="Qty" value=<%=pqty[i]%>>
			<% } %>
		
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
				<td>Adress</td>	
				<td><input type="text" id="address" name="address" style="width:100%;"></td>
			</tr>
			
		</tbody>
	</table>
	<input type="hidden" name="person" value=<%=person%>>
	<input type="hidden" name="totalCost" value=<%=totalCost%>>
	<div align=right><input type="submit" value="Review Order"></div>
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>