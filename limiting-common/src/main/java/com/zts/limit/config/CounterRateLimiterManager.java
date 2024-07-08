package com.zts.limit.config;

import com.zts.limit.RateLimiter;
import com.zts.limit.annotation.CounterRateLimiter;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zhangtusheng
 * @Date 2024 07 08 23 05
 * @describe：
 **/
public class CounterRateLimiterManager {

    private ConcurrentHashMap<String, RateLimiter> rateLimiterConcurrentHashMap;

    public CounterRateLimiterManager() {
        this.rateLimiterConcurrentHashMap = new ConcurrentHashMap<>();
    }

    public boolean addRateLimiter(String name, RateLimiter rateLimiter) {
        if (rateLimiter instanceof CounterRateLimiter) {
            this.rateLimiterConcurrentHashMap.put(name, rateLimiter);
            return true;
        }
        return false;
    }

    public RateLimiter getRateLimiterByName(String name) {
        if (Objects.isNull(name)) {
            return null;
        }
        return this.rateLimiterConcurrentHashMap.get(name);
    }
}
