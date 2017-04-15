package com.qunar.fresh2017.exam3.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shuai.lv on 2017/3/23.
 */
@Service
public interface UserService {

    void login(String username, String password, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

}