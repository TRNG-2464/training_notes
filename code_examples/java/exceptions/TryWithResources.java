package com.revature.exceptions;

import java.util.Scanner;

public class TryWithResources {
    public static void main(String[] args) {
        // Try-with-resources block using Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            System.out.printf("Hello, %s! You are %d years old.%n", name, age);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } // Here - exiting the block the Scanner will be closed
    }
}
