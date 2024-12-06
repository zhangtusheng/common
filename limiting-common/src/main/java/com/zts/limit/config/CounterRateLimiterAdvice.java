package com.zts.limit.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.lang.reflect.Method;

/**
 * @Author zhangtusheng
 * @Date 2024 07 09 00 23
 * @describe：
 **/
public class CounterRateLimiterAdvice implements MethodInterceptor, BeanFactoryAware {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        return execute(invocation, invocation.getThis(), method, invocation.getArguments());
    }


    private Object execute(MethodInvocation invoker, Object target, Method method, Object[] args) throws Throwable {

        return invoker.proceed();
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
