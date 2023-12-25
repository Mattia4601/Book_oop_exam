package it.polito.oop.books;

import java.util.LinkedList;
import java.util.List;

public class Response {
	private Question q;
	private List<String> studAnswers = new LinkedList<>();
	private double score;
	public Response(Question q, List<String> studAnswers) {
		super();
		this.q = q;
		this.studAnswers = studAnswers;
	}
	
	
	
	public Question getQ() {
		return q;
	}

	public double getScore() {
		return score;
	}



	public void setScore(double res) {
		this.score = res;
	}



	public void setQ(Question q) {
		this.q = q;
	}

	public List<String> getStudAnswers() {
		return studAnswers;
	}

	public void setStudAnswers(List<String> studAnswers) {
		this.studAnswers = studAnswers;
	}
	
	
}
