<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Activities list</h1>
			<c:if test="${message != null}">
				<p class="error">${message}</p>
			</c:if>
			<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
			<form action="${context}/admin/activities" method="get" name="changeYear">
				<p>
					Year:
					<select name="year" onchange="document.forms.changeYear.submit();">
						<option value="${year}">${year}</option>
						<option ${param.year == year-1 ? 'selected' : ''} value="${year-1}">${year-1}</option>
					</select>
				</p>
			</form>
			<p>
				<a href="${context}/admin/activities/new">&gt; Add a new activity</a>
			</p>
			<c:if test="${activities != null}">
				<table id="data-table" cellspacing="0" cellpadding="5">
					<tr>
						<th>Title</th>
						<th>Location</th>
						<th>Activity date</th>
						<th>Points</th>
						<th>Code</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${activities}" var="currentActivity">
						<tr>
							<td>${currentActivity.title}</td>
							<td>${currentActivity.location}</td>
							<td>
								<fmt:formatDate value="${currentActivity.startDate}" pattern="dd/MM/yyyy HH:mm"/>
								
							</td>
							<td>${currentActivity.points}</td>
							<td>${currentActivity.code}</td>
							<td>
								<a href="${context}/admin/activities/${currentActivity.id}">&gt; Edit</a>
								<a href="${context}/admin/activities/${currentActivity.id}/members">&gt; Manage members</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:import url="logout.jsp" />
		</div>
	</div>
</body>
</html>