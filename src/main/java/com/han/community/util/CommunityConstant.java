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

}
