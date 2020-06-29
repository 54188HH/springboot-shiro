package com.lzq.controller;

import com.lzq.dao.UserMapper;
import com.lzq.service.LoginService;
import com.lzq.service.LoginServiceImpl;
import com.lzq.utils.LoginResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: springbootshiro
 * @description:
 * @author: liuzhenqi
 * @create: 2020-06-28 13:47
 **/
@Controller
public class LoginController {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private LoginService service;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLogin(){
        return "/login";
    }
    @RequestMapping("/logout")
    public void loginOut(){
        service.logout();
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Map<String, Object> map, HttpServletRequest request) throws Exception{
        System.out.println("loginController()");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        LoginResult loginResult = service.login(userName,password);
        if(loginResult.getLogin())
        {
            return "/index";
        }
        else {
            map.put("msg",loginResult.getResult());
            map.put("userName",userName);
            return "/login";
        }
    }
    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "/403";
    }

    @RequestMapping("/userAdd")
    //权限管理;
    @RequiresPermissions("user:add")
    public String userInfo(){
        return "/userAdd";
    }

    @RequestMapping("/userUpdate")
    //权限管理;
    @RequiresPermissions("user:update")
    public String userUpdate(){
        return "/userUpdate";
    }

    @RequestMapping("/userDel")
    //权限管理;
    @RequiresPermissions("user:del")
    public String userDel(){
        return "/userDel";
    }

    public String getPwd(String pwd){
            //原始密码
            String source = "111111";
            //盐
            String salt = "wasd";
            //散列次数
            int hashIterations = 2;
            //散列一次:1b2814e7f4cbb32fea953252e45fface
            //散列两次:174aec66f457f5169dbb1922393b6b4c
            //构造方法中:
            //第一个参数:明文，原始密码
            //第二个参数:盐，通过使用随机数
            //第三个参数:散列的次数，比如散列两次，相当于md5(md5(''))
            Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

            String password_md5 = md5Hash.toString();
            System.out.println(password_md5);

            //第一个参数:散列算法
            SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
            System.out.println(simpleHash.toString());
            return simpleHash.toString();
    }
}
