<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- display product details using EL syntax --%>

<%-- <h5> Product ID- ${param.pid}</h5>
<h5> Product Name- ${param.p_name}</h5>
<h5> Product Price- ${param.price}</h5>
<h5> ${param}</h5> --%>
<%-- create an attrb to store product details and then forward the clnt to test5.jsp--%>


<%
//out.flush();
//create request scoped attribute to store product details

String details = request.getParameter("pid")+" "+
request.getParameter("p_name")+ " "
+request.getParameter("price");

request.setAttribute("product_details", details);
%>
<jsp:forward page="test5.jsp" />

</body>
</html>