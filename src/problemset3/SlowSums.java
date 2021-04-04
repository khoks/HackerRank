package problemset3;

import java.io.*; 
import java.util.*;
// Add any extra import statements you may need here
import java.util.stream.*;

public class SlowSums {

  // Add any helper functions you may need here
  

  int getTotalTime(int[] arr) {
    // Write your code here
    int countPenalty = 0;
    int max1 = Integer.MIN_VALUE;
    int max2 = Integer.MIN_VALUE;
    int max1Pos = -1;
    int max2Pos = -1;
    int i = 0;
    List<Integer> workingArr = Arrays.stream(arr).boxed().collect(Collectors.toList());
    while(workingArr.size() > 1) {
      max1 = Integer.MIN_VALUE;
      max2 = Integer.MIN_VALUE;
      max1Pos = -1;
      max2Pos = -1;
      for(i = 0 ; i < workingArr.size() ; i++) {
        if(arr[i] > max1) {
          max2 = max1;
          max2Pos = max1Pos;
          max1 = arr[i];
          max1Pos = i;
        } else if(arr[i] > max2) {
          max2 = arr[i];
          max2Pos = i;
        }
      }
      countPenalty += max1 + max2;
      workingArr.set(max1Pos, max1+max2);
      workingArr.remove(max2Pos);
    }
    return countPenalty;
  }












  // These are the tests we use to determine if the solution is correct.
  // You can add your own at the bottom, but they are otherwise not editable!
  int test_case_number = 1;
  void check(int expected, int output) {
    boolean result = (expected == output);
    char rightTick = '\u2713';
    char wrongTick = '\u2717';
    if (result) {
      System.out.println(rightTick + " Test #" + test_case_number);
    }
    else {
      System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
      printInteger(expected); 
      System.out.print(" Your output: ");
      printInteger(output);
      System.out.println();
    }
    test_case_number++;
  }
  void printInteger(int n) {
    System.out.print("[" + n + "]");
  }
  public void run() {
    int[] arr_1 = {4, 2, 1, 3};
    int expected_1 = 26;
    int output_1 = getTotalTime(arr_1);
    check(expected_1, output_1);

    int[] arr_2 = {2, 3, 9, 8, 4};
    int expected_2 = 88;
    int output_2 = getTotalTime(arr_2);
    check(expected_2, output_2);
  
    // Add your own test cases here
    
  }
  public static void main(String[] args) {
    new SlowSums().run();
  }
}