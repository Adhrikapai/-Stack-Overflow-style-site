package ca.sheridancollege.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.sheridancollege.beans.Answers;
import ca.sheridancollege.beans.Questions;

public class QuestionsDAO implements DAO{
	SessionFactory sessionFactory= new Configuration()
			.configure("ca/sheridancollege/config/hibernate.cfg.xml")
			.buildSessionFactory();
	
	public void populate() {
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		
		Questions q1 = new Questions("What is something that everyone looks stupid doing?");
		session.save(q1);
		Questions q2 = new Questions("If animals could talk, which would be the rudest?");
		session.save(q2);
		Questions q3 = new Questions("If you were arrested with no explanation, what would your friends and family assume you had done?");
		session.save(q3);
		Questions q4 = new Questions("Is a hotdog a sandwich?");
		session.save(q4);
		Questions q5 = new Questions("How do you feel about putting pineapple on pizza?");
		session.save(q5);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public Questions getQuestionsById(Long qid) { 
		return (Questions) sessionFactory.openSession() 
				.getNamedQuery("Questions.getQuestionsById") 
				.setParameter("qid", qid) 
				.getSingleResult(); 
	}
	
	public Answers getAnswersById(Long id) { 
		return (Answers) sessionFactory.openSession() 
				.getNamedQuery("Answers.getAnswersById") 
				.setParameter("id", id) 
				.getSingleResult(); 
	}
	
	public List<Questions> getQuestionsListById(Long qid) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Questions.getQuestionsById");
		query.setParameter("qid", qid);
		List<Questions> questions = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return questions;
	}
	
	 public void updateAnswerVotes(Long id){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createNamedQuery("Answers.updateAnswerVotes");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();	
	}
	 
	 public List<Questions> getQuestionsList(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		Query query = session.createNamedQuery("Questions.getQuestionsList");
		List<Questions> questions = query.getResultList();	
		session.getTransaction().commit();
		session.close();	
		return questions;
	 }
	 
	 public void insertAnswers(Answers answer) {
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		session.save(answer);
		session.getTransaction().commit();
		session.close();
	}
	 
	public void insertQuestions(Questions question) {
		Session session= sessionFactory.openSession();
		session.beginTransaction();	
		session.saveOrUpdate(question);
		session.getTransaction().commit();
		session.close();
	}

	
}
	
	

