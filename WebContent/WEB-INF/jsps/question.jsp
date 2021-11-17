<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QuackOverflow</title>
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
</head>
<body>
	<header>
	</header>
	<c:url value="/home" var="homeUrl" />
	<a href="${homeUrl}">Go back home</a>
	<div id="content">
		<c:url value="/saveQuestion" var="url" />
			<form:form modelAttribute="questions" method="post" action="${url}">
				Question:
				<form:input path="question" /><div style="color:red">${question}</div><br />
				<form:hidden path="qid"/>
			<input type="submit" value="Go!" />
			</form:form>
	</div>
	<footer>
	</footer>
</body>
</html>