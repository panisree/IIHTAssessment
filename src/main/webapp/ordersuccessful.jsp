<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Corona Kit-Order Successful</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<body>
<%Integer coronaKitID=(Integer) request.getAttribute("coronaKitID"); %>
<h2 align="left" color=blue>Your order is successful</h2>
<h2 align="left" color=blue>Order Confirmation ID: <%=coronaKitID%> </h2>
<br>
<span></span>
</br>
<br>
<span></span>
</br>
<jsp:include page="footer.jsp"/>
</body>
</html>