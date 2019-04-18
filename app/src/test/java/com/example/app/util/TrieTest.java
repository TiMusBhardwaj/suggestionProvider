package com.example.app.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import com.company.app.util.Trie;

public class TrieTest {
	

	private String states = "Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, " +
            "Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, " +
            "Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, " +
            "Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, " +
            "New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, " + 
            "Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, " +
            "Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming";
	
	 private Trie trie;
	
	@Before
	public void setup() {
		 trie = new Trie();
	        StringTokenizer st = new StringTokenizer(states, ",");
	        while(st.hasMoreTokens()) trie.insert(st.nextToken().trim());
	}
	
	@Test
	public void testAutoComplete() {
		List<String> results = trie.autoComplete("Al", 3);
		//Returns Max matches if less than requestedCount
		assertTrue(results.contains("Alabama") && results.contains("Alabama") && results.size() == 2);
	}
	
	@Test
	public void testAutoCompleteWithRequestedCount() {
		List<String> results = trie.autoComplete("Al", 1);
		assertTrue(results.size() == 1);
	}
	
	@Test
	public void testResquestedPrefixNotPresent() {
		List<String> results = trie.autoComplete("ZZ", 1);
		assertTrue(results.size() == 0);
	}
	
	@Test
	public void testNullPrefix() {
		List<String> results = trie.autoComplete(null, 1);
		assertTrue(results.size() == 0);
	}
	
	@Test
	public void testBlankPrefix() {
		List<String> results = trie.autoComplete(" ", 1);
		assertTrue(results.size() == 0);
	}
	
	@Test
	public void testNegativeResultCount() {
		List<String> results = trie.autoComplete("Al", -1);
		assertTrue(results.size() == 0);
	}
    
	

}
