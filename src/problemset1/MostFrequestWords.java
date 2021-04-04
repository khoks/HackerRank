package problemset1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MostFrequestWords {

	public static void main(String[] args) {
		
		MostFreqWords freqWords = new MostFreqWords();
		
		
		
		int i,j;
		
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		
		Integer tempInt;
		for(i = 0 ; i < 100000 ; i ++) {
			tempInt = new Double(Math.floor(Math.random()*1000)).intValue();
			m.put(tempInt, null != m.get(tempInt)?m.get(tempInt) + 1:1);
			freqWords.add(tempInt,  m.get(tempInt));
			
			
		}
		i = 0 ;
		
		SortedSet<Map.Entry<Integer, Integer>> sortedM = new TreeSet<Map.Entry<Integer, Integer>>(new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				if(o1.getValue() < o2.getValue()) {
					return 1;
				} else if(o1.getValue() == o2.getValue()){
					return 0;
				} else {
					return -1;
				}
			}
		});
		sortedM.addAll(m.entrySet());
		for(Object e : sortedM.toArray()) {
			i++;
			if(i>10)  break;
			System.out.println("Freq word = " + e.toString());
		}
		System.out.println("----------------");
		freqWords.printFreqWords();
	}

}

class Word {
	Integer val;
	Integer count;
	Word previous;
	Word next;
	Word(Integer val, Integer count) {
		this.val = val;
		previous = null;
		next = null;
		this.count = count;
	}
}

class MostFreqWords {
	Word head;
	Word leaf;
	int MAX_LENGHT = 10;
	int currentLength;
	
	public MostFreqWords() {
		currentLength = 0;
		head = null;
		leaf = null;
	}
	
	
	
	void add(Integer val, Integer count) {
		
		if(null != leaf) {
			if(count < leaf.count) {
				return;
			}
		}
		
		Word temp = new Word(val, count);
		if(head == null) {
			head = temp;
			leaf = temp;
			trimList();
			return;
		}
		
		Word tempLeaf = leaf;
		while(true) {
			
			if(null != tempLeaf && temp.val == tempLeaf.val) {
				
				tempLeaf.count = temp.count;
				break;
				
			} else if(null != tempLeaf && temp.count > tempLeaf.count) {
				tempLeaf = tempLeaf.previous;
				continue;
			} else if(null != tempLeaf) {
				if(tempLeaf == head) {
					temp.next = head;
					head.previous = temp;
					head = temp;
				} else if(tempLeaf == leaf) {
					temp.next = leaf;
					temp.previous = leaf.previous;
					leaf.previous = temp;
				} else {
					temp.next = tempLeaf;
					temp.previous = tempLeaf.previous;
					tempLeaf.previous = temp;
				}
				trimList();
				break;
			} else {
				temp.next = head;
				head.previous = temp;
				head = temp;
				trimList();
				break;
			}
		}
		
		
		
	}
	
	void trimList() {
		if(currentLength < MAX_LENGHT) {
			currentLength++;
		} else {
			leaf = leaf.previous;
			leaf.next = null;
		}
	}
	
	void printFreqWords() {
		Word temp = leaf;
		while (null != temp) {
			System.out.println("Freq word = " + temp.val + " with count = " + temp.count);
			temp = temp.previous;
		}
	}
}
