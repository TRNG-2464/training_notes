package com.revature.expanded.logging;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/*
 * This is a custom annotation which we will use to mark
 * methods that should be logged.
 *
 * See 'LoggingAspect' and the various service classes
 * for its use
 *
 * @Retention(RetentionPolicy.RUNTIME) - specifies that this
 *  annotation should be accessible via the class file through
 *  reflections Spring actually requires this since it reflectively
 *  looks at the bean code during execution
 *
 * @Target(ElementType.METHOD) - specifies that this annotation
 *  should only be applied to methods. Since the only Join Point
 *  in Spring AOP are method execution, we only ever need to
 *  annotate methods
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecution { }
