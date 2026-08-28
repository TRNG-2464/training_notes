package com.revature.basics.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELContext {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        // The following showcases examples requiring Context
        // Method access on an Object -- Typically the objects
        // bound to Context for various spring modules are your
        // apps beans!
        Greeter greeter = new Greeter();

        /*
         * EvaluationContext defines the 'environment' in which an expression
         * is evaluated.
         * i.e. When parsing a SpEL expression for a method or property value
         * the Context defines what object the expression operates against
         *
         * The 'StandardEvaluationContext' is a general purpose implementation
         * of EvaluationContext. Keep in mind, what we see below is effectively
         * what certain Spring Modules do behind the scenes.
         */
        EvaluationContext context = new StandardEvaluationContext(greeter); // 'greeter' is my root object!

        // Calling a VOID method greeter is in the Context, so this
        // is effectively evaluating as: greeter.greet("Joseph")
        Expression voidCallExpr = parser.parseExpression("greet('Joseph')");
        voidCallExpr.getValue(context); // Prints: "Hello Joseph"
        // Since this method returns void, we don't need to specific a type class

        Object result = voidCallExpr.getValue(context); // this calls the method again
        System.out.println("Result of calling the void method: " + result); // null

        Expression countExp = parser.parseExpression("greetCount");
        int count = countExp.getValue(context, Integer.class);
        System.out.println("greetCount is now: " + count);  // prints 2

        // Calling a method that returns a value
        Expression returnCallExpr = parser.parseExpression("shout('spring is fun')");
        System.out.println(returnCallExpr.getValue(context, String.class)); // here we expect the expression

        /*
         * The Context does not just contain an object, but it can also
         * contain other variables (these can be set to literal values, or
         * even references to other objects/beans)
         */
        context.setVariable("targetName", "Target");

        /*
         * Within your SpEL expression, if you want to reference a variable
         * within the context, you prefix the named variable with a '#'
         *
         * When Spring sees this '#' it tells spring that this is a named
         * variable in the context - look it up
         *
         * Below, we are calling the variable 'targetName' (set above) in this
         * expression.
         */
        Expression targetExpr = parser.parseExpression("greet(#targetName)");
        targetExpr.getValue(context); // "Hello Target"
    }
}

class Greeter {
    private int doesntMatter = 0;

    public void greet(String name) {
        System.out.println("Hello " + name);
        doesntMatter++;
    }

    public String shout(String message) {
        return message.toUpperCase() + "!";
    }

    public int getGreetCount() {
        System.out.println("greetCount Getter was called: Spring relies on conventions!");
        return doesntMatter;
    }
}
