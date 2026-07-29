package com.revature.oop.abstraction.interfaces;

/*
 * Interfaces are abstract entities in which java will
 * automatically add certain keywords to.
 * 
 * Specifically, methods (and the interface as a whole) are
 * automatically established to be abstract. Interface methods
 * are also implicitly public.
 * 
 * Variables within an interface are implicitly public, static
 * and final!
 */
public interface Aquatic {
	/*
	 * the use of the keyword 'abstract' is not required.
	 * for Interfaces. Java interfaces are  implicitly
	 * abstract for any created methods!
	 */
	public void sink();

	/*
	 * The keyword 'default' can be used with an Interface
	 * method to provide a concrete implementation within
	 * the interface.
	 */
	public default void floatOnWater() {
		System.out.println("This is floating on water");
	}
}
