<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h1>Enhorabona!</h1>
			<p>Has activat correctament el teu usuari.</p>
			<p>Ja pots accedir a la teva àrea privada.</p>
			<p>
				<a href="${context}/private">&gt; Accedir</a>
			</p>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>