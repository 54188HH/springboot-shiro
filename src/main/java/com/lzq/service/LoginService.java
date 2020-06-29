package com.lzq.service;

import com.lzq.utils.LoginResult;

public interface LoginService {
    public LoginResult login(String userName, String password);
    public void logout();
}
