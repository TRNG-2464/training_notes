/**
 * @author William Gentry
 */

package com.revature.javaBasics.staticexample;

public class StaticExample {
	
	private int myInstanceInt;
	public static int myStaticInt;
	
	public void increment() {
		this.myInstanceInt++;
		myStaticInt++;
	}

//	 public static void incrementStatic(){
//	 	myInstanceInt++;	// Instance variables exist at an object/instance level
//	 	myStaticInt++;
//	 }
	
	public int getMyInstanceInt() {
		return this.myInstanceInt;
	}
	
	public static int getMyStaticInt() {
		return myStaticInt;
	}


	public static void main(String... args) {
		StaticExample.myStaticInt = 1;
		System.out.println( StaticExample.getMyStaticInt() );
	}
}
