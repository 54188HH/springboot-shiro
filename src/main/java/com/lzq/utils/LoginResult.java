package com.lzq.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 14:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    private Boolean Login;
    private String result;
}
