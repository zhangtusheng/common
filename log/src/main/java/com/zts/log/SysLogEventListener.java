package com.zts.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author zhangtusheng
 * @Date 2024 12 06 10 42
 * @describe：
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class SysLogEventListener {


    @EventListener
    public void saveSysLog(SysLog sysLog) {
        System.out.println("saveSysLog");
    }
}
