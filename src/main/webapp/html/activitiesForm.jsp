<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="admin">
			<h1>Activity form</h1>
			<p>
				<a href="${context}/admin/activities">&lt; Return to activities list</a>
			</p>
			<spring:hasBindErrors name="activitiesFormDTO">
				<c:forEach var="error" items="${errors.allErrors}">
					<c:catch var="exception"><c:set var="errorField" value="${error.field}" /></c:catch>
					<c:if test="${not empty exception}">
						<p class="error">Error: <spring:message message="${error}" /></p>
					</c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<form:form action="${context}/admin/activities/save" modelAttribute="activitiesFormDTO" method="post">
				<form:hidden path="id"/>
				<p>
					<span>Title:</span>
					<form:input path="title"/>
					<form:errors path="title" cssClass="error"/>
				</p>
				<p>
					<span>Location:</span>
					<form:input path="location"/>
					<form:errors path="location" cssClass="error"/>
				</p>
				<p>
					<span>Start date:</span>
					<form:input path="startDate" onchange="fillEndDate()"/>
					<form:errors path="startDate" cssClass="error"/>
				</p>
				<p>
					<span>End date:</span>
					<form:input path="endDate"/>
					<form:errors path="endDate" cssClass="error"/>
				</p>
				<p>
					<span>Points:</span>
					<form:input path="points"/>
					<form:errors path="points" cssClass="error"/>
				</p>
				<p>
					<span>Code:</span>
					<form:input path="code"/>
					<form:errors path="code" cssClass="error"/>
				</p>
				<input type="submit" value="Save" class="boton" />
			</form:form>
		</div>
	</div>
</body>
</html>