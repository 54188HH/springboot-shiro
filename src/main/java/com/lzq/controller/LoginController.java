package com.lzq.controller;

import com.lzq.dao.UserMapper;
import com.lzq.service.LoginService;
import com.lzq.utils.LoginResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

}
