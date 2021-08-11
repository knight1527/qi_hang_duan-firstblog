package com.firstblog_admin.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class BlogsController {
    @GetMapping("/blogs")
    public String list(){
        return "admin/blogs";
    }
}
