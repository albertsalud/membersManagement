<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Participation list</title>
</head>
<body>
	<h1>Participation list</h1>
	<c:if test="${message != null}">
		<p class="error">${message}</p>
	</c:if>
	<p>
		<a href="${context}/admin/activities">&lt; Return to activities list</a>
	</p>
	<p>Title: ${activity.title}</p>
	<p>Date: <fmt:formatDate value="${activity.startDate}" pattern="dd/MM/yyyy HH:mm"/></p>
	<c:if test="${members != null}">
		<p>Registered members: </p>
		<table>
			<tr>
				<th>Name</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${members}" var="currentMember">
				<tr>
					<td>${currentMember.fullName}</td>
					<td>
						<a href="${context}/admin/activities/${activity.id}/members/${currentMember.id}/remove" onclick="return confirm('Sure?');">&gt; Remove</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<form:form modelAttribute="participationFormDTO" method="post">
		<form:hidden path="activity"/>
		<p>
			Add member:
			<form:select path="member" items="${allMembers}" itemLabel="fullName" />
			<input type="submit" value="Add" />
		</p>
	</form:form>
</body>
</html>