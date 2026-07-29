package com.revature.exceptions;

public class ExceptionBasics {
    public static void main(String[] args) {
        System.out.println("Start of application");
        try {
            int i = 1/0;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Diving by 0 is a no-no");
        }


        System.out.println("End of application");
    }
}
