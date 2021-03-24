package com.han.community.controller.admin;

import com.han.community.model.DiscussPost;
import com.han.community.model.Page;
import com.han.community.model.User;
import com.han.community.service.AdminService;
import com.han.community.service.DeleteService;
import com.han.community.service.DiscussPostService;
import com.han.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping(path = "/index1", method = RequestMethod.GET)
    public String getIndexPage(Model model,Page page) {



        final List<User> user = adminService.selectUser();
//Model model , Page page
//        int i = user.size();
//
//        page.setRows(i);
//        page.setPath("/admin/index");

        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/admin/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user1 = userService.findUserById(post.getUserId());
                map.put("user1", user1);


                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);

        model.addAttribute("user",user);

        return "/site/admin/adminindex";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndex_v1Page() {


        return "/site/admin/index_v1";
    }

    @RequestMapping(path = "/userdata", method = RequestMethod.GET)
    public String getuserdataPage(Model model,Page page) {

        final List<User> user = adminService.selectUser();
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/admin/index");
        model.addAttribute("user",user);
        return "/site/admin/userdata";
    }

    @RequestMapping(path = "/postdata" , method = RequestMethod.GET)
    public String getPostdataPage(Model model,Page page){

        int i =0 ;
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/admin/postdata");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {

                Map<String, Object> map = new HashMap<>();
                map.put("i",i);
                i++;
                map.put("post", post);
                User user1 = userService.findUserById(post.getUserId());
                map.put("user1", user1);


                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);

        return "/site/admin/postdata";
    }

/*    @RequestMapping(path = "/selectPostById", method = RequestMethod.GET)
    public DiscussPost selectPostById(int id,Model model){

        final DiscussPost post = adminService.selectPostById(id);
        model.addAttribute("apost",post);
        System.out.println("1111111111111111111111111111111"+post);
        return post;
    }*/



    @RequestMapping(path = "/deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public int delete(int userId){
        return deleteService.deleteUserByUserId(userId);
    }

}
