package com.ith.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ith.myblog.domain.Blog;
import com.ith.myblog.service.BlogService;
import com.ith.myblog.service.TagService;
import com.ith.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author muyun
 * @date 2020/4/20 - 22:02
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public ModelAndView blogs(@RequestParam(name = "page",required = true,defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView();
        List<Blog> all = blogService.ListBlog(page);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("types",typeService.ListTypeAll());
        mv.setViewName("admin/blogs");
        return mv;
    }

    @GetMapping("/blogs/input")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("types", typeService.ListTypeAll());
        mv.addObject("tags", tagService.ListTagToAll());
        mv.setViewName("admin/blogs-input");
        return mv;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/input")
    public ModelAndView editInput(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Blog blog = blogService.seachBlogById(id);
        String tagIds = blogService.getTagIds(blog.getId());
        blog.setTagIds(tagIds);
        mv.addObject("types",typeService.ListTypeAll());
        mv.addObject("tags", tagService.ListTagToAll());
        mv.addObject("blog", blog);
        mv.setViewName("admin/blogs-update");
        return mv;
    }

    @PostMapping("/blogs/update")
    public String update(Blog blog) {
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs")
    public String add(Blog blog){
        blogService.saveBlog(blog);
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/seach")
    public ModelAndView seach(Blog blog){
        ModelAndView mv = new ModelAndView();
        List<Blog> all = blogService.seachBlog(blog);
        mv.addObject("types",typeService.ListTypeAll());
        mv.addObject("all", all);
        mv.setViewName("admin/blogs-seach");
        return mv;
    }

}
