<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Participation</title>
</head>
<body>
	<h1>Activities participation</h1>
	<form action="/private/members/participation" method="get" name="changeYear">
		<p>
			Select year:
			<select name="year" onchange="document.forms.changeYear.submit();">
				<option value="2020">2020</option>
				<option value="2021">2021</option>
			</select>
		</p>
	</form>
	<c:if test="${activities != null}">
		<table>
			<tr>
				<th>Title</th>
				<th>Activity date</th>
				<th>Points</th>
			</tr>
			<c:forEach items="${activities}" var="currentActivity">
				<tr>
					<td>${currentActivity.title}</td>
					<td>
						<fmt:formatDate value="${currentActivity.startDate}" pattern="dd/MM/yyyy HH:mm"/>
						
					</td>
					<td>${currentActivity.points}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<p>
		<a href="/private/members/">&lt; Back to member's home</a>
	</p>
</body>
</html>