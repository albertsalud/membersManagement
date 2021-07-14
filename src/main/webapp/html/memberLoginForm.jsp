<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
		<h1>Àrea privada</h1>
		<c:if test="${param.error != null}">
			<p class="error">Invalid credentials</p>
		</c:if>
		<form:form method="post" modelAttribute="memberLoginFormDTO" action="${context}/login" id="form-login">
			<p>
				<span>E-mail: </span>
				<form:input path="username"/>
				<form:errors path="username" cssClass="error"/>
			</p>
			<p>
				<span>Paraula de pas: </span>
				<form:password path="password"/>
				<form:errors path="password" cssClass="error" />
			</p>
			<p>
				No recordes la teva paraula de pas? Fes click <a href="${context }/recovery">aquí</a>
			</p>
			<input type="submit" value="Accedir" class="boton"/>
		</form:form>
		<p>
			<a href="${context }/new">Encara no ets soci? Registra't!</a>
		</p>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>