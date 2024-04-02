package com.zts.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface CsvIndex {

	/**
	 * 索引
	 * @return
	 */
	int index() default 0;

	/**
	 * 索引名称
	 * @return
	 */
	String indexName() default "";
}
