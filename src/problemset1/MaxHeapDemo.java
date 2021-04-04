package problemset1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxHeapDemo {

	public static void main(String[] args) {
		ArrayList<HeapNode> maxHeap = new ArrayList<HeapNode>();
		
		Path path = Paths.get("C:/Dev/temp/file.txt");
		
		try {
			
			
					
			Set<String> words = new HashSet<String>();
			HashMap<String, Integer> wordsWithFreq = new HashMap<String, Integer>();
			kMinHeap kMinHeap = new kMinHeap(7);
			words = Files.lines(path).flatMap(line -> Arrays.stream(line.split(" "))).map(word -> word.replace(".", "").replace(",", "").toLowerCase()).filter(word -> word.trim().length() > 0)
				.peek(word ->  {
					addToMaxHeap(word, maxHeap);
					
					
					wordsWithFreq.put(word, 1 + (wordsWithFreq.get(word)!=null?wordsWithFreq.get(word):0) );
					kMinHeap.addMinHeapNode(word, wordsWithFreq.get(word));
					
				}).collect(Collectors.toSet());
				
			System.out.println(kMinHeap);
			Stream.iterate(0, i -> i+1).limit(40).forEach(index -> {
				HeapNode extracted = maxHeap!= null && maxHeap.size() > 0? extraxtMaxNode(maxHeap) : new HeapNode(0, "");
				System.out.println(extracted.value + "\t\t\t " + extracted.freq);
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
	
	private static HeapNode extraxtMaxNode(ArrayList<HeapNode> maxHeap) {
		if(maxHeap!= null && maxHeap.size()>0) {
			HeapNode tmp = maxHeap.get(0);
			maxHeap.set(0,  maxHeap.get(maxHeap.size()-1));
			maxHeap.remove(maxHeap.size()-1);
			if(maxHeap.size() > 0) {
				maxHeapify(maxHeap, 0);
			}
			return tmp;
		}
		return null;
	}

	private static void maxHeapify(ArrayList<HeapNode> maxHeap, int index) {
		
		if(isLeafIndex(index, maxHeap.size())) {
			return;
		}
		if(maxHeap.get(getLeftChildIndex(index)) != null && maxHeap.get(getRightChildIndex(index)) != null) {
			if(maxHeap.get(index).freq < maxHeap.get(getLeftChildIndex(index)).freq || 
					maxHeap.get(index).freq < maxHeap.get(getRightChildIndex(index)).freq) {
				if(maxHeap.get(getLeftChildIndex(index)).freq > maxHeap.get(getRightChildIndex(index)).freq) {
					swapHeapNodes(index, getLeftChildIndex(index), maxHeap);
					maxHeapify(maxHeap, getLeftChildIndex(index));
				} else {
					swapHeapNodes(index, getRightChildIndex(index), maxHeap);
					maxHeapify(maxHeap, getRightChildIndex(index));
				}
			}
		} else if(maxHeap.get(getLeftChildIndex(index)) != null && maxHeap.get(index).freq < maxHeap.get(getLeftChildIndex(index)).freq) {
			swapHeapNodes(index, getLeftChildIndex(index), maxHeap);
			maxHeapify(maxHeap, getLeftChildIndex(index));
		} else if(maxHeap.get(getRightChildIndex(index)) != null && maxHeap.get(index).freq < maxHeap.get(getRightChildIndex(index)).freq) {
			swapHeapNodes(index, getRightChildIndex(index), maxHeap);
			maxHeapify(maxHeap, getRightChildIndex(index));
		}
		
	}

	public static void addToMaxHeap(String word, ArrayList<HeapNode> maxHeap) {
		
		boolean found = false;
		int i = 0;
		for(HeapNode node : maxHeap) {
			if(node.value.equals(word) ) {
				node.freq++;
				maxify(maxHeap, i);
				found = true;
			}
			i++;
		}
		if(!found) {
			maxHeap.add(new HeapNode(1, word));
			maxify(maxHeap, maxHeap.size()-1);
		}
	}
	
	public static void maxify(ArrayList<HeapNode> maxHeap, int index) {
		if(index > 0) {
			int parentIndex = getParentIndex(index);
			if(maxHeap.get(index).freq > maxHeap.get(parentIndex).freq) {
				swapHeapNodes(index, parentIndex, maxHeap);
				maxify(maxHeap, parentIndex);
			}
		}
	}
	
	public static void swapHeapNodes(int index, int parentIndex, ArrayList<HeapNode> maxHeap) {
		HeapNode tmp = maxHeap.get(index);
		maxHeap.set(index,  maxHeap.get(parentIndex));
		maxHeap.set(parentIndex,  tmp);
	}
	
	public static boolean isLeafIndex(int index, int size) {
		return index >= (size/2) ? true : false;
	}
	
	public static int getLeftChildIndex(int index) {
		return (index*2) + 1;
	}

	public static int getRightChildIndex(int index) {
		return (index*2) + 2;
	}
	
	public static int getParentIndex(int index) {
		return (index-1) / 2;
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
		
		boolean found = false;
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
		if(kMinHeap[getLeftChildIndex(pos)] != null && kMinHeap[getRightChildIndex(pos)] != null) {
			if(kMinHeap[pos].freq > kMinHeap[getLeftChildIndex(pos)].freq || kMinHeap[pos].freq > kMinHeap[getRightChildIndex(pos)].freq ) {
				if(kMinHeap[getLeftChildIndex(pos)].freq < kMinHeap[getRightChildIndex(pos)].freq) {
					swapNodes(pos, getLeftChildIndex(pos));
					minHeapify(getLeftChildIndex(pos));
				} else {
					swapNodes(pos, getRightChildIndex(pos));
					minHeapify(getRightChildIndex(pos));
				}
			}
		} else if(kMinHeap[getLeftChildIndex(pos)] != null && kMinHeap[pos].freq > kMinHeap[getLeftChildIndex(pos)].freq) {
			swapNodes(pos, getLeftChildIndex(pos));
			minHeapify(getLeftChildIndex(pos));
		} else if(kMinHeap[getRightChildIndex(pos)] != null && kMinHeap[pos].freq > kMinHeap[getRightChildIndex(pos)].freq) {
			swapNodes(pos, getRightChildIndex(pos));
			minHeapify(getRightChildIndex(pos));
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