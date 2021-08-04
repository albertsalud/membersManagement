<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Les meves dades</h1>
			<c:if test="${message != null}">
				<p class="error">${message}</p>
			</c:if>
			<form:form method="post" modelAttribute="membersDataFormDTO" action="${context}/private/save">
				<form:hidden path="id"/>
				<form:hidden path="password"/>
				<p>
					<span>Nom*: </span>
					<form:input path="name"/>
					<form:errors path="name" cssClass="error"/>
				</p>
				<p>
					<span>Cognoms*: </span>
					<form:input path="surname"/>
					<form:errors path="surname" cssClass="error" />
				</p>
				<p>
					<span>Correu electrònic*: </span>
					<form:input path="email"/>
					<form:errors path="email" cssClass="error" />
				</p>
				<p>
					<span>Telèfon: </span>
					<form:input path="phone"/>
					<form:errors path="phone" cssClass="error" />
				</p>
				<input type="submit" value="Desar" class="boton"/>
			</form:form>
			<p>
				<a href="${context}/private/home">&lt; Tornar sense desar</a>
			</p>
		</div>
		<div id="tools">
			<c:import url="${applicationScope.webURL}/tools.html" />
		</div>
	</div>
</body>
</html>