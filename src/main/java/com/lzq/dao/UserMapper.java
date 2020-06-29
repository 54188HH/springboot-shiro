package com.lzq.dao;

import com.lzq.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 10:33
 **/
@Mapper
public interface UserMapper {

    User findByUserName(@Param("name") String userName);

    User findByUser(@Param("name") String userName,@Param("pwd") String pwd);

    List<String> findByAuth(@Param("name") String name);
}
