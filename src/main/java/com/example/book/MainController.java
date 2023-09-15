package com.example.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mars")
public class MainController {
    @GetMapping("/admin")
    public String mainPage(){
        return "index";
    }
}
