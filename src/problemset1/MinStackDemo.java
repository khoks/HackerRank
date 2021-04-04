package problemset1;

import java.util.ArrayList;
import java.util.List;

public class MinStackDemo {

	public static void main (String args[]) {
		MinStack minStack = new MinStack();
		Integer tempInt;
		int i;
		System.out.println("Pushing values");
		for(i = 0 ; i < 10 ; i ++) {
			tempInt = new Double(Math.floor(Math.random()*1000)).intValue();
			minStack.push(tempInt);
			System.out.println(tempInt + " - " + minStack.min);
		}
		System.out.println("Popping values");
		for(i = 0 ; i < 10 ; i ++) {
			tempInt = minStack.pop();
			System.out.println(tempInt + " - " + minStack.min);
		}
	}
	
}

class MinStack {
	
	Item head; 
	int min = Integer.MAX_VALUE;
	
	public MinStack() {
		head = null;
	}
	
	void push(Integer val) {
		Item temp = new Item(val);
		
		if(val < min) {
			temp.val = 2*val - min;
			min = val;
		}
		temp.next = head;
			
		 
		head = temp;
	}
	
	Integer pop() {
		Item temp = null;
		if(null != head) {
			if(head.val < min) {
				min = 2*min -head.val;
			}
			if(null != head.next) {
				temp = head;
				head = head.next;
			} else {
				temp = head;
				head = null;
			}
		}
		return (null != temp ? temp.val : null) ;
	}
}


class Item {
	int val;
	Item next;
	public Item(int val) {
		this.val = val;
		next = null;
	}
}