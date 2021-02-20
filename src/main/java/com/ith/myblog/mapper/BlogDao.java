package com.ith.myblog.mapper;

import com.ith.myblog.domain.Blog;
import com.ith.myblog.domain.Type;
import com.ith.myblog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhouyuanwei
 * @date 2020/5/26 - 14:37
 */
@Mapper
@Repository
public interface BlogDao {

    @Select("select * from t_blog order by update_time desc")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id" ,property = "type",javaType = Type.class,one = @One(select = "com.ith.myblog.mapper.TypeDao.getType")),
            @Result(column = "user_id" ,property = "user",javaType = User.class,one = @One(select = "com.ith.myblog.mapper.UserDao.getUser"))
    })
    List<Blog> ListBlog();

    @Select("select * from t_blog where id=#{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id" ,property = "type",javaType = Type.class,one = @One(select = "com.ith.myblog.mapper.TypeDao.getType"))
    })
    Blog seachBlogById(Long id);

    @Select("select * from t_blog where id=#{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id" ,property = "type",javaType = Type.class,one = @One(select = "com.ith.myblog.mapper.TypeDao.getType")),
            @Result(column = "user_id" ,property = "user",javaType = User.class,one = @One(select = "com.ith.myblog.mapper.UserDao.getUser"))
    })
    Blog seachBlogByIdIndex(Long id);

    List<Blog> getBlogAll();

    @Select("select id, title from t_blog where recommend = 1 order by update_time desc limit #{limit}")
    List<Blog> getRecommendedBlogs(int limit);

    @Select("select * from t_blog where name=#{name}")
    Blog getBlogByName(String name);

    void saveBlog(Blog blog);

    //(appreciation,commentabled,content,description,first_picture,flag,published,recommend,share_statement,title,create_time,update_time,views,type_id)
    //            values(#{appreciation},#{commentabled},#{content},#{description},#{firstPicture},#{flag},#{published},#{recommend},#{shareStatement},#{title},#{createTime},#{updateTime},#{views},#{type.id})

    @Update("update t_blog set appreciation=#{appreciation},commentabled=#{commentabled},content=#{content},description=#{description}," +
            "first_picture=#{firstPicture},flag=#{flag},recommend=#{recommend},share_statement=#{shareStatement}," +
            "title=#{title},update_time=#{updateTime},type_id=#{type.id} where id=#{id}")
    void updateBlog(Blog blog);

    @Delete("delete from t_blog where id=#{id}")
    void deleteBlog(Long id);

    List<Blog> seachBlog(Blog blog);

    @Select("select * from t_blog where title like \"%\"#{query}\"%\" or description like \"%\"#{query}\"%\"  order by update_time desc")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id" ,property = "type",javaType = Type.class,one = @One(select = "com.ith.myblog.mapper.TypeDao.getType")),
            @Result(column = "user_id" ,property = "user",javaType = User.class,one = @One(select = "com.ith.myblog.mapper.UserDao.getUser"))
    })
    List<Blog> seachBlogByQuery(String query);
}
