package problemset1;

import java.io.*; 
import java.util.*;
// Add any extra import statements you may need here


public class MinimumLengthSubsString {

  // Add any helper functions you may need here
  

  int minLengthSubstring(String s, String t) {
    // Write your code here
    /*start looping the first string from first character
      copy the second string
      loop the first string from current index
        search for the current character of first string in copied second string
        if found
          delete the found character from copied second string
          mark the current index as the beginning of the substring if not marked yet
          if the copied second string is 0, then we found the substring within the first string
            check if it is the minimum length, and store in minimum variable if needed
            break this loop
      increment the current index to marked index + 1*/
    int minSubStringLength = s.length();
    char[] sArr = s.toCharArray();
    String tCopy = null;
    int markedIndex = 0;
    int tmpSubStringLength = 0;
    for(int i = 0 ; i < s.length() ; i++) {
      tCopy = new String(t.toCharArray());
      markedIndex = -1;
      for(int j = i ; j < s.length() ; j++) {
	      if(markedIndex > -1) {
	    	  tmpSubStringLength++;
	      }
		  if(tCopy.indexOf(sArr[j]) > -1 ) {
			  tCopy = tCopy.replaceFirst(String.valueOf(sArr[j]), "");
	      
		      if (markedIndex < 0) {
		        markedIndex = j;
		        tmpSubStringLength++;
		      }
		      if(tCopy.length() == 0) {
		        if(minSubStringLength > tmpSubStringLength) {
		          minSubStringLength = tmpSubStringLength;
		        }
		        break;
		      }
		  }
        
        int[][] ii = new int[10][2];
        for(int[] iii : ii) {
        	
        }
        List<Integer> connectedComponents = new ArrayList<Integer>();
        connectedComponents.stream().map(iii -> iii*2);
      }
      i = markedIndex > -1 ? markedIndex + 1 : i;
    }
    return minSubStringLength < s.length() ? minSubStringLength : -1;
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
  public void run() throws IOException {
    String s_1 = "dcbefebce";
    String t_1 = "fd";
    int expected_1 = 5;
    int output_1 = minLengthSubstring(s_1, t_1);
    check(expected_1, output_1);

    String s_2 = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
    String t_2 = "cbccfafebccdccebdd";
    int expected_2 = -1;
    int output_2 = minLengthSubstring(s_2, t_2);
    check(expected_2, output_2);

    // Add your own test cases here
    
  }
  public static void main(String[] args) throws IOException {
    new MinimumLengthSubsString().run();
  }
}