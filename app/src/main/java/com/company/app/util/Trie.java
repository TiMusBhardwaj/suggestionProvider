package com.company.app.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Trie {
	
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

    public Trie(String value) {
        root = new TrieNode(value);
        size = 0;
    }

    public void insert(String word) {
        //word = word.toLowerCase();
        if (word == null) throw new IllegalArgumentException();
        TrieNode node = root;
        char[] charArr = word.toCharArray();
        for (char c : charArr) {
            if (!node.children.containsKey(c)) node.add(c);
            node = node.children.get(c);
        }
        node.full = true;
        size++;
    }

    public String find(String word) {
        TrieNode node = root;
        char[] charArr = word.toCharArray();
        for (char c : charArr) {
            if (!node.children.containsKey(c)) return "";
            node = node.children.get(c);
        }
        return node.value;
    }

    public List<String> getPrefixes(TrieNode node) {
        ArrayList<String> results = new ArrayList<String>();
        if (node.full) results.add(node.value);
        for (Entry<Character, TrieNode> child : node.children.entrySet()) {
            TrieNode val = child.getValue();
            List<String> childPrefixes = getPrefixes(val);
            results.addAll(childPrefixes);
        }
        return results;
    }
    
    
    public List<String> getPrefixes(TrieNode node,  int requestedCount) {
        ArrayList<String> results = new ArrayList<String>();
        if (node.full) {
        	results.add(node.value);
        	resultcount.set(resultcount.get().intValue()+1);
        	if (resultcount.get().intValue() >= requestedCount ) {
        		return results;
        	}
        }
        for (Entry<Character, TrieNode> child : node.children.entrySet()) {
            TrieNode val = child.getValue();
            List<String> childPrefixes = getPrefixes(val, requestedCount);
            results.addAll(childPrefixes);
            if (resultcount.get().intValue() >= requestedCount ) {
        		return results;
        	}
        }
        return results;
    }
    
    public List<String> autoComplete(String prefix) {
        TrieNode node = root;
        List<String> result = new ArrayList<String>();
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return result;
            node = node.children.get(c);
        }
        result = getPrefixes(node);
        return result;
    }
    
    public List<String> autoComplete(String prefix, int requestedCount) {
        TrieNode node = root;
        List<String> result = new ArrayList<String>();
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return result;
            node = node.children.get(c);
        }
        result = getPrefixes(node, requestedCount);
        return result;
    }
    
    public int size() {
        return size;
    }
    
    // public static void main(String args[]) {
        // Trie trie = new Trie();
        // trie.insert("Alabama");
        // trie.insert("Alaska");
        // trie.insert("Arizona");
        // trie.insert("Arkansas");
        // trie.insert("California");
        // trie.insert("Colorado");
        // trie.insert("Connecticut");
        // trie.insert("Delaware");
        // trie.insert("Florida");
        // trie.insert("Georgia");
        // trie.insert("Hawaii");
        // trie.insert("Idaho");
        // trie.insert("Illinois");
        // trie.insert("Indiana");
        // trie.insert("Iowa");
        // trie.insert("Kansas");
        // trie.insert("Kentucky");
        // trie.insert("Louisiana");
        // trie.insert("Maine");
        // trie.insert("Maryland");
        // trie.insert("Massachusetts");
        // trie.insert("Michigan");
        // trie.insert("Minnesota");
        // trie.insert("Mississippi");
        // trie.insert("Missouri");
        // trie.insert("Montana");
        // trie.insert("Nebraska");
        // trie.insert("Nevada");
        // trie.insert("New Hampshire");
        // trie.insert("New Jersey");
        // trie.insert("New Mexico");
        // trie.insert("New York");
        // trie.insert("North Dakota");
        // trie.insert("North Carolina");
        // trie.insert("Ohio");
        // trie.insert("Oklahoma");
        // trie.insert("Oregon");
        // trie.insert("Pennsylvania");
        // trie.insert("Rhode Island");
        // trie.insert("South Carolina");
        // trie.insert("South Dakota");
        // trie.insert("Tennessee");
        // trie.insert("Texas");
        // trie.insert("Utah");
        // trie.insert("Vermont");
        // trie.insert("Virginia");
        // trie.insert("Washington");
        // trie.insert("West Virginia");
        // trie.insert("Wisconsin");
        // trie.insert("Wyoming");
        // List<String> results = trie.autoComplete("a");
        // System.out.println(results.toString());

    // }
}