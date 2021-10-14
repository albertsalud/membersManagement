<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="${applicationScope.webURL}/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>Àrea privada</h1>
			<p>Benvingut/da a l'area privada de socis.</p>
			<p>Aqui podràs canviar les teves dades personals, comprovar els punts acumulats, i veure properes activitats planificades.</p>
			
			<h2>Les teves dades</h2>
			<p>
				<strong>Nom:</strong> ${member.name}
			</p>
			<p>
				<strong>Cognoms:</strong> ${member.surname}
			</p>
			<p>
				<strong>E-mail:</strong> ${member.email}
			</p>
			<p>
				<strong>Telèfon:</strong> ${member.phone}
			</p>
			<p><a href="${context}/private/data">&gt; Canvia les meves dades</a></p>
			<p><a href="${context}/private/changePassword">&gt; Canvia la meva paraula de pas</a></p>
			
			<h2>La teva participació</h2>
			<p>Has acumulat <strong>${member.points}</strong> punts aquest any.</p>
			<p><a href="${context}/private/participation">&gt; Comprova la meva participació</a></p>
			
			<h2>Properes activitats</h2>
			<p>A continuació tens una llista amb noves propostes:</p>
			<table id="data-table" cellpadding="5" cellspacing="0">
				<tr>
					<th>Data</th>
					<th>Activitat</th>
					<th>Ubicació</th>
					<th>Punts</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${activities}" var="currentActivity">
					<tr>
						<td><fmt:formatDate value="${currentActivity.startDate}" pattern="dd/MM/yyyy HH:mm"/></td>
						<td>${currentActivity.title}</td>
						<td>${currentActivity.location}</td>
						<td>${currentActivity.points}</td>
						<td>
							<c:if test="${currentActivity.startDate.before(now) && currentActivity.endDate.after(now)}">
								<a href="${context}/activities/check">&gt; Participar</a>
							</c:if>
							 - ${now}
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:import url="logout.jsp" />
		</div>
		<div id="tools">
			<c:import url="${applicationScope.webURL}/tools.html" />
		</div>
	</div>
</body>
</html>