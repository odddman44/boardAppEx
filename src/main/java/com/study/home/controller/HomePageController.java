package com.study.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/members/login";
    }

}
