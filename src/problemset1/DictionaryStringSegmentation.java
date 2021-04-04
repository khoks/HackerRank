package problemset1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;

public class DictionaryStringSegmentation {

	public static void main(String[] args) {
		
		try {
			
			
			HashSet<String> dictWords = new HashSet<String>();
			Path path = Paths.get("C:\\Dev\\temp\\file.txt");
			Files.lines(path).flatMap(line -> Arrays.asList(line.split(" ")).stream() ).map(word -> word.replace(".", "").replace(",", ""))
				.filter(word -> word.trim().length() > 0).distinct().forEach(dictWords::add);
			
			dictWords.forEach(System.out::println);
			System.out.println(dictWords.stream().count());
			System.out.println("------------");
			
			
			Trie trie = new Trie();
			dictWords.forEach(word -> trie.addWord(word));
			
			trie.traverse();
			
			String inputString = "furtherminimumthe";
			
			System.out.println(checkSegmentRecursively(inputString, 0, dictWords));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private static boolean checkSegmentRecursively(String inputString, int index, HashSet<String> dictWords) {
		if(index >= inputString.length()) {
			return true;
		}
		
		for(int i = index ; i < inputString.length() ; i++) {
			if( dictWords.contains( inputString.substring(index, i + 1) ) ) {
				if(i == inputString.length() -1) {
					return true;
				}
				else if(checkSegmentRecursively(inputString, i + 1, dictWords)) {
					return true;
				} else {
					continue;
				}
			}
		}
		return false;
	}

}

class MaxHeapNode {
	int freq;
	TrieNode node;
	int index;
}

class MaxHeap {
	
	int capacity;
	MaxHeapNode[] maxHeapNodes;
	
	public MaxHeap(int capacity) {
		maxHeapNodes = new MaxHeapNode[capacity];
		this.capacity = capacity;
	}
	
	public void addMaxHeapNode(TrieNode trieNode) {
		
	}
}

class TrieNode {
	String value;
	char c;
	HashMap<Character, TrieNode> children;
	TrieNode parent;
	
	public TrieNode(char c, String value, TrieNode parent) {
		this.c = c;
		this.value = value;
		this.parent = parent;
		children = new HashMap<Character, TrieNode>();
	}
}

class Trie {
	TrieNode root;
	HashSet<TrieNode> trieNodes;
	
	public Trie() {
		root = new TrieNode('.', null, null);
		trieNodes = new HashSet<TrieNode>();
		trieNodes.add(root);
	}
	
	public TrieNode addWord(String word) {
		return addWordRecursively(word, 0, root);
	}

	private TrieNode addWordRecursively(String word, int index, TrieNode current) {
		if(word != null & index < word.length()) {
			char currentChar = word.charAt(index);
			if(current.c == currentChar) {
				if(index == word.length() - 1) {
					current.value = word;
					return current;
				} else {
					currentChar = word.charAt(index + 1);
					if(!current.children.containsKey(currentChar)) {
						current.children.put(currentChar, new TrieNode(currentChar, null, current));
					}
					return addWordRecursively(word, index + 1, current.children.get(currentChar));
				}
			} else if(current.c == '.') {
				if(!current.children.containsKey(currentChar)) {
					current.children.put(currentChar, new TrieNode(currentChar, null, current));
				}
				return addWordRecursively(word, index, current.children.get(currentChar));
			}
		} 
		return null;
	}
	
	public void traverse() {
		traverseRecursively(this.root);
	}

	private void traverseRecursively(TrieNode root2) {
		if(root2 != null) {
			root2.children.forEach((k, v) -> traverseRecursively(v));
			if(root2.value != null) {
				System.out.println(root2.value);
				return;
			}
		}
	}
}

