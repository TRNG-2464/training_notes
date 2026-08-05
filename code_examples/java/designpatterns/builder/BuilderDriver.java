package com.revature.designpatterns.builder;

/*
 * Constructors in Java are used to create object and can take parameters 
 * required to create object. The problem starts when objects can be created 
 * with a lot of parameters, some of them may be mandatory and others may be 
 * optional.
 * 
 * Instead of making the desired object directly, the client calls a 
 * constructor (or static factory) with all of the required parameters and 
 * gets a builder object. Then the client calls methods on the builder 
 * object to set each optional parameters. Finally, the client calls a build
 * method which generate an instance of the object which is immutable.
 */
public class BuilderDriver {

	public static void main(String[] args) {
		/*
		 * Time to create a cake using our nested builder class!
		 */
		Cake myCake = new Cake.Builder().sugar(1).vegOil(0.33)
				.vanilla(2).almondMilk(1).build();






		/*
		 * Our cake is now ready to eat!
		 */
		System.out.println(myCake);
		System.out.println("Nom nom nom nom nom");
	}
}

/*
 * Pros: 
 * 		1) Code is more maintainable if number of fields required to create 
 * 		   object is more than 4 or 5.
 * 		
 * 		2) Object creation code less error-prone as user will know what they 
 * 		   are passing because of explicit method call.
 * 		
 * 		3) Builder patter increases robustness, as only fully constructed object 
 * 		   will be available to client.
 * 
 * Cons:
 * 		1) Builder pattern is verbose and requires code duplication as Builder 
 * 		   needs to copy all fields from Original or Item class.
 */