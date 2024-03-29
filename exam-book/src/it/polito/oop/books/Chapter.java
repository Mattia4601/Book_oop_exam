package it.polito.oop.books;

import java.util.List;

public abstract class Chapter {
	
	// we have two different types of chapter: theory and exercise
	private String title;
	private int numPages;
	
	public Chapter(String title, int numPages) {
		this.title=title;
		this.numPages = numPages;
	}
	
	public abstract List<Topic> getTopics();
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

}

