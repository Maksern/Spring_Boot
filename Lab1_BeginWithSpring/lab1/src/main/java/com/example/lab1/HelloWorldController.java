package com.example.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloWorldController {
    @GetMapping("/hello")
    public String HelloWorld(Model model){
        model.addAttribute("message", "Hello World");
        return "helloWorld";
    }

    @GetMapping("/hrum")
    public String Hrum(Model model){
        model.addAttribute("message", "Hrum");
        return "hrum";
    }
}
