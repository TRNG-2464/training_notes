package com.revature.basics.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELElvisSafeNavOperators {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        // Alice has both a nickname and an address set
        Person alice = new Person("Alice", "Allie", new Address("Denver"));

        // Bob has NEITHER a nickname NOR an address
        Person bob = new Person("Bob", null, null);

        StandardEvaluationContext aliceContext = new StandardEvaluationContext(alice);
        StandardEvaluationContext bobContext = new StandardEvaluationContext(bob);

        /*
         * The Elvis operator (?:) is used to provide a default value if a
         * reference is null
         *
         * Here, the expression is: "if the nickname is null use the firstName value"
         */
        Expression displayNameExpr = parser.parseExpression("nickname ?: firstName");
        System.out.println(displayNameExpr.getValue(aliceContext, String.class)); // "Allie"
        System.out.println(displayNameExpr.getValue(bobContext, String.class));   // "Bob" (nickname was null)

        /*
         * The Safe navigation operator (?.) is used to short circuit an
         * expression (halt it early) if a value is null. This doesn't provide
         * a default, fall-back, like Elvis. This just prevents a potential
         * NullReferenceException.
         */
        Expression cityExpr = parser.parseExpression("address?.city");
        System.out.println(cityExpr.getValue(aliceContext, String.class)); // "Denver"
        System.out.println(cityExpr.getValue(bobContext, String.class));  // null, but no exception is thrown

        /*
         * Combining both operators is a common pattern seen
         *
         * The following will attempt to reference the Address object reference. If
         * it is NOT null, then we safely navigate to the city. The city has a default
         * to fall back to if the whole chain comes back null
         */
        Expression cityWithDefaultExpr = parser.parseExpression("address?.city ?: 'Unknown City'");
        System.out.println(cityWithDefaultExpr.getValue(aliceContext, String.class)); // "Denver"
        System.out.println(cityWithDefaultExpr.getValue(bobContext, String.class));   // "Unknown City"
    }
}

class Address {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}

class Person {
    private String firstName;
    private String nickname; // may be null
    private Address address; // may be null

    public Person(String firstName, String nickname, Address address) {
        this.firstName = firstName;
        this.nickname = nickname;
        this.address = address;
    }

    public String getFirstName() { return firstName; }
    public String getNickname() { return nickname; }
    public Address getAddress() { return address; }
}