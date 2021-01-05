package com.han.community.service;

import com.han.community.dao.UserMapper;
import com.han.community.model.User;
import com.han.community.util.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired   //注册邮件客户端
    private MailClient mailClient;

    @Autowired   //注册模板引擎
    private TemplateEngine templateEngine;

    //域名
    @Value("${community.path.domain}")
    private String domain;

    //项目名
    @Value("${server.servlet.context-path}")
    private String contextPath;


    public User findUserById(int id){
        return userMapper.selectById(id);
    }

    public Map<String ,Object> register(User user){
        Map<String ,Object> map = new HashMap<>();

        //判断空值

        return map;
    }

}
