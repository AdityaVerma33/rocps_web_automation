package com.subex.automation.helpers.performance.iePreformance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SeleniumConfiguration {
	String location();
	String browser() default "";
	String seleniumServer() default "localhost";
	int seleniumPort() default 4444;
}