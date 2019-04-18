package com.company.app.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.service.AutoCompleteService;
import com.company.app.service.CityService;

/**
 * 
 * 
 * @author sumit.bhardwaj
 *
 */
@Service
public class CityServiceImpl implements CityService {

	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Autowired
	private AutoCompleteService autoCompleteService;
	
	@Override
	public List<String> suggestCities(String start, int requestCount) {
		logger.debug("Service called with request params : [{}] and request count {}" , start, requestCount);
		return autoCompleteService.getSuggestion(start,requestCount);
	}


}
