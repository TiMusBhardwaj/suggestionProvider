package com.company.app.util;
import java.util.*;


/**
 * I need use Alphabet array here to save space.
 * Reason for that is some cities in world have special char in their name.
 * So to avoid those issues i have decided not to use alphanbet array.
 * 
 * @author sumit.bhardwaj
 *
 */
public class TrieNode
{
    public String value;
    public final Map<Character, TrieNode> children;
    public boolean full;
    
    TrieNode() {
        this(null);
        full = false;
    }
    
    TrieNode(String value){
        this.value = value;
        children = new TreeMap<Character, TrieNode>();  
        full = false;
    }
    
    public void add(char c) {
        String str;
        if (value == null) str = Character.toString(c);
        else str = value + c;
        children.put(c, new TrieNode(str));
    }

	@Override
	public String toString() {
		return "TrieNode [value=" + value + ", children=" + children.keySet() + ", full=" + full + "]";
	}
	
	
    
    
}