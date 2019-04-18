package com.company.app.util;
import java.util.List;
import java.util.StringTokenizer;
 
public class AutoComplete {
    private String states = "Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, " +
                             "Delaware, Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, " +
                             "Kentucky, Louisiana, Maine, Maryland, Massachusetts, Michigan, Minnesota, " +
                             "Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, " +
                             "New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, " + 
                             "Pennsylvania, Rhode Island, South Carolina, South Dakota, Tennessee, Texas, Utah, " +
                             "Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming";
    private Trie trie;
    public AutoComplete() {
        trie = new Trie();
        StringTokenizer st = new StringTokenizer(states, ",");
        while(st.hasMoreTokens()) trie.insert(st.nextToken().trim());
    }
    public List<String> getData(String query) {
        //String state = null;
        query = query.toLowerCase();
        List<String> results = trie.autoComplete(query);
        return results;
    }
    
    public static void main(String[] args) {
		System.out.println(new AutoComplete().getData("West  "));
	}
}