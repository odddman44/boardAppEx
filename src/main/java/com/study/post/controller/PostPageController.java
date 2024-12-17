package com.study.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post")
public class PostPageController {

    // 게시글 리스트 페이지
    @GetMapping("/list")
    public String openPostList() {
        return "post/list";
    }

    // 게시글 작성 페이지
    @GetMapping("/write")
    public String openPostWrite(@RequestParam(required = false) final Long id, Model model) {
        model.addAttribute("id", id);
        return "post/write";
    }

    // 게시글 상세 페이지
    @GetMapping("/view/{id}")
    public String openPostView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "post/view";
    }
}
