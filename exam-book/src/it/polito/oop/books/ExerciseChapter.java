package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ExerciseChapter extends Chapter{
	
	private List<Question> questions = new LinkedList<>();
	
	// constructor
    public ExerciseChapter(String title, int numPages) {
		super(title, numPages);
	}

//    Questions may be added to it using the method
//    addQuestion(Question) that accepts a Question. The method
//    getTopics() returns all topics specified as main topic in the
//    questions, without repetitions and sorted in alphabetical order.
	
    public List<Topic> getTopics() {
        // create a set to collect all the main topics of our questions
    	Set<Topic> topics = new HashSet<>();
    	
    	// for each question add its own topic to the set previously created
    	for (Question q : questions) {
    		topics.add(q.getMainTopic());
    	}
		
    	// create a list that will contain all the main topics
    	ArrayList<Topic> lst = new ArrayList<>(topics);
    	Collections.sort(lst); // sort it
    	
		return lst;
	}
	

	public void addQuestion(Question question) {
	
		this.questions.add(question);
	}	
}
