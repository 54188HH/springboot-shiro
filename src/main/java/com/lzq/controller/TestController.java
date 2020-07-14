package com.lzq.controller;

import com.lzq.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-29 14:00
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void methdo(){
        User user = User.builder()
                .id(1)
                .userName("刘振奇")
                .password("123")
                .salt("qwregrfv123245t")
                .roleId(1)
                .build();
        redisTemplate.boundListOps("bound"+1).leftPush(1);
        redisTemplate.boundListOps("bound"+1).leftPush(2);
        redisTemplate.boundListOps("bound"+1).leftPush(3);
        redisTemplate.opsForList().leftPush("ops"+2,1);
        redisTemplate.opsForList().leftPush("ops"+2,2);
        redisTemplate.opsForList().leftPush("ops"+2,3);
        redisTemplate.opsForHash().put(User.class.getName(),user.getId(),user);
        User o = (User) redisTemplate.opsForHash().get(User.class.getName(), user.getId());
        System.out.println(o.toString());
        System.out.println("ops:"+redisTemplate.opsForList().rightPop("ops"+2));
        System.out.println("bound:"+redisTemplate.boundListOps("bound"+1).rightPop());
        redisTemplate.opsForValue().set("1","1",1,TimeUnit.MINUTES);
    }
}
