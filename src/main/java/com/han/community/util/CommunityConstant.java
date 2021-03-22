package com.han.community.util;

public interface CommunityConstant {

    //定义激活状态
    int ACTIVATION_SUCCESS = 0;
    int ACTIVATION_REPEAT = 1;
    int ACTIVATION_FAILURE = 2;

    //默认状态的登陆超时时间
    int DEFAULT_EXPIREO_SECONDS = 3600*12;

    //记住状态下的登陆超时时间
    int REMBER_EXPIEO_SECONDS = 3600*24*100;

    //实体类型 帖子
    int ENTITY_TYPE_POST = 1;

    //实体类型  评论
    int ENTITY_TYPE_COMMENT = 2;

    //实体类型  用户人数
    int ENTITY_TYPE_USER = 3;

    //主题  评论
    String TOPIC_COMMENT = "comment";
    //点赞
    String TOPIC_LIKE = "like";
    //关注
    String TOPIC_FOLLOW = "follow";

    //系统用户id
    int SYSTEM_USER_ID= 1;

    //普通用户的权限
    String AUTHORITY_USER = "user";

    //管理员
    String AUTHORITY_ADMIN = "admin";


}

