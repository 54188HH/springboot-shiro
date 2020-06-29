package com.lzq.service;

import com.lzq.utils.LoginResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 14:11
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public LoginResult login(String userName, String password) {
        LoginResult loginResult = new LoginResult();
        if (userName == null || userName.isEmpty()) {
            loginResult.setLogin(false);
            loginResult.setResult("用户名为空");
            return loginResult;
        }
        String msg = "";
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();
//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//
//        }
        // 3、将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        // 4、认证
        try {
            // 传到MyAuthorizingRealm类中的方法进行认证
            currentUser.login(token);
            Session session = currentUser.getSession();
            session.setAttribute("userName", userName);
            loginResult.setLogin(true);
            return loginResult;
            //return "/index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            msg = "UnknownAccountException -- > 账号不存在：";
        } catch (IncorrectCredentialsException e) {
            msg = "IncorrectCredentialsException -- > 密码不正确：";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            msg = "用户验证失败";
        }

        loginResult.setLogin(false);
        loginResult.setResult(msg);

        return loginResult;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
