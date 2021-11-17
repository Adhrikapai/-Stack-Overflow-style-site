function getQuestions(url, qid) {
	if (document.getElementById("questions" + qid).innerHTML == "") {
		fetch(url+qid)
		.then(data => data.json())
		.then(function(data) {
			var textToDisplay = "";
			textToDisplay += "Answer: " + data.questions.answers.answer + "<br>";
			textToDisplay += "UpVotes: " + data.questions.answers.upVotes + "<br>";
		document.getElementById("questions"+qid).innerHTML = textToDisplay;
		});
	} 
	else {
		document.getElementById("questions" + qid).innerHTML = "";
	}
}