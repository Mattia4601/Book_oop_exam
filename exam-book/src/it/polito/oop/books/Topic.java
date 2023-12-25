package it.polito.oop.books;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Topic implements Comparable<Topic>{
	
	private String keyword; // unique attribute 
	// subtopics collection
	private TreeSet<Topic> subtopics = new TreeSet<>();
	
	
	@Override
	public int compareTo(Topic t) {
		return this.keyword.compareTo(t.getKeyword());
	}
	
	public String getKeyword() {
        return this.keyword;
	}
	
	@Override
	public String toString() {
	    return this.keyword;
	}
	/*
	The method addSubTopic(Topic) accepts a Topic as an argument and adds it as sub-topic
	it returns true if the subtopic was added or false if the subtopic was not added 
	because already listed as a sub-topic
	*/
	public boolean addSubTopic(Topic topic) {
        
		// check if the topic has already been entered in the subtopics collection
		if (this.subtopics.contains(topic))
			return false; // it has already been added
		
		// otherwise add it to the collection
		this.subtopics.add(topic);
		
		return true;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
        
		List<Topic> list = new ArrayList<>(this.subtopics);
		return list;
	}
	
	// recursive version of getSubtopics to get also the subtopics of the subtopics
	public List<Topic> getSubTopicsR(List<Topic> topicList){
		
		// if the current topic does not have other subtopics terminate
		if (this.subtopics.size()==0) {
			return topicList;
		}
		
		// otherwise call the recursion for each subtopic
		for (Topic t : this.subtopics) {
			topicList.addAll(t.getSubTopicsR(topicList));
		}
		
		return topicList;
	}
	
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
