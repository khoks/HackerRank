package problemset2;

import java.io.*; 
import java.util.*;
// Add any extra import statements you may need here


public class LargestTripleProducts {

  // Add any helper functions you may need here
  static class MinHeap {
    int[] heapArr;
    int capacity;
    int size;
    public MinHeap(int capacity) {
      this.capacity = capacity;
      this.size = 0;
      this.heapArr = new int[this.capacity];
    }
    
    public void addNode(int value) {
      if(size < capacity) {
        heapArr[size] = value;
        buildHeap(size);
        size++;
      } else {
        if(value > heapArr[0]) {
          heapArr[0] = value;
          minHeapify(0);
        }
      }
    }
    
    public void buildHeap(int index) {
      if(parentIndex(index) > -1) {
        if(heapArr[index] < heapArr[parentIndex(index)]) {
          swap(index, parentIndex(index));
          buildHeap(parentIndex(index));
        }
      }
    }
    
    public void minHeapify(int index) {
      if(isLeafNode(index)) {
        return;
      }
      
      if(heapArr[index] > heapArr[leftChildIndex(index)] || heapArr[index] > heapArr[rightChildIndex(index)]) {
        if(heapArr[leftChildIndex(index)] < heapArr[rightChildIndex(index)]) {
          swap(index, leftChildIndex(index));
          minHeapify(leftChildIndex(index));
        } else {
          swap(index, rightChildIndex(index));
          minHeapify(rightChildIndex(index));
        }
      }
    }
    
    public int leftChildIndex(int index) {
      return (index*2) + 1;
    }
    
    public int rightChildIndex(int index) {
      return (index*2) + 2;
    }
    
    public int parentIndex(int index) {
      return (index-1)/2;
    }
    
    public boolean isLeafNode(int index) {
      return index >= (size/2);
    }
    
    public void swap(int pos1, int pos2) {
      int tmp = heapArr[pos1];
      heapArr[pos1] = heapArr[pos2];
      heapArr[pos2] = tmp;
    }
  }

  int[] findMaxProduct(int[] arr) {
    // Write your code here
    MinHeap minHeap = new MinHeap(3);
    int[] result = new int[arr.length];
    for(int i = 0 ; i < arr.length ; i++) {
      minHeap.addNode(arr[i]);
      if(i < 2) {
        result[i] = -1;
      } else {
        result[i] = arr[i]*arr[i-1]*arr[i-2];
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
	    int[] expected_2 = {-1, -1, 56, 56, 140, 140};
	    int[] output_2 = findMaxProduct(arr_2);
	    check(expected_2, output_2);
	  
	  int[] arr_1 = {1, 2, 3, 4, 5};
    int[] expected_1 = {-1, -1, 6, 24, 60};
    int[] output_1 = findMaxProduct(arr_1);
    check(expected_1, output_1);
    
    
  
    // Add your own test cases here
    
  }
  
  public static void main(String[] args) {
    new LargestTripleProducts().run();
  }
}
