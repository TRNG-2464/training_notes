package com.revature.expanded.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * A pointcut is an expression that selects which
 * join points a particular piece of advice should
 * match.
 *
 * It is common practice to create a 'named pointcut'
 * using the @Pointcut annotation. This annotation
 * takes a 'designator' which is used to specific the
 * exact details of a method's execution you are trying
 * to match.
 */
@Aspect
@Component
public class PointcutExamples {

    /*
     * Anatomy of an execution() pointcut expression:
     *
     *   execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?
     *             name-pattern(param-pattern) throws-pattern?)
     *
     * - modifiers-pattern (optional): e.g. "public"
     * - ret-type-pattern: e.g. "*" (wildcard) or a specific type like "String"
     * - declaring-type-pattern (optional): the package/class the method belongs to
     * - name-pattern: the method name; supports wildcards like "*" or "process*"
     * - param-pattern: "(..)" means any number of arguments, of any type
     * - throws-pattern (optional): restricts by declared checked exceptions
     */

    /*
     * Matches execution of ANY method (any modifiers, any return type,
     * any parameters) in ANY class within com.revature.expanded.aop and its
     * sub-packages.
     */
    @Pointcut("execution(* com.revature.expanded.aop..*.*(..))")
    public void anyMethodInAopPackage() {}

    /*
     * Matches execution of any PUBLIC method, with ANY return type,
     * declared specifically on AdviceService, taking any number of
     * arguments of any type.
     */
    @Pointcut("execution(public * com.revature.expanded.aop.AdviceService.*(..))")
    public void anyPublicMethodOnAdviceService() {}

    /*
     * Matches execution of ANY method whose name starts with "process",
     * regardless of class, return type, or arguments.
     */
    @Pointcut("execution(* process*(..))")
    public void anyProcessMethod() {}

    /*
     * A common best practice for pointcuts is to combine
     * named pointcuts with &&, ||, and !
     * This lets you build more precise rules out of smaller,
     * reusable, well-named pieces.
     *
     * The following matches methods that satisfy BOTH conditions above.
     */
    @Pointcut("anyPublicMethodOnAdviceService() && anyProcessMethod()")
    public void combinedPointcut() {}

    /*
     * Showcases 'within'
     *
     * Matches execution of any method where the method is executed
     * within the 'com.revature.expanded.aop' package, or any of its
     * subclasses. Regardless of its method's name, return type or
     * arguments.
     *
     * Note: we achieved something similar above. 'within' is useful
     * when you have logic that applies broadly to a package's methods
     *
     * execution is more useful to name a pointcut matching a more
     * specific pattern
     */
    @Pointcut("within(com.revature.expanded.aop..*)")
    public void anythingWithinAopPackage () {}

    /*
     * Showcases @annotation
     *
     * Matches execution of any method annotated with the annotation
     * @LogExecution, wherever it appears in the codebase.
     *
     * Note: this Annotation doesn't actually exist - this example
     * assumes that we created a custom annotation for this purpose
     */
    @Pointcut("@annotation(com.example.aop.LogExecution)")
    public void annotatedWithLogExecution() {}
}
