package com.lzq.utils;

import com.lzq.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 17:15
 **/
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 2;

    /**
     * 注册时调用这个方法生成盐
     * @param user
     */
    public  void encryptPassword(User user) {
        // User对象包含最基本的字段Username和Password
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getUserSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
    }
    @Test
    public void method() {
        User u = new User();
        u.setId(2);
        u.setPassword("123456");
        u.setRoleId(1);
        u.setUserName("admin");
        encryptPassword(u);
        System.out.println(u.toString());
    }
}