package problemset2;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;

public class RoadsAndLibraries {

    // Complete the roadsAndLibraries function below.
	static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if(c_lib < c_road) {
            return n*c_lib;
        }
        
        HashMap<Integer, ArrayList<Integer>> citiesMap = new HashMap<Integer, ArrayList<Integer>>();
        
        IntStream.rangeClosed(1,n).forEach(i -> {
        	citiesMap.put(i, new ArrayList<Integer>());
        	citiesMap.get(i).add(i);
        });
        
        for(int[] road : cities) {
        	if(citiesMap.get(road[0]) != citiesMap.get(road[1])) {
        		citiesMap.get(road[0]).addAll(citiesMap.get(road[1]));
            	
            	citiesMap.get(road[1]).forEach(city -> citiesMap.put(city, citiesMap.get(road[0])));
        	}
        }
        long cost = 0;
        for(ArrayList<Integer> component : citiesMap.values()) {
        	if(component.size() > 0) {
        		cost = cost + (component.size()-1)*c_road;
            	cost = cost + c_lib;
            	component.removeIf(i -> true);
        	}	
        }
        return cost;
    }
    
    

    //private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = 3;

        int m = 3;

        int c_lib = 2;

        int c_road = 1;

        int[][] cities = new int[][] {{1,2},{3,1},{2,3}};

        

        long result = roadsAndLibraries(n, c_lib, c_road, cities);
        System.out.println(result);

    }
}
