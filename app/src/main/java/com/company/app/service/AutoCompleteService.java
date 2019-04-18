package com.company.app.service;

import java.util.List;

public interface AutoCompleteService {

	public List<String> getSuggestion(String start);
	
	public List<String> getSuggestion(String start, int resultCount);
	
}
