package com.zts.limit.annotation;

import java.lang.annotation.*;

/**
 * @Author zhangtusheng
 * @Date 2024 07 06 17 37
 * @describe：
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface CounterRateLimiter {

    /**
     * 时间大小
     */
    int windowSize() default 1000;


    /**
     * 窗口内数量的大小
     * @return
     */
    int limit() default 100;

    /**
     * 限流名字。
     */
    String name() default "";



}
