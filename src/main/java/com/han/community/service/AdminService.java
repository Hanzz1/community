package com.han.community.service;

import com.han.community.dao.DiscussPostMapper;
import com.han.community.dao.UserMapper;
import com.han.community.model.DiscussPost;
import com.han.community.model.User;
import com.han.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    //登录
    public User selectByIdAndTpye (String username , String password , String  admintype){
       return userMapper.selectByIdAndTpye(username, password, admintype);

    }

    // 显示全体用户
    public List<User> selectUser(){
        return userMapper.selectUser();
    }

    //通过id查帖子
    public DiscussPost selectPostById(int id){
        return discussPostMapper.selectDiscussPostById(id);
    }

    //通过id查用户
    public User selectUserById(int id){
        return userMapper.selectById(id);
    }

    //通过id更新post
    public int updateTitleAndContent(int id,String title,String content){
        return discussPostMapper.updateTitleAndContent(id, title, content);
    }

    //通过id更新user
    public int updateUserData(int id,String username,String email,int type ,int status){
        return userMapper.updateUserData(id, username, email, type, status);
    }

    //通过userid增加帖子
    public int addPostByUserId(int userId,String title,String content){

        DiscussPost post = new DiscussPost();
        post.setUserId(userId);
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        post.setType(0);
        post.setStatus(0);

        return discussPostMapper.insertDiscussPost(post);

    }

    //增加user
    //String username,String password,String email ,int type , int status
    public Map<String,Object> addUser(User user){
        Map<String, Object> map = new HashMap<>();
        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "账号已存在");
            return map;
        }

        //验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "邮箱已被注册");
            return map;
        }
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("https://avatars1.githubusercontent.com/u/11111", new Random().nextInt(1000)));
//        http://95.169.14.31/data/a/a2.gif
        user.setCreateTime(new Date());
        userMapper.insertUser(user);


        return map;
    }

}
