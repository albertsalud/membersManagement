<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Activitiy form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css" />
	<script>
    jQuery(function($) {
        $("#startDate").datetimepicker({
        	dateFormat: 'dd/mm/yy',
        	timeFormat:'HH:mm'
        });
        $("#endDate").datetimepicker({
        	dateFormat: 'dd/mm/yy',
        	timeFormat:'HH:mm'
        });
    });
    
    function fillEndDate(){
    	var startDate = $("#startDate").val();
    	var endDate = $("#endDate").val();
    	
    	if(endDate == "") $("#endDate").val(startDate);
    }
  </script>
</head>
<body>
	<h1>Activity form</h1>
	<p>
		<a href="${context}/admin/activities">&lt; Return to activities list</a>
	</p>
	<spring:hasBindErrors name="activitiesFormDTO">
		<c:forEach var="error" items="${errors.allErrors}">
			<c:catch var="exception"><c:set var="errorField" value="${error.field}" /></c:catch>
			<c:if test="${not empty exception}">
				<p class="error">Error: <spring:message message="${error}" /></p>
			</c:if>
		</c:forEach>
	</spring:hasBindErrors>
	<form:form action="${context}/admin/activities/save" modelAttribute="activitiesFormDTO" method="post">
		<form:hidden path="id"/>
		<table>
			<tr>
				<td>Title:</td>
				<td>
					<form:input path="title" />
					<form:errors path="title" />
				</td>
			</tr>
			<tr>
				<td>Start date:</td>
				<td>
					<form:input path="startDate" onchange="fillEndDate()"/>
					<form:errors path="startDate" />
				</td>
			</tr>
			<tr>
				<td>End date:</td>
				<td>
					<form:input path="endDate" />
					<form:errors path="endDate" />
				</td>
			</tr>
			<tr>
				<td>Points:</td>
				<td>
					<form:input path="points" />
					<form:errors path="points" />
				</td>
			</tr>
			<tr>
				<td>Code:</td>
				<td>
					<form:input path="code" />
					<form:errors path="code" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Save" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>