package it.polito.oop.books;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Book {
	
	// topics collection --> sorted map: key = keyword, value = topic object
	private TreeMap<String, Topic> topicsColl = new TreeMap<>();
	// questions collection --> Sorted Set of questions
	private TreeSet<Question> questionsColl = new TreeSet<>();
    // theory chapter collection --> sorted map: key = chapter title, value = TheoryChapter
	private List<Chapter> chaptersColl = new LinkedList<>();
	// assignments collection --> sorted map: key = student id, value = assignment object
	private TreeMap<String,Assignment> assignmentsColl = new TreeMap<>();

	
	/**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	public Topic getTopic(String keyword) throws BookException {
	    
		// check if the string is not empty
		if (keyword.equals(null))
			throw new BookException();
		
		// check if the topic has already been added to the collection
		if (this.topicsColl.containsKey(keyword))
			return this.topicsColl.get(keyword); // return the existing one
		
		// if it doesn't exist yet we must create the new topic
		Topic newEl = new Topic();
		newEl.setKeyword(keyword);
		// and add it to the collection
		this.topicsColl.put(keyword, newEl);
		
		return newEl;
	}
	/*
	* the method createQuestion(String,Topic) that accepts the text of the question and its main topic.
	*/
	public Question createQuestion(String question, Topic mainTopic) {
        
		Question q = new Question(question,mainTopic);
		
		this.questionsColl.add(q);
		
		return q;
	}
	/*
	* A theory chapter can be built using the method
	* createTheoryChapter(String title, int numPages, String text) that gets
	* the title of the chapter, the number of pages, and a string with all the
	* text of the chapter. The method returns a TheoryChapter object.
	*/
	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
        
		TheoryChapter tc = new TheoryChapter(title,numPages,text);
		
		// adding it to the chapters collection
		this.chaptersColl.add(tc);
		
		return tc;
	}
	
//	An exercise chapter can be added to the book using the method
//	createExerciseChapter(String title, int numPages) that accepts the
//	title of the chapter and the number of pages. The method returns an
//	ExerciseChapter object.
	public ExerciseChapter createExerciseChapter(String title, int numPages) {
        
		ExerciseChapter exChapter = new ExerciseChapter(title, numPages);
		this.chaptersColl.add(exChapter);
		
		return exChapter;
	}
	
//	The method getAllTopics() in Book returns a list of all topics
//	List<Topic> specified in the book chapters -- both theory and exercise
//	-- the list is sorted in alphabetical order and does not contains
//	duplicates.
	public List<Topic> getAllTopics() {
        List<Topic> res = this.chaptersColl.stream()
        		.flatMap(c -> c.getTopics().stream())
        		.distinct()
        		.sorted()
        		.collect(Collectors.toList());
        		return res;
	}
	
//	The method checkTopics return true if all the topics specified in
//	all exercise chapters are contained in at least one theory chapter.
	public boolean checkTopics() {
        
		// we need a list with all exercise chapters topics
		List<Topic> exTopicsList = this.chaptersColl.stream()
				.filter(c -> c instanceof ExerciseChapter) // take only exercise chapters
				.flatMap(c->c.getTopics().stream()) // take all the topics and merge them into a sigular stream of topics
				.distinct()
				.collect(Collectors.toList());
		
		// we also need a list with all the theory chapters topics
		List<Topic> theoryTopicsList = this.chaptersColl.stream()
				.filter(c -> c instanceof TheoryChapter) // take only exercise chapters
				.flatMap(c->c.getTopics().stream()) // take all the topics and merge them into a sigular stream of topics
				.distinct()
				.collect(Collectors.toList());
		
		return theoryTopicsList.containsAll(exTopicsList);
	}

//	An assignment represents the answers to a question provided by a
//	student.
//	The method newAssignment(String, ExerciseChapter) creates a new
//	assignment for the given student whose ID is provided as argument and
//	the given exercise chapter. It returns an Assignment object that
//	provides the getter methods for the two pieces of information.
	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
        
		Assignment assObj = new Assignment(ID, chapter);
		
		if (!this.assignmentsColl.containsKey(ID))
			this.assignmentsColl.put(ID, assObj);
		return assObj;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
//	The method questionOptions() in Book returns a map having as key the
//	number of answers and as values the list of questions having that number
//	of answers.
    public Map<Long,List<Question>> questionOptions(){
        return this.questionsColl.stream()
        		.collect(Collectors.groupingBy( q->q.numAnswers(),
        				Collectors.toList()));
    }
   
}
