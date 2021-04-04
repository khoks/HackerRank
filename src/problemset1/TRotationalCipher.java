package problemset1;

import java.util.*;
//Add any extra import statements you may need here


public class TRotationalCipher {

// Add any helper functions you may need here
private char rotateChar(char c, int factor) {
 if((c >= 'a' && c <= 'z')) {
   factor = factor % 26;
   c = (char)((int)c + factor);
   if( c > 'z') {
     c = (char)((int)c - 26);
   }
 } else if((c >= 'A' && c <= 'Z')) {
   factor = factor % 26;
   c = (char)((int)c + factor);
   if( c > 'Z') {
     c = (char)((int)c - 26);
   }
 }else if(c >= '0' && c <= '9') {
   factor = factor % 10;
   c = (char)((char)c + factor);
   if(c > '9') {
     c = (char)((int)c - 10);
   }
 }
 return c;
}

String rotationalCipher(String input, int rotationFactor) {
    // Write your code here
    char[] chars = input.toCharArray();
    char[] rotatedChars = new char[input.length()];
    int i = 0;
    for(char c : chars) {
      rotatedChars[i] = rotateChar(c, rotationFactor);
      i++;
    }
    return new String(rotatedChars);
    
   
  }











// These are the tests we use to determine if the solution is correct.
// You can add your own at the bottom, but they are otherwise not editable!
int test_case_number = 1;
void check(String expected, String output) {
 boolean result = (expected.equals(output));
 char rightTick = '\u2713';
 char wrongTick = '\u2717';
 if (result) {
   System.out.println(rightTick + " Test #" + test_case_number);
 }
 else {
   System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
   printString(expected); 
   System.out.print(" Your output: ");
   printString(output);
   System.out.println();
 }
 test_case_number++;
}
void printString(String str) {
 System.out.print("[\"" + str + "\"]");
}

public void run() {
 String input_1 = "All-convoYs-9-be:Alert1.";
 int rotationFactor_1 = 4;
 String expected_1 = "Epp-gsrzsCw-3-fi:Epivx5.";
 String output_1 = rotationalCipher(input_1, rotationFactor_1);
 check(expected_1, output_1);

 String input_2 = "abcdZXYzxy-999.@";
 int rotationFactor_2 = 200;
 String expected_2 = "stuvRPQrpq-999.@";
 String output_2 = rotationalCipher(input_2, rotationFactor_2);
 check(expected_2, output_2);

 // Add your own test cases here
 
}

public static void main(String[] args) {
 new TRotationalCipher().run();
}
}