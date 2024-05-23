<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<jsp:setProperty property="*" name="user"/>
<%--invoke user bean's method for validation n registration  --%>
<h5>Registration status - ${sessionScope.user.validateAndRegister()} </h5>
<body>


</body>
</html>