/**
 * @author William Gentry
 */

package com.revature.javaBasics.constructors;

/* Don't worry too much about the keyword 'extends' here
 * this is part of OOP (specifically inheritance) and will
 * be covered in more detail later...
 */
public class B extends A{

	private int myNumber;
	
	public B() {
		System.out.println("Printing from inside B's no-arg constructor");
	}
	
	public B(int myNumber) {
		super(myNumber);
		System.out.println("Inside the 1-arg constructor of B");
		this.myNumber = myNumber;
	}
	
}
