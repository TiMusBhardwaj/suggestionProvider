package com.company.app.util;
import java.util.*;

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
}