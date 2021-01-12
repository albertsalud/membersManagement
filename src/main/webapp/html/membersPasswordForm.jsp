<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>La mave paraula de pas</h1>
			<c:if test="${message != null}">
				<p class="error">${message}</p>
			</c:if>
			<form:form method="post" modelAttribute="changeMembersPasswordDTO" action="${context}/private/changePassword" id="form-changePassword">
				<form:hidden path="email"/>
				<form:hidden path="password"/>
				<p>
					<span>Paraula de pas*: </span>
					<form:password path="newPassword"/>
					<form:errors path="newPassword" cssClass="error" />
				</p>
				<p>
					<span>Repeteix*: </span>
					<form:password path="repeatPassword"/>
					<form:errors path="repeatPassword" cssClass="error" />
				</p>
				<input type="submit" value="Desar" class="boton"/>
			</form:form>
			<p>
				<a href="${context}/private/home">&lt; Tornar sense desar</a>
			</p>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>