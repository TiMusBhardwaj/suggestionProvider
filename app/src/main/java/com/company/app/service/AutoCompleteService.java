package com.company.app.service;


import java.util.List;

/**
 * 
 * @author sumit.bhardwaj
 *
 */
public interface AutoCompleteService {
	
	
	/**
	 * Initialize the data structure needed to store information
	 * 
	 */
	public void intilaize();
	
	/**
	 * @param prefix
	 * @param requestedCount
	 * @return list of suggestion based on prefix provided.
	 * List equal to requestedCountor number of result present whichever is smaller.
	 */
	public List<String> getSuggestion(String prefix, int requestedCount);
	
}
