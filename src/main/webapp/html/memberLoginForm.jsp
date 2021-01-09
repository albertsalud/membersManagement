<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<head>
<meta charset="UTF-8">
<title>Member login form</title>
</head>
<body>
	<h1>Member login form</h1>
	<c:if test="${param.error != null}">
		<p class="error">Invalid credentials</p>
	</c:if>
	<form:form method="post" modelAttribute="memberLoginFormDTO" action="${context}/login">
		<table>
			<tr>
				<td>E-mail:</td>
				<td>
					<form:input path="username"/>
					<form:errors path="username"/>
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
	<p>
		<a href="${context }/new">No member yet? Sign in!</a>
	</p>
</body>
</html>