package com.firstblog_admin.web;

import com.firstblog_admin.NotFoundException;
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
        String blog = null;
        if(blog == null){
            throw new NotFoundException("Blog Not Found");
        }
        return "index";
    }
}
