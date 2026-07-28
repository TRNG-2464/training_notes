/**
 * @author Wezley Singleton
 */

package com.revature.javaBasics.varargs;

public class VarArgsDriver {
	
	public static void main(String... args) {
		
		int[] myIntArray = {9, 8, 7, 6, 5};
		
		display("Kyle", myIntArray);
		display("John"); // the variable argument is optional
		display("Steve", 2,3,4,5,78,52); //another way to write varargs
		
		int[][] my2dArray = { {1, 2}, {3, 4} };
//		show(my2dArray);
	}
	
	// The variable argument MUST be last in the parameter list! Otherwise you get a compiler error!
	public static void display(String strValue, int... arr) {
		
		System.out.println(strValue);
		
		// Using a for-each (enhanced for-loop) is the easiest way to iterate through an array
		for(int val : arr) {
			System.out.println(val);
		}
	}
	
	/*
	 * We can make our variable arguments as many dimensions as desired, just add a single set of square 
	 * brackets and the ellipses to pass in a 2d array.
	 */
	public static void show(int[]... twoDimArr) {
		
		// nested for-each loops to iterate through the positions of the matrix
		for(int[] arr : twoDimArr) {
			for(int num : arr) {
				System.out.println(num);
			}
		}
	}

}
