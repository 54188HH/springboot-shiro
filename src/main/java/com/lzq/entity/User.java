package com.lzq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 10:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Integer id;
    private String userName;
    private String password;
    private String salt;
    private Integer roleId;

    public String getUserSalt(){
        return this.userName+this.salt;
    }
}
