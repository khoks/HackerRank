package problemset1;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Test4 {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();  
	    
	    pq.add(3);
	    pq.add(1);
	    pq.add(7);
	    pq.add(5);
	    pq.add(9);
	    pq.add(0);
	    pq.add(2);
	    pq.add(5);
	    
	    Integer tmp;
	    
	    Iterator it = pq.iterator();
	    
	    
	    while(it.hasNext()) {
	    	System.out.println(it.next());
	    }
	    
	    while(true) {
	    	tmp = pq.poll();
	    	if(tmp== null) {
	    		break;
	    	}
	    	System.out.println(tmp);
	    }

	}

}
