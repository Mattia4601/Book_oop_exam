package it.polito.oop.books;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;


public class Assignment {
	
	private String studentID;
	private ExerciseChapter exC;
	// responses collection --> sorted map: key = Question object, value = Response
	private TreeMap<Question, Response> respColl = new TreeMap<>();
	
    public String getID() {
        return this.studentID;
    }

    public ExerciseChapter getChapter() {
        return this.exC;
    }

    
//  The method addResponse(Question, List<String>) adds the response by
//  the student to the given question: the list contains the answers that
//  the student considers correct. The method returns the score achieved by
//  the student: the score is (N - FP - FN)/N where:
//
//
//  N is the number of total answers for the question,
//
//  FP is the number of answers provided by the student that are not
//  correct for the question,
//
//  FN is the number of correct answers for the question that are not
//  provided by the student.

    public double addResponse(Question q,List<String> answers) {
      
        	
        // create the new response question
       	Response r = new Response(q,answers);
       	
       	// get the number of total answers available for the question 
       	long n = q.numAnswers();
       	
       	// check the number of answers provided by the student which are correct
       	// and check the of correct answers not provided
       	float fp = 0, correctAnswers = 0;
       	float remCorrAnsw = 0;
        	
       	// getting the sets for correct answers and incorrect answers
       	Set<String> corrAnsSet = q.getCorrectAnswers();
       	Set<String> incorrAnsSet = q.getIncorrectAnswers();
       	
       	// for each answer provided
       	for (String answ : answers) {
       		if (corrAnsSet.contains(answ))
       			correctAnswers++;
        	else
        		fp++;
       		
       	}
//        	the number of correct answers for the question that are not
//        	provided by the student
       	float fn = corrAnsSet.size()-correctAnswers;
       	System.out.println("n fp fn"+n+" "+fp+ " "+fn);
       	float res = ((float)n - fp - fn)/n;
       	System.out.println(res);	
       	r.setScore(res);
       	// adding the new response to our collection
       	this.respColl.put(q, r);
        	
       	return res;
        
    }
    
    
    public Assignment(String studentID, ExerciseChapter exC) {
		super();
		this.studentID = studentID;
		this.exC = exC;
	}

    // he method totalScore() returns the sum of the scores for all provided responses.
	public double totalScore() {
		return  this.respColl.values().stream() // get a stream of responses
	    		.mapToDouble(Response::getScore)
	    		.sum();
	    
    }

}
