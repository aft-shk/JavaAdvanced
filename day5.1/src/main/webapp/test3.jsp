<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test3</title>
</head>
<body>
<h5> Instance variable - <%= mesg1 %></h5>
<h5> Method  local variable - <%= mesg2 %></h5>
<h5> Page Scoped variable - <%= pageContext.getAttribute("nm1")%></h5>
<h5> Page Scoped variable - ${pageScope.nm1}</h5>
</body>
</html>