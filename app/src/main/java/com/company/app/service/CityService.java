package com.company.app.service;

import java.util.List;

/**
 * @author sumit.bhardwaj
 *
 */
public interface CityService {
	
	/**
	 * @param prefix
	 * @param requestCount
	 * @return returns list of suggested cities that match the prefix.
	 * List count is equal to requestCount or results present which ever is smaller.
	 */
	public List<String> suggestCities(String prefix, int requestCount);

}
