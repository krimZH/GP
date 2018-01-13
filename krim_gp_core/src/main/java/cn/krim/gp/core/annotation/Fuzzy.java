package cn.krim.gp.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Fuzzy {
	
	/**模糊查询标识*/
	boolean isFuzzy() default true;
	
}
