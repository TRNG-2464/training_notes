package com.revature.threads.basics;

public class LambdaThread {
	public static void main(String[] args) {
		System.out.println("Main method in 'LambdaThread' class");
		
		// Runnable is a functional interface. As such, we can use Lambdas!
		/*
		 * Recall that Lambdas are a way for you to provide an implementation of a method,
		 * without needing to explicitly implement the interface or extend the class In the
		 * class file itself.
		 * Lambda Syntax:
		 * 		(parameter_list) -> { lambda-body };
		 * 
		 * The method run can be implemented by a method that takes no arguments and returns no values:
		 * void run()
		 */
		Runnable r = () -> {
			System.out.println("I am a thread created with Lambdas!");
			
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " is running");
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
					/*
					 *  If the thread is interrupted, we want this loop
					 *  to stop, so that we do not potentially cause a
					 *  stop in our system 
					 */
					break;
				}
			}
			
			System.out.println(Thread.currentThread().getName() + " has finished");
		};
		
	}
}
