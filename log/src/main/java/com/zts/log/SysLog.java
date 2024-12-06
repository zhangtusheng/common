package com.zts.log;

import lombok.Data;

/**
 * @Author zhangtusheng
 * @Date 2024 12 06 10 12
 * @describe：
 **/
@Data
public class SysLog {

    private Long id;

    private String userName;

    private Long userId;

    private Integer logType;

    private String logContent;

    private Integer operateType;

    private String method;

    private Long costTime;

    private String ip;

    private String requestType;
}
