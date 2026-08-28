package com.revature.basics.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELBasics {
    public static void main(String[] args) {
        /*
         * Here, we are creating a direct ExpressionParser to illustrate
         * the LANGUAGE of SpEL directly.
         *
         * Note: SpEL is not used outside of annotations or other Spring-managed
         * context, so you would not do this directly in most cases
         *
         * Keep in mind, all of these would typically be used in some annotation:
         * @Value(@someBean.isActive ? 'resolve' : 'halt')
         */
        ExpressionParser parser = new SpelExpressionParser();

        // Parsing a String. Note the use of single-quotes within the string
        Expression strExpr = parser.parseExpression("'Hello SpEL!'");
        System.out.println(strExpr.getValue(String.class));   // "Hello SpEL!"

        // Parsing Arithmetic and Comparison operations
        Expression mathCompExpr = parser.parseExpression("(10 + 5) > 12");
        System.out.println(mathCompExpr.getValue(Boolean.class));   // True

        // Parsing a logical operator. Note the following is identical: "(150 > 100) && (10 < 20)"
        Expression logicExpr = parser.parseExpression("(150 > 100) and (10 < 20)");
        System.out.println(logicExpr.getValue(Boolean.class));

        // Parsing a ternary
        Expression ternaryExpr = parser.parseExpression("100 < 50 ? 'bulk' : 'standard'");
        System.out.println(ternaryExpr.getValue(String.class));
    }
}
