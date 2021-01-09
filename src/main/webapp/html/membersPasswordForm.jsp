<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="http://daudecinc.tk/css/dd5.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
	<h1>Members password form</h1>
	<c:if test="${message != null}">
		<p class="error">${message}</p>
	</c:if>
	<form:form method="post" modelAttribute="changeMembersPasswordDTO" action="${context}/private/changePassword">
		<form:hidden path="id"/>
		<table>
			<tr>
				<td>Password:</td>
				<td>
					<form:password path="password"/>
					<form:errors path="password" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td>
					<form:password path="repeatPassword"/>
					<form:errors path="repeatPassword" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Save" />
				</td>
			</tr>
		</table>	
	</form:form>
	<p>
		<a href="${context}/private/home">&lt; Back to member's home</a>
	</p>
</body>
</html>