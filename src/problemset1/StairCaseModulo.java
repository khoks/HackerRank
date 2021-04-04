package problemset1;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StairCaseModulo {

    // Complete the stepPerms function below.
    static int stepPerms(int n) {
    	if(results.containsKey(n)) {
    		return results.get(n); 
    	} else {
    		results.put(n, stepPerms(n-1) + stepPerms(n-2) + stepPerms(n-3));
    		return results.get(n);
    	}
    }

    private static final Scanner scanner = new Scanner(System.in);
    
    static Map<Integer, Integer> results;

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        results = new HashMap<Integer, Integer>();
        results.put(1, 1);
        results.put(2, 2);
        results.put(3, 4);
        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = new Long(stepPerms(n)%10000000007l).intValue();
            results.put(n, res);

            System.out.println(res);
            //bufferedWriter.write(String.valueOf(res));
            //bufferedWriter.newLine();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}
