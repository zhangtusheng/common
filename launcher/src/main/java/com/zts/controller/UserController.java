package com.zts.controller;

import com.zts.log.ILog;
import com.zts.log.LogType;
import com.zts.log.OperateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangtusheng
 * @Date 2024 12 06 10 44
 * @describe：
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class UserController {

    @ILog(value = "新增用户", logType = LogType.OPERATE_LOG, operateType = OperateType.INSERT)
    @PostMapping(value = "/save")
    public String save() {
        return "测试";
    }
}

