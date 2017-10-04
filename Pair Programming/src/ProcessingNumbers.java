//Programmer 1 Shivneel Chand and Programmer 2 James Lee partner code.

import java.util.*;
public class ProcessingNumbers {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("How many terms are you going to write?");
		double numberOfTerms = userInput.nextDouble();
		double maxEven = -1;
		double max = 0;
		double min = 0;
		double evenSum = 0;
		boolean isFirstNumber = true;
		for(int i = 1; i <= numberOfTerms; i++) {
			System.out.println("Enter number " + i + ": ");
			double num = userInput.nextDouble();
			if(isFirstNumber) {
				min = num;
				max= num;
				isFirstNumber = false;
			}else {
				if(num > max) {
					max = num;
				}
				if(num < min) {
					min = num;
				}
				if(num %2 == 0) {
					if(num > maxEven) {
						maxEven = num;
					}
				evenSum += num;
				}
			}
		}
		System.out.println("The lowest number is: " + min + " The greatest number is: " + max);
		System.out.println("The sum of even is: " + evenSum);
		if(maxEven == -1) {
			System.out.println("Your list only had odd numbers.");
		}else {
			System.out.println("The largest even number is: " + maxEven);
		}
		
	}
}