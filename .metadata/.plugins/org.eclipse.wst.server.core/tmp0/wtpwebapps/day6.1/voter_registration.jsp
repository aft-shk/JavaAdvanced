<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Voter Registration</title>
</head>
<body >
<form action="register.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Enter Voter's First Name</td>
				<td><input type="text" name="fname" /></td>
			</tr>
			<tr>
				<td>Enter Last Name</td>
				<td><input type="text" name="lname" /></td>
			</tr>
			<tr>
				<td>Enter Voter Email</td>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Select DoB</td>
				<td><input type="date" name="dob" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Voter Registration" /></td>
			</tr>
		</table>
	</form>

</body>
</html>