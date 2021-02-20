package com.ith.myblog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhouyuanwei
 * @date 2020/8/17 16:53
 * @description：
 */
@Mapper
@Repository
public interface Blog_TagsDao {

    @Insert("insert into t_blog_tags (blogs_id,tags_id) values(#{bid},#{tid})")
    void saveBlog_Tags(long bid,long tid);

    @Select("select tags_id from t_blog_tags where blogs_id=#{bid}")
    List<Long> getBlog_Tags(long bid);

    @Delete("delete from t_blog_tags where blogs_id=#{bid} and tags_id=#{tid}")
    void deleteBlog_Tags(long bid,long tid);

}
