package it.polito.oop.books;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Question implements Comparable<Question>{
	
	private String question;
	private Topic mainTopic;
	// answers collection --> map: key = answer (string), value = boolean to indicate whether the answer is correct or not
	private TreeMap<String,Boolean> answersMap = new TreeMap<>();
	
	
	@Override
	public int compareTo(Question old) {
		
		return this.question.compareTo(old.question);
	}
	
	
	// constructor
	public Question(String question, Topic mainTopic) {
		super();
		this.question = question;
		this.mainTopic = mainTopic;
	}

	public String getQuestion() {
		return this.question;
	}
	
	public Topic getMainTopic() {
		return this.mainTopic;
	}
	
	/*
	* The method addAnswer(String,boolean) of class Question accepts an 
	* answer and a boolean value indicating whether the given answer is correct for the specific question
	*/
	public void addAnswer(String answer, boolean correct) {
		
		if (!this.answersMap.containsKey(answer))
			this.answersMap.put(answer, correct);
		
		
	}
	
    @Override
    public String toString() {
        return this.question+" ("+this.mainTopic.getKeyword()+")";
    }
    // get the number of answers added
	public long numAnswers() {
	    return (long) this.answersMap.size();
	}
	// get a set of strings containing the correct answers added
	public Set<String> getCorrectAnswers() {
		return this.answersMap.entrySet().stream()
				.filter(e->e.getValue()==true) // take just the correct answers
				.map(e->e.getKey())
				.collect(Collectors.toSet());
				
	}
	// get a set of string containing only the uncorrect answers
	public Set<String> getIncorrectAnswers() {
        return this.answersMap.entrySet().stream()
        		.filter(e->e.getValue()==false)
        		.map(e->e.getKey())
        		.collect(Collectors.toSet());
	}
	
	
}
