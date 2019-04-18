package com.company.app.service;

import java.util.List;

public interface WorldCityService {
	
	public List<String> suggestCities(String start, int requestCount);
	public List<String> suggestCities(String start);
}
