package com.han.community.dao;

import com.han.community.model.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //offset每页起始行的行号  limit每页最多显示的数据
    List<DiscussPost> selectDiscussPost(int userId,int offset , int limit);


    //param用于给参数取别名
    //如果只有一个参数，并且在if中使用，必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id , int commentCount);

    //修改类型
    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);

    //删除
    int deletePostByPostId(int postId);

    //改帖子标题和内容
    int updateTitleAndContent(int id , String title , String content);

}
