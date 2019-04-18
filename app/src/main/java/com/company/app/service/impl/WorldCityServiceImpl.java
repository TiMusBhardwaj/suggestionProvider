package com.company.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.service.AutoCompleteService;
import com.company.app.service.WorldCityService;

/**
 * 
 * 
 * @author sumit.bhardwaj
 *
 */
@Service
public class WorldCityServiceImpl implements WorldCityService {

	
	@Autowired
	private AutoCompleteService autoCompleteService;
	
	@Override
	public List<String> suggestCities(String start, int requestCount) {
		
		return autoCompleteService.getSuggestion(start,requestCount);
	}

	@Override
	public List<String> suggestCities(String start) {
		
		return autoCompleteService.getSuggestion(start);
	}
	
	

}
