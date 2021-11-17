package ca.sheridancollege.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Answers.updateAnswerVotes", query = "update Answers set upVotes = upVotes + 1 where id= :id")
@NamedQuery(name="Answers.getAnswersById", query="from Answers where id = :id")
public class Answers {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Length(min=3, max=300)
	private String answer;
	
	private int upVotes;
	
	public Answers(String answer, int upVotes) {
		super();
		this.answer = answer;
		this.upVotes = upVotes;
	}

	
}
