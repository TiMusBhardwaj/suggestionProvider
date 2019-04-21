package com.company.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.company.app.service.AutoCompleteService;
import com.company.domain.configdata.repository.CityInfoRepository;

//@Component
/**
 * This is compact trie using single leave node until their is a divergence.
 * Its a improvement over trie
 * TODO:Still need to figure out a way to get only request count, coz at present it delivers all results.
 * For now go with trie 
 * 
 * @author sumit.bhardwaj
 *
 */
public class AutoCompleteServicePatricia implements AutoCompleteService {

	private static final Logger logger = LoggerFactory.getLogger(AutoCompleteServicePatricia.class);

	private Trie<String, Object> trie = new PatriciaTrie<>();
	@Autowired
	private CityInfoRepository cityInfoRepository;

	private static final Object PRESENT = new Object();

	@Override
	@EventListener(value = ContextRefreshedEvent.class)
	@Transactional(readOnly = true)
	public void intilaize() {
		logger.info("-----Populating Trie Start--------");
		try (Stream<String> cities = cityInfoRepository.getAllCities()) {
			trie = new PatriciaTrie<>();
			cities.filter(x -> x != null && !x.trim().isEmpty())
					.forEach(x -> trie.put(x.trim().toUpperCase(), PRESENT));
			this.trie = trie;

		}
		logger.info("-----Populating Trie DONE--------");

	}

	@Override
	public List<String> getSuggestion(String prefix, int requestedCount) {
		logger.info("Finding suggestion for {}. Requsted Count {}", prefix, requestedCount);

		 List<String> suggestions = new ArrayList<String>(trie.prefixMap(prefix.toUpperCase()).keySet()).subList(0, requestedCount);

		logger.debug("Suggested List : {}", suggestions);
		return suggestions;
	}

	public int getTrieSize() {
		return trie.size();
	}
}
