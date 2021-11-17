package ca.sheridancollege.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name="Questions.getQuestionsById", query="from Questions where qid = :qid")
@NamedQuery(name = "Questions.getQuestionsList", query = "from Questions")
public class Questions {
	
	@Id
	@GeneratedValue
	private Long qid;
	
	@NotNull
	@Length(min=3, max=300)
	private String question;
	
	public Questions(String question) {
		super();
		this.question = question;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	  private List<Answers> answers = new ArrayList<Answers>(); 
	
}
