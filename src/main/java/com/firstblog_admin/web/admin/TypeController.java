package com.firstblog_admin.web.admin;

import com.firstblog_admin.pojo.Type;
import com.firstblog_admin.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String input() {
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String save(Type type){
        Type temp = typeService.saveType(type);
        if(temp == null){

        }else{

        }
        return "redirect:/admin/types";
    }
}
