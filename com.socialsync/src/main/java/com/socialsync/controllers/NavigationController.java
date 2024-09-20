package com.socialsync.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.socialsync.entities.Post;
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
	public String openCreatePost(HttpSession session) {
		if (session.getAttribute("username") != null) {
		return "createPost";
		} else {
			return "index";
		}
	}
	
	@GetMapping("/profilePage")
	public String profilePage(HttpSession session, Model model) {
		if (session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			User user = service.getUser(username);
			model.addAttribute("user", user);
			
			List <Post> myPosts = user.getPosts();
			model.addAttribute("myPosts",myPosts);
			
			return "profile";
		} else {
			return "index";
		}
	}
	
	@GetMapping("/updateProfilePage")
	public String updateProfilePage(HttpSession session, Model model) {
		if (session.getAttribute("username") != null) {
		String username = (String) session.getAttribute("username");
		User user = service.getUser(username);
		model.addAttribute("user", user);
		return "/updateProfile";
		} else {
			return "index";
		}
	}
	
	@GetMapping("/findEmailPage")
	public String findEmailPage(HttpSession session) {
		if (session.getAttribute("username") != null) {
		return "findEmail";
		} else {
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.invalidate();
		redirectAttributes.addFlashAttribute("msg", "Logging Out!");
		return "redirect:/";
	}
	
	@GetMapping("/updatePasswordPage")
	public String updatePasswordPage() {
		return "changePassword";
	}
	

}
