package it.polito.oop.books;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class TheoryChapter extends Chapter{
	

	private String text;
	// topics coll for a chapter
	private TreeSet<Topic> topicsSet = new TreeSet<>();
	
	// constructor
	public TheoryChapter(String title, int numPages, String text) {
		super(title, numPages);
		this.text = text;
	}

	// getters and setters
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	


//	The class provides getter and setter for the text. The method
//	getTopics() returns all topics inserted by addTopic(), without
//	repetitions and sorted in alphabetical order.
//
	public List<Topic> getTopics() {
		List<Topic> lst = new ArrayList<>(topicsSet);
		return lst;
	}

	/*
	* Topics
	* may be added to the chapter using the method addTopic(Topic); the
	* method adds the given topic to the chapter and, recursively, all its
	* subtopics, either directly or indirectly specified.
    */
    public void addTopic(Topic topic) {
    	if (!topicsSet.contains(topic)) {
    		this.topicsSet.add(topic);
    		// we also recursively add the subtopics 
    		for (Topic t : topic.getSubTopics()) {
    			addTopic(t); 
    		}
    	}
    }
    
}
