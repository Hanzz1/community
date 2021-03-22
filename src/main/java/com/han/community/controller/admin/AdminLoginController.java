package com.han.community.controller.admin;

import com.han.community.model.User;
import com.han.community.service.AdminService;
import com.han.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;



    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String  index(){

        return "/site/admin/adminlogin";
    }

    @RequestMapping(path = "/login1" , method = RequestMethod.POST)
    @ResponseBody
    public String login (String username , String password , String admintype, Model model) {

        final User user = adminService.selectByIdAndTpye(username, password, admintype);
        System.out.println(user);
        if (user != null){
           return "1";
        }
          return "2";

    }




}
