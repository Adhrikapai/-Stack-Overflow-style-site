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
<c:url value="/scripts/script.js" var="scriptUrl" /> 
<script src="${scriptUrl}"></script>
</head>
<body>
	<header>
	</header>
	<c:url value="/addQuestion" var="addUrl" />
	<a href="${addUrl}">Add new Question</a>
	<br>
	<div id="content">
		<c:url value="/getQuestions/" var="JSONUrl" /> 
			<c:forEach var="q" items="${questions}"> 
			<h3> 
				<a href="#" id="a${q.qid}" onclick="getQuestions('${JSONUrl}', ${q.qid})">${q.question}</a> 
				<div id="questions${q.qid}">
				</div> 
			</h3> 
			<c:url value="/addAnswers/${q.qid}" var="answerUrl"/>
			<a href="${answerUrl}">New answer</a>
			</c:forEach>
	</div>
	<footer>
	</footer>
</body>
</html>