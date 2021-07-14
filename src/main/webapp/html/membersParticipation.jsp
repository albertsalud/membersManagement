<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
	<div id="header">
		<c:import url="http://daudecinc.tk/menu.html" />
	</div>
	<div id="content-wrapper">
		<div id="content" class="no-news">
			<h1>La meva participació</h1>
			<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
			<form action="${context}/private/participation" method="get" name="changeYear">
				<p>
					Any:
					<select name="year" onchange="document.forms.changeYear.submit();">
						<option value="${year}">${year}</option>
						<option ${param.year == year-1 ? 'selected' : ''} value="${year-1}">${year-1}</option>
					</select>
				</p>
			</form>
			<c:if test="${activities != null}">
				<table id="data-table" cellspacing="0" cellpadding="5">
					<tr>
						<th>Activitat</th>
						<th>Data</th>
						<th>Punts</th>
					</tr>
					<c:forEach items="${activities}" var="currentActivity">
						<tr>
							<td>${currentActivity.title}</td>
							<td>
								<fmt:formatDate value="${currentActivity.startDate}" pattern="dd/MM/yyyy HH:mm"/>
								
							</td>
							<td>${currentActivity.points}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<p>
				<a href="${context}/private/home">&lt; Torna a la pàgina principal</a>
			</p>
		</div>
		<div id="tools">
			<c:import url="http://daudecinc.tk/tools.html" />
		</div>
	</div>
</body>
</html>