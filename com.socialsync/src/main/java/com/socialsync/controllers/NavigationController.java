package com.socialsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/home")
    public String homePage() {
        return "home";
    }
	
	@GetMapping("/createPost")
	public String openCreatePost() {
		return "createPost";
	}

}
