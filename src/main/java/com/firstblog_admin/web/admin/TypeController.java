package com.firstblog_admin.web.admin;

import com.firstblog_admin.pojo.Type;
import com.firstblog_admin.service.TypeService;
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
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable,
                                    Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/backstage_types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String save(@Valid Type type, BindingResult bindingResult, RedirectAttributes attributes){
        Type temp1 = typeService.getTypeByName(type.getName());
        if(type.getName().equals("")||temp1 != null){
            bindingResult.rejectValue("name","nameError","该分类名称不能为空或者重复");
        }
        if(bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        Type temp = typeService.saveType(type);
        if(temp == null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editSave(@Valid Type type, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes attributes){
        Type temp1 = typeService.getTypeByName(type.getName());
        if(type.getName().equals("")){
            bindingResult.rejectValue("name","nameError","该分类名称不能为空");
        }
        if(bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        Type temp = typeService.updateType(id,type);
        if(temp == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
