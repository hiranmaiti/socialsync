package com.socialsync.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.socialsync.entities.Post;
import com.socialsync.entities.User;
import com.socialsync.services.PostService;
import com.socialsync.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	PostService postService;
	
	@PostMapping("/signUp")
	public String addUser(User user, RedirectAttributes redirectAttributes) {
		String username = user.getUsername();
		String email = user.getEmail();
		boolean status = service.userExists(username, email);
		if (!status) {
	        service.addUser(user);
	        redirectAttributes.addFlashAttribute("msg", "Signup successful! You can now log in.");
	    } else {
	        redirectAttributes.addFlashAttribute("msg", "User already exists! Please try logging in.");
	    }
		return "redirect:/";
	}

	@PostMapping("/login")
	public String validateUser(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes, Model model ) {
		boolean status = service.validateUser(username, password);
		 if (status) {
		        redirectAttributes.addFlashAttribute("msg", "Login Successful!");
		        List<Post> allPosts = postService.getAllPosts();
			    model.addAttribute("allPosts", allPosts);
		        return "redirect:/homePage";
		    } else {
		        redirectAttributes.addFlashAttribute("msg", "Invalid credentials! Please try again.");
		        return "redirect:/";
		    }
	}
	
	@GetMapping("/profilePage")
	public String profilePage() {
		return "profile";
	}

}
