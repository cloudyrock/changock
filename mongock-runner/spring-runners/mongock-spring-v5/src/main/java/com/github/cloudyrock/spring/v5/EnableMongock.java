package com.github.cloudyrock.spring.v5;

import com.github.cloudyrock.spring.v5.config.MongockContext;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MongockContext.class})
public @interface EnableMongock {
}
