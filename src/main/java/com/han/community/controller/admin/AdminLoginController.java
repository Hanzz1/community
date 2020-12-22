package com.han.community.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/adminLogin")
public class AdminLoginController {

    @RequestMapping(value = "/login")
    public String  index(){
        return "/admin/login";
    }
}
