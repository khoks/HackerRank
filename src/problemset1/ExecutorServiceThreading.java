package problemset1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExecutorServiceThreading {

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(10);
		
		CompletionService exC = new ExecutorCompletionService(ex);

		Stream.iterate(100, i -> i+1).limit(199).map(TestClass::new).forEach(exC::submit);
		
		IntStream.rangeClosed(0, 99).forEach(i -> {
			try {
				System.out.println((String)exC.take().get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
				
		//Set<Future<String>> fs = new HashSet<Future<String>>();
		//List<Future<String>> listOfFutures = IntStream.rangeClosed(0, 99).mapToObj(TestClass::new).map(i -> ex.submit(i) ).collect(Collectors.toList());

		
		ex.shutdown();
	}

}

class TestClass implements Callable<String> {
	
	Integer i;
	
	public TestClass(Integer i) {
		this.i = i;
	}
	
	@Override
	public String call() {
		
		//System.out.println(this.i);
		return "yo" + this.i;
	}
}