<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activities check form</title>
</head>
<body>
	<h1>Activities check form</h1>
	<c:choose>
		<c:when test="${activities.isEmpty()}">
			<p>Sorry, there's no activities at this moment.</p>
		</c:when>
		<c:otherwise>
			<c:if test="${errorMessage != null }">
				<p class="error">${errorMessage}</p>
			</c:if>
			<form:form method="post" modelAttribute="activitiesCheckFormDTO" action="${context}/activities/check">
				<table>
					<tr>
						<td>Activity:</td>
						<td>
							<form:select path="activity" items="${activities}" itemValue="id" itemLabel="title" />
						</td>
					</tr>
					<tr>
						<td>Activity code:</td>
						<td>
							<form:input path="code" />
							<form:errors path="code" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="I'm here!" />
						</td>
					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
	<p>
		<a href="${context}/private/home">&lt; Back to member's home</a>
	</p>
</body>
</html>