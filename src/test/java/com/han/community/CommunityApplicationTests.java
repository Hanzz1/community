package com.han.community;

import com.han.community.config.AlphaConfig;
import com.han.community.dao.AlphaDao;
import com.han.community.dao.AlphaDaoImpl;
import com.han.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)

class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;

    }

    @Test
    public void testApplicationContext(){
        System.out.println("aaaa"+applicationContext);

        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    public void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }


    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat =
                applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }


    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private AlphaDaoImpl alphaDaoimpl;

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI(){

        System.out.println(alphaDao);
        System.out.println(simpleDateFormat);
        System.out.println(alphaDaoimpl);

    }

}
