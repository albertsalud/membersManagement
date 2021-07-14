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
			<h1>Fes-te soci/a!</h1>
			<p>Vols fer-te soci/a, i assabentar-te de manera directa de les nostres activitats? Omple el formulari, i ens posarem en contacte amb tu!</p>
			
			<c:if test="${message != null}">
				<p class="error">${message}</p>
			</c:if>
			
			<form:form method="post" modelAttribute="membersFormDTO" action="${context}/save" id="form-contacto">
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
				<p>
					<span>Paraula de pas*: </span>
					<form:password path="password"/>
					<form:errors path="password" cssClass="error" />
				</p>
				<p>
					<span>Repeteix*: </span>
					<form:password path="repeatPassword"/>
					<form:errors path="repeatPassword" cssClass="error" />
				</p>
				<input type="submit" value="Registrar-me" class="boton"/>
			</form:form>
			<span class="note" id="form-note">
				* Dades obligatòries.
			</span>
			
			<p>Fer-te soci/a de l'<strong>Associació</strong> t'aportarà els següents drets:</p>
			<ul>
				<li>Accès a activitats exclussives per a socis.</li>
				<li>Preferència a l'hora d'accedir a activitats obertes, en cas de sobreaforament.</li>
				<!--li>Descomptes per la compra de jocs de taula a <a href="http://www.zigguratjocs.com" target="_blank">Ziggurat Jocs</a>.-->
				<li>Informació directa a la teva bústia de correu electrònic d'activitats i esdeveniments.</li>
				<li>Participació al sorteig anual que es realitza al mes de desembre.</li>
			</ul>
			<p>I més coses per les quals estem treballant en aquests moments...</p>
			<p>Si vols fer-nos arribar qualsevol informació, suggerència, o comentari, pots escriure'ns directament a <a href="mailto:daudecinc@gmail.com">daudecinc@gmail.com</a>.</p>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>