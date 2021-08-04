<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h1>Enhorabona</h1>
			<p>Hem rebut la teva sol·licitut d'enregistrament.</p>
			<p>Comprova la teva bústia de correu per tal d'activar el teu usuari.</p>
			<p>Gràcies.</p>
		</div>
		<div id="tools">
			<c:import url="${applicationScope.webURL}/tools.html" />
		</div>
	</div>
</body>
</html>