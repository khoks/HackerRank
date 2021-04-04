package problemset3;

import java.io.*; 
import java.util.*;
// Add any extra import statements you may need here


public class MedianStream {

  // Add any helper functions you may need here
  static class MinHeap {
    int[] heapArr;
    int size;
    int capacity;
    
    public MinHeap(int capacity) {
      this.capacity = capacity;
      this.size = 0;
      this.heapArr = new int[this.capacity];
    }
    
    public void addNode(int value) {
      if(size < capacity) {
        heapArr[size] = value;
        buildMinHeap(size);
        size++;
      } else {
        if(value > heapArr[0]) {
          heapArr[0] = value;
          minHeapify(0);
        }
      }
    }
    
    private void buildMinHeap(int index) {
      if(index > 0) {
        if(heapArr[index] < heapArr[parentIndex(index)]) {
          swap(index, parentIndex(index));
          buildMinHeap(parentIndex(index));
        }
      }
    }
    
    private void minHeapify(int index) {
      if(isLeaf(index)) {
        return;
      }
      if(leftChildIndex(index) < this.size && rightChildIndex(index) < this.size) {
    	  if(heapArr[index] > heapArr[leftChildIndex(index)] || heapArr[index] > heapArr[rightChildIndex(index)]) {
	        if(heapArr[leftChildIndex(index)] < heapArr[rightChildIndex(index)]) {
	          swap(index, leftChildIndex(index));
	          minHeapify(leftChildIndex(index));
	        } else {
	          swap(index, rightChildIndex(index));
	          minHeapify(rightChildIndex(index));
	        }
	      }
      } else if(leftChildIndex(index) < this.size && heapArr[index] > heapArr[leftChildIndex(index)]) {
    	  swap(index, leftChildIndex(index));
          minHeapify(leftChildIndex(index));
      }
      
    }
    
    public void increaseCapacity(int capacity) {
      this.capacity = capacity;
      int[] tmp = new int[this.capacity];
      for(int i = 0 ; i < this.size ; i++) {
        tmp[i] = heapArr[i];
      }
      heapArr = tmp;
    }
    
    private int leftChildIndex(int index) {
      return (index*2) + 1;
    }
    
    private int rightChildIndex(int index) {
      return (index*2) + 2;
    }
    
    private int parentIndex(int index) {
      return (index-1) /2;
    }
    
    private boolean isLeaf(int index) {
      return (index >= size/2);
    }
    
    private void swap(int pos1, int pos2) {
      int tmp = heapArr[pos1];
      heapArr[pos1] = heapArr[pos2];
      heapArr[pos2] = tmp;
    }
  }

  int[] findMedian(int[] arr) {
    // Write your code here
    int[] result = new int[arr.length];
    MinHeap minHeap = new MinHeap(2);
    for(int i = 0 ; i < arr.length ; i++) {
      if(i == 0) {
        result[i] = arr[i];
        minHeap.addNode(arr[i]);
      } else if(i == 1) {
        result[i] = (arr[i]+arr[i-1])/2;
        minHeap.addNode(arr[i]);
      } else {
        minHeap.increaseCapacity(((i+1)/2) + 1);
        minHeap.addNode(arr[i]);
        if((i+1)%2 == 0) {
          result[i] = ( minHeap.heapArr[0] + (minHeap.heapArr[1] < minHeap.heapArr[2] ? minHeap.heapArr[1] : minHeap.heapArr[2]) ) / 2;
        } else {
          result[i] = minHeap.heapArr[0];
        }
      } 
    }
    return result;
  }












  // These are the tests we use to determine if the solution is correct.
  // You can add your own at the bottom, but they are otherwise not editable!
  int test_case_number = 1;
  void check(int[] expected, int[] output) {
    int expected_size = expected.length; 
    int output_size = output.length; 
    boolean result = true; 
    if (expected_size != output_size) {
      result = false;
    }
    for (int i = 0; i < Math.min(expected_size, output_size); i++) {
      result &= (output[i] == expected[i]);
    }
    char rightTick = '\u2713';
    char wrongTick = '\u2717';
    if (result) {
      System.out.println(rightTick + " Test #" + test_case_number);  
    }
    else {
      System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
      printIntegerArray(expected); 
      System.out.print(" Your output: ");
      printIntegerArray(output);
      System.out.println();
    }
    test_case_number++;
  }
  void printIntegerArray(int[] arr) {
    int len = arr.length; 
    System.out.print("[");
    for(int i = 0; i < len; i++) {
      if (i != 0) {
        System.out.print(", ");
      }
      System.out.print(arr[i]);
    }
    System.out.print("]");
  }
  public void run() {
    

    int[] arr_2 = {2, 4, 7, 1, 5, 3};
    int[] expected_2 = {2, 3, 4, 3, 4, 3};
    int[] output_2 = findMedian(arr_2);
    check(expected_2, output_2);
  
    // Add your own test cases here
    
  }
  public static void main(String[] args) {
    new MedianStream().run();
  }
}
