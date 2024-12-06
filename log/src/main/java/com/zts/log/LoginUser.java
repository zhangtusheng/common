package com.zts.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangtusheng
 * @Date 2024 12 06 10 10
 * @describe：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

    private Long id;

    private String name;
}
