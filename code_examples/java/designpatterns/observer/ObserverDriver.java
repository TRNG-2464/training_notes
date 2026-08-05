/**
 * @author Wezley Singleton
 */

package com.revature.designpatterns.observer;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Observer design pattern guidelines:
 * 		1) Model the "independent" functionality with a "subject" abstraction
 * 		2) Model the "dependent" functionality with "observer" hierarchy
 * 		3) The Subject is coupled only to the Observer base class
 * 		4) Observers register themselves with the Subject
 * 		5) The Subject broadcasts events to all registered Observers
 * 		6) Observers "pull" the information they need from the Subject
 * 		7) Client configures the number and type of Observers
 */
public class ObserverDriver {
	
	public static void main(String[] args) {
		Subject subj = new Subject();
		
		new HexObserver(subj);
		new BinaryObserver(subj);
		new OctalObserver(subj);
		
		Scanner scan = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++) {
			System.out.print("\nPlease enter a number: ");
			
			try {
				subj.setState(scan.nextInt());
			} catch (InputMismatchException ime) {
				ime.printStackTrace();
				main(args);
			}
		}
		
		scan.close();
	}

}
