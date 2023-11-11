package com.example.sport_competitions.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/login")
	public String loginPage(){
		return "user/login";
	}

    @GetMapping("/*")
	public String homePage(){
		return "redirect:/game/page/1";
	}
}
