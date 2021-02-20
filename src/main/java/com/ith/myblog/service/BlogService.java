package com.ith.myblog.service;

import com.ith.myblog.domain.Blog;
import com.ith.myblog.domain.Tag;

import java.util.List;

/**
 * @author zhouyuanwei
 * @date 2020/5/26 - 14:35
 */
public interface BlogService {

    List<Blog> ListBlog(int page);

    Blog seachBlogById(Long id);

    List<Blog> getBlogAll();

    List<Blog> getRecommendedBlogs(int limit);

    Blog getBlogByName(String name);

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> seachBlog(Blog blog);

    String getTagIds(long bid);

    List<Blog> seachBlogByQuery(String query);

    Blog seachBlogByIdIndex(Long id);

}
