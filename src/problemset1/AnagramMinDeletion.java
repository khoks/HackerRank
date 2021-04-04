package problemset1;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AnagramMinDeletion {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int[] freq = new int[26];
        a.chars().forEach(c -> freq[c-97]++);
        b.chars().forEach(c -> freq[c-97]--);
        return Arrays.stream(freq).map(Math::abs).sum();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        //bufferedWriter.write(String.valueOf(res));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}

