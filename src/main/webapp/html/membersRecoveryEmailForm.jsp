<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
			<h1>Restauració de la paraula de pas</h1>
			<c:if test="${message != null}">
				<p class="error">${message}</p>
			</c:if>
			<p>Introduiu el vostre e-mail per tal de rebre un correu per recuperar l'accès al vostre compte.</p>
			<form:form method="post" modelAttribute="emailRecoveryFormDTO" action="${context}/recovery" id="form-recovery">
				<p>
					<span>E-mail*: </span>
					<form:input path="email"/>
					<form:errors path="email" cssClass="error" />
				</p>
				<input type="submit" value="Recuperar la paraula de pas" class="boton"/>
			</form:form>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>
