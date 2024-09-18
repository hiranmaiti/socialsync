package com.socialsync.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.socialsync.entities.User;
import com.socialsync.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/createPost")
	public String openCreatePost() {
		return "createPost";
	}
	
	@GetMapping("/updateProfilePage")
	public String updateProfilePage(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		User user = service.getUser(username);
		model.addAttribute("user", user);
		return "/updateProfile";
	}
	
	

}
