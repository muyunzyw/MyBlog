package com.ith.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ith.myblog.domain.Blog;
import com.ith.myblog.mapper.BlogDao;
import com.ith.myblog.mapper.Blog_TagsDao;
import com.ith.myblog.service.BlogService;
import com.ith.myblog.util.MarkdownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhouyuanwei
 * @date 2020/5/26 - 14:35
 */
@Service
public class BlogServicelmpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private Blog_TagsDao blog_tagsDao;

    @Override
    public List<Blog> ListBlog(int page) {
        PageHelper.startPage(page, 5);
        return blogDao.ListBlog();
    }

    @Override
    public Blog seachBlogById(Long id) {
        return blogDao.seachBlogById(id);
    }

    @Override
    public Blog seachBlogByIdIndex(Long id) {
        Blog blog = blogDao.seachBlogByIdIndex(id);
        String content = blog.getContent();
        String html_content = MarkdownUtils.markdownToHtmlExtensions(content);
        System.out.println(html_content);
        blog.setContent(html_content);
        return blog;
    }

    @Override
    public List<Blog> getBlogAll() {
        return blogDao.getBlogAll();
    }

    @Override
    public List<Blog> getRecommendedBlogs(int limit) {
        return blogDao.getRecommendedBlogs(limit);
    }

    @Override
    public Blog getBlogByName(String name) {
        return null;
    }

    @Override
    public void saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogDao.saveBlog(blog);
        Long bid = blog.getId();

        String[] tagIds = blog.getTagIds().split(",");
        for(String tid : tagIds){
            blog_tagsDao.saveBlog_Tags(bid, Long.parseLong(tid));
        }
    }

    @Override
    public void updateBlog(Blog blog) {
//        Blog b = seachBlogById(blog.getId());
//        if (b == null){
//            return;
//        }
//        BeanUtils.copyProperties(blog, b,);
        if(blog.getTagIds() != null){
            updateBlog_Tags(blog.getId(), blog.getTagIds());
        }
        blog.setUpdateTime(new Date());
        blogDao.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> seachBlog(Blog blog) {
        return blogDao.seachBlog(blog);
    }

    @Override
    public String getTagIds(long bid) {
        List<Long> tags = blog_tagsDao.getBlog_Tags(bid);
        if(tags.size() == 0){
            return null;
        }
        String tagIds = "";
        for(int i = 0; i < tags.size(); i++){
            tagIds = tagIds + tags.get(i);
            if(i != tags.size()-1){
                tagIds = tagIds + ",";
            }
        }
        return tagIds;
    }

    @Override
    public List<Blog> seachBlogByQuery(String query) {
        return blogDao.seachBlogByQuery(query);
    }

    // 更新 处理Blog_Tags
    public void updateBlog_Tags(Long bid,String tIds){
        String[] tagIds = tIds.split(",");
        List<Long> new_tags = new ArrayList<>();
        for (int i = 0; i < tagIds.length; i++){
            new_tags.add(Long.parseLong(tagIds[i]));
        }
        List<Long> old_tags = blog_tagsDao.getBlog_Tags(bid);
        // 删除多余 倒序避免下标问题
        for(int i = old_tags.size()-1; i >= 0; i--){
            Long o_tid = old_tags.get(i);
            if(!new_tags.contains(o_tid)){
                blog_tagsDao.deleteBlog_Tags(bid,o_tid);
                old_tags.remove(o_tid);
            }
        }
        // 新增
        for(Long n_tid : new_tags){
            if(!old_tags.contains(n_tid)){
                blog_tagsDao.saveBlog_Tags(bid, n_tid);
            }
        }
    }

}
