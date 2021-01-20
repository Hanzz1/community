package com.han.community.service;

import com.han.community.dao.AlphaDao;
import com.han.community.dao.DiscussPostMapper;
import com.han.community.dao.UserMapper;
import com.han.community.model.DiscussPost;
import com.han.community.model.User;
import com.han.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public AlphaService() {
        System.out.println("实例化，，，，，，，，，，");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void aa() {
        System.out.println("销毁了  -----------");
    }


    public String find() {
        return alphaDao.select();
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1() {
        //新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
        user.setEmail("123321@qq.com");
        user.setHeaderUrl("http://www.gravatar.com/avatar/186?r=PG&s=256&default=identicon");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
        //新增帖子
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        discussPost.setContent("新人报到");
        discussPost.setCreateTime(new Date());
        discussPost.setTitle("hello");
        discussPostMapper.insertDiscussPost(discussPost);

        int x = 0 / 0;

        return "ok";
    }


    public Object save2() {

        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                //新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
                user.setEmail("btea@qq.com");
                user.setHeaderUrl("http://www.gravatar.com/avatar/1112223?r=PG&s=256&default=identicon");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);
                //新增帖子
                DiscussPost discussPost = new DiscussPost();
                discussPost.setUserId(user.getId());
                discussPost.setContent("新人报到");
                discussPost.setCreateTime(new Date());
                discussPost.setTitle("你好");
                discussPostMapper.insertDiscussPost(discussPost);

                int x = 0 / 0;

                return "ok";
            }
        });
    }


}


