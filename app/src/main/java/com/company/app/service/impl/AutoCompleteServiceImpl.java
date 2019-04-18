package com.company.app.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.app.service.AutoCompleteService;
import com.company.app.util.Trie;
import com.company.domain.configdata.repository.CityInfoRepository;

/**
 * This implementation of {@link AutoCompleteService} uses Trie data-structure. 
 * 
 * @author sumit.bhardwaj
 *
 */
@Service
public class AutoCompleteServiceImpl implements AutoCompleteService {
	
	private static final Logger logger = LoggerFactory.getLogger(AutoCompleteServiceImpl.class);
	private Trie trie = new Trie();
	
	@Autowired
	private CityInfoRepository cityInfoRepository;
	
	/**
	 * This Method will Initialize your Trie Data structure.
	 * We can refresh our Trie by calling this method after database update.
	 * 
	 */
	@EventListener(value=ContextRefreshedEvent.class)
	@Transactional(readOnly=true)
	public void intilaize() {
		
		logger.info("-----Populating Trie Start--------");
		try(Stream<String> cities = cityInfoRepository.getAllCities()){
			Trie trie = new Trie();
			cities.filter(x -> x != null && !x.isEmpty()).forEach(x->trie.insert(x.toUpperCase()));
			this.trie = trie;
			
		}
		logger.info("-----Populating Trie DONE--------");
		
	}

	
	
	/* (non-Javadoc)
	 * @see com.company.app.service.AutoCompleteService#getSuggestion(java.lang.String, int)
	 */
	@Override
	public List<String> getSuggestion(String prefix, int requestedCount) {
		logger.info("Finding suggestion for {}. Requsted Count {}", prefix, requestedCount);
		
		List<String> suggestions = trie.autoComplete(prefix.toUpperCase(), requestedCount);
		
		logger.debug("Suggested List : {}", suggestions);
		return suggestions;
	}

}
