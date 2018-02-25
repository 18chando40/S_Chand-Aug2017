package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import polymorphism.Animal;
import polymorphism.B;
import polymorphism.C;
import polymorphism.Pig;

public class Main {
	
	public static void main(String[] args) {
		//consumerTest();
		streamTest();
		//iteratorTest();
		//polymorphismTest();
	}
	
	public static void consumerTest() {
	    IntConsumer intCon = x -> System.out.println(x);
	    intCon.accept(7);
		
	    Consumer<Integer> c = x -> System.out.println(x);
	    c.accept(7);
	}
	
	public static void streamTest() {
		int SIDE = 10;
		
	    int[][] intStream = new int[SIDE][SIDE];
	    IntStream.range(0, SIDE).forEach(row -> {
	        IntStream.range(0, SIDE).forEach(col -> intStream[row][col] = (row+1) *(col + 1));
	    });
	    
	    Stream.of(intStream).forEach(row -> {
	    	IntStream.of(row).forEach(col -> System.out.print(col + " "));
	    	System.out.println();
	    });
	    
	    System.out.println("\nWith for Loop:");
	    int[][] forLoop = new int[SIDE][SIDE];
		for(int row = 0; row < forLoop.length; row ++) {
			for(int col = 0; col < forLoop[row].length; col++) {
				forLoop[row][col] = (row+1) *(col + 1);
			}
		}
	    
		for(int[] row: forLoop) {
			for(int col: row) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
	}
	
	public static void iteratorTest() {
		Random r = new Random();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i < 21; i ++) {
			list.add(r.nextInt(i));
		}
		
		for(int i : list) {
			System.out.println(i);
		}
		list.add(1);
		list.add(null);
		list.add(0);
		
		Iterator<Integer> i = list.iterator();
		System.out.println(i.next());
		System.out.println(i.next());
	}
	
	public static void polymorphismTest() {
		Pig p = new Pig();
		((Animal) p).method1();
		
		C c = new C(); 
		c.firstMethod(); 
		c.secondMethod(); 
		c.thirdMethod();
		
		B b = new C();
		b.thirdMethod();
	}
}
