<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>,
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activities list</title>
</head>
<body>
	<h1>Activities list</h1>
	<p>
		<a href="/activities/new">&gt; Add a new activity</a>
	</p>
	<c:if test="${activities != null}">
		<table>
			<tr>
				<th>Title</th>
				<th>Activity date</th>
				<th>Points</th>
				<th>Code</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${activities}" var="currentActivity">
				<tr>
					<td>${currentActivity.title}</td>
					<td>
						<fmt:formatDate value="${currentActivity.startDate}" pattern="dd/MM/yyyy HH:mm"/>
						
					</td>
					<td>${currentActivity.points}</td>
					<td>${currentActivity.code}</td>
					<td>
						<a href="/activities/${currentActivity.id}">&gt; Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>