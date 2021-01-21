package com.han.community.controller;

import com.han.community.model.Message;
import com.han.community.model.Page;
import com.han.community.model.User;
import com.han.community.service.MessageService;
import com.han.community.service.UserService;
import com.han.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    //方法处理私信列表
    @RequestMapping(path = "/letter/list",method = RequestMethod.GET)
    public String getLetterList(Model model, Page page){
        User user = hostHolder.getUser();
        //会话列表
        page.setLimit(5);
        page.setPath("/letter/list");
        page.setRows(messageService.fingConversationCount(user.getId()));

        //查询会话列表
        final List<Message> conversationList = messageService.findConversations(
                user.getId(), page.getOffset(), page.getLimit());

        List<Map<String , Object>> conversations = new ArrayList<>();
        if (conversationList != null){
            for (Message message : conversationList){
                Map<String , Object> map = new HashMap<>();
                map.put("conversation", message);
                map.put("letterCount",messageService.findLetterCount(message.getConversationId()));
                map.put("unreadCount",messageService.findLetterUnreadCount(user.getId(),message.getConversationId()));
                int targetId = user.getId() == message.getFromId() ? message.getToId() : message.getFromId();
                map.put("target",userService.findUserById(targetId));

                conversations.add(map);
            }
        }

        model.addAttribute("conversations",conversations);

        //查询未读消息数量
        int letterUnreadCount  = messageService.findLetterUnreadCount(user.getId(),null);
        model.addAttribute("letterUnreadCount",letterUnreadCount);

        return "/site/letter";
    }


}