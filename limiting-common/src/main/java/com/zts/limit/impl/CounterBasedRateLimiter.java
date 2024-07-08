package com.zts.limit.impl;

import com.zts.limit.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhangtusheng
 * @Date 2024 07 06 16 18
 * @describe：基于计数的限流算法
 * 这种算法的基本思想是通过维护一个计数器，在特定的时间窗口内累计接收到的请求次数，当请求次数达到预设的阈值时，后续的请求会被限流或直接拒绝。
 **/
public class CounterBasedRateLimiter implements RateLimiter {

    /**
     * 窗口大小
     */
    private final long windowSize;

    /**
     * 限流大小
     */
    private final int limit;


    /**
     * 窗口开始时间，毫秒级
     */
    private long windowStartTime;


    private final AtomicInteger counter;

    /**
     * 限流的名字。通常是利用类路径名+
     */
    private String name;


    public CounterBasedRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.counter = new AtomicInteger(0);
        this.reset();
    }

    /**
     * 检验窗口内是否合法。
     * @return
     */
    private boolean checkIfWindowValid() {
        return System.currentTimeMillis() - windowStartTime <= windowSize;
    }

    private synchronized void reset() {
        if (checkIfWindowValid()) {
            return;
        }
        windowStartTime = System.currentTimeMillis();
        counter.set(0);
    }

    @Override
    public boolean acquire() {
        if(!checkIfWindowValid()) {
            reset();
        }
        return counter.incrementAndGet() <= limit;
    }
}
