/**
 * @author Andrew Crenwelge
 */

package com.revature.javaBasics.constructors;

public class Chaining {

	public static void main(String args[]) {
		// even though we invoke the no-args constructor, 
		// we eventually get to the 2-arg constructor because of chaining
		new Chaining();
	}
	
	Chaining() {
		// here, "this" refers to other constructors of the current class 
		this("hello"); // other constructor call must be first line in the constructor
		System.out.println("inside no args!!!");
	}
	
	Chaining(String s1) {
		this(s1,"world");
		// no output here
	}
	
	Chaining(String s1, String s2) {
		System.out.println("2 args: " + s1 + " and " + s2);
		System.out.println("2-arg constructor finished!");
	}

}
