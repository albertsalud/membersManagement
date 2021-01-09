<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activity registration</title>
</head>
<body>
	<h1>Congratulations!</h1>
	<p>You have been registered into ${activity.title}.</p>
	<p>Your points account has been increased in ${activity.points} points.</p>
	<p>
		<a href="${context}/private/home">&lt; Back to member's home</a>
	</p>
</body>
</html>