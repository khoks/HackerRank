package problemset1;

import java.io.*; 
import java.util.*;
// Add any extra import statements you may need here


class MatchingPairs {

  // Add any helper functions you may need here
  private void swapElements(Map<Integer, Character> tPool, int j, int k) {
    Character tmp = tPool.get(j);
    tPool.put(j, tPool.get(k));
    tPool.put(k, tmp);
  }

  int matchingPairs(String s, String t) {
    // Write your code here
    /*we will loop through the char arrays of both the strings simultaneously
      if chars match, 
        increment the pair count
      if chars dont match
        we add the non matching index to a pool for both the strings
        if we have not made 2 swaps yet
          then loop through the 2 pools to see 
            if we can find matching characters and swap count is less than 2
              increment the pair count
              incement the swap count*/
    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();
    
    int size = s.length();
    int pairCount = 0;
    int swapCount = 0;
    int i = 0;
    int j = -1;
    int k = -1;
    Map<Integer, Character> sPool = new HashMap<Integer, Character>();
    Map<Integer, Character> tPool = new HashMap<Integer, Character>();
    for(i = 0 ; i < size ; i++) {
      if(sArr[i] == tArr[i]) {
        pairCount++;
        continue;
      } else {
        sPool.put(i, sArr[i]);
        tPool.put(i, tArr[i]);
        while(swapCount<1) {
          j = -1;
          for(Map.Entry eS : sPool.entrySet()) {
            k = -1;
            
            for(Map.Entry eT : tPool.entrySet()) {
              if(eS.getValue() == eT.getValue()) {
                k = (int)eT.getKey();
                j = (int)eS.getKey();
                break;
              }
            }
            if(k > -1) {
              break;
            }
          }
          
          if(k > -1) {
            swapElements(tPool, j, k);
            swapCount++;
            pairCount++;
            if(sPool.get(k) == tPool.get(k)) {
              pairCount++;
              sPool.remove(k);
              tPool.remove(k);
            }
            sPool.remove(j);
            tPool.remove(j);
          } else {
              break;
          }
          
        }
      }
      
    }
    return pairCount - (2 - swapCount);
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
    String s_1 = "abcde";
    String t_1 = "adcbe";
    int expected_1 = 5;
    int output_1 = matchingPairs(s_1, t_1);
    check(expected_1, output_1);

    String s_2 = "abcd";
    String t_2 = "abcd";
    int expected_2 = 2;
    int output_2 = matchingPairs(s_2, t_2);
    check(expected_2, output_2);

    // Add your own test cases here
    
  }
  public static void main(String[] args) {
    new MatchingPairs().run();
  }
}