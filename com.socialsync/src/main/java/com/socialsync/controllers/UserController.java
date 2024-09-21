package com.socialsync.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			session.setAttribute("username", username);
			redirectAttributes.addFlashAttribute("msg", "Invalid credentials! Please try again.");
			return "redirect:/";
		}
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam String dob, @RequestParam String name, @RequestParam String gender,
			@RequestParam String city, @RequestParam String bio, @RequestParam String linkedin,
			@RequestParam String github, @RequestParam String x, @RequestParam String website,
			@RequestParam String grade, @RequestParam String occupation, @RequestParam MultipartFile photo,
			@RequestParam MultipartFile backgroundPhoto, HttpSession session, Model model) {
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

	@GetMapping("/viewProfile")
	public String viewProfile(@RequestParam("username") String username, Model model, HttpSession session) {
	    User user = service.getUser(username);

	    model.addAttribute("user", user);
	    model.addAttribute("posts", user.getPosts());

	    return "viewProfile";
	
	
	

	}

	@PostMapping("/changePassword")
	public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			@RequestParam String confirmPassword, HttpSession session, RedirectAttributes redirectAttributes) {
		String username = (String) session.getAttribute("username");
		boolean oldPasswordValid = service.validateUser(username, oldPassword);

		if (oldPasswordValid) {
			if (newPassword.equals(confirmPassword)) {
				service.updatePassword(username, newPassword);
				redirectAttributes.addFlashAttribute("msg", "Your Password Changed Successfully!");
				return "redirect:/";
			} else {
				return "changePassword";
			}
		} else {
			return "changePassword";
		}
	}
	
	
	
	@PostMapping("/verifyEmail")
	public String verifyEmail(HttpSession session, @RequestParam String email, Model model) {
		if (session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			User user = service.getUser(username);
			boolean status = service.verifyEmail(username, email);
			if (status == true) {
				 model.addAttribute("username", username);
				return "resetPassword";
			} else {
				return "login";
			}
		} else {
			return "index";
		}
	}
	
	
	@PostMapping("/resetPassword")
	public String resetPassword(HttpSession session, @RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {
		
		if (session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			User user = service.getUser(username);
			if (newPassword.equals(confirmPassword)) {
		        service.resetPassword(username, newPassword);
		        return "index";
		    } else {
		        System.out.println("Password do not match");
		        return "resetPassword";
		    }
		} else {
			return "index";
		}
	}

}
