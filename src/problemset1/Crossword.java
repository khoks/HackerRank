package problemset1;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Crossword {

    // Complete the crosswordPuzzle function below.
    static String[] crosswordPuzzle(String[] crossword, String words) {
    	
    	
    	
    	return null;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] crossword = new String[10];

        crossword[0] = "+-++++++++"; 
        crossword[1] = "+-++++++++" ;
        crossword[2] = "+-------++" ; 
        crossword[3] = "+-++++++++" ; 
        crossword[4] = "+-++++++++" ; 
        crossword[5] = "+------+++" ; 
        crossword[6] = "+-+++-++++" ; 
        crossword[7] = "+++++-++++" ; 
        crossword[8] = "+++++-++++" ; 
        crossword[9] = "++++++++++" ;

        String words = "AGRA;NORWAY;ENGLAND;GWALIOR";

        String[] result = crosswordPuzzle(crossword, words);

        for (int i = 0; i < result.length; i++) {
            //bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                //bufferedWriter.write("\n");
            }
        }

        //bufferedWriter.newLine();

        //bufferedWriter.close();
        
        scanner.close();
    }
}
