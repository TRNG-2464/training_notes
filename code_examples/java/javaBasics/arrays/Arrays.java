/**
 * @author Wezley Singleton
 */

package com.revature.javaBasics.arrays;

public class Arrays {
	
	public static void main(String[] args) {
		
		/*
		 * Arrays
		 * 
		 * An array is a group of like-typed variables that are referred to by a common name. Some things 
		 * to note about arrays in Java include:
		 * 
		 * 		- Arrays are of a fixed size. This means that once they are instantiated (yes, arrays are 
		 * 		  objects) their size can not be changed. 
		 * 
		 * 		- The variables in an array are ordered and each have an index beginning from 0. 
		 * 
		 * 		- The size of an array must be specified by an int value and not long or short.
		 * 
		 * 		- Can be multi-dimensional (an array of arrays)
		 * 
		 * 		- The length of an array can be found by invoking the 'length' member on any array
		 */
		
		// Arrays can be declared in one of two ways:
		int myIntArray[]; // this a valid array declaration
		int[] myOtherIntArray; // this is also a valid array declaration
		
		// Likewise, there are a few ways to instantiate arrays:
		myIntArray = new int[5]; // valid
		myOtherIntArray = new int[] {6, 4, 2, 5, 1, 9}; // values
		
		// array literals by themselves need to be included on the same line as the array's declaration
		char[] myCharArray = {'a', 'b', 'c'};
		
		// throws ArrayIndexOutOfBoundsException (array indexes are zero-based and of a fixed size!)
		// myOtherIntArray[0] - 6
		// myOtherIntArray[1] - 4
		// myOtherIntArray[2] - 2
//		myOtherIntArray[10] = 10; // index 6 and above are out of bounds for this array
		
		// The length field of the all arrays is actually a final member (constant, cannot be changed)
//		myIntArray.length = 8;
		
		// Use a standard for loop to iterate through the loop
		for(int i = 0; i < myIntArray.length; i++) {
			System.out.println(myIntArray[i]);
		}
		
		System.out.println("---------");
		
		// A for-each loop can also be used to iterate through an array
		for(Integer number : myIntArray) {	// even though myIntArray is an array of ints, they will be autoboxed into the Integer wrapper class
			System.out.println(number);
		}
		
		System.out.println("----------");
		
		// We can make arrays with objects, like Strings, as well
		String[] names = new String[3];
		names[0] = "Adam";
		names[1] = "Brittany";
		names[2] = "Charles";
		
		for(String name : names) {
			System.out.println(name);
		}
		
		System.out.println("---------");
		
		// multi-dimensional arrays!
		int[][] the2dArray = new int[3][3];
		int[][] my2dArray = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
		
		int value = my2dArray[1][2]; // grab the array within the 2d array at position 1, then grab the value at position 2 within it 
		System.out.println(value);
		System.out.println(my2dArray[1]); // prints out an object memory reference, arrays are objects!
		
		// Use nested loop structures to iterate through the layers of multi-dimensional arrays
		for(int i = 0; i < my2dArray.length; i++) {
			
			for(int j = 0; j < my2dArray[i].length; j++) {
				System.out.println("Value at position " + i + ", " + j + ": "+ my2dArray[i][j]);
			}
			
		}
		
		// Likewise, for-each loops can be used to do the same (notice the type of each element in the outer loop)
		for(int[] intArray : my2dArray) {
			for(int num : intArray) {
				System.out.println(num);
			}
		}
		
		System.out.println("-----------");
		
		// Arrays can be passed as arguments
		workWithArray(myOtherIntArray);
		
	} 
	
	public static void workWithArray(int[] arr) {
		for(Integer number : arr) {
			System.out.println(number);
		}
	}

}
