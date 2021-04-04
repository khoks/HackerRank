package problemset2;

import java.io.*;
import java.util.*;

public class QueuingUsingTwoStacks {
    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        ArrayDeque<String> deque = new ArrayDeque<String>();
        
        int q = Integer.parseInt(sc.nextLine());
        String tmp;
        for(int i = 0 ; i < q ; i++){
            
            tmp = sc.nextLine();
            
            if(tmp.startsWith("1")) {
                deque.add(tmp.split(" ")[1]);
            } else if(tmp.startsWith("2")) {
                deque.poll();
            } else if(tmp.startsWith("3")){
                System.out.println(deque.peek());
            }
            
        }
        sc.close();
        
        ArrayList<String> as = new ArrayList<String>();
        
        
    }
}