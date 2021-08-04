<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Participation list</h1>
			<c:if test="${message != null}">
				<p class="error">${message}</p>
			</c:if>
			<p>
				<a href="${context}/admin/activities">&lt; Return to activities list</a>
			</p>
			<p>
				<strong>Title:</strong> ${activity.title}
			</p>
			<p>
				<strong>Date:</strong> <fmt:formatDate value="${activity.startDate}" pattern="dd/MM/yyyy HH:mm"/>
			</p>
			<c:if test="${members != null}">
				<p><strong>Registered members:</strong></p>
				<table id="data-table" cellspacing="0" cellpadding="5">
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
					<span>Add member:</span>
					<form:select path="member" items="${allMembers}" itemLabel="fullName" />
					<input type="submit" value="Add" class="boton"/>
				</p>
			</form:form>
		</div>
	</div>
</body>
</html>