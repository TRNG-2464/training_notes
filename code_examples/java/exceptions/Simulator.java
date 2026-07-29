package com.revature.exceptions;

public class Simulator {
	/*
	 * When a declared method has a 'throws' declaration,
	 * it is used to consolidate how many try/catch blocks
	 * are needed throughout your code. In this case, any
	 * method which has the 'throws' declaration must 
	 * eventually be handled within a try/catch block, as 
	 * shown below:
	 */
	public static void main(String[] args) {
		CustomMathOperations math = new CustomMathOperations();

		try {
			int sum1 = math.sumOddNumbers(5, 2);
			int sum2 = math.sumDifferentOddNumbers(2,5);
			System.out.println(sum1 + " " + sum2);
		} catch (EvenNumberException e) {
			e.printStackTrace();
		} catch (DuplicateInputException e) {
			e.printStackTrace();
			System.out.println("You passed two of the same number, try not to do that!");
		}


		/*
		 * If you want to handle multiple exceptions with the same handler logic
		 * use a pipe ( '|' ) to separate each Exception class in the catch
		 * statement
		 */
		try {
			int sum2 = math.sumDifferentOddNumbers(2,5);
			System.out.println(sum2);
		} catch (EvenNumberException | DuplicateInputException e) {
			e.printStackTrace();
		}


		/*
		 * When handling multiple exceptions, Exceptions higher
		 * in your exception hierarchy (parent classes) should
		 * appear later in your catch/handler blocks
		 */
		try {
			int sum2 = math.sumDifferentOddNumbers(2,5);
			System.out.println(sum2);
		}
		catch (EvenNumberException | DuplicateInputException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
