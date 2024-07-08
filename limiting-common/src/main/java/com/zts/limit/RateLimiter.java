package com.zts.limit;

/**
 * @Author zhangtusheng
 * @Date 2024 07 06 16 18
 * @describe：
 **/
public interface RateLimiter {

    boolean acquire();

}
