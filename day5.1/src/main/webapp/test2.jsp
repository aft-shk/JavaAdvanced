<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!
String mesg1 = "some mesg";// implicityly has private access;
%>
<body>
<%

String mesg2 ="another mesg";//local var
pageContext.setAttribute("nm1", 1000);

%>
<%@ include file = "test3.jsp" %>
</body>
</html>