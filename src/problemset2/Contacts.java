package problemset2;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class Contacts {

    /*
     * Complete the contacts function below.
     */
	static int[] contacts(String[][] queries) {
        HashMap<String, Integer> entries = new HashMap<String, Integer>();
        int i = 0;
        String tmp = null;
        ArrayList<Integer> resultArr = new ArrayList<Integer>();
        for(String[] query : queries) {
            if(query[0].equals("add")) {
                for(i = 0 ; i < query[1].length() ; i++) {
                    tmp = query[1].substring(0, i+1);
                    if(entries.containsKey(tmp)) {
                        entries.put(tmp, entries.get(tmp) + 1);
                    } else {
                        entries.put(tmp, 1);
                    }
                }
            } else if(query[0].equals("find")) {
                if(entries.containsKey(query[1])) {
                    resultArr.add(entries.get(query[1]));
                }else {
                    resultArr.add(0);
                }
            }
        }
        int[] results = new int[resultArr.size()];
        Stream.iterate(0, counter -> counter+1).limit(resultArr.size())
            .forEach(counter -> results[counter] = resultArr.get(counter));
        return results;
    }
	
	static int[] contacts2(String[][] queries) {
        Trie trie = new Trie();
        Node tmp = null;
        ArrayList<Integer> resultArr = new ArrayList<Integer>();
        int i = 0;
        for(String[] query : queries) {
            if(query[0].equals("add")) {
                trie.addNode(query[1]);
            } else if(query[0].equals("find")) {
                tmp = trie.findNode(query[1]);
                if(tmp!=null) {
                    resultArr.add(trie.traverseWithWordCount(tmp));
                } else {
                    resultArr.add(0);
                }
            }
            
        }
        int[] results = new int[resultArr.size()];
        Stream.iterate(0, counter -> counter+1).limit(resultArr.size())
            .forEach(counter -> results[counter] = resultArr.get(counter));
        return results;
    }
    
    static class Trie {
        Node root;
        public Trie() {
            this.root = new Node('.');
        }
        
        public int traverseWithWordCount(Node partial) {
            return traverseWithWordCountRecursively(partial);
        }
        
        private int traverseWithWordCountRecursively(Node current) {
                
            if(current == null) {
                return 0;
            }
            int count = current.value!= null ? 1 : 0;
            for(Node child : current.children) {
                count = count + traverseWithWordCountRecursively(child);
            }
            return count;
        }
        
        public Node findNode(String partial) {
            return findNodeRecursively(partial, root, 0);
        }
        
        private Node findNodeRecursively(String partial, Node current, int index) {
            if(current == null) {
                return null;
            }
            if(current.c == partial.charAt(index)) {
                if(index == partial.length()-1) {
                    return current;
                }
                return findNodeRecursively(partial, 
                    current.children[partial.charAt(index+1) -'a'], index+1);
            }
            if(current.c == '.') {
                return findNodeRecursively(partial, 
                    current.children[partial.charAt(index) - 'a'], index);
            }
            return null;
        }
        
        public void addNode(String value) {
            addNodeRecursively(value, root, 0);
        }
        
        private void addNodeRecursively(String value, Node current, int index) {
            if(current.c == value.charAt(index)) {
                if(index == value.length() -1) {
                    current.value = value;
                    return;
                }
                if(current.children[value.charAt(index+1)-'a'] == null) {
                    current.children[value.charAt(index+1)-'a'] = 
                        new Node(value.charAt(index+1));
                }
                addNodeRecursively(value, 
                    current.children[value.charAt(index+1)-'a'], index+1);
                    
            } else if(current.c == '.') {
                if(current.children[value.charAt(index)-'a'] == null) {
                   current.children[value.charAt(index)-'a'] = 
                        new Node(value.charAt(index));
                }
                addNodeRecursively(value, current.children[value.charAt(index)-'a'], index);
            }
        }
    }
    
    static class Node {
        String value;
        char c;
        Node[] children;
        
        public Node(char c) {
            this.c = c;
            value = null;
            children = new Node[26];
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);
        System.out.println(result);
		/*
		 * for (int resultItr = 0; resultItr < result.length; resultItr++) {
		 * bufferedWriter.write(String.valueOf(result[resultItr]));
		 * 
		 * if (resultItr != result.length - 1) { bufferedWriter.write("\n"); } }
		 */

        //bufferedWriter.newLine();

        //bufferedWriter.close();
    }
}
