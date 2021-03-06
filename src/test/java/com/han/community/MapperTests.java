package com.han.community;

import com.han.community.CommunityApplication;
import com.han.community.dao.DiscussPostMapper;
import com.han.community.dao.LoginTicketMapper;
import com.han.community.dao.MessageMapper;
import com.han.community.dao.UserMapper;
import com.han.community.model.DiscussPost;
import com.han.community.model.LoginTicket;
import com.han.community.model.Message;
import com.han.community.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("aaa");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("121@qq.com");
        user.setHeaderUrl("http://95.169.14.31/data/a/a1.png");
        user.setCreateTime(new Date());

        int i  = userMapper.insertUser(user);
        System.out.println(i);
        System.out.println(user.getId());

    }

    @Test
    public void testUpdateUser(){
        int row  = userMapper.updateHeader(150,"aaaaaaaaaaaatest");
        System.out.println(row);

        row = userMapper.updateStatus(150,1);
        System.out.println(row);

        row = userMapper.updatePassword(150,"aaaaaab");
        System.out.println(row);
    }



    @Test
    public  void testSelectPosts(){
        List<DiscussPost>  list=  discussPostMapper.selectDiscussPost(101,0,10);
        for (DiscussPost post: list) {
            System.out.println(post);
        }

        int row = discussPostMapper.selectDiscussPostRows(101);
        System.out.println(row);
    }


    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("aasadasd");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));

        loginTicketMapper.insertLoginTicket(loginTicket);

    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("aasadasd");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("aasadasd",1);
        loginTicket = loginTicketMapper.selectByTicket("aasadasd");
        System.out.println(loginTicket);
    }

    /*@Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }
*/

    @Test
    public void testSelectMesage(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for (Message message : messages) {
            System.out.println(message);
        }
        final int i = messageMapper.selectConversationCount(111);
        System.out.println(i);

        final List<Message> messages1 = messageMapper.selectLetters("111_112", 0, 10);
        for (Message a : messages1) {
            System.out.println(a);
        }

        final int i1 = messageMapper.selectLetterCount("111_112");
        System.out.println(i1);

        final int i2 = messageMapper.selectLetterUnreadCount(111, "111_131");
        System.out.println(i2);

    }

}
