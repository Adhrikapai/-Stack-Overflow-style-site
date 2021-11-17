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
		<c:forEach var="q" items="${questions}">
			<h2>${q.question}</h2> 
			
			<h2>Answers:</h2>
				<c:forEach var="a" items="${q.answers}">
					<h3>Answer: ${a.answer}</h3>
					<c:url value="/upVote/${a.upVotes}/${q.qid}" var="voteUrl"/>
					<a href="${voteUrl}">Up Vote</a> ${a.upVotes}
				</c:forEach>
		
				<c:url value="/insertAnswers/${q.qid}" var="url" />
				<form:form modelAttribute="answers" method="post" action="${url}">
					<form:input path="answer" placeholder="Enter your answer"/><div style="color:red">${answer}</div>
					<form:hidden path="id"/>
					<input type="submit" value="Add Answer" />
				</form:form>
		</c:forEach>
	</div>
	<footer>
	</footer>
</body>
</html>