package com.socialsync.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/createPost")
	public String openCreatePost() {
		return "createPost";
	}
	
	@GetMapping("/profilePage")
	public String profilePage() {
		return "profile";
	}
	
	

}
