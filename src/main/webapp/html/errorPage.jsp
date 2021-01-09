<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<h1>Ops</h1>
	<p>Something was wrong! Sorry the inconvenience.</p>
	<c:if test="${user != null}">
		<a href="${context}/private">&lt; Back to home</a>
	</c:if>
</body>
</html>