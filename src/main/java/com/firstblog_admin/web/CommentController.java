package com.firstblog_admin.web;

import com.firstblog_admin.pojo.Comment;
import com.firstblog_admin.service.BlogService;
import com.firstblog_admin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author knight1527
 * Created with IntelliJ IDEA.
 * User: suse_QiHang
 * Date: 2021/06/30 15:07
 * Description:
 * Version: V1.0
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avator}")
    private String avator;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return"blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        Long blogId =  comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setAvator(avator);
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
