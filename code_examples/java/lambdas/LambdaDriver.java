package com.revature.lambdas;

import com.revature.lambdas.implementors.HelloWorldImplementor;
import com.revature.lambdas.interfaces.HelloWorldInterface;

public class LambdaDriver {

    public static void sayHi(HelloWorldInterface hello) {
        hello.sayHello();
    }

    public static void main(String[] args) {
        /*
         * To use an interface, we need to create an instance of a
         * class, which implements that interface
         */
        HelloWorldInterface hwiObj = new HelloWorldImplementor();
        sayHi(hwiObj);


        /*
         * A Lambda allows you to create an in-line implementation
         * of a functional interface
         *
         * Lambdas when created, are placed on the stack
         */
        HelloWorldInterface lambda =
                () -> {
            System.out.println("Hello World - Lambda!");
        };

        sayHi(lambda);
        lambda.sayHello();


        /*
         * Furthermore, you can pass this in-line implementation
         * directly to a method call
         *
         * Both examples below are doing the exact same thing
         */
        sayHi(() -> {System.out.println( "Hello World implementation as an argument!" );});
        sayHi(
                () -> {
                    System.out.println( "Hello World implementation as an argument!" );
                }
        );


        // This example is a more "realistic" look at how Lambdas are used in application code
        User u = new User("Revature", "Pass123");
        validateUser( u ,

            (User input) -> {
            return input.uName.equals("Revature") &&
                    input.uPass.equals("Pass123");
            });
    }



    public static boolean validateUser(User u, Validator v) {
        return v.validate(u);
    }
}

/*
 * The following Class and Interface are used to showcase how Lambdas
 * are typically be used when expecting implementation for a Class
 */
class User {
    public String uName;
    public String uPass;
    public User(String uName, String uPass) {
        this.uName = uName;
        this.uPass = uPass;
    }
}

/*
 * This 'Validator' implementation should 'validate' a user - perhaps from the database
 */
@FunctionalInterface
interface Validator {
    public boolean validate(User u);
}