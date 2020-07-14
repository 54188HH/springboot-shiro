package com.lzq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 10:00
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String salt;
    private Integer roleId;

    public String getUserSalt(){
        return this.userName+this.salt;
    }
}
