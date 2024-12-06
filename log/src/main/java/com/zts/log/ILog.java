package com.zts.log;

import java.lang.annotation.*;

/**
 * @Author zhangtusheng
 * @Date 2024 12 06 09 57
 * @describe：
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ILog {

    /**
     * 日志内容
     * @return
     */
    String value() default "";

    /**
     * 日志类型
     *
     * @return 0: 操作日志; 1:登录日志
     */
    LogType logType() default LogType.OPERATE_LOG;

    /**
     * 操作日志类型
     *
     * @return （0其它 1添加，2修改，3删除 4导出 5导入 6查询）
     */
    OperateType operateType() default OperateType.OTHER;

}
