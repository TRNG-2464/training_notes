package com.revature.javaBasics.controlFlow;

import java.util.Scanner;

public class Control {
	public static void main(String[] args) {
//		ifStatements();
//		ternary();
//		switchStatements();
//		loops();
		breakControl();
//		shortCircuit();
//		enhanced();
	}

	private static void ifStatements() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");

		int x = scan.nextInt();

		// if statements
		if( x > 3 ) {
			System.out.println("x is greater than 3");
		} else if (x < 0) {
			System.out.println("x is a negative number");
		} else {
			if(x == 3) {
				System.out.println("x is 3");
			} else {
				System.out.println("x is 0, 1 or 2");
			}
		}
	}

	/*
	 * The ternary operator is an operator which takes 3 arguments:
	 * 		condition ? expressionReturnedIfTrue : expressionReturnedIfFalse
	 *
	 * In many ways, a ternary acts as a condensed if...else statement that
	 * also returns a value.
	 */
	private static void ternary() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = scan.nextInt();

		String s = x>3 ? "x is greater than 3" : "x is not greater than 3";

		System.out.println(s);
	}

	private static void switchStatements() {
		Scanner scan  = new Scanner(System.in);
		System.out.println("Input a value: ");
		int x = scan.nextInt();
		switch(x) {
			case 0:
				System.out.println("x was 0");
			case 1:
				System.out.println("x was 1");
			case 2:
				System.out.println("x was 2");
				break;
			default: System.out.println("x wasn't within bounds.");
		}
	}

	private static void loops() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input a value for the for-loop: ");
		int f = scan.nextInt();
		// for loops
		for (int i = 0;
			 i < f;
			 i++) {
			System.out.println(i);
		}

		// While loops
		System.out.println("Input a value for the while-loop: ");
		int w = scan.nextInt();
		int i = 0;
		while(i<w) {
			System.out.println(i);
			i++;
		}

		// do while loop
		System.out.println("Input a value for the do-while-loop: ");
		int d = scan.nextInt();
		i = 0;
		do {
			System.out.println(i);
			i++;
		} while(i < d);

		// Note: A do-while loop is guaranteed to execute at least once:
		do {
			System.out.println("The condition of this do-while loop is 'false'.");
			System.out.println("This loop still executes once!.");
		} while(false); // this condition is false
	}

	/*
	 * Label: a name that can be assigned to a control statement
	 * 	Which provides greater control over break and continue
	 * 	statements. Using a label allows you to choose which
	 * 	control statement a break / continue should apply
	 *
	 * break: keyword used to end the current control statement
	 * 	and return control back to the enclosing statement.
	 *
	 * continue: keyword used to stop the execution of the
	 * 	current iteration of a loop statement (while/do-while/for)
	 * 	and jump to the next iteration.
	 */
	private static void breakControl() {
		// 'break' - exits the nearest enclosing loop
		System.out.println("=== break ===");
		for (int i = 1; i <= 10; i++) {
			if (i == 5) {
				// As soon as i equals 5, exit the loop entirely
				break;
			}
			System.out.println("i = " + i);
		}

		// 'continue' - skips the rest of the current iteration and moves to the next one
		System.out.println("\n=== continue ===");
		for (int i = 1; i <= 10; i++) {
			if (i % 2 == 0) {
				// Skip even numbers; go straight to the next iteration
				continue;
			}
			System.out.println("Odd number: " + i);
		}

		// Labeled break - breaks out of an OUTER loop from within a nested (inner) loop
		System.out.println("\n=== Labeled break ===");
		// The label "outerLoop:" is attached to the outer for-loop
		outerLoop:
		for (int row = 1; row <= 3; row++) {
			innerLoop: // this label isn't really required, but is included for visibility
			for (int col = 1; col <= 3; col++) {
				if (row == 2 && col == 2) {
					// Without the label, this would only break the
					// inner loop. With "break outerLoop;", it exits
					// BOTH loops immediately.
					System.out.println("Breaking outer loop at row="
							+ row + ", col=" + col);
					break outerLoop;
				}
				System.out.println("row=" + row + ", col=" + col);
			}
		}

		// Labeled continue - skips to the next iteration of the OUTER loop, bypassing the rest of the inner loop
		System.out.println("\n=== Labelled continue ===");
		searchLoop:
		for (int row = 1; row <= 3; row++) {
			innerLoop: // This label isn't really required, but is included for visibility
			for (int col = 1; col <= 3; col++) {
				if (col == 2) {
					// Skips remaining columns in the inner loop and
					// moves directly to the next row in the outer loop
					System.out.println("Skipping rest of row " + row
							+ " at col=" + col);
					continue searchLoop;
				}
				System.out.println("row=" + row + ", col=" + col);
			}
		}
	}

	/*
	 * Demonstrates short-circuit evaluation: since x++ == y is true,
	 * the JVM never evaluates the right-hand side (x == z++), so z
	 * is never incremented. Also shows post-increment (x++) using
	 * the current value of x before incrementing it.
	 */
	private static void shortCircuit() {
		int x = 6;
		int y = 6;
		int z = 7;
		System.out.println("x=" + x + ", y=" + y + ", z=" + z);

		if (x++ == y || x == z++) { // right side skipped due to short-circuit
			System.out.println("true");
		}

		System.out.println("x=" + x + ", y=" + y + ", z=" + z); // z unchanged
	}

	/*
	 * Compares a traditional indexed for-loop with an 'enhanced
	 * for-each' loop when iterating over the same array.
	 */
	private static void enhanced() {
		String[] arr = { "Horse", "Dog", "Bat", "Iguana", "Monkey", "Pizza" };
		System.out.println("For-Loop (standard notation)");
		for (int i = 0; i < arr.length; i++) { // indexed loop
			System.out.println(arr[i]);
		}

		System.out.println("");
		System.out.println("For-Each Loop ('enhanced' notation) ");
		// for-each loop
		for (String animal : arr) {
			System.out.println(animal);
		}
	}
}
