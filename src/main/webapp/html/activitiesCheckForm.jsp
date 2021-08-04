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
		<div id="content" class="no-news">
			<h1>Formulari de participació</h1>
			<p>Pregunta a la persona encarregada de l'activitat el codi per tal de poder-te registrar a la mateixa.</p>
			<c:choose>
				<c:when test="${activities.isEmpty()}">
					<p>Ho sentim, ara mateix no hi ha activitats.</p>
				</c:when>
				<c:otherwise>
					<c:if test="${errorMessage != null }">
						<p class="error">${errorMessage}</p>
					</c:if>
					<form:form method="post" modelAttribute="activitiesCheckFormDTO" action="${context}/activities/check">
						<p>
							<span>Activitat: </span>
							<form:select path="activity" items="${activities}" itemValue="id" itemLabel="title" />
							<form:errors path="activity" cssClass="error"/>
						</p>
						<p>
							<span>Codi: </span>
							<form:input path="code"/>
							<form:errors path="code" cssClass="error"/>
						</p>
						<input type="submit" value="Estic aqui!" class="boton"/>
					</form:form>
				</c:otherwise>
			</c:choose>
			<p>
				<a href="${context}/private/home">&lt; Torna a la pàgina principal</a>
			</p>
		</div>
		<div id="tools">
			<c:import url="${applicationScope.webURL}/tools.html" />
		</div>
	</div>
</body>
</html>