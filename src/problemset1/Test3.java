package problemset1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {

	
	public static void main(String[] ars) {
		Character c1 = 'a';
	    Character c2 = 'a';
	    System.out.println(c1 == c2);
	    String s = "asdasd";
	    System.out.println(s.substring(0,1));
	    s.replaceFirst(String.valueOf(c1), "");
	    
	    String[] ss = new String[] {"sad", "wqe", "zxc"};
	    char[][] cc = new char[ss.length][ss[0].length()];
	    for(int i = 0 ; i < ss.length ; i++) {
	    	for(int j = 0 ; j < ss[0].length() ; j++) {
	    		cc[i][j] = ss[i].toCharArray()[j];
	    		System.out.println(cc[i][j]);
	    	}
	    }
	    
	    System.out.println((int)'1' - (int)'0');
	    
	    StringBuilder sb = new StringBuilder();
	    String sss = "213213";
	    sss.chars().map( c -> c-'0').toArray();
	    Double a = 19d;
	    System.out.println(Math.floor(a/10));
	    BigInteger b1 = new BigInteger("12321321321312312312312312");
	    BigInteger b2 = new BigInteger("12321321321312312312312312");
	    System.out.println(b1.multiply(b2).toString());
	    
	    LinkedList<String> ll = new LinkedList<String>();
	    
	    
	    System.out.println("sadasdsadasdadas");
	    Stream.iterate(0, i -> i+1).limit(10).forEach(System.out::println);
	    
	    HashMap<String, String> asd = new HashMap<String, String>();
	    
	    
	    ArrayList<String> asdsa = new ArrayList<String>();
	    List<Integer> asdasd = Arrays.asList();
	    Object i;
	    
	    
	    int[] arrr = new int[] {1,2,4,7};
	    List<Integer> working = Arrays.stream(arrr).mapToObj(iii -> (Integer)iii).collect(Collectors.toList()); 
	    
	    Collections.sort(working, new Comparator<Integer>() {
	    	@Override
	    	public int compare(Integer o1, Integer o2) {
	    		return 1;
	    	}
	    });
	    
	    working.sort(new Comparator<Integer>() {
	    	@Override
	    	public int compare(Integer i1, Integer i2) {
	    		return 0;
	    	}
	    });
	    
	    
	    
	    
	    HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	    hmap.entrySet().forEach(e -> System.out.println(e.getKey() + e.getValue()));
	    
	    hmap.put(1,  "a");
	    
	    hmap.forEach((k,v) -> {
	    	System.out.println(k + v);
	    	v = "b";
	    });
	    
	    hmap.entrySet().stream().filter(e -> e.getKey() != 1).collect(Collectors.toMap(Map.Entry::getKey,  Map.Entry::getValue, (o1, o2)->o2, LinkedHashMap::new));
	    
	    
	    HashSet<Integer> hset = new HashSet<Integer>();
	    
	    
	}
	
	
}
