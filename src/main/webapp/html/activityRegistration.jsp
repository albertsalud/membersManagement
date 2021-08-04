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
			<h1>Enhorabona!</h1>
			<p>T'has registrat correctament a l'activitat <strong>${activity.title}</strong>.</p>
			<p>El teu compte de punts a aumentat en <strong>${activity.points}</strong> punts.</p>
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
