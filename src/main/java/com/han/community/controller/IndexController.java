package com.han.community.controller;

import com.han.community.service.AlphaService;
import com.han.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class IndexController {

    @Autowired
    private AlphaService alphaService;

    @GetMapping("/index")
    public String hello(){
        return "index";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+"--------"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应对象
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();

        ){
            writer.write("<h>hhhhh</h>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //get请求

    //students?current=1&limit=20
    @RequestMapping(path = "/students" , method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current" , required = false , defaultValue = "1") int current ,
            @RequestParam(name = "limit" , required = false , defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return  "some students";
    }

    //students/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){

        System.out.println(id);
        return  "a student";

    }



    //post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name ,int age){
        System.out.println(name);
        System.out.println(age);
        return  "success";
    }



    //响应HTML数据
    @RequestMapping(path = "/teacher" , method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","zhangsan");
        modelAndView.addObject("age","44");

        modelAndView.setViewName("/demo/view");

        return modelAndView;
    }

    @RequestMapping(path = "/school" , method = RequestMethod.GET)
    public String getSchool(Model model){

        model.addAttribute("name","ouya");
        model.addAttribute("dizhi","xian");

        return "/demo/view";
    }


    //响应json数据
    //java对象   =》 可以通过json转换   =》 js对象
    @RequestMapping(path = "/emp" , method = RequestMethod.GET)
    @ResponseBody //加上这个返回的是 json字符串
    public Map<String , Object> getEmp(){
        Map<String , Object> emp = new HashMap<>();
        emp.put("naem","张三");
        emp.put("age",22);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps" , method = RequestMethod.GET)
    @ResponseBody //加上这个返回的是 json字符串
    public List<Map<String , Object>> getEmps(){
        List<Map<String , Object>> list = new ArrayList<>();
        Map<String , Object> emp = new HashMap<>();
        emp.put("naem","张三");
        emp.put("age",22);
        emp.put("salary",8000.00);
        list.add(emp);

        emp.put("naem","李四");
        emp.put("age",25);
        emp.put("salary",3300.00);
        list.add(emp);

        emp.put("naem","王五");
        emp.put("age",24);
        emp.put("salary",12000.00);
        list.add(emp);

        return list;
    }

    //cookie示例
    @RequestMapping(path = "/cookie/set" ,method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        //设置生效范围
        cookie.setPath("/community/alpha");
        //设置cookie的最大生存时间
        cookie.setMaxAge(60*10);
        //发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get" ,method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        return "get cookie";
    }

    //session
    @RequestMapping(path = "/session/set" ,method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","TESt");
        return "set session";
    }
    @RequestMapping(path = "/session/get" ,method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        System.out.println(session.getId());
        return "get session";
    }
    //ajax示例
    @RequestMapping(path = "/ajax" ,method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name , int age){
        System.out.println(name);
        System.out.println(age);

        return CommunityUtil.getJsonString(0,"操作成功");
    }



}
