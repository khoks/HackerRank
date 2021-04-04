package problemset2;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RecursiveDigitSum {

    // Complete the superDigit function below.
    /*static int superDigit1(String n, int k) {
        String concat = "";
        
		
		for(int i = 0 ; i < k ; i++) 
		{ 
			concat = concat + n; 
		}
		 
        concat = (String.valueOf(Integer.parseInt(n)* ((double)k) ));
        concat = concat.split("\\.")[0];
        
        int[] intArr = new int[concat.length()];
        int i = 0;
        for(char c : concat.toCharArray()) {
            intArr[i] = c - '0';
            i++;
        }
        return superDigitRec(intArr);
    }
    private static int superDigitRec1(int[] cArr) {
        if(cArr.length == 1) {
            return cArr[0];
        }
        
        String result = "";
        int carry = 0;
        
        for(int i = cArr.length - 1 ; i > 0 ; i--) {
            int sum = carry + ( cArr[i-1] + cArr[i] );
            
            result = String.valueOf(sum%10) + result;
            carry = sum >= 10 ? (sum - sum%10)/10 : 0;
        }
        if(carry > 0) {
            result = String.valueOf(carry) + result;
        }
        return superDigitRec(result.chars().map(c -> c-'0').toArray());
    }*/
    
    static int superDigit(String n, int k) {
        String concat = "";
        
        concat = (String.valueOf(Long.parseLong(n)* ((long)k) ));
        concat = concat.split("\\.")[0];
        
        int[] intArr = new int[concat.length()];
        int i = 0;
        for(char c : concat.toCharArray()) {
            intArr[i] = c - '0';
            i++;
        }
        return superDigitRec(intArr);
    }
    private static int superDigitRec(int[] cArr) {
        if(cArr.length == 1) {
            return cArr[0];
        }
        int sum = 0;
        for(int i = cArr.length - 1 ; i >= 0 ; i--) {
            sum = sum + cArr[i] ;
        }
        String result = String.valueOf(sum);
        return superDigitRec(result.chars().map(c -> c-'0').toArray());
    }



    public static void main(String[] args) throws IOException {


        int result = superDigit("861568688536788", 100000);

        System.out.println(result);
    }
}
