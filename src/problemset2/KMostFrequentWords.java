package problemset2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class KMostFrequentWords {

	public static void main(String[] args) {
		
		
		Path path = Paths.get("file.txt");
		
		try {
		
			HashMap<String, Integer> wordsWithFreq = new HashMap<String, Integer>();
			kMinHeap kMinHeap = new kMinHeap(7);
			Files.lines(path).flatMap(line -> Arrays.stream(line.split(" "))).map(word -> word.toLowerCase()).filter(word -> word.trim().length() > 0)
				.forEach(word ->  {
					wordsWithFreq.put(word, 1 + (wordsWithFreq.get(word)!=null?wordsWithFreq.get(word):0) );
					kMinHeap.addMinHeapNode(word, wordsWithFreq.get(word));
				});
	
			Stream.iterate(0,  i -> i + 1).limit(7).forEach(index -> {
				HeapNode extracted = kMinHeap.size > 0 ? kMinHeap.extractMinNode() : new HeapNode(0, "");
				System.out.println(extracted.value + "\t\t\t " + extracted.freq);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}

class HeapNode {
	int freq;
	String value;
	
	public HeapNode(int freq, String value) {
		this.value = value;
		this.freq = freq;
	}
}

class kMinHeap {
	HeapNode[] kMinHeap;
	int capacity;
	int size;
	public kMinHeap(int capacity) {
		this.capacity = capacity;
		this.kMinHeap = new HeapNode[capacity];
		this.size = 0;
	}
	
	public HeapNode extractMinNode() {
		HeapNode tmp = size > 0 ? kMinHeap[0] : new HeapNode(0, "");
		kMinHeap[0] = kMinHeap[size-1];
		kMinHeap[size-1] = null;
		size--;
		minHeapify(0);
		return tmp;
	}
	
	public void addMinHeapNode(String value, int freq) {
		
		int i = 0;
		for(HeapNode node : kMinHeap) {
			if(node == null) {
				i++;
				continue;
			}
			if(node.value.equals(value)) {
				node.freq = freq;
				minHeapify(i);
				return;
			}
			i++;
		}
		
		if(size == capacity && freq > kMinHeap[0].freq) {
			kMinHeap[0] = new HeapNode(freq, value);
			minHeapify(0);
		} else if(size != capacity) {
			kMinHeap[size] = new HeapNode(freq, value);
			size++;
			minBuild(size-1);
			
		}
	}
	
	private void minHeapify(int pos) {
		if(isLeafIndex(pos)) {
			return;
		}
		int leftPos = getLeftChildIndex(pos);
		int rightPos = getRightChildIndex(pos);
		if(leftPos < size && rightPos < size && kMinHeap[leftPos] != null && kMinHeap[rightPos] != null) {
			if(kMinHeap[pos].freq > kMinHeap[leftPos].freq || kMinHeap[pos].freq > kMinHeap[rightPos].freq ) {
				if(kMinHeap[leftPos].freq < kMinHeap[rightPos].freq) {
					swapNodes(pos, leftPos);
					minHeapify(leftPos);
				} else {
					swapNodes(pos, rightPos);
					minHeapify(rightPos);
				}
			}
		} else if(leftPos < size && kMinHeap[leftPos] != null && kMinHeap[pos].freq > kMinHeap[leftPos].freq) {
			swapNodes(pos, leftPos);
			minHeapify(leftPos);
		} else if(rightPos < size && kMinHeap[rightPos] != null && kMinHeap[pos].freq > kMinHeap[rightPos].freq) {
			swapNodes(pos, rightPos);
			minHeapify(rightPos);
		}
	}
	
	private void minBuild(int pos) {
		if(getParentIndex(pos) >= 0 && kMinHeap[pos].freq < kMinHeap[getParentIndex(pos)].freq) {
			swapNodes(pos, getParentIndex(pos));
			minBuild(getParentIndex(pos));
		}
	}
	
	
	public int getRootFreq() {
		if(size == 0) {
			return 0;
		} else {
			return kMinHeap[0].freq;
		}
	}
	
	private int getLeftChildIndex(int pos) {
		return (pos*2) + 1;
	}
	
	private int getRightChildIndex(int pos) {
		return (pos*2) + 2;
	}
	
	private int getParentIndex(int pos) {
		return (pos-1)/2;
	}
	
	private boolean isLeafIndex(int pos) {
		return pos >= (this.size/2) ? true : false;
	}
	
	private void swapNodes(int pos1, int pos2) {
		HeapNode tmp = kMinHeap[pos1];
		kMinHeap[pos1] = kMinHeap[pos2];
		kMinHeap[pos2] = tmp;
	}
}