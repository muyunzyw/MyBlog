package com.ith.myblog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.ith.myblog.domain.Type;
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
public class TypeController {

    @Autowired
    private TypeService typeService;
    
    @GetMapping("/types")
    public ModelAndView list(@RequestParam(name = "page",required = true,defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView();
        List<Type> all = typeService.ListType(page);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("admin/types");
        return mv;
    }

    @GetMapping("/types/input")
    public String toAdd(){
        return "admin/types-input";
    }

    @PostMapping("/types/add")
    public String insert(Type type, BindingResult result){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName == null){
            typeService.saveType(type);
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Type type = typeService.getType(id);
        model.addAttribute("type", type);
        return "admin/types-update";
    }

    @PostMapping("/types/update")
    public String update(Type type) {
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }

}
