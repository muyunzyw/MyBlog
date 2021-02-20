package com.ith.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ith.myblog.domain.Tag;
import com.ith.myblog.domain.Type;
import com.ith.myblog.service.TagService;
import com.ith.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/21 - 10:07
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/tags")
    public ModelAndView list(@RequestParam(name = "page",required = true,defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView();
        List<Tag> all = tagService.ListTag(page);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/tags");
        return mv;
    }

    @GetMapping("/tags/input")
    public String toAdd(){
        return "admin/tags-input";
    }

    @PostMapping("/tags/add")
    public String insert(Tag tag, BindingResult result){
        Tag ByName = tagService.getTagByName(tag.getName());
        if(ByName == null){
            tagService.saveTag(tag);
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag", tag);
        return "admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String update(Tag tag) {
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }

}
