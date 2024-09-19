package com.socialsync.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.socialsync.entities.Post;
import com.socialsync.entities.User;
import com.socialsync.services.PostService;
import com.socialsync.services.UserService;

import jakarta.servlet.http.HttpSession;

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
	public String validateUser(@RequestParam String username, @RequestParam String password,
			RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		boolean status = service.validateUser(username, password);
		 if (status) {
		        redirectAttributes.addFlashAttribute("msg", "Login Successful!");
		        session.setAttribute("username", username);
		        model.addAttribute("session", session);
		        List<Post> allPosts = postService.getAllPosts();
			    model.addAttribute("allPosts", allPosts);
		        return "redirect:/homePage";
		    } else {
		        redirectAttributes.addFlashAttribute("msg", "Invalid credentials! Please try again.");
		        return "redirect:/";
		    }
	}
	
	@GetMapping("/profilePage")
	public String profilePage(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
//		String name = (String) session.getAttribute("name");
		User user = service.getUser(username);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob, @RequestParam String name,
			@RequestParam String gender, @RequestParam String city, @RequestParam String bio, 
			@RequestParam String linkedin, @RequestParam String github, @RequestParam String x, 
			@RequestParam String website, @RequestParam String grade, @RequestParam String occupation, @RequestParam MultipartFile photo, @RequestParam MultipartFile backgroundPhoto,
			HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		
		User user = service.getUser(username);
		
		user.setDob(dob);
		user.setCity(city);
		user.setBio(bio);
		user.setGithub(github);
		user.setLinkedin(linkedin);
		user.setX(x);
		user.setWebsite(website);
		user.setGender(gender);
		user.setGrade(grade);
		user.setOccupation(occupation);
		
		
		 if (!photo.isEmpty()) {
		        try {                        
		            user.setPhoto(photo.getBytes());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		
		if (!backgroundPhoto.isEmpty()) {
		    try {
		        user.setBackgroundPhoto(backgroundPhoto.getBytes());
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		service.updateUser(user);
		model.addAttribute("user", user);
		
		return "profile";
		
	}
	
	
	
	

}
