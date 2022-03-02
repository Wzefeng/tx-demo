package demo.tx;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 模拟 Spring @Transactional 注解
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface MyTransactional {
}
