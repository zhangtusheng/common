package com.zts.log;

import cn.hutool.extra.spring.SpringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangtusheng
 * @Date 2024 12 06 09 59
 * @describe：
 **/
@Aspect
@Component
public class LogAspect {

    static Map<String, LoginUser> userMap = new HashMap<>();

    static {
        userMap.put("zts", new LoginUser(1L, "zts"));
    }

    @Around("@annotation(ILog)")
    public Object around(ProceedingJoinPoint joinPoint, ILog ILog) throws Throwable {
        long beginTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - beginTime;

        saveLog(joinPoint, time, result);

        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();

        ILog ILog = method.getAnnotation(ILog.class);

        if (ILog != null) {
            sysLog.setLogType(ILog.logType().ordinal());
            sysLog.setOperateType(ILog.operateType().ordinal());
            sysLog.setLogContent(ILog.value());
            sysLog.setCostTime(time);

            // 请求方法
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String ip = getIpAddr(request);

            LoginUser user = userMap.get("zts");

            if (user != null) {
                sysLog.setUserId(user.getId());
                sysLog.setUserName(user.getName());
            }
            sysLog.setMethod(className + "." + methodName + "()");
            sysLog.setIp(ip);
            sysLog.setRequestType(request.getMethod());
        }
        SpringUtil.getApplicationContext().publishEvent(sysLog);
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }

            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
        }
        return ip;
    }
}
