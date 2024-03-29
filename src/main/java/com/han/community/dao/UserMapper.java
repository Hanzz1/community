package com.han.community.dao;

import com.han.community.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id,int status);

    int updateHeader(int id , String headerUrl);

    int updatePassword(int id,String password);

    User selectByIdAndTpye(String username, String password , String admintype);

    List<User> selectUser();

    //删除
    int deleteUserByUserId(int userId);


    //更新admin
    int updateUserData(int id,String username,String email,int type ,int status);

}
