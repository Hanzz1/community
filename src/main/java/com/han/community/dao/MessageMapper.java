package com.han.community.dao;

import com.han.community.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    //查询当前 用户的会话列表，针对每个会话只显示一个最新的私信
    List<Message> selectConversations(int userId,int offset,int limit);

    //查询当前用户对话的数量
    int selectConversationCount (int userId);

    //查询某个会话所包含的私信列表
    List<Message> selectLetters(String  conversationId , int offset , int limit);

    //查询某个会话所包含的私信数量
    int selectLetterCount(String conversationId);

    //查询未读的私信数量
    int selectLetterUnreadCount(int userId , String conversationId);



}
