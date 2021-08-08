package com.firstblog_admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author knight1527
 * Created with IntelliJ IDEA.
 * User: suse_QiHang
 * Date: 2021/06/30 15:07
 * Description:
 * Version: V1.0
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        /*String blog = null;
        if(blog == null){
            throw new NotFoundException("Blog Not Found");
        }*/

        return "index";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
    @GetMapping("/aboutMe")
    public String aboutMe(){
        return "aboutMe";
    }
    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }
    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }
    @GetMapping("/types")
    public String types(){
        return "types";
    }
    @GetMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }
    @GetMapping("/blogs-input")
    public String blogs_input(){
        return "../debugHtml/blogs-input";
    }
}
