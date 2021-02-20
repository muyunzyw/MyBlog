package com.ith.myblog.controller;

import com.github.pagehelper.PageInfo;
import com.ith.myblog.domain.Blog;
import com.ith.myblog.domain.Tag;
import com.ith.myblog.domain.Type;
import com.ith.myblog.service.BlogService;
import com.ith.myblog.service.TagService;
import com.ith.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/20 - 17:28
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "page",required = true,defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView();
        List<Blog> all = blogService.ListBlog(page);
        List<Type> types = typeService.ListTypeLimit(5);
        List<Tag> tags = tagService.ListTagLimit(5);
        List<Blog> recommendedBlogs = blogService.getRecommendedBlogs(5);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("types",types);
        mv.addObject("tags",tags);
        mv.addObject("recommendedBlogs",recommendedBlogs);
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam String query) {
        ModelAndView mv = new ModelAndView();
        List<Blog> blogs = blogService.seachBlogByQuery(query);
        System.out.println(blogs);
        mv.addObject("blogs",blogs);
        mv.addObject("query",query);
        mv.setViewName("search");
        return mv;
    }

    @GetMapping("/blog/{id}")
    public ModelAndView blog(@PathVariable long id){
        ModelAndView mv = new ModelAndView();
        Blog blog = blogService.seachBlogByIdIndex(id);
        mv.addObject("blog",blog);
        mv.setViewName("blog");
        return mv;
    }
}
