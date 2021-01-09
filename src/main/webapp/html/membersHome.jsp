<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MembersHome</title>
</head>
<body>
	<h1>Members home</h1>
	<p>Welcome to members area.</p>
	<p>In this place you can check your data, your activities participation and the points obtained.</p>
	<p>
		<strong>Your name:</strong> ${member.name}
	</p>
	<p>
		<strong>Your surname:</strong> ${member.surname}
	</p>
	<p>
		<strong>Your e-mail:</strong> ${member.email}
	</p>
	<p>
		<strong>Your phone:</strong> ${member.phone}
	</p>
	<p><a href="${context}/private/data">&gt; Change my data</a></p>
	<p><a href="${context}/private/changePassword">&gt; Change my password</a></p>
	<p><a href="${context}/private/participation">&gt; Check my activities participation</a></p>
	
	<p>You have accumulated ${member.points} points this year.</p>
	
	<p>You can see next planified activities below:</p>
	<table>
		<tr>
			<th>Date</th>
			<th>Activity</th>
			<th>Points</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${activities}" var="currentActivity">
			<tr>
				<td><fmt:formatDate value="${currentActivity.startDate}" pattern="dd/MM/yyyy HH:mm"/></td>
				<td>${currentActivity.title}</td>
				<td>${currentActivity.points}</td>
				<td>
					<c:if test="${currentActivity.startDate.before(now) && currentActivity.endDate.after(now)}">
						<a href="${context}/activities/check">&gt; Check in</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>