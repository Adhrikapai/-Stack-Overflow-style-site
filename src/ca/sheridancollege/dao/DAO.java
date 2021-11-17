package ca.sheridancollege.dao;

import java.util.List;

import ca.sheridancollege.beans.Answers;
import ca.sheridancollege.beans.Questions;

public interface DAO {
	
	public void populate();
	public Questions getQuestionsById(Long id);
	public List<Questions> getQuestionsListById(Long qid);
	public List<Questions> getQuestionsList();
	public void insertQuestions(Questions question) ;
	
	 public void updateAnswerVotes(Long id);
	 public void insertAnswers(Answers answer);
	public Answers getAnswersById(Long id); 

	
}
