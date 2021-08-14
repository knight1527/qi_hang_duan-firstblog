package com.firstblog_admin.web.admin;

import com.firstblog_admin.pojo.Tag;
import com.firstblog_admin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author knight1527
 * Created with IntelliJ IDEA.
 * User: suse_QiHang
 * Date: 2021/06/30 15:07
 * Description:
 * Version: V1.0
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable,
                                    Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/backstage_tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String save(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes attributes){
        Tag temp1 = tagService.getTagByName(tag.getName());
        if(tag.getName().equals("")||temp1 != null){
            bindingResult.rejectValue("name","nameError","该分类名称不能为空或者重复");
        }
        if(bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        Tag temp = tagService.saveTag(tag);
        if(temp == null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editSave(@Valid Tag tag, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes attributes){
        Tag temp1 = tagService.getTagByName(tag.getName());
        if(tag.getName().equals("")){
            bindingResult.rejectValue("name","nameError","该分类名称不能为空");
        }
        if(bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        Tag temp = tagService.updateTag(id,tag);
        if(temp == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
