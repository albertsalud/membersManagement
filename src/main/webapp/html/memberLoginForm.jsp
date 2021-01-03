<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member login form</title>
</head>
<body>
	<h1>Member login form</h1>
	<c:if test="${errorMessage != null}">
		<p class="error">${errorMessage}</p>
	</c:if>
	<form:form method="post" modelAttribute="memberLoginFormDTO" action="/members/login">
		<table>
			<tr>
				<td>E-mail:</td>
				<td>
					<form:input path="email"/>
					<form:errors path="email"/>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<form:password path="password"/>
					<form:errors path="password"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Log in" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>