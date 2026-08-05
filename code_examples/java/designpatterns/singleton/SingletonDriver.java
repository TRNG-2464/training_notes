/**
 * @author Wezley Singleton
 */

package com.revature.designpatterns.singleton;

public class SingletonDriver {
	
	public static void main(String[] args) {

		System.out.println("+-------------------------------------------------------------+");
		System.out.println("+           SINGLETON DESIGN PATTERN IMPLEMENTATIONS          +");

		// LAZILY-INITIALIZED SINGLETON
		System.out.println("+-------------------------------------------------------------+");
		System.out.println("Lazily-initialized singleton:\n");

		/* 
		 * This does not work! Since the no-args constructor for OnDemandSingleton
		 * class is marked as private. We instead must access it through the public
		 * getInstance() method.
		 */
		//LazySingleton ex1_1 = new LazySingleton();

		LazySingleton ex1_1 = LazySingleton.getInstance();
		LazySingleton ex1_2 = LazySingleton.getInstance();
		System.out.println("ex1_1 == ex1_2? " + (ex1_1 == ex1_2)); // prints true -> they are the same object!

		// Show the default value for both LazySingleton references (0)
		System.out.println("ex1_1.getValue() = " + ex1_1.getValue());
		System.out.println("ex1_2.getValue() = " + ex1_2.getValue());

		// Change 'value' using the public setter and see it reflect in both references
		ex1_1.setValue(10);
		System.out.println("ex1_1.getValue() = " + ex1_1.getValue());
		System.out.println("ex1_2.getValue() = " + ex1_2.getValue());


		//--------------------------------------------------------------------------------------------
		// EAGERLY-INITIALIZED SINGLETON
		System.out.println("\n+-------------------------------------------------------------+");
		System.out.println("Eagerly-initialized singleton:\n");

		EagerSingleton ex3_1 = EagerSingleton.getInstance();
		EagerSingleton ex3_2 = EagerSingleton.getInstance();
		System.out.println("ex3_1 == ex3_2? " + (ex3_1 == ex3_2)); // prints true

		// Show the default value for both LazyLockedSingleton references (0)
		System.out.println("ex3_1.getValue() = " + ex3_1.getValue());
		System.out.println("ex3_2.getValue() = " + ex3_2.getValue());

		// Change 'value' using the public setter and see it reflect in both references
		ex3_1.setValue(1000);
		System.out.println("ex3_1.getValue() = " + ex3_1.getValue());
		System.out.println("ex3_2.getValue() = " + ex3_2.getValue());

	}
}
