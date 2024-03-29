package com.han.community.service;

import com.han.community.model.User;
import com.han.community.util.CommunityConstant;
import com.han.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FollowService implements CommunityConstant {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    public void follow(int userid , int entityType , int entityId){
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String followeeKey = RedisKeyUtil.getFolloweeKey(userid,entityType);
                String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);
                //启用事务
                redisOperations.multi();

                redisOperations.opsForZSet().add(followeeKey,entityId, System.currentTimeMillis());
                redisOperations.opsForZSet().add(followerKey,userid,System.currentTimeMillis());


                return redisOperations.exec();
            }
        });
    }

    //取消关注
    public void unfollow(int userid , int entityType , int entityId){
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String followeeKey = RedisKeyUtil.getFolloweeKey(userid,entityType);
                String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);

                redisOperations.multi();

                redisOperations.opsForZSet().remove(followeeKey,entityId);
                redisOperations.opsForZSet().remove(followerKey,userid);


                return redisOperations.exec();
            }
        });
    }

    //查询用户关注实体的数量
    public long findFolloweeCount(int userId, int entityType) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
        return redisTemplate.opsForZSet().zCard(followeeKey);
    }

    //查询实体的粉丝数量
    public long findFollowerCount(int entityType, int entityId) {
        String followerKey = RedisKeyUtil.getFollowerKey(entityType, entityId);
        return redisTemplate.opsForZSet().zCard(followerKey);
    }

    //查询当前用户是否已关注该实体
    public boolean hasFollowed(int userId, int entityType , int entityId){
        String followeeKey  = RedisKeyUtil.getFolloweeKey(userId, entityType);
        return redisTemplate.opsForZSet().score(followeeKey,entityId) != null;
    }

    //查询某用户关注的人
    public List<Map<String , Object>> findFollowees(int userId , int offset , int limit){
        String followeeKey  = RedisKeyUtil.getFolloweeKey(userId,ENTITY_TYPE_USER);
        Set<Integer> sets = redisTemplate.opsForZSet().reverseRange(followeeKey, offset, limit + offset - 1);
        if (sets == null){
            return null;
        }
        List<Map<String , Object>> list = new ArrayList<>();
        for (Integer set : sets){
            Map<String, Object> map = new HashMap<>();
            final User user = userService.findUserById(set);
            map.put("user" , user);
            final Double score = redisTemplate.opsForZSet().score(followeeKey, set);
            map.put("followTime",new Date(score.longValue()));
            list.add(map);
        }
        return list;
    }

    //查询某用户的粉丝
    public List<Map<String , Object >> findFollowers(int userId , int offset , int limit){
        String followerKey = RedisKeyUtil.getFollowerKey(ENTITY_TYPE_USER,userId);
        Set<Integer> targetIds = redisTemplate.opsForZSet().reverseRange(followerKey,offset,offset+limit-1);
        if (targetIds == null){
            return  null;
        }
        List<Map<String , Object>> list = new ArrayList<>();
        for (Integer targetId : targetIds){
            Map<String, Object> map = new HashMap<>();
            final User user = userService.findUserById(targetId);
            map.put("user" , user);
            final Double score = redisTemplate.opsForZSet().score(followerKey, targetId);
            map.put("followTime",new Date(score.longValue()));
            list.add(map);
        }
        return list;

    }

}
