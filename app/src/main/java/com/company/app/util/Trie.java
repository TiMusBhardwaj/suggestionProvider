package com.company.app.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *TODO: Working on Patricia tree to return only required number of elements.
 *Its is compact trie will save a lot of memory
 *Trie will work for now.
 * 
 * 
 * A Trie is a special data structure used to store strings that can be visualized like a graph. 
 * It consists of nodes and edges. Each node consists of at max 26 children and edges 
 * connect each parent node to its children. These 26 pointers are nothing but pointers for each 
 * of the 26 letters of the English alphabet A separate edge is maintained for every edge.
 * 
 * 
 * I could have used ternary tree to save space, but memory used here is very small so 
 * sacrificing speed for memory optimization is not worth it.
 * 
 * 
 * 
 * I could have used Alphabet array here to save space.
 * Reason for not using the same is that, some cities in world have special char in their name.
 * All those char were difficult to know if is a little difficult to know. 
 * As i dont which country will be used for city info
 * So to avoid those issues i have decided not to use alphabet array.
 * 
 * 
 * @author sumit.bhardwaj
 *
 */
public class Trie {
	
	private static final Logger logger = LoggerFactory.getLogger(Trie.class);
	
	private ThreadLocal<Integer> resultcount = new ThreadLocal<Integer>() {
		@Override 
		protected Integer initialValue() {
	        return new Integer(0);
	    }
	};
    private TrieNode root;
    private int size;
    
    public Trie() {
        root = new TrieNode();
        size = 0;
    }

   

    /**
     * This Method add the input string to trie by breaking it down in char array.
     * @param word
     */
    public void insert(String word) {
    	logger.debug("Adding [ {} ] to trie ", word);
        //word = word.toLowerCase();
        if (word == null || word.isEmpty()) throw new IllegalArgumentException();
        TrieNode node = root;
        char[] charArr = word.trim().toCharArray();
        for (char c : charArr) {
        	logger.trace("Current char [{}], currentnode :  {}", c, node);
        	
            if (!node.children.containsKey(c)) node.add(c);
            node = node.children.get(c);
        }
        logger.trace("Added [ {} ] to trie ", word);
        if (!node.full)size++;
        node.full = true;
        
    }

    //TODO: Instead of passing requestcount and 
    //incrementing threadlocal should decrement threadlocal to zero
    private List<String> getPrefixes(TrieNode node,  int requestedCount) {
    	logger.debug("Get Prefixed for [ {} ] , current requestCount :{}", node, requestedCount);
        ArrayList<String> results = new ArrayList<String>();
        if (node.full) {
        	logger.trace("This node will be added to result : {}", node);
        	results.add(node.value);
        	resultcount.set(resultcount.get().intValue()+1);
        	if (resultcount.get().intValue() >= requestedCount ) {
        		logger.debug("Get Prefixed result : {}", results);
        		return results;
        	}
        }
        for (Entry<Character, TrieNode> child : node.children.entrySet()) {
            TrieNode val = child.getValue();
            List<String> childPrefixes = getPrefixes(val, requestedCount);
            results.addAll(childPrefixes);
            if (resultcount.get().intValue() >= requestedCount ) {
            	logger.debug("Get Prefixed result : {}", results);
        		return results;
        	}
        }
        logger.debug("Get Prefixed result : {}", results);
        return results;
    }
    
    
    
    /**
	 * @param prefix
	 * @param requestedCount
	 * @return list of suggestion based on prefix provided.
	 * List equal to requestedCountor number of result present whichever is smaller.
	 */
    public List<String> autoComplete(String prefix, int requestedCount) {
    	logger.debug("AutoComplete request received for [ {} ] , requestCount :{}", prefix, requestedCount);
        TrieNode node = root;
        List<String> result = new ArrayList<String>();
        if (requestedCount < 1 || prefix == null || prefix.trim().isEmpty()) {
        	logger.debug("Return Empty List "); 
        	return result;
        }
        for (char c : prefix.toCharArray()) {
        	logger.trace("Current char [{}], currentnode :  {}", c, node);
            if (!node.children.containsKey(c)) return result;
            node = node.children.get(c);
        }
        
        result = getPrefixes(node, requestedCount);
        return result;
    }
    
    public int size() {
        return size;
    }
    
   
}