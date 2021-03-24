package com.han.community.service;


import com.han.community.dao.DiscussPostMapper;
import com.han.community.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private UserMapper userMapper;

    public int deletePostByPostId(int postId){
        return discussPostMapper.deletePostByPostId(postId);
    }

    public int deleteUserByUserId(int userId){
        return userMapper.deleteUserByUserId(userId);
    }
}
