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

import com.socialsync.entities.Post;
import com.socialsync.services.PostService;

@Controller
public class PostController {

	@Autowired
	PostService service;

	@PostMapping("/createPost")
	public String createPost(@RequestParam("caption") String caption, @RequestParam("photo") MultipartFile photo, Model model)
			throws IOException {

		Post post = new Post();
		post.setCaption(caption);
		try {
			post.setPhoto(photo.getBytes());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		List<Post> allPosts = service.getAllPosts();
	    model.addAttribute("allPosts", allPosts);

		service.createPost(post);

		return "home";
	}
	
	
	@GetMapping("/homePage") 
	public String getPosts(Model model) {
		List<Post> allPosts = service.getAllPosts();
	    model.addAttribute("allPosts", allPosts);
	    return "home";
	}
	
	

}
